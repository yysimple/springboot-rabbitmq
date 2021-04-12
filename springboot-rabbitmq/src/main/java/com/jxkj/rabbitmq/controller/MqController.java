package com.jxkj.rabbitmq.controller;

import com.jxkj.rabbitmq.entity.MyRabbitMqUser;
import com.jxkj.rabbitmq.entity.OrderTicket;
import com.jxkj.rabbitmq.entity.SysUser;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

/**
 * 项目: springboot-rabbitmq
 * <p>
 * 功能描述: 用于向mq中发送消息的controller
 *
 * @author: WuChengXing
 * @create: 2020-08-08 00:11
 **/
@RestController
@Slf4j
public class MqController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @GetMapping("/sendMq/{num}")
    public String sendMq(@PathVariable("num") Integer num) {
        for (int i = 0; i < num; i++) {
            MyRabbitMqUser myRabbitMqUser = new MyRabbitMqUser(1L + i, "hahah" + i, "123456", 19);
            rabbitTemplate.convertAndSend("hello-java-exchange", "hello.java", myRabbitMqUser, new CorrelationData(UUID.randomUUID().toString()));
            log.info("消息开始发送 ==> {}", myRabbitMqUser.getUsername());
            /**
             * else {
             SysUser sysUser = new SysUser(1L + 1, "haha" + i, "123" + i);
             rabbitTemplate.convertAndSend("hello-java-exchange", "hello.java", sysUser);
             log.info("消息开始发送 ==> {}", sysUser.getName());
             }*/
        }
        return "ok";
    }

    @GetMapping("/reSend/{num}")
    public String testReSend(@PathVariable("num") Integer num) {
        for (int i = 0; i < num; i++) {
            MyRabbitMqUser myRabbitMqUser = new MyRabbitMqUser(1L + i, "hahah" + i, "123456", 19);
            rabbitTemplate.convertAndSend("resend-delay-exchange", "resend", myRabbitMqUser, new CorrelationData(UUID.randomUUID().toString()));
            log.info("消息开始发送 ==> {}", myRabbitMqUser.getUsername());
            /**
             * else {
             SysUser sysUser = new SysUser(1L + 1, "haha" + i, "123" + i);
             rabbitTemplate.convertAndSend("hello-java-exchange", "hello.java", sysUser);
             log.info("消息开始发送 ==> {}", sysUser.getName());
             }*/
        }
        return "ok";
    }

    @GetMapping("/createOrder")
    public String createOrder() {
        OrderTicket orderTicket = new OrderTicket();
        String orderNo = UUID.randomUUID().toString();
        orderTicket.setOrderNo(orderNo);
        orderTicket.setProductName("我是" + orderNo + "下的订单");
        orderTicket.setCreateTime(new Date());
        orderTicket.setCurrentMils(System.currentTimeMillis());
        rabbitTemplate.convertAndSend("order-event-exchange", "order.create.order", orderTicket);
        System.out.println("消息开始发送： ===> " + new Date());
        return orderNo;
     }
}
