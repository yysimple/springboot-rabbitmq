package com.jxkj.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@RestController
public class HelloController {

    /**
     * Model 中包含了前面保存的全局配置
     * @param model
     * @return
     */
    @GetMapping("/hello")
    public String hello(Model model) {
        Map<String, Object> map = model.asMap();
        Set<String> keys = map.keySet();
        Iterator<String> iterator = keys.iterator();
        String key = null;
        Object val = null;
        while (iterator.hasNext()) {
            key = iterator.next();
            val = map.get(key);
            System.out.println(key + " ===> " + val);
        }
        return key + " ===> " + val;
    }
}
