package com.driving.map.service.impl;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.driving.common.exception.BusinessException;
import com.driving.map.service.ITencentMapService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;

/**
 * @author YueLiMin
 * @version 1.0.0
 */
@Slf4j
@Service
public class TencentMapServiceImpl implements ITencentMapService {
    @Value("${tencent.map-service.key}")
    private String tencentMapKey;

    @Override
    public HashMap<String, Object> routePlanning(String mode,
                                                 String startPlaceLatitude,
                                                 String startPlaceLongitude,
                                                 String endPlaceLatitude,
                                                 String endPlaceLongitude) {
        // 腾讯地图-线路规划API
        String routerPlanningUrl = "https://apis.map.qq.com/ws/direction/v1" + mode + "/";

        HttpRequest req = new HttpRequest(routerPlanningUrl);
        req.form("from", startPlaceLatitude + "," + startPlaceLongitude);
        req.form("to", endPlaceLatitude + "," + endPlaceLongitude);
        req.form("key", tencentMapKey);
        HttpResponse resp = req.execute();
        JSONObject json = JSONUtil.parseObj(resp.body());

        int status = json.getInt("status");
        String message = json.getStr("message");
        if (status != 0) {
            log.error(message);
            throw new BusinessException("线路规划异常: " + message);
        }

        return json.getJSONObject("result").toBean(HashMap.class);
    }

    @Override
    public HashMap<String, Object> estimatedMileage(String mode,
                                                    String startPlaceLatitude,
                                                    String startPlaceLongitude,
                                                    String endPlaceLatitude,
                                                    String endPlaceLongitude) {
        // 腾讯地图-预估里程API
        String distanceMatrixUrl = "https://apis.map.qq.com/ws/distance/v1/matrix";

        HttpRequest req = new HttpRequest(distanceMatrixUrl);
        req.form("mode", mode);
        req.form("from", startPlaceLatitude + "," + startPlaceLongitude);
        req.form("to", endPlaceLatitude + "," + endPlaceLongitude);
        req.form("key", tencentMapKey);

        HttpResponse resp = req.execute();
        JSONObject json = JSONUtil.parseObj(resp.body());

        int status = json.getInt("status");
        String message = json.getStr("message");
        if (status != 0) {
            log.error(message);
            throw new BusinessException("预估里程异常: " + message);
        }

        JSONArray rows = json.getJSONObject("result").getJSONArray("rows");
        JSONObject element = rows.get(0, JSONObject.class).getJSONArray("elements").get(0, JSONObject.class);
        int distance = element.getInt("distance");
        String mileage = new BigDecimal(distance).divide(new BigDecimal(1000)).toString();
        int duration = element.getInt("duration");
        String temp = new BigDecimal(duration).divide(new BigDecimal(60), 0, RoundingMode.CEILING).toString();
        int minute = Integer.parseInt(temp);

        return new HashMap<>() {{
            put("mileage", mileage);
            put("minute", minute);
        }};
    }
}
