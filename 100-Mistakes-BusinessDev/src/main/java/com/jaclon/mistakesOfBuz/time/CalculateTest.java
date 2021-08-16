package com.jaclon.mistakesOfBuz.time;

/**
 * 推荐使用java8
 *
 * Java 8 中有
 * 一个专门的类 Period 定义了日期间隔，通过 Period.between 得到了两个 LocalDate 的
 * 差，返回的是两个日期差几年零几月零几天。如果希望得知两个日期之间差几天，直接调用
 * Period 的 getDays() 方法得到的只是最后的“零几天”，而不是算总的间隔天数
 * 可以使用 ChronoUnit.DAYS.between 解决这个问题：
 *
 * 日期时间数据始终要保存到数据库中，MySQL 中有两种数据类型 datetime 和
 * timestamp 可以用来保存日期时间。你能说说它们的区别吗，它们是否包含时区信息
 * 呢？
 *
 * @author jaclon
 * @date 2021/6/12
 */
public class CalculateTest {
}