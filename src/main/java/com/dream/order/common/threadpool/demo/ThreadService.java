package com.dream.order.common.threadpool.demo;

import lombok.SneakyThrows;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * @author jiangll01
 * @Date: 2020/11/3 16:18
 * @Description:
 */
@Service
public class ThreadService {

    @SneakyThrows
    @Async
    public ListenableFuture<String> getMessage(String name) {
        Thread.sleep(5000);
        String res = name + ":Hello World!";
        return new AsyncResult<>(res);
    }
}
