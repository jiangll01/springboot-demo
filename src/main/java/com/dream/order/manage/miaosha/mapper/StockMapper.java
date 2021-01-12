package com.dream.order.manage.miaosha.mapper;

import com.dream.order.manage.miaosha.entity.Stock;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author jiangll01
 * @Date: 2020/11/10 10:18
 * @Description:
 */
@Mapper
@Repository
public interface StockMapper {
    Stock checkStock(@Param("id") Integer id);

    int updateSale(Stock stock);

}
