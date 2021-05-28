/**
 * 1. == 和equals 字符串常量池 -128~127 枚举类型需要注意成员类型
 * 2. equals,hashCode,compareTo ,类加载器必须相同
 * 3. Lombok equalsAndHashCode.EXCLUDE
 *
 * * equals实现
 *  * 考虑到性能，可以先进行指针判等，如果对象是同一个那么直接返回 true;
 *  * 需要对另一方进行判空，空对象和自身进行比较，结果一定是 fasle;
 *  * 需要判断两个对象的类型，如果类型都不同，那么直接返回 false;
 *
 * @author jaclon
 * @since 2021/5/20 11:18
 */
package com.jaclon.mistakesOfBuz.compare;