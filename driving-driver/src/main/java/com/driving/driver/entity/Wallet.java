package com.driving.driver.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;

/**
 * @author YueLiMin
 * @version 1.0.0
 */
@Data
@TableName("tb_wallet")
public class Wallet implements Serializable {

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
     * 钱包金额
     */
    private BigDecimal balance;

    /**
     * 支付密码，如果为空，不能支付，提示用户设置支付密码
     */
    private String password;


}
