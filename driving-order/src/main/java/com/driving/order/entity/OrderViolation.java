package com.driving.order.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author YueLiMin
 * @version 1.0.0
 */
@Data
@TableName("tb_order_violation")
public class OrderViolation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 司机ID
     */
    private Long driverId;

    /**
     * 违纪类型，1服务，2驾驶
     */
    private Integer type;

    /**
     * 违纪原因
     */
    private String reason;

    /**
     * 状态，1未申诉，2已申诉，3申诉失败，4申诉成功
     */
    private Integer status;

    /**
     * 申诉工作流实例ID
     */
    private String instanceId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


}
