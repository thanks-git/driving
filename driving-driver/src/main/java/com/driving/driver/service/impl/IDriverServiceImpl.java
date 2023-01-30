package com.driving.driver.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapUtil;
import com.alibaba.fastjson.JSONObject;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.driving.common.exception.BusinessException;
import com.driving.common.util.MicroAppUtil;
import com.driving.driver.controller.form.UpdateDriverAuthForm;
import com.driving.driver.entity.Driver;
import com.driving.driver.entity.DriverSettings;
import com.driving.driver.entity.Wallet;
import com.driving.driver.mapper.DriverMapper;
import com.driving.driver.mapper.DriverSettingsMapper;
import com.driving.driver.mapper.WalletMapper;
import com.driving.driver.service.IDriverService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author YueLiMin
 * @version 1.0.0
 */
@Slf4j
@Service
public class IDriverServiceImpl implements IDriverService {
    @Resource
    private MicroAppUtil microAppUtil;

    @Resource
    private DriverMapper driverMapper;
    @Resource
    private DriverSettingsMapper driverSettingsMapper;
    @Resource
    private WalletMapper walletMapper;

    @Override
    @Transactional(rollbackFor = {Exception.class})
    @LcnTransaction
    public String registerNewDriver(Map<String, Object> map) {
        // 获取临时授权码
        String code = MapUtil.getStr(map, "code");
        // 获取永久授权码
        String openId = microAppUtil.getOpenId(code);

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("openId", openId);
        // 判断司机是否注册
        if (driverMapper.hasDriver(hashMap) != 0) {
            throw new BusinessException("司机已注册");
        }

        // 司机注册
        map.put("openId", openId);
        driverMapper.registerNewDriver(map);

        // 获取司机id
        String driverId = driverMapper.searchDriverIdByOpenId(openId);

        DriverSettings driverSettings = new DriverSettings();
        driverSettings.setDriverId(Long.parseLong(driverId));
        // 司机设置-默认设置
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("orientation", "");
        jsonObject.put("listenService", true);
        jsonObject.put("orderDistance", 0);
        jsonObject.put("rangeDistance", 5);
        jsonObject.put("autoAccept", false);
        driverSettings.setSettings(jsonObject.toJSONString());
        // 保存司机设置
        driverSettingsMapper.insert(driverSettings);

        // 司机钱包设置
        Wallet wallet = new Wallet();
        wallet.setDriverId(Long.parseLong(driverId));
        wallet.setBalance(new BigDecimal("0"));
        // 默认密码
        wallet.setPassword("666666");
        // 保存司机钱包设置
        walletMapper.insert(wallet);

        return driverId;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    @LcnTransaction
    public void updateDriverAuth(UpdateDriverAuthForm form) {
        Driver driver = new Driver();
        BeanUtil.copyProperties(form, driver);

        // 1未认证 2已认证 3待认证
        driver.setRealAuth(3);
        driverMapper.updateById(driver);
    }
}
