package com.driving.driver.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;

/**
 * @author YueLiMin
 * @version 1.0.0
 */
@Data
@TableName("tb_wallet_income")
public class WalletIncome implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    /**
     * uuid字符串
     */
    private String uuid;

    /**
     * 司机ID
     */
    private Long driverId;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 1充值，2奖励，3补贴
     */
    private Integer type;

    /**
     * 预支付订单ID
     */
    private String prepayId;

    /**
     * 1未支付，2已支付，3已到账
     */
    private Integer status;

    /**
     * 备注信息
     */
    private String remark;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


}
