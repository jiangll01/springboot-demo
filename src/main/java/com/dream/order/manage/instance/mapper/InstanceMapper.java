package com.dream.order.manage.instance.mapper;

import com.dream.order.manage.timezone.bean.TimeBean;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author jiangll01
 * @Date: 2020/11/4 16:30
 * @Description:
 */
@Repository
@Mapper
public interface InstanceMapper {
    List<TimeBean> selectAll();
}
