package com.testmaven.jdk8;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class LambdaPri {


    /**
     * 1、调用Collections.sort()方法，通过定制排比比较两个employee（先按年龄比，年龄相同按姓名比），使用lambda表达式作为参数传递。
     *
     * 2、声明函数式接口，接口中声明抽象方法，public String getValue(String str);
     *
     *    声明类TestLambda,类中编写方法使用接口作为参数，将一个字符串转换成大写，并作为方法的返回值；
     *
     *    再将一个字符串的第2个和第4个索引位置进行截取子串。
     *
     * 3、声明一个带两个泛型的函数式接口，泛型类型为<T,R>  T 为参数，R 为返回值
     *
     *    接口中声明对应抽象方法
     *
     *    在TestLambda类中声明方法，使用接口作为参数，计算两个long型参数的和。
     *
     *    再计算两个long型参数的乘积。
     *
     *
     *
     *
     */

    List<Employee> emps = Arrays.asList(
            new Employee("zhangsan",11,234234.00),
            new Employee("lisi",31,14234.00),
            new Employee("zhouwu",11,2344.00),
            new Employee("wangsan",31,24234.00),
            new Employee("liuqi",11,12344.00),
            new Employee("zhengwang",13,4234.00)
    );//数组转集合

    @Test
    public void test1(){
        Collections.sort(emps,(e1,e2) ->{
             if(e1.getAge() == e2.getAge()){
                return e1.getName().compareTo(e2.getName());
             }else{
                return Integer.compare(e1.getAge(),e2.getAge());
             }
        });
        for(Employee emp:emps){
            System.out.println(emp);
        }
    }

    //2 处理字符串的方法：
    public String strHandler(String str,MyFunction mf){
         return mf.getValue(str);
    }

    @Test
    public void test2(){
        String s = strHandler("  sdfsdfsdf ", (x) -> x.trim());
        System.out.println(s);
        System.out.println("-------------------------------------------------------");
        String s1 = strHandler("sdfsdfsdf", (x) -> x.toUpperCase());
        System.out.println(s1);
    }
}
