package com.dream.order.common.threadpool.demo;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author jiangll01
 * @Date: 2020/11/3 9:50
 * @Description:
 */
public class Test1 {
    public static void main(String[] args) {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("测试线程%d").build();
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5, 10, 1L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(10), threadFactory);
        for (int i = 0; i < 10; i++) {
            Server server = new Server();
            poolExecutor.execute(server);
        }

    }
}
