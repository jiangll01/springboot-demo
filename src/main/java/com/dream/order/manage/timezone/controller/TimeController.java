package com.dream.order.manage.timezone.controller;

import com.dream.order.manage.timezone.bean.TimeBean;
import com.dream.order.manage.timezone.bean.TimeVo;
import com.dream.order.manage.timezone.service.TimeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author jiangll01
 * @Date: 2020/8/19 16:24
 * @Description: 验证 前后端进行日期传输时，通过注解进行转换
 */
@RestController
@Slf4j

@RequestMapping("/demo")
public class TimeController {


    private final TimeService timeService;

    @Autowired
    public TimeController(TimeService timeService) {
        this.timeService = timeService;
    }

    //开启事务，如果出现异常进行回滚,控制整个流程，如果service层等出现异常也是要进行回滚的
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/times")
    public void insertTime(@RequestBody TimeBean timeBean) {
        timeService.insertTime(timeBean);
    }

    @GetMapping("/time/{id}")
    public TimeBean getTimeBean(@PathVariable String id) {
        return timeService.getTimeBean(id);
    }

    @PatchMapping("/time/update/{id}")
    public void update(@PathVariable String id, String timeName) {
        timeService.update(id, timeName);
    }

    @DeleteMapping("/time/delete/{id}")
    public void delete(@PathVariable String id) {
        timeService.delete(id);
    }

    @GetMapping("/time/list")
    public List<TimeBean> selectAll() {
        return timeService.selectAll();
    }

    @GetMapping("/time/list1")
    public List<TimeVo> selectAll1() {
        return timeService.selectAll1();
    }

    @GetMapping("/test5")
    public String test5() {
        timeService.sayHello();
        return "成功的异步了";
    }
}
