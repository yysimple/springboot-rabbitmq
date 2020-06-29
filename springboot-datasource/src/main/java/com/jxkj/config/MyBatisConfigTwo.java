package com.jxkj.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @MapperScan： 前面的参数是找到接口包 后面的参数是 SqlSessionFactory 的实例名称
 */
@Configuration
@MapperScan(value = "com.jxkj.jxkj",sqlSessionFactoryRef = "sqlSessionFactoryBean2")
public class MyBatisConfigTwo {

    @Autowired
    @Qualifier("dsTwo")
    DataSource dataSource;
    @Bean
    SqlSessionFactory sqlSessionFactoryBean2() throws Exception{
        // 新建一个工厂实例
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        // 将数据源设置给工厂实例
        sqlSessionFactoryBean.setDataSource(dataSource);
        // 最后返回一个工厂
        return sqlSessionFactoryBean.getObject();
    }

    /**
     * SqlSessionTemplate 是一个线程安全的类 主要用来管理 sqlsession
     * @return
     * @throws Exception
     */
    @Bean
    SqlSessionTemplate sqlSessionTemplate2() throws Exception{
        return new SqlSessionTemplate(sqlSessionFactoryBean2());
    }
}
