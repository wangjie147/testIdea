package com.testmaven.interfaceMethod;

public interface MyFun {

           //jdk8中允许接口中有默认方法也可以有静态方法
          default String getName(){return "hehehhe";}

          public static void show(){
              System.out.println("接口中的静态方法");
          }
}
