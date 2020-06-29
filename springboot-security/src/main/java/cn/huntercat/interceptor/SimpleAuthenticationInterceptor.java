package cn.huntercat.interceptor;

import cn.huntercat.model.UserDto;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author wuchengxing
 * @version 1.0
 * @date 2019/12/11 12:02
 */
// @Configuration
public class SimpleAuthenticationInterceptor implements HandlerInterceptor {
   /* @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object o = request.getSession().getAttribute(UserDto.SESSION_USER_KEY);
        if (null == o) {
            // 没有认证
            writeContent(response, "请登录！");
        }

        UserDto userDto = (UserDto) o;
        // 获取用户的请求url
        String requestURI = request.getRequestURI();
        if(userDto.getAuthorities().contains("p1") && requestURI.contains("/r/r1")){
            return true;
        }

        if(userDto.getAuthorities().contains("p2") && requestURI.contains("/r/r2")){
            return true;
        }

        writeContent(response, "没有权限，拒绝访问");
        return false;
    }

    *//**
     * 响应信息给客户端
     * @param response
     * @param msg
     * @throws IOException
     *//*
    private void writeContent(HttpServletResponse response, String msg) throws IOException {
        response.setContentType("text/plain;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.print(msg);

        writer.close();
        response.resetBuffer();

    }*/
}
