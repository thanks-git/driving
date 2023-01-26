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
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '客户车辆表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_customer_car
-- ----------------------------

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
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '客户罚款表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_customer_fine
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;


CREATE SCHEMA hxds_dr;
USE hxds_dr;
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

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
-- Records of tb_driver
-- ----------------------------

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
  INDEX `idx_driver_id`(`driver_id`) USING BTREE,
  INDEX `idx_order_id`(`order_id`) USING BTREE,
  INDEX `idx_status`(`status`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '司机罚款表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_driver_fine
-- ----------------------------

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
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '司机禁闭表（禁止接单）' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_driver_lockdown
-- ----------------------------

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
) ENGINE = InnoDB AUTO_INCREMENT = 687336941305704449 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '司机人脸识别表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_driver_recognition
-- ----------------------------

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
-- Records of tb_wallet
-- ----------------------------

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
-- Records of tb_wallet_income
-- ----------------------------

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
  INDEX `idx_driver_id`(`driver_id`) USING BTREE,
  INDEX `idx_type`(`type`) USING BTREE,
  INDEX `idx_create_time`(`create_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '司机钱包付款表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_wallet_payment
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;
CREATE SCHEMA hxds_mis;
USE hxds_mis;
-- ----------------------------
-- Table structure for tb_action
-- ----------------------------
DROP TABLE IF EXISTS `tb_action`;
CREATE TABLE `tb_action`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `action_code` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '行为编号',
  `action_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '行为名称',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `unq_action_name`(`action_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '行为表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_action
-- ----------------------------
INSERT INTO `tb_action` VALUES (1, 'INSERT', '添加');
INSERT INTO `tb_action` VALUES (2, 'DELETE', '删除');
INSERT INTO `tb_action` VALUES (3, 'UPDATE', '修改');
INSERT INTO `tb_action` VALUES (4, 'SELECT', '查询');
INSERT INTO `tb_action` VALUES (5, 'APPROVAL', '审批');
INSERT INTO `tb_action` VALUES (6, 'EXPORT', '导出');
INSERT INTO `tb_action` VALUES (7, 'BACKUP', '备份');
INSERT INTO `tb_action` VALUES (8, 'ARCHIVE', '归档');

-- ----------------------------
-- Table structure for tb_dept
-- ----------------------------
DROP TABLE IF EXISTS `tb_dept`;
CREATE TABLE `tb_dept`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dept_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '部门名称',
  `tel` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门电话',
  `email` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门邮箱',
  `desc` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `unq_dept_name`(`dept_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 104 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_dept
-- ----------------------------
INSERT INTO `tb_dept` VALUES (1, '管理部', '010-12345678', 'manage@163.com', '管理部负责管理公司所有事务');
INSERT INTO `tb_dept` VALUES (2, '行政部', NULL, 'tech@163.com', NULL);
INSERT INTO `tb_dept` VALUES (3, '技术部', '010-12345678', 'tech@163.com', NULL);
INSERT INTO `tb_dept` VALUES (4, '市场部', NULL, 'tech@163.com', NULL);
INSERT INTO `tb_dept` VALUES (5, '后勤部', NULL, 'tech@163.com', NULL);
INSERT INTO `tb_dept` VALUES (6, '人事部', NULL, '106135489@qq.com', NULL);

-- ----------------------------
-- Table structure for tb_feedback
-- ----------------------------
DROP TABLE IF EXISTS `tb_feedback`;
CREATE TABLE `tb_feedback`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `customer_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '反馈者姓名',
  `customer_tel` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '反馈者电话',
  `user_id` bigint(20) NOT NULL COMMENT '接待员ID',
  `type` tinyint(4) NOT NULL COMMENT '反馈类型，1系统故障，2服务质量，3支付异常，4其他',
  `content` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '反馈内容',
  `status` tinyint(4) NOT NULL COMMENT '反馈状态，1未处理，2已处理',
  `result` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '处理结果',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_feedback
-- ----------------------------

-- ----------------------------
-- Table structure for tb_module
-- ----------------------------
DROP TABLE IF EXISTS `tb_module`;
CREATE TABLE `tb_module`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `module_code` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '模块编号',
  `module_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '模块名称',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `unq_module_id`(`module_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '模块资源表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_module
-- ----------------------------
INSERT INTO `tb_module` VALUES (1, 'USER', '用户模块');
INSERT INTO `tb_module` VALUES (2, 'DEPT', '部门模块');
INSERT INTO `tb_module` VALUES (3, 'ROLE', '角色模块');
INSERT INTO `tb_module` VALUES (4, 'DRIVER', '司机信息模块');
INSERT INTO `tb_module` VALUES (5, 'DRIVER_WALLET', '司机钱包模块');
INSERT INTO `tb_module` VALUES (6, 'DRIVER_FINE', '司机罚款模块');
INSERT INTO `tb_module` VALUES (7, 'DRIVER_LOCKDOWN', '司机禁止接单模块');
INSERT INTO `tb_module` VALUES (8, 'ORDER', '订单模块');
INSERT INTO `tb_module` VALUES (9, 'MONITORING', '安全监控模块');

-- ----------------------------
-- Table structure for tb_permission
-- ----------------------------
DROP TABLE IF EXISTS `tb_permission`;
CREATE TABLE `tb_permission`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `permission_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限',
  `module_id` int(10) UNSIGNED NOT NULL COMMENT '模块ID',
  `action_id` int(10) UNSIGNED NOT NULL COMMENT '行为ID',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `unq_permission`(`permission_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_permission
-- ----------------------------
INSERT INTO `tb_permission` VALUES (0, 'ROOT', 0, 0);
INSERT INTO `tb_permission` VALUES (1, 'USER:INSERT', 1, 1);
INSERT INTO `tb_permission` VALUES (2, 'USER:DELETE', 1, 2);
INSERT INTO `tb_permission` VALUES (3, 'USER:UPDATE', 1, 3);
INSERT INTO `tb_permission` VALUES (4, 'USER:SELECT', 1, 4);
INSERT INTO `tb_permission` VALUES (5, 'DEPT:INSERT', 2, 1);
INSERT INTO `tb_permission` VALUES (6, 'DEPT:DELETE', 2, 2);
INSERT INTO `tb_permission` VALUES (7, 'DEPT:UPDATE', 2, 3);
INSERT INTO `tb_permission` VALUES (8, 'DEPT:SELECT', 2, 4);
INSERT INTO `tb_permission` VALUES (9, 'ROLE:INSERT', 3, 1);
INSERT INTO `tb_permission` VALUES (10, 'ROLE:DELETE', 3, 2);
INSERT INTO `tb_permission` VALUES (11, 'ROLE:UPDATE', 3, 3);
INSERT INTO `tb_permission` VALUES (12, 'ROLE:SELECT', 3, 4);
INSERT INTO `tb_permission` VALUES (13, 'DRIVER:INSERT', 4, 1);
INSERT INTO `tb_permission` VALUES (14, 'DRIVER:DELETE', 4, 2);
INSERT INTO `tb_permission` VALUES (15, 'DRIVER:UPDATE', 4, 3);
INSERT INTO `tb_permission` VALUES (16, 'DRIVER:SELECT', 4, 4);
INSERT INTO `tb_permission` VALUES (17, 'DRIVER_WALLET:INSERT', 5, 1);
INSERT INTO `tb_permission` VALUES (18, 'DRIVER_WALLET:DELETE', 5, 2);
INSERT INTO `tb_permission` VALUES (19, 'DRIVER_WALLET:UPDATE', 5, 3);
INSERT INTO `tb_permission` VALUES (20, 'DRIVER_WALLET:SELECT', 5, 4);
INSERT INTO `tb_permission` VALUES (21, 'DRIVER_FINE:INSERT', 6, 1);
INSERT INTO `tb_permission` VALUES (22, 'DRIVER_FINE:DELETE', 6, 2);
INSERT INTO `tb_permission` VALUES (23, 'DRIVER_FINE:UPDATE', 6, 3);
INSERT INTO `tb_permission` VALUES (24, 'DRIVER_FINE:SELECT', 6, 4);
INSERT INTO `tb_permission` VALUES (25, 'DRIVER_LOCKDOWN:INSERT', 7, 1);
INSERT INTO `tb_permission` VALUES (26, 'DRIVER_LOCKDOWN:DELETE', 7, 2);
INSERT INTO `tb_permission` VALUES (27, 'DRIVER_LOCKDOWN:UPDATE', 7, 3);
INSERT INTO `tb_permission` VALUES (28, 'DRIVER_LOCKDOWN:SELECT', 7, 4);
INSERT INTO `tb_permission` VALUES (29, 'ORDER:INSERT', 8, 1);
INSERT INTO `tb_permission` VALUES (30, 'ORDER:DELETE', 8, 2);
INSERT INTO `tb_permission` VALUES (31, 'ORDER:UPDATE', 8, 3);
INSERT INTO `tb_permission` VALUES (32, 'ORDER:SELECT', 8, 4);
INSERT INTO `tb_permission` VALUES (33, 'MONITORING:INSERT', 9, 1);
INSERT INTO `tb_permission` VALUES (34, 'MONITORING:DELETE', 9, 2);
INSERT INTO `tb_permission` VALUES (35, 'MONITORING:UPDATE', 9, 3);
INSERT INTO `tb_permission` VALUES (36, 'MONITORING:SELECT', 9, 4);
UPDATE `tb_permission` SET id = 0 WHERE permission_name = "ROOT";
-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `permissions` json NOT NULL COMMENT '权限集合',
  `desc` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `default_permissions` json NULL COMMENT '系统角色内置权限',
  `systemic` tinyint(1) NULL DEFAULT 0 COMMENT '是否为系统内置角色',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `unq_role_name`(`role_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_role
-- ----------------------------
INSERT INTO `tb_role` VALUES (0, '超级管理员', '[0]', '超级管理员用户不能删除和修改', '[0]', 1);
INSERT INTO `tb_role` VALUES (1, '总经理', '[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 19, 20, 21, 22, 23, 24, 27]', NULL, '[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 27]', 1);
INSERT INTO `tb_role` VALUES (2, '部门经理', '[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 15, 16, 17, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36]', NULL, '[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 17, 27]', 1);
INSERT INTO `tb_role` VALUES (3, '普通员工', '[1, 2, 3, 4, 5, 6, 7, 8]', NULL, '[1, 2, 3, 4, 5, 6, 7, 8]', 1);
INSERT INTO `tb_role` VALUES (4, 'HR', '[1, 2, 3, 4, 5, 6, 7, 8, 28, 27]', NULL, '[1, 2, 3, 4, 5, 6, 7, 8, 28, 27]', 1);
INSERT INTO `tb_role` VALUES (5, '财务', '[1, 2, 3, 4, 5, 6, 7, 8, 17, 28, 36]', NULL, '[1, 2, 3, 4, 5, 6, 7, 8, 28, 36, 17]', 1);
UPDATE `tb_role` SET id = 0 WHERE role_name="超级管理员";

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码（AES加密）',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `sex` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '性别',
  `tel` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `email` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '邮箱',
  `role` json NOT NULL COMMENT '角色',
  `root` tinyint(4) NOT NULL COMMENT '是否为超级管理员',
  `dept_id` int(11) NOT NULL COMMENT '部门编号',
  `status` tinyint(4) NOT NULL COMMENT '1有效，2离职，3禁用',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `unq_root`(`root`) USING BTREE,
  INDEX `idx_username`(`username`) USING BTREE,
  INDEX `idx_status`(`status`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '管理系统用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (1, 'admin', 'e523a41fc563203575d9b07be9c84872', '管理员', '男', '13312345678', 'admin@163.com', '[0]', 1, 3, 1, '2022-01-29 18:44:31');

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
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单取消规则表' ROW_FORMAT = Dynamic;

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
-- Records of tb_charge_rule
-- ----------------------------

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
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '司机分账表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
