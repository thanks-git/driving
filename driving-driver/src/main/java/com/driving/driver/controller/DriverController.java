package com.driving.driver.controller;

import cn.hutool.core.bean.BeanUtil;
import com.driving.common.util.R;
import com.driving.driver.controller.form.*;
import com.driving.driver.service.IDriverService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @author YueLiMin
 * @version 1.0.0
 */
@RestController
@RequestMapping("/driver")
@Tag(name = "DriverController", description = "司机模块-司机web接口")
public class DriverController {
    @Resource
    private IDriverService driverService;

    @PutMapping("/updateDriverRealAuth/{driverId}/{realAuth}")
    @Operation(summary = "更新司机认证状态")
    public R updateDriverRealAuth(@PathVariable("driverId") Long driverId, @PathVariable("realAuth") Integer realAuth) {
        driverService.updateDriverRealAuth(driverId, realAuth);

        return R.ok();
    }

    @GetMapping("/searchDriverRealSummary/{driverId}")
    @Operation(summary = "查询司机认证摘要信息(MIS审核)")
    public R searchDriverRealSummary(@PathVariable("driverId") Long driverId) {
        return R.ok().put("result", driverService.searchDriverRealSummary(driverId));
    }

    @PostMapping("/registerNewDriver")
    @Operation(summary = "司机注册")
    public R registerNewDriver(@RequestBody @Valid RegisterNewDriverForm registerNewDriverForm) {
        Map<String, Object> param = BeanUtil.beanToMap(registerNewDriverForm);
        String driverId = driverService.registerNewDriver(param);

        return R.ok().put("userId", driverId);
    }

    @PutMapping("/updateDriverAuth")
    @Operation(summary = "更新司机认证信息")
    public R updateDriverAuth(@RequestBody @Valid UpdateDriverAuthForm form) {
        driverService.updateDriverAuth(form);

        return R.ok();
    }

    @PostMapping("/createDriverFaceModel")
    @Operation(summary = "创建司机人脸模型")
    public R createDriverFaceModel(@RequestBody @Valid CreateDriverFaceModelForm form) {
        String result = driverService.createDriverFaceModel(form.getDriverId(), form.getPhoto());
        return R.ok().put("result", result);
    }

    @PostMapping("/driverFaceAuth")
    @Operation(summary = "司机人脸识别")
    public R driverFaceAuth(@RequestBody @Valid CreateDriverFaceModelForm form) {
        Boolean auth = driverService.driverFaceAuth(form.getDriverId(), form.getPhoto());
        return R.ok().put("result", auth);
    }

    @PostMapping("/login")
    @Operation(summary = "司机登录")
    public R driverLogin(@RequestBody @Valid DriverLoginForm driverLoginForm) {
        HashMap<String, Object> map = driverService.driverLogin(driverLoginForm.getCode(), null);
        // HashMap<String, Object> map = driverService.driverLogin(driverLoginForm.getCode(), driverLoginForm.getPhoneCode());
        return R.ok().put("result", map);
    }

    @GetMapping("/searchDriverBaseInfo/{driverId}")
    @Operation(summary = "查询司机基本信息")
    public R searchDriverBaseInfo(@PathVariable("driverId") Long driverId) {
        return R.ok().put("result", driverService.searchDriverBaseInfo(driverId));
    }

    @GetMapping("/searchDriverAuthInformation/{driverId}")
    @Operation(summary = "查询司机认证信息")
    public R searchDriverAuthInformation(@PathVariable("driverId") Long driverId) {
        return R.ok().put("result", driverService.searchDriverAuthInformation(driverId));
    }

    @PostMapping("/searchDriverByPage")
    @Operation(summary = "查询司机分页记录")
    public R searchDriverByPage(@RequestBody @Valid DriverPagingForm form) {
        HashMap<String, Object> param = (HashMap<String, Object>) BeanUtil.beanToMap(form);
        int page = form.getPage();
        int length = form.getLength();
        int start = (page - 1) * length;
        param.put("start", start);

        return R.ok().put("result", driverService.searchDriverByPage(param));
    }
}
