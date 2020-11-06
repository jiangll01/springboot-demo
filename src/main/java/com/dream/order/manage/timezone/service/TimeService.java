package com.dream.order.manage.timezone.service;

import com.dream.order.manage.timezone.bean.TimeBean;
import com.dream.order.manage.timezone.bean.TimeVo;
import com.dream.order.manage.timezone.mapper.TimeMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jiangll01
 * @Date: 2020/8/19 16:34
 * @Description:
 */
@Service
@Slf4j


public class TimeService {
    @Autowired
    private TimeMapper timeMapper;

    public void insertTime(TimeBean timeBean) {
        int insertTime = timeMapper.insertTime(timeBean);
    }

    /**
     * @param id
     * @return
     */
    public TimeBean getTimeBean(String id) {
        return timeMapper.getTime(id);
    }

    public void update(String id, String timeName) {
        timeMapper.update(id, timeName);
    }

    public void delete(String id) {
        timeMapper.delete(id);
    }

    public List<TimeBean> selectAll() {
        return timeMapper.selectAll();
    }

    public List<TimeVo> selectAll1() {
        return timeMapper.selectAllList();
    }

    @SneakyThrows
    @Async
    public void sayHello() {
        Thread.sleep(6000);
        System.out.println("开启了异步");
    }


}
