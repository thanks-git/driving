package com.driving.order.service.impl;

import cn.hutool.core.map.MapUtil;
import com.driving.order.mapper.OrderMapper;
import com.driving.order.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @author YueLiMin
 * @version 1.0.0
 */
@Slf4j
@Service
public class OrderServiceImpl implements IOrderService {
    @Resource
    private OrderMapper orderMapper;

    @Override
    public HashMap<String, Object> searchDriverTodayBusinessData(Long driverId) {
        HashMap<String, Object> hashMap = orderMapper.searchDriverTodayBusinessData(driverId);
        String duration = MapUtil.getStr(hashMap, "duration");
        if (duration == null) {
            duration = "0";
        }
        hashMap.replace("duration", duration);

        String income = MapUtil.getStr(hashMap, "income");
        if (income == null) {
            income = "0.00";
        }
        hashMap.replace("income", income);

        return hashMap;
    }
}
