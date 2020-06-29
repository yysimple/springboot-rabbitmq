package com.jxkj.service;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    public String getUserById(Integer id){
        System.out.println("get...:" + id);
        return "user";
    }

    public void  deleteUserById(Integer id){
        System.out.println("delete a user...id为:" + id);
    }
}
