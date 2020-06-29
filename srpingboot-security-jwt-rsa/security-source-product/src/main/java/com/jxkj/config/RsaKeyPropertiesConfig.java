package com.jxkj.config;

import com.jxkj.utils.RsaUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * 功能描述：
 *
 * @author wcx
 * @version 1.0
 */
@ConfigurationProperties(prefix = "rsa.key")
public class RsaKeyPropertiesConfig {

    /**
     * 公钥对象所在文件路径
     */
    private String pubKeyPath;

    /**
     * 公钥对象
     */
    private PublicKey publicKey;

    /**
     * 在spring容器赋值完之后 赋值给这两个对象
     * @throws Exception
     */
    @PostConstruct
    public void createKey() throws Exception {
        publicKey = RsaUtils.getPublicKey(pubKeyPath);
    }

    public String getPubKeyPath() {
        return pubKeyPath;
    }

    public void setPubKeyPath(String pubKeyPath) {
        this.pubKeyPath = pubKeyPath;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
    }

}
