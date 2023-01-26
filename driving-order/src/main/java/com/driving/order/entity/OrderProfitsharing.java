package com.driving.order.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author YueLiMin
 * @version 1.0.0
 */
@Data
@TableName("tb_order_profitsharing")
public class OrderProfitsharing implements Serializable {

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
     * 规则ID
     */
    private Long ruleId;

    /**
     * 总费用
     */
    private BigDecimal amountFee;

    /**
     * 微信支付渠道费率
     */
    private BigDecimal paymentRate;

    /**
     * 微信支付渠道费
     */
    private BigDecimal paymentFee;

    /**
     * 为代驾司机代缴税率
     */
    private BigDecimal taxRate;

    /**
     * 税率支出
     */
    private BigDecimal taxFee;

    /**
     * 企业分账收入
     */
    private BigDecimal systemIncome;

    /**
     * 司机分账收入
     */
    private BigDecimal driverIncome;

    /**
     * 分账状态，1未分账，2已分账
     */
    private Integer status;


}
