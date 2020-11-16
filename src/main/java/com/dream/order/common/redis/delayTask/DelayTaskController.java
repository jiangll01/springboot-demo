package com.dream.order.common.redis.delayTask;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static java.time.LocalDate.now;

/**
 * @author jiangll01
 * @Date: 2020/10/21 21:13
 * @Description:
 */
@Slf4j
@RestController
@RequestMapping("/delay")
public class DelayTaskController {
    @Autowired
    DelayTaskProducer delayTaskProducer;

    @GetMapping("/test")
    public void test(String name,int time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
        log.info("当前的时间{}",format.format(new Date()));
        long time1 = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(time);
        log.info("将在未来的时间 {} 执行",format.format(new Date(time1)));
        delayTaskProducer.putTask(name,time1);



    }

    public static void main(String[] args) throws ParseException {
        System.out.println(new Date());
        System.out.println(System.currentTimeMillis());
        System.out.println(new Date().getTime());
        long l = System.currentTimeMillis()+TimeUnit.SECONDS.toMillis(5);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        System.out.println(simpleDateFormat.parse(format));
    }
}
