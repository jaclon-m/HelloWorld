/**
 * innodb采用页存储数据，页内有槽，二分搜索；页索引用B+树构建
 * B+树特点： a 分层存储，层内数据有指向下个节点的指针（数据范围左包含[) ）和下层的指针 b 非叶节点不存储数据、叶节点存储数据 c 叶节点数据双向指针连接
 *
 * 数据库基于成本决定是否走索引，可以粗略计算 IO成本为1，CPU成本为0.2
 * @author jaclon
 * @since 2021/5/28 11:36
 */
package com.jaclon.mistakesOfBuz.database;