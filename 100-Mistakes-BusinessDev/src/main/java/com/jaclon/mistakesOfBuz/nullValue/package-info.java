/**
 * arthas性能监控
 *
 *通过 sum 函数统计一个只有 NULL 值的列的总和，比如 SUM(score);
 * select 记录数量，count 使用一个允许 NULL 的字段，比如 COUNT(score);
 * 使用 =NULL 条件查询字段值为 NULL 的记录，比如 score=null 条件。
 * 得到的结果，分别是 null、0 和空 List:
 *
 * MySQL 中 sum 函数没统计到任何记录时，会返回 null 而不是 0，可以使用 IFNULL 函数把 null 转换为 0;
 * MySQL 中 count 字段不统计 null 值，COUNT(*) 才是统计所有记录数量的正确方 式。
 * MySQL 中 =NULL 并不是判断条件而是赋值，对 NULL 进行判断只能使用 IS NULL 或 者 IS NOT NULL。
 *
 *  @Query(nativeQuery = true, value = "SELECT IFNULL(SUM(score),0) FROM `user`")
 *  Long right1();
 *  @Query(nativeQuery = true, value = "SELECT COUNT(*) FROM `user`")
 *  Long right2();
 *  @Query(nativeQuery = true, value = "SELECT * FROM `user` WHERE score IS NULL") 6 List<User> right3();
 *
 *  ---
 *  在MySQL的使用中，对于索引列，建议都设置为not null，因为如果有null的话，MySQL需要单独专门处理null值，会额外耗费性能
 *  ConcurrentMaps（ConcurrentHashMaps，ConcurrentSkipListMaps）不允许使用null的主要原因是，无法容纳在非并行映射中几乎无法容忍的歧义。
 *  最主要的是，如果map.get(key)return null，则无法检测到该键是否显式映射到null该键。
 *  在非并行映射中，您可以通过进行检查 map.contains(key)，但在并行映射中，两次调用之间的映射可能已更改。
 *  MyBatis @Column注解的updateIfNull属性，可以控制，当对应的列value为null时，updateIfNull的true和false可以控制
 *
 * @author jaclon
 * @since 2021/5/28 15:55
 */
package com.jaclon.mistakesOfBuz.nullValue;