package com.driving.driver.service;

import com.driving.driver.controller.form.RegisterNewDriverForm;
import com.driving.driver.controller.form.UpdateDriverAuthForm;

/**
 * @author YueLiMin
 * @version 1.0.0
 */
public interface IDriverService {
    String registerNewDriver(RegisterNewDriverForm registerNewDriverForm);

    void updateDriverAuth(UpdateDriverAuthForm updateDriverAuthForm);
}
