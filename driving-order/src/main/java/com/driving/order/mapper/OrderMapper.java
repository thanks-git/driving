package com.driving.order.mapper;

import com.driving.order.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author YueLiMin
 * @version 1.0.0
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
