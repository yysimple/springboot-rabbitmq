package com.jxkj.mall.sdkill.to;

import com.jxkj.mall.sdkill.entity.SmsGoods;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 项目: skukill
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2020-08-27 00:35
 **/
@Data
public class SecSessionSkuRedisTO {
    /**
     * 活动信息id
     */
    private Long promotionId;

    /**
     * 场次id
     */
    private Long promotionSessionId;

    /**
     * 商品id
     */
    private Long skuId;

    /**
     * 秒杀价
     */
    private BigDecimal seckillPrice;

    /**
     * 秒杀总件数
     */
    private BigDecimal seckillCount;

    /**
     * 每个人秒杀的限制件数
     */
    private BigDecimal seckillLimit;

    /**
     * 排序
     */
    private Integer seckillSort;

    /**
     * 商品信息
     */
    private SmsGoods smsGoods;

    /**
     * 活动开始时间
     */
    private Long startTime;

    /**
     * 活动结束时间
     */
    private Long endTime;

    /**
     * 随机码，防止恶意脚本无限抢购
     */
    private String randomCode;
}
