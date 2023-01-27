package com.driving.driver.controller.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author YueLiMin
 * @version 1.0.0
 */
@Data
@Schema(description = "司机注册")
public class RegisterNewDriverForm {
    @NotBlank(message = "code不能为空")
    @Schema(description = "微信小程序临时授权码")
    private String code;

    @NotBlank(message = "nickname不能为空")
    @Schema(description = "用户昵称")
    private String nickname;

    @NotBlank(message = "photo不能为空")
    @Schema(description = "用户头像")
    private String photo;
}
