package com.jxkj.config;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobleConfig {
    /***
     * 设置  @ModelAttribute(value = "info") 在全局任意位置可以访问到Model中的数据
     * @return
     */
    @ModelAttribute(value = "info")
    public Map<String, String> userInfo(){
        Map<String, String> map = new HashMap<>();
        map.put("name","zss");
        map.put("gender","man");
        return map;
    }

    /**
     * 对传入参数 book、author 进行加前缀的处理
     * @param webDataBinder
     */
    @InitBinder
    public void initBook(WebDataBinder webDataBinder){
        webDataBinder.setFieldDefaultPrefix("b.");
    }

    @InitBinder
    public void initAuthor(WebDataBinder webDataBinder){
        webDataBinder.setFieldDefaultPrefix("a.");
    }


}
