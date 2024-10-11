package com.jaclon.spring.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

/**
 * TODO Description
 *
 * @author jaclon
 * @since 2024/10/9 15:36
 */
public class MybatisConfig {

    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource){
        // 获取SqlSessionFactoryBean对象
        SqlSessionFactoryBean ssfb = new SqlSessionFactoryBean();
        // 别名包
        ssfb.setTypeAliasesPackage("com.jaclon.spring.domain");
        // 数据源
        ssfb.setDataSource(dataSource);
        return ssfb;
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer msc = new MapperScannerConfigurer();
        msc.setBasePackage("com.jaclon.spring.dao");
        return msc;
    }
}
