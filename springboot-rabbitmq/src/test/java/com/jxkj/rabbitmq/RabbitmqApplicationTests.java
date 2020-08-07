package com.jxkj.rabbitmq;

import com.jxkj.rabbitmq.entity.MyRabbitMqUser;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class RabbitmqApplicationTests {

    @Autowired
    AmqpAdmin amqpAdmin;

    @Autowired
    RabbitTemplate rabbitTemplate;

    /**
     * 向mq中发送消息
     */
    @Test
    void sendMessage(){
        MyRabbitMqUser myRabbitMqUser = new MyRabbitMqUser(1L, "hahah", "123456", 19);
        rabbitTemplate.convertAndSend("hello-java-exchange", "hello.java", myRabbitMqUser);
    }

    /**
     * 创建交换机
     */
    @Test
    void contextLoads() {
        Exchange exchange = new DirectExchange("hello-java-exchange", true, false);
        amqpAdmin.declareExchange(exchange);
        log.info("交换机已创建：{}", "hello-java-exchange");
    }

    /**
     * 创建队列
     */
    @Test
    void createQueue(){
        Queue queue = new Queue("hello-java-queue");
        amqpAdmin.declareQueue(queue);
        log.info("队列已创建：{}", "hello-java-queue");
    }

    /**
     * 绑定队列和交换机
     */
    @Test
    void binding(){
        Binding binding = new Binding("hello-java-queue", Binding.DestinationType.QUEUE, "hello-java-exchange", "hello.java", null);
        amqpAdmin.declareBinding(binding);
        log.info("已绑定：{}", "hello-java-binding");
    }

}
