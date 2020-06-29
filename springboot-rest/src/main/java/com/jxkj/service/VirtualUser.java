package com.jxkj.service;

import com.jxkj.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "virtual", types = User.class)
public interface VirtualUser {

    /**
     * spel 数据
     * @return
     */
    @Value("#{target.name}#{target.age}")
    String getFullInfo();
}
