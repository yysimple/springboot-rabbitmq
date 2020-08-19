package com.jxkj.alipay.vo;

import lombok.Data;

/**
 * 项目: springboot-alipay
 * <p>
 * 功能描述: 阿里支付VO
 *
 * @author: WuChengXing
 * @create: 2020-08-19 23:17
 **/
@Data
public class PayVO {
    /**
     * 商户订单号 必填
     */
    private String out_trade_no;

    /**
     * 订单名称 必填
     */
    private String subject;

    /**
     * 付款金额 必填
     */
    private String total_amount;

    /**
     * 商品描述 可空
     */
    private String body;
}
