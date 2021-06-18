package com.jaclon.mistakesOfBuz.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author jaclon
 * @date 2021/6/12
 */
public class DateTimeTest {

    public static void main(String[] args) {
        DateTimeTest test = new DateTimeTest();
//        test.newDateWithCalendar();
        test();
    }

    /**
     * 1. Date 并无时区问题,Date 中保存的是 UTC 时间，UTC 是以原子钟为基础的统一时间，不以
     * 太阳参照计时，并无时区划分。
     * 2. Date 中保存的是一个时间戳，代表的是从 1970 年 1 月 1 日 0 点（Epoch 时
     * 间）到现在的毫秒数
     */
    private static void test() {
        System.out.println("test");
        System.out.println(new Date(0));
        //System.out.println(TimeZone.getDefault().getID() + ":" + TimeZone.getDefault().getRawOffset()/3600/1000);
        //ZoneId.getAvailableZoneIds().forEach(id -> System.out.println(String.format("%s:%s", id, ZonedDateTime.now(ZoneId.of(id)))));
    }

    /**
     * 时间的正确保存方式
     * 1. 以 UTC 保存，保存的时间没有时区属性。我们通常说的时间戳，或 Java 中的 Date 类就是用的这种方式，这也是推荐的方
     * 式
     * 2. 以字面量保存，比如年 / 月 / 日 时: 分: 秒，一定要同时保存时区信息
     * 只有有了时区信息，我们才能知道这个 【字面量】 时间真正的时间点，否则它只是一个给人看的【时间表示】，只在当前时区有意义
     *
     * 从字面量解析成时间和从时间格式化为字面量这两类问题
     *
     * 对于同一个时间表示，比如 2020-01-02 22:00:00，不同时区的人转换成 Date
     * 会得到不同的时间（时间戳）
     * @throws ParseException
     */
    private static void wrong1() throws ParseException {
        System.out.println("wrong1");
        String stringDate = "2020-01-02 22:00:00";
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = inputFormat.parse(stringDate);
        System.out.println(date1 + ":" + date1.getTime());
        inputFormat.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        Date date2 = inputFormat.parse(stringDate);
        System.out.println(date2 + ":" + date2.getTime());
    }

    /**
     * 第二类问题是，格式化后出现的错乱，即同一个 Date，在不同的时区下格式化得到不同的
     * 时间表示
     *
     * ，要正确处理时区，在于存进去和读出来两方面：存的时候，需要使用正确的当前时区
     * 来保存，这样 UTC 时间才会正确；读的时候，也只有正确设置本地时区，才能把 UTC 时
     * 间转换为正确的当地时间。
     * @throws ParseException
     */
    private static void wrong2() throws ParseException {
        System.out.println("wrong2");
        String stringDate = "2020-01-02 22:00:00";
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = inputFormat.parse(stringDate);
        System.out.println(new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss Z]").format(date));
        TimeZone.setDefault(TimeZone.getTimeZone("America/New_York"));
        System.out.println(new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss Z]").format(date));
    }

    /**
     * 对于日期时间表示，LocalDateTime 不带有时区属性，所以命名为本地时区的日期时
     * 间；而 ZonedDateTime=LocalDateTime+ZoneId，具有时区属性。因此，
     * LocalDateTime 只能认为是一个时间表示，ZonedDateTime 才是一个有效的时间。
     */
    private static void right() {
        System.out.println("right");
        String stringDate = "2020-01-02 22:00:00";
        ZoneId timeZoneSH = ZoneId.of("Asia/Shanghai");
        ZoneId timeZoneNY = ZoneId.of("America/New_York");
        ZoneId timeZoneJST = ZoneOffset.ofHours(9);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ZonedDateTime date = ZonedDateTime.of(LocalDateTime.parse(stringDate, dateTimeFormatter), timeZoneJST);

        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss Z");
        System.out.println(timeZoneSH.getId() + outputFormat.withZone(timeZoneSH).format(date));
        System.out.println(timeZoneNY.getId() + outputFormat.withZone(timeZoneNY).format(date));
        System.out.println(timeZoneJST.getId() + outputFormat.withZone(timeZoneJST).format(date));
    }


    void newDate(){
        Date date = new Date(2012, 11, 31, 11, 12, 12);
        System.out.println(date);
    }

    /**
     * 年应该是和 1900 的差值，月应该是从
     * 0 到 11 而不是从 1 到 12
     */
    void newDateWithCalendar(){
        Calendar instance = Calendar.getInstance();
        instance.set(2012,11,31,11,12,12);
        System.out.println(instance.getTime());
        Calendar instance2 = Calendar.getInstance(TimeZone.getTimeZone("America/New_York"));
        instance2.set(2012,Calendar.DECEMBER,31,11,12,13);
        System.out.println(instance2.getTime());
    }
}
