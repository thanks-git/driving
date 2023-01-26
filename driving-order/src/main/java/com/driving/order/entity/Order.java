package com.driving.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author YueLiMin
 * @version 1.0.0
 */
@Data
@TableName("tb_order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    /**
     * 订单序列号
     */
    private String uuid;

    /**
     * 客户ID
     */
    private Long customerId;

    /**
     * 起始地点
     */
    private String startPlace;

    /**
     * 起始地点坐标信息
     */
    private String startPlaceLocation;

    /**
     * 结束地点
     */
    private String endPlace;

    /**
     * 结束地点坐标信息
     */
    private String endPlaceLocation;

    /**
     * 预估里程
     */
    private BigDecimal expectsMileage;

    /**
     * 实际里程
     */
    private BigDecimal realMileage;

    /**
     * 返程里程
     */
    private BigDecimal returnMileage;

    /**
     * 预估订单价格
     */
    private BigDecimal expectsFee;

    /**
     * 顾客好处费
     */
    private BigDecimal favourFee;

    /**
     * 系统奖励费
     */
    private BigDecimal incentiveFee;

    /**
     * 实际订单价格
     */
    private BigDecimal realFee;

    /**
     * 司机ID
     */
    private Long driverId;

    /**
     * 订单日期
     */
    private LocalDate date;

    /**
     * 订单创建时间
     */
    private LocalDateTime createTime;

    /**
     * 司机接单时间
     */
    private LocalDateTime acceptTime;

    /**
     * 司机到达时间
     */
    private LocalDateTime arriveTime;

    /**
     * 代驾开始时间
     */
    private LocalDateTime startTime;

    /**
     * 代驾结束时间
     */
    private LocalDateTime endTime;

    /**
     * 代驾等时分钟数
     */
    private Integer waitingMinute;

    /**
     * 微信预支付单ID
     */
    private String prepayId;

    /**
     * 微信支付单ID
     */
    private String payId;

    /**
     * 微信付款时间
     */
    private LocalDateTime payTime;

    /**
     * 费用规则ID
     */
    private Long chargeRuleId;

    /**
     * 订单取消规则ID
     */
    private Long cancelRuleId;

    /**
     * 车牌号
     */
    private String carPlate;

    /**
     * 车型
     */
    private String carType;

    /**
     * 1等待接单，2已接单，3司机已到达，4开始代驾，5结束代驾，6未付款，7已付款，8订单已结束，9顾客撤单，10司机撤单，11事故关闭，12其他
     */
    private Integer status;

    /**
     * 订单备注信息
     */
    private String remark;


}
