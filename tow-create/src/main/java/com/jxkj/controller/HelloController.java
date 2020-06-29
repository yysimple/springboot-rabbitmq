package com.jxkj.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hellotomcat")
    public String sayTomcat(){
        return "hello tomcat";
    }
}
