package com.jxkj.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 功能描述：
 *
 * @author wcx
 * @version 1.0
 */
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                // 配置两个用户，并赋予角色
                .withUser("wcx")
                .roles("admin")
                .password("$2a$10$BaNloEP0aTDMPiGnFcD8burFdeRmIr6ifCzmdc8hfM3uuseK/t.RK")
                .and()
                .withUser("zyy")
                .roles("user")
                .password("$2a$10$NWUC6/gDWHt5iShqWSlsxOUvxx7ZE8RblWvqwmKjhRe8Ci3UeaoOi");
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("123"));
    }
}
