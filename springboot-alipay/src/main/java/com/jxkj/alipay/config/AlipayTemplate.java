package com.jxkj.alipay.config;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.jxkj.alipay.vo.PayVO;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "alipay")
@Component
@Data
public class AlipayTemplate {

    //在支付宝创建的应用的id
    private   String app_id = "2021000118646250";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String RSA_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC1KmgJAU31Tddgp/il6qMj1EjNgMguT3nSNBqlP5M1PXLuU4l7I95I8Wid22RW4ua8L6GYyy1LiVF+r1hcwQCAHIZZogKK9ptCzuPgDMcYnMV16K3uRyuna5jK11KcigphGc6w1vn5fMHjKlRQCWrBTtK0WrDjc5A1iLa3NcoJv+uC5PWesuGnAFMMPwt2KPcQj36R+h2rZSt4xNlM79J5Naj57vrxqpGtMXoBUAeDQmk8KxGCoJLkciVo1wFdvndGSGdSGUadhT6lTYQV6wRFLHfuiJbYij0/IU1uDjeU31mr+YwVKHOY5ZLUaCHlqPv5BKu9Tu8RLz+BaFAMLsNjAgMBAAECggEAFl286Fez4zQQAgvaw8kbCQJdVpmunSEMvinWnE4kA2+pr8i6cWx1NfYv30VFtwBeUh9jSPTUi73RRN2rABCH2kkrQQeiXruhYXcfZPrO3bgFHNB0ILXCqvG6tFgvB8p3C5PfE7AGvolrenswW40+E82Cb+e+bOZIiINvY2ENp3RHbzXJ+DneqWbo436T+DisRRyNqfFW73MO8BEP7Y+ooqQ9HDzXgJEZTLe3c2ixbldEBuUryB4D/VB6Hijg9gFb7t++cgu29cRT6jHk++2pzyv5v6gtf4iCvvq1yhBdLGrp4HwWf/2+W4DaVXLuZG2OPhHwWiWRaFN0FFXBkMNemQKBgQD79fr1gxR7ImWj8pniYn6lqTImcSYvfqC/NwfMDNtHBgXsToOyntUHbQgT2xyXikavMErrFRSRQrbTmc+o8uutJYQSqECnVyA5LKErNvSebKKRDg6myRGGsgVqMov2k5qemq+Eg5lD6OsjBTpp2EjqLosr7iRjByh+1hlUqMYZPwKBgQC4EePremLLSHhQSy3Eh+wdJCKQZne0DwaaCTqx2p+g3Fy0lhhcr9ZFoQsNA93rVhP7qbCrJiRBUBXkOArSbNaWnEzQ452tLm8XzlxS3WxcQ52VowB/hpihDVTxKUd9omCIdmFhZG7Os+Ud0fx12Yzp98cJJ/uDJtLBTwUCtl8I3QKBgDACxzdRKu3ruAJtj/UbX5ahjvTUSrpyHp9RFSNEr+PwueA1ddLrgRxRImDmN6SIrL9lLGFvOuQTGlNn07IV1+K+a1RCy08XwwQiBPP/HM2zVabeTtxpI/XOpkl+KD1sEuJ2B0vw55uRHn0l2eOhWwOEqvBMCJL2IjlJArqnDlhxAoGAAL/56Lyv6FocO1+DCTjwilHaXjI8rJwfD8OX3J+iZNpg+k9ePApvWgtE60Gnxsyr+it/z8KxBR8ZZ7uet6q/2Q879OHog4sb4sHxeFj0lw+jdbebSem3d3iBbxYW93kQ4Z1x2h65oNf0yAFSAPzo6Q8cXXovOSKQWz9CKvepPXUCgYEA690qlLROdpi0VqLsGyctOXkqQaaX80ouU6/s63/I4XY+O47blMW8X9nBTZLnmXBcuLdDQFOVvVwKABQDu3dYsSYEf89Q/foE9sz1r313FWB54s9rZGpVHx2lLh8IhivnMJVbRQTvhuY4/8ss+kSF3loEUCtP+plAq52MAocJhKo=";
    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA7anPOzdxMtceE0Fhisri/HcguWB5P1rHhhvaLrYJ3iEgrUFWGY8FQeRsq87mS6URYXfrm50Ijm4QiyXQeo9NH4ePxK31PFF7c98xroJjCLwYP/O+sS9nRY2PI+wDMi5ip3mMw0Zev4QXssU+QaU9iCzS+2X/xEJ0Nlmuuh+XUaFYcjtqmEXcbXZ1DX8fHsHY1UAvJHS8NOQXAmtC90+zHIuBd2Z3yP0PZ68In3Hd3zDqgA+CNiGWLnt9UY5CvzXXWWPlF5KjkzQGV6F0Y3yIH1DQlMS9BYaMqsVFy3yYYrndq804mtgZ7CyFcCpWtj35NJvlWl0MjNFiNsSrIFvwHwIDAQAB";
    // 服务器[异步通知]页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    // 支付宝会悄悄的给我们发送一个请求，告诉我们支付成功的信息
    /*private  String notify_url;

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    //同步通知，支付成功，一般跳转到成功页
    private  String return_url;*/

    // 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://123.57.26.161:43863/alipay.trade.wap.pay-JAVA-UTF-8/notify_url.jsp";
    // 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
    public static String return_url = "http://123.57.26.161:43863/alipay.trade.wap.pay-JAVA-UTF-8/return_url.jsp";

    // 签名方式
    private  String sign_type = "RSA2";

    // 字符编码格式
    private  String charset = "utf-8";

    // 支付宝网关； https://openapi.alipaydev.com/gateway.do
    private  String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    public  String pay(PayVO vo) throws AlipayApiException {

        //AlipayClient alipayClient = new DefaultAlipayClient(AlipayTemplate.gatewayUrl, AlipayTemplate.app_id, AlipayTemplate.merchant_private_key, "json", AlipayTemplate.charset, AlipayTemplate.alipay_public_key, AlipayTemplate.sign_type);
        //1、根据支付宝的配置生成一个支付客户端
        AlipayClient alipayClient = new DefaultAlipayClient(gatewayUrl,
                app_id, RSA_PRIVATE_KEY, "json",
                charset, ALIPAY_PUBLIC_KEY, sign_type);

        //2、创建一个支付请求 //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(return_url);
        alipayRequest.setNotifyUrl(notify_url);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = vo.getOut_trade_no();
        //付款金额，必填
        String total_amount = vo.getTotal_amount();
        //订单名称，必填
        String subject = vo.getSubject();
        //商品描述，可空
        String body = vo.getBody();

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        String result = alipayClient.pageExecute(alipayRequest).getBody();

        //会收到支付宝的响应，响应的是一个页面，只要浏览器显示这个页面，就会自动来到支付宝的收银台页面
        System.out.println("支付宝的响应："+result);

        return result;

    }
}
