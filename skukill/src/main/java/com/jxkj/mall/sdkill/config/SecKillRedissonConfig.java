package com.jxkj.mall.sdkill.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * 项目: skukill
 * <p>
 * 功能描述: 分布式锁的配置
 *
 * @author: WuChengXing
 * @create: 2020-08-27 00:57
 **/
@Configuration
public class SecKillRedissonConfig {
    @Bean(destroyMethod = "shutdown")
    RedissonClient redissonClient() throws IOException {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://182.92.89.118:6379")
                .setPassword("970412@wcx.com");
        return Redisson.create(config);
    }
}
