package com.driving.driver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.driving.driver.entity.Driver;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author YueLiMin
 * @version 1.0.0
 */
@Mapper
public interface DriverMapper extends BaseMapper<Driver> {
    /**
     * 根据openId查询司机Id
     *
     * @param openId openId
     * @return 司机id
     */
    String searchDriverIdByOpenId(String openId);

    /**
     * 司机注册
     *
     * @param map 司机信息
     */
    void registerNewDriver(Map<String, Object> map);

    /**
     * 查询司机是否存在
     *
     * @param map openId, driverId
     * @return 0不存在, 1已存在
     */
    long hasDriver(Map<String, Object> map);

    /**
     * 查询司机基本信息
     *
     * @param driverId 司机id
     * @return HashMap
     */
    HashMap<String, Object> searchDriverBaseInfo(Long driverId);

    /**
     * 查询司机认证信息
     *
     * @param driverId 司机Id
     * @return HashMap
     */
    HashMap<String, Object> searchDriverAuthInformation(Long driverId);

    /**
     * 司机分页条件查询
     *
     * @param hashMap 查询条件
     * @return List
     */
    ArrayList<HashMap<String, Object>> searchDriverByPage(HashMap<String, Object> hashMap);

    /**
     * 查询司机记录数
     *
     * @param hashMap 查询条件
     * @return HashMap
     */
    Long searchDriverCount(HashMap<String, Object> hashMap);

    /**
     * 查询司机认证信息
     *
     * @param driverId 司机id
     * @return HashMap
     */
    HashMap<String, Object> searchDriverRealSummary(Long driverId);
}
