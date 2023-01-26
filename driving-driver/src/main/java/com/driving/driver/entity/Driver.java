package com.driving.driver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author YueLiMin
 * @version 1.0.0
 */
@Data
@TableName("tb_driver")
public class Driver implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    /**
     * 小程序长期授权
     */
    private String openId;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private String sex;

    /**
     * 头像
     */
    private String photo;

    /**
     * 身份证号码
     */
    private String pid;

    /**
     * 生日
     */
    private LocalDate birthday;

    /**
     * 电话
     */
    private String tel;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 收信地址
     */
    private String mailAddress;

    /**
     * 应急联系人
     */
    private String contactName;

    /**
     * 应急联系人电话
     */
    private String contactTel;

    /**
     * 1未认证，2已认证，3审核中
     */
    private Integer realAuth;

    /**
     * 身份证地址
     */
    private String idcardAddress;

    /**
     * 身份证有效期
     */
    private LocalDate idcardExpiration;

    /**
     * 身份证正面
     */
    private String idcardFront;

    /**
     * 身份证背面
     */
    private String idcardBack;

    /**
     * 手持身份证
     */
    private String idcardHolding;

    /**
     * 驾驶证类型
     */
    private String drcardType;

    /**
     * 驾驶证有效期
     */
    private LocalDate drcardExpiration;

    /**
     * 驾驶证初次领证日期
     */
    private LocalDate drcardIssueDate;

    /**
     * 驾驶证正面
     */
    private String drcardFront;

    /**
     * 驾驶证背面
     */
    private String drcardBack;

    /**
     * 手持驾驶证
     */
    private String drcardHolding;

    /**
     * 家庭地址，包含地址和定位，用于回家导航
     */
    private String home;

    /**
     * 摘要信息，level等级，totalOrder接单数，weekOrder周接单，weekComment周好评，appeal正在申诉量
     */
    private String summary;

    /**
     * 是否在腾讯云归档存放司机面部信息
     */
    private Boolean archive;

    /**
     * 状态，1正常，2禁用，3.降低接单量
     */
    private Integer status;

    /**
     * 注册时间
     */
    private LocalDateTime createTime;


}
