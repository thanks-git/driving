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
@TableName("tb_order_bill")
public class OrderBill implements Serializable {

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
     * 总金额
     */
    private BigDecimal total;

    /**
     * 实付款金额
     */
    private BigDecimal realPay;

    /**
     * 里程费
     */
    private BigDecimal mileageFee;

    /**
     * 通话费
     */
    private BigDecimal telFee;

    /**
     * 等时费用
     */
    private BigDecimal waitingFee;

    /**
     * 路桥费
     */
    private BigDecimal tollFee;

    /**
     * 停车费
     */
    private BigDecimal parkingFee;

    /**
     * 其他费用
     */
    private BigDecimal otherFee;

    /**
     * 返程费
     */
    private BigDecimal returnFee;

    /**
     * 顾客好处费
     */
    private BigDecimal favourFee;

    /**
     * 系统奖励费
     */
    private BigDecimal incentiveFee;

    /**
     * 代金券面额
     */
    private BigDecimal voucherFee;

    /**
     * 详情
     */
    private String detail;

    /**
     * 基础里程（公里）
     */
    private Integer baseMileage;

    /**
     * 基础里程价格
     */
    private BigDecimal baseMileagePrice;

    /**
     * 超出基础里程的价格
     */
    private BigDecimal exceedMileagePrice;

    /**
     * 基础分钟
     */
    private Integer baseMinute;

    /**
     * 超出基础分钟的价格
     */
    private BigDecimal exceedMinutePrice;

    /**
     * 基础返程里程（公里）
     */
    private Integer baseReturnMileage;

    /**
     * 超出基础返程里程的价格
     */
    private BigDecimal exceedReturnPrice;


}
