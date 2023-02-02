package com.driving.driver.service;

import com.driving.driver.controller.form.*;

import java.util.HashMap;

/**
 * @author YueLiMin
 * @version 1.0.0
 */
public interface IDriverService {
    String registerNewDriver(RegisterNewDriverForm registerNewDriverForm);

    void updateDriverAuth(UpdateDriverAuthForm updateDriverAuthForm);

    String createDriverFaceModel(CreateDriverFaceModelForm createDriverFaceModelForm);

    Boolean driverFaceAuth(CreateDriverFaceModelForm createDriverFaceModelForm);

    HashMap<String, Object> driverLogin(DriverLoginForm driverLoginForm);

    HashMap<String, Object> searchDriverBaseInfo(Long driverId);

    /**
     * 查询司机工作台数据
     *
     * @param driverId 司机id
     * @return HashMap
     */
    HashMap<String, Object> searchWorkbenchData(Long driverId);

    /**
     * 修改司机配置信息
     *
     * @param driverId 司机id
     * @param form     司机设置实体类
     */
    void updateDriverSettings(Long driverId, UpdateDriverSettingsForm form);
}
