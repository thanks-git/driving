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
@TableName("tb_order_appeal")
public class OrderAppeal implements Serializable {

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
     * 申诉内容
     */
    private String detail;

    /**
     * 处理结果
     */
    private String result;

    /**
     * 申诉状态，1申诉中，2申诉成功，3申诉失败
     */
    private Integer status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


}
