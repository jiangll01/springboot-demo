package com.dream.order.manage.thread.demo3;

import java.util.concurrent.*;

/**
 * @author jiangll01
 * @Date: 2020/6/16 17:21
 * @Description: CallerRunsPolicy的例子 线程main也执行了任务，这
 * 正说明了此拒绝策略由调用线程（提交任务的线程）直接执行被丢弃的任务的。
 */
public class Test {
    public static void main(String[] args) {
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(10);
        ThreadFactory factory = r -> new Thread(r, "test-thread-pool");
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 5,
                0L, TimeUnit.SECONDS, queue, factory, new ThreadPoolExecutor.CallerRunsPolicy());
        for (int i = 0; i < 1000; i++) {
            executor.submit(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + ":执行任务");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
