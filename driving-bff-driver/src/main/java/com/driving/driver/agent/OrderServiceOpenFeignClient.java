package com.driving.driver.agent;

import com.driving.common.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author YueLiMin
 * @version 1.0.0
 */
@FeignClient(value = "driving-order")
public interface OrderServiceOpenFeignClient {
    @GetMapping("/order/searchDriverTodayBusinessData/{driverId}")
    R searchDriverTodayBusinessData(@PathVariable("driverId") Long driverId);
}
