package com.jxkj.mall.sdkill.seckill.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.jxkj.mall.sdkill.entity.SmsGoods;
import com.jxkj.mall.sdkill.entity.SmsSeckillSession;
import com.jxkj.mall.sdkill.seckill.service.SecKillService;
import com.jxkj.mall.sdkill.seckill.to.mq.SecKillOrderTO;
import com.jxkj.mall.sdkill.service.ISmsGoodsService;
import com.jxkj.mall.sdkill.service.ISmsSeckillSessionService;
import com.jxkj.mall.sdkill.seckill.to.SecSessionSkuRedisTO;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RSemaphore;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 项目: skukill
 * <p>
 * 功能描述: 秒杀实现类
 *
 * @author: WuChengXing
 * @create: 2020-08-27 00:01
 **/
@Service
@Slf4j
public class SecKillServiceImpl implements SecKillService {

    @Autowired
    private ISmsSeckillSessionService smsSeckillSessionService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ISmsGoodsService goodsService;

    @Autowired
    private RedissonClient redissonClient;

    /**
     * redis 中存入活动信息的前缀
     */
    public static final String SESSIONS_CACHE_PREFIX = "seckill:sessions:";

    /**
     * redis 存入的商品详情信息
     */
    public static final String SKU_CACHE_PREFIX = "seckill:skus:";

    /**
     * redis 存入商品的库存信息 + 商品随机码
     */
    public static final String SKU_STOCK_PREFIX = "seckill:stock:";


    /**
     * 上架三天的活动
     */
    @Override
    public void uploadThreeDaySession() {
        // 获取需要在最近三天内上架的商品
        List<SmsSeckillSession> threeDaySession = smsSeckillSessionService.getThreeDaySession();
        // 1. 将场次信息保存到redis中
        this.saveSessionInfo(threeDaySession);
        // 2. 将商品信息保存到redis中
        this.saveSessionSkuInfo(threeDaySession);
    }

    /**
     * 1. 将场次信息保存到redis中
     *
     * @param threeDaySession
     */
    private void saveSessionInfo(List<SmsSeckillSession> threeDaySession) {
        threeDaySession.stream().forEach(smsSeckillSession -> {
            long start = smsSeckillSession.getStartTime().getTime();
            long end = smsSeckillSession.getEndTime().getTime();
            // 以当前场次的开始时间 + 结束时间 + 上前缀作为缓存的key
            String key = SESSIONS_CACHE_PREFIX + start + "_" + end;
            // 判断redis是否已经存在此票
            Boolean aBoolean = redisTemplate.hasKey(key);
            if (!aBoolean) {
                // 将该场次与商品的关联关系的id存入redis
                List<String> collect = smsSeckillSession.getSmsSeckillSkuRelations().stream()
                        .map(smsSeckillSkuRelation ->
                                smsSeckillSkuRelation.getPromotionSessionId() + "_" + smsSeckillSkuRelation.getSkuId().toString())
                        .collect(Collectors.toList());
                // 以list结构存入
                redisTemplate.opsForList().leftPushAll(key, collect);
            }
        });
    }

    /**
     * 2. 将商品信息保存到redis中
     *
     * @param threeDaySession
     */
    private void saveSessionSkuInfo(List<SmsSeckillSession> threeDaySession) {
        threeDaySession.stream().forEach(smsSeckillSession -> {
            BoundHashOperations<String, String, String> opsHash = redisTemplate.boundHashOps(SKU_CACHE_PREFIX);
            smsSeckillSession.getSmsSeckillSkuRelations().stream()
                    .forEach(smsSeckillSkuRelation -> {
                        // 生成随机字符串
                        String token = UUID.randomUUID().toString().replaceAll("-", "");
                        // 缓存中不存在再往里面设置数据
                        if (!opsHash.hasKey(smsSeckillSkuRelation.getPromotionSessionId() + "_" + smsSeckillSkuRelation.getSkuId().toString())) {
                            SecSessionSkuRedisTO secSessionSkuRedisTO = new SecSessionSkuRedisTO();
                            // 1. sku的基本信息
                            SmsGoods sku = goodsService.getById(smsSeckillSkuRelation.getSkuId());
                            secSessionSkuRedisTO.setSmsGoods(sku);

                            // 2. 秒杀活动信息
                            BeanUtils.copyProperties(smsSeckillSkuRelation, secSessionSkuRedisTO);

                            // 3. 设置当前秒杀活动的时间信息
                            secSessionSkuRedisTO.setStartTime(smsSeckillSession.getStartTime().getTime());
                            secSessionSkuRedisTO.setEndTime(smsSeckillSession.getEndTime().getTime());

                            // 4. 防伪随机码
                            secSessionSkuRedisTO.setRandomCode(token);

                            // 5. 设置库存信号量，防止超卖
                            RSemaphore semaphore = redissonClient.getSemaphore(SKU_STOCK_PREFIX + token);
                            // 商品可以秒杀的数量作为信号量
                            semaphore.trySetPermits(smsSeckillSkuRelation.getSeckillCount().intValue());

                            String s = JSON.toJSONString(secSessionSkuRedisTO);
                            opsHash.put(smsSeckillSkuRelation.getPromotionSessionId() + "_" + smsSeckillSkuRelation.getSkuId().toString(), s);
                        }

                    });
        });
    }

    /**
     * 获取当前时间正在参与的秒杀活动
     *
     * @return
     */
    @Override
    public List<SecSessionSkuRedisTO> getCurrentSecKillSkus() {
        // 1. 获取当前时间、确定当前时间对应的场次
        long time = System.currentTimeMillis();
        // 2. 从redis里面拿到所有的带前缀 SESSIONS_CACHE_PREFIX 的key
        Set<String> keys = redisTemplate.keys(SESSIONS_CACHE_PREFIX + "*");
        for (String key : keys) {
            // 将前缀去掉
            String s = key.replaceAll(SESSIONS_CACHE_PREFIX, "");
            // 获取拼接的时间如：123456789_234567899
            String[] timeArr = s.split("_");
            // 第一个是开始时间
            long start = Long.parseLong(timeArr[0]);
            // 第二个是结束时间
            long end = Long.parseLong(timeArr[1]);
            // 取出时间区间内的数据
            if (time >= start && time <= end) {
                // 取出redis里面list结构中的数据 200条 1_1  2_2 等，作为商品的hash key
                List<String> range = redisTemplate.opsForList().range(key, -100, 100);
                // 获取到对hash结构方便的操作
                BoundHashOperations<String, String, String> hashOperations = redisTemplate.boundHashOps(SKU_CACHE_PREFIX);
                // 拿到hash里面保存的所有活动详情信息
                List<String> strings = hashOperations.multiGet(range);
                // 返回前端需要的数据类型
                List<SecSessionSkuRedisTO> collect = strings.stream().map(s1 -> {
                    SecSessionSkuRedisTO secSessionSkuRedisTO = JSON.parseObject(s1, SecSessionSkuRedisTO.class);
                    return secSessionSkuRedisTO;
                }).collect(Collectors.toList());
                return collect;
            }
        }
        return null;
    }

    /**
     * 拿到该商品的秒杀信息
     *
     * @param skuId
     * @return
     */
    @Override
    public SecSessionSkuRedisTO getSeckillSkuInfo(Long skuId) {
        // 绑定hash操作
        BoundHashOperations<String, String, String> hashOps = redisTemplate.boundHashOps(SKU_CACHE_PREFIX);
        // 拿到所有的keys
        Set<String> keys = hashOps.keys();
        // 匹配 6_4 这类的数据 key
        String pattern = "\\d_" + skuId;
        // 如果不为空
        if (null != keys && keys.size() > 0) {
            for (Object key : keys) {
                // 匹配是否存在这样的数据
                if (Pattern.matches(pattern, (CharSequence) key)) {
                    // 拿到redis里面保存的数据
                    String s = hashOps.get(key);
                    SecSessionSkuRedisTO secSessionSkuRedisTO = JSON.parseObject(s, SecSessionSkuRedisTO.class);
                    /**
                     * 判断当前时间是否已经在活动时间范围内== 在的话 就返回随机码给前端
                     */
                    // 当前时间
                    long millis = System.currentTimeMillis();
                    // 活动开始时间
                    Long startTime = secSessionSkuRedisTO.getStartTime();
                    // 活动结束时间
                    Long endTime = secSessionSkuRedisTO.getEndTime();
                    // 在活动时间外，设置器校验码为空
                    if (millis < startTime || millis > endTime) {
                        secSessionSkuRedisTO.setRandomCode(null);
                    }
                    return secSessionSkuRedisTO;
                }
            }
        }
        return null;
    }

    /**
     * 秒杀
     *
     * @param killId
     * @param key
     * @param num
     * @param userId (这个应该是系统自动校验的，应该是登录状态才能进行秒杀)
     * @return
     */
    @Override
    public String kill(String killId, String key, Integer num, Long userId) {
        BoundHashOperations<String, String, String> hashOps = redisTemplate.boundHashOps(SKU_CACHE_PREFIX);
        String s = hashOps.get(killId);
        // 如果redis中不存在这个商品
        if (StrUtil.hasBlank(s)) {
            return "商品不存在！请选择其他的商品！";
        }
        // 拿到redis中的数据
        SecSessionSkuRedisTO secSessionSkuRedisTO = JSON.parseObject(s, SecSessionSkuRedisTO.class);
        // 当前时间
        long millis = System.currentTimeMillis();
        // 活动开始时间
        Long startTime = secSessionSkuRedisTO.getStartTime();
        // 活动结束时间
        Long endTime = secSessionSkuRedisTO.getEndTime();
        // redis中用于该产品期间内用户限购的过期时间 ttl
        long ttl = endTime - startTime;
        /**
         * 1. 时间校验
         */
        if (millis < startTime && millis > endTime) {
            return "秒杀活动已结束！";
        }
        /**
         * 2. 进行参数校验
         */
        // 进行随机码校验
        String randomCode = secSessionSkuRedisTO.getRandomCode();
        // 进行商品校验
        String skuId = secSessionSkuRedisTO.getPromotionSessionId() + "_" + secSessionSkuRedisTO.getSkuId();
        // 校验通过
        if (!randomCode.equals(key) || !skuId.equals(killId)) {
            return "参数不正确！";
        }

        /**
         * 3. 校验购买的数量是否合法
         */
        if (num > secSessionSkuRedisTO.getSeckillLimit().intValue()) {
            return "购买数量大于限购数量";
        }

        /**
         * 4. 幂等性校验，限制每个人的购买数量，只能购买指定数据（前端写死）
         *    如果秒杀成功 == 则去redis中进行占位
         */
        // 1_2_2
        String userKey = userId + "_" + skuId;
        // 在redis中占位 == 如果存在此key则返回false，不存在时返回true
        Boolean aBoolean = redisTemplate.opsForValue().setIfAbsent(userKey, num.toString(), ttl, TimeUnit.MILLISECONDS);
        if (!aBoolean) {
            return "你已经购买过一次了，请等待下次秒杀活动！";
        }

        /**
         * 5. 判断库存是否还有
         */
        RSemaphore semaphore = redissonClient.getSemaphore(SKU_STOCK_PREFIX + secSessionSkuRedisTO.getRandomCode());
        try {
            // 进行尝试抢占信号量（库存）
            boolean hasStock = semaphore.tryAcquire(num, 100, TimeUnit.MILLISECONDS);
            // 如果有库存，进行下单操作
            if (hasStock) {
                String orderNo = IdWorker.getTimeId();
                // 发送给mq的对象
                SecKillOrderTO secKillOrderTO = new SecKillOrderTO();
                secKillOrderTO.setNum(num);
                secKillOrderTO.setOrderNo(orderNo);
                secKillOrderTO.setSkuId(secSessionSkuRedisTO.getSkuId());
                secKillOrderTO.setPromotionSessionId(secSessionSkuRedisTO.getPromotionSessionId());
                secKillOrderTO.setUserId(userId);
                return orderNo;
            }else {
                // 购买失败则删除用户信息，让其可再次购买
                redisTemplate.delete(userKey);
                return "库存不足！";
            }
        } catch (InterruptedException e) {
            return "秒杀失败！";
        }

    }
}
