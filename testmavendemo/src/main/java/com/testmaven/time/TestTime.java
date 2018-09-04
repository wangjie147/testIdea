package com.testmaven.time;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestTime {

        public static void main(String[] args) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Callable<Date> call =new Callable<Date>() {
                @Override
                public Date call() throws Exception {
                    return sdf.parse("20180201");
                }
            };
               //创建一个线程池
            ExecutorService pool  = Executors.newFixedThreadPool(10);//长度为10的线程池


        }
}
