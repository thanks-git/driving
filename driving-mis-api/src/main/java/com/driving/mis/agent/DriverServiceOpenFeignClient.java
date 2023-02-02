package com.driving.mis.agent;

import com.driving.common.util.R;
import com.driving.mis.controller.form.DriverPagingForm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author YueLiMin
 * @version 1.0.0
 */
@FeignClient(value = "driving-driver")
public interface DriverServiceOpenFeignClient {
    @PutMapping("/driver/updateDriverRealAuth/{driverId}/{realAuth}")
    R updateDriverRealAuth(@PathVariable("driverId") Long driverId, @PathVariable("realAuth") Integer realAuth);

    @PostMapping("/driver/searchDriverByPage")
    R searchDriverByPage(@RequestBody DriverPagingForm form);

    @GetMapping("/driver/searchDriverRealSummary/{driverId}")
    R searchDriverRealSummary(@PathVariable("driverId") Long driverId);
}
