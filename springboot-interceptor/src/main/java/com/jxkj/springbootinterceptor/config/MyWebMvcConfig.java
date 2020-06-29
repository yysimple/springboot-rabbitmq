package com.jxkj.springbootinterceptor.config;

import com.jxkj.springbootinterceptor.handler.MyInterceptort;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptort())
                .addPathPatterns("/**")
                .excludePathPatterns("/hello");
    }
}
