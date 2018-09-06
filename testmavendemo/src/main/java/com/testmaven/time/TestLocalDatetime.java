package com.testmaven.time;

import org.junit.jupiter.api.Test;

import java.sql.SQLOutput;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Set;

public class TestLocalDatetime {

       //1  localDate localtime localDateTime
       @Test
       public void test1(){
           LocalDateTime ldt = LocalDateTime.now();//获取当前系统时间
           System.out.println(ldt);
           LocalDateTime ldt2  = LocalDateTime.of(2018,02,01,13,11,11);
           System.out.println(ldt2);
           LocalDateTime localDateTime = ldt.plusYears(2);//加两年
           System.out.println(localDateTime);
           LocalDateTime localDateTime1 = ldt.minusMonths(2);
           System.out.println(localDateTime1);
           System.out.println(ldt.getYear());
           System.out.println(ldt.getMonth());
           System.out.println(ldt.getMonthValue());
           System.out.println(ldt.getHour());
           System.out.println(ldt.getMinute());
           System.out.println(ldt.getSecond());
       }
       //Instant :时间戳 （以unix元年：1970年1月1日00.00.00到某个时间之间的毫秒值）
      @Test
      public void test2(){
          Instant ins1 = Instant.now();//默认获取UTC时区的时间
          System.out.println(ins1);
          OffsetDateTime odt = ins1.atOffset(ZoneOffset.ofHours(8));
          System.out.println(odt);
          System.out.println(ins1.toEpochMilli());
          Instant inst2 = Instant.ofEpochSecond(1);
          System.out.println(inst2);
      }
      //Duration:计算两个时间之间的间隔
      //Period:计算两个日期之间的间隔
      @Test
      public void test3(){
          Instant inst1 = Instant.now();
          try {
              Thread.sleep(1000);
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
          Instant inst2 = Instant.now();
          Duration between = Duration.between(inst1, inst2);
          System.out.println(between.toMillis());
          LocalTime now = LocalTime.now();
          try {
              Thread.sleep(1000);
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
          LocalTime now1 = LocalTime.now();
          System.out.println(Duration.between(now, now1).toMillis());
      }

     @Test
     public void test4(){
         LocalDate now1 = LocalDate.of(2019,1,1);
         LocalDate now2 = LocalDate.now();
         Period between = Period.between(now1, now2);
         System.out.println(between);
     }

     //TemporalAdjuster : 时间矫正器
     @Test
     public void test5(){
         LocalDateTime now = LocalDateTime.now();
         System.out.println(now);
         LocalDateTime localDateTime = now.withDayOfMonth(10);//指定天
         System.out.println(localDateTime);
         LocalDateTime with = now.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
         System.out.println(with);
         //TemporalAdjuster是一个接口，所以可以自定义 ，函数式接口，可以使用lamdba表达式
         //自定义：下一个工作日、
         LocalDateTime with1 = now.with((l) -> {
             LocalDateTime ldt = (LocalDateTime) l;
             DayOfWeek dayOfWeek = ldt.getDayOfWeek();
             if (dayOfWeek.equals(DayOfWeek.SATURDAY)) {
                 return ldt.plusDays(3);
             } else if (dayOfWeek.equals(DayOfWeek.FRIDAY)) {
                 return ldt.plusDays(2);
             } else {
                 return ldt.plusDays(1);
             }
         });
         System.out.println(with1);

     }
     //DateTimeFormatter:格式化时间、日期
     @Test
     public void test6(){
         DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE_TIME;
         LocalDateTime ldt = LocalDateTime.now();
         String format = ldt.format(dtf);
         System.out.println(format);
     }
    @Test
    public void test7(){
        DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE;
        LocalDateTime ldt = LocalDateTime.now();
        String format = ldt.format(dtf);
        System.out.println(format);
    }

    @Test
    public void test8(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH时mm分ss秒");
        LocalDateTime ldt = LocalDateTime.now();
        String format = dtf.format(ldt);
        System.out.println(format);
        LocalDateTime parse = ldt.parse(format,dtf);
        System.out.println(parse);
    }

     //带时区的   ZonedDate\ZonedTime\ZonedDateTime
     @Test
     public void test9(){
         Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
         availableZoneIds.forEach(System.out::println);
     }
    @Test
    public void test10() {
        LocalDateTime ldt = LocalDateTime.now(ZoneId.of("Europe/Monaco"));
        System.out.println(ldt);
        LocalDateTime now = LocalDateTime.now();
        ZonedDateTime zonedDateTime = now.atZone(ZoneId.of("Europe/Monaco"));
        System.out.println(zonedDateTime);
    }
}
