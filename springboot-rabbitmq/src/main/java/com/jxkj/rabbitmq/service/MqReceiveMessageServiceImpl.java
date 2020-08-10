package com.jxkj.rabbitmq.service;

import com.jxkj.rabbitmq.entity.MyRabbitMqUser;
import com.jxkj.rabbitmq.entity.SysUser;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

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

    /**
     * 测试消息的ack模式，自动和手动，一般需要手动，自动模式下服务一宕机，消息就会丢失，而手动模式下则会回滚
     * @param message
     * @param myRabbitMqUser
     * @param channel
     */
    @RabbitHandler
    public void receive01(Message message, MyRabbitMqUser myRabbitMqUser, Channel channel) {
        log.info("receive01: ==> {}", myRabbitMqUser);
        MessageProperties messageProperties = message.getMessageProperties();
        //System.out.println("消息体 ==> " + messageProperties);
        log.info("消息处理完成 ==> {}", myRabbitMqUser.getUsername());
        // 获取标签，channel里面是自增的
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        log.info("deliveryTag ==> {}", deliveryTag);
        try {
            if (deliveryTag % 2 == 0) {
                // 签收货物
                channel.basicAck(deliveryTag, false);
                log.info("已手动签收消息 ==> {}", deliveryTag);
            } else {
                // 拒绝货物 b 是 是否批量拒绝 b1是否拒绝后重回队列，false是直接丢弃该条消息
                channel.basicNack(deliveryTag, false, false);
                log.info("未能签收到的消息 ==> {}", deliveryTag);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @RabbitHandler
    public void receive02(SysUser sysUser) {
        log.info("receive02: ==> {}", sysUser);
    }
}
