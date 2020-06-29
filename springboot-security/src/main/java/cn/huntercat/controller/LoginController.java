package cn.huntercat.controller;

import cn.huntercat.model.AuthenticationRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author wuchengxing
 * @version 1.0
 * @date 2019/12/11 11:25
 */
@RestController
@SuppressWarnings("all")
public class LoginController {

    @PostMapping(value = "/login-success", produces = {"text/plain;charset=UTF-8"})
    public String loginSuccess(AuthenticationRequest authenticationRequest) {
       return getUsername() + "登录成功";
    }

    @GetMapping(value = "/r/r1", produces = {"text/plain;charset=UTF-8"})
    public String r1(){
        return getUsername() + "访问资源 r1";
    }

    @GetMapping(value = "/r/r2", produces = {"text/plain;charset=UTF-8"})
    public String r2(){
        return getUsername() + "访问资源 r2";
    }


    private String getUsername(){

        String username = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Object principal = authentication.getPrincipal();
        if(null == principal){
            username = "匿名";
        }

        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;
            username = userDetails.getUsername();
        }else {
            username = principal.toString();
        }
        return username;
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){

        return "退出成功";
    }



    /*@Autowired
    private AuthenticationService authenticationService;

        @PostMapping(value = "/login", produces = {"text/plain;charset=UTF-8"})
        public String login(AuthenticationRequest authenticationRequest, HttpSession session) {
            UserDto userDto = authenticationService.authentication(authenticationRequest);
            session.setAttribute(UserDto.SESSION_USER_KEY, userDto);
            return userDto.getFullname() + "：登录成功";
    }


    @GetMapping(value = "/r/r1", produces = {"text/plain;charset=UTF-8"})
    public String r1(HttpSession session) {
        String fullname = null;
        Object o = session.getAttribute(UserDto.SESSION_USER_KEY);
        if(null == o){
            fullname = "匿名";
        }else {
            UserDto userDto = (UserDto) o;
            fullname = userDto.getFullname();
        }
        return fullname + "fangwen-001";
    }

    @GetMapping(value = "/r/r2", produces = {"text/plain;charset=UTF-8"})
    public String r2(HttpSession session) {
        String fullname = null;
        Object o = session.getAttribute(UserDto.SESSION_USER_KEY);
        if(null == o){
            fullname = "匿名";
        }else {
            UserDto userDto = (UserDto) o;
            fullname = userDto.getFullname();
        }
        return fullname + "fangwen-002";
    }*/
}
