package com.driving.driver.controller;

import com.driving.common.util.R;
import com.driving.driver.controller.form.UpdateDriverSettingsForm;
import com.driving.driver.service.IDriverSettingsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author YueLiMin
 * @version 1.0.0
 */
@RestController
@RequestMapping("/driver-settings")
@Tag(name = "DriverSettingsController", description = "司机模块-司机设置web接口")
public class DriverSettingsController {
    @Resource
    private IDriverSettingsService driverSettingsService;

    @GetMapping("/searchDriverSettings/{driverId}")
    @Operation(summary = "查询司机配置信息")
    public R searchDriverSettings(@PathVariable("driverId") Long driverId) {
        return R.ok().put("result", driverSettingsService.searchDriverSettings(driverId));
    }

    @PutMapping("/updateDriverSettings/{driverId}")
    @Operation(summary = "修改司机配置信息")
    public R updateDriverSettings(@PathVariable("driverId") Long driverId, @RequestBody UpdateDriverSettingsForm form) {
        driverSettingsService.updateDriverSettings(driverId, form);
        return R.ok();
    }
}
