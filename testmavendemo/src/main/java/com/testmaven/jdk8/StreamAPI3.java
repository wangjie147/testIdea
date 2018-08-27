package com.testmaven.jdk8;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

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
            new Employee("zhangsan",1111,234234.00),
            new Employee("lisi",311,4234.00),
            new Employee("zhouwu",111,2344.00),
            new Employee("zhengwang",13,4234.00),
            new Employee("zhouwu",111,2344.00),
            new Employee("zhengwang",13,4234.00)
    );

    @Test
    public void test1(){

    }



}
