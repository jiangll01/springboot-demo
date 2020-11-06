package com.dream.order.manage.instance.service;

import com.dream.order.manage.instance.mapper.InstanceMapper;
import com.dream.order.manage.timezone.bean.TimeBean;
import org.apache.tomcat.InstanceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jiangll01
 * @Date: 2020/11/4 9:46
 * @Description:
 */
@Service
public class InstanceService {

    private final InstanceMapper instanceMapper;

    @Autowired
    public InstanceService(InstanceMapper instanceMapper) {
        this.instanceMapper = instanceMapper;
    }

    public List<TimeBean> selectAll() {
        return instanceMapper.selectAll();
    }
}
