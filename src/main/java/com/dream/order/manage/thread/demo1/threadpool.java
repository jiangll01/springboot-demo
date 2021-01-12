package com.dream.order.manage.thread.demo1;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author jiangll01
 * @Date: 2020/6/16 11:16
 * @Description: 开启线程，模拟tomcat多个请求进来后，
 * 遇到异步或者其他的进行线程池进行处理进来的并发请求
 */
public class threadpool {
    public static void main(String[] args) {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(1, 5, 200, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(10));
        ThreadPoolDemo getName = new ThreadPoolDemo(poolExecutor);
        for (int i = 0; i < 15; i++) {
            new Thread(() -> {
                Runnable thread = Thread.currentThread();
                getName.getName(thread);
            }).start();
        }
    }
}
