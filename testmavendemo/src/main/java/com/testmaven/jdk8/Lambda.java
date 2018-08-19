package com.testmaven.jdk8;

import org.junit.jupiter.api.Test;

import java.util.*;
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
     * 语法格式五： 若Lambda体中只有一条语句，return 和大括号都可以省略不写
     *
     * 语法格式六： Lambda表达式的参数列表的数据类型可以省略不写，因为jvm编译器通过上下文推断出，数据类型，即“类型推断”
     *
     * 左右遇一   括号省
     *
     * 左侧推断类型省
     *
     *
     * 二、Lambda表达式需要“函数式接口”的支持
     *
     *    函数式接口：接口中只有一个抽象方法的接口，称为函数式接口。可以使用注解 @FunctionalInterface 修饰
     *
     *    可以检查是否是函数式接口。
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
        System.out.println(comn.compare(10,2));

    }

    @Test
    public void test4(){
        System.out.println("----------- 语法格式五： 若Lambda体中只有一条语句，return 和大括号都可以省略不写----------------------");
        Comparator<Integer> comn = (x, y) -> Integer.compare(x,y);
        System.out.println(comn.compare(10,2));
    }

    @Test
    public void test5(){
        List<String> list =new ArrayList<>();
        show(new HashMap<>());
    }

    public void show(Map<String,Integer> map){

    }

    //需求：对一个数进行运算
    public Integer operation(Integer num, MyFun mf){
         return mf.getValue(num);
    }

    @Test
    public void test6(){
       Integer num= operation(100,(x) -> x*x);
        System.out.println(num);
    }

}
