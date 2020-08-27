package com.jxkj.mall.sdkill.seckill.service;

import com.jxkj.mall.sdkill.seckill.to.SecSessionSkuRedisTO;

import java.util.List;

/**
 * 项目: skukill
 * <p>
 * 功能描述: 秒杀接口类
 *
 * @author: WuChengXing
 * @create: 2020-08-26 23:59
 **/
public interface SecKillService {

    /**
     * 功能描述: 上架三天的活动
     * @author WuChengxing
     * @date 2020/8/27
     * @param
     * @return void
     */
    void uploadThreeDaySession();

    /**
     * 功能描述: 获取当前时间正在参与的秒杀活动
     * @author WuChengxing
     * @date 2020/8/27
     * @param
     * @return java.util.List<com.jxkj.mall.sdkill.to.SecSessionSkuRedisTO>
     */
    List<SecSessionSkuRedisTO> getCurrentSecKillSkus();

    /**
     * 功能描述: 拿到该商品的秒杀信息
     * @author WuChengxing
     * @date 2020/8/28
     * @param skuId
     * @return com.jxkj.mall.sdkill.to.SecSessionSkuRedisTO
     */
    SecSessionSkuRedisTO getSeckillSkuInfo(Long skuId);

    /**
     * 功能描述: 秒杀
     * @author WuChengxing
     * @date 2020/8/28
     * @param killId
     * @param key
     * @param num
     * @param userId (这个应该是系统自动校验的，应该是登录状态才能进行秒杀)
     * @return java.lang.String
     */
    String kill(String killId, String key, Integer num, Long userId);

}
