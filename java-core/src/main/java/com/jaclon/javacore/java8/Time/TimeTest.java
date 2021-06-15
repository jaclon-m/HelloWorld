package com.jaclon.javacore.java8.Time;



import org.apache.commons.lang3.time.DateUtils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.Set;

public class TimeTest {


    public static void main(String[] args) {
        Date date = new Date();
        System.out.println(date.toInstant().toString());
       /* try {
            test8();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }

    public static void test8() throws InterruptedException {
        LocalDateTime now = LocalDateTime.now();
        Thread.sleep(1200);
        long between = ChronoUnit.SECONDS.between(now, LocalDateTime.now());
        System.out.println(between);
    }
    public void test7(){
        LocalDate localDate = LocalDate.now();
        LocalDateTime localDateTime = LocalDateTime.now();
        String hourStr = localDateTime.toString().substring(0, 13).replace("T", " ");
        System.out.println(localDate.toString() + "==========" +hourStr);
    }

    public void test6(){
        LocalDateTime ldt = LocalDateTime.now(ZoneId.of("US/Pacific"));
        Set<String> zoneIds = ZoneId.getAvailableZoneIds();
        zoneIds.stream().forEach(System.out::println);
        System.out.println(ldt);
    }

    //DateTimeFormatter
    public void test5(){
        //DateTimeFormatter dtf = DateTimeFormatter.BASIC_ISO_DATE;
        //如果MM小写，调用parse方法会报错：Unable to obtain LocalDateTime from TemporalAccessor
        DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss E");
        LocalDateTime ldt = LocalDateTime.now();
        String format = ldt.format(dtf1);
        System.out.println(format);

        LocalDateTime parse = ldt.parse(format,dtf1);
        System.out.println(parse);
    }

    //TemporalAdjuster
    public void test4(){
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);
        LocalDateTime ldt1 = ldt.withDayOfMonth(10);
        System.out.println(ldt1);
        LocalDateTime ldt2 = ldt.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        System.out.println(ldt2);


    }
    //时间间隔
    public void test3(){
        Instant instant1 = Instant.now();
        try {
            Thread.sleep(1003);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Instant instant2 = Instant.now();
        System.out.println("间隔 " + Duration.between(instant1,instant2));
        LocalDateTime ldt1 = LocalDateTime.now();
        LocalDateTime ldt2 = LocalDateTime.of(2018, 01, 02, 12, 23, 5);
        System.out.println(Period.between(ldt1.toLocalDate(),ldt2.toLocalDate() ));
    }


    //时间戳
    public void test2(){
        Instant ins = Instant.now();
        System.out.println(ins);
        OffsetDateTime offset = ins.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offset);
    }

    public void test1(){
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        LocalDate localDate = now.toLocalDate();
        System.out.println(localDate);
        LocalDateTime ldt = LocalDateTime.of(2019, 2, 16, 13, 06, 10);
        System.out.println(ldt);
        LocalDateTime ldt1 = ldt.plusYears(20);
        System.out.println(ldt1);
        System.out.println(ldt1.minusWeeks(56));
        System.out.println(ldt1.getDayOfMonth());
        System.out.println(ldt.getMonthValue());
        System.out.println(ldt.getMonth());
    }
}
