package com.jxkj.rabbitmq.config;

import com.jxkj.rabbitmq.entity.OrderTicket;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 项目: springboot-rabbitmq
 * <p>
 * 功能描述: mq的初始化信息
 *
 * @author: WuChengXing
 * @create: 2020-08-16 23:56
 **/
@Configuration
public class MyRabbitInit {

    @RabbitListener(queues = "order.release.order.queue")
    public void listenMq(OrderTicket orderTicket, Channel channel, Message message) {
        System.out.println("订单编号：===> " + orderTicket.getOrderNo());
        System.out.println("订单创建时间：===> " + orderTicket.getCreateTime());
        System.out.println("订单取消时间: ===> " + new Date());
        System.out.println("耗时： ===> " + (System.currentTimeMillis() - orderTicket.getCurrentMils()));
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 容器中声明的这些 queue 、 exchange等都会自动初始化到队列中
     *
     * @return
     */
    @Bean
    public Queue orderDelayQueue() {
        Map<String, Object> args = new HashMap<>(4);
        // 死信交换机
        args.put("x-dead-letter-exchange", "order-event-exchange");
        // 死信路由键
        args.put("x-dead-letter-routing-key", "order.release.order");
        // 过期时间，单位是毫秒
        args.put("x-message-ttl", 10000);
        /**
         * String name  队列名称
         * boolean durable  是否持久化
         * boolean exclusive  是否声明为独占队列
         * boolean autoDelete  是否自动删除
         * @Nullable Map<String, Object> arguments 特殊参数
         */
        return new Queue("order.delay.queue", true, false, false, args);
    }

    @Bean
    public Queue orderReleaseOrderQueue() {
        return new Queue("order.release.order.queue", true, false, false);
    }

    @Bean
    public Exchange orderEventExchange() {
        /**
         * String name 交换机名称
         * boolean durable 是否持久化
         * boolean autoDelete 是否自动删除
         * Map<String, Object> arguments 特殊参数
         */
        return new TopicExchange("order-event-exchange", true, false);
    }

    @Bean
    public Binding orderCreateOrderBinding() {
        /**
         * String destination  要绑定的目标
         * DestinationType destinationType 目标类型是队列
         * String exchange 要绑定的交换机
         * String routingKey 要绑定路由键
         * @Nullable Map<String, Object> arguments 特殊参数
         */
        return new Binding("order.delay.queue",
                Binding.DestinationType.QUEUE,
                "order-event-exchange",
                "order.create.order", null);
    }

    @Bean
    public Binding orderReleaseOrderBinding() {
        return new Binding("order.release.order.queue",
                Binding.DestinationType.QUEUE,
                "order-event-exchange",
                "order.release.order", null);
    }

    /**
     * 消息发送的交换机(延迟交换机)
     *
     * @return
     */
    @Bean
    public Exchange reSendExchange() {
        return new TopicExchange("resend-delay-exchange");
    }

    /**
     * 延迟队列
     *
     * @return
     */
    @Bean
    public Queue reSendDelayQueue() {
        Map<String, Object> args = new HashMap<>(4);
        // 死信交换机
        args.put("x-dead-letter-exchange", "topic-exchange");
        // 死信路由键
        args.put("x-dead-letter-routing-key", "resend.relay");
        // 过期时间，单位是毫秒
        args.put("x-message-ttl", 15000);
        /**
         * String name  队列名称
         * boolean durable  是否持久化
         * boolean exclusive  是否声明为独占队列
         * boolean autoDelete  是否自动删除
         * @Nullable Map<String, Object> arguments 特殊参数
         */
        return new Queue("resend.delay.queue", true, false, false, args);
    }

    @Bean
    public Binding reSendDelayBinding() {
        return new Binding("resend.delay.queue",
                Binding.DestinationType.QUEUE,
                "resend-delay-exchange",
                "resend", null);
    }

    /**
     * 接收延迟队列消息的交换机
     *
     * @return
     */
    @Bean
    public Exchange topicExchange() {
        return new TopicExchange("topic-exchange");
    }

    /**
     * 接收延时队列消息的死信队列
     *
     * @return
     */
    @Bean
    public Queue topicQueue() {
        return new Queue("topic.queue", true, false, false);
    }

    @Bean
    public Binding reSendBinding() {
        return new Binding("topic.queue",
                Binding.DestinationType.QUEUE,
                "topic-exchange",
                "resend.relay", null);
    }


    /**
     * 创建普通交换机.
     */
    @Bean
    public TopicExchange lindExchange() {
        //消息持久化
        return (TopicExchange) ExchangeBuilder.topicExchange("exchange").durable(true).build();
    }

    @Bean
    public TopicExchange deadExchange() {
        return (TopicExchange) ExchangeBuilder.topicExchange("lind_dl_exchange").durable(true).build();
    }

    /**
     * 基于消息事务的处理方式，当消费失败进行重试，有时间间隔，当达到超时时间，就发到死信队列，等待人工处理.
     *
     * @return
     */
    @Bean
    public Queue testQueue() {
        //设置死信交换机
        return QueueBuilder.durable("queue").withArgument("x-dead-letter-exchange", "lind_dl_exchange")
                //毫秒
                .withArgument("x-message-ttl", 5000)
                //设置死信routingKey
                .withArgument("x-dead-letter-routing-key", "lind_dead_queue").build();
    }

    @Bean
    public Queue deadQueue() {
        return new Queue("lind_dead_queue");
    }

    @Bean
    public Binding bindBuildersRouteKey() {
        return BindingBuilder.bind(testQueue()).to(lindExchange()).with("router");
    }

    @Bean
    public Binding bindDeadBuildersRouteKey() {
        return BindingBuilder.bind(deadQueue()).to(deadExchange()).with("lind_dead_queue");
    }

}
