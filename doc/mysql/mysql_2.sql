SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

CREATE SCHEMA hxds_cst;
USE hxds_cst;

-- ----------------------------
-- Table structure for tb_customer
-- ----------------------------
DROP TABLE IF EXISTS `tb_customer`;
CREATE TABLE `tb_customer`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `open_id` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '小程序授权字符串',
  `nickname` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '客户昵称',
  `sex` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '性别',
  `photo` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `tel` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '电话',
  `email` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `status` tinyint(4) NOT NULL COMMENT '1有效，2禁用',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `unq_open_id`(`open_id`) USING BTREE,
  INDEX `idx_status`(`status`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '客户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_customer_car
-- ----------------------------
DROP TABLE IF EXISTS `tb_customer_car`;
CREATE TABLE `tb_customer_car`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `customer_id` bigint(20) NOT NULL COMMENT '客户ID',
  `car_plate` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '车牌号',
  `car_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '车型',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_customer_id`(`customer_id`) USING BTREE,
  INDEX `idx_car_plate`(`car_plate`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '客户车辆表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for tb_customer_fine
-- ----------------------------
DROP TABLE IF EXISTS `tb_customer_fine`;
CREATE TABLE `tb_customer_fine`  (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `customer_id` bigint(20) NOT NULL COMMENT '客户ID',
  `order_id` bigint(20) NOT NULL COMMENT '订单ID',
  `amount` decimal(10, 2) NOT NULL COMMENT '罚款金额',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '备注信息',
  `status` tinyint(4) NOT NULL COMMENT '1未缴纳，2已缴纳',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_customer_id`(`customer_id`) USING BTREE,
  INDEX `idx_order_id`(`order_id`) USING BTREE,
  INDEX `idx_status`(`status`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '客户罚款表' ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;


SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;
CREATE SCHEMA hxds_dr;
USE hxds_dr;
-- ----------------------------
-- Table structure for tb_driver
-- ----------------------------
DROP TABLE IF EXISTS `tb_driver`;
CREATE TABLE `tb_driver`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `open_id` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '小程序长期授权',
  `nickname` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '昵称',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `sex` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `photo` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `pid` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证号码',
  `birthday` date NULL DEFAULT NULL COMMENT '生日',
  `tel` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `email` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `mail_address` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收信地址',
  `contact_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '应急联系人',
  `contact_tel` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '应急联系人电话',
  `real_auth` tinyint(4) NULL DEFAULT NULL COMMENT '1未认证，2已认证，3审核中',
  `idcard_address` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证地址',
  `idcard_expiration` date NULL DEFAULT NULL COMMENT '身份证有效期',
  `idcard_front` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证正面',
  `idcard_back` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证背面',
  `idcard_holding` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手持身份证',
  `drcard_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '驾驶证类型',
  `drcard_expiration` date NULL DEFAULT NULL COMMENT '驾驶证有效期',
  `drcard_issue_date` date NULL DEFAULT NULL COMMENT '驾驶证初次领证日期',
  `drcard_front` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '驾驶证正面',
  `drcard_back` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '驾驶证背面',
  `drcard_holding` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手持驾驶证',
  `home` json NULL COMMENT '家庭地址，包含地址和定位，用于回家导航',
  `summary` json NULL COMMENT '摘要信息，level等级，totalOrder接单数，weekOrder周接单，weekComment周好评，appeal正在申诉量',
  `archive` tinyint(1) NOT NULL COMMENT '是否在腾讯云归档存放司机面部信息',
  `status` tinyint(4) NOT NULL COMMENT '状态，1正常，2禁用，3.降低接单量',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `unq_open_id`(`open_id`) USING BTREE,
  UNIQUE INDEX `unq_pid`(`pid`) USING BTREE,
  INDEX `idx_real_auth`(`real_auth`) USING BTREE,
  INDEX `idx_status`(`status`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '代驾司机表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_driver_fine
-- ----------------------------
DROP TABLE IF EXISTS `tb_driver_fine`;
CREATE TABLE `tb_driver_fine`  (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `driver_id` bigint(20) NOT NULL COMMENT '司机ID',
  `order_id` bigint(20) NULL DEFAULT NULL COMMENT '订单ID',
  `amount` decimal(10, 2) NOT NULL COMMENT '罚款金额',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '备注信息',
  `status` tinyint(4) NOT NULL COMMENT '1未缴纳，2已缴纳',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_order_id`(`order_id`) USING BTREE,
  INDEX `idx_status`(`status`) USING BTREE,
  INDEX `idx_driver_id`(`driver_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '司机罚款表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for tb_driver_lockdown
-- ----------------------------
DROP TABLE IF EXISTS `tb_driver_lockdown`;
CREATE TABLE `tb_driver_lockdown`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `driver_id` bigint(20) NOT NULL COMMENT '司机ID',
  `reason` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '原因',
  `order_id` bigint(20) NULL DEFAULT NULL COMMENT '订单ID',
  `start_date` date NOT NULL COMMENT '起始日期',
  `end_date` date NOT NULL COMMENT '结束日期',
  `days` smallint(6) NOT NULL COMMENT '天数',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_driver_id`(`driver_id`) USING BTREE,
  INDEX `idx_order_id`(`order_id`) USING BTREE,
  INDEX `idx_start_date`(`start_date`) USING BTREE,
  INDEX `idx_end_date`(`end_date`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '司机禁闭表（禁止接单）' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for tb_driver_recognition
-- ----------------------------
DROP TABLE IF EXISTS `tb_driver_recognition`;
CREATE TABLE `tb_driver_recognition`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `driver_id` bigint(20) NOT NULL COMMENT '司机ID',
  `date` date NOT NULL COMMENT '检测日期',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `unq_complex_1`(`driver_id`, `date`) USING BTREE,
  INDEX `idx_driver_id`(`driver_id`) USING BTREE,
  INDEX `idx_date`(`date`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 698343989501734913 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '司机人脸识别表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_driver_settings
-- ----------------------------
DROP TABLE IF EXISTS `tb_driver_settings`;
CREATE TABLE `tb_driver_settings`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `driver_id` bigint(20) NOT NULL COMMENT '司机ID',
  `settings` json NOT NULL COMMENT '个人设置',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_driver_id`(`driver_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '司机设置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_wallet
-- ----------------------------
DROP TABLE IF EXISTS `tb_wallet`;
CREATE TABLE `tb_wallet`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `driver_id` bigint(20) NOT NULL COMMENT '司机ID',
  `balance` decimal(12, 2) NOT NULL COMMENT '钱包金额',
  `password` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付密码，如果为空，不能支付，提示用户设置支付密码',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_driver_id`(`driver_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '钱包表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_wallet_income
-- ----------------------------
DROP TABLE IF EXISTS `tb_wallet_income`;
CREATE TABLE `tb_wallet_income`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `uuid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'uuid字符串',
  `driver_id` bigint(20) NOT NULL COMMENT '司机ID',
  `amount` decimal(7, 2) NOT NULL COMMENT '金额',
  `type` tinyint(4) NOT NULL COMMENT '1充值，2奖励，3补贴',
  `prepay_id` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预支付订单ID',
  `status` tinyint(4) NOT NULL COMMENT '1未支付，2已支付，3已到账',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注信息',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_prepay_id`(`prepay_id`) USING BTREE,
  INDEX `idx_driver_id`(`driver_id`) USING BTREE,
  INDEX `idx_type`(`type`) USING BTREE,
  INDEX `idx_status`(`status`) USING BTREE,
  INDEX `idx_create_time`(`create_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '钱包收入表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_wallet_payment
-- ----------------------------
DROP TABLE IF EXISTS `tb_wallet_payment`;
CREATE TABLE `tb_wallet_payment`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `driver_id` bigint(255) NOT NULL COMMENT '司机ID',
  `amount` decimal(7, 2) NOT NULL COMMENT '支付金额',
  `type` tinyint(4) NOT NULL COMMENT '1话费，2罚款，3抽奖，4缴费，5其他',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_type`(`type`) USING BTREE,
  INDEX `idx_driver_id`(`driver_id`) USING BTREE,
  INDEX `idx_create_time`(`create_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '司机钱包付款表' ROW_FORMAT = Dynamic;


SET FOREIGN_KEY_CHECKS = 1;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;
CREATE SCHEMA hxds_rule;
USE hxds_rule;
-- ----------------------------
-- Table structure for tb_award_rule
-- ----------------------------
DROP TABLE IF EXISTS `tb_award_rule`;
CREATE TABLE `tb_award_rule`  (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '规则编码',
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '规则名称',
  `rule` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '规则代码',
  `status` tinyint(4) NOT NULL COMMENT '状态代码，1有效，2关闭',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_status`(`status`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '司机奖励规则表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_cancel_rule
-- ----------------------------
DROP TABLE IF EXISTS `tb_cancel_rule`;
CREATE TABLE `tb_cancel_rule`  (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '规则编码',
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '规则名称',
  `rule` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '规则代码',
  `type` tinyint(4) NOT NULL COMMENT '1司机取消规则，2乘客取消规则',
  `status` tinyint(4) NOT NULL COMMENT '状态代码，1有效，2关闭',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_status`(`status`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单取消规则表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for tb_charge_rule
-- ----------------------------
DROP TABLE IF EXISTS `tb_charge_rule`;
CREATE TABLE `tb_charge_rule`  (
  `id` bigint(11) NOT NULL COMMENT '主键',
  `code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '规则编码',
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '规则名称',
  `rule` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '规则代码',
  `status` tinyint(4) NOT NULL DEFAULT 2 COMMENT '状态代码，1有效，2关闭',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_status`(`status`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '代驾计费规则表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_profitsharing_rule
-- ----------------------------
DROP TABLE IF EXISTS `tb_profitsharing_rule`;
CREATE TABLE `tb_profitsharing_rule`  (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '规则编码',
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '规则名称',
  `rule` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '规则代码',
  `status` tinyint(4) NOT NULL COMMENT '状态代码，1有效，2关闭',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_status`(`status`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '司机分账表' ROW_FORMAT = DYNAMIC;


SET FOREIGN_KEY_CHECKS = 1;
