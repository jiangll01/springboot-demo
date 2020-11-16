package com.dream.order.manage.instance.service;

import com.dream.order.common.redis.lock.DelayQueueManager;
import com.dream.order.common.redis.lock.Message;
import com.dream.order.manage.instance.mapper.InstanceMapper;
import com.dream.order.manage.timezone.bean.TimeBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.InstanceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.rmi.runtime.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author jiangll01
 * @Date: 2020/11/4 9:46
 * @Description:
 */
@Service
@Slf4j
public class InstanceService {

    private final InstanceMapper instanceMapper;
    private final DelayQueueManager delayQueueManager;

    @Autowired
    public InstanceService(InstanceMapper instanceMapper, DelayQueueManager delayQueueManager) {
        this.instanceMapper = instanceMapper;
        this.delayQueueManager = delayQueueManager;
    }

    public List<TimeBean> selectAll(int id) {
        Message message = new Message(id, "提交延迟队列中", 10, TimeUnit.SECONDS);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(format.format(new Date()));
        delayQueueManager.put(message);
        return instanceMapper.selectAll();
    }
}
