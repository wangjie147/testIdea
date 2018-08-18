package com.testmaven.jdk8;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

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
    public List<Employee> filterEmployees(List<Employee> list, MyPredicate<Employee> myPredicate){
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
        List<Employee> employees = filterEmployees(emp, new MyPredicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return false;
            }
        });
        for(Employee employee:employees){
            System.out.println(employee);
        }
    }
    //获取当前公司中员工工资大于5000的员工信息
                public List<Employee> filterEmployeessalary(List<Employee> list){
                    List<Employee> emps = new ArrayList<>();
                    for(Employee em:list){
                        if(em.getSalary()>=5000){
                            emps.add(em);
                        }
                    }
                    return emps;
                }
    //这两个需求都有相同的代码，而且只是改变这一句代码：em.getAge()>=35    em.getSalary()>=5000
    //优化1、使用设计模式：策略模式
    public List<Employee> filterEmployeeAge(List<Employee> list,MyPredicate<Employee> mp){
        List<Employee> emps = new ArrayList<>();
        for(Employee em:list){
            if(mp.test(em)){
                emps.add(em);
            }
        }
        return emps;
    }
    @Test
    public void test4(){
        List<Employee> employees = filterEmployeeAge(emp,new FilterEmployeeByAge());
        for(Employee employee:employees){
            System.out.println(employee);
        }
        List<Employee> employeeSa = filterEmployeeAge(emp,new FilterEmployeeBySalary());
        for(Employee employee:employeeSa){
            System.out.println(employee);
        }

    }

    //优化2:匿名内部类
    @Test
    public void test5(){
        List<Employee> employees=filterEmployeeAge(emp, new MyPredicate<Employee>() {
             @Override
             public boolean test(Employee t) {
                 return t.getSalary()<=5000;
             }
         });
        for(Employee employee:employees){
            System.out.println(employee);
        }

    }

    //优化3:lamdba表达式
    @Test
    public void test6(){

        List<Employee> employees= filterEmployeeAge(emp,(e) -> e.getSalary() <=5000);
        employees.forEach(System.out::println);
    }

    //优化4:
    @Test
    public void test7(){
       /* List<Employee> employees=emp.stream().filter((e) -> e.getSalary() <=5000).collect(Collectors.toList());
        employees.forEach(System.out::println);*/
        emp.stream().filter((e) -> e.getSalary() <=5000).forEach(System.out::println);
        System.out.println("-------//取前两个----------------------");
        emp.stream().filter((e) -> e.getSalary() <=5000).limit(2).forEach(System.out::println);
        System.out.println("---------------取名字--------------");
        emp.stream().map(Employee::getName).forEach(System.out::println);
    }

}
