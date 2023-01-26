package com.driving.customer.mapper;

import com.driving.customer.entity.Customer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author YueLiMin
 * @version 1.0.0
 */
@Mapper
public interface CustomerMapper extends BaseMapper<Customer> {
}
