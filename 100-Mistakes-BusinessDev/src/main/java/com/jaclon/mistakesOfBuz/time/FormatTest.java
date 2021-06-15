package com.jaclon.mistakesOfBuz.time;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

/**
 * SimpleDateFormat 的各种格式化模式
 * 跨年问题：小写 y 是年，而大写 Y 是 week year，也就是所在的周属于哪一年
 *
 * 定义的 static 的 SimpleDateFormat 可能会出现线程安全问题
 *
 * 当需要解析的字符串和格式不匹配的时候，SimpleDateFormat 表现得很宽容
 *
 * @author jaclon
 * @date 2021/6/12
 */
public class FormatTest {
    /**
     * 使用 DateTimeFormatterBuilder 来定义格式化字符串，不用去记忆使用大写的 Y 还是小写的 Y，大写的 M 还是小写的 m
     * DateTimeFormatter 是线程安全的，可以定义为 static 使用；最后，
     * DateTimeFormatter 的解析比较严格，需要解析的字符串和格式不匹配时，会直接报错
     */
    private static DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder()
            .appendValue(ChronoField.YEAR)
            .appendLiteral("/")
            .appendValue(ChronoField.MONTH_OF_YEAR)
            .appendLiteral("/")
            .appendValue(ChronoField.DAY_OF_MONTH)
            .appendLiteral(" ")
            .appendValue(ChronoField.HOUR_OF_DAY)
            .appendLiteral(":")
            .appendValue(ChronoField.MINUTE_OF_HOUR)
            .appendLiteral(":")
            .appendValue(ChronoField.SECOND_OF_MINUTE)
            .appendLiteral(".")
            .appendValue(ChronoField.MILLI_OF_SECOND)
            .toFormatter();

}
