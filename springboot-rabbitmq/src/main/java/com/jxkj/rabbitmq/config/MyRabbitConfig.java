package com.jxkj.rabbitmq.config;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

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

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    /**
     * 当初始化配置的时候就触发
     */
    @PostConstruct
    public void initRabbitTemplate() {
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            /**
             * 功能描述: 在消息顺利抵达bloker的时候 ack 为 true
             * @author WuChengxing
             * @date 2020/8/8
             * @param correlationData 消息的唯一id ， 需要在发送方自己生成，在这里就可以接受到
             * @param ack
             * @param s 失败的原因
             * @return void
             */
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String s) {
                System.out.println("投递成功 ==》 correlationData = " + correlationData + ", ack = " + ack + ", s = " + s);
            }
        });

        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            /**
             * 功能描述: 消息抵达队列的回调(只有投递失败的时候才能触发)
             * @author WuChengxing
             * @date 2020/8/8
             * @param message 投递失败的详细信息
             * @param code 回复的状态码
             * @param text 回复的文本内容
             * @param exchange 使用的交换机
             * @param routeKey 使用的路由键
             * @return void
             */
            @Override
            public void returnedMessage(Message message, int code, String text, String exchange, String routeKey) {
                System.out.println("投递失败 ==》 message = " + message + ", code = " + code + ", text = " + text + ", exchange = " + exchange + ", routeKey = " + routeKey);
            }
        });
    }
}
