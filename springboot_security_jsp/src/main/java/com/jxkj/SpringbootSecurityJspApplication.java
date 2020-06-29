package com.jxkj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.jxkj.mapper")
@SpringBootApplication
public class SpringbootSecurityJspApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootSecurityJspApplication.class, args);
    }

}
