package com.jxkj.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * 通过导入spring的配置文件，进行对bean的注入
 */
@Configuration
@ImportResource("classpath:spring.xml")
public class Beans {

}
