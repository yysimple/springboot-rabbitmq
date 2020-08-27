package com.jxkj.mall.sdkill.seckill.service.impl;

import com.alibaba.fastjson.JSON;
import com.jxkj.mall.sdkill.entity.SmsGoods;
import com.jxkj.mall.sdkill.entity.SmsSeckillSession;
import com.jxkj.mall.sdkill.seckill.service.SecKillService;
import com.jxkj.mall.sdkill.service.ISmsGoodsService;
import com.jxkj.mall.sdkill.service.ISmsSeckillSessionService;
import com.jxkj.mall.sdkill.to.SecSessionSkuRedisTO;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RSemaphore;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
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
    public static final String SKU_CACHE_PREFIX = "seckill:sku:";

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


}
