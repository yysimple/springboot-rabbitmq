package com.jxkj.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * MyWebSecurityConfig 自己定义的类继承 WebSecurityConfigurerAdapter
 * 进行自定义的认证
 */
@Configuration
public class MyWebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 定义密码加密策略
     *
     * @return
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        // 不适用密码加密策略
        return NoOpPasswordEncoder.getInstance();
    }

    /**
     * 基于内存的认证，并授权
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("root").password("123").roles("ADMIN", "DBA")
                .and()
                .withUser("admin").password("123").roles("ADMIN", "USER")
                .and()
                .withUser("user").password("123").roles("USER");
    }

    /**
     * 指定资源对应的权限，使其可以进行正常的访问
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 开启登录授权的配置
        http.authorizeRequests()

                // ======= 指定资源路劲为/admin/**的路径,只有权限为ADMIN的用户才可以进行登录
                .antMatchers("/admin/**")
                .hasRole("ADMIN")

                // =======  指定资源路劲为/user/**的路径,权限为ADMIN USER的用户才可以进行登录
                .antMatchers("/user/**")
                .access("hasAnyRole('ADMIN','USER')")

                // =======  指定资源路劲为/db/**的路径,只有权限为 ADMIN 并且为 DBA 的用户才可以进行登录
                .antMatchers("/db/**")
                .access("hasRole('ADMIN') and hasRole('DBA')")

                // =======  除了上面的指定请求外的所有请求都需要进行登录验证
                .anyRequest()
                .authenticated()
                .and()

                // ======= 指定登录页面和登录请求路径
                .formLogin()
                .loginPage("/login_page")
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler((request, response, auth) -> {
                    Object principal = auth.getPrincipal();
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter out = response.getWriter();
                    response.setStatus(200);
                    Map<String, Object> map = new HashMap<>();
                    map.put("status", 200);
                    map.put("msg", principal);
                    ObjectMapper om = new ObjectMapper();
                    out.write(om.writeValueAsString(map));
                    out.flush();
                    out.close();
                })
                .failureHandler((request, response, e) -> {
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter out = response.getWriter();
                    response.setStatus(401);
                    Map<String, Object> map = new HashMap<>();
                    map.put("status", 401);
                    if (e instanceof LockedException) {
                        map.put("msg", "当前账户被锁定");
                    } else if (e instanceof BadCredentialsException) {
                        map.put("msg", "账户名或密码不正确");
                    } else if (e instanceof DisabledException) {
                        map.put("msg", "用户被禁用");
                    } else if (e instanceof AccountExpiredException) {
                        map.put("msg", "账户已过期");
                    } else if (e instanceof CredentialsExpiredException) {
                        map.put("msg", "密码已过期");
                    } else {
                        map.put("msg", "登录失败");
                    }

                    ObjectMapper om = new ObjectMapper();
                    out.write(om.writeValueAsString(map));
                    out.flush();
                    out.close();
                })

                // =======  允许与登录相关的请求
                .permitAll()
                .and()

                // ======= 接触对post的拦截
                .csrf()
                .disable();
    }

}

