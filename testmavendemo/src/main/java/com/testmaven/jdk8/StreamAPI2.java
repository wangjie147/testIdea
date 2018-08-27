package com.testmaven.jdk8;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Stream;

public class StreamAPI2 {

    List<Employee> emp = Arrays.asList(
            new Employee("zhangsan",1111,234234.00),
            new Employee("lisi",311,4234.00),
            new Employee("zhouwu",111,2344.00),
            new Employee("zhengwang",13,4234.00),
             new Employee("zhouwu",111,2344.00),
            new Employee("zhengwang",13,4234.00)
    );

    @Test
    public void test1(){
        /**
         *   帅选 与切片
         *   filter--- 接收lambda，从流中排除某些元素
         *   limit----截断流，使其元素不超过给定数量
         *   skip(n)--跳过元素，返回一个扔掉了前n个元素的流。若流中元素不足n个，
         *   则返回一个空流。与limit（n）互补
         *   distinct---帅选，通过流所生成元素的hashcode（）和equals（）去除重复的元素
         *
         *  内部迭代：迭代操作由stream api 完成
         *
         */
        //中间操作：不会执行任何操作
        Stream<Employee> employeeStream = emp.stream().filter((e) -> e.getAge() > 35);
        //终止操作：一次性执行全部内容，既“惰性0-求值”
        employeeStream.forEach(System.out::println);

    }
    @Test   //外部迭代
    public void test2(){
        Iterator<Employee> it = emp.iterator();

        while(it.hasNext()){
            System.out.println(it.next());
        }

    }

    @Test
    public void test3(){
          emp.stream().filter((e) ->e.getAge()>35).limit(2).forEach(System.out::println);
    }

    @Test
    public void test4(){
        emp.stream().filter((e) ->{
            System.out.println("短路");
            return e.getSalary()>2000;
        }).limit(2).forEach(System.out::println);
    }

    @Test   //跳过
    public void test5(){
          emp.stream().filter((e)->e.getSalary()>2000).skip(2).forEach(System.out::println);
    }

    @Test   //去重
    public void test6(){
        emp.stream().filter((e)->e.getSalary()>2000).skip(2).distinct().forEach(System.out::println);
    }

    /**
     *  映射
     *  map----接受lambda表达式，将元素转换成其他形式或提取信息，接收一个函数作为参数，该函数会被应用到每个元素上，
     *  并将其映射成一个新的元素，
     *
     *  flatMap  ----接受一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
     *
     *
     */
    @Test
    public void test7(){
        List<String> list = Arrays.asList("asdasda","asdafsdfsd","asdsaWEFAAW");
        list.stream().map((str) -> str.toUpperCase()).forEach(System.out::println);
        System.out.println("---------------map---------------------------------");
        emp.stream().map((w)->w.getName()).forEach(System.out::println);
        System.out.println("------------------------------------------------");
        Stream<Stream<Character>> streamStream = list.stream().map(StreamAPI2::filterCharacter);
        streamStream.forEach((sm)->{sm.forEach(System.out::println);});
        System.out.println("------------------flatMap------------------------------");
        list.stream().flatMap(StreamAPI2::filterCharacter).forEach(System.out::println);

    }

    public static Stream<Character> filterCharacter(String str) {
         List<Character> list = new ArrayList<>();
         for(Character ch : str.toCharArray()){
              list.add(ch);
         }
         return list.stream();
    }

    @Test
    public void test8(){
        List<String> list =Arrays.asList("aaa","sss","cccc","dddd","eeeee","rrrrr","aaaa","bbbb");
        List list1 = new ArrayList();
        list1.add(2222);
        list1.add(1111);
        //list1.add(list);
        list1.addAll(list);
        System.out.println(list1);
    }

    /**
     *  排序
     *
     *  sorted（） ---自然排序（comparable）
     *  sorted(Comparator com) ----定制排序
     *
     *
     */
    @Test
    public void test9(){
        List<String> list =Arrays.asList("aaa","sss","cccc","dddd","eeeee","rrrrr","aaaa","bbbb");
        list.stream().sorted().forEach(System.out::println);
        System.out.println("------------------定制排序------------------------------");

        //TODO  不知道为什么报错
        emp.stream().sorted((e1,e2)-> {
               if(e1.getAge().equals(e2.getAge())){
                   return e1.getName().compareTo(e2.getName());
               }else{
                   return e1.getAge().compareTo(e2.getAge());
               }
        });
        //先按年龄排，年龄一样按姓名排。

    }

}
