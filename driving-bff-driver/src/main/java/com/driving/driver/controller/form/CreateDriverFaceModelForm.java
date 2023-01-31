package com.driving.driver.controller.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author YueLiMin
 * @version 1.0.0
 */
@Data
@Schema(description = "司机人脸模型")
public class CreateDriverFaceModelForm {
    @Schema(description = "司机ID")
    private Long driverId;

    @NotBlank(message = "photo不能为空")
    @Schema(description = "司机面部照片Base64字符串")
    private String photo;
}