package com.driving.driver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author YueLiMin
 * @version 1.0.0
 */
@Data
@TableName("tb_driver_fine")
public class DriverFine implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    /**
     * 司机ID
     */
    private Long driverId;

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 罚款金额
     */
    private BigDecimal amount;

    /**
     * 备注信息
     */
    private String remark;

    /**
     * 1未缴纳，2已缴纳
     */
    private Integer status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


}
