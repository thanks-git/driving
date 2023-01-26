package com.driving.driver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author YueLiMin
 * @version 1.0.0
 */
@Data
@TableName("tb_driver_recognition")
public class DriverRecognition implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 司机ID
     */
    private Long driverId;

    /**
     * 检测日期
     */
    private LocalDate date;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


}
