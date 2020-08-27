package com.jxkj.mall.sdkill.seckill.to.mq;

import io.swagger.models.auth.In;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 项目: skukill
 * <p>
 * 功能描述: 秒杀之后给mq发送的vo
 *
 * @author: WuChengXing
 * @create: 2020-08-28 01:03
 **/
@Data
public class SecKillOrderTO {
    /**
     * 订单编号
     */
    private String orderNo;

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
     * 购买的数量
     */
    private Integer num;

    /**
     * 用户id
     */
    private Long userId;
}
