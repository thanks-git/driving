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
@TableName("tb_order_monitoring")
public class OrderMonitoring implements Serializable {

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
     * 录音文档云存储网址
     */
    private String recording;

    /**
     * 谈话文字内容
     */
    private String content;

    /**
     * 谈话内容的标签，比如辱骂、挑逗、开房、包养等
     */
    private String tag;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


}
