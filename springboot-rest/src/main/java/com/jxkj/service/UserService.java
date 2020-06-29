package com.jxkj.service;

import com.jxkj.entity.User;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "user", types = User.class)
public interface UserService {

    Long getId();

    String getName();

    Integer getAge();
}
