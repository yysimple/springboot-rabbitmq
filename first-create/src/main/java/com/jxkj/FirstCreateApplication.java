package com.jxkj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @EnableAutoConfiguration: 自动配置类注解，会自动去进行spring和springmvc的配置
 * @ComponentScan: 包扫描注解,主程序需要放在包中,其加载的是其同目录下所有的包，所以建议放在根目录下
 */
/*@EnableAutoConfiguration
@ComponentScan*/
@SpringBootApplication
public class FirstCreateApplication {
    public static void main(String[] args) {
        SpringApplication.run(FirstCreateApplication.class,args);
    }
}
