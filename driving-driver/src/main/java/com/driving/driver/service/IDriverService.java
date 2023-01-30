package com.driving.driver.service;

import com.driving.driver.controller.form.UpdateDriverAuthForm;

import java.util.Map;

/**
 * @author YueLiMin
 * @version 1.0.0
 */
public interface IDriverService {
    String registerNewDriver(Map<String, Object> map);

    void updateDriverAuth(UpdateDriverAuthForm form);
}
