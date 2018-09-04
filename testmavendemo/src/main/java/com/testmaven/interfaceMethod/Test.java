package com.testmaven.interfaceMethod;

public class Test {

       public static void main(String[] args) {
           SubClass subClass = new SubClass();
           System.out.println(subClass.getName());  //类优先原则3

           SubClassTwo subClassTwo =new SubClassTwo();
           System.out.println(subClassTwo.getName());
       }
}
