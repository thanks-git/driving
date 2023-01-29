package com.driving.driver.controller.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author YueLiMin
 * @version 1.0.0
 */
@Data
@Schema(description = "删除腾讯云对象存储文件")
public class DeleteCosFileForm {
    @NotEmpty(message = "paths不能为空")
    @Schema(description = "文件路径数组")
    private String[] paths;
}
