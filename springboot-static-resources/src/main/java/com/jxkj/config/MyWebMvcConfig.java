package com.jxkj.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/***
 * 静态资源请求的路径配置
 * 也可以通过在主配置文件中添加：
 *      # 配置请求资源的路径样式
 *      spring.mvc.static-path-pattern=/static/**
 *      # 将默认加载资源路径修改成static文件下
 *      spring.resources.static-locations=classpath:/static/
 */
@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }
}
