package com.jaclon.helloworld.elasticsearch.entity;

import org.elasticsearch.index.VersionType;
import org.springframework.data.annotation.Persistent;

import java.lang.annotation.*;

/**
 * @author jaclon
 */
@Persistent
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Document {
    /**
     * 索引库的名称，个人建议以项目的名称命名
     *
     * @return
     */
    String indexName();

    @Deprecated
    String type() default ""; // 类型，7.x之后以废弃

    short shards() default 1;//默认分区数

    short replicas() default 1; // 每个分区默认的备份数

    String refreshInterval() default "1s"; // 刷新间隔

    String indexStoreType() default "fs"; // 索引文件存储类型

    boolean createIndex() default true;  // 是否创建索引

    VersionType versionType() default VersionType.EXTERNAL;  // 版本
}
