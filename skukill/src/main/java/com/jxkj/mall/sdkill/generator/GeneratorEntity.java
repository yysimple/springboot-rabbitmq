package com.jxkj.mall.sdkill.generator;

import lombok.Data;

/**
 * 功能描述：逆向生成的参数
 *
 * @author wcx
 * @version 1.0
 */
@Data
public class GeneratorEntity {
    /**
     * 作者姓名
     */
    private String author;

    /**
     * 是否开启swagger,默认开启
     */
    private boolean isOpenSwagger = true;

    /**
     * 数据库的url：
     * jdbc:mysql://localhost:3306/db_jxwxkj?serverTimezone=UTC&useUnicode=true&useSSL=false&characterEncoding=utf8
     */
    private String url;

    /**
     * 数据库用户名
     */
    private String username;

    /**
     * 数据库密码
     */
    private String password;

    /**
     * 在什么路径下生成 controller 等包：
     * com.jxkj.xxx
     */
    private String parentPackage;

    /**
     * 表名，传String数组
     */
    private String[] tableNames;

    /**
     * 项目的绝对路径
     */
    private String projectPath;
}
