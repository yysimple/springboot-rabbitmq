package com.jxkj.controller;

import com.jxkj.service.JDContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 功能描述：
 *
 * @author wcx
 * @version 1.0
 */
@RestController
public class JDContentController {

    @Autowired
    private JDContentService jdContentService;

    @GetMapping("/parse/{keywords}")
    public boolean parse(@PathVariable("keywords") String keywords){
        return jdContentService.parse(keywords);
    }

    @GetMapping("/search/{type}/{keyword}/{currentPage}/{pageSize}")
    public List<Map<String, Object>> search(
            @PathVariable("type") String type,
            @PathVariable("keyword") String keyword,
            @PathVariable("currentPage") int currentPage,
            @PathVariable("pageSize") int pageSize){
        return jdContentService.search(type, keyword, currentPage, pageSize);
    }
}
