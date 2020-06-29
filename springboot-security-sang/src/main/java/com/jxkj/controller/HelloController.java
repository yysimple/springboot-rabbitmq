package com.jxkj.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    /**
     * 在默认情况下，密码是在运行时项目的控制台打印
     *
     * 在springboot的配置文件中配置了之后，就不会自动生成密码
     *
     * @param name
     * @return
     */
    @GetMapping("/hello")
    public String hello(String name){
        return "hello:" + name;
    }

    @GetMapping("/admin/hello")
    public String helloAmdin(String name){
        return "hello admin:" + name;
    }

    @GetMapping("/user/hello")
    public String helloUser(String name){
        return "hello user:" + name;
    }

    @GetMapping("/db/hello")
    public String helloDba(String name){
        return "hello dba:" + name;
    }
}
