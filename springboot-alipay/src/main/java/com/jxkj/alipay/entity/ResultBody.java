package com.jxkj.alipay.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 项目: springboot-alipay
 * <p>
 * 功能描述: 返回类
 *
 * @author: WuChengXing
 * @create: 2020-08-21 00:20
 **/
@Data
public class ResultBody implements Serializable {

    private Integer code;

    private String msg;

    private Object data;
}
