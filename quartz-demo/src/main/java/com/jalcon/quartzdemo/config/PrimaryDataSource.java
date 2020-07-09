/**
 * Juzifenqi.com Inc.
 * Copyright (c) 2019-2029 All Rights Reserved.
 */
package com.jalcon.quartzdemo.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @Classname PrimaryDataSource
 * @Description TODO
 *
 * @author jaclon
 * @date 2020/3/4
 */
@Configuration
public class PrimaryDataSource {


    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.primarydatasource")
    public DataSourceProperties dataSourceProperties(){
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    public DataSource dataSource(DataSourceProperties properties){
       return properties.initializeDataSourceBuilder().build();
    }


}
