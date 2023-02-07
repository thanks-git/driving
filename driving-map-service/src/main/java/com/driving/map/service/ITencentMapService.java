package com.driving.map.service;

import java.util.HashMap;

/**
 * @author YueLiMin
 * @version 1.0.0
 */
public interface ITencentMapService {
    /**
     * 选路规划
     *
     * @param mode                规划模式, 取值: driving-驾车, walking-步行, bicycling-自行车, ebicycling-电动车, transit-公交
     * @param startPlaceLatitude  开始地点纬度
     * @param startPlaceLongitude 开始地点经度
     * @param endPlaceLatitude    结束地点纬度
     * @param endPlaceLongitude   结束地点经度
     * @return HashMap
     * @link <a href="https://lbs.qq.com/service/webService/webServiceGuide/webServiceRoute">腾讯地图-线路规划</a>
     */
    HashMap<String, Object> routePlanning(String mode,
                                          String startPlaceLatitude,
                                          String startPlaceLongitude,
                                          String endPlaceLatitude,
                                          String endPlaceLongitude);

    /**
     * 预估里程
     *
     * @param mode                计算方式, 取值: driving-驾车, walking-步行, bicycling-自行车
     * @param startPlaceLatitude  开始地点纬度
     * @param startPlaceLongitude 开始地点经度
     * @param endPlaceLatitude    结束地点纬度
     * @param endPlaceLongitude   结束地点经度
     * @return HashMap
     * @link <a href="https://lbs.qq.com/service/webService/webServiceGuide/webServiceMatrix">腾讯地图-批量距离计算</a>
     */
    HashMap<String, Object> estimatedMileage(String mode,
                                             String startPlaceLatitude,
                                             String startPlaceLongitude,
                                             String endPlaceLatitude,
                                             String endPlaceLongitude);
}
