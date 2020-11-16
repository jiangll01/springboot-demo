package com.dream.order.manage.instance.controller;

import com.dream.order.manage.instance.service.InstanceService;
import com.dream.order.manage.timezone.bean.TimeBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author jiangll01
 * @Date: 2020/11/4 9:45
 * @Description:
 */
@RestController
@RequestMapping("/ddos")
public class InstanceController {
    private final InstanceService instanceService;

    @Autowired
    public InstanceController(InstanceService instanceService) {
        this.instanceService = instanceService;
    }

    @GetMapping("/instance/list")
    public List<TimeBean> selectAll(int id) {
        return instanceService.selectAll(id);
    }

}
