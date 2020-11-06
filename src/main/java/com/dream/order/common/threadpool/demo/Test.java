package com.dream.order.common.threadpool.demo;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;

import static java.util.concurrent.Executors.newFixedThreadPool;

/**
 * @author jiangll01
 * @Date: 2020/10/22 9:41
 * @Description:
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        ThreadFactoryBuilder factoryBuilder = new ThreadFactoryBuilder();
        ThreadFactory threadFactory = factoryBuilder.setNameFormat("site-pool-sync-%d").build();
        ExecutorService newFixedThreadPool = newFixedThreadPool(5, threadFactory);
        for (int i = 0; i < 10; i++) {
            Server server = new Server();
            newFixedThreadPool.execute(server);
        }
        Thread.sleep(4000);

        System.out.println("...............");
        for (int i = 0; i < 10; i++) {
            Server server = new Server();
            newFixedThreadPool.execute(server);
        }

        System.out.println("执行到主线程了");
        newFixedThreadPool.shutdown();
    }
}
