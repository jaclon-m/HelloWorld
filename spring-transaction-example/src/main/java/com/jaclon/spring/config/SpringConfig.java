package com.jaclon.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * TODO Description
 *
 * @author jaclon
 * @since 2024/10/9 15:33
 */
// 告知这是个配置类
@Configuration
// 告知扫描哪些包
@ComponentScan("com.jaclon.spring")
// 告知加载哪个配置文件
@PropertySource("classpath:jdbc.properties")
// 告知加载哪些配置文件
@Import({JdbcConfig.class,MybatisConfig.class})
//开启注解式事务驱动
@EnableTransactionManagement
public class SpringConfig {
}