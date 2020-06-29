package com.jxkj.controller;

import com.jxkj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/getuserbyid")
    public String getUserById(Integer id){
        return userService.getUserById(id);
    }

    @DeleteMapping("/deleteuserbyid")
    public void deleteUserById(Integer id){
        userService.deleteUserById(id);
    }
}
