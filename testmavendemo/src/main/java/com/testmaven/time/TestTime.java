package com.testmaven.time;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

public class TestTime {

        /**
         *
         * 线程不安全
         * @param args
         * @throws ExecutionException
         * @throws InterruptedException
         */

        /*public static void main(String[] args) throws ExecutionException, InterruptedException {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Callable<Date> call =new Callable<Date>() {
                @Override
                public Date call() throws Exception {
                    return sdf.parse("20180201");
                }
            };
               //创建一个线程池
            ExecutorService pool  = Executors.newFixedThreadPool(10);//长度为10的线程池

            List<Future<Date>> results = new ArrayList<>();

            for(int i =0;i<10;i++){
                 results.add(pool.submit(call));
            }

            for(Future<Date> future:results){
                System.out.println(future.get());
            }
        }*/


        /**
         * 以前的处理方式:线程安全
         *
         */
       /* public static void main(String[] args) throws ExecutionException, InterruptedException {
            Callable<Date> call =new Callable<Date>() {
                @Override
                public Date call() throws Exception {
                    return DateFormatThreadLocal.convert("20180201");
                }
            };
            //创建一个线程池
            ExecutorService pool  = Executors.newFixedThreadPool(10);//长度为10的线程池

            List<Future<Date>> results = new ArrayList<>();

            for(int i =0;i<10;i++){
                results.add(pool.submit(call));
            }

            for(Future<Date> future:results){
                System.out.println(future.get());
            }
            pool.shutdown();
        }*/
        //线程安全的
        public static void main(String[] args) throws ExecutionException, InterruptedException {
            //DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE;
            DateTimeFormatter dtf= DateTimeFormatter.ofPattern("yyyyMMdd");  //自己制定格式
            Callable<LocalDate> task =new Callable<LocalDate>() {
                @Override
                public LocalDate call() throws Exception {
                    return  LocalDate.parse("20180201",dtf);
                }
            };
            //创建一个线程池
            ExecutorService pool  = Executors.newFixedThreadPool(10);//长度为10的线程池

            List<Future<LocalDate>> results = new ArrayList<>();

            for(int i =0;i<10;i++){
                results.add(pool.submit(task));
            }

            for(Future<LocalDate> future:results){
                System.out.println(future.get());
            }
            pool.shutdown();
        }
}
