package com.driving.driver.controller.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author YueLiMin
 * @version 1.0.0
 */
@Data
@Schema(description = "司机登陆")
public class DriverLoginForm {
    @NotBlank(message = "code不能为空")
    @Schema(description = "微信小程序临时授权")
    private String code;

    // @NotBlank(message = "phoneCode不能为空")
    // @Schema(description = "微信小程序获取电话号码临时授权")
    // private String phoneCode;
}
