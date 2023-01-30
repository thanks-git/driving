package com.driving.driver.service.impl;

import cn.hutool.core.convert.Convert;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.driving.common.util.R;
import com.driving.driver.controller.form.RegisterNewDriverForm;
import com.driving.driver.controller.form.UpdateDriverAuthForm;
import com.driving.driver.feign.IDriverOpenFeignService;
import com.driving.driver.service.IDriverService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author YueLiMin
 * @version 1.0.0
 */
@Slf4j
@Service
public class IDriverServiceImpl implements IDriverService {
    @Resource
    private IDriverOpenFeignService driverOpenFeignService;

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
}
