SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

CREATE SCHEMA hxds_odr;
USE hxds_odr;

-- ----------------------------
-- Table structure for tb_order
-- ----------------------------
DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `uuid` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单序列号',
  `customer_id` bigint(20) NOT NULL COMMENT '客户ID',
  `start_place` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '起始地点',
  `start_place_location` json NOT NULL COMMENT '起始地点坐标信息',
  `end_place` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '结束地点',
  `end_place_location` json NOT NULL COMMENT '结束地点坐标信息',
  `expects_mileage` decimal(10, 2) NOT NULL COMMENT '预估里程',
  `real_mileage` decimal(10, 2) NULL DEFAULT NULL COMMENT '实际里程',
  `return_mileage` decimal(10, 2) NULL DEFAULT NULL COMMENT '返程里程',
  `expects_fee` decimal(10, 2) NOT NULL COMMENT '预估订单价格',
  `favour_fee` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '顾客好处费',
  `incentive_fee` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '系统奖励费',
  `real_fee` decimal(10, 2) NULL DEFAULT NULL COMMENT '实际订单价格',
  `driver_id` bigint(20) NULL DEFAULT NULL COMMENT '司机ID',
  `date` date NOT NULL COMMENT '订单日期',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '订单创建时间',
  `accept_time` datetime NULL DEFAULT NULL COMMENT '司机接单时间',
  `arrive_time` datetime NULL DEFAULT NULL COMMENT '司机到达时间',
  `start_time` datetime NULL DEFAULT NULL COMMENT '代驾开始时间',
  `end_time` datetime NULL DEFAULT NULL COMMENT '代驾结束时间',
  `waiting_minute` smallint(6) NULL DEFAULT NULL COMMENT '代驾等时分钟数',
  `prepay_id` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微信预支付单ID',
  `pay_id` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微信支付单ID',
  `pay_time` datetime NULL DEFAULT NULL COMMENT '微信付款时间',
  `charge_rule_id` bigint(11) NOT NULL COMMENT '费用规则ID',
  `cancel_rule_id` bigint(20) NULL DEFAULT NULL COMMENT '订单取消规则ID',
  `car_plate` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '车牌号',
  `car_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '车型',
  `status` tinyint(4) NOT NULL DEFAULT 1 COMMENT '1等待接单，2已接单，3司机已到达，4开始代驾，5结束代驾，6未付款，7已付款，8订单已结束，9顾客撤单，10司机撤单，11事故关闭，12其他',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单备注信息',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `unq_uuid`(`uuid`) USING BTREE,
  UNIQUE INDEX `idx_prepay_id`(`prepay_id`) USING BTREE,
  UNIQUE INDEX `idx_pay_id`(`pay_id`) USING BTREE,
  INDEX `idx_customer_id`(`customer_id`) USING BTREE,
  INDEX `idx_driver_id`(`driver_id`) USING BTREE,
  INDEX `idx_date`(`date`) USING BTREE,
  INDEX `idx_status`(`status`) USING BTREE,
  INDEX `idx_charge_rule_id`(`charge_rule_id`) USING BTREE,
  INDEX `idx_cancel_rule_id`(`cancel_rule_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_order_appeal
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_appeal`;
CREATE TABLE `tb_order_appeal`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `order_id` bigint(20) NOT NULL COMMENT '订单ID',
  `driver_id` bigint(20) NOT NULL COMMENT '司机ID',
  `detail` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '申诉内容',
  `result` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '处理结果',
  `status` tinyint(4) NOT NULL DEFAULT 1 COMMENT '申诉状态，1申诉中，2申诉成功，3申诉失败',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_order_id`(`order_id`) USING BTREE,
  INDEX `idx_driver_id`(`driver_id`) USING BTREE,
  INDEX `idx_status`(`status`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '司机申诉表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_order_appeal
-- ----------------------------

-- ----------------------------
-- Table structure for tb_order_bill
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_bill`;
CREATE TABLE `tb_order_bill`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `order_id` bigint(20) NOT NULL COMMENT '订单ID',
  `total` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '总金额',
  `real_pay` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '实付款金额',
  `mileage_fee` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '里程费',
  `tel_fee` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '通话费',
  `waiting_fee` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '等时费用',
  `toll_fee` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '路桥费',
  `parking_fee` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '停车费',
  `other_fee` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '其他费用',
  `return_fee` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '返程费',
  `favour_fee` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '顾客好处费',
  `incentive_fee` decimal(10, 2) UNSIGNED NULL DEFAULT 0.00 COMMENT '系统奖励费',
  `voucher_fee` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '代金券面额',
  `detail` json NULL COMMENT '详情',
  `base_mileage` smallint(6) NOT NULL COMMENT '基础里程（公里）',
  `base_mileage_price` decimal(10, 2) NOT NULL COMMENT '基础里程价格',
  `exceed_mileage_price` decimal(10, 2) NOT NULL COMMENT '超出基础里程的价格',
  `base_minute` smallint(6) NOT NULL COMMENT '基础分钟',
  `exceed_minute_price` decimal(10, 2) NOT NULL COMMENT '超出基础分钟的价格',
  `base_return_mileage` smallint(6) NOT NULL COMMENT '基础返程里程（公里）',
  `exceed_return_price` decimal(10, 2) NOT NULL COMMENT '超出基础返程里程的价格',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_order_id`(`order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单账单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_order_call
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_call`;
CREATE TABLE `tb_order_call`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `order_id` bigint(20) NOT NULL COMMENT '订单ID',
  `minute` smallint(6) NOT NULL COMMENT '通话分钟数',
  `start_time` datetime NOT NULL COMMENT '开始时间',
  `end_time` datetime NULL DEFAULT NULL COMMENT '结束时间',
  `fee` decimal(10, 2) NOT NULL COMMENT '通话费',
  `date` date NOT NULL COMMENT '日期',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_order_id`(`order_id`) USING BTREE,
  INDEX `idx_date`(`date`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单通话记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_order_call
-- ----------------------------

-- ----------------------------
-- Table structure for tb_order_comment
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_comment`;
CREATE TABLE `tb_order_comment`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `order_id` bigint(20) NOT NULL COMMENT '订单ID',
  `driver_id` bigint(20) NOT NULL COMMENT '司机ID',
  `customer_id` bigint(20) NOT NULL COMMENT '顾客ID',
  `rate` tinyint(4) NOT NULL COMMENT '评分，1星~5星',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '差评备注',
  `status` tinyint(4) NOT NULL COMMENT '状态，1未申诉，2已申诉，3申诉失败，4申诉成功',
  `instance_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申诉工作流ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `unq_order_id`(`order_id`) USING BTREE,
  INDEX `idx_driver_id`(`driver_id`) USING BTREE,
  INDEX `idx_customer_id`(`customer_id`) USING BTREE,
  INDEX `idx_status`(`status`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单评价表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_order_monitoring
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_monitoring`;
CREATE TABLE `tb_order_monitoring`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `order_id` bigint(20) NOT NULL COMMENT '订单ID',
  `recording` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '录音文档云存储网址',
  `content` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '谈话文字内容',
  `tag` json NULL COMMENT '谈话内容的标签，比如辱骂、挑逗、开房、包养等',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_order_id`(`order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单监控表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_order_monitoring
-- ----------------------------

-- ----------------------------
-- Table structure for tb_order_profitsharing
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_profitsharing`;
CREATE TABLE `tb_order_profitsharing`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `order_id` bigint(20) NOT NULL COMMENT '订单ID',
  `rule_id` bigint(20) NOT NULL COMMENT '规则ID',
  `amount_fee` decimal(10, 2) NOT NULL COMMENT '总费用',
  `payment_rate` decimal(10, 2) NOT NULL COMMENT '微信支付渠道费率',
  `payment_fee` decimal(10, 2) NOT NULL COMMENT '微信支付渠道费',
  `tax_rate` decimal(10, 2) NOT NULL COMMENT '为代驾司机代缴税率',
  `tax_fee` decimal(10, 2) NOT NULL COMMENT '税率支出',
  `system_income` decimal(10, 2) NOT NULL COMMENT '企业分账收入',
  `driver_income` decimal(10, 2) NOT NULL COMMENT '司机分账收入',
  `status` tinyint(4) NOT NULL COMMENT '分账状态，1未分账，2已分账',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `unq_order_id`(`order_id`) USING BTREE,
  INDEX `idx_status`(`status`) USING BTREE,
  INDEX `idx_rule_id`(`rule_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单分账表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_order_violation
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_violation`;
CREATE TABLE `tb_order_violation`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `order_id` bigint(20) NOT NULL COMMENT '订单ID',
  `driver_id` bigint(20) NOT NULL COMMENT '司机ID',
  `type` tinyint(4) NOT NULL COMMENT '违纪类型，1服务，2驾驶',
  `reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '违纪原因',
  `status` tinyint(4) NOT NULL COMMENT '状态，1未申诉，2已申诉，3申诉失败，4申诉成功',
  `instance_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申诉工作流实例ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单违规表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_order_violation
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;


SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;
CREATE SCHEMA hxds_vhr;
USE hxds_vhr;
-- ----------------------------
-- Table structure for tb_voucher
-- ----------------------------
DROP TABLE IF EXISTS `tb_voucher`;
CREATE TABLE `tb_voucher`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `uuid` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'UUID',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '代金券标题',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述文字',
  `tag` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '代金券标签，例如新人专用',
  `total_quota` int(11) NOT NULL COMMENT '代金券数量，如果是0，则是无限量',
  `take_count` int(11) NOT NULL DEFAULT 0 COMMENT '实际领取数量',
  `used_count` int(11) NOT NULL DEFAULT 0 COMMENT '已经使用的数量',
  `discount` decimal(10, 2) NOT NULL COMMENT '代金券面额',
  `with_amount` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '最少消费金额才能使用代金券',
  `type` tinyint(4) NOT NULL DEFAULT 0 COMMENT '代金券赠送类型，如果是1则通用券，用户领取；如果是2，则是注册赠券',
  `limit_quota` smallint(6) NOT NULL DEFAULT 1 COMMENT '用户领券限制数量，如果是0，则是不限制；默认是1，限领一张',
  `status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '代金券状态，如果是1则是正常可用；如果是2则是过期; 如果是3则是下架',
  `time_type` tinyint(4) NULL DEFAULT NULL COMMENT '有效时间限制，如果是1，则基于领取时间的有效天数days；如果是2，则start_time和end_time是优惠券有效期；',
  `days` smallint(6) NULL DEFAULT NULL COMMENT '基于领取时间的有效天数days',
  `start_time` datetime NULL DEFAULT NULL COMMENT '代金券开始时间',
  `end_time` datetime NULL DEFAULT NULL COMMENT '代金券结束时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_uuid`(`uuid`) USING BTREE,
  INDEX `idx_tag`(`tag`) USING BTREE,
  INDEX `idx_type`(`type`) USING BTREE,
  INDEX `idx_status`(`status`) USING BTREE,
  INDEX `idx_time_type`(`time_type`) USING BTREE,
  INDEX `idx_limit`(`limit_quota`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '代金券表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_voucher_customer
-- ----------------------------
DROP TABLE IF EXISTS `tb_voucher_customer`;
CREATE TABLE `tb_voucher_customer`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `customer_id` bigint(20) NOT NULL COMMENT '客户ID',
  `voucher_id` bigint(20) NOT NULL COMMENT '代金券ID',
  `status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '使用状态, 如果是1则未使用；如果是2则已使用；如果是3则已过期；如果是4则已经下架；',
  `used_time` datetime NULL DEFAULT NULL COMMENT '使用代金券的时间',
  `start_time` datetime NULL DEFAULT NULL COMMENT '有效期开始时间',
  `end_time` datetime NULL DEFAULT NULL COMMENT '有效期截至时间',
  `order_id` bigint(20) NULL DEFAULT NULL COMMENT '订单ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_customer_id`(`customer_id`) USING BTREE,
  INDEX `idx_voucher_id`(`voucher_id`) USING BTREE,
  INDEX `idx_status`(`status`) USING BTREE,
  INDEX `idx_order_id`(`order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '代金券领取表' ROW_FORMAT = Dynamic;


SET FOREIGN_KEY_CHECKS = 1;
