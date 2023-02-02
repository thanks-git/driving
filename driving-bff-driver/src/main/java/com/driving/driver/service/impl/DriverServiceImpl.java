package com.driving.driver.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.map.MapUtil;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.driving.common.util.R;
import com.driving.driver.agent.OrderServiceOpenFeignClient;
import com.driving.driver.controller.form.*;
import com.driving.driver.agent.DriverServiceOpenFeignClient;
import com.driving.driver.service.IDriverService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @author YueLiMin
 * @version 1.0.0
 */
@Slf4j
@Service
public class DriverServiceImpl implements IDriverService {
    @Resource
    private DriverServiceOpenFeignClient driverServiceOpenFeignClient;
    @Resource
    private OrderServiceOpenFeignClient orderServiceOpenFeignClient;

    @Override
    @LcnTransaction
    @Transactional(rollbackFor = {Exception.class})
    public String registerNewDriver(RegisterNewDriverForm registerNewDriverForm) {
        R r = driverServiceOpenFeignClient.registerNewDriver(registerNewDriverForm);
        return Convert.toStr(r.get("userId"));
    }

    @Override
    @LcnTransaction
    @Transactional(rollbackFor = {Exception.class})
    public void updateDriverAuth(UpdateDriverAuthForm updateDriverAuthForm) {
        driverServiceOpenFeignClient.updateDriverAuth(updateDriverAuthForm);
    }

    @Override
    @LcnTransaction
    @Transactional(rollbackFor = Exception.class)
    public String createDriverFaceModel(CreateDriverFaceModelForm createDriverFaceModelForm) {
        return MapUtil.getStr(driverServiceOpenFeignClient.createDriverFaceModel(createDriverFaceModelForm), "result");
    }

    @Override
    public Boolean driverFaceAuth(CreateDriverFaceModelForm createDriverFaceModelForm) {
        return MapUtil.getBool(driverServiceOpenFeignClient.driverFaceAuth(createDriverFaceModelForm), "result");
    }

    @Override
    public HashMap<String, Object> driverLogin(DriverLoginForm driverLoginForm) {

        return (HashMap<String, Object>) driverServiceOpenFeignClient.driverLogin(driverLoginForm).get("result");
    }

    @Override
    public HashMap<String, Object> searchDriverBaseInfo(Long driverId) {
        return (HashMap<String, Object>) driverServiceOpenFeignClient.searchDriverBaseInfo(driverId).get("result");
    }

    @Override
    public HashMap<String, Object> searchWorkbenchData(Long driverId) {
        HashMap<String, Object> orderTodayBusinessData = (HashMap<String, Object>) orderServiceOpenFeignClient.searchDriverTodayBusinessData(driverId).get("result");
        HashMap<String, Object> driverSettingsData = (HashMap<String, Object>) driverServiceOpenFeignClient.searchDriverSettings(driverId).get("result");

        HashMap<String, Object> map = new HashMap<>() {{
            put("business", orderTodayBusinessData);
            put("settings", driverSettingsData);
        }};
        return map;
    }

    @Override
    @LcnTransaction
    @Transactional(rollbackFor = Exception.class)
    public void updateDriverSettings(Long driverId, UpdateDriverSettingsForm form) {
        driverServiceOpenFeignClient.updateDriverSettings(driverId, form);
    }
}
