package com.jxkj.controller;

import com.jxkj.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    User user;

    @GetMapping("/getuser")
    public User getUser(){
        return user;
    }
}
