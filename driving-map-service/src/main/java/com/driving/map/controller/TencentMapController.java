package com.driving.map.controller;

import com.driving.common.util.R;
import com.driving.map.controller.form.CoordinateForm;
import com.driving.map.service.ITencentMapService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author YueLiMin
 * @version 1.0.0
 */
@RestController
@RequestMapping("/map/tencent-map")
@Tag(name = "TencentMapController", description = "腾讯地图Web接口")
public class TencentMapController {
    @Resource
    private ITencentMapService tencentMapService;

    @PostMapping("/routePlanning")
    @Operation(summary = "线路规划")
    public R routePlanning(@RequestBody @Valid CoordinateForm form) {
        return R.ok().put("result", tencentMapService.routePlanning(form.getMode(), form.getStartPlaceLatitude(), form.getStartPlaceLongitude(), form.getEndPlaceLatitude(), form.getEndPlaceLongitude()));
    }

    @PostMapping("/estimatedMileage")
    @Operation(summary = "估算里程")
    public R estimatedMileage(@RequestBody @Valid CoordinateForm form) {
        return R.ok()
                .put("result", tencentMapService.estimatedMileage(form.getMode(), form.getStartPlaceLatitude(), form.getStartPlaceLongitude(), form.getEndPlaceLatitude(), form.getEndPlaceLongitude()));
    }

}
