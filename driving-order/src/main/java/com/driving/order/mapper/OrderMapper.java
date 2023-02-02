package com.driving.order.mapper;

import com.driving.order.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;

/**
 * @author YueLiMin
 * @version 1.0.0
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {
    /**
     * 查询进入司机业务信息
     *
     * @param driverId 司机id
     * @return HashMap
     */
    HashMap<String, Object> searchDriverTodayBusinessData(Long driverId);
}
