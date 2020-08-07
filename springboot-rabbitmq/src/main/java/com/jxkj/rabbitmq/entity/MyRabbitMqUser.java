package com.jxkj.rabbitmq.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 项目: springboot-rabbitmq
 * <p>
 * 功能描述: 用于测试的实体类
 *
 * @author: WuChengXing
 * @create: 2020-08-07 23:41
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyRabbitMqUser {

    private Long id;

    private String username;

    private String password;

    private Integer age;
}
