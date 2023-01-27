package com.driving.driver.feign;

import com.driving.common.util.R;
import com.driving.driver.controller.form.RegisterNewDriverForm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author YueLiMin
 * @version 1.0.0
 */
@FeignClient(value = "driving-driver")
public interface IDriverOpenFeignService {
    @PostMapping("/driver/registerNewDriver")
    R registerNewDriver(@RequestBody RegisterNewDriverForm registerNewDriverForm);
}
