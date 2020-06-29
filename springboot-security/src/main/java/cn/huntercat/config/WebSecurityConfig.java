package cn.huntercat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author wuchengxing
 * @version 1.0
 * @date 2019/12/11 14:36
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 将浏览器对post的保护关掉
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/r/r1")
                .hasAuthority("p1")
                .antMatchers("/r/r2")
                .hasAuthority("p2")
                .antMatchers("/r/**")
                .authenticated()
                .anyRequest()
                .permitAll()
                .and()
                .formLogin()
                .loginPage("/login.html")
                .successForwardUrl("/login-success")
                .and()
                .logout()
                .logoutSuccessUrl("/logout");
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }


}
