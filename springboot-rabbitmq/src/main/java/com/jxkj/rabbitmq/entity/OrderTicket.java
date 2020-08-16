package com.jxkj.rabbitmq.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 项目: springboot-rabbitmq
 * <p>
 * 功能描述: 门票订单
 *
 * @author: WuChengXing
 * @create: 2020-08-16 23:50
 **/
@Data
public class OrderTicket {
    private String orderNo;

    /**
     * 产品编号
     */
    private String productName;

    /**
     * 创建订单时间
     */
    private Date createTime;

    /**
     * 创建时间毫秒
     */
    private Long currentMils;
}
