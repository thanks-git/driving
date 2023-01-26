package com.driving.driver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author YueLiMin
 * @version 1.0.0
 */
@Data
@TableName("tb_driver_lockdown")
public class DriverLockdown implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    /**
     * 司机ID
     */
    private Long driverId;

    /**
     * 原因
     */
    private String reason;

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 起始日期
     */
    private LocalDate startDate;

    /**
     * 结束日期
     */
    private LocalDate endDate;

    /**
     * 天数
     */
    private Integer days;


}
