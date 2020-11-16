package com.dream.order.manage.miaosha.mapper;

import com.dream.order.manage.miaosha.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author jiangll01
 * @Date: 2020/11/11 17:46
 * @Description:
 */
@Repository
@Mapper
public interface UserMapper {
    User findById(Integer userId);
}
