package com.dream.order.manage.timezone.mapper;

import com.dream.order.manage.timezone.bean.TimeBean;
import com.dream.order.manage.timezone.bean.TimeVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author jiangll01
 * @Date: 2020/8/19 17:40
 * @Description:
 */
@Mapper
@Repository
public interface TimeMapper {
    public int insertTime(TimeBean timeBean);

    void update(@Param("id") String id, @Param("timeName") String timeName);

    void delete(String id);

    TimeBean getTime(String id);

    List<TimeBean> selectAll();

    List<TimeVo> selectAllList();
}
