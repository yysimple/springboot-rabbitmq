package com.jxkj.alipay.config;

import org.springframework.context.annotation.Configuration;

/**
 * 项目: springboot-alipay
 * <p>
 * 功能描述: 阿里支付配置
 *
 * @author: WuChengXing
 * @create: 2020-08-19 00:44
 **/
@Configuration
public class AlipayConfig {

    // 商户appid
    public static String APPID = "2021000118646250";
    // 私钥 pkcs8格式的
    public static String RSA_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC1KmgJAU31Tddgp/il6qMj1EjNgMguT3nSNBqlP5M1PXLuU4l7I95I8Wid22RW4ua8L6GYyy1LiVF+r1hcwQCAHIZZogKK9ptCzuPgDMcYnMV16K3uRyuna5jK11KcigphGc6w1vn5fMHjKlRQCWrBTtK0WrDjc5A1iLa3NcoJv+uC5PWesuGnAFMMPwt2KPcQj36R+h2rZSt4xNlM79J5Naj57vrxqpGtMXoBUAeDQmk8KxGCoJLkciVo1wFdvndGSGdSGUadhT6lTYQV6wRFLHfuiJbYij0/IU1uDjeU31mr+YwVKHOY5ZLUaCHlqPv5BKu9Tu8RLz+BaFAMLsNjAgMBAAECggEAFl286Fez4zQQAgvaw8kbCQJdVpmunSEMvinWnE4kA2+pr8i6cWx1NfYv30VFtwBeUh9jSPTUi73RRN2rABCH2kkrQQeiXruhYXcfZPrO3bgFHNB0ILXCqvG6tFgvB8p3C5PfE7AGvolrenswW40+E82Cb+e+bOZIiINvY2ENp3RHbzXJ+DneqWbo436T+DisRRyNqfFW73MO8BEP7Y+ooqQ9HDzXgJEZTLe3c2ixbldEBuUryB4D/VB6Hijg9gFb7t++cgu29cRT6jHk++2pzyv5v6gtf4iCvvq1yhBdLGrp4HwWf/2+W4DaVXLuZG2OPhHwWiWRaFN0FFXBkMNemQKBgQD79fr1gxR7ImWj8pniYn6lqTImcSYvfqC/NwfMDNtHBgXsToOyntUHbQgT2xyXikavMErrFRSRQrbTmc+o8uutJYQSqECnVyA5LKErNvSebKKRDg6myRGGsgVqMov2k5qemq+Eg5lD6OsjBTpp2EjqLosr7iRjByh+1hlUqMYZPwKBgQC4EePremLLSHhQSy3Eh+wdJCKQZne0DwaaCTqx2p+g3Fy0lhhcr9ZFoQsNA93rVhP7qbCrJiRBUBXkOArSbNaWnEzQ452tLm8XzlxS3WxcQ52VowB/hpihDVTxKUd9omCIdmFhZG7Os+Ud0fx12Yzp98cJJ/uDJtLBTwUCtl8I3QKBgDACxzdRKu3ruAJtj/UbX5ahjvTUSrpyHp9RFSNEr+PwueA1ddLrgRxRImDmN6SIrL9lLGFvOuQTGlNn07IV1+K+a1RCy08XwwQiBPP/HM2zVabeTtxpI/XOpkl+KD1sEuJ2B0vw55uRHn0l2eOhWwOEqvBMCJL2IjlJArqnDlhxAoGAAL/56Lyv6FocO1+DCTjwilHaXjI8rJwfD8OX3J+iZNpg+k9ePApvWgtE60Gnxsyr+it/z8KxBR8ZZ7uet6q/2Q879OHog4sb4sHxeFj0lw+jdbebSem3d3iBbxYW93kQ4Z1x2h65oNf0yAFSAPzo6Q8cXXovOSKQWz9CKvepPXUCgYEA690qlLROdpi0VqLsGyctOXkqQaaX80ouU6/s63/I4XY+O47blMW8X9nBTZLnmXBcuLdDQFOVvVwKABQDu3dYsSYEf89Q/foE9sz1r313FWB54s9rZGpVHx2lLh8IhivnMJVbRQTvhuY4/8ss+kSF3loEUCtP+plAq52MAocJhKo=";
    // 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://123.57.26.161:43863/alipay.trade.wap.pay-JAVA-UTF-8/notify_url.jsp";
    // 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
    public static String return_url = "http://123.57.26.161:43863/alipay.trade.wap.pay-JAVA-UTF-8/return_url.jsp";
    // 请求网关地址
    public static String URL = "https://openapi.alipaydev.com/gateway.do";
    // 编码
    public static String CHARSET = "UTF-8";
    // 返回格式
    public static String FORMAT = "json";
    // 支付宝公钥
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA7anPOzdxMtceE0Fhisri/HcguWB5P1rHhhvaLrYJ3iEgrUFWGY8FQeRsq87mS6URYXfrm50Ijm4QiyXQeo9NH4ePxK31PFF7c98xroJjCLwYP/O+sS9nRY2PI+wDMi5ip3mMw0Zev4QXssU+QaU9iCzS+2X/xEJ0Nlmuuh+XUaFYcjtqmEXcbXZ1DX8fHsHY1UAvJHS8NOQXAmtC90+zHIuBd2Z3yP0PZ68In3Hd3zDqgA+CNiGWLnt9UY5CvzXXWWPlF5KjkzQGV6F0Y3yIH1DQlMS9BYaMqsVFy3yYYrndq804mtgZ7CyFcCpWtj35NJvlWl0MjNFiNsSrIFvwHwIDAQAB";
    // 日志记录目录
    public static String log_path = "/log";
    // RSA2
    public static String SIGNTYPE = "RSA2";
}