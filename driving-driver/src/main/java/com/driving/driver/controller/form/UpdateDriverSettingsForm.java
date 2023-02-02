package com.driving.driver.controller.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author YueLiMin
 * @version 1.0.0
 */
@Data
@Schema(description = "修改司机设置")
public class UpdateDriverSettingsForm {

    @Schema(description = "自动抢单")
    private Boolean autoAccept;

    @Schema(description = "定向接单")
    private String orientation;

    @Schema(description = "自动听单")
    private Boolean listenService;

    @Schema(description = "代驾预估里程(公里)")
    private Integer orderDistance;

    @Schema(description = "接受订单范围(公里)")
    private Integer rangeDistance;
}
