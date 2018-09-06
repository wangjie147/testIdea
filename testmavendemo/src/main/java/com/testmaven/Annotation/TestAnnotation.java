package com.testmaven.Annotation;


import org.junit.Test;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Method;

public class TestAnnotation {

       @MyAnnotation("Hello")
       @MyAnnotation("World")
       public void show(@MyAnnotation("abc") String str){

       }

       @Test
       public void test1() throws NoSuchMethodException {
           Class<TestAnnotation>  clazz=TestAnnotation.class;
           Method show = clazz.getMethod("show");
           MyAnnotation[] annotationsByType = show.getAnnotationsByType(MyAnnotation.class);
           for(MyAnnotation list:annotationsByType){
               System.out.println(list.value());
           }
       }

        /**
         *
         *    checker framework
         *
         */
}


