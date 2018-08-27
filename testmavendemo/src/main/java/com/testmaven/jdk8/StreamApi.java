package com.testmaven.jdk8;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;


/**
 * 一、stream的三个操作步骤：
 * 1、制作stream
 * 2、中间操作
 * 3、终止操作（终端操作）
 *
 */
public class StreamApi {

       //创建sream
       @Test
       public void test1(){
           //1、可以通过Collection系列集合提供的stream（）串行或者parallelStream()并行
           List<String> list = new ArrayList<>();
           Stream<String> stream1 = list.stream();
           //2、通过arrays 中的静态方法stream*（）获取数组流
           Employee[] emps = new Employee[10];
           Stream<Employee> stream2 = Arrays.stream(emps);

           //3、通过stream类中的静态方法
           Stream<String> stream3 = Stream.of("aa", "aaaa", "aaaa", "uuu");

           //4、创建无限流
               // 迭代(起始值，)
               //Stream<Integer> stream4 = Stream.iterate(0, (x) -> x + 2);
               //Stream.iterate(0, (x) -> x + 2).forEach(System.out::println);
               //Stream.iterate(0, (x) -> x + 2).limit(10).forEach(System.out::println);
               //生成
              Stream<Double> generate = Stream.generate(() -> Math.random());
              generate.limit(10).forEach(System.out::println);

       }
}
