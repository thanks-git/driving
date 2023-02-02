package com.driving.driver.agent;

import com.driving.common.util.R;
import com.driving.driver.controller.form.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author YueLiMin
 * @version 1.0.0
 */
@FeignClient(value = "driving-driver")
public interface DriverServiceOpenFeignClient {
    @PostMapping("/driver/registerNewDriver")
    R registerNewDriver(@RequestBody RegisterNewDriverForm registerNewDriverForm);

    @PutMapping("/driver/updateDriverAuth")
    R updateDriverAuth(@RequestBody UpdateDriverAuthForm updateDriverAuthForm);

    @PostMapping("/driver/createDriverFaceModel")
    R createDriverFaceModel(@RequestBody CreateDriverFaceModelForm createDriverFaceModelForm);

    @PostMapping("/driver/driverFaceAuth")
    R driverFaceAuth(@RequestBody CreateDriverFaceModelForm createDriverFaceModelForm);

    @PostMapping("/driver/login")
    R driverLogin(@RequestBody DriverLoginForm driverLoginForm);

    @GetMapping("/driver/searchDriverBaseInfo/{driverId}")
    R searchDriverBaseInfo(@PathVariable("driverId") Long driverId);

    @GetMapping("/driver-settings/searchDriverSettings/{driverId}")
    R searchDriverSettings(@PathVariable("driverId") Long driverId);

    @PutMapping("/driver-settings/updateDriverSettings/{driverId}")
    R updateDriverSettings(@PathVariable("driverId") Long driverId, @RequestBody UpdateDriverSettingsForm form);

    @GetMapping("/driver/searchDriverAuthInformation/{driverId}")
    R searchDriverAuthInformation(@PathVariable("driverId") Long driverId);
}
