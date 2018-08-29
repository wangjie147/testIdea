package com.testmaven.jdk8;

import org.junit.jupiter.api.Test;

import java.sql.SQLOutput;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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
        Map<Employee.Status, List<Employee>> collect = emp.stream().collect(Collectors.groupingBy(Employee::getStatus));
        System.out.println(collect);
        System.out.println("----------------------------多级分组：------------------------------------------");
        Map<Employee.Status, Map<String, List<Employee>>> collect1 = emp.stream().collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy((e) -> {
            if (((Employee) e).getAge() <= 35) {
                return "青年";
            } else if (((Employee) e).getAge() <= 50) {
                return "中年";
            } else {
                return "老年";
            }
        })));
        System.out.println(collect1);
    }
    //分片
    @Test
    public void test5(){
        System.out.println("---------------------------- 分片------------------------------------------");
        Map<Boolean, List<Employee>> collect = emp.stream().collect(Collectors.partitioningBy((e) -> e.getSalary() > 8000));
        System.out.println(collect);
    }
    //
    @Test
    public void test6(){
        System.out.println("---------------------------- 统计------------------------------------------");
        DoubleSummaryStatistics collect = emp.stream().collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println(collect.getAverage());
        System.out.println(collect.getCount());
        System.out.println(collect.getMax());
        System.out.println(collect.getMin());
        System.out.println(collect.getSum());
    }

    @Test
    public void test7(){
        System.out.println("----------------------------------------------------------------------");
        String collect = emp.stream().map(Employee::getName).collect(Collectors.joining(","));
        System.out.println(collect);
    }


    public static void main(String[] args) {
        //jdk8新特性
        LocalDateTime now = LocalDateTime.now();
        now = now.minus(30, ChronoUnit.DAYS);
        System.out.println(now.toString());
    }
    //Java 获取两个日期之间的所有日期
    @Test
    public void test8(){
        for(int i=0;i>-120;i--){
            SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd");
            String maxDateStr = "2018-08-27";
            String minDateStr = "";
            String calcSmallDateStr = "";
            Calendar calc =Calendar.getInstance();
            Calendar calcSmall =Calendar.getInstance();
            try {
                calc.setTime(sdf.parse(maxDateStr));
                calcSmall.setTime(sdf.parse(maxDateStr));
                calc.add(calc.DATE, i);
                calcSmall.add(calc.DATE,i+1);
                Date minDate = calc.getTime();
                Date calcSmallDate = calcSmall.getTime();
                minDateStr = sdf.format(minDate);
                calcSmallDateStr= sdf.format(calcSmallDate);
                System.out.println("minDateStr:"+minDateStr+" 09:00:00"+"------calcSmallDateStr:"+calcSmallDateStr+" 09:00:00");
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
        }
    }





    /**
     * 获取两个日期之间的所有日期
     *
     * @param startTime
     *            开始日期
     * @param endTime
     *            结束日期
     * @return
     */
    public static List<String> getDays(String startTime, String endTime) {

        // 返回的日期集合
        List<String> days = new ArrayList<String>();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date start = dateFormat.parse(startTime);
            Date end = dateFormat.parse(endTime);

            Calendar tempStart = Calendar.getInstance();
            tempStart.setTime(start);

            Calendar tempEnd = Calendar.getInstance();
            tempEnd.setTime(end);
            tempEnd.add(Calendar.DATE, +1);// 日期加1(包含结束)
            while (tempStart.before(tempEnd)) {
                days.add(dateFormat.format(tempStart.getTime()));
                tempStart.add(Calendar.DAY_OF_YEAR, 1);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return days;
    }

    @Test
    public void test20(){
        System.out.println(getDays("2018-05-01", "2018-08-29"));
    }

    @Test
    public void test9() {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.DAY_OF_MONTH, -1);
        String endDate = new SimpleDateFormat("yyyy-MM-dd").format(now.getTime());
        System.out.println(endDate+" 09:00:00");
    }

    /**
     *  给定一个数字列表，如何返回一个由每个数的平方构成的列表呢
     *  给定（1,2，3,4,5），返回应该为（1,4,9,16,25）
     *
     */
    @Test
    public void test12() {
          Integer[] nums = new Integer[]{1,2,3,4,5};
          Arrays.stream(nums).map((x)->x*x).forEach(System.out::println);
          System.out.println("----------------------------------------------------------------------");
    }
    /**
     *  怎么样用map和reduce方法数一数流中有多少个Employee呢
     *
     */

    List<Employee> emps = Arrays.asList(
            new Employee("zhangsan",1111,234234.00,Employee.Status.FREE),
            new Employee("lisi",311,4234.00,Employee.Status.BUSY),
            new Employee("zhouwu",111,2344.00,Employee.Status.FREE),
            new Employee("zhengwang",13,4234.00,Employee.Status.VOCATION),
            new Employee("zhouwu",111,2344.00,Employee.Status.FREE),
            new Employee("zhengwang",13,4234.00,Employee.Status.BUSY)
    );

    @Test
    public void test22(){
        Optional<Integer> reduce = emps.stream().map((e) -> 1).reduce(Integer::sum);
        System.out.println(reduce.get());
    }


}
