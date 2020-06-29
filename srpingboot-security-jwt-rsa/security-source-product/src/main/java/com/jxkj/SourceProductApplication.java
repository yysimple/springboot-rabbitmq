package com.jxkj;

import com.jxkj.config.RsaKeyPropertiesConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * 功能描述：
 *
 * @author wcx
 * @version 1.0
 */
@SpringBootApplication
@EnableConfigurationProperties(RsaKeyPropertiesConfig.class)
public class SourceProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(SourceProductApplication.class, args);
    }
}
