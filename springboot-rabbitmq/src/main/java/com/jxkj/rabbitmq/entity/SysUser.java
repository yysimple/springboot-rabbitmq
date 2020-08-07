package com.jxkj.rabbitmq.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 项目: springboot-rabbitmq
 * <p>
 * 功能描述: 测试实体类
 *
 * @author: WuChengXing
 * @create: 2020-08-08 00:13
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUser {

    private Long id;

    private String name;

    private String phone;
}
