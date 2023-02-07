package com.driving.map.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author YueLiMin
 * @version 1.0.0
 */
public interface ILocationService {
    /**
     * 更新司机位置信息
     *
     * @param map Map
     */
    void updateDriverLocation(Map<String, Object> map);

    /**
     * 移除司机位置信息
     *
     * @param driverId 司机id
     */
    void removeDriverLocation(Long driverId);

    /**
     * 查询符合客户条件的司机列表
     *
     * @param startPlaceLatitude  起点纬度
     * @param startPlaceLongitude 起点经度
     * @param endPlaceLatitude    终点纬度
     * @param endPlaceLongitude   终点经度
     * @param mileage             预估里程
     * @return ArrayList
     */
    ArrayList<Object> searchHitList(Double startPlaceLatitude,
                                    Double startPlaceLongitude,
                                    Double endPlaceLatitude,
                                    Double endPlaceLongitude,
                                    Double mileage);

    /**
     * 更新定位位置缓存
     *
     * @param map 订单位置信息
     */
    void updateOrderLocationCache(Map<String, Object> map);

    /**
     * 查询订单位置缓存
     *
     * @param orderId 订单id
     * @return HashMap
     */
    HashMap<String, Object> searchOrderLocationCache(Long orderId);
}
