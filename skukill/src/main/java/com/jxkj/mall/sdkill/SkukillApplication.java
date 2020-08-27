package com.jxkj.mall.sdkill;

import org.mybatis.spring.annotation.MapperScan;import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableAsync
@MapperScan(basePackages = "com.jxkj.mall.sdkill.mapper")
public class SkukillApplication {

    public static void main(String[] args) {
        SpringApplication.run(SkukillApplication.class, args);
    }

}
