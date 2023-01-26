package com.driving.customer.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author YueLiMin
 * @version 1.0.0
 */
@Data
@TableName("tb_customer")
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    /**
     * 小程序授权字符串
     */
    private String openId;

    /**
     * 客户昵称
     */
    private String nickname;

    /**
     * 性别
     */
    private String sex;

    /**
     * 头像
     */
    private String photo;

    /**
     * 电话
     */
    private String tel;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 1有效，2禁用
     */
    private Integer status;

    /**
     * 注册时间
     */
    private LocalDateTime createTime;


}
