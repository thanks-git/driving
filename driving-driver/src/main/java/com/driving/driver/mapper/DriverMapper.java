package com.driving.driver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.driving.driver.entity.Driver;
import org.apache.ibatis.annotations.Mapper;

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
}
