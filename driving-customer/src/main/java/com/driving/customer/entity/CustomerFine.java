package com.driving.customer.entity;

import java.math.BigDecimal;
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
@TableName("tb_customer_fine")
public class CustomerFine implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    /**
     * 客户ID
     */
    private Long customerId;

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
