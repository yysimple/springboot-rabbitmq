package com.jxkj.rabbitmq.service;

import com.jxkj.rabbitmq.entity.MyRabbitMqUser;
import com.jxkj.rabbitmq.entity.SysUser;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * 项目: springboot-rabbitmq
 * <p>
 * 功能描述: 接收消息
 *
 * @author: WuChengXing
 * @create: 2020-08-08 00:19
 **/
@Service
@Slf4j
@RabbitListener(queues = {"hello-java-queue"})
public class MqReceiveMessageServiceImpl {

    @RabbitHandler
    public void receive01(Message message, MyRabbitMqUser myRabbitMqUser, Channel channel) {
        log.info("receive01: ==> {}", myRabbitMqUser);
    }

    @RabbitHandler
    public void receive02(SysUser sysUser) {
        log.info("receive02: ==> {}", sysUser);
    }
}
