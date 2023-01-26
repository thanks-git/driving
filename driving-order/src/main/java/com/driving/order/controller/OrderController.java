package com.driving.order.controller;

import com.driving.order.entity.Order;
import com.driving.order.mapper.OrderMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author YueLiMin
 * @version 1.0.0
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Resource
    private OrderMapper orderMapper;

    @GetMapping
    public List<Order> get() {
        return orderMapper.selectList(null);
    }
}
