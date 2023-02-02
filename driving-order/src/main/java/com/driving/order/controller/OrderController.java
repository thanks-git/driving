package com.driving.order.controller;

import com.driving.common.util.R;
import com.driving.order.service.IOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author YueLiMin
 * @version 1.0.0
 */
@RestController
@RequestMapping("/order")
@Tag(name = "OrderController", description = "订单服务web接口")
public class OrderController {
    @Resource
    private IOrderService orderService;

    @GetMapping("/searchDriverTodayBusinessData/{driverId}")
    @Operation(summary = "查询司机当天营业数据")
    public R searchDriverTodayBusinessData(@PathVariable("driverId") Long driverId) {
        return R.ok().put("result", orderService.searchDriverTodayBusinessData(driverId));
    }
}
