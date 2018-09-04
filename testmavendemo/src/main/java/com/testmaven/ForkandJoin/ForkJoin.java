package com.testmaven.ForkandJoin;

import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

public class ForkJoin extends RecursiveTask<Long> {

        //使用fork join框架必须继承一个类 RecursiveAction(无返回值)或者RecursiveTask(有返回值)


    public ForkJoin(long start, long end) {
        this.start = start;
        this.end = end;
    }

    private long start;

        private long end;

        private static final long THRESHOLD =10000;

        @Override
        protected Long compute() {
            long length = end -start;
            if(length <=THRESHOLD ){
                long sum =0;
                for(long i=start;i<= end ;i++){
                     sum +=i;
                }
                return sum;
            }else{
                 long middle =(start+end)/2;
                 ForkJoin left = new ForkJoin(start,middle);
                 left.fork();   //拆分子任务，同时压入线程队列
                 ForkJoin right = new ForkJoin(middle+1,end);
                 right.fork();

                 return left.join()+right.join();
            }
        }


}
