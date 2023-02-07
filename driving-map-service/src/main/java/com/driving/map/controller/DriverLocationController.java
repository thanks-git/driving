package com.driving.map.controller;

import cn.hutool.core.bean.BeanUtil;
import com.driving.common.util.R;
import com.driving.map.controller.form.HitConditionForm;
import com.driving.map.controller.form.LocationCacheForm;
import com.driving.map.controller.form.OrderLocationCacheForm;
import com.driving.map.service.ILocationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Map;

/**
 * @author YueLiMin
 * @version 1.0.0
 */
@RestController
@RequestMapping("/map/driver-location")
@Tag(name = "DriverLocationController", description = "司机位置web接口")
public class DriverLocationController {
    @Resource
    private ILocationService locationService;

    @PostMapping("/updateLocationCache")
    @Operation(summary = "更新司机GPS定位缓存")
    public R updateLocationCache(@RequestBody @Valid LocationCacheForm form) {
        Map<String, Object> param = BeanUtil.beanToMap(form);
        locationService.updateDriverLocation(param);
        return R.ok();
    }

    @DeleteMapping("/removeLocationCache/{driverId}")
    @Operation(summary = "删除司机GPS定位缓存")
    public R removeLocationCache(@PathVariable("driverId") Long driverId) {
        locationService.removeDriverLocation(driverId);
        return R.ok();
    }

    @PostMapping("/searchHitList")
    @Operation(summary = "查询符合某个订单接单的司机列表")
    public R searchHitList(@RequestBody @Valid HitConditionForm form) {
        double startPlaceLatitude = Double.parseDouble(form.getStartPlaceLatitude());
        double startPlaceLongitude = Double.parseDouble(form.getStartPlaceLongitude());
        double endPlaceLatitude = Double.parseDouble(form.getEndPlaceLatitude());
        double endPlaceLongitude = Double.parseDouble(form.getEndPlaceLongitude());
        double mileage = Double.parseDouble(form.getMileage());

        return R.ok().put("result", locationService.searchHitList(startPlaceLatitude, startPlaceLongitude, endPlaceLatitude, endPlaceLongitude, mileage));
    }

    @PostMapping("/updateOrderLocationCache")
    @Operation(summary = "更新订单定位缓存")
    public R updateOrderLocationCache(@RequestBody @Valid OrderLocationCacheForm form) {
        Map<String, Object> param = BeanUtil.beanToMap(form);
        locationService.updateOrderLocationCache(param);
        return R.ok();
    }

    @GetMapping("/searchOrderLocationCache/{orderId}")
    @Operation(summary = "查询订单定位缓存")
    public R searchOrderLocationCache(@PathVariable("orderId") Long orderId) {
        return R.ok().put("result", locationService.searchOrderLocationCache(orderId));
    }
}
