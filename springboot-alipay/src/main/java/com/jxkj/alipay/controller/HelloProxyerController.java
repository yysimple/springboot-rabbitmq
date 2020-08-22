package com.jxkj.alipay.controller;

import com.alipay.api.AlipayApiException;
import com.jxkj.alipay.config.AlipayTemplate;
import com.jxkj.alipay.entity.ResultBody;
import com.jxkj.alipay.vo.PayVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Map;

/**
 * 项目: springboot-alipay
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2020-08-19 02:04
 **/
@RestController
@CrossOrigin
public class HelloProxyerController {

    @Autowired
    AlipayTemplate alipayTemplate;

    @GetMapping("/alipay")
    public String hello() {
        return "hello proxyer!!";
    }

    @PostMapping(value = "/payOrder", produces = "text/html;charset=UTF-8")
    public String payOrder(@RequestParam("orderNo") String orderNo) throws AlipayApiException {
        PayVO payVO = new PayVO();
        payVO.setOut_trade_no(orderNo);
        payVO.setBody("测试支付宝是否成功！");
        payVO.setSubject("测试应用");
        payVO.setTotal_amount(String.valueOf(new BigDecimal(12.00)));
        String pay = alipayTemplate.pay(payVO);

        return pay;
    }

    @PostMapping("/aliReturnUrl")
    public String aliReturnUrl(HttpServletRequest httpRequest) {
        Map<String, String[]> parameterMap = httpRequest.getParameterMap();
        System.out.println("我是异步返回的通知 ===>: " + parameterMap);
        return "ok";
    }
}
