package com.jxkj.entity;

import lombok.Data;

import java.util.Date;

/**
 * 功能描述：
 *
 * @author wcx
 * @version 1.0
 */
@Data
public class Payload<T> {

    private String id;

    private T userInfo;

    private Date expiration;
}
