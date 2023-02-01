package com.driving.driver.service;

import com.driving.driver.controller.form.UpdateDriverAuthForm;

import java.util.HashMap;
import java.util.Map;

/**
 * @author YueLiMin
 * @version 1.0.0
 */
public interface IDriverService {
    String registerNewDriver(Map<String, Object> map);

    void updateDriverAuth(UpdateDriverAuthForm form);

    /**
     * 创建司机人脸模型
     *
     * @param driverId 司机id
     * @param base64   图片编码
     * @return 提示信息-比如创建人脸模型失败
     */
    String createDriverFaceModel(Long driverId, String base64);

    /**
     * 司机认证
     *
     * @param driverId 司机id
     * @param base64   图片编码
     * @return Boolean: true-认证成功, false-认证失败
     */
    Boolean driverFaceAuth(Long driverId, String base64);

    /**
     * 司机微信登录
     *
     * @param openid 临时授权码
     * @param phone  临时授权手机号
     * @return HashMap
     */
    HashMap<String, Object> driverLogin(String openid, String phone);

    /**
     * 查询司机基本信息
     *
     * @param driverId 司机id
     * @return HashMap
     */
    HashMap<String, Object> searchDriverBaseInfo(Long driverId);
}
