package com.jxkj.rabbitmq.service;

import com.jxkj.rabbitmq.entity.MyRabbitMqUser;
import com.jxkj.rabbitmq.entity.SysUser;
import com.jxkj.rabbitmq.util.RedisUtils;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

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
public class MqReceiveMessageServiceImpl {

    /**
     * 最大重试次数
     */
    private static final int MAX_RECONSUME_COUNT = 3;

    /**
     * 测试消息的ack模式，自动和手动，一般需要手动，自动模式下服务一宕机，消息就会丢失，而手动模式下则会回滚
     *
     * @param message
     * @param myRabbitMqUser
     * @param channel
     */
    @RabbitListener(queues = {"hello-java-queue"})
    public void receive01(Message message, MyRabbitMqUser myRabbitMqUser, Channel channel) throws IOException {
        log.info("receive01: ==> {}", myRabbitMqUser);
        MessageProperties messageProperties = message.getMessageProperties();
        //System.out.println("消息体 ==> " + messageProperties);
        log.info("消息处理完成 ==> {}", myRabbitMqUser.getUsername());
        // 获取标签，channel里面是自增的
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        log.info("deliveryTag ==> {}", deliveryTag);

        if (deliveryTag % 2 == 0) {
            // 签收货物
            channel.basicAck(deliveryTag, false);
            log.info("已手动签收消息 ==> {}", deliveryTag);
        } else {
            // 拒绝货物 b 是 是否批量拒绝 b1是否拒绝后重回队列，false是直接丢弃该条消息
            channel.basicNack(deliveryTag, false, false);
            log.info("未能签收到的消息 ==> {}", deliveryTag);
        }
    }

    /**
     * 测试消息的重发
     *
     * @param message
     * @param myRabbitMqUser
     * @param channel
     * @throws IOException
     */
    // @RabbitListener(queues = {"resend.delay.queue"})
    public void testReSend(Message message, MyRabbitMqUser myRabbitMqUser, Channel channel) throws IOException {

        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        //消费失败重试3次，3次失败后放入死信队列
        String msgId = (String) message.getMessageProperties().getHeaders().get("spring_returned_message_correlation");
        int retryCount = (int) RedisUtils.get(msgId);
        System.out.println("------ retryCount : " + retryCount);
        if (retryCount >= MAX_RECONSUME_COUNT) {
            //requeue = false 放入死信队列
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
        } else {
            //requeue = true 放入消费队列重试消费
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            RedisUtils.set(msgId, retryCount + 1);
        }

        try {
            int i = 1 / 0;
            channel.basicAck(deliveryTag, false);
            log.info("已手动签收消息 ==> {}", deliveryTag);
        } catch (Exception e) {
            channel.basicNack(deliveryTag, false, false);
            e.printStackTrace();
        }
    }

    /**
     * 死信队列.
     *
     * @param message
     */
   //  @RabbitListener(queues = "{topic.queue}")
    public void dealSubscribe(Message message, Channel channel) throws IOException {
        System.out.println("Dead Subscriber:" + new String(message.getBody(), "UTF-8"));
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
    }

    @RabbitHandler
    public void receive02(SysUser sysUser) {
        log.info("receive02: ==> {}", sysUser);
    }
}
