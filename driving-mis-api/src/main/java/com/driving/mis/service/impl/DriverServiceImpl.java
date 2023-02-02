package com.driving.mis.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapUtil;
import com.driving.common.util.CosUtil;
import com.driving.common.util.PageUtils;
import com.driving.mis.agent.DriverServiceOpenFeignClient;
import com.driving.mis.controller.form.DriverPagingForm;
import com.driving.mis.service.IDriverService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
    private CosUtil cosUtil;

    @Override
    public void updateDriverRealAuth(Long driverId, Integer realAuth) {
        driverServiceOpenFeignClient.updateDriverRealAuth(driverId, realAuth);
    }

    @Override
    public HashMap<String, Object> searchDriverComprehensiveData(Long driverId, Integer realAuth) {
        HashMap<String, Object> map = new HashMap<>();
        if (realAuth == 2 || realAuth == 3) {
            HashMap<String, Object> summaryMap = (HashMap<String, Object>) driverServiceOpenFeignClient.searchDriverRealSummary(driverId).get("result");

            String idcardFront = MapUtil.getStr(summaryMap, "idcardFront");
            String idcardBack = MapUtil.getStr(summaryMap, "idcardBack");
            String idcardHolding = MapUtil.getStr(summaryMap, "idcardHolding");
            String drcardFront = MapUtil.getStr(summaryMap, "drcardFront");
            String drcardBack = MapUtil.getStr(summaryMap, "drcardBack");
            String drcardHolding = MapUtil.getStr(summaryMap, "drcardHolding");

            idcardFront = idcardFront.length() > 0 ? cosUtil.getPrivateFileUrl(idcardFront) : "";
            idcardBack = idcardBack.length() > 0 ? cosUtil.getPrivateFileUrl(idcardBack) : "";
            idcardHolding = idcardHolding.length() > 0 ? cosUtil.getPrivateFileUrl(idcardHolding) : "";
            drcardFront = drcardFront.length() > 0 ? cosUtil.getPrivateFileUrl(drcardFront) : "";
            drcardBack = drcardBack.length() > 0 ? cosUtil.getPrivateFileUrl(drcardBack) : "";
            drcardHolding = drcardHolding.length() > 0 ? cosUtil.getPrivateFileUrl(drcardHolding) : "";

            summaryMap.replace("idcardFront", idcardFront);
            summaryMap.replace("idcardBack", idcardBack);
            summaryMap.replace("idcardHolding", idcardHolding);
            summaryMap.replace("drcardFront", drcardFront);
            summaryMap.replace("drcardBack", drcardBack);
            summaryMap.replace("drcardHolding", drcardHolding);

            map.put("summaryMap", summaryMap);
        }
        return map;
    }

    @Override
    public PageUtils searchDriverByPage(DriverPagingForm form) {
        HashMap<String, Object> hashMap = (HashMap<String, Object>) driverServiceOpenFeignClient.searchDriverByPage(form).get("result");
        return BeanUtil.toBean(hashMap, PageUtils.class);
    }
}
