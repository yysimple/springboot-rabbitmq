package com.jxkj.mail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 功能描述：
 *
 * @author wcx
 * @version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SimpleMailMessageTest {

    @Autowired
    private JavaMailSender javaMailSender;

    @Test
    public void sendSimpleMail(){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("这是一封来自wcx的邮件");
        simpleMailMessage.setFrom("1449697757@qq.com");
    }
}
