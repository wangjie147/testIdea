package com.testmaven.jdk8;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.function.Consumer;

public class Lambda {

    /**
     * lambda表达式的语法
     *
     * java8：引入一个新的操作符“->”该操作符称为箭头操作符或者称为lambda操作符
     *
     * 左侧：Lambda表达式的参数列表    (T t)
     *
     * 右侧：Lambda表达式中所需执行的功能，即Lambda体     实现public boolean test(T t)方法;
     *
     *
     * Lambda表达式实现接口
     *
     *
     * 语法格式一：
     *无参数，无返回值
     * () -> System.out.println("hello Lambda")
     *
     * 语法格式二：
     * 有一个参数，无返回值
     *
     * (x) -> System.out.println("hello Lambda")
     *
     *语法格式三：
     *有一个参数，无返回值（小括号可以省略不写）
     *
     *语法格式四：
     *
     * 有两个以上的参数，有返回值并且Lambda体中有多条语句
     *
     * 语法格式五：
     *
     *
     */
     @Test
     public void test1(){

         int num =0;//jdk1.7以前必须是final，jdk1.8默认是final，不需要手动加

         Runnable r = new Runnable() {
             @Override
             public void run() {
                 System.out.println("hello world"+num);
             }
         };

         r.run();

         System.out.println("--------------无参数，无返回值--------------------");
         Runnable r1 =() -> System.out.println("hello world");

         r1.run();



     }



    @Test
    public void test2(){
        System.out.println("----------- 有一个参数，无返回值-----------------------");
        Consumer<String> consumer=(x) -> System.out.println(x);
        consumer.accept("我是参数");
        System.out.println("----------- 有一个参数，无返回值（小括号可以省略不写）-----------------------");
        Consumer<String> consumer1= x -> System.out.println(x);
        consumer1.accept("我是参数");
    }

    @Test
    public void test3(){
        System.out.println("----------- 有两个以上的参数，有返回值并且Lambda体中有多条语句-----------------------");
         Comparator<Integer> comn = (x, y) -> {
             System.out.println("我是函数体");
             return Integer.compare(x,y);
         };

    }


}
