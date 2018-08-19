package com.testmaven.jdk8;

import org.junit.jupiter.api.Test;

import java.util.function.Consumer;

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

     public void happy(double money, Consumer<Double> con){
         con.accept(money);
     }

     @Test
     public void test1(){
         happy(100000,(m) -> System.out.println("你们"+m+"元"));
     }
}
