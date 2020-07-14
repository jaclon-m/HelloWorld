package com.jaclon.dubbo.redis.r2;

/**
 * key的设计
 * key的存活时间：
 * 无论什么时候，只要有可能就利用key超时的优势。一个很好的例子就是储存一些诸如临时认证key之类的东西。
 * 当你去查找一个授权key时——以OAUTH为例——通常会得到一个超时时间。
 * 这样在设置key的时候，设成同样的超时时间，Redis就会自动为你清除。
 *
 * 关系型数据库的redis
 * 1: 把表名转换为key前缀 如, tag:
 * 2: 第2段放置用于区分区key的字段--对应mysql中的主键的列名,如userid
 * 3: 第3段放置主键值,如2,3,4...., a , b ,c
 * 4: 第4段,写要存储的列名
 * 例：user:userid:9:username
 *
 *本类是key设计对应的工具类
 *
 * @author jaclon
 * @date 2019/9/17
 */
public class RedisKeyUtil {

    /**
     * redis的key
     * 形式为：
     * 表名:主键名:主键值:列名
     *
     * @param tableName 表名
     * @param majorKey 主键名
     * @param majorKeyValue 主键值
     * @param column 列名
     * @return
     */
    public static String getKeyWithColumn(String tableName,String majorKey,String majorKeyValue,String column){
        StringBuffer buffer = new StringBuffer();
        buffer.append(tableName).append(":");
        buffer.append(majorKey).append(":");
        buffer.append(majorKeyValue).append(":");
        buffer.append(column);
        return buffer.toString();
    }
    /**
     * redis的key
     * 形式为：
     * 表名:主键名:主键值
     *
     * @param tableName 表名
     * @param majorKey 主键名
     * @param majorKeyValue 主键值
     * @return
     */
    public static String getKey(String tableName,String majorKey,String majorKeyValue){
        StringBuffer buffer = new StringBuffer();
        buffer.append(tableName).append(":");
        buffer.append(majorKey).append(":");
        buffer.append(majorKeyValue).append(":");
        return buffer.toString();
    }
}
