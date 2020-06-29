package com.jxkj.springbootinterceptor.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String sayHello(String name){
        return "hello:" + name;
    }

    @GetMapping("/hello1")
    public String sayHello2(String name){
        return "hello1:" + name;
    }
}
