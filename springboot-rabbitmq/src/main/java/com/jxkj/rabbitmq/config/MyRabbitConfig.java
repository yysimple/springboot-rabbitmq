package com.jxkj.rabbitmq.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 项目: springboot-rabbitmq
 * <p>
 * 功能描述: rabbit的配置类
 *
 * @author: WuChengXing
 * @create: 2020-08-07 23:43
 **/
@Configuration
public class MyRabbitConfig {

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
