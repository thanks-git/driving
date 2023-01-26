package com.driving.voucher.entity;

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
@TableName("tb_voucher_customer")
public class VoucherCustomer implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    /**
     * 客户ID
     */
    private Long customerId;

    /**
     * 代金券ID
     */
    private Long voucherId;

    /**
     * 使用状态, 如果是1则未使用；如果是2则已使用；如果是3则已过期；如果是4则已经下架；
     */
    private Integer status;

    /**
     * 使用代金券的时间
     */
    private LocalDateTime usedTime;

    /**
     * 有效期开始时间
     */
    private LocalDateTime startTime;

    /**
     * 有效期截至时间
     */
    private LocalDateTime endTime;

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


}
