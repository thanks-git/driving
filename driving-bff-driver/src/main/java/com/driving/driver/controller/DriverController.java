package com.driving.driver.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.map.MapUtil;
import com.driving.common.util.R;
import com.driving.driver.controller.form.*;
import com.driving.driver.service.IDriverService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;

/**
 * @author YueLiMin
 * @version 1.0.0
 */
@RestController
@RequestMapping("/driver")
@Tag(name = "DriverController", description = "司机bff模块-司机web接口")
public class DriverController {
    @Resource
    private IDriverService driverService;

    @PostMapping("/registerNewDriver")
    @Operation(summary = "司机bff端司机注册")
    public R registerNewDriver(@RequestBody @Valid RegisterNewDriverForm registerNewDriverForm) {
        String userId = driverService.registerNewDriver(registerNewDriverForm);
        // sa-token登录
        StpUtil.login(userId);
        // sa-token获取token value
        String tokenValue = StpUtil.getTokenInfo().getTokenValue();

        return R.ok().put("token", tokenValue);
    }

    @SaCheckLogin
    @PutMapping("/updateDriverAuth")
    @Operation(summary = "更新司机认证信息")
    public R updateDriverAuth(@RequestBody @Valid UpdateDriverAuthForm updateDriverAuthForm) {
        Long driverId = StpUtil.getLoginIdAsLong();
        updateDriverAuthForm.setDriverId(driverId);

        driverService.updateDriverAuth(updateDriverAuthForm);

        return R.ok();
    }

    @SaCheckLogin
    @PostMapping("/createDriverFaceModel")
    @Operation(summary = "创建司机人脸模型")
    public R createDriverFaceModel(@RequestBody @Valid CreateDriverFaceModelForm createDriverFaceModelForm) {
        createDriverFaceModelForm.setDriverId(StpUtil.getLoginIdAsLong());
        return R.ok().put("result", driverService.createDriverFaceModel(createDriverFaceModelForm));
    }

    @SaCheckLogin
    @PostMapping("/driverFaceAuth")
    @Operation(summary = "司机人脸认证")
    public R driverFaceAuth(@RequestBody @Valid CreateDriverFaceModelForm createDriverFaceModelForm) {
        createDriverFaceModelForm.setDriverId(StpUtil.getLoginIdAsLong());
        return R.ok().put("result", driverService.driverFaceAuth(createDriverFaceModelForm));
    }

    @PostMapping("/login")
    @Operation(summary = "司机登录")
    public R driverLogin(@RequestBody @Valid DriverLoginForm driverLoginForm) {
        HashMap<String, Object> map = driverService.driverLogin(driverLoginForm);

        StpUtil.login(MapUtil.getStr(map, "driverId"));
        String tokenValue = StpUtil.getTokenInfo().getTokenValue();

        map.put("token", tokenValue);

        return R.ok().put("result", map);
    }

    @SaCheckLogin
    @DeleteMapping("/logout")
    @Operation(summary = "司机退出登录")
    public R driverLogout() {
        StpUtil.logout();

        return R.ok();
    }

    @SaCheckLogin
    @GetMapping("/searchDriverBaseInfo")
    @Operation(summary = "查询司机基本信息")
    public R searchDriverBaseInfo() {
        return R.ok().put("result", driverService.searchDriverBaseInfo(StpUtil.getLoginIdAsLong()));
    }

    @SaCheckLogin
    @GetMapping("/searchWorkbenchData")
    @Operation(summary = "查询工作台数据")
    public R searchWorkbenchData() {
        return R.ok().put("result", driverService.searchWorkbenchData(StpUtil.getLoginIdAsLong()));
    }

    @SaCheckLogin
    @PutMapping("/updateDriverSettings")
    @Operation(summary = "修改司机配置信息")
    public R updateDriverSettings(@RequestBody UpdateDriverSettingsForm form) {
        driverService.updateDriverSettings(StpUtil.getLoginIdAsLong(), form);

        return R.ok();
    }

    @SaCheckLogin
    @GetMapping("/searchDriverAuthInformation")
    @Operation(summary = "查询司机认证信息")
    public R searchDriverAuthInformation() {
        return R.ok().put("result", driverService.searchDriverAuthInformation(StpUtil.getLoginIdAsLong()));
    }
}
