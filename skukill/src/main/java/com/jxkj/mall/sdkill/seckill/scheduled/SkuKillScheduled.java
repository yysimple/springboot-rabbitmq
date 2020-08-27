package com.jxkj.mall.sdkill.seckill.scheduled;

import com.jxkj.mall.sdkill.seckill.service.SecKillService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 项目: skukill
 * <p>
 * 功能描述: 秒杀上架商品的定时器
 *
 * @author: WuChengXing
 * @create: 2020-08-26 23:19
 **/
@Slf4j
@Component
public class SkuKillScheduled {

    /**
     * 上架锁，防止多服务之间重复上架
     */
    public static final String UPLOAD_LOCK = "seckill:upload:lock";

    @Autowired
    private SecKillService secKillService;

    @Autowired
    private RedissonClient redissonClient;

    /**
     * 每三天上架一次
     */
    @Scheduled(cron = "*/10 * * * * *")
    @Async
    public void uploadSeckillSkuLateThreeDays() {
        log.info("秒杀上架开始 === > {}", new Date());
        RLock lock = redissonClient.getLock(UPLOAD_LOCK);
        // 分布式锁 == 防止其他的服务一起写入，保证释放锁之后，其他人拿到的是最新的数据
        lock.lock(10, TimeUnit.SECONDS);
        try {
            // 上架
            secKillService.uploadThreeDaySession();
        } finally {
            // 释放锁
            lock.unlock();
        }
    }
}
