package com.dream.order.common.threadpool.demo;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author jiangll01
 * @Date: 2020/11/3 16:17
 * @Description:
 */
@RestController
@RequestMapping("/thread")
public class ThreadController {

    private ThreadService threadService;

    @Autowired
    public ThreadController(ThreadService threadService) {
        this.threadService = threadService;
    }

    @SneakyThrows
    @GetMapping("/test")

    public String test() {
        // 阻塞调用
        String yan = threadService.getMessage("yan").get();
        System.out.println(yan);
        // 限时调用
        String yan1 = threadService.getMessage("yan").get(2, TimeUnit.SECONDS);
        System.out.println(yan1);
        return "成功";
    }
}
