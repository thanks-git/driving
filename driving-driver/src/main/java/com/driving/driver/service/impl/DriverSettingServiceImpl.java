package com.driving.driver.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.driving.driver.controller.form.UpdateDriverSettingsForm;
import com.driving.driver.entity.DriverSettings;
import com.driving.driver.mapper.DriverSettingsMapper;
import com.driving.driver.service.IDriverSettingsService;
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
public class DriverSettingServiceImpl implements IDriverSettingsService {
    @Resource
    private DriverSettingsMapper driverSettingsMapper;

    @Override
    public HashMap<String, Object> searchDriverSettings(Long driverId) {
        String settings = driverSettingsMapper.selectOne(new LambdaQueryWrapper<DriverSettings>().eq(DriverSettings::getDriverId, driverId)).getSettings();
        return JSONUtil.parseObj(settings).toBean(HashMap.class);
    }

    @Override
    @LcnTransaction
    @Transactional(rollbackFor = Exception.class)
    public void updateDriverSettings(Long driverId, UpdateDriverSettingsForm form) {
        String jsonStr = JSONUtil.toJsonStr(form);
        DriverSettings driverSettings = new DriverSettings();
        driverSettings.setSettings(jsonStr);

        driverSettingsMapper.update(driverSettings, new LambdaQueryWrapper<DriverSettings>().eq(DriverSettings::getDriverId, driverSettings));
    }

}
