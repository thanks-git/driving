package com.driving.driver.service;

import com.driving.driver.controller.form.CreateDriverFaceModelForm;
import com.driving.driver.controller.form.DriverLoginForm;
import com.driving.driver.controller.form.RegisterNewDriverForm;
import com.driving.driver.controller.form.UpdateDriverAuthForm;

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
}
