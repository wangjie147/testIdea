package com.testmaven.ForkandJoin;


import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

public class TestForkJoin {

        /**
          *  Fork/Join 框架
         *  就是在必要的情况下，将一个大任务，进行拆分（fork）成若干个小任务（拆到不可再拆时），
         *  再将一个个的小任务运算的结果进行join汇总
         *
         *  工作窃取模式：
         *  当执行新的任务时它可以将其拆分分成更小的任务执行，并将小任务加到线程队列中，然后再从一个随机线程的队列中偷一个并把它放在自己的队列中。
         *
         *  相对于一般的线程池实现。fork/join框架的优势体现在对其中包含的任务的处理方式上，在一般的线程池中，如果一个线程正在执行的任务由于某些原因
         *
         *  无法继续运行，那么该线程会处于等待状态，而在fork、join框架实现中，如果某个子问题由于等待另外一个子问题的完成而无法继续运行
         *
         *  那么处理该子问题的线程会主动寻找其他尚未运行的子问题来执行。这种方式
         *
         *
          */
        @Test
        public void test(){
                Instant now = Instant.now();
                ForkJoinPool pool = new ForkJoinPool();
                ForkJoinTask<Long> task = new ForkJoin(0,100000000L);
                Long invoke = pool.invoke(task);
                System.out.println(invoke);
                Instant end = Instant.now();
                System.out.println("耗时："+Duration.between(now,end).getNano());
                System.out.println("耗时："+Duration.between(now,end).toMillis());
        }

        @Test
        public void test1(){
                Instant now = Instant.now();
                long sum =0L;
                for(long i=0;i<100000000L;i++){
                     sum+=i;
                }
                System.out.println(sum);
                Instant end = Instant.now();
                System.out.println("耗时："+Duration.between(now,end).toMillis());
        }

        /**
         *
         * 并行流，进行提升
         * jdk1.8
         */
        @Test
        public void test2(){
                //LongStream.range(0,100000000L).reduce(0,Long::sum);  //顺序流
                Instant now = Instant.now();
                LongStream.range(0,100000000L).parallel().reduce(0,Long::sum);
                Instant end = Instant.now();
                System.out.println("耗时："+Duration.between(now,end).toMillis());
        }


}
