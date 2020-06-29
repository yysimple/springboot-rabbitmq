package com.jxkj.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jxkj.config.RsaKeyPropertiesConfig;
import com.jxkj.entity.SysRole;
import com.jxkj.entity.SysUser;
import com.jxkj.utils.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能描述：
 *
 * @author wcx
 * @version 1.0
 */
public class TokenLoginFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    private RsaKeyPropertiesConfig rsaKeyPropertiesConfig;


    public TokenLoginFilter(AuthenticationManager authenticationManager, RsaKeyPropertiesConfig rsaKeyPropertiesConfig) {
        this.authenticationManager = authenticationManager;
        this.rsaKeyPropertiesConfig = rsaKeyPropertiesConfig;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) {
        try {
            SysUser sysUser = new ObjectMapper().readValue(req.getInputStream(), SysUser.class);
            return new UsernamePasswordAuthenticationToken(sysUser.getUsername(), sysUser.getPassword());
        } catch (Exception e) {
            try {
                //如果认证失败，提供自定义json格式异常
                res.setContentType("application/json;charset=utf-8");
                res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                PrintWriter out = res.getWriter();
                Map<String, Object> map = new HashMap(16);
                map.put("code", HttpServletResponse.SC_UNAUTHORIZED);
                map.put("message", "账号或密码错误！");
                out.write(new ObjectMapper().writeValueAsString(map));
                out.flush();
                out.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse res, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        // 得到当前的认证对象
        SysUser user = new SysUser();
        user.setUsername(authResult.getName());
        user.setRoles((List<SysRole>) authResult.getAuthorities());

        //json web token构建
        String token = JwtUtils.generateTokenExpireInMinutes(user, rsaKeyPropertiesConfig.getPrivateKey(), 24 * 60);
        //返回token
        res.addHeader("Authorization", "Bearer " + token);
        try {
            //登录成功時，返回json格式进行提示
            res.setContentType("application/json;charset=utf-8");
            res.setStatus(HttpServletResponse.SC_OK);
            PrintWriter out = res.getWriter();
            Map<String, Object> map = new HashMap(16);
            map.put("code", HttpServletResponse.SC_OK);
            map.put("message", "登陆成功！");
            out.write(new ObjectMapper().writeValueAsString(map));
            out.flush();
            out.close();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
