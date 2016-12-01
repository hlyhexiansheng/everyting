package quartz;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by noodles on 16/3/9.
 */
//public class NotifyJob {
//
//    public void runTask(){
//
//        String threadName = Thread.currentThread().getName();
//
//        System.out.println("定制执行任务--start" + threadName);
//        try {
//            Thread.currentThread().sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("定制执行任务--  end" + threadName);
//    }
//
//}

//package com.uxin.open.platform.quartz;

        import java.util.concurrent.CountDownLatch;
        import java.util.concurrent.ExecutorService;
        import java.util.concurrent.Executors;

/**
 * Created by noodles on 16/3/9.
 */
public class NotifyJob {

    public static final int ThreadCount = 10;

    public ExecutorService executorService = Executors.newFixedThreadPool(ThreadCount);

    private UpdateService updateService = new UpdateService();

    public void executeScheduleTask(){

        String threadName = Thread.currentThread().getName();

        try {
            System.out.println("定时任务执行开始....获得全局锁.." + threadName);
            CountDownLatch countDownLatch = new CountDownLatch(ThreadCount);

            for(int i = 0; i < 100; i+=10){
                executorService.submit(new NotifyCommand(countDownLatch,i,i+9,updateService));
            }

            countDownLatch.await();
            System.out.println("定时任务执行完毕....释放全局锁.." + threadName);
        }catch (Exception e){
            System.out.println("执行出现了异常...中断本次定时任务,并释放锁.....");
        }finally {

        }
    }
}

class NotifyCommand implements Runnable{

    private CountDownLatch countDownLatch;

    private int startTableId;

    private int endTableId;

    private UpdateService updateService;

    public NotifyCommand(CountDownLatch countDownLatch, int startTableId, int endTableId, UpdateService updateService) {
        this.countDownLatch = countDownLatch;
        this.startTableId = startTableId;
        this.endTableId = endTableId;
        this.updateService = updateService;
    }

    @Override
    public void run() {
        try {
            this.updateService.updateData(startTableId,endTableId);
        }catch (Exception e){
            System.out.println("command 捕获到异常....");
        }
        this.countDownLatch.countDown();
    }
}

class UpdateService{

    public void updateData(int startTableId,int endTableId){
        try {
            System.out.println(String.format("执行任务中,%s-%s",startTableId,endTableId));
            Thread.sleep((long) Math.random() * 1000000);
            System.out.println(String.format("执行任务完成,%s-%s",startTableId,endTableId));
            if(startTableId > 50){
                throw new RuntimeException("...");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

