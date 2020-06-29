package com.jxkj.config;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * 定制返回的数据
 * 在定制错误页面的时候，里面使用的数据会用这里保存的属性
 * 取值也是getErrorAttributes  ==》 获取到 "custommsg" 中的值
 */
@Component
public class MyErrorAttributeConfig extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, includeStackTrace);
        //errorAttributes.put("custommsg","出错了...custom error info");
        //errorAttributes.remove("error");
        return errorAttributes;
    }
}
