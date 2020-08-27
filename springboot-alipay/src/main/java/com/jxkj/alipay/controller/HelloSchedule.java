package com.jxkj.alipay.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

/**
 * 项目: springboot-alipay
 * <p>
 * 功能描述: 定时任务
 *
 * @author: WuChengXing
 * @create: 2020-08-22 23:38
 **/
@EnableScheduling
@EnableAsync
@Component
@Slf4j
public class HelloSchedule {

    /**
     * Async：
     *
     * 1. 开启springboot自带的异步操作
     * 2. 可以追踪底层TaskExecutionProperties，其最后还是使用juc里面的线程池Execute类
     *
     */

    /**
     * Scheduled：
     * 1. springboot里面的定时任务只能是 6 位数字
     * 2. TaskSchedulingAutoConfiguration -> TaskSchedulingProperties(这个类里面其实也是开启了线程池)
     *    但是默认的大小是 private int size = 1; 所以我们可以通过修改配置文件的方式做到一步操作
     * 3. 可以使用 异步编排
     * 4. 可以使用springboot自带的 Async
     */
    @Scheduled(cron = "* * * * * *")
    @Async
    public void hello () {
        log.info("定时任务 ==> {}", "hello" );
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        LocalDate localDate1 = LocalDate.now();
        LocalDate localDate2 = localDate1.plusDays(3);
        System.out.println(localDate1);
        System.out.println(localDate2);
        LocalTime max = LocalTime.MAX;
        LocalTime min = LocalTime.MIN;
        System.out.println(max);
        System.out.println(min);

        LocalDateTime start = LocalDateTime.of(localDate1, min);
        LocalDateTime end = LocalDateTime.of(localDate2, max);
        System.out.println(start);
        System.out.println(end);

        String format = start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
        System.out.println("format = " + format);


    }
}
