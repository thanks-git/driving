package com.driving.driver.service;

import com.driving.driver.controller.form.UpdateDriverSettingsForm;

import java.util.HashMap;

/**
 * @author YueLiMin
 * @version 1.0.0
 */
public interface IDriverSettingsService {
    /**
     * 查询司机设置信息
     *
     * @param driverId 司机id
     * @return HashMap
     */
    HashMap<String, Object> searchDriverSettings(Long driverId);

    /**
     * 修改司机设置信息
     *
     * @param driverId 司机id
     * @param form     实体类
     */
    void updateDriverSettings(Long driverId, UpdateDriverSettingsForm form);
}
