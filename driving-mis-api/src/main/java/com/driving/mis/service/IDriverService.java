package com.driving.mis.service;

import com.driving.common.util.PageUtils;
import com.driving.mis.controller.form.DriverPagingForm;

import java.util.HashMap;

/**
 * @author YueLiMin
 * @version 1.0.0
 */
public interface IDriverService {
    void updateDriverRealAuth(Long driverId, Integer realAuth);

    HashMap<String, Object> searchDriverComprehensiveData(Long driverId, Integer realAuth);

    PageUtils searchDriverByPage(DriverPagingForm form);
}
