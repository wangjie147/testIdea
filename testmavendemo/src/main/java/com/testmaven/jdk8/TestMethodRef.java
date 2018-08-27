package com.testmaven.jdk8;

import org.junit.jupiter.api.Test;

import javax.sound.midi.Soundbank;
import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class TestMethodRef {

    /**
     * 方法引用：若Lambda体中的内容有方法已经实现了，我们可以使用“方法引用”
     * （可以理解为方法引用是lambda表达式的另外一种表现形式）
     * 主要有三种语法格式：
     * 对象：：实例方法名
     *
     * 类：：静态方法名
     *
     * 类：：实例方法名
     *
     *
     * 注意事项：1、lambda体中调用的方法的参数列表与返回值的类型，要与函数式接口中的抽象方法的函数列表和返回值类型保持一致。
     *            2、若Lambda体   参数列表中的第一参数是实例方法的调用者，而第二个参数是实例方法的参数时，可以使用className：：method
     *
     *
     * 构造器引用：
     *
     *
     */

      //对象：：实例方法名
      //@Test
      public void test1(){

          PrintStream ps1 =System.out;

          Consumer<String> con =(x) -> ps1.println(x);

          PrintStream ps =System.out;

          Consumer<String> con1 =ps::println;

          Consumer<String> conn2 =System.out::println;

          conn2.accept("acsdas");
      }

      //@Test
     /* public void test2(){
          Employee emp = new Employee();
          Supplier<String> sup =() ->emp.getName();
          String str =sup.get();
          System.out.println(str);

          Supplier<Integer> sup2=emp::getAge;
          Integer num = sup2.get();
          System.out.println(num);
      }*/



      //类：：静态方法名
      //@Test
     /* public void test3(){
          Comparator<Integer> com = (x,y) -> Integer.compare(x,y);
          Comparator<Integer> com1 = Integer::compare;
      }*/

      //类：：实例方法名
      //@Test
    /*public void test4(){
        BiPredicate<String,String> bp = (x,y) ->x.equals(y);

        BiPredicate<String,String> bp2 = String::equals;

    }*/

    //@Test
    /*public void test5(){
          Supplier<Employee> sup = () -> new Employee();

          //构造器引用方式
          Supplier<Employee> sup2 = Employee::new;

           Employee emp = sup2.get();//自动匹配无参的构造器

           System.out.println(emp);
    }*/

    //@Test
   /* public void test6(){
        Function<Integer,Employee> fun  = (x) -> new Employee(x);

        Function<Integer,Employee> fun2 =Employee::new;
    }*/

}
