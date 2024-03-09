package com.jason.java;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Date;
import java.util.Locale;
import java.text.DateFormat;
/**
 * TODO Description
 *
 * @author jaclon
 * @since 2022/6/22 16:49
 */
public class DateTimeTest {
    public static void main(String args[]) throws ParseException {
        //testDateFormat();
        testInstant();
    }

    public static void testDateFormat() throws ParseException {
        //创建日期
        Date date = new Date();

        //创建不同的日期格式
        DateFormat df1 = DateFormat.getInstance();
        DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss EE");
        //产生一个指定国家指定长度的日期格式，长度不同，显示的日期完整性也不同
        DateFormat df3 = DateFormat.getDateInstance(DateFormat.FULL, Locale.CHINA);
        DateFormat df4 = new SimpleDateFormat("yyyy年MM月dd日 hh时mm分ss秒 EE", Locale.CHINA);
        DateFormat df5 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss EE", Locale.US);
        DateFormat df6 = new SimpleDateFormat("yyyy-MM-dd");

        //将日期按照不同格式进行输出
        System.out.println("-------将日期按照不同格式进行输出------");
        System.out.println("按照Java默认的日期格式，默认的区域：" + df1.format(date));
        System.out.println("按照指定格式 yyyy-MM-dd hh:mm:ss EE ，系统默认区域      :" + df2.format(date));
        System.out.println("按照日期的FULL模式，区域设置为中文：" + df3.format(date));
        System.out.println("按照指定格式 yyyy年MM月dd日 hh时mm分ss秒 EE ，区域为中文：" + df4.format(date));
        System.out.println("按照指定格式 yyyy-MM-dd hh:mm:ss EE ，区域为美国：" + df5.format(date));
        System.out.println("按照指定格式 yyyy-MM-dd ，系统默认区域：" + df6.format(date));

        //将符合该格式的字符串转换为日期，若格式不相配，则会出错
        Date date1 = df1.parse("07-11-30 下午2:32");
        Date date2 = df2.parse("2007-11-30 02:51:07 星期五");
        Date date3 = df3.parse("2007年11月30日 星期五");
        Date date4 = df4.parse("2007年11月30日 02时51分18秒 星期五");
        Date date5 = df5.parse("2007-11-30 02:51:18 Friday");
        Date date6 = df6.parse("2007-11-30");

        System.out.println("-------输出将字符串转换为日期的结果------");
        System.out.println(date1);
        System.out.println(date2);
        System.out.println(date3);
        System.out.println(date4);
        System.out.println(date5);
        System.out.println(date6);
    }

    //-----java8-----

    public  static  void testInstant(){
        Instant instant1 = Instant.now();
        System.out.println(instant1); //2019-05-31T16:19:28.719Z

        Instant instant2 = Instant.parse("2018-11-11T10:12:35.342Z");
        System.out.println(instant2); //2018-11-11T10:12:35.342Z

        //java.util.Date与Instant可相互转换
        //Instant timestamp = new Date().toInstant();
        Date.from(Instant.now());

        //为了更好的显示，代码改写为
        Date date = new Date();
        Instant timestamp = date.toInstant();
        System.out.println(date);
        System.out.println(timestamp);
        Instant now1 = Instant.now();
        Date date1 = Date.from(now1);
        System.out.println(now1);
        System.out.println(date1);
    }

    public static void convert(){
        Date date = new Date();
        System.out.println("current date: " + date);

        // Date -> LocalDateTime
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        System.out.println("localDateTime by Instant: " + localDateTime);

        //2. Date -> LocalDateTime
        localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        System.out.println("localDateTime by ofInstant: " + localDateTime);
        //由于JDK8实现了向下兼容，所以Date里在JDK8版本引入了2个方法，from和
        //toInstant，所以我们可以借助这两个方法来实现LocalDateTime到Date的转换
        LocalDateTime localDateTime2 = LocalDateTime.now();
        System.out.println("localDateTime: " + localDateTime2);

        // LocalDateTime -> Date
        Date date2 = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        System.out.println("LocalDateTime -> current date: " + date2);

        // LocalDate -> Date，时间默认都是00
        LocalDate localDate = LocalDate.now();
        date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        System.out.println("LocalDate -> current date: " + date);
    }
}
