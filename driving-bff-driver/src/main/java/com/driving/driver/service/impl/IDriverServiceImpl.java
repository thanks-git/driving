package com.driving.driver.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.map.MapUtil;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.driving.common.util.R;
import com.driving.driver.controller.form.CreateDriverFaceModelForm;
import com.driving.driver.controller.form.DriverLoginForm;
import com.driving.driver.controller.form.RegisterNewDriverForm;
import com.driving.driver.controller.form.UpdateDriverAuthForm;
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
public class IDriverServiceImpl implements IDriverService {
    @Resource
    private DriverServiceOpenFeignClient driverOpenFeignService;

    @Override
    @LcnTransaction
    @Transactional(rollbackFor = {Exception.class})
    public String registerNewDriver(RegisterNewDriverForm registerNewDriverForm) {
        R r = driverOpenFeignService.registerNewDriver(registerNewDriverForm);
        return Convert.toStr(r.get("userId"));
    }

    @Override
    @LcnTransaction
    @Transactional(rollbackFor = {Exception.class})
    public void updateDriverAuth(UpdateDriverAuthForm updateDriverAuthForm) {
        driverOpenFeignService.updateDriverAuth(updateDriverAuthForm);
    }

    @Override
    @LcnTransaction
    @Transactional(rollbackFor = Exception.class)
    public String createDriverFaceModel(CreateDriverFaceModelForm createDriverFaceModelForm) {
        return MapUtil.getStr(driverOpenFeignService.createDriverFaceModel(createDriverFaceModelForm), "result");
    }

    @Override
    public Boolean driverFaceAuth(CreateDriverFaceModelForm createDriverFaceModelForm) {
        return MapUtil.getBool(driverOpenFeignService.driverFaceAuth(createDriverFaceModelForm), "result");
    }

    @Override
    public HashMap<String, Object> driverLogin(DriverLoginForm driverLoginForm) {

        return (HashMap<String, Object>) driverOpenFeignService.driverLogin(driverLoginForm).get("result");
    }

    @Override
    public HashMap<String, Object> searchDriverBaseInfo(Long driverId) {
        return (HashMap<String, Object>) driverOpenFeignService.searchDriverBaseInfo(driverId).get("result");
    }
}
