package com.jxkj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.jxkj.jxkj")
public class SpringbootDatasourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDatasourceApplication.class, args);
    }

}
