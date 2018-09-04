package com.testmaven.Optional;

import com.testmaven.Trade.Employee;
import org.junit.Test;

import javax.swing.text.html.Option;
import java.util.Optional;

public class TestOptional {

    /**
     *
     *  OPtional  容器类的常用方法：
     *  OPtional.of(T t):创建一个OPtional实例
     *  OPtional.empty()  创建一个空的OPtional实例
     *  OPtional.ofNullable(T t):若t 不为null，创建OPtional实例，否则创建空实例
     *   ispRESENT():判断是否包含值
     *   orElse(T t):如果调用对象包含值，返回该值，否则返回t
     *   orElseGet(Supplier s):如果调用对象包含值，返回该值，否则返回s获取的值
     *   map(Function f):如果有值对其处理，并返回处理后的OPtional，否则返回OPtional.empty()
     *   flatMap(Function mapper)：与map类似，要求返回值必须是OPtional
     *
     *
     */

     @Test
     public void test(){
         Optional<Employee> emp= Optional.of(new Employee());
         Employee employee= emp.get();
         System.out.println(employee);
     }

    @Test
    public void test1(){
        Optional<Employee> emp= Optional.of(new Employee());
        Employee employee= emp.get();
        System.out.println(employee);
    }

    @Test
    public void test2(){
        Optional<Employee> emp = Optional.empty();
        System.out.println(emp.get());
    }

    @Test
    public void test3(){
        Optional<Employee> employee = Optional.ofNullable(null);
        if(employee.isPresent()){
            System.out.println(employee.get());
        }
        System.out.println("mei");
    }

    @Test
    public void test4(){
        Optional<Employee> employee = Optional.ofNullable(null);
        if(employee.isPresent()){
            System.out.println(employee.get());
        }
        Employee zhangwan = employee.orElse(new Employee(1, "zhangwan", 19, 888.88, Employee.Status.FREE));
        System.out.println(zhangwan.getAge());
    }

    @Test
    public void test5(){
        Optional<Employee> employee = Optional.ofNullable(null);
        Employee employee1 = employee.orElseGet(() -> new Employee());
        System.out.println(employee1.getAge());
    }

    @Test
    public void test6(){
        Optional<Employee> zhangwan = Optional.ofNullable(new Employee(1, "zhangwan", 19, 888.88, Employee.Status.FREE));
        Optional<Integer> integer = zhangwan.map((e) -> e.getAge());

        System.out.println(integer.get());
    }

    @Test
    public void test7(){
        Optional<Employee> zhangwan = Optional.ofNullable(new Employee(1, "zhangwan", 19, 888.88, Employee.Status.FREE));
        Optional<Integer> integer = zhangwan.flatMap((e) -> Optional.of(e.getAge()));
        System.out.println(integer.get());
    }

    //例题   需求：获取一个男人心中的女神的名字
    @Test
    public void test8(){
         Man man = new Man();
        String godnessName = getGodnessName(man);
        System.out.println(godnessName);
    }

    /*public String getGodnessName(Man man){
           return man.getGodness().getName();
    }*/
    public String getGodnessName(Man man){
        if(man!=null){
                Godness gn = man.getGodness();
                if(gn!=null){
                    return gn.getName();
                }
        }
        return "gooooooooo";
    }

    public String getGodnessName1(Man man){
        if(man!=null){
            Godness gn = man.getGodness();
            if(gn!=null){
                return gn.getName();
            }
        }
        return "gooooooooo";
    }

    public String getGodnessName2(Optional<NewMan> newMan){
         return newMan.orElse(new NewMan()).getGodness().orElse(new Godness("hhahahha")).getName();
    }

    @Test
    public void test9(){
        Optional<NewMan> o = Optional.ofNullable(null);
        String godnessName2 = getGodnessName2(o);
        System.out.println(godnessName2);
    }




}
