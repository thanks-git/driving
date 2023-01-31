package com.driving.driver.feign;

import com.driving.common.util.R;
import com.driving.driver.controller.form.CreateDriverFaceModelForm;
import com.driving.driver.controller.form.DriverLoginForm;
import com.driving.driver.controller.form.RegisterNewDriverForm;
import com.driving.driver.controller.form.UpdateDriverAuthForm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author YueLiMin
 * @version 1.0.0
 */
@FeignClient(value = "driving-driver")
public interface IDriverOpenFeignService {
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
}
