package com.testmaven.jdk8;

import org.junit.jupiter.api.Test;

import java.util.*;

public class jdk {


    /**
     *  java 8   新特性
     *
     *  速度更快
     *  代码更少（增加了新的语法lambda表达式）
     *  强大的stream API
     *  便于并行
     *  最大化减少空指针异常( Optional)
     *
     *  其中最核心的为 Lambda 表达式与 Stream API
     *
     *
     *
     */

    public static void main(String[] args) {

        HashMap map =new HashMap();  //哈希表    数组—链表-红黑树

        /**
         *
         * lambda表达式
         *
         * lambda是一个匿名函数，我们可以把lambda表达式理解为是一段可以传递的代码（将代码像数据一样进行传递）。
         *
         * 可以写出更简洁、更灵活的代码。
         *
         * 作为一种更紧凑的代码风格，是java语言表达能力得到提升。
         *
         */
        //匿名内部类
        Comparator<Integer> com = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1,o2);
            }
        };

        TreeSet<Integer> ts = new TreeSet<>(com);

    }

    @Test
    public void test2(){
         //lamdba表达式
         Comparator<Integer> com =(x,y) -> Integer.compare(x,y);
         TreeSet<Integer> ts = new TreeSet<>(com);
    }

    //获取当前公司中员工年龄大于35的员工信息
    List<Employee> emp = Arrays.asList(
            new Employee("zhangsan",1111,234234.00),
            new Employee("lisi",311,4234.00),
            new Employee("zhouwu",111,2344.00),
            new Employee("zhengwang",13,4234.00)
    );//数组转集合
    public List<Employee> filterEmployees(List<Employee> list){
            List<Employee> emps = new ArrayList<>();
            for(Employee em:list){
                if(em.getAge()>=35){
                    emps.add(em);
                }
            }
            return emps;
    }

    @Test
    public void test3(){
        List<Employee> employees = filterEmployees(emp);
        for(Employee employee:employees){
            System.out.println(employee);
        }
    }

}
