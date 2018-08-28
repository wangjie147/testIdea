package com.testmaven.jdk8;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

public class StreamAPI3 {

    /**
     *
     * 终止操作
     *
     * 查找与匹配
     * allMatch --检查是否匹配所有元素
     * anyMatch  --- 检查是否至少匹配一个元素
     * noneMatch  --检查是否没有匹配所有元素
     * findFirst ---返回第一个元素
     * findAny   -- 返回当前流中的任意元素
     * count --返回流中元素的总个数
     * max -- 返回流中的最大值
     * min --返回流中的最小值
     */

    List<Employee> emp = Arrays.asList(
            new Employee("zhangsan",1111,234234.00,Employee.Status.FREE),
            new Employee("lisi",311,4234.00,Employee.Status.BUSY),
            new Employee("zhouwu",111,2344.00,Employee.Status.FREE),
            new Employee("zhengwang",13,4234.00,Employee.Status.VOCATION),
            new Employee("zhouwu",111,2344.00,Employee.Status.FREE),
            new Employee("zhengwang",13,4234.00,Employee.Status.BUSY)
    );

    @Test
    public void test1(){
        System.out.println("-----------------检查是否匹配所有元素-------------------------------------");
        boolean b = emp.stream().allMatch((e) -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b);
        System.out.println("-------------------检查是否至少匹配一个元素-----------------------------------");
        boolean b1 = emp.stream().anyMatch((e) -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b1);
        System.out.println("-------------------检查是否没有匹配所有元素-----------------------------------");
        boolean b2 = emp.stream().noneMatch((e) -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b2);
        System.out.println("-------------------返回第一个元素-----------------------------------");
        Optional<Employee> first = emp.stream().sorted((e1, e2) -> -Double.compare(e1.getSalary(), e2.getSalary())).findFirst(); //取出工资最高的
        //Optional的使用解决了空指针异常。     first.orElse()相当于sql中如果为空，就给一个默认值。
        System.out.println(first.get());//取出Employee对象
        System.out.println("-------------------返回当前流中的任意元素-----------------------------------");
        Optional<Employee> any = emp.stream().filter((e) -> e.getStatus().equals(Employee.Status.FREE)).findAny();//串行流
        //emp.parallelStream().filter((e) -> e.getStatus().equals(Employee.Status.FREE)).findAny();  并行流，谁先找到算谁。多个线程同时找
        System.out.println(any.get());//取出Employee对象
        System.out.println("-------------------返回流中元素的总个数-----------------------------------");
        long count = emp.stream().count();
        System.out.println(count);
        System.out.println("-------------------返回流中的最大值-----------------------------------");
        Optional<Employee> max = emp.stream().max((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
        System.out.println(max.get().getSalary());
        System.out.println("-------------------返回流中的最小值-----------------------------------");
        Optional<Double> min = emp.stream().map(Employee::getSalary).min(Double::compare);
        System.out.println(min.get());
    }

    @Test
    public void test2(){
            /**
             *  归约
             *   reduce（T identity，BinaryOperator）  / reduce(BinaryOperator)   ----可以将流中元素反复结合起来，得到一个值。
             */
            List<Integer> list = Arrays.asList(1,2,3,5,8,5,6,7,9);
            Integer sum = list.stream().reduce(0, (x, y) -> x + y);
            System.out.println(sum);
            //查询工资的总和
            Optional<Double> reduce = emp.stream().map(Employee::getSalary).reduce(Double::sum);
            System.out.println(reduce.get());
    }
    @Test
    public void test3(){
        /**
         *   收集
         *   collect  ---将流转换为其他形式。接收一个collector接口的实现，用于给stream中元素汇总的方法
         */
        List<String> collect = emp.stream().map(Employee::getName).collect(Collectors.toList());
        collect.forEach(System.out::println);
        System.out.println("----------------------------set去重------------------------------------------");
        Set<String> collect1 = emp.stream().map(Employee::getName).collect(Collectors.toSet());
        collect1.forEach(System.out::println);
        System.out.println("----------------------------hashSet------------------------------------------");
        HashSet<String> collect2 = emp.stream().map(Employee::getName).collect(Collectors.toCollection(HashSet::new));
        collect2.forEach(System.out::println);
        System.out.println("----------------------------总数：------------------------------------------");
        Long collect3 = emp.stream().collect(Collectors.counting());
        System.out.println(collect3);
        System.out.println("----------------------------平均值：------------------------------------------");
        Double collect4 = emp.stream().collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println(collect4);
        System.out.println("----------------------------总和：------------------------------------------");
        Double sum = emp.stream().collect(Collectors.summingDouble(Employee::getSalary));
        System.out.println(sum);
        System.out.println("----------------------------最大值：------------------------------------------");
        Optional<Employee> collect5 = emp.stream().collect(Collectors.maxBy((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary())));
        System.out.println(collect5.get());
        System.out.println("----------------------------最小值：------------------------------------------");
        Optional<Employee> collect6 = emp.stream().collect(Collectors.maxBy((e1, e2) -> -Double.compare(e1.getSalary(), e2.getSalary())));
        System.out.println(collect6.get());
        Optional<Double> collect7 = emp.stream().map(Employee::getSalary).collect(Collectors.minBy(Double::compare));
        System.out.println(collect7.get());
    }

    @Test
    public void test4(){
        System.out.println("----------------------------分组：------------------------------------------");

    }

}
