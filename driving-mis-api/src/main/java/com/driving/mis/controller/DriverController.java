package com.driving.mis.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaMode;
import com.driving.common.util.R;
import com.driving.mis.controller.form.DriverPagingForm;
import com.driving.mis.service.IDriverService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author YueLiMin
 * @version 1.0.0
 */
@RestController
@RequestMapping("/driver")
@Tag(name = "DriverController", description = "司机管理Web接口")
public class DriverController {
    @Resource
    private IDriverService driverService;

    @SaCheckPermission(value = {"ROOT", "DRIVER:SELECT"}, mode = SaMode.OR)
    @PutMapping("/updateDriverRealAuth/{driverId}/{realAuth}")
    @Operation(summary = "更新司机认证状态")
    public R updateDriverRealAuth(@PathVariable("driverId") Long driverId, @PathVariable("realAuth") Integer realAuth) {
        driverService.updateDriverRealAuth(driverId, realAuth);

        return R.ok();
    }

    @SaCheckPermission(value = {"ROOT", "DRIVER:SELECT"}, mode = SaMode.OR)
    @GetMapping("/searchDriverComprehensiveData/{driverId}/{realAuth}")
    @Operation(summary = "查询司机认证信息摘要")
    public R searchDriverComprehensiveData(@PathVariable("driverId") Long driverId, @PathVariable("realAuth") Integer realAuth) {
        return R.ok().put("result", driverService.searchDriverComprehensiveData(driverId, realAuth));
    }

    @SaCheckPermission(value = {"ROOT", "DRIVER:SELECT"}, mode = SaMode.OR)
    @PostMapping("/searchDriverByPage")
    @Operation(summary = "司机分页查询")
    public R searchDriverByPage(@RequestBody @Valid DriverPagingForm form) {
        return R.ok().put("result", driverService.searchDriverByPage(form));
    }
}
