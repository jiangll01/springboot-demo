package com.dream.order.manage.thread.demo2;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author jiangll01
 * @Date: 2020/6/16 14:51
 * @Description:
 */
public class MythreadTest {
    public static void main(String[] args) {
        ThreadFactory factory = new ThreadFactoryBuilder().setNameFormat("site-pool-sync-%d").build();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 5, 200, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(1), factory);
        for (int i = 0; i < 10; i++) {
            Mythread mythread = new Mythread();
            executor.execute(mythread);
        }
        executor.shutdown();
    }
}
