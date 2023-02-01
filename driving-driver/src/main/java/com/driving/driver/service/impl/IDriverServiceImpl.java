package com.driving.driver.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.iai.v20200303.IaiClient;
import com.tencentcloudapi.iai.v20200303.models.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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
    @Value("${tencent.cloud.secretId}")
    private String secretId;
    @Value("${tencent.cloud.secretKey}")
    private String secretKey;
    @Value("${tencent.cloud.ai-face.groupName}")
    private String groupName;
    @Value("${tencent.cloud.ai-face.groupId}")
    private String groupId;
    @Value("${tencent.cloud.ai-face.region}")
    private String region;

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
        driver.setId(form.getDriverId());

        // 1未认证 2已认证 3待认证
        driver.setRealAuth(3);
        driverMapper.updateById(driver);
    }

    @Override
    @LcnTransaction
    @Transactional(rollbackFor = {Exception.class})
    public String createDriverFaceModel(Long driverId, String base64) {
        log.info("driver id -> {}, base64 -> {}, groupId -> {}", driverId, base64, groupId);

        Driver driver = driverMapper.selectById(driverId);
        if (driver == null) {
            return "创建人脸模型失败[用户不存在啊]";
        }

        String result = "success";

        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setEndpoint("iai.tencentcloudapi.com");

        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setHttpProfile(httpProfile);

        Credential cred = new Credential(secretId, secretKey);
        IaiClient client = new IaiClient(cred, region, clientProfile);

        try {
            Long gender = "男".equals(driver.getSex()) ? 1L : 2L;

            CreatePersonRequest req = new CreatePersonRequest();
            req.setGroupId(groupId);
            req.setPersonId(driverId + "");
            req.setPersonName(driver.getName());
            req.setGender(gender);
            req.setQualityControl(4L);
            req.setUniquePersonControl(4L);
            req.setImage(base64);
            CreatePersonResponse resp = client.CreatePerson(req);

            if (StrUtil.isNotBlank(resp.getFaceId())) {
                Driver temp = new Driver();
                temp.setId(driverId);
                temp.setArchive(true);

                driverMapper.updateById(temp);
            }
        } catch (TencentCloudSDKException sdkException) {
            log.error(sdkException.getMessage());
            result = sdkException.getMessage();
        } catch (Exception e) {
            log.error("创建司机人脸模型失败", e);
            result = "创建司机人脸模型失败";
        }

        return result;
    }

    @Override
    public Boolean driverFaceAuth(Long driverId, String base64) {
        boolean isAuth = false;

        Boolean driverFaceRecognition = this.driverFaceRecognition(driverId, base64);
        Boolean driverInVivoDetection = this.driverInVivoDetection(driverId, base64);

        if (driverFaceRecognition && driverInVivoDetection) {
            isAuth = true;
        }

        return isAuth;
    }

    @Override
    public HashMap<String, Object> driverLogin(String openid, String phone) {
        // 永久授权
        String openId = microAppUtil.getOpenId(openid);
        Driver driver = driverMapper.selectOne(new LambdaQueryWrapper<Driver>().eq(Driver::getOpenId, openId).ne(Driver::getStatus, 2));
        if (driver == null) {
            throw new BusinessException("司机未注册");
        }

        // 获取手机号-企业版本可以通过小程序获取手机号
        // String tel = microAppUtil.getTel(phone);
        // if (!tel.equals(driver.getTel())) {
        //     throw new BusinessException("登录手机号与注册手机号不一致");
        // }

        // 是否进行人脸模型的创建 0未创建 1已创建
        Integer archive = driver.getArchive() ? 1 : 0;
        // 审核状态 1未认证 2已认证 3待审核
        Integer realAuth = driver.getRealAuth();

        HashMap<String, Object> map = new HashMap<>();
        map.put("archive", archive);
        map.put("realAuth", realAuth);
        map.put("driverId", driver.getId());

        return map;
    }

    @Override
    public HashMap<String, Object> searchDriverBaseInfo(Long driverId) {
        HashMap<String, Object> map = driverMapper.searchDriverBaseInfo(driverId);
        // summary 属性值为一个json对象
        cn.hutool.json.JSONObject jsonObject = JSONUtil.parseObj(MapUtil.getStr(map, "summary"));
        map.replace("summary", jsonObject);
        return map;
    }

    /**
     * 司机人脸识别
     *
     * @param driverId 司机id
     * @param base64   图片编码
     * @return Boolean
     */
    private Boolean driverFaceRecognition(Long driverId, String base64) {
        Boolean flag = false;

        Credential cred = new Credential(secretId, secretKey);

        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setEndpoint("iai.tencentcloudapi.com");

        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setHttpProfile(httpProfile);

        IaiClient client = new IaiClient(cred, region, clientProfile);

        VerifyFaceRequest req = new VerifyFaceRequest();
        req.setPersonId(driverId + "");
        req.setQualityControl(4L);
        req.setImage(base64);

        try {
            VerifyFaceResponse resp = client.VerifyFace(req);
            flag = resp.getIsMatch();
        } catch (TencentCloudSDKException exception) {
            log.error(exception.getMessage());
        } catch (Exception e) {
            log.error("发生了什么事情?", e);
        }

        return flag;
    }

    /**
     * 司机人脸识别
     *
     * @param driverId 司机id
     * @param base64   图片编码
     * @return Boolean
     */
    private Boolean driverInVivoDetection(Long driverId, String base64) {
        boolean flag = false;

        Credential cred = new Credential(secretId, secretKey);

        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setEndpoint("iai.tencentcloudapi.com");

        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setHttpProfile(httpProfile);

        IaiClient client = new IaiClient(cred, region, clientProfile);

        DetectLiveFaceAccurateRequest req = new DetectLiveFaceAccurateRequest();
        req.setImage(base64);

        try {
            DetectLiveFaceAccurateResponse resp = client.DetectLiveFaceAccurate(req);
            flag = resp.getScore() >= 40;
        } catch (TencentCloudSDKException exception) {
            log.error(exception.getMessage());
        } catch (Exception e) {
            log.error("发生什么事情了?", e);
        }

        return flag;
    }

}
