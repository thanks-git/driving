package com.driving.order.service;

import java.util.HashMap;

/**
 * @author YueLiMin
 * @version 1.0.0
 */
public interface IOrderService {
    HashMap<String, Object> searchDriverTodayBusinessData(Long driverId);
}
