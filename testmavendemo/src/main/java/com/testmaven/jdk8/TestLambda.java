package com.testmaven.jdk8;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class TestLambda {

    /**
     *  java 8 内置的四大核心函数式接口
     *  Consumer<T>消费性接口
     *     void accept(T t);
     *
     *  Supplier<T>:供给型接口
     *     T get();
     *
     *  Function<T,R> : 函数型接口
     *     R apply(T t);
     *
     *  Predicate<T> :断言型接口
     *     boolean test(T t);
     *
     */

     //Consumer<T>消费性接口
    //void accept(T t);

     public void happy(double money, Consumer<Double> con){
         con.accept(money);
     }

     @Test
     public void test1(){
         happy(100000,(m) -> System.out.println("你们"+m+"元"));
     }

     // Supplier<T>:供给型接口
     // T get();
     // 需求：产生指定个数的整数，并放入集合中
     @Test
     public void test2(){
         List<Integer> numList = getNumList(5, () -> (int) (Math.random() * 100));

         for(Integer n:numList){
             System.out.println(n);
         }
     }

     public List<Integer> getNumList(int num, Supplier<Integer> supplier){
         List<Integer> list = new ArrayList<>();
         for(int i=0;i<num;i++){
               Integer n = supplier.get();
               list.add(n);
         }
         return list;
     }
     // Function<T,R> : 函数型接口
     // R apply(T t);  用于处理字符串
     public String strHandler(String str, Function<String,String> function){
          return function.apply(str);
     }

     @Test
     public void test3(){
         String s = strHandler("  234234fsfs  ", (str) -> str.trim());
         System.out.println(s);
     }

     //Predicate<T> :断言型接口
     //boolean test(T t);
     //需求：将满足条件的字符串放入到集合中去
     public List<String> filterStr(List<String> list, Predicate<String> pre){
          List<String> strList = new ArrayList<>();
          for(String str:list){
              if(pre.test(str)){
                  strList.add(str);
              }
          }
          return strList;
     }
    @Test
    public void test4(){
         List<String> list =Arrays.asList("gdfg","dfgjhg","fghfgh","fghfd","ok");
         List<String> list1 = filterStr(list, (x) -> x.length() > 4);
         for(String l:list1){
             System.out.println(l);
         }
    }



}
