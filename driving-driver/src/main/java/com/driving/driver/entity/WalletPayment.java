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
@TableName("tb_wallet_payment")
public class WalletPayment implements Serializable {

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
     * 支付金额
     */
    private BigDecimal amount;

    /**
     * 1话费，2罚款，3抽奖，4缴费，5其他
     */
    private Integer type;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


}
