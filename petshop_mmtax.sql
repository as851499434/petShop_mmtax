/*
 Navicat Premium Data Transfer

 Source Server         : 梁凡的服务器
 Source Server Type    : MySQL
 Source Server Version : 50738
 Source Host           : 116.62.141.102:3306
 Source Schema         : petshop_mmtax

 Target Server Type    : MySQL
 Target Server Version : 50738
 File Encoding         : 65001

 Date: 27/08/2022 21:59:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `trigger_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `blob_data` blob NULL,
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_blob_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `calendar_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `calendar` blob NOT NULL,
  PRIMARY KEY (`sched_name`, `calendar_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_calendars
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `trigger_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `cron_expression` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `time_zone_id` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `entry_id` varchar(95) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `trigger_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `instance_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `fired_time` bigint(13) NOT NULL,
  `sched_time` bigint(13) NOT NULL,
  `priority` int(11) NOT NULL,
  `state` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `job_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `job_group` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `is_nonconcurrent` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `requests_recovery` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`sched_name`, `entry_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_fired_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `job_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `job_group` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `description` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `job_class_name` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `is_durable` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `is_nonconcurrent` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `is_update_data` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `requests_recovery` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `job_data` blob NULL,
  PRIMARY KEY (`sched_name`, `job_name`, `job_group`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `lock_name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`sched_name`, `lock_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------
INSERT INTO `qrtz_locks` VALUES ('mmciScheduler', 'STATE_ACCESS');
INSERT INTO `qrtz_locks` VALUES ('mmtaxScheduler', 'STATE_ACCESS');
INSERT INTO `qrtz_locks` VALUES ('mmtaxScheduler', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`sched_name`, `trigger_group`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_paused_trigger_grps
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `instance_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `last_checkin_time` bigint(13) NOT NULL,
  `checkin_interval` bigint(13) NOT NULL,
  PRIMARY KEY (`sched_name`, `instance_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------
INSERT INTO `qrtz_scheduler_state` VALUES ('mmtaxScheduler', 'iZbp155utcxcqhk037nzzhZ1657713238027', 1661608773854, 15000);

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `trigger_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `repeat_count` bigint(7) NOT NULL,
  `repeat_interval` bigint(12) NOT NULL,
  `times_triggered` bigint(10) NOT NULL,
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_simple_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `trigger_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `str_prop_1` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `str_prop_2` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `str_prop_3` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `int_prop_1` int(11) NULL DEFAULT NULL,
  `int_prop_2` int(11) NULL DEFAULT NULL,
  `long_prop_1` bigint(20) NULL DEFAULT NULL,
  `long_prop_2` bigint(20) NULL DEFAULT NULL,
  `dec_prop_1` decimal(13, 4) NULL DEFAULT NULL,
  `dec_prop_2` decimal(13, 4) NULL DEFAULT NULL,
  `bool_prop_1` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bool_prop_2` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_simprop_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `trigger_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `job_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `job_group` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `description` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `next_fire_time` bigint(13) NULL DEFAULT NULL,
  `prev_fire_time` bigint(13) NULL DEFAULT NULL,
  `priority` int(11) NULL DEFAULT NULL,
  `trigger_state` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `trigger_type` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `start_time` bigint(13) NOT NULL,
  `end_time` bigint(13) NULL DEFAULT NULL,
  `calendar_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `misfire_instr` smallint(2) NULL DEFAULT NULL,
  `job_data` blob NULL,
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  INDEX `sched_name`(`sched_name`, `job_name`, `job_group`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`  (
  `config_id` int(5) NOT NULL AUTO_INCREMENT COMMENT '参数主键',
  `config_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '参数名称',
  `config_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '参数键名',
  `config_value` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '参数键值',
  `config_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `provider_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`config_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '参数配置表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES (1, '主框架页-默认皮肤样式名称', 'sys.index.skinName', 'skin-blue', 'Y', 'admin', '2018-03-16 11:33:00', 'admin', '2021-04-09 09:53:17', '蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow', 1);
INSERT INTO `sys_config` VALUES (2, '用户管理-账号初始密码', 'sys.user.initPassword', '123456', 'Y', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '初始化密码 123456', 1);

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `dept_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `parent_id` bigint(20) NULL DEFAULT 0 COMMENT '父部门id',
  `ancestors` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '祖级列表',
  `dept_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '部门名称',
  `order_num` int(4) NULL DEFAULT 0 COMMENT '显示顺序',
  `leader` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '负责人',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `provider_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 109 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '部门表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (100, 0, '0', 'petshop', 0, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'admin', '2021-05-01 14:29:10', 1);
INSERT INTO `sys_dept` VALUES (101, 100, '0,100', '怀化宠物店', 1, '梁凡', '15888888888', 'lf@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'admin', '2021-05-01 14:29:10', 1);
INSERT INTO `sys_dept` VALUES (102, 100, '0,100', '长沙宠物分店', 2, '若依', '15888888888', 'ry@qq.com', '0', '2', 'admin', '2018-03-16 11:33:00', 'admin', '2021-04-05 15:03:40', 1);
INSERT INTO `sys_dept` VALUES (103, 101, '0,100,101', '管理部门', 1, '梁凡', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'admin', '2021-05-01 14:29:10', 1);
INSERT INTO `sys_dept` VALUES (105, 101, '0,100,101', '销售部门', 3, '梁凡', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'admin', '2021-04-27 14:41:07', 1);
INSERT INTO `sys_dept` VALUES (106, 101, '0,100,101', '医疗部门', 4, '梁凡', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'admin', '2021-05-01 14:29:06', 1);
INSERT INTO `sys_dept` VALUES (107, 101, '0,100,101', '运维部门', 5, '若依', '15888888888', 'ry@qq.com', '0', '2', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', 1);
INSERT INTO `sys_dept` VALUES (108, 102, '0,100,102', '市场部门', 1, '若依', '15888888888', 'ry@qq.com', '0', '2', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', 1);

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data`  (
  `dict_code` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  `dict_sort` int(4) NULL DEFAULT 0 COMMENT '字典排序',
  `dict_label` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '表格回显样式',
  `is_default` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `provider_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`dict_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 40 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典数据表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------
INSERT INTO `sys_dict_data` VALUES (1, 1, '男', '0', 'sys_user_sex', '', '', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '性别男', 1);
INSERT INTO `sys_dict_data` VALUES (2, 2, '女', '1', 'sys_user_sex', '', '', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '性别女', 1);
INSERT INTO `sys_dict_data` VALUES (3, 3, '未知', '2', 'sys_user_sex', '', '', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '性别未知', 1);
INSERT INTO `sys_dict_data` VALUES (4, 1, '显示', '0', 'sys_show_hide', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '显示菜单', 1);
INSERT INTO `sys_dict_data` VALUES (5, 2, '隐藏', '1', 'sys_show_hide', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '隐藏菜单', 1);
INSERT INTO `sys_dict_data` VALUES (6, 1, '正常', '0', 'sys_normal_disable', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '正常状态', 1);
INSERT INTO `sys_dict_data` VALUES (7, 2, '停用', '1', 'sys_normal_disable', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '停用状态', 1);
INSERT INTO `sys_dict_data` VALUES (8, 1, '正常', '0', 'sys_job_status', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '正常状态', 1);
INSERT INTO `sys_dict_data` VALUES (9, 2, '暂停', '1', 'sys_job_status', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '停用状态', 1);
INSERT INTO `sys_dict_data` VALUES (10, 1, '是', 'Y', 'sys_yes_no', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统默认是', 1);
INSERT INTO `sys_dict_data` VALUES (11, 2, '否', 'N', 'sys_yes_no', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统默认否', 1);
INSERT INTO `sys_dict_data` VALUES (12, 1, '通知', '1', 'sys_notice_type', '', 'warning', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '通知', 1);
INSERT INTO `sys_dict_data` VALUES (13, 2, '公告', '2', 'sys_notice_type', '', 'success', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '公告', 1);
INSERT INTO `sys_dict_data` VALUES (14, 1, '正常', '0', 'sys_notice_status', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '正常状态', 1);
INSERT INTO `sys_dict_data` VALUES (15, 2, '关闭', '1', 'sys_notice_status', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '关闭状态', 1);
INSERT INTO `sys_dict_data` VALUES (16, 1, '新增', '1', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '新增操作', 1);
INSERT INTO `sys_dict_data` VALUES (17, 2, '修改', '2', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '修改操作', 1);
INSERT INTO `sys_dict_data` VALUES (18, 3, '删除', '3', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '删除操作', 1);
INSERT INTO `sys_dict_data` VALUES (19, 4, '授权', '4', 'sys_oper_type', '', 'primary', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '授权操作', 1);
INSERT INTO `sys_dict_data` VALUES (20, 5, '导出', '5', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '导出操作', 1);
INSERT INTO `sys_dict_data` VALUES (21, 6, '导入', '6', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '导入操作', 1);
INSERT INTO `sys_dict_data` VALUES (22, 7, '强退', '7', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '强退操作', 1);
INSERT INTO `sys_dict_data` VALUES (23, 8, '生成代码', '8', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '生成操作', 1);
INSERT INTO `sys_dict_data` VALUES (24, 9, '清空数据', '9', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '清空操作', 1);
INSERT INTO `sys_dict_data` VALUES (25, 1, '成功', '0', 'sys_common_status', '', 'primary', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '正常状态', 1);
INSERT INTO `sys_dict_data` VALUES (26, 2, '失败', '1', 'sys_common_status', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '停用状态', 1);
INSERT INTO `sys_dict_data` VALUES (27, 1, '店养宠物', '4', 'pet_info_type', '', 'success', 'Y', '0', 'admin', '2021-04-10 15:03:41', 'admin', '2021-04-26 14:12:13', '', NULL);
INSERT INTO `sys_dict_data` VALUES (28, 2, '医疗宠物', '1', 'pet_info_type', '', 'warning', 'Y', '0', 'admin', '2021-04-10 15:04:00', 'admin', '2021-04-10 15:04:33', '', NULL);
INSERT INTO `sys_dict_data` VALUES (29, 3, '销售宠物', '2', 'pet_info_type', '', 'primary', 'N', '0', 'admin', '2021-04-10 15:04:30', 'admin', '2021-04-26 14:10:10', '', NULL);
INSERT INTO `sys_dict_data` VALUES (30, 4, '寄养宠物', '3', 'pet_info_type', NULL, 'info', 'Y', '0', 'admin', '2021-04-10 15:04:55', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (31, 1, '公', '0', 'pet_sex', NULL, 'primary', 'Y', '0', 'admin', '2021-04-10 15:05:51', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (32, 2, '母', '1', 'pet_sex', NULL, 'info', 'Y', '0', 'admin', '2021-04-10 15:06:06', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (33, 3, '未知', '2', 'pet_sex', '', 'warning', 'Y', '0', 'admin', '2021-04-10 15:31:20', 'admin', '2021-04-10 15:31:25', '', NULL);
INSERT INTO `sys_dict_data` VALUES (34, 1, '男', '0', 'master_sex', '', 'info', 'N', '0', 'admin', '2021-04-26 14:45:00', 'admin', '2021-04-26 14:52:21', '', NULL);
INSERT INTO `sys_dict_data` VALUES (35, 2, '女', '1', 'master_sex', NULL, 'primary', 'Y', '0', 'admin', '2021-04-26 14:45:12', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (36, 3, '未知', '2', 'master_sex', NULL, 'info', 'Y', '0', 'admin', '2021-04-26 14:45:23', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (37, 1, '是', '1', 'info_status', '', 'success', 'N', '0', 'admin', '2021-04-27 14:43:00', 'admin', '2021-04-27 14:54:54', '', NULL);
INSERT INTO `sys_dict_data` VALUES (38, 2, '否', '0', 'info_status', '', 'info', 'N', '0', 'admin', '2021-04-27 14:43:13', 'admin', '2021-04-27 14:54:50', '', NULL);
INSERT INTO `sys_dict_data` VALUES (39, 5, '宠物服务', '5', 'pet_info_type', '', 'danger', 'N', '0', 'admin', '2021-04-28 16:38:56', 'admin', '2021-04-28 16:53:35', '', NULL);

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type`  (
  `dict_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典主键',
  `dict_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典名称',
  `dict_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `provider_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`dict_id`) USING BTREE,
  UNIQUE INDEX `dict_type`(`dict_type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典类型表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
INSERT INTO `sys_dict_type` VALUES (1, '用户性别', 'sys_user_sex', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '用户性别列表', 1);
INSERT INTO `sys_dict_type` VALUES (2, '菜单状态', 'sys_show_hide', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '菜单状态列表', 1);
INSERT INTO `sys_dict_type` VALUES (3, '系统开关', 'sys_normal_disable', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统开关列表', 1);
INSERT INTO `sys_dict_type` VALUES (4, '任务状态', 'sys_job_status', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '任务状态列表', 1);
INSERT INTO `sys_dict_type` VALUES (5, '系统是否', 'sys_yes_no', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统是否列表', 1);
INSERT INTO `sys_dict_type` VALUES (6, '通知类型', 'sys_notice_type', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '通知类型列表', 1);
INSERT INTO `sys_dict_type` VALUES (7, '通知状态', 'sys_notice_status', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '通知状态列表', 1);
INSERT INTO `sys_dict_type` VALUES (8, '操作类型', 'sys_oper_type', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '操作类型列表', 1);
INSERT INTO `sys_dict_type` VALUES (9, '系统状态', 'sys_common_status', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '登录状态列表', 1);
INSERT INTO `sys_dict_type` VALUES (10, '宠物信息类型', 'pet_info_type', '0', 'admin', '2021-04-10 15:02:32', '', NULL, '宠物信息类型 0 店养宠物 1 医疗宠物 2 销售宠物 3寄养宠物', NULL);
INSERT INTO `sys_dict_type` VALUES (11, '宠物性别', 'pet_sex', '0', 'admin', '2021-04-10 15:05:30', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (12, '主人性别', 'master_sex', '0', 'admin', '2021-04-26 14:44:37', 'admin', '2021-04-26 14:56:46', '', NULL);
INSERT INTO `sys_dict_type` VALUES (13, '是否状态', 'info_status', '0', 'admin', '2021-04-27 14:42:39', '', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_job`;
CREATE TABLE `sys_job`  (
  `job_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务ID',
  `job_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '任务名称',
  `job_group` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '任务组名',
  `invoke_target` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '调用目标字符串',
  `cron_expression` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT 'cron执行表达式',
  `misfire_policy` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '3' COMMENT '计划执行错误策略（1立即执行 2执行一次 3放弃执行）',
  `concurrent` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '是否并发执行（0允许 1禁止）',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1暂停）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '备注信息',
  `provider_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`job_id`, `job_name`, `job_group`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '定时任务调度表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_job
-- ----------------------------

-- ----------------------------
-- Table structure for sys_job_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_job_log`;
CREATE TABLE `sys_job_log`  (
  `job_log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务日志ID',
  `job_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务名称',
  `job_group` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务组名',
  `invoke_target` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '调用目标字符串',
  `job_message` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '日志信息',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '执行状态（0正常 1失败）',
  `exception_info` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '异常信息',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `provider_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`job_log_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '定时任务调度日志表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_job_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_logininfor
-- ----------------------------
DROP TABLE IF EXISTS `sys_logininfor`;
CREATE TABLE `sys_logininfor`  (
  `info_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '访问ID',
  `login_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '登录账号',
  `ipaddr` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '操作系统',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
  `msg` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '提示消息',
  `login_time` datetime(0) NULL DEFAULT NULL COMMENT '访问时间',
  `provider_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`info_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8241 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统访问记录' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_logininfor
-- ----------------------------
INSERT INTO `sys_logininfor` VALUES (7657, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '退出成功', '2021-04-05 14:53:37', 1);
INSERT INTO `sys_logininfor` VALUES (7658, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-05 14:53:38', 1);
INSERT INTO `sys_logininfor` VALUES (7659, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-05 14:57:43', 1);
INSERT INTO `sys_logininfor` VALUES (7660, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-05 16:25:18', 1);
INSERT INTO `sys_logininfor` VALUES (7661, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-05 16:31:54', 1);
INSERT INTO `sys_logininfor` VALUES (7662, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-05 16:34:42', 1);
INSERT INTO `sys_logininfor` VALUES (7663, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-05 21:02:50', 1);
INSERT INTO `sys_logininfor` VALUES (7664, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-05 21:15:30', 1);
INSERT INTO `sys_logininfor` VALUES (7665, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-05 21:23:25', 1);
INSERT INTO `sys_logininfor` VALUES (7666, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-05 22:02:44', 1);
INSERT INTO `sys_logininfor` VALUES (7667, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-06 08:45:14', 1);
INSERT INTO `sys_logininfor` VALUES (7668, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-06 09:51:38', 1);
INSERT INTO `sys_logininfor` VALUES (7669, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-06 10:02:25', 1);
INSERT INTO `sys_logininfor` VALUES (7670, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2021-04-06 10:13:15', 1);
INSERT INTO `sys_logininfor` VALUES (7671, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2021-04-06 10:24:40', 1);
INSERT INTO `sys_logininfor` VALUES (7672, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2021-04-06 10:25:30', 1);
INSERT INTO `sys_logininfor` VALUES (7673, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2021-04-06 10:28:09', 1);
INSERT INTO `sys_logininfor` VALUES (7674, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2021-04-06 10:31:34', 1);
INSERT INTO `sys_logininfor` VALUES (7675, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2021-04-06 10:32:30', 1);
INSERT INTO `sys_logininfor` VALUES (7676, '13107280912', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2021-04-06 10:32:35', 1);
INSERT INTO `sys_logininfor` VALUES (7677, '13107280912', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2021-04-06 10:33:14', 1);
INSERT INTO `sys_logininfor` VALUES (7678, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2021-04-06 10:33:15', 1);
INSERT INTO `sys_logininfor` VALUES (7679, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2021-04-06 10:49:05', 1);
INSERT INTO `sys_logininfor` VALUES (7680, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2021-04-06 10:49:49', 1);
INSERT INTO `sys_logininfor` VALUES (7681, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2021-04-06 10:49:55', 1);
INSERT INTO `sys_logininfor` VALUES (7682, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码错误', '2021-04-06 10:49:58', 1);
INSERT INTO `sys_logininfor` VALUES (7683, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码错误', '2021-04-06 10:49:59', 1);
INSERT INTO `sys_logininfor` VALUES (7684, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码错误', '2021-04-06 10:50:02', 1);
INSERT INTO `sys_logininfor` VALUES (7685, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码错误', '2021-04-06 10:50:03', 1);
INSERT INTO `sys_logininfor` VALUES (7686, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2021-04-06 10:50:08', 1);
INSERT INTO `sys_logininfor` VALUES (7687, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2021-04-06 20:06:42', 1);
INSERT INTO `sys_logininfor` VALUES (7688, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2021-04-06 20:28:07', 1);
INSERT INTO `sys_logininfor` VALUES (7689, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2021-04-06 20:28:32', 1);
INSERT INTO `sys_logininfor` VALUES (7690, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2021-04-06 20:28:39', 1);
INSERT INTO `sys_logininfor` VALUES (7691, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2021-04-06 20:28:40', 1);
INSERT INTO `sys_logininfor` VALUES (7692, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2021-04-06 20:33:11', 1);
INSERT INTO `sys_logininfor` VALUES (7693, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2021-04-07 08:26:03', 1);
INSERT INTO `sys_logininfor` VALUES (7694, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2021-04-07 08:53:52', 1);
INSERT INTO `sys_logininfor` VALUES (7695, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-08 22:17:58', 1);
INSERT INTO `sys_logininfor` VALUES (7696, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-09 09:32:15', 1);
INSERT INTO `sys_logininfor` VALUES (7697, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-09 11:02:22', 1);
INSERT INTO `sys_logininfor` VALUES (7698, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2021-04-10 11:34:49', 1);
INSERT INTO `sys_logininfor` VALUES (7699, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2021-04-10 11:54:23', 1);
INSERT INTO `sys_logininfor` VALUES (7700, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2021-04-10 14:07:31', 1);
INSERT INTO `sys_logininfor` VALUES (7701, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2021-04-10 14:10:13', 1);
INSERT INTO `sys_logininfor` VALUES (7702, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2021-04-10 14:22:18', 1);
INSERT INTO `sys_logininfor` VALUES (7703, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2021-04-10 14:23:11', 1);
INSERT INTO `sys_logininfor` VALUES (7704, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2021-04-10 14:24:48', 1);
INSERT INTO `sys_logininfor` VALUES (7705, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-10 14:29:09', 1);
INSERT INTO `sys_logininfor` VALUES (7706, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-10 14:39:00', 1);
INSERT INTO `sys_logininfor` VALUES (7707, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-10 14:39:36', 1);
INSERT INTO `sys_logininfor` VALUES (7708, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2021-04-10 14:42:39', 1);
INSERT INTO `sys_logininfor` VALUES (7709, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2021-04-10 14:45:18', 1);
INSERT INTO `sys_logininfor` VALUES (7710, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2021-04-10 14:46:59', 1);
INSERT INTO `sys_logininfor` VALUES (7711, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2021-04-10 14:51:15', 1);
INSERT INTO `sys_logininfor` VALUES (7712, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2021-04-10 14:55:02', 1);
INSERT INTO `sys_logininfor` VALUES (7713, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2021-04-10 14:57:49', 1);
INSERT INTO `sys_logininfor` VALUES (7714, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2021-04-10 15:01:33', 1);
INSERT INTO `sys_logininfor` VALUES (7715, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2021-04-10 15:10:24', 1);
INSERT INTO `sys_logininfor` VALUES (7716, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-10 15:12:09', 1);
INSERT INTO `sys_logininfor` VALUES (7717, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2021-04-10 15:18:22', 1);
INSERT INTO `sys_logininfor` VALUES (7718, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2021-04-10 15:19:29', 1);
INSERT INTO `sys_logininfor` VALUES (7719, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2021-04-10 15:28:48', 1);
INSERT INTO `sys_logininfor` VALUES (7720, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2021-04-10 15:29:54', 1);
INSERT INTO `sys_logininfor` VALUES (7721, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2021-04-10 15:31:43', 1);
INSERT INTO `sys_logininfor` VALUES (7722, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2021-04-10 15:32:38', 1);
INSERT INTO `sys_logininfor` VALUES (7723, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2021-04-10 15:33:28', 1);
INSERT INTO `sys_logininfor` VALUES (7724, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-10 17:11:22', 1);
INSERT INTO `sys_logininfor` VALUES (7725, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-10 17:32:01', 1);
INSERT INTO `sys_logininfor` VALUES (7726, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-10 17:51:34', 1);
INSERT INTO `sys_logininfor` VALUES (7727, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-10 18:00:02', 1);
INSERT INTO `sys_logininfor` VALUES (7728, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-10 18:03:41', 1);
INSERT INTO `sys_logininfor` VALUES (7729, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-10 18:04:37', 1);
INSERT INTO `sys_logininfor` VALUES (7730, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-10 18:05:13', 1);
INSERT INTO `sys_logininfor` VALUES (7731, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-10 18:10:05', 1);
INSERT INTO `sys_logininfor` VALUES (7732, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-10 18:19:33', 1);
INSERT INTO `sys_logininfor` VALUES (7733, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2021-04-10 21:02:12', 1);
INSERT INTO `sys_logininfor` VALUES (7734, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2021-04-10 21:09:08', 1);
INSERT INTO `sys_logininfor` VALUES (7735, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2021-04-10 21:10:38', 1);
INSERT INTO `sys_logininfor` VALUES (7736, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2021-04-10 21:18:44', 1);
INSERT INTO `sys_logininfor` VALUES (7737, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2021-04-10 21:20:51', 1);
INSERT INTO `sys_logininfor` VALUES (7738, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2021-04-10 21:22:30', 1);
INSERT INTO `sys_logininfor` VALUES (7739, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2021-04-10 22:15:56', 1);
INSERT INTO `sys_logininfor` VALUES (7740, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-10 22:56:43', 1);
INSERT INTO `sys_logininfor` VALUES (7741, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-10 22:57:58', 1);
INSERT INTO `sys_logininfor` VALUES (7742, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-10 22:59:23', 1);
INSERT INTO `sys_logininfor` VALUES (7743, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-12 19:03:40', 1);
INSERT INTO `sys_logininfor` VALUES (7744, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-12 19:07:47', 1);
INSERT INTO `sys_logininfor` VALUES (7745, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-12 19:10:52', 1);
INSERT INTO `sys_logininfor` VALUES (7746, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-12 19:12:00', 1);
INSERT INTO `sys_logininfor` VALUES (7747, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-12 19:13:12', 1);
INSERT INTO `sys_logininfor` VALUES (7748, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-12 19:16:48', 1);
INSERT INTO `sys_logininfor` VALUES (7749, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-12 19:17:52', 1);
INSERT INTO `sys_logininfor` VALUES (7750, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-12 19:24:29', 1);
INSERT INTO `sys_logininfor` VALUES (7751, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-12 19:25:51', 1);
INSERT INTO `sys_logininfor` VALUES (7752, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-12 19:27:06', 1);
INSERT INTO `sys_logininfor` VALUES (7753, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-21 20:12:32', 1);
INSERT INTO `sys_logininfor` VALUES (7754, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-26 00:33:08', 1);
INSERT INTO `sys_logininfor` VALUES (7755, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-04-26 13:38:48', 1);
INSERT INTO `sys_logininfor` VALUES (7756, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-04-26 13:54:40', 1);
INSERT INTO `sys_logininfor` VALUES (7757, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-04-26 13:56:19', 1);
INSERT INTO `sys_logininfor` VALUES (7758, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-04-26 13:59:15', 1);
INSERT INTO `sys_logininfor` VALUES (7759, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-04-26 14:02:06', 1);
INSERT INTO `sys_logininfor` VALUES (7760, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-04-26 14:06:34', 1);
INSERT INTO `sys_logininfor` VALUES (7761, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-04-26 14:22:08', 1);
INSERT INTO `sys_logininfor` VALUES (7762, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-04-26 14:22:52', 1);
INSERT INTO `sys_logininfor` VALUES (7763, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-04-26 14:23:21', 1);
INSERT INTO `sys_logininfor` VALUES (7764, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-04-26 14:25:08', 1);
INSERT INTO `sys_logininfor` VALUES (7765, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-04-26 14:26:19', 1);
INSERT INTO `sys_logininfor` VALUES (7766, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-04-26 14:36:06', 1);
INSERT INTO `sys_logininfor` VALUES (7767, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-04-26 14:40:33', 1);
INSERT INTO `sys_logininfor` VALUES (7768, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-04-26 14:49:49', 1);
INSERT INTO `sys_logininfor` VALUES (7769, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-04-26 14:51:58', 1);
INSERT INTO `sys_logininfor` VALUES (7770, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-04-26 14:58:14', 1);
INSERT INTO `sys_logininfor` VALUES (7771, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-04-26 14:59:45', 1);
INSERT INTO `sys_logininfor` VALUES (7772, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-04-26 15:00:30', 1);
INSERT INTO `sys_logininfor` VALUES (7773, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-04-26 15:02:55', 1);
INSERT INTO `sys_logininfor` VALUES (7774, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-04-26 15:04:33', 1);
INSERT INTO `sys_logininfor` VALUES (7775, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-04-26 15:10:59', 1);
INSERT INTO `sys_logininfor` VALUES (7776, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-04-26 15:11:44', 1);
INSERT INTO `sys_logininfor` VALUES (7777, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-04-26 15:15:06', 1);
INSERT INTO `sys_logininfor` VALUES (7778, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-04-26 15:16:12', 1);
INSERT INTO `sys_logininfor` VALUES (7779, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-04-26 19:37:17', 1);
INSERT INTO `sys_logininfor` VALUES (7780, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-04-26 20:40:46', 1);
INSERT INTO `sys_logininfor` VALUES (7781, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-04-26 20:57:42', 1);
INSERT INTO `sys_logininfor` VALUES (7782, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-26 21:00:00', 1);
INSERT INTO `sys_logininfor` VALUES (7783, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-26 21:01:24', 1);
INSERT INTO `sys_logininfor` VALUES (7784, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-26 21:04:45', 1);
INSERT INTO `sys_logininfor` VALUES (7785, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-26 21:05:32', 1);
INSERT INTO `sys_logininfor` VALUES (7786, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-26 21:06:57', 1);
INSERT INTO `sys_logininfor` VALUES (7787, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-26 21:23:15', 1);
INSERT INTO `sys_logininfor` VALUES (7788, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-26 21:26:34', 1);
INSERT INTO `sys_logininfor` VALUES (7789, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-26 21:30:50', 1);
INSERT INTO `sys_logininfor` VALUES (7790, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-26 21:34:42', 1);
INSERT INTO `sys_logininfor` VALUES (7791, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-26 21:38:17', 1);
INSERT INTO `sys_logininfor` VALUES (7792, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-26 21:39:27', 1);
INSERT INTO `sys_logininfor` VALUES (7793, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-27 13:33:15', 1);
INSERT INTO `sys_logininfor` VALUES (7794, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-27 14:13:13', 1);
INSERT INTO `sys_logininfor` VALUES (7795, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-27 14:16:58', 1);
INSERT INTO `sys_logininfor` VALUES (7796, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-27 14:46:46', 1);
INSERT INTO `sys_logininfor` VALUES (7797, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-27 14:51:48', 1);
INSERT INTO `sys_logininfor` VALUES (7798, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-27 14:53:31', 1);
INSERT INTO `sys_logininfor` VALUES (7799, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-27 14:54:29', 1);
INSERT INTO `sys_logininfor` VALUES (7800, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-27 14:57:11', 1);
INSERT INTO `sys_logininfor` VALUES (7801, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-27 14:58:34', 1);
INSERT INTO `sys_logininfor` VALUES (7802, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-27 15:00:54', 1);
INSERT INTO `sys_logininfor` VALUES (7803, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-27 15:02:20', 1);
INSERT INTO `sys_logininfor` VALUES (7804, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-27 15:02:48', 1);
INSERT INTO `sys_logininfor` VALUES (7805, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-27 18:29:35', 1);
INSERT INTO `sys_logininfor` VALUES (7806, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-27 20:08:48', 1);
INSERT INTO `sys_logininfor` VALUES (7807, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-27 20:11:01', 1);
INSERT INTO `sys_logininfor` VALUES (7808, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-27 20:26:29', 1);
INSERT INTO `sys_logininfor` VALUES (7809, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-27 20:26:46', 1);
INSERT INTO `sys_logininfor` VALUES (7810, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-27 20:28:50', 1);
INSERT INTO `sys_logininfor` VALUES (7811, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-27 20:30:11', 1);
INSERT INTO `sys_logininfor` VALUES (7812, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-27 20:41:17', 1);
INSERT INTO `sys_logininfor` VALUES (7813, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-27 21:10:18', 1);
INSERT INTO `sys_logininfor` VALUES (7814, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-27 21:11:36', 1);
INSERT INTO `sys_logininfor` VALUES (7815, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-27 21:13:21', 1);
INSERT INTO `sys_logininfor` VALUES (7816, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-27 21:14:40', 1);
INSERT INTO `sys_logininfor` VALUES (7817, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-27 21:16:25', 1);
INSERT INTO `sys_logininfor` VALUES (7818, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-27 21:18:03', 1);
INSERT INTO `sys_logininfor` VALUES (7819, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-28 15:53:30', 1);
INSERT INTO `sys_logininfor` VALUES (7820, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-28 16:30:19', 1);
INSERT INTO `sys_logininfor` VALUES (7821, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-28 16:52:00', 1);
INSERT INTO `sys_logininfor` VALUES (7822, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-28 17:02:12', 1);
INSERT INTO `sys_logininfor` VALUES (7823, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-29 00:58:36', 1);
INSERT INTO `sys_logininfor` VALUES (7824, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-29 01:28:56', 1);
INSERT INTO `sys_logininfor` VALUES (7825, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-29 01:30:02', 1);
INSERT INTO `sys_logininfor` VALUES (7826, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-29 01:34:52', 1);
INSERT INTO `sys_logininfor` VALUES (7827, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-29 01:36:48', 1);
INSERT INTO `sys_logininfor` VALUES (7828, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-29 01:40:03', 1);
INSERT INTO `sys_logininfor` VALUES (7829, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-29 01:56:24', 1);
INSERT INTO `sys_logininfor` VALUES (7830, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-29 01:58:53', 1);
INSERT INTO `sys_logininfor` VALUES (7831, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-29 02:03:19', 1);
INSERT INTO `sys_logininfor` VALUES (7832, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-29 02:05:17', 1);
INSERT INTO `sys_logininfor` VALUES (7833, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-29 02:08:56', 1);
INSERT INTO `sys_logininfor` VALUES (7834, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-29 02:10:43', 1);
INSERT INTO `sys_logininfor` VALUES (7835, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-29 02:11:28', 1);
INSERT INTO `sys_logininfor` VALUES (7836, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-29 02:13:55', 1);
INSERT INTO `sys_logininfor` VALUES (7837, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-29 02:15:31', 1);
INSERT INTO `sys_logininfor` VALUES (7838, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-29 02:19:09', 1);
INSERT INTO `sys_logininfor` VALUES (7839, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-29 02:20:07', 1);
INSERT INTO `sys_logininfor` VALUES (7840, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-29 02:24:36', 1);
INSERT INTO `sys_logininfor` VALUES (7841, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-29 02:27:27', 1);
INSERT INTO `sys_logininfor` VALUES (7842, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '退出成功', '2021-04-29 02:27:52', 1);
INSERT INTO `sys_logininfor` VALUES (7843, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-29 02:31:49', 1);
INSERT INTO `sys_logininfor` VALUES (7844, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-29 02:33:13', 1);
INSERT INTO `sys_logininfor` VALUES (7845, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '退出成功', '2021-04-29 02:34:52', 1);
INSERT INTO `sys_logininfor` VALUES (7846, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-29 02:36:18', 1);
INSERT INTO `sys_logininfor` VALUES (7847, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-29 02:39:09', 1);
INSERT INTO `sys_logininfor` VALUES (7848, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-04-29 02:40:18', 1);
INSERT INTO `sys_logininfor` VALUES (7849, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-29 02:43:22', 1);
INSERT INTO `sys_logininfor` VALUES (7850, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-29 02:44:23', 1);
INSERT INTO `sys_logininfor` VALUES (7851, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-29 14:54:46', 1);
INSERT INTO `sys_logininfor` VALUES (7852, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-29 14:58:06', 1);
INSERT INTO `sys_logininfor` VALUES (7853, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-29 15:00:57', 1);
INSERT INTO `sys_logininfor` VALUES (7854, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-29 15:01:36', 1);
INSERT INTO `sys_logininfor` VALUES (7855, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-29 15:05:58', 1);
INSERT INTO `sys_logininfor` VALUES (7856, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-29 15:06:35', 1);
INSERT INTO `sys_logininfor` VALUES (7857, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-29 15:07:30', 1);
INSERT INTO `sys_logininfor` VALUES (7858, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '退出成功', '2021-04-29 15:16:23', 1);
INSERT INTO `sys_logininfor` VALUES (7859, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-29 15:16:34', 1);
INSERT INTO `sys_logininfor` VALUES (7860, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-29 15:33:24', 1);
INSERT INTO `sys_logininfor` VALUES (7861, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-29 15:53:57', 1);
INSERT INTO `sys_logininfor` VALUES (7862, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-29 15:55:15', 1);
INSERT INTO `sys_logininfor` VALUES (7863, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-29 15:57:34', 1);
INSERT INTO `sys_logininfor` VALUES (7864, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-29 15:58:05', 1);
INSERT INTO `sys_logininfor` VALUES (7865, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-29 15:59:19', 1);
INSERT INTO `sys_logininfor` VALUES (7866, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-29 16:00:18', 1);
INSERT INTO `sys_logininfor` VALUES (7867, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-29 16:12:02', 1);
INSERT INTO `sys_logininfor` VALUES (7868, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-29 16:13:09', 1);
INSERT INTO `sys_logininfor` VALUES (7869, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-29 16:13:47', 1);
INSERT INTO `sys_logininfor` VALUES (7870, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-29 16:16:38', 1);
INSERT INTO `sys_logininfor` VALUES (7871, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-29 16:18:19', 1);
INSERT INTO `sys_logininfor` VALUES (7872, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-29 16:26:24', 1);
INSERT INTO `sys_logininfor` VALUES (7873, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-29 16:27:02', 1);
INSERT INTO `sys_logininfor` VALUES (7874, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-29 16:31:01', 1);
INSERT INTO `sys_logininfor` VALUES (7875, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-29 16:37:50', 1);
INSERT INTO `sys_logininfor` VALUES (7876, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-29 16:39:29', 1);
INSERT INTO `sys_logininfor` VALUES (7877, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-29 16:44:44', 1);
INSERT INTO `sys_logininfor` VALUES (7878, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-29 16:45:32', 1);
INSERT INTO `sys_logininfor` VALUES (7879, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-29 16:47:11', 1);
INSERT INTO `sys_logininfor` VALUES (7880, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-29 16:48:20', 1);
INSERT INTO `sys_logininfor` VALUES (7881, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-29 16:49:10', 1);
INSERT INTO `sys_logininfor` VALUES (7882, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-29 16:50:51', 1);
INSERT INTO `sys_logininfor` VALUES (7883, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-29 18:38:02', 1);
INSERT INTO `sys_logininfor` VALUES (7884, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-29 18:39:36', 1);
INSERT INTO `sys_logininfor` VALUES (7885, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-29 18:45:57', 1);
INSERT INTO `sys_logininfor` VALUES (7886, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-29 18:47:36', 1);
INSERT INTO `sys_logininfor` VALUES (7887, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-29 18:53:09', 1);
INSERT INTO `sys_logininfor` VALUES (7888, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-29 19:29:36', 1);
INSERT INTO `sys_logininfor` VALUES (7889, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-29 19:32:55', 1);
INSERT INTO `sys_logininfor` VALUES (7890, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-29 20:11:19', 1);
INSERT INTO `sys_logininfor` VALUES (7891, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-29 20:13:52', 1);
INSERT INTO `sys_logininfor` VALUES (7892, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-29 21:41:09', 1);
INSERT INTO `sys_logininfor` VALUES (7893, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-29 21:43:27', 1);
INSERT INTO `sys_logininfor` VALUES (7894, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-29 22:17:42', 1);
INSERT INTO `sys_logininfor` VALUES (7895, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-29 22:49:38', 1);
INSERT INTO `sys_logininfor` VALUES (7896, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-29 22:52:26', 1);
INSERT INTO `sys_logininfor` VALUES (7897, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-29 23:18:23', 1);
INSERT INTO `sys_logininfor` VALUES (7898, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-29 23:35:17', 1);
INSERT INTO `sys_logininfor` VALUES (7899, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-29 23:38:53', 1);
INSERT INTO `sys_logininfor` VALUES (7900, 'admin', '112.10.237.146', 'XX XX', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-29 23:57:07', 1);
INSERT INTO `sys_logininfor` VALUES (7901, 'admin', '112.10.237.146', 'XX XX', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-30 00:18:17', 1);
INSERT INTO `sys_logininfor` VALUES (7902, 'admin', '112.10.249.25', 'XX XX', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-04-30 00:19:32', 1);
INSERT INTO `sys_logininfor` VALUES (7903, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-30 00:20:01', 1);
INSERT INTO `sys_logininfor` VALUES (7904, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-30 00:21:29', 1);
INSERT INTO `sys_logininfor` VALUES (7905, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-30 00:22:33', 1);
INSERT INTO `sys_logininfor` VALUES (7906, 'admin', '112.10.249.25', 'XX XX', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-04-30 00:25:43', 1);
INSERT INTO `sys_logininfor` VALUES (7907, 'admin', '112.10.237.146', 'XX XX', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-30 00:27:25', 1);
INSERT INTO `sys_logininfor` VALUES (7908, 'admin', '112.10.237.146', 'XX XX', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-30 00:35:15', 1);
INSERT INTO `sys_logininfor` VALUES (7909, 'admin', '112.10.237.146', 'XX XX', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-30 00:39:53', 1);
INSERT INTO `sys_logininfor` VALUES (7910, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-30 00:41:24', 1);
INSERT INTO `sys_logininfor` VALUES (7911, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-30 00:43:18', 1);
INSERT INTO `sys_logininfor` VALUES (7912, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-30 00:43:52', 1);
INSERT INTO `sys_logininfor` VALUES (7913, 'admin', '112.10.249.25', 'XX XX', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-04-30 00:45:24', 1);
INSERT INTO `sys_logininfor` VALUES (7914, 'admin', '112.10.237.146', 'XX XX', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-30 00:52:29', 1);
INSERT INTO `sys_logininfor` VALUES (7915, 'admin', '112.10.237.146', 'XX XX', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-30 00:57:31', 1);
INSERT INTO `sys_logininfor` VALUES (7916, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-30 01:11:05', 1);
INSERT INTO `sys_logininfor` VALUES (7917, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-30 01:15:56', 1);
INSERT INTO `sys_logininfor` VALUES (7918, 'admin', '115.196.93.43', 'XX XX', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-04-30 01:19:53', 1);
INSERT INTO `sys_logininfor` VALUES (7919, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-30 01:23:47', 1);
INSERT INTO `sys_logininfor` VALUES (7920, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-30 01:24:37', 1);
INSERT INTO `sys_logininfor` VALUES (7921, 'admin', '112.10.237.146', 'XX XX', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-30 01:27:18', 1);
INSERT INTO `sys_logininfor` VALUES (7922, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-30 01:30:02', 1);
INSERT INTO `sys_logininfor` VALUES (7923, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-30 01:30:42', 1);
INSERT INTO `sys_logininfor` VALUES (7924, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-30 01:31:36', 1);
INSERT INTO `sys_logininfor` VALUES (7925, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-30 01:32:38', 1);
INSERT INTO `sys_logininfor` VALUES (7926, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-30 01:33:23', 1);
INSERT INTO `sys_logininfor` VALUES (7927, 'admin', '112.10.237.146', 'XX XX', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-30 01:36:00', 1);
INSERT INTO `sys_logininfor` VALUES (7928, 'admin', '112.10.237.146', 'XX XX', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-30 12:38:41', 1);
INSERT INTO `sys_logininfor` VALUES (7929, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-30 12:45:09', 1);
INSERT INTO `sys_logininfor` VALUES (7930, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-30 12:46:48', 1);
INSERT INTO `sys_logininfor` VALUES (7931, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-30 12:58:46', 1);
INSERT INTO `sys_logininfor` VALUES (7932, 'admin', '112.10.237.146', 'XX XX', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-30 13:05:00', 1);
INSERT INTO `sys_logininfor` VALUES (7933, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-30 13:11:48', 1);
INSERT INTO `sys_logininfor` VALUES (7934, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-30 13:18:27', 1);
INSERT INTO `sys_logininfor` VALUES (7935, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-30 13:26:54', 1);
INSERT INTO `sys_logininfor` VALUES (7936, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-30 13:29:19', 1);
INSERT INTO `sys_logininfor` VALUES (7937, 'admin', '112.10.237.146', 'XX XX', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-30 13:32:06', 1);
INSERT INTO `sys_logininfor` VALUES (7938, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-30 15:48:14', 1);
INSERT INTO `sys_logininfor` VALUES (7939, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-30 16:48:57', 1);
INSERT INTO `sys_logininfor` VALUES (7940, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '退出成功', '2021-04-30 17:18:56', 1);
INSERT INTO `sys_logininfor` VALUES (7941, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-30 17:19:15', 1);
INSERT INTO `sys_logininfor` VALUES (7942, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '退出成功', '2021-04-30 17:19:18', 1);
INSERT INTO `sys_logininfor` VALUES (7943, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-30 17:19:21', 1);
INSERT INTO `sys_logininfor` VALUES (7944, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '退出成功', '2021-04-30 17:19:36', 1);
INSERT INTO `sys_logininfor` VALUES (7945, '13107280912', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-30 17:19:40', 1);
INSERT INTO `sys_logininfor` VALUES (7946, '13107280912', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '退出成功', '2021-04-30 17:19:48', 1);
INSERT INTO `sys_logininfor` VALUES (7947, '13107280912', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-30 17:19:52', 1);
INSERT INTO `sys_logininfor` VALUES (7948, '13107280912', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '退出成功', '2021-04-30 17:20:01', 1);
INSERT INTO `sys_logininfor` VALUES (7949, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-30 17:20:02', 1);
INSERT INTO `sys_logininfor` VALUES (7950, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-30 17:20:55', 1);
INSERT INTO `sys_logininfor` VALUES (7951, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '退出成功', '2021-04-30 17:21:16', 1);
INSERT INTO `sys_logininfor` VALUES (7952, '13107280912', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-30 17:21:20', 1);
INSERT INTO `sys_logininfor` VALUES (7953, '13107280912', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '退出成功', '2021-04-30 17:21:37', 1);
INSERT INTO `sys_logininfor` VALUES (7954, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-30 17:21:38', 1);
INSERT INTO `sys_logininfor` VALUES (7955, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '退出成功', '2021-04-30 17:22:15', 1);
INSERT INTO `sys_logininfor` VALUES (7956, '13107280912', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-30 17:22:17', 1);
INSERT INTO `sys_logininfor` VALUES (7957, '13107280912', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '退出成功', '2021-04-30 17:23:03', 1);
INSERT INTO `sys_logininfor` VALUES (7958, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-30 17:23:04', 1);
INSERT INTO `sys_logininfor` VALUES (7959, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '退出成功', '2021-04-30 17:23:51', 1);
INSERT INTO `sys_logininfor` VALUES (7960, '13107280912', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-30 17:23:53', 1);
INSERT INTO `sys_logininfor` VALUES (7961, '13107280912', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '退出成功', '2021-04-30 17:24:00', 1);
INSERT INTO `sys_logininfor` VALUES (7962, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-30 17:24:01', 1);
INSERT INTO `sys_logininfor` VALUES (7963, '13107280912', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-30 17:24:20', 1);
INSERT INTO `sys_logininfor` VALUES (7964, '13107280912', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '退出成功', '2021-04-30 17:24:38', 1);
INSERT INTO `sys_logininfor` VALUES (7965, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-30 17:24:39', 1);
INSERT INTO `sys_logininfor` VALUES (7966, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-30 17:25:00', 1);
INSERT INTO `sys_logininfor` VALUES (7967, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '退出成功', '2021-04-30 17:25:10', 1);
INSERT INTO `sys_logininfor` VALUES (7968, '13107280912', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-30 17:25:12', 1);
INSERT INTO `sys_logininfor` VALUES (7969, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-30 17:27:10', 1);
INSERT INTO `sys_logininfor` VALUES (7970, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '退出成功', '2021-04-30 17:27:14', 1);
INSERT INTO `sys_logininfor` VALUES (7971, '13107280912', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-30 17:27:15', 1);
INSERT INTO `sys_logininfor` VALUES (7972, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-30 17:32:19', 1);
INSERT INTO `sys_logininfor` VALUES (7973, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '退出成功', '2021-04-30 17:36:28', 1);
INSERT INTO `sys_logininfor` VALUES (7974, '13107280912', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-30 17:36:32', 1);
INSERT INTO `sys_logininfor` VALUES (7975, '13107280912', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '退出成功', '2021-04-30 17:36:51', 1);
INSERT INTO `sys_logininfor` VALUES (7976, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-30 17:36:52', 1);
INSERT INTO `sys_logininfor` VALUES (7977, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '退出成功', '2021-04-30 17:37:21', 1);
INSERT INTO `sys_logininfor` VALUES (7978, '13107280912', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-30 17:37:25', 1);
INSERT INTO `sys_logininfor` VALUES (7979, '13107280912', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '退出成功', '2021-04-30 17:39:15', 1);
INSERT INTO `sys_logininfor` VALUES (7980, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-30 17:39:16', 1);
INSERT INTO `sys_logininfor` VALUES (7981, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-30 17:39:55', 1);
INSERT INTO `sys_logininfor` VALUES (7982, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '退出成功', '2021-04-30 17:40:20', 1);
INSERT INTO `sys_logininfor` VALUES (7983, '13107280912', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-30 17:40:23', 1);
INSERT INTO `sys_logininfor` VALUES (7984, '13107280912', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '退出成功', '2021-04-30 17:42:55', 1);
INSERT INTO `sys_logininfor` VALUES (7985, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-30 17:42:56', 1);
INSERT INTO `sys_logininfor` VALUES (7986, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '退出成功', '2021-04-30 17:56:13', 1);
INSERT INTO `sys_logininfor` VALUES (7987, '13107280912', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-30 17:56:17', 1);
INSERT INTO `sys_logininfor` VALUES (7988, '13107280912', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '退出成功', '2021-04-30 17:56:25', 1);
INSERT INTO `sys_logininfor` VALUES (7989, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-30 17:56:25', 1);
INSERT INTO `sys_logininfor` VALUES (7990, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '退出成功', '2021-04-30 17:57:20', 1);
INSERT INTO `sys_logininfor` VALUES (7991, '13107280912', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-30 17:57:23', 1);
INSERT INTO `sys_logininfor` VALUES (7992, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-30 18:00:07', 1);
INSERT INTO `sys_logininfor` VALUES (7993, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '退出成功', '2021-04-30 18:00:10', 1);
INSERT INTO `sys_logininfor` VALUES (7994, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-30 18:00:46', 1);
INSERT INTO `sys_logininfor` VALUES (7995, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '1', '验证码错误', '2021-04-30 18:09:41', 1);
INSERT INTO `sys_logininfor` VALUES (7996, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '1', '验证码错误', '2021-04-30 18:10:07', 1);
INSERT INTO `sys_logininfor` VALUES (7997, 'admin', '112.10.237.146', 'XX XX', 'Chrome', 'Windows 10', '0', '登录成功', '2021-04-30 18:18:49', 1);
INSERT INTO `sys_logininfor` VALUES (7998, 'admin', '112.10.237.146', 'XX XX', 'Chrome', 'Windows 10', '0', '登录成功', '2021-05-01 03:56:02', 1);
INSERT INTO `sys_logininfor` VALUES (7999, 'admin', '112.10.237.146', 'XX XX', 'Chrome', 'Windows 10', '0', '登录成功', '2021-05-01 13:38:27', 1);
INSERT INTO `sys_logininfor` VALUES (8000, 'admin', '112.10.237.146', 'XX XX', 'Chrome', 'Windows 10', '0', '登录成功', '2021-05-01 14:27:12', 1);
INSERT INTO `sys_logininfor` VALUES (8001, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-05-01 14:28:44', 1);
INSERT INTO `sys_logininfor` VALUES (8002, 'admin', '112.10.237.146', 'XX XX', 'Chrome', 'Windows 10', '0', '登录成功', '2021-05-01 15:01:14', 1);
INSERT INTO `sys_logininfor` VALUES (8003, 'admin', '112.10.237.146', 'XX XX', 'Chrome', 'Windows 10', '0', '登录成功', '2021-05-01 15:31:40', 1);
INSERT INTO `sys_logininfor` VALUES (8004, 'admin', '112.10.237.146', 'XX XX', 'Chrome', 'Windows 10', '0', '登录成功', '2021-05-01 16:02:44', 1);
INSERT INTO `sys_logininfor` VALUES (8005, 'admin', '112.10.237.146', 'XX XX', 'Chrome', 'Windows 10', '0', '登录成功', '2021-05-01 17:14:23', 1);
INSERT INTO `sys_logininfor` VALUES (8006, 'admin', '111.22.206.22', 'XX XX', 'Chrome Mobile', 'Android Mobile', '0', '登录成功', '2021-05-03 01:25:55', 1);
INSERT INTO `sys_logininfor` VALUES (8007, 'admin', '111.22.206.22', 'XX XX', 'Internet Explorer 11', 'Windows 7', '0', '登录成功', '2021-05-03 08:42:31', 1);
INSERT INTO `sys_logininfor` VALUES (8008, 'admin', '112.10.237.146', 'XX XX', 'Chrome', 'Windows 10', '0', '登录成功', '2021-05-03 15:13:23', 1);
INSERT INTO `sys_logininfor` VALUES (8009, 'admin', '112.10.237.146', 'XX XX', 'Chrome', 'Windows 10', '0', '登录成功', '2021-05-04 13:34:55', 1);
INSERT INTO `sys_logininfor` VALUES (8010, 'admin', '112.10.237.60', 'XX XX', 'Chrome', 'Windows 10', '0', '登录成功', '2021-05-08 12:25:33', 1);
INSERT INTO `sys_logininfor` VALUES (8011, 'admin', '112.10.237.60', 'XX XX', 'Chrome', 'Windows 10', '0', '登录成功', '2021-05-08 14:54:04', 1);
INSERT INTO `sys_logininfor` VALUES (8012, 'admin', '112.10.237.60', 'XX XX', 'Chrome', 'Windows 10', '0', '登录成功', '2021-05-08 16:08:51', 1);
INSERT INTO `sys_logininfor` VALUES (8013, 'admin', '112.10.237.60', 'XX XX', 'Chrome', 'Windows 10', '0', '登录成功', '2021-05-11 21:31:14', 1);
INSERT INTO `sys_logininfor` VALUES (8014, 'admin', '112.10.237.60', 'XX XX', 'Chrome', 'Windows 10', '0', '登录成功', '2021-05-11 22:10:53', 1);
INSERT INTO `sys_logininfor` VALUES (8015, 'admin', '112.10.237.60', 'XX XX', 'Chrome', 'Windows 10', '0', '登录成功', '2021-05-12 22:15:39', 1);
INSERT INTO `sys_logininfor` VALUES (8016, 'admin', '116.21.15.176', 'XX XX', 'Chrome', 'Windows 10', '1', '密码输入错误1次', '2021-05-13 17:01:47', 1);
INSERT INTO `sys_logininfor` VALUES (8017, 'admin', '116.21.15.176', 'XX XX', 'Chrome', 'Windows 10', '0', '登录成功', '2021-05-13 17:01:51', 1);
INSERT INTO `sys_logininfor` VALUES (8018, 'admin', '116.21.15.176', 'XX XX', 'Chrome', 'Windows 10', '0', '退出成功', '2021-05-13 17:01:56', 1);
INSERT INTO `sys_logininfor` VALUES (8019, 'admin', '116.21.15.176', 'XX XX', 'Firefox', 'Windows 10', '0', '登录成功', '2021-05-14 10:52:37', 1);
INSERT INTO `sys_logininfor` VALUES (8020, 'admin', '112.10.237.60', 'XX XX', 'Chrome', 'Windows 10', '0', '登录成功', '2021-05-14 12:44:51', 1);
INSERT INTO `sys_logininfor` VALUES (8021, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-05-14 13:01:08', 1);
INSERT INTO `sys_logininfor` VALUES (8022, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '1', '密码输入错误1次', '2021-05-14 13:08:42', 1);
INSERT INTO `sys_logininfor` VALUES (8023, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '1', '密码输入错误2次', '2021-05-14 13:08:43', 1);
INSERT INTO `sys_logininfor` VALUES (8024, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-05-14 13:08:45', 1);
INSERT INTO `sys_logininfor` VALUES (8025, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-05-14 13:09:03', 1);
INSERT INTO `sys_logininfor` VALUES (8026, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '退出成功', '2021-05-14 13:09:51', 1);
INSERT INTO `sys_logininfor` VALUES (8027, '13107280912', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-05-14 13:09:58', 1);
INSERT INTO `sys_logininfor` VALUES (8028, '13107280912', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '退出成功', '2021-05-14 13:10:11', 1);
INSERT INTO `sys_logininfor` VALUES (8029, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-05-14 13:10:12', 1);
INSERT INTO `sys_logininfor` VALUES (8030, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '退出成功', '2021-05-14 13:10:30', 1);
INSERT INTO `sys_logininfor` VALUES (8031, '13107280912', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-05-14 13:10:36', 1);
INSERT INTO `sys_logininfor` VALUES (8032, '13107280912', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '退出成功', '2021-05-14 13:10:43', 1);
INSERT INTO `sys_logininfor` VALUES (8033, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-05-14 13:10:44', 1);
INSERT INTO `sys_logininfor` VALUES (8034, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '退出成功', '2021-05-14 13:11:07', 1);
INSERT INTO `sys_logininfor` VALUES (8035, '13107280912', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-05-14 13:11:14', 1);
INSERT INTO `sys_logininfor` VALUES (8036, '13107280912', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '退出成功', '2021-05-14 13:11:29', 1);
INSERT INTO `sys_logininfor` VALUES (8037, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-05-14 13:11:30', 1);
INSERT INTO `sys_logininfor` VALUES (8038, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '退出成功', '2021-05-14 13:12:30', 1);
INSERT INTO `sys_logininfor` VALUES (8039, '13107280912', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-05-14 13:12:33', 1);
INSERT INTO `sys_logininfor` VALUES (8040, '13107280912', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '退出成功', '2021-05-14 13:12:38', 1);
INSERT INTO `sys_logininfor` VALUES (8041, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-05-14 13:12:42', 1);
INSERT INTO `sys_logininfor` VALUES (8042, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '退出成功', '2021-05-14 13:12:59', 1);
INSERT INTO `sys_logininfor` VALUES (8043, '13107280912', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-05-14 13:13:03', 1);
INSERT INTO `sys_logininfor` VALUES (8044, '13107280912', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '退出成功', '2021-05-14 13:13:08', 1);
INSERT INTO `sys_logininfor` VALUES (8045, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-05-14 13:13:09', 1);
INSERT INTO `sys_logininfor` VALUES (8046, 'admin', '112.10.237.60', 'XX XX', 'Chrome', 'Windows 10', '0', '登录成功', '2021-05-14 13:40:56', 1);
INSERT INTO `sys_logininfor` VALUES (8047, 'admin', '112.10.237.60', 'XX XX', 'Chrome', 'Windows 10', '0', '登录成功', '2021-05-14 14:27:52', 1);
INSERT INTO `sys_logininfor` VALUES (8048, 'admin', '112.10.237.60', 'XX XX', 'Chrome', 'Windows 10', '0', '退出成功', '2021-05-14 14:31:33', 1);
INSERT INTO `sys_logininfor` VALUES (8049, '13107280912', '112.10.237.60', 'XX XX', 'Chrome', 'Windows 10', '0', '登录成功', '2021-05-14 14:31:35', 1);
INSERT INTO `sys_logininfor` VALUES (8050, 'admin', '112.10.237.60', 'XX XX', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-14 15:20:58', 1);
INSERT INTO `sys_logininfor` VALUES (8051, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-14 15:24:25', 1);
INSERT INTO `sys_logininfor` VALUES (8052, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-14 15:28:38', 1);
INSERT INTO `sys_logininfor` VALUES (8053, 'admin', '112.10.237.60', 'XX XX', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-14 15:33:55', 1);
INSERT INTO `sys_logininfor` VALUES (8054, 'admin', '112.10.237.60', 'XX XX', 'Chrome 9', 'Windows 10', '0', '退出成功', '2021-05-14 15:38:31', 1);
INSERT INTO `sys_logininfor` VALUES (8055, '13107280912', '112.10.237.60', 'XX XX', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-14 15:38:39', 1);
INSERT INTO `sys_logininfor` VALUES (8056, '13107280912', '112.10.237.60', 'XX XX', 'Chrome 9', 'Windows 10', '0', '退出成功', '2021-05-14 15:41:06', 1);
INSERT INTO `sys_logininfor` VALUES (8057, 'admin', '112.10.237.60', 'XX XX', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-14 15:41:36', 1);
INSERT INTO `sys_logininfor` VALUES (8058, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-14 15:44:56', 1);
INSERT INTO `sys_logininfor` VALUES (8059, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-14 15:48:09', 1);
INSERT INTO `sys_logininfor` VALUES (8060, 'admin', '112.10.237.60', 'XX XX', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-14 15:51:24', 1);
INSERT INTO `sys_logininfor` VALUES (8061, 'admin', '112.10.237.60', 'XX XX', 'Chrome 9', 'Windows 10', '0', '退出成功', '2021-05-14 15:56:16', 1);
INSERT INTO `sys_logininfor` VALUES (8062, '13107280912', '112.10.237.60', 'XX XX', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-14 15:56:19', 1);
INSERT INTO `sys_logininfor` VALUES (8063, '13107280912', '112.10.237.60', 'XX XX', 'Chrome 9', 'Windows 10', '0', '退出成功', '2021-05-14 16:02:00', 1);
INSERT INTO `sys_logininfor` VALUES (8064, 'admin', '112.10.237.60', 'XX XX', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-14 16:02:02', 1);
INSERT INTO `sys_logininfor` VALUES (8065, 'admin', '112.10.237.60', 'XX XX', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-14 16:07:20', 1);
INSERT INTO `sys_logininfor` VALUES (8066, 'admin', '112.10.237.60', 'XX XX', 'Chrome 9', 'Windows 10', '0', '退出成功', '2021-05-14 16:08:04', 1);
INSERT INTO `sys_logininfor` VALUES (8067, 'admin', '112.10.237.60', 'XX XX', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-14 16:08:21', 1);
INSERT INTO `sys_logininfor` VALUES (8068, 'admin', '112.10.237.60', 'XX XX', 'Chrome 9', 'Windows 10', '0', '退出成功', '2021-05-14 16:13:03', 1);
INSERT INTO `sys_logininfor` VALUES (8069, '13107280912', '112.10.237.60', 'XX XX', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-14 16:13:06', 1);
INSERT INTO `sys_logininfor` VALUES (8070, '13107280912', '112.10.237.60', 'XX XX', 'Chrome 9', 'Windows 10', '0', '退出成功', '2021-05-14 16:13:20', 1);
INSERT INTO `sys_logininfor` VALUES (8071, 'admin', '112.10.237.60', 'XX XX', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-14 16:13:21', 1);
INSERT INTO `sys_logininfor` VALUES (8072, 'admin', '112.10.237.60', 'XX XX', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-14 21:18:01', 1);
INSERT INTO `sys_logininfor` VALUES (8073, 'admin', '112.10.237.60', 'XX XX', 'Chrome 9', 'Windows 10', '0', '退出成功', '2021-05-14 21:18:41', 1);
INSERT INTO `sys_logininfor` VALUES (8074, 'admin', '112.10.237.60', 'XX XX', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-14 21:19:03', 1);
INSERT INTO `sys_logininfor` VALUES (8075, 'admin', '112.10.237.60', 'XX XX', 'Chrome 9', 'Windows 10', '0', '退出成功', '2021-05-14 21:23:14', 1);
INSERT INTO `sys_logininfor` VALUES (8076, '13107280912', '112.10.237.60', 'XX XX', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-14 21:23:18', 1);
INSERT INTO `sys_logininfor` VALUES (8077, '13107280912', '112.10.237.60', 'XX XX', 'Chrome 9', 'Windows 10', '0', '退出成功', '2021-05-14 21:23:29', 1);
INSERT INTO `sys_logininfor` VALUES (8078, 'admin', '112.10.237.60', 'XX XX', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-14 21:23:29', 1);
INSERT INTO `sys_logininfor` VALUES (8079, 'admin', '112.10.237.60', 'XX XX', 'Chrome 9', 'Windows 10', '0', '退出成功', '2021-05-14 21:25:45', 1);
INSERT INTO `sys_logininfor` VALUES (8080, 'admin', '112.10.237.60', 'XX XX', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-14 21:26:08', 1);
INSERT INTO `sys_logininfor` VALUES (8081, 'admin', '112.10.237.60', 'XX XX', 'Chrome 9', 'Windows 10', '0', '退出成功', '2021-05-14 21:30:24', 1);
INSERT INTO `sys_logininfor` VALUES (8082, '13107280912', '112.10.237.60', 'XX XX', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-14 21:30:27', 1);
INSERT INTO `sys_logininfor` VALUES (8083, '13107280912', '112.10.237.60', 'XX XX', 'Chrome 9', 'Windows 10', '0', '退出成功', '2021-05-14 21:30:33', 1);
INSERT INTO `sys_logininfor` VALUES (8084, 'admin', '112.10.237.60', 'XX XX', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-14 21:30:34', 1);
INSERT INTO `sys_logininfor` VALUES (8085, 'admin', '112.10.237.60', 'XX XX', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-15 09:24:17', 1);
INSERT INTO `sys_logininfor` VALUES (8086, 'admin', '112.10.237.60', 'XX XX', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-15 13:57:24', 1);
INSERT INTO `sys_logininfor` VALUES (8087, 'admin', '112.10.237.60', 'XX XX', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-15 15:25:52', 1);
INSERT INTO `sys_logininfor` VALUES (8088, 'admin', '112.10.237.60', 'XX XX', 'Chrome 9', 'Windows 10', '0', '退出成功', '2021-05-15 15:28:52', 1);
INSERT INTO `sys_logininfor` VALUES (8089, '13107280912', '112.10.237.60', 'XX XX', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-15 15:28:57', 1);
INSERT INTO `sys_logininfor` VALUES (8090, '13107280912', '112.10.237.60', 'XX XX', 'Chrome 9', 'Windows 10', '0', '退出成功', '2021-05-15 15:29:05', 1);
INSERT INTO `sys_logininfor` VALUES (8091, 'admin', '112.10.237.60', 'XX XX', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-15 15:29:06', 1);
INSERT INTO `sys_logininfor` VALUES (8092, 'admin', '112.10.237.60', 'XX XX', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-15 15:45:42', 1);
INSERT INTO `sys_logininfor` VALUES (8093, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2021-05-19 10:09:45', 1);
INSERT INTO `sys_logininfor` VALUES (8094, 'admin', '183.129.46.131', 'XX XX', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-19 17:09:15', 1);
INSERT INTO `sys_logininfor` VALUES (8095, 'admin', '183.129.46.131', 'XX XX', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-19 17:10:49', 1);
INSERT INTO `sys_logininfor` VALUES (8096, 'admin', '183.129.46.131', 'XX XX', 'Chrome', 'Windows 10', '0', '登录成功', '2021-05-19 17:12:12', 1);
INSERT INTO `sys_logininfor` VALUES (8097, 'admin', '112.10.237.60', 'XX XX', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-20 18:24:40', 1);
INSERT INTO `sys_logininfor` VALUES (8098, 'admin', '71.209.164.10', 'XX XX', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-24 18:15:46', 1);
INSERT INTO `sys_logininfor` VALUES (8099, 'admin', '71.209.164.10', 'XX XX', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-24 18:15:48', 1);
INSERT INTO `sys_logininfor` VALUES (8100, 'siibqstoimzfhqodkgpm', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:15:50', 1);
INSERT INTO `sys_logininfor` VALUES (8101, '${924781153+902983207}', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:15:50', 1);
INSERT INTO `sys_logininfor` VALUES (8102, 'admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '密码输入错误1次', '2021-05-24 18:15:51', 1);
INSERT INTO `sys_logininfor` VALUES (8103, 'admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '0', '登录成功', '2021-05-24 18:15:51', 1);
INSERT INTO `sys_logininfor` VALUES (8104, 'admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:15:51', 1);
INSERT INTO `sys_logininfor` VALUES (8105, 'admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '0', '登录成功', '2021-05-24 18:15:51', 1);
INSERT INTO `sys_logininfor` VALUES (8106, '/*1*/{{920455084+864817489}}', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:15:51', 1);
INSERT INTO `sys_logininfor` VALUES (8107, 'admin\nexpr 927294479 + 882301964\n', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:15:51', 1);
INSERT INTO `sys_logininfor` VALUES (8108, 'A4bCYEbO', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:15:51', 1);
INSERT INTO `sys_logininfor` VALUES (8109, 'admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '0', '登录成功', '2021-05-24 18:15:51', 1);
INSERT INTO `sys_logininfor` VALUES (8110, '${@var_dump(md5(468286881))};', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:15:51', 1);
INSERT INTO `sys_logininfor` VALUES (8111, 'admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '0', '登录成功', '2021-05-24 18:15:51', 1);
INSERT INTO `sys_logininfor` VALUES (8112, 'admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '0', '登录成功', '2021-05-24 18:15:52', 1);
INSERT INTO `sys_logininfor` VALUES (8113, 'admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '0', '登录成功', '2021-05-24 18:15:52', 1);
INSERT INTO `sys_logininfor` VALUES (8114, '${976068111+850108016}', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:15:52', 1);
INSERT INTO `sys_logininfor` VALUES (8115, 'admin|expr 833379149 + 853286805 ', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:15:52', 1);
INSERT INTO `sys_logininfor` VALUES (8116, 'A4bCYEbO', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:15:52', 1);
INSERT INTO `sys_logininfor` VALUES (8117, 'admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '0', '登录成功', '2021-05-24 18:15:52', 1);
INSERT INTO `sys_logininfor` VALUES (8118, '\'-var_dump(md5(254550357))-\'', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:15:52', 1);
INSERT INTO `sys_logininfor` VALUES (8119, 'admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '0', '登录成功', '2021-05-24 18:15:52', 1);
INSERT INTO `sys_logininfor` VALUES (8120, 'admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '0', '登录成功', '2021-05-24 18:15:52', 1);
INSERT INTO `sys_logininfor` VALUES (8121, '${(858926384+934081646)?c}', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:15:52', 1);
INSERT INTO `sys_logininfor` VALUES (8122, 'admin$(expr 883798999 + 835650798)', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:15:52', 1);
INSERT INTO `sys_logininfor` VALUES (8123, 'A4bCYEbO', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:15:52', 1);
INSERT INTO `sys_logininfor` VALUES (8124, 'admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:15:53', 1);
INSERT INTO `sys_logininfor` VALUES (8125, 'admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '0', '登录成功', '2021-05-24 18:15:53', 1);
INSERT INTO `sys_logininfor` VALUES (8126, 'admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:15:53', 1);
INSERT INTO `sys_logininfor` VALUES (8127, 'admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '0', '登录成功', '2021-05-24 18:15:53', 1);
INSERT INTO `sys_logininfor` VALUES (8128, '#set($c=803982393+897662862)${c}$c', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:15:53', 1);
INSERT INTO `sys_logininfor` VALUES (8129, 'admin&set /A 933032444+994943728', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:15:53', 1);
INSERT INTO `sys_logininfor` VALUES (8130, 'admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '密码输入错误1次', '2021-05-24 18:15:53', 1);
INSERT INTO `sys_logininfor` VALUES (8131, 'admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:15:53', 1);
INSERT INTO `sys_logininfor` VALUES (8132, 'admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:15:53', 1);
INSERT INTO `sys_logininfor` VALUES (8133, 'admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '密码输入错误2次', '2021-05-24 18:15:53', 1);
INSERT INTO `sys_logininfor` VALUES (8134, 'admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '0', '登录成功', '2021-05-24 18:15:53', 1);
INSERT INTO `sys_logininfor` VALUES (8135, '<%- 882962561+903832981 %>', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:15:53', 1);
INSERT INTO `sys_logininfor` VALUES (8136, 'expr 867619721 + 930732623', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:15:53', 1);
INSERT INTO `sys_logininfor` VALUES (8137, 'admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '密码输入错误1次', '2021-05-24 18:15:54', 1);
INSERT INTO `sys_logininfor` VALUES (8138, '../../../../../../etc/passwd', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:15:54', 1);
INSERT INTO `sys_logininfor` VALUES (8139, 'admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:15:54', 1);
INSERT INTO `sys_logininfor` VALUES (8140, 'admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '密码输入错误2次', '2021-05-24 18:15:54', 1);
INSERT INTO `sys_logininfor` VALUES (8141, 'admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '0', '登录成功', '2021-05-24 18:15:54', 1);
INSERT INTO `sys_logininfor` VALUES (8142, 'admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:15:54', 1);
INSERT INTO `sys_logininfor` VALUES (8143, 'admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:15:54', 1);
INSERT INTO `sys_logininfor` VALUES (8144, 'admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '密码输入错误1次', '2021-05-24 18:15:54', 1);
INSERT INTO `sys_logininfor` VALUES (8145, '../../../../../../etc/passwd\0admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:15:54', 1);
INSERT INTO `sys_logininfor` VALUES (8146, 'admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:15:54', 1);
INSERT INTO `sys_logininfor` VALUES (8147, 'admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '密码输入错误2次', '2021-05-24 18:15:54', 1);
INSERT INTO `sys_logininfor` VALUES (8148, 'admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:15:55', 1);
INSERT INTO `sys_logininfor` VALUES (8149, './../../../../../../etc/passwd', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:15:55', 1);
INSERT INTO `sys_logininfor` VALUES (8150, 'admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:15:55', 1);
INSERT INTO `sys_logininfor` VALUES (8151, 'admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '密码输入错误3次', '2021-05-24 18:15:55', 1);
INSERT INTO `sys_logininfor` VALUES (8152, 'admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:15:55', 1);
INSERT INTO `sys_logininfor` VALUES (8153, 'admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '密码输入错误4次', '2021-05-24 18:15:55', 1);
INSERT INTO `sys_logininfor` VALUES (8154, 'admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:15:55', 1);
INSERT INTO `sys_logininfor` VALUES (8155, 'admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:15:55', 1);
INSERT INTO `sys_logininfor` VALUES (8156, 'admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '密码输入错误5次', '2021-05-24 18:15:55', 1);
INSERT INTO `sys_logininfor` VALUES (8157, 'admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '密码输入错误5次，帐户锁定10分钟', '2021-05-24 18:15:56', 1);
INSERT INTO `sys_logininfor` VALUES (8158, 'admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:15:56', 1);
INSERT INTO `sys_logininfor` VALUES (8159, 'admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:15:56', 1);
INSERT INTO `sys_logininfor` VALUES (8160, 'admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '密码输入错误5次，帐户锁定10分钟', '2021-05-24 18:15:56', 1);
INSERT INTO `sys_logininfor` VALUES (8161, 'admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:15:56', 1);
INSERT INTO `sys_logininfor` VALUES (8162, 'admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '密码输入错误5次，帐户锁定10分钟', '2021-05-24 18:15:56', 1);
INSERT INTO `sys_logininfor` VALUES (8163, 'admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '密码输入错误5次，帐户锁定10分钟', '2021-05-24 18:15:56', 1);
INSERT INTO `sys_logininfor` VALUES (8164, 'admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:15:56', 1);
INSERT INTO `sys_logininfor` VALUES (8165, '/etc/passwd', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:15:56', 1);
INSERT INTO `sys_logininfor` VALUES (8166, 'admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:15:56', 1);
INSERT INTO `sys_logininfor` VALUES (8167, 'admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '密码输入错误5次，帐户锁定10分钟', '2021-05-24 18:15:56', 1);
INSERT INTO `sys_logininfor` VALUES (8168, 'admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '密码输入错误5次，帐户锁定10分钟', '2021-05-24 18:15:56', 1);
INSERT INTO `sys_logininfor` VALUES (8169, 'admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '密码输入错误5次，帐户锁定10分钟', '2021-05-24 18:15:56', 1);
INSERT INTO `sys_logininfor` VALUES (8170, 'admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '密码输入错误5次，帐户锁定10分钟', '2021-05-24 18:15:57', 1);
INSERT INTO `sys_logininfor` VALUES (8171, '/etc/passwd\0admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:15:57', 1);
INSERT INTO `sys_logininfor` VALUES (8172, 'admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:15:57', 1);
INSERT INTO `sys_logininfor` VALUES (8173, 'admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '密码输入错误5次，帐户锁定10分钟', '2021-05-24 18:15:57', 1);
INSERT INTO `sys_logininfor` VALUES (8174, 'admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '密码输入错误5次，帐户锁定10分钟', '2021-05-24 18:15:57', 1);
INSERT INTO `sys_logininfor` VALUES (8175, 'admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '密码输入错误5次，帐户锁定10分钟', '2021-05-24 18:15:57', 1);
INSERT INTO `sys_logininfor` VALUES (8176, '%2fetc%2fpasswd', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:15:57', 1);
INSERT INTO `sys_logininfor` VALUES (8177, 'admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '密码输入错误5次，帐户锁定10分钟', '2021-05-24 18:15:58', 1);
INSERT INTO `sys_logininfor` VALUES (8178, 'admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '密码输入错误5次，帐户锁定10分钟', '2021-05-24 18:15:58', 1);
INSERT INTO `sys_logininfor` VALUES (8179, '%u2215etc%u2215passwd', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:15:58', 1);
INSERT INTO `sys_logininfor` VALUES (8180, 'admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:15:58', 1);
INSERT INTO `sys_logininfor` VALUES (8181, 'admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '密码输入错误5次，帐户锁定10分钟', '2021-05-24 18:15:58', 1);
INSERT INTO `sys_logininfor` VALUES (8182, 'admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:15:59', 1);
INSERT INTO `sys_logininfor` VALUES (8183, 'admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:16:00', 1);
INSERT INTO `sys_logininfor` VALUES (8184, 'admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:16:01', 1);
INSERT INTO `sys_logininfor` VALUES (8185, '..\\..\\..\\..\\..\\..\\Windows\\win.ini', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:16:01', 1);
INSERT INTO `sys_logininfor` VALUES (8186, 'admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:16:01', 1);
INSERT INTO `sys_logininfor` VALUES (8187, '.\\..\\..\\..\\..\\..\\..\\Windows\\win.ini', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:16:01', 1);
INSERT INTO `sys_logininfor` VALUES (8188, 'admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:16:02', 1);
INSERT INTO `sys_logininfor` VALUES (8189, 'admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:16:03', 1);
INSERT INTO `sys_logininfor` VALUES (8190, '..\\..\\..\\..\\..\\..\\Windows\\win.ini\0admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:16:03', 1);
INSERT INTO `sys_logininfor` VALUES (8191, 'admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:16:03', 1);
INSERT INTO `sys_logininfor` VALUES (8192, 'admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:16:04', 1);
INSERT INTO `sys_logininfor` VALUES (8193, 'extractvalue(1,concat(char(126),md5(1552355534)))', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:16:04', 1);
INSERT INTO `sys_logininfor` VALUES (8194, 'admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:16:05', 1);
INSERT INTO `sys_logininfor` VALUES (8195, 'admin/**/and/**/cast(md5(\'1032328976\')as/**/int)>0', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:16:05', 1);
INSERT INTO `sys_logininfor` VALUES (8196, 'admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:16:06', 1);
INSERT INTO `sys_logininfor` VALUES (8197, 'admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:16:06', 1);
INSERT INTO `sys_logininfor` VALUES (8198, '../../../../../../Windows/win.ini', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:16:07', 1);
INSERT INTO `sys_logininfor` VALUES (8199, 'admin鎈\'\"\\(', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:16:07', 1);
INSERT INTO `sys_logininfor` VALUES (8200, './../../../../../../Windows/win.ini', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:16:08', 1);
INSERT INTO `sys_logininfor` VALUES (8201, 'admin\'\"\\(', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:16:08', 1);
INSERT INTO `sys_logininfor` VALUES (8202, '../../../../../../Windows/win.ini\0admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:16:08', 1);
INSERT INTO `sys_logininfor` VALUES (8203, 'admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:16:08', 1);
INSERT INTO `sys_logininfor` VALUES (8204, 'WEB-INF/web.xml', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:16:10', 1);
INSERT INTO `sys_logininfor` VALUES (8205, 'WEB-INF/web.xml;admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:16:10', 1);
INSERT INTO `sys_logininfor` VALUES (8206, 'admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:16:10', 1);
INSERT INTO `sys_logininfor` VALUES (8207, '../WEB-INF/web.xml', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:16:11', 1);
INSERT INTO `sys_logininfor` VALUES (8208, 'admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:16:11', 1);
INSERT INTO `sys_logininfor` VALUES (8209, '../WEB-INF/web.xml;admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:16:12', 1);
INSERT INTO `sys_logininfor` VALUES (8210, 'admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:16:12', 1);
INSERT INTO `sys_logininfor` VALUES (8211, '../../WEB-INF/web.xml', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:16:12', 1);
INSERT INTO `sys_logininfor` VALUES (8212, 'admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:16:12', 1);
INSERT INTO `sys_logininfor` VALUES (8213, '../../WEB-INF/web.xml;admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:16:13', 1);
INSERT INTO `sys_logininfor` VALUES (8214, 'admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:16:13', 1);
INSERT INTO `sys_logininfor` VALUES (8215, '../../../WEB-INF/web.xml', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:16:13', 1);
INSERT INTO `sys_logininfor` VALUES (8216, '../../../WEB-INF/web.xml;admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:16:15', 1);
INSERT INTO `sys_logininfor` VALUES (8217, '../../../../WEB-INF/web.xml', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:16:15', 1);
INSERT INTO `sys_logininfor` VALUES (8218, '../../../../WEB-INF/web.xml;admin', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:16:16', 1);
INSERT INTO `sys_logininfor` VALUES (8219, 'admin\'and\'r\'=\'r', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:16:24', 1);
INSERT INTO `sys_logininfor` VALUES (8220, 'admin\'and\'o\'=\'z', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:16:26', 1);
INSERT INTO `sys_logininfor` VALUES (8221, 'admin\"and\"k\"=\"k', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:16:26', 1);
INSERT INTO `sys_logininfor` VALUES (8222, 'admin\"and\"g\"=\"a', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:16:26', 1);
INSERT INTO `sys_logininfor` VALUES (8223, 'admin\'/**/and(select\'1\'from/**/pg_sleep(0))>\'0', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:16:32', 1);
INSERT INTO `sys_logininfor` VALUES (8224, 'admin\'/**/and(select\'1\'from/**/pg_sleep(2))>\'0', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:16:32', 1);
INSERT INTO `sys_logininfor` VALUES (8225, 'admin\'and(select+1)>0waitfor/**/delay\'0:0:0', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:16:33', 1);
INSERT INTO `sys_logininfor` VALUES (8226, 'admin\'and(select+1)>0waitfor/**/delay\'0:0:2', '71.209.164.10', 'XX XX', 'Firefox 7', 'Windows 10', '1', '用户不存在/密码错误', '2021-05-24 18:16:34', 1);
INSERT INTO `sys_logininfor` VALUES (8227, 'admin', '112.10.237.181', 'XX XX', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-05-28 20:09:25', 1);
INSERT INTO `sys_logininfor` VALUES (8228, 'admin', '111.199.191.189', 'XX XX', 'Chrome 9', 'Windows 7', '0', '登录成功', '2021-05-31 17:55:17', 1);
INSERT INTO `sys_logininfor` VALUES (8229, 'admin', '122.233.152.195', 'XX XX', 'Chrome', 'Windows 10', '0', '登录成功', '2021-06-08 16:45:45', 1);
INSERT INTO `sys_logininfor` VALUES (8230, 'admin', '112.10.237.181', 'XX XX', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-06-08 21:59:26', 1);
INSERT INTO `sys_logininfor` VALUES (8231, 'admin', '58.101.246.70', 'XX XX', 'Internet Explorer 11', 'Windows 10', '0', '登录成功', '2022-07-13 19:54:47', 1);
INSERT INTO `sys_logininfor` VALUES (8232, 'admin', '223.104.244.211', 'XX XX', 'Chrome Mobile', 'Android 1.x', '0', '登录成功', '2022-07-13 20:24:51', 1);
INSERT INTO `sys_logininfor` VALUES (8233, 'admin', '117.189.142.141', 'XX XX', 'Chrome Mobile', 'Android Mobile', '0', '登录成功', '2022-07-13 21:05:59', 1);
INSERT INTO `sys_logininfor` VALUES (8234, 'admin', '58.101.246.70', 'XX XX', 'Chrome 8', 'Windows 7', '0', '登录成功', '2022-07-13 21:07:30', 1);
INSERT INTO `sys_logininfor` VALUES (8235, 'admin', '58.101.246.70', 'XX XX', 'Chrome Mobile', 'Android 1.x', '0', '登录成功', '2022-07-13 23:41:08', 1);
INSERT INTO `sys_logininfor` VALUES (8236, 'admin', '58.101.246.70', 'XX XX', 'Chrome 10', 'Mac OS X', '0', '登录成功', '2022-07-24 11:54:49', 1);
INSERT INTO `sys_logininfor` VALUES (8237, 'admin', '120.231.65.105', 'XX XX', 'Firefox 10', 'Windows 10', '0', '登录成功', '2022-07-25 22:28:03', 1);
INSERT INTO `sys_logininfor` VALUES (8238, 'admin', '59.55.48.105', 'XX XX', 'Chrome Mobile', 'Android 1.x', '0', '登录成功', '2022-07-29 05:10:38', 1);
INSERT INTO `sys_logininfor` VALUES (8239, 'admin', '222.212.201.9', 'XX XX', 'Chrome 10', 'Windows 10', '0', '登录成功', '2022-07-31 03:00:25', 1);
INSERT INTO `sys_logininfor` VALUES (8240, 'admin', '127.0.0.1', '内网IP', 'Chrome 10', 'Windows 10', '0', '登录成功', '2022-08-01 20:38:30', 1);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名称',
  `parent_id` bigint(20) NULL DEFAULT 0 COMMENT '父菜单ID',
  `order_num` int(4) NULL DEFAULT 0 COMMENT '显示顺序',
  `url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '#' COMMENT '请求地址',
  `target` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '打开方式（menuItem页签 menuBlank新窗口）',
  `menu_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `perms` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '#' COMMENT '菜单图标',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '备注',
  `provider_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2313 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单权限表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '系统管理', 0, 9, '#', 'menuItem', 'M', '0', '', 'fa fa-gear', 'admin', '2018-03-16 11:33:00', 'admin', '2021-04-06 20:25:51', '系统管理目录', 1);
INSERT INTO `sys_menu` VALUES (2, '系统监控', 0, 11, '#', 'menuItem', 'M', '0', '', 'fa fa-video-camera', 'admin', '2018-03-16 11:33:00', 'admin', '2022-07-13 19:55:29', '系统监控目录', 1);
INSERT INTO `sys_menu` VALUES (3, '系统工具', 0, 10, '#', 'menuItem', 'M', '0', '', 'fa fa-bars', 'admin', '2018-03-16 11:33:00', 'admin', '2021-04-06 20:25:58', '系统工具目录', 1);
INSERT INTO `sys_menu` VALUES (100, '用户管理', 2247, 1, '/system/user', 'menuItem', 'C', '0', 'system:user:view', '#', 'admin', '2018-03-16 11:33:00', 'admin', '2021-04-07 09:00:28', '用户管理菜单', 1);
INSERT INTO `sys_menu` VALUES (101, '角色管理', 2247, 2, '/system/role', 'menuItem', 'C', '0', 'system:role:view', '#', 'admin', '2018-03-16 11:33:00', 'admin', '2021-04-07 09:00:37', '角色管理菜单', 1);
INSERT INTO `sys_menu` VALUES (102, '菜单管理', 1, 3, '/system/menu', '', 'C', '0', 'system:menu:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '菜单管理菜单', 1);
INSERT INTO `sys_menu` VALUES (103, '部门管理', 2247, 4, '/system/dept', 'menuItem', 'C', '0', 'system:dept:view', '#', 'admin', '2018-03-16 11:33:00', 'admin', '2021-04-07 09:00:47', '部门管理菜单', 1);
INSERT INTO `sys_menu` VALUES (104, '岗位管理', 2247, 5, '/system/post', 'menuItem', 'C', '0', 'system:post:view', '#', 'admin', '2018-03-16 11:33:00', 'admin', '2021-04-07 09:00:57', '岗位管理菜单', 1);
INSERT INTO `sys_menu` VALUES (105, '字典管理', 1, 6, '/system/dict', '', 'C', '0', 'system:dict:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '字典管理菜单', 1);
INSERT INTO `sys_menu` VALUES (106, '参数设置', 1, 7, '/system/config', 'menuItem', 'C', '0', 'system:config:view', '#', 'admin', '2018-03-16 11:33:00', 'admin', '2022-07-13 20:32:07', '参数设置菜单', 1);
INSERT INTO `sys_menu` VALUES (107, '通知公告', 1, 8, '/system/notice', 'menuItem', 'C', '0', 'system:notice:view', '#', 'admin', '2018-03-16 11:33:00', 'admin', '2022-07-13 20:32:12', '通知公告菜单', 1);
INSERT INTO `sys_menu` VALUES (108, '日志管理', 1, 9, '#', 'menuItem', 'M', '0', '', '#', 'admin', '2018-03-16 11:33:00', 'admin', '2022-07-13 20:31:58', '日志管理菜单', 1);
INSERT INTO `sys_menu` VALUES (109, '在线用户', 2, 1, '/monitor/online', '', 'C', '0', 'monitor:online:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '在线用户菜单', 1);
INSERT INTO `sys_menu` VALUES (110, '定时任务', 2, 2, '/monitor/job', '', 'C', '0', 'monitor:job:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '定时任务菜单', 1);
INSERT INTO `sys_menu` VALUES (111, '数据监控', 2, 3, '/monitor/data', 'menuItem', 'C', '0', 'monitor:data:view', '#', 'admin', '2018-03-16 11:33:00', 'admin', '2022-07-13 20:31:49', '数据监控菜单', 1);
INSERT INTO `sys_menu` VALUES (112, '服务监控', 2, 3, '/monitor/server', '', 'C', '0', 'monitor:server:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '服务监控菜单', 1);
INSERT INTO `sys_menu` VALUES (113, '表单构建', 3, 1, '/tool/build', 'menuItem', 'C', '0', 'tool:build:view', '#', 'admin', '2018-03-16 11:33:00', 'admin', '2022-07-13 20:31:37', '表单构建菜单', 1);
INSERT INTO `sys_menu` VALUES (114, '代码生成', 3, 2, '/tool/gen', 'menuItem', 'C', '0', 'tool:gen:view', '#', 'admin', '2018-03-16 11:33:00', 'admin', '2022-07-13 20:31:43', '代码生成菜单', 1);
INSERT INTO `sys_menu` VALUES (115, '系统接口', 3, 3, '/tool/swagger', 'menuItem', 'C', '0', 'tool:swagger:view', '#', 'admin', '2018-03-16 11:33:00', 'admin', '2021-04-30 17:18:16', '系统接口菜单', 1);
INSERT INTO `sys_menu` VALUES (500, '操作日志', 108, 1, '/monitor/operlog', '', 'C', '0', 'monitor:operlog:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '操作日志菜单', 1);
INSERT INTO `sys_menu` VALUES (501, '登录日志', 108, 2, '/monitor/logininfor', '', 'C', '0', 'monitor:logininfor:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '登录日志菜单', 1);
INSERT INTO `sys_menu` VALUES (1000, '用户查询', 100, 1, '#', '', 'F', '0', 'system:user:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1001, '用户新增', 100, 2, '#', '', 'F', '0', 'system:user:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1002, '用户修改', 100, 3, '#', '', 'F', '0', 'system:user:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1003, '用户删除', 100, 4, '#', '', 'F', '0', 'system:user:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1004, '用户导出', 100, 5, '#', '', 'F', '0', 'system:user:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1005, '用户导入', 100, 6, '#', '', 'F', '0', 'system:user:import', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1006, '重置密码', 100, 7, '#', '', 'F', '0', 'system:user:resetPwd', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1007, '角色查询', 101, 1, '#', '', 'F', '0', 'system:role:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1008, '角色新增', 101, 2, '#', '', 'F', '0', 'system:role:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1009, '角色修改', 101, 3, '#', '', 'F', '0', 'system:role:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1010, '角色删除', 101, 4, '#', '', 'F', '0', 'system:role:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1011, '角色导出', 101, 5, '#', '', 'F', '0', 'system:role:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1012, '菜单查询', 102, 1, '#', '', 'F', '0', 'system:menu:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1013, '菜单新增', 102, 2, '#', '', 'F', '0', 'system:menu:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1014, '菜单修改', 102, 3, '#', '', 'F', '0', 'system:menu:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1015, '菜单删除', 102, 4, '#', '', 'F', '0', 'system:menu:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1016, '部门查询', 103, 1, '#', '', 'F', '0', 'system:dept:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1017, '部门新增', 103, 2, '#', '', 'F', '0', 'system:dept:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1018, '部门修改', 103, 3, '#', '', 'F', '0', 'system:dept:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1019, '部门删除', 103, 4, '#', '', 'F', '0', 'system:dept:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1020, '岗位查询', 104, 1, '#', '', 'F', '0', 'system:post:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1021, '岗位新增', 104, 2, '#', '', 'F', '0', 'system:post:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1022, '岗位修改', 104, 3, '#', '', 'F', '0', 'system:post:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1023, '岗位删除', 104, 4, '#', '', 'F', '0', 'system:post:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1024, '岗位导出', 104, 5, '#', '', 'F', '0', 'system:post:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1025, '字典查询', 105, 1, '#', '', 'F', '0', 'system:dict:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1026, '字典新增', 105, 2, '#', '', 'F', '0', 'system:dict:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1027, '字典修改', 105, 3, '#', '', 'F', '0', 'system:dict:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1028, '字典删除', 105, 4, '#', '', 'F', '0', 'system:dict:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1029, '字典导出', 105, 5, '#', '', 'F', '0', 'system:dict:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1030, '参数查询', 106, 1, '#', '', 'F', '0', 'system:config:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1031, '参数新增', 106, 2, '#', '', 'F', '0', 'system:config:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1032, '参数修改', 106, 3, '#', '', 'F', '0', 'system:config:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1033, '参数删除', 106, 4, '#', '', 'F', '0', 'system:config:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1034, '参数导出', 106, 5, '#', '', 'F', '0', 'system:config:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1035, '公告查询', 107, 1, '#', '', 'F', '0', 'system:notice:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1036, '公告新增', 107, 2, '#', '', 'F', '0', 'system:notice:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1037, '公告修改', 107, 3, '#', '', 'F', '0', 'system:notice:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1038, '公告删除', 107, 4, '#', '', 'F', '0', 'system:notice:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1039, '操作查询', 500, 1, '#', '', 'F', '0', 'monitor:operlog:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1040, '操作删除', 500, 2, '#', '', 'F', '0', 'monitor:operlog:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1041, '详细信息', 500, 3, '#', '', 'F', '0', 'monitor:operlog:detail', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1042, '日志导出', 500, 4, '#', '', 'F', '0', 'monitor:operlog:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1043, '登录查询', 501, 1, '#', '', 'F', '0', 'monitor:logininfor:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1044, '登录删除', 501, 2, '#', '', 'F', '0', 'monitor:logininfor:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1045, '日志导出', 501, 3, '#', '', 'F', '0', 'monitor:logininfor:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1046, '在线查询', 109, 1, '#', '', 'F', '0', 'monitor:online:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1047, '批量强退', 109, 2, '#', '', 'F', '0', 'monitor:online:batchForceLogout', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1048, '单条强退', 109, 3, '#', '', 'F', '0', 'monitor:online:forceLogout', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1049, '任务查询', 110, 1, '#', '', 'F', '0', 'monitor:job:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1050, '任务新增', 110, 2, '#', '', 'F', '0', 'monitor:job:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1051, '任务修改', 110, 3, '#', '', 'F', '0', 'monitor:job:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1052, '任务删除', 110, 4, '#', '', 'F', '0', 'monitor:job:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1053, '状态修改', 110, 5, '#', '', 'F', '0', 'monitor:job:changeStatus', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1054, '任务详细', 110, 6, '#', '', 'F', '0', 'monitor:job:detail', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1055, '任务导出', 110, 7, '#', '', 'F', '0', 'monitor:job:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1056, '生成查询', 114, 1, '#', '', 'F', '0', 'tool:gen:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1057, '生成代码', 114, 2, '#', '', 'F', '0', 'tool:gen:code', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (2244, '常见问题管理', 0, 7, '#', 'menuItem', 'M', '0', '', 'fa fa-question-circle', 'admin', '2021-04-06 20:07:41', 'admin', '2021-04-09 10:13:23', '', 1);
INSERT INTO `sys_menu` VALUES (2245, '常见问题', 2244, 1, 'business/commonProblem', 'menuItem', 'C', '0', 'business:commonProblem:view', '#', 'admin', '2021-04-06 20:11:47', 'admin', '2021-04-06 20:16:59', '', 1);
INSERT INTO `sys_menu` VALUES (2246, '宠物医疗管理', 0, 2, '#', 'menuItem', 'M', '0', '', 'fa fa-plus-circle', 'admin', '2021-04-06 20:20:39', 'admin', '2021-04-09 10:12:41', '', 1);
INSERT INTO `sys_menu` VALUES (2247, '用户管理', 0, 8, '#', 'menuItem', 'M', '0', '', 'fa fa-user-circle-o', 'admin', '2021-04-06 20:21:07', 'admin', '2021-04-09 10:13:17', '', 1);
INSERT INTO `sys_menu` VALUES (2248, '宠物管理', 0, 1, '#', 'menuItem', 'M', '0', '', 'fa fa-paw', 'admin', '2021-04-06 20:21:49', 'admin', '2021-04-09 10:12:30', '', 1);
INSERT INTO `sys_menu` VALUES (2249, '宠物销售管理', 0, 3, '#', 'menuItem', 'M', '0', '', 'fa fa-bell', 'admin', '2021-04-06 20:23:51', 'admin', '2021-04-09 10:12:49', '', 1);
INSERT INTO `sys_menu` VALUES (2250, '宠物寄养管理', 0, 4, '#', 'menuItem', 'M', '0', '', 'fa fa-wifi', 'admin', '2021-04-06 20:24:31', 'admin', '2021-04-09 10:12:54', '', 1);
INSERT INTO `sys_menu` VALUES (2251, '宠物用品管理', 0, 5, '#', 'menuItem', 'M', '0', '', 'fa fa-bell', 'admin', '2021-04-06 20:24:53', 'admin', '2021-04-09 10:12:59', '', 1);
INSERT INTO `sys_menu` VALUES (2252, '宠物日常服务管理', 0, 6, '#', 'menuItem', 'M', '0', '', 'fa fa-futbol-o', 'admin', '2021-04-06 20:25:28', 'admin', '2021-04-09 10:13:08', '', 1);
INSERT INTO `sys_menu` VALUES (2253, '宠物信息列表', 2248, 2, 'business/petInfo', 'menuItem', 'M', '0', 'business:petInfo:view', '#', 'admin', '2021-04-10 14:08:56', '13107280912', '2021-04-30 17:31:04', '', 1);
INSERT INTO `sys_menu` VALUES (2254, '主人信息列表页面', 2255, 1, 'business/petMasterInfo', 'menuItem', 'C', '0', 'business:petMasterInfo:view', '#', 'admin', '2021-04-26 14:29:25', 'admin', '2021-04-30 17:34:00', '', 1);
INSERT INTO `sys_menu` VALUES (2255, '主人管理', 0, 0, '#', 'menuItem', 'M', '0', NULL, 'fa fa-address-book', 'admin', '2021-04-26 15:06:03', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2256, '宠物病历管理', 2246, 1, 'business/petMedicalRecord', 'menuItem', 'C', '0', 'business:petMedicalRecord:view', '#', 'admin', '2021-04-26 19:38:05', 'admin', '2021-04-26 19:45:01', '', 1);
INSERT INTO `sys_menu` VALUES (2257, '宠物销售管理', 2249, 1, 'business/petSaleRecord', 'menuItem', 'C', '0', 'business:petSaleRecord:view', '#', 'admin', '2021-04-27 14:13:47', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2258, '宠物寄养管理', 2250, 1, 'business/petFosterRecord', 'menuItem', 'C', '0', 'business:petFosterRecord:view', '#', 'admin', '2021-04-27 20:09:32', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2259, '宠物服务管理', 2252, 1, 'business/petServiceRecord', 'menuItem', 'C', '0', 'business:petServiceRecord:view', '#', 'admin', '2021-04-28 16:52:42', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2260, '宠物玩具管理', 2251, 1, 'business/petToys', 'menuItem', 'C', '0', 'business:petToys:view', '#', 'admin', '2021-04-29 01:29:33', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2261, '宠物食物管理', 2251, 2, 'business/petFoods', 'menuItem', 'C', '0', 'business:petFoods:view', '#', 'admin', '2021-04-29 20:12:04', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2262, '宠物药品管理', 2251, 3, 'business/petMedicine', 'menuItem', 'C', '0', 'business:petMedicine:view', '#', 'admin', '2021-04-29 22:50:15', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2263, '宠物日用品管理', 2251, 4, 'business/petDailyNecessities', 'menuItem', 'C', '0', 'business:petDailyNecessities:view', '#', 'admin', '2021-04-29 22:54:56', 'admin', '2021-04-29 23:18:49', '', 1);
INSERT INTO `sys_menu` VALUES (2264, '宠物服饰管理', 2251, 5, 'business/petClothes', 'menuItem', 'C', '0', 'business:petClothes:view', '#', 'admin', '2021-04-29 22:55:16', 'admin', '2021-04-29 23:35:49', '', 1);
INSERT INTO `sys_menu` VALUES (2265, '查询宠物列表', 2253, 1, '#', 'menuItem', 'F', '0', 'business:petInfo:list', '#', '13107280912', '2021-04-30 17:29:49', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2266, '查询宠物主人列表', 2254, 2, '#', 'menuItem', 'F', '0', 'business:petMasterInfo:list', '#', 'admin', '2021-04-30 17:34:26', '13107280912', '2021-04-30 17:42:33', '', 1);
INSERT INTO `sys_menu` VALUES (2267, '新增保存宠物主人', 2254, 3, '#', 'menuItem', 'F', '0', 'business:petMasterInfo:add', '#', 'admin', '2021-04-30 17:35:39', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2268, '修改保存宠物主人', 2254, 4, '#', 'menuItem', 'F', '0', 'business:petMasterInfo:edit', '#', 'admin', '2021-04-30 17:35:54', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2269, '删除宠物主人', 2254, 5, '#', 'menuItem', 'F', '0', 'business:petMasterInfo:remove', '#', 'admin', '2021-04-30 17:36:09', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2270, '新增保存宠物', 2253, 2, '#', 'menuItem', 'F', '0', 'business:petInfo:add', '#', 'admin', '2021-04-30 17:43:29', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2271, '修改保存宠物', 2253, 3, '#', 'menuItem', 'F', '0', 'business:petInfo:edit', '#', 'admin', '2021-04-30 17:43:47', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2272, '删除宠物', 2253, 4, '#', 'menuItem', 'F', '0', 'business:petInfo:remove', '#', 'admin', '2021-04-30 17:44:14', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2273, '查询医疗宠物病历列表', 2256, 1, '#', 'menuItem', 'F', '0', 'business:petMedicalRecord:list', '#', 'admin', '2021-04-30 17:44:54', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2274, '新增保存医疗宠物病历', 2256, 2, '#', 'menuItem', 'F', '0', 'business:petMedicalRecord:add', '#', 'admin', '2021-04-30 17:45:18', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2275, '修改保存医疗宠物病历', 2256, 3, '#', 'menuItem', 'F', '0', 'business:petMedicalRecord:edit', '#', 'admin', '2021-04-30 17:45:34', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2276, '删除医疗宠物病历', 2256, 4, '#', 'menuItem', 'F', '0', 'business:petMedicalRecord:remove', '#', 'admin', '2021-04-30 17:45:50', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2277, '查询销售宠物记录列表', 2257, 1, '#', 'menuItem', 'F', '0', 'business:petSaleRecord:list', '#', 'admin', '2021-04-30 17:46:16', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2278, '新增保存销售宠物记录', 2257, 2, '#', 'menuItem', 'F', '0', 'business:petSaleRecord:add', '#', 'admin', '2021-04-30 17:46:33', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2279, '修改保存销售宠物记录', 2257, 3, '#', 'menuItem', 'F', '0', 'business:petSaleRecord:edit', '#', 'admin', '2021-04-30 17:46:48', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2280, '删除销售宠物记录', 2257, 4, '#', 'menuItem', 'F', '0', 'business:petSaleRecord:remove', '#', 'admin', '2021-04-30 17:47:02', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2281, '查询宠物寄养记录列表', 2258, 1, '#', 'menuItem', 'F', '0', 'business:petFosterRecord:list', '#', 'admin', '2021-04-30 17:47:32', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2282, '新增保存宠物寄养记录', 2258, 2, '#', 'menuItem', 'F', '0', 'business:petFosterRecord:add', '#', 'admin', '2021-04-30 17:47:48', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2283, '修改保存宠物寄养记录', 2258, 3, '#', 'menuItem', 'F', '0', 'business:petFosterRecord:edit', '#', 'admin', '2021-04-30 17:48:02', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2284, '删除宠物寄养记录', 2258, 4, '#', 'menuItem', 'F', '0', 'business:petFosterRecord:remove', '#', 'admin', '2021-04-30 17:48:17', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2285, '查询宠物玩具列表', 2260, 1, '#', 'menuItem', 'F', '0', 'business:petToys:list', '#', 'admin', '2021-04-30 17:48:54', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2286, '新增保存宠物玩具', 2260, 2, '#', 'menuItem', 'F', '0', 'business:petToys:add', '#', 'admin', '2021-04-30 17:49:08', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2287, '修改保存宠物玩具', 2260, 3, '#', 'menuItem', 'F', '0', 'business:petToys:edit', '#', 'admin', '2021-04-30 17:49:22', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2288, '删除宠物玩具', 2260, 4, '#', 'menuItem', 'F', '0', 'business:petToys:remove', '#', 'admin', '2021-04-30 17:49:38', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2289, '查询宠物食品列表', 2261, 1, '#', 'menuItem', 'F', '0', 'business:petFoods:list', '#', 'admin', '2021-04-30 17:49:55', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2290, '新增保存宠物食品接口', 2261, 2, '#', 'menuItem', 'F', '0', 'business:petFoods:add', '#', 'admin', '2021-04-30 17:50:11', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2291, '修改保存宠物食品', 2261, 3, '#', 'menuItem', 'F', '0', 'business:petFoods:edit', '#', 'admin', '2021-04-30 17:50:23', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2292, '删除宠物食品', 2261, 4, '#', 'menuItem', 'F', '0', 'business:petFoods:remove', '#', 'admin', '2021-04-30 17:50:36', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2293, '查询宠物药品列表', 2262, 1, '#', 'menuItem', 'F', '0', 'business:petMedicine:list', '#', 'admin', '2021-04-30 17:51:08', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2294, '新增保存宠物药品', 2262, 2, '#', 'menuItem', 'F', '0', 'business:petMedicine:add', '#', 'admin', '2021-04-30 17:51:21', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2295, '修改保存宠物药品', 2262, 3, '#', 'menuItem', 'F', '0', 'business:petMedicine:edit', '#', 'admin', '2021-04-30 17:51:36', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2296, '删除宠物药品', 2262, 4, '#', 'menuItem', 'F', '0', 'business:petMedicine:remove', '#', 'admin', '2021-04-30 17:51:50', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2297, '查询到宠物日用品列表', 2263, 1, '#', 'menuItem', 'F', '0', 'business:petDailyNecessities:list', '#', 'admin', '2021-04-30 17:52:03', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2298, '新增到宠物日用品接口', 2263, 2, '#', 'menuItem', 'F', '0', 'business:petDailyNecessities:add', '#', 'admin', '2021-04-30 17:52:17', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2299, '修改宠物日用品接口', 2263, 3, '#', 'menuItem', 'F', '0', 'business:petDailyNecessities:edit', '#', 'admin', '2021-04-30 17:52:31', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2300, '删除宠物日用品接口', 2263, 4, '#', 'menuItem', 'F', '0', 'business:petDailyNecessities:remove', '#', 'admin', '2021-04-30 17:52:47', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2301, '查询宠物服饰列表', 2264, 1, '#', 'menuItem', 'F', '0', 'business:petClothes:list', '#', 'admin', '2021-04-30 17:53:10', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2302, '跳转到新增宠物服饰页面', 2264, 2, '#', 'menuItem', 'F', '0', 'business:petClothes:add', '#', 'admin', '2021-04-30 17:53:25', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2303, '修改保存宠物服饰接口', 2264, 3, '#', 'menuItem', 'F', '0', 'business:petClothes:edit', '#', 'admin', '2021-04-30 17:53:41', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2304, '删除保存宠物服饰接口', 2264, 4, '#', 'menuItem', 'F', '0', 'business:petClothes:remove', '#', 'admin', '2021-04-30 17:53:59', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2305, '查询宠物服务记录列表', 2259, 1, '#', 'menuItem', 'F', '0', 'business:petServiceRecord:list', '#', 'admin', '2021-04-30 17:54:28', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2306, '新增保存宠物服务记录', 2259, 2, '#', 'menuItem', 'F', '0', 'business:petServiceRecord:add', '#', 'admin', '2021-04-30 17:54:40', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2307, '修改保存宠物服务记录', 2259, 3, '#', 'menuItem', 'F', '0', 'business:petServiceRecord:edit', '#', 'admin', '2021-04-30 17:54:51', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2308, '删除宠物服务记录', 2259, 4, '#', 'menuItem', 'F', '0', 'business:petServiceRecord:remove', '#', 'admin', '2021-04-30 17:55:04', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2309, '查询问题列表', 2245, 1, '#', 'menuItem', 'F', '0', 'business:commonProblem:list', '#', 'admin', '2021-04-30 17:55:22', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2310, '新增保存常见问题', 2245, 2, '#', 'menuItem', 'F', '0', 'business:commonProblem:add', '#', 'admin', '2021-04-30 17:55:36', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2311, '修改保存常见问题', 2245, 3, '#', 'menuItem', 'F', '0', 'business:commonProblem:edit', '#', 'admin', '2021-04-30 17:55:53', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2312, '删除常见问题', 2245, 4, '#', 'menuItem', 'F', '0', 'business:commonProblem:remove', '#', 'admin', '2021-04-30 17:56:07', '', NULL, '', 1);

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice`  (
  `notice_id` int(4) NOT NULL AUTO_INCREMENT COMMENT '公告ID',
  `notice_title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '公告标题',
  `notice_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '公告类型（1通知 2公告）',
  `notice_content` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '公告内容',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `provider_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`notice_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '通知公告表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_notice
-- ----------------------------
INSERT INTO `sys_notice` VALUES (1, '温馨提醒：2018-07-01 若依新版本发布啦', '2', '新版本内容', '0', 'admin', '2018-03-16 11:33:00', 'admin', '2021-01-15 09:05:08', '管理员', 1);
INSERT INTO `sys_notice` VALUES (2, '维护通知：2018-07-01 若依系统凌晨维护', '1', '维护内容', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '管理员', 1);
INSERT INTO `sys_notice` VALUES (3, '新版本上线', '2', '<p>上线通知</p><p>尊敬的用户，您好，友加云商系统平台将于2021年1月14日6:00至7:00，进行系统发布升级</p><p>发布时长：1个小时</p><p>发布期间影响业务：提现&amp;代发&amp;电签</p><p>7:00后系统恢复使用。由此给您带来的不便，敬请见谅。如有问题可随时沟通联系，谢谢！</p>', '0', 'admin', '2021-01-14 15:13:02', '', NULL, NULL, 1);
INSERT INTO `sys_notice` VALUES (4, '一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十', '1', '<p>方法</p>', '0', 'admin', '2021-01-15 09:45:11', 'admin', '2021-01-15 09:45:28', NULL, 1);

-- ----------------------------
-- Table structure for sys_oper_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_oper_log`;
CREATE TABLE `sys_oper_log`  (
  `oper_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '模块标题',
  `business_type` int(2) NULL DEFAULT 0 COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `method` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '方法名称',
  `operator_type` int(1) NULL DEFAULT 0 COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
  `oper_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '操作人员',
  `dept_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '部门名称',
  `oper_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '请求URL',
  `oper_ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '主机地址',
  `oper_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '操作地点',
  `oper_param` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '请求参数',
  `status` int(1) NULL DEFAULT 0 COMMENT '操作状态（0正常 1异常）',
  `error_msg` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '错误消息',
  `oper_time` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
  `provider_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`oper_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 273 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '操作日志记录' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_oper_log
-- ----------------------------
INSERT INTO `sys_oper_log` VALUES (1, '用户管理', 3, 'com.mmtax.web.controller.system.SysUserController.editSave()', 1, 'admin', '研发部门', '/system/user/edit', '127.0.0.1', '内网IP', '{\r\n  \"userId\" : [ \"1\" ],\r\n  \"deptId\" : [ \"103\" ],\r\n  \"userName\" : [ \"宠物店管理员\" ],\r\n  \"dept.deptName\" : [ \"研发部门\" ],\r\n  \"phonenumber\" : [ \"15888888888\" ],\r\n  \"email\" : [ \"liangfan1104@163.com\" ],\r\n  \"loginName\" : [ \"admin\" ],\r\n  \"sex\" : [ \"1\" ],\r\n  \"role\" : [ \"1\" ],\r\n  \"remark\" : [ \"管理员\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"roleIds\" : [ \"1\" ],\r\n  \"postIds\" : [ \"1\" ]\r\n}', 0, NULL, '2021-04-05 22:06:54', 1);
INSERT INTO `sys_oper_log` VALUES (2, '用户管理', 2, 'com.mmtax.web.controller.system.SysUserController.addSave()', 1, 'admin', '研发部门', '/system/user/add', '127.0.0.1', '内网IP', '{\r\n  \"deptId\" : [ \"108\" ],\r\n  \"userName\" : [ \"梁凡\" ],\r\n  \"deptName\" : [ \"市场部门\" ],\r\n  \"phonenumber\" : [ \"13107280912\" ],\r\n  \"email\" : [ \"123@qq.com\" ],\r\n  \"password\" : [ \"123456\" ],\r\n  \"sex\" : [ \"0\" ],\r\n  \"loginName\" : [ \"13107280912\" ],\r\n  \"role\" : [ \"2\" ],\r\n  \"remark\" : [ \"测试\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"roleIds\" : [ \"2\" ],\r\n  \"postIds\" : [ \"4\" ]\r\n}', 1, '\r\n### Error updating database.  Cause: java.sql.SQLIntegrityConstraintViolationException: Duplicate entry \'2-2\' for key \'PRIMARY\'\r\n### The error may involve defaultParameterMap\r\n### The error occurred while setting parameters\r\n### SQL: INSERT INTO sys_user_role (user_id, role_id, provider_id) VALUES (?, ?, 1)\r\n### Cause: java.sql.SQLIntegrityConstraintViolationException: Duplicate entry \'2-2\' for key \'PRIMARY\'\n; Duplicate entry \'2-2\' for key \'PRIMARY\'; nested exception is java.sql.SQLIntegrityConstraintViolationException: Duplicate entry \'2-2\' for key \'PRIMARY\'', '2021-04-06 09:52:25', 1);
INSERT INTO `sys_oper_log` VALUES (3, '用户管理', 2, 'com.mmtax.web.controller.system.SysUserController.addSave()', 1, 'admin', '研发部门', '/system/user/add', '127.0.0.1', '内网IP', '{\r\n  \"deptId\" : [ \"108\" ],\r\n  \"userName\" : [ \"梁凡\" ],\r\n  \"deptName\" : [ \"市场部门\" ],\r\n  \"phonenumber\" : [ \"13107280912\" ],\r\n  \"email\" : [ \"851499434@qq.com\" ],\r\n  \"password\" : [ \"123456\" ],\r\n  \"sex\" : [ \"0\" ],\r\n  \"loginName\" : [ \"13107280912\" ],\r\n  \"role\" : [ \"2\" ],\r\n  \"remark\" : [ \"随意\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"roleIds\" : [ \"2\" ],\r\n  \"postIds\" : [ \"4\" ]\r\n}', 0, NULL, '2021-04-06 10:28:49', 1);
INSERT INTO `sys_oper_log` VALUES (4, '角色管理', 3, 'com.mmtax.web.controller.system.SysRoleController.editSave()', 1, 'admin', '研发部门', '/system/role/edit', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"2\" ],\r\n  \"roleName\" : [ \"普通角色\" ],\r\n  \"roleKey\" : [ \"common\" ],\r\n  \"roleSort\" : [ \"2\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"普通角色\" ],\r\n  \"menuIds\" : [ \"1,100,1000,1001,1002,1003,1004,1005,1006,101,1007,1008,1009,1010,1011,102,1012,1013,1014,1015,103,1016,1017,1018,1019,104,1020,1021,1022,1023,1024,105,1025,1026,1027,1028,1029,106,1030,1031,1032,1033,1034,107,1035,1036,1037,1038,108,500,1039,1040,1041,1042,501,1043,1044,1045\" ]\r\n}', 0, NULL, '2021-04-06 10:32:26', 1);
INSERT INTO `sys_oper_log` VALUES (5, '菜单管理', 2, 'com.mmtax.web.controller.system.SysMenuController.addSave()', 1, 'admin', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"常见问题\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"9\" ],\r\n  \"icon\" : [ \"fa fa-question-circle\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-06 20:07:41', 1);
INSERT INTO `sys_oper_log` VALUES (6, '菜单管理', 3, 'com.mmtax.web.controller.system.SysMenuController.editSave()', 1, 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2244\" ],\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"常见问题\" ],\r\n  \"url\" : [ \"#\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"7\" ],\r\n  \"icon\" : [ \"fa fa-question-circle\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-06 20:07:58', 1);
INSERT INTO `sys_oper_log` VALUES (7, '菜单管理', 3, 'com.mmtax.web.controller.system.SysMenuController.editSave()', 1, 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2244\" ],\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"常见问题管理\" ],\r\n  \"url\" : [ \"#\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"7\" ],\r\n  \"icon\" : [ \"fa fa-question-circle\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-06 20:09:57', 1);
INSERT INTO `sys_oper_log` VALUES (8, '菜单管理', 2, 'com.mmtax.web.controller.system.SysMenuController.addSave()', 1, 'admin', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2244\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"常见问题\" ],\r\n  \"url\" : [ \"business/commonProblem/commonProblem\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:commonProblem:view\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-06 20:11:47', 1);
INSERT INTO `sys_oper_log` VALUES (9, '菜单管理', 3, 'com.mmtax.web.controller.system.SysMenuController.editSave()', 1, 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2245\" ],\r\n  \"parentId\" : [ \"2244\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"常见问题\" ],\r\n  \"url\" : [ \"business/commonProblem/commonProblem\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:commonProblem:view\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-06 20:13:44', 1);
INSERT INTO `sys_oper_log` VALUES (10, '菜单管理', 3, 'com.mmtax.web.controller.system.SysMenuController.editSave()', 1, 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2245\" ],\r\n  \"parentId\" : [ \"2244\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"常见问题\" ],\r\n  \"url\" : [ \"business/commonProblem\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:commonProblem:view\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-06 20:16:59', 1);
INSERT INTO `sys_oper_log` VALUES (11, '常见问题', 4, 'com.mmtax.web.controller.business.CommonProblemController.remove()', 1, 'admin', '研发部门', '/business/commonProblem/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"11\" ]\r\n}', 0, NULL, '2021-04-06 20:17:39', 1);
INSERT INTO `sys_oper_log` VALUES (12, '菜单管理', 2, 'com.mmtax.web.controller.system.SysMenuController.addSave()', 1, 'admin', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"宠物医疗管理\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"fa fa-plus-circle\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-06 20:20:39', 1);
INSERT INTO `sys_oper_log` VALUES (13, '菜单管理', 2, 'com.mmtax.web.controller.system.SysMenuController.addSave()', 1, 'admin', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"用户管理\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"fa fa-user-circle-o\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-06 20:21:07', 1);
INSERT INTO `sys_oper_log` VALUES (14, '菜单管理', 2, 'com.mmtax.web.controller.system.SysMenuController.addSave()', 1, 'admin', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"宠物管理\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"3\" ],\r\n  \"icon\" : [ \"fa fa-heart\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-06 20:21:49', 1);
INSERT INTO `sys_oper_log` VALUES (15, '菜单管理', 3, 'com.mmtax.web.controller.system.SysMenuController.editSave()', 1, 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2248\" ],\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"宠物管理\" ],\r\n  \"url\" : [ \"#\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"3\" ],\r\n  \"icon\" : [ \"fa fa-paw\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-06 20:23:33', 1);
INSERT INTO `sys_oper_log` VALUES (16, '菜单管理', 2, 'com.mmtax.web.controller.system.SysMenuController.addSave()', 1, 'admin', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"宠物销售管理\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"4\" ],\r\n  \"icon\" : [ \"fa fa-bell\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-06 20:23:51', 1);
INSERT INTO `sys_oper_log` VALUES (17, '菜单管理', 2, 'com.mmtax.web.controller.system.SysMenuController.addSave()', 1, 'admin', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"宠物寄养管理\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"5\" ],\r\n  \"icon\" : [ \"fa fa-wifi\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-06 20:24:31', 1);
INSERT INTO `sys_oper_log` VALUES (18, '菜单管理', 2, 'com.mmtax.web.controller.system.SysMenuController.addSave()', 1, 'admin', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"宠物用品管理\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"6\" ],\r\n  \"icon\" : [ \"fa fa-bell\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-06 20:24:53', 1);
INSERT INTO `sys_oper_log` VALUES (19, '菜单管理', 2, 'com.mmtax.web.controller.system.SysMenuController.addSave()', 1, 'admin', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"宠物日常服务管理\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"7\" ],\r\n  \"icon\" : [ \"fa fa-futbol-o\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-06 20:25:28', 1);
INSERT INTO `sys_oper_log` VALUES (20, '菜单管理', 3, 'com.mmtax.web.controller.system.SysMenuController.editSave()', 1, 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2244\" ],\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"常见问题管理\" ],\r\n  \"url\" : [ \"#\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"8\" ],\r\n  \"icon\" : [ \"fa fa-question-circle\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-06 20:25:46', 1);
INSERT INTO `sys_oper_log` VALUES (21, '菜单管理', 3, 'com.mmtax.web.controller.system.SysMenuController.editSave()', 1, 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"1\" ],\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"系统管理\" ],\r\n  \"url\" : [ \"#\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"9\" ],\r\n  \"icon\" : [ \"fa fa-gear\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-06 20:25:51', 1);
INSERT INTO `sys_oper_log` VALUES (22, '菜单管理', 3, 'com.mmtax.web.controller.system.SysMenuController.editSave()', 1, 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"3\" ],\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"系统工具\" ],\r\n  \"url\" : [ \"#\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"10\" ],\r\n  \"icon\" : [ \"fa fa-bars\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-06 20:25:58', 1);
INSERT INTO `sys_oper_log` VALUES (23, '菜单管理', 3, 'com.mmtax.web.controller.system.SysMenuController.editSave()', 1, 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2\" ],\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"系统监控\" ],\r\n  \"url\" : [ \"#\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"11\" ],\r\n  \"icon\" : [ \"fa fa-video-camera\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-06 20:26:04', 1);
INSERT INTO `sys_oper_log` VALUES (24, '个人信息', 3, 'com.mmtax.web.controller.system.SysProfileController.updateAvatar()', 1, 'admin', '研发部门', '/system/user/profile/updateAvatar', '127.0.0.1', '内网IP', '{ }', 0, NULL, '2021-04-06 20:30:13', 1);
INSERT INTO `sys_oper_log` VALUES (25, '个人信息', 3, 'com.mmtax.web.controller.system.SysProfileController.updateAvatar()', 1, 'admin', '研发部门', '/system/user/profile/updateAvatar', '127.0.0.1', '内网IP', '{ }', 0, NULL, '2021-04-06 20:30:47', 1);
INSERT INTO `sys_oper_log` VALUES (26, '个人信息', 3, 'com.mmtax.web.controller.system.SysProfileController.updateAvatar()', 1, 'admin', '研发部门', '/system/user/profile/updateAvatar', '127.0.0.1', '内网IP', '{ }', 0, NULL, '2021-04-06 20:30:52', 1);
INSERT INTO `sys_oper_log` VALUES (27, '个人信息', 3, 'com.mmtax.web.controller.system.SysProfileController.update()', 1, 'admin', '研发部门', '/system/user/profile/update', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"\" ],\r\n  \"userName\" : [ \"宠物店管理员\" ],\r\n  \"phonenumber\" : [ \"15888888888\" ],\r\n  \"email\" : [ \"liangfan1104@163.com\" ],\r\n  \"sex\" : [ \"1\" ]\r\n}', 0, NULL, '2021-04-06 20:32:24', 1);
INSERT INTO `sys_oper_log` VALUES (28, '常见问题', 3, 'com.mmtax.web.controller.business.CommonProblemController.editSave()', 1, 'admin', '研发部门', '/business/commonProblem/edit', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"8\" ],\r\n  \"problem\" : [ \"袅袅娜娜\" ],\r\n  \"answer\" : [ \"你是猪\" ]\r\n}', 0, NULL, '2021-04-07 08:26:18', 1);
INSERT INTO `sys_oper_log` VALUES (29, '重置密码', 3, 'com.mmtax.web.controller.system.SysUserController.resetPwd()', 1, 'admin', '研发部门', '/system/user/resetPwd/3', '127.0.0.1', '内网IP', '{ }', 0, NULL, '2021-04-07 08:54:26', 1);
INSERT INTO `sys_oper_log` VALUES (30, '菜单管理', 3, 'com.mmtax.web.controller.system.SysMenuController.editSave()', 1, 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"100\" ],\r\n  \"parentId\" : [ \"2247\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"用户管理\" ],\r\n  \"url\" : [ \"/system/user\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"system:user:view\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-07 09:00:28', 1);
INSERT INTO `sys_oper_log` VALUES (31, '菜单管理', 3, 'com.mmtax.web.controller.system.SysMenuController.editSave()', 1, 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"101\" ],\r\n  \"parentId\" : [ \"2247\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"角色管理\" ],\r\n  \"url\" : [ \"/system/role\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"system:role:view\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-07 09:00:37', 1);
INSERT INTO `sys_oper_log` VALUES (32, '菜单管理', 3, 'com.mmtax.web.controller.system.SysMenuController.editSave()', 1, 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"103\" ],\r\n  \"parentId\" : [ \"2247\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"部门管理\" ],\r\n  \"url\" : [ \"/system/dept\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"system:dept:view\" ],\r\n  \"orderNum\" : [ \"4\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-07 09:00:47', 1);
INSERT INTO `sys_oper_log` VALUES (33, '菜单管理', 3, 'com.mmtax.web.controller.system.SysMenuController.editSave()', 1, 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"104\" ],\r\n  \"parentId\" : [ \"2247\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"岗位管理\" ],\r\n  \"url\" : [ \"/system/post\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"system:post:view\" ],\r\n  \"orderNum\" : [ \"5\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-07 09:00:57', 1);
INSERT INTO `sys_oper_log` VALUES (34, '参数管理', 3, 'com.mmtax.web.controller.system.SysConfigController.editSave()', 1, 'admin', '研发部门', '/system/config/edit', '127.0.0.1', '内网IP', '{\r\n  \"configId\" : [ \"1\" ],\r\n  \"configName\" : [ \"主框架页-默认皮肤样式名称\" ],\r\n  \"configKey\" : [ \"sys.index.skinName\" ],\r\n  \"configValue\" : [ \"skin-green\" ],\r\n  \"configType\" : [ \"Y\" ],\r\n  \"remark\" : [ \"蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow\" ]\r\n}', 0, NULL, '2021-04-09 09:52:58', 1);
INSERT INTO `sys_oper_log` VALUES (35, '参数管理', 3, 'com.mmtax.web.controller.system.SysConfigController.editSave()', 1, 'admin', '研发部门', '/system/config/edit', '127.0.0.1', '内网IP', '{\r\n  \"configId\" : [ \"1\" ],\r\n  \"configName\" : [ \"主框架页-默认皮肤样式名称\" ],\r\n  \"configKey\" : [ \"sys.index.skinName\" ],\r\n  \"configValue\" : [ \"skin-blue\" ],\r\n  \"configType\" : [ \"Y\" ],\r\n  \"remark\" : [ \"蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow\" ]\r\n}', 0, NULL, '2021-04-09 09:53:17', 1);
INSERT INTO `sys_oper_log` VALUES (36, '菜单管理', 3, 'com.mmtax.web.controller.system.SysMenuController.editSave()', 1, 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2248\" ],\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"宠物管理\" ],\r\n  \"url\" : [ \"#\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"fa fa-paw\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-09 10:12:30', 1);
INSERT INTO `sys_oper_log` VALUES (37, '菜单管理', 3, 'com.mmtax.web.controller.system.SysMenuController.editSave()', 1, 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2246\" ],\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"宠物医疗管理\" ],\r\n  \"url\" : [ \"#\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"fa fa-plus-circle\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-09 10:12:41', 1);
INSERT INTO `sys_oper_log` VALUES (38, '菜单管理', 3, 'com.mmtax.web.controller.system.SysMenuController.editSave()', 1, 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2249\" ],\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"宠物销售管理\" ],\r\n  \"url\" : [ \"#\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"3\" ],\r\n  \"icon\" : [ \"fa fa-bell\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-09 10:12:49', 1);
INSERT INTO `sys_oper_log` VALUES (39, '菜单管理', 3, 'com.mmtax.web.controller.system.SysMenuController.editSave()', 1, 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2250\" ],\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"宠物寄养管理\" ],\r\n  \"url\" : [ \"#\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"4\" ],\r\n  \"icon\" : [ \"fa fa-wifi\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-09 10:12:54', 1);
INSERT INTO `sys_oper_log` VALUES (40, '菜单管理', 3, 'com.mmtax.web.controller.system.SysMenuController.editSave()', 1, 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2251\" ],\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"宠物用品管理\" ],\r\n  \"url\" : [ \"#\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"5\" ],\r\n  \"icon\" : [ \"fa fa-bell\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-09 10:12:59', 1);
INSERT INTO `sys_oper_log` VALUES (41, '菜单管理', 3, 'com.mmtax.web.controller.system.SysMenuController.editSave()', 1, 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2252\" ],\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"宠物日常服务管理\" ],\r\n  \"url\" : [ \"#\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"6\" ],\r\n  \"icon\" : [ \"fa fa-futbol-o\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-09 10:13:08', 1);
INSERT INTO `sys_oper_log` VALUES (42, '菜单管理', 3, 'com.mmtax.web.controller.system.SysMenuController.editSave()', 1, 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2247\" ],\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"用户管理\" ],\r\n  \"url\" : [ \"#\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"8\" ],\r\n  \"icon\" : [ \"fa fa-user-circle-o\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-09 10:13:17', 1);
INSERT INTO `sys_oper_log` VALUES (43, '菜单管理', 3, 'com.mmtax.web.controller.system.SysMenuController.editSave()', 1, 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2244\" ],\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"常见问题管理\" ],\r\n  \"url\" : [ \"#\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"7\" ],\r\n  \"icon\" : [ \"fa fa-question-circle\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-09 10:13:23', 1);
INSERT INTO `sys_oper_log` VALUES (44, '菜单管理', 4, 'com.mmtax.web.controller.system.SysMenuController.remove()', 1, 'admin', '研发部门', '/system/menu/remove/111', '127.0.0.1', '内网IP', '{ }', 0, NULL, '2021-04-09 10:15:15', 1);
INSERT INTO `sys_oper_log` VALUES (45, '菜单管理', 4, 'com.mmtax.web.controller.system.SysMenuController.remove()', 1, 'admin', '研发部门', '/system/menu/remove/111', '127.0.0.1', '内网IP', '{ }', 0, NULL, '2021-04-09 10:15:32', 1);
INSERT INTO `sys_oper_log` VALUES (46, '菜单管理', 3, 'com.mmtax.web.controller.system.SysMenuController.editSave()', 1, 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"111\" ],\r\n  \"parentId\" : [ \"2\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"数据监控\" ],\r\n  \"url\" : [ \"/monitor/data\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"monitor:data:view\" ],\r\n  \"orderNum\" : [ \"3\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"1\" ]\r\n}', 0, NULL, '2021-04-09 10:17:10', 1);
INSERT INTO `sys_oper_log` VALUES (47, '菜单管理', 3, 'com.mmtax.web.controller.system.SysMenuController.editSave()', 1, 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2\" ],\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"系统监控\" ],\r\n  \"url\" : [ \"#\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"11\" ],\r\n  \"icon\" : [ \"fa fa-video-camera\" ],\r\n  \"visible\" : [ \"1\" ]\r\n}', 0, NULL, '2021-04-09 10:25:19', 1);
INSERT INTO `sys_oper_log` VALUES (48, '代码生成', 9, 'com.mmtax.generator.controller.GenController.genCode()', 1, 'admin', '研发部门', '/tool/gen/genCode/tbl_pet_info', '127.0.0.1', '内网IP', '{ }', 0, NULL, '2021-04-09 11:02:28', 1);
INSERT INTO `sys_oper_log` VALUES (49, '部门管理', 3, 'com.mmtax.web.controller.system.SysDeptController.editSave()', 1, 'admin', '研发部门', '/system/dept/edit', '127.0.0.1', '内网IP', '{\r\n  \"deptId\" : [ \"103\" ],\r\n  \"parentId\" : [ \"101\" ],\r\n  \"parentName\" : [ \"怀化宠物分店\" ],\r\n  \"deptName\" : [ \"医疗部门\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"leader\" : [ \"若依\" ],\r\n  \"phone\" : [ \"15888888888\" ],\r\n  \"email\" : [ \"ry@qq.com\" ],\r\n  \"status\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-09 11:25:28', 1);
INSERT INTO `sys_oper_log` VALUES (50, '部门管理', 3, 'com.mmtax.web.controller.system.SysDeptController.editSave()', 1, 'admin', '研发部门', '/system/dept/edit', '127.0.0.1', '内网IP', '{\r\n  \"deptId\" : [ \"105\" ],\r\n  \"parentId\" : [ \"101\" ],\r\n  \"parentName\" : [ \"怀化宠物分店\" ],\r\n  \"deptName\" : [ \"销售部门\" ],\r\n  \"orderNum\" : [ \"3\" ],\r\n  \"leader\" : [ \"若依\" ],\r\n  \"phone\" : [ \"15888888888\" ],\r\n  \"email\" : [ \"ry@qq.com\" ],\r\n  \"status\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-09 11:25:36', 1);
INSERT INTO `sys_oper_log` VALUES (51, '部门管理', 3, 'com.mmtax.web.controller.system.SysDeptController.editSave()', 1, 'admin', '研发部门', '/system/dept/edit', '127.0.0.1', '内网IP', '{\r\n  \"deptId\" : [ \"106\" ],\r\n  \"parentId\" : [ \"101\" ],\r\n  \"parentName\" : [ \"怀化宠物分店\" ],\r\n  \"deptName\" : [ \"管理部门\" ],\r\n  \"orderNum\" : [ \"4\" ],\r\n  \"leader\" : [ \"若依\" ],\r\n  \"phone\" : [ \"15888888888\" ],\r\n  \"email\" : [ \"ry@qq.com\" ],\r\n  \"status\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-09 11:38:44', 1);
INSERT INTO `sys_oper_log` VALUES (52, '部门管理', 4, 'com.mmtax.web.controller.system.SysDeptController.remove()', 1, 'admin', '研发部门', '/system/dept/remove/107', '127.0.0.1', '内网IP', '{ }', 0, NULL, '2021-04-09 11:38:48', 1);
INSERT INTO `sys_oper_log` VALUES (53, '用户管理', 3, 'com.mmtax.web.controller.system.SysUserController.editSave()', 1, 'admin', '医疗部门', '/system/user/edit', '127.0.0.1', '内网IP', '{\r\n  \"userId\" : [ \"2\" ],\r\n  \"deptId\" : [ \"106\" ],\r\n  \"userName\" : [ \"梁凡\" ],\r\n  \"dept.deptName\" : [ \"管理部门\" ],\r\n  \"phonenumber\" : [ \"13107280912\" ],\r\n  \"email\" : [ \"851499434@qq.com\" ],\r\n  \"loginName\" : [ \"13107280912\" ],\r\n  \"sex\" : [ \"0\" ],\r\n  \"role\" : [ \"1\" ],\r\n  \"remark\" : [ \"随意\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"roleIds\" : [ \"1\" ],\r\n  \"postIds\" : [ \"1,2\" ]\r\n}', 0, NULL, '2021-04-10 11:37:59', 1);
INSERT INTO `sys_oper_log` VALUES (54, '代码生成', 9, 'com.mmtax.generator.controller.GenController.genCode()', 1, 'admin', '医疗部门', '/tool/gen/genCode/tbl_pet_info', '127.0.0.1', '内网IP', '{ }', 0, NULL, '2021-04-10 12:24:47', 1);
INSERT INTO `sys_oper_log` VALUES (55, '菜单管理', 2, 'com.mmtax.web.controller.system.SysMenuController.addSave()', 1, 'admin', '医疗部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2248\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"宠物信息列表\" ],\r\n  \"url\" : [ \"business/petInfo/petInfo\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:petInfo:view\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-10 14:08:56', 1);
INSERT INTO `sys_oper_log` VALUES (56, '菜单管理', 3, 'com.mmtax.web.controller.system.SysMenuController.editSave()', 1, 'admin', '医疗部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2253\" ],\r\n  \"parentId\" : [ \"2248\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"宠物信息列表\" ],\r\n  \"url\" : [ \"business/petInfo\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:petInfo:view\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-10 14:11:27', 1);
INSERT INTO `sys_oper_log` VALUES (57, '字典类型', 2, 'com.mmtax.web.controller.system.SysDictTypeController.addSave()', 1, 'admin', '医疗部门', '/system/dict/add', '127.0.0.1', '内网IP', '{\r\n  \"dictName\" : [ \"宠物信息类型\" ],\r\n  \"dictType\" : [ \"pet_info_type\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"宠物信息类型 0 店养宠物 1 医疗宠物 2 销售宠物 3寄养宠物\" ]\r\n}', 0, NULL, '2021-04-10 15:02:32', 1);
INSERT INTO `sys_oper_log` VALUES (58, '字典数据', 2, 'com.mmtax.web.controller.system.SysDictDataController.addSave()', 1, 'admin', '医疗部门', '/system/dict/data/add', '127.0.0.1', '内网IP', '{\r\n  \"dictLabel\" : [ \"店养宠物\" ],\r\n  \"dictValue\" : [ \"0\" ],\r\n  \"dictType\" : [ \"pet_info_type\" ],\r\n  \"cssClass\" : [ \"\" ],\r\n  \"dictSort\" : [ \"1\" ],\r\n  \"listClass\" : [ \"\" ],\r\n  \"isDefault\" : [ \"Y\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', 0, NULL, '2021-04-10 15:03:41', 1);
INSERT INTO `sys_oper_log` VALUES (59, '字典数据', 2, 'com.mmtax.web.controller.system.SysDictDataController.addSave()', 1, 'admin', '医疗部门', '/system/dict/data/add', '127.0.0.1', '内网IP', '{\r\n  \"dictLabel\" : [ \"医疗宠物\" ],\r\n  \"dictValue\" : [ \"1\" ],\r\n  \"dictType\" : [ \"pet_info_type\" ],\r\n  \"cssClass\" : [ \"\" ],\r\n  \"dictSort\" : [ \"2\" ],\r\n  \"listClass\" : [ \"\" ],\r\n  \"isDefault\" : [ \"Y\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', 0, NULL, '2021-04-10 15:04:00', 1);
INSERT INTO `sys_oper_log` VALUES (60, '字典数据', 2, 'com.mmtax.web.controller.system.SysDictDataController.addSave()', 1, 'admin', '医疗部门', '/system/dict/data/add', '127.0.0.1', '内网IP', '{\r\n  \"dictLabel\" : [ \"销售宠物\" ],\r\n  \"dictValue\" : [ \"2\" ],\r\n  \"dictType\" : [ \"pet_info_type\" ],\r\n  \"cssClass\" : [ \"\" ],\r\n  \"dictSort\" : [ \"3\" ],\r\n  \"listClass\" : [ \"primary\" ],\r\n  \"isDefault\" : [ \"Y\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', 0, NULL, '2021-04-10 15:04:30', 1);
INSERT INTO `sys_oper_log` VALUES (61, '字典数据', 3, 'com.mmtax.web.controller.system.SysDictDataController.editSave()', 1, 'admin', '医疗部门', '/system/dict/data/edit', '127.0.0.1', '内网IP', '{\r\n  \"dictCode\" : [ \"28\" ],\r\n  \"dictLabel\" : [ \"医疗宠物\" ],\r\n  \"dictValue\" : [ \"1\" ],\r\n  \"dictType\" : [ \"pet_info_type\" ],\r\n  \"cssClass\" : [ \"\" ],\r\n  \"dictSort\" : [ \"2\" ],\r\n  \"listClass\" : [ \"warning\" ],\r\n  \"isDefault\" : [ \"Y\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', 0, NULL, '2021-04-10 15:04:33', 1);
INSERT INTO `sys_oper_log` VALUES (62, '字典数据', 3, 'com.mmtax.web.controller.system.SysDictDataController.editSave()', 1, 'admin', '医疗部门', '/system/dict/data/edit', '127.0.0.1', '内网IP', '{\r\n  \"dictCode\" : [ \"27\" ],\r\n  \"dictLabel\" : [ \"店养宠物\" ],\r\n  \"dictValue\" : [ \"0\" ],\r\n  \"dictType\" : [ \"pet_info_type\" ],\r\n  \"cssClass\" : [ \"\" ],\r\n  \"dictSort\" : [ \"1\" ],\r\n  \"listClass\" : [ \"default\" ],\r\n  \"isDefault\" : [ \"Y\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', 0, NULL, '2021-04-10 15:04:38', 1);
INSERT INTO `sys_oper_log` VALUES (63, '字典数据', 3, 'com.mmtax.web.controller.system.SysDictDataController.editSave()', 1, 'admin', '医疗部门', '/system/dict/data/edit', '127.0.0.1', '内网IP', '{\r\n  \"dictCode\" : [ \"27\" ],\r\n  \"dictLabel\" : [ \"店养宠物\" ],\r\n  \"dictValue\" : [ \"0\" ],\r\n  \"dictType\" : [ \"pet_info_type\" ],\r\n  \"cssClass\" : [ \"\" ],\r\n  \"dictSort\" : [ \"1\" ],\r\n  \"listClass\" : [ \"default\" ],\r\n  \"isDefault\" : [ \"Y\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', 0, NULL, '2021-04-10 15:04:38', 1);
INSERT INTO `sys_oper_log` VALUES (64, '字典数据', 2, 'com.mmtax.web.controller.system.SysDictDataController.addSave()', 1, 'admin', '医疗部门', '/system/dict/data/add', '127.0.0.1', '内网IP', '{\r\n  \"dictLabel\" : [ \"寄养宠物\" ],\r\n  \"dictValue\" : [ \"3\" ],\r\n  \"dictType\" : [ \"pet_info_type\" ],\r\n  \"cssClass\" : [ \"\" ],\r\n  \"dictSort\" : [ \"4\" ],\r\n  \"listClass\" : [ \"info\" ],\r\n  \"isDefault\" : [ \"Y\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', 0, NULL, '2021-04-10 15:04:55', 1);
INSERT INTO `sys_oper_log` VALUES (65, '字典类型', 2, 'com.mmtax.web.controller.system.SysDictTypeController.addSave()', 1, 'admin', '医疗部门', '/system/dict/add', '127.0.0.1', '内网IP', '{\r\n  \"dictName\" : [ \"宠物性别\" ],\r\n  \"dictType\" : [ \"pet_sex\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', 0, NULL, '2021-04-10 15:05:30', 1);
INSERT INTO `sys_oper_log` VALUES (66, '字典数据', 2, 'com.mmtax.web.controller.system.SysDictDataController.addSave()', 1, 'admin', '医疗部门', '/system/dict/data/add', '127.0.0.1', '内网IP', '{\r\n  \"dictLabel\" : [ \"公\" ],\r\n  \"dictValue\" : [ \"0\" ],\r\n  \"dictType\" : [ \"pet_sex\" ],\r\n  \"cssClass\" : [ \"\" ],\r\n  \"dictSort\" : [ \"1\" ],\r\n  \"listClass\" : [ \"primary\" ],\r\n  \"isDefault\" : [ \"Y\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', 0, NULL, '2021-04-10 15:05:51', 1);
INSERT INTO `sys_oper_log` VALUES (67, '字典数据', 2, 'com.mmtax.web.controller.system.SysDictDataController.addSave()', 1, 'admin', '医疗部门', '/system/dict/data/add', '127.0.0.1', '内网IP', '{\r\n  \"dictLabel\" : [ \"母\" ],\r\n  \"dictValue\" : [ \"1\" ],\r\n  \"dictType\" : [ \"pet_sex\" ],\r\n  \"cssClass\" : [ \"\" ],\r\n  \"dictSort\" : [ \"2\" ],\r\n  \"listClass\" : [ \"info\" ],\r\n  \"isDefault\" : [ \"Y\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', 0, NULL, '2021-04-10 15:06:06', 1);
INSERT INTO `sys_oper_log` VALUES (68, '字典数据', 3, 'com.mmtax.web.controller.system.SysDictDataController.editSave()', 1, 'admin', '医疗部门', '/system/dict/data/edit', '127.0.0.1', '内网IP', '{\r\n  \"dictCode\" : [ \"27\" ],\r\n  \"dictLabel\" : [ \"店养宠物\" ],\r\n  \"dictValue\" : [ \"0\" ],\r\n  \"dictType\" : [ \"pet_info_type\" ],\r\n  \"cssClass\" : [ \"\" ],\r\n  \"dictSort\" : [ \"1\" ],\r\n  \"listClass\" : [ \"success\" ],\r\n  \"isDefault\" : [ \"Y\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', 0, NULL, '2021-04-10 15:19:53', 1);
INSERT INTO `sys_oper_log` VALUES (69, '字典数据', 2, 'com.mmtax.web.controller.system.SysDictDataController.addSave()', 1, 'admin', '医疗部门', '/system/dict/data/add', '127.0.0.1', '内网IP', '{\r\n  \"dictLabel\" : [ \"位置\" ],\r\n  \"dictValue\" : [ \"2\" ],\r\n  \"dictType\" : [ \"pet_sex\" ],\r\n  \"cssClass\" : [ \"\" ],\r\n  \"dictSort\" : [ \"3\" ],\r\n  \"listClass\" : [ \"warning\" ],\r\n  \"isDefault\" : [ \"Y\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', 0, NULL, '2021-04-10 15:31:20', 1);
INSERT INTO `sys_oper_log` VALUES (70, '字典数据', 3, 'com.mmtax.web.controller.system.SysDictDataController.editSave()', 1, 'admin', '医疗部门', '/system/dict/data/edit', '127.0.0.1', '内网IP', '{\r\n  \"dictCode\" : [ \"33\" ],\r\n  \"dictLabel\" : [ \"未知\" ],\r\n  \"dictValue\" : [ \"2\" ],\r\n  \"dictType\" : [ \"pet_sex\" ],\r\n  \"cssClass\" : [ \"\" ],\r\n  \"dictSort\" : [ \"3\" ],\r\n  \"listClass\" : [ \"warning\" ],\r\n  \"isDefault\" : [ \"Y\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', 0, NULL, '2021-04-10 15:31:25', 1);
INSERT INTO `sys_oper_log` VALUES (71, '宠物', 2, 'com.mmtax.web.controller.business.PetInfoController.addSave()', 1, 'admin', '医疗部门', '/business/petInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"petName\" : [ \"1\" ],\r\n  \"petType\" : [ \"1\" ],\r\n  \"sex\" : [ \"0\" ],\r\n  \"petAge\" : [ \"1\" ],\r\n  \"petInfoType\" : [ \"0\" ],\r\n  \"remake\" : [ \"1\" ],\r\n  \"createTime\" : [ \"1\" ],\r\n  \"updateTime\" : [ \"1\" ]\r\n}', 0, NULL, '2021-04-10 15:32:51', 1);
INSERT INTO `sys_oper_log` VALUES (72, '代码生成', 9, 'com.mmtax.generator.controller.GenController.genCode()', 1, 'admin', '医疗部门', '/tool/gen/genCode/tbl_pet_master_info', '127.0.0.1', '内网IP', '{ }', 0, NULL, '2021-04-10 17:33:48', 1);
INSERT INTO `sys_oper_log` VALUES (73, '宠物', 2, 'com.mmtax.web.controller.business.PetInfoController.addSave()', 1, 'admin', '医疗部门', '/business/petInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"petName\" : [ \"皮特\" ],\r\n  \"petType\" : [ \"哈巴狗\" ],\r\n  \"petSex\" : [ \"0\" ],\r\n  \"petAge\" : [ \"1\" ],\r\n  \"petInfoType\" : [ \"0\" ],\r\n  \"remake\" : [ \"特殊宠物\" ],\r\n  \"name\" : [ \"机枪哥\" ],\r\n  \"age\" : [ \"2\" ],\r\n  \"sex\" : [ \"0\" ],\r\n  \"phonenumber\" : [ \"13100001234\" ],\r\n  \"email\" : [ \"851499434@qq.com\" ]\r\n}', 1, '', '2021-04-10 17:52:24', 1);
INSERT INTO `sys_oper_log` VALUES (74, '宠物', 2, 'com.mmtax.web.controller.business.PetInfoController.addSave()', 1, 'admin', '医疗部门', '/business/petInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"petName\" : [ \"皮特\" ],\r\n  \"petType\" : [ \"哈巴狗\" ],\r\n  \"petSex\" : [ \"0\" ],\r\n  \"petAge\" : [ \"1\" ],\r\n  \"petInfoType\" : [ \"0\" ],\r\n  \"remake\" : [ \"特殊宠物\" ],\r\n  \"name\" : [ \"机枪哥\" ],\r\n  \"age\" : [ \"2\" ],\r\n  \"sex\" : [ \"0\" ],\r\n  \"phonenumber\" : [ \"13100001234\" ],\r\n  \"email\" : [ \"851499434@qq.com\" ]\r\n}', 1, '', '2021-04-10 17:52:42', 1);
INSERT INTO `sys_oper_log` VALUES (75, '宠物', 2, 'com.mmtax.web.controller.business.PetInfoController.addSave()', 1, 'admin', '医疗部门', '/business/petInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"petName\" : [ \"皮特\" ],\r\n  \"petType\" : [ \"哈巴狗\" ],\r\n  \"petSex\" : [ \"0\" ],\r\n  \"petAge\" : [ \"1\" ],\r\n  \"petInfoType\" : [ \"0\" ],\r\n  \"remake\" : [ \"特殊宠物\" ],\r\n  \"name\" : [ \"机枪哥\" ],\r\n  \"age\" : [ \"2\" ],\r\n  \"sex\" : [ \"0\" ],\r\n  \"phonenumber\" : [ \"13100001234\" ],\r\n  \"email\" : [ \"851499434@qq.com\" ]\r\n}', 1, '', '2021-04-10 17:54:33', 1);
INSERT INTO `sys_oper_log` VALUES (76, '宠物', 2, 'com.mmtax.web.controller.business.PetInfoController.addSave()', 1, 'admin', '医疗部门', '/business/petInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"petName\" : [ \"皮特\" ],\r\n  \"petType\" : [ \"哈巴狗\" ],\r\n  \"petSex\" : [ \"0\" ],\r\n  \"petAge\" : [ \"1\" ],\r\n  \"petInfoType\" : [ \"0\" ],\r\n  \"remake\" : [ \"特殊宠物\" ],\r\n  \"name\" : [ \"机枪哥\" ],\r\n  \"age\" : [ \"2\" ],\r\n  \"sex\" : [ \"0\" ],\r\n  \"phonenumber\" : [ \"13100001234\" ],\r\n  \"email\" : [ \"851499434@qq.com\" ]\r\n}', 1, '', '2021-04-10 17:55:05', 1);
INSERT INTO `sys_oper_log` VALUES (77, '宠物', 2, 'com.mmtax.web.controller.business.PetInfoController.addSave()', 1, 'admin', '医疗部门', '/business/petInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"petName\" : [ \"皮特\" ],\r\n  \"petType\" : [ \"哈巴狗\" ],\r\n  \"petSex\" : [ \"0\" ],\r\n  \"petAge\" : [ \"1\" ],\r\n  \"petInfoType\" : [ \"0\" ],\r\n  \"remake\" : [ \"特殊宠物\" ],\r\n  \"name\" : [ \"机枪哥\" ],\r\n  \"age\" : [ \"2\" ],\r\n  \"sex\" : [ \"0\" ],\r\n  \"phonenumber\" : [ \"13100001234\" ],\r\n  \"email\" : [ \"851499434@qq.com\" ]\r\n}', 1, '', '2021-04-10 17:55:13', 1);
INSERT INTO `sys_oper_log` VALUES (78, '宠物', 2, 'com.mmtax.web.controller.business.PetInfoController.addSave()', 1, 'admin', '医疗部门', '/business/petInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"petName\" : [ \"1\" ],\r\n  \"petType\" : [ \"1\" ],\r\n  \"petSex\" : [ \"0\" ],\r\n  \"petAge\" : [ \"1\" ],\r\n  \"petInfoType\" : [ \"0\" ],\r\n  \"remake\" : [ \"1\" ],\r\n  \"name\" : [ \"1\" ],\r\n  \"age\" : [ \"1\" ],\r\n  \"sex\" : [ \"1\" ],\r\n  \"phonenumber\" : [ \"1\" ],\r\n  \"email\" : [ \"1\" ]\r\n}', 1, '', '2021-04-10 18:03:43', 1);
INSERT INTO `sys_oper_log` VALUES (79, '宠物', 2, 'com.mmtax.web.controller.business.PetInfoController.addSave()', 1, 'admin', '医疗部门', '/business/petInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"petName\" : [ \"1\" ],\r\n  \"petType\" : [ \"1\" ],\r\n  \"petSex\" : [ \"0\" ],\r\n  \"petAge\" : [ \"1\" ],\r\n  \"petInfoType\" : [ \"0\" ],\r\n  \"remake\" : [ \"1\" ],\r\n  \"name\" : [ \"1\" ],\r\n  \"age\" : [ \"1\" ],\r\n  \"sex\" : [ \"1\" ],\r\n  \"phonenumber\" : [ \"1\" ],\r\n  \"email\" : [ \"1\" ]\r\n}', 1, '为传入数据', '2021-04-10 18:04:39', 1);
INSERT INTO `sys_oper_log` VALUES (80, '宠物', 2, 'com.mmtax.web.controller.business.PetInfoController.addSave()', 1, 'admin', '医疗部门', '/business/petInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"petName\" : [ \"1\" ],\r\n  \"petType\" : [ \"1\" ],\r\n  \"petSex\" : [ \"0\" ],\r\n  \"petAge\" : [ \"1\" ],\r\n  \"petInfoType\" : [ \"0\" ],\r\n  \"remake\" : [ \"1\" ],\r\n  \"name\" : [ \"1\" ],\r\n  \"age\" : [ \"1\" ],\r\n  \"sex\" : [ \"1\" ],\r\n  \"phonenumber\" : [ \"1\" ],\r\n  \"email\" : [ \"1\" ]\r\n}', 1, '传入数据为空', '2021-04-10 18:05:14', 1);
INSERT INTO `sys_oper_log` VALUES (81, '常见问题', 2, 'com.mmtax.web.controller.business.CommonProblemController.addSave()', 1, 'admin', '医疗部门', '/business/commonProblem/add', '127.0.0.1', '内网IP', '{\r\n  \"problem\" : [ \"庄陶泽是不是傻逼\" ],\r\n  \"answer\" : [ \"是\" ]\r\n}', 0, NULL, '2021-04-10 21:23:24', 1);
INSERT INTO `sys_oper_log` VALUES (82, '宠物', 2, 'com.mmtax.web.controller.business.PetInfoController.addSave()', 1, 'admin', '医疗部门', '/business/petInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"petName\" : [ \"测试\" ],\r\n  \"petType\" : [ \"1\" ],\r\n  \"petSex\" : [ \"0\" ],\r\n  \"petAge\" : [ \"1\" ],\r\n  \"petInfoType\" : [ \"3\" ],\r\n  \"remake\" : [ \"1\" ],\r\n  \"name\" : [ \"测试主人\" ],\r\n  \"age\" : [ \"100\" ],\r\n  \"sex\" : [ \"1\" ],\r\n  \"phonenumber\" : [ \"12312312311\" ],\r\n  \"email\" : [ \"xx@qq.com\" ],\r\n  \"adress\" : [ \"asdasdasd\" ]\r\n}', 0, NULL, '2021-04-10 22:58:00', 1);
INSERT INTO `sys_oper_log` VALUES (83, '宠物', 4, 'com.mmtax.web.controller.business.PetInfoController.remove()', 1, 'admin', '医疗部门', '/business/petInfo/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"3\" ]\r\n}', 0, NULL, '2021-04-12 19:03:45', 1);
INSERT INTO `sys_oper_log` VALUES (84, '宠物', 3, 'com.mmtax.web.controller.business.PetInfoController.editSave()', 1, 'admin', '医疗部门', '/business/petInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"2\" ],\r\n  \"petName\" : [ \"露露\" ],\r\n  \"petType\" : [ \"怪物\" ],\r\n  \"petSex\" : [ \"1\" ],\r\n  \"petAge\" : [ \"2\" ],\r\n  \"petInfoType\" : [ \"0\" ],\r\n  \"photo\" : [ \"\" ],\r\n  \"remake\" : [ \"1\" ],\r\n  \"delStatus\" : [ \"0\" ],\r\n  \"providerId\" : [ \"1\" ],\r\n  \"createTime\" : [ \"Sat Apr 10 15:32:51 CST 2021\" ],\r\n  \"updateTime\" : [ \"Sat Apr 10 15:32:51 CST 2021\" ]\r\n}', 0, NULL, '2021-04-12 19:03:53', 1);
INSERT INTO `sys_oper_log` VALUES (85, '宠物', 3, 'com.mmtax.web.controller.business.PetInfoController.editSave()', 1, 'admin', '医疗部门', '/business/petInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"3\" ],\r\n  \"petName\" : [ \"测试\" ],\r\n  \"petType\" : [ \"1\" ],\r\n  \"petSex\" : [ \"0\" ],\r\n  \"petAge\" : [ \"1\" ],\r\n  \"petInfoType\" : [ \"1\" ],\r\n  \"remake\" : [ \"1\" ]\r\n}', 0, NULL, '2021-04-12 19:07:54', 1);
INSERT INTO `sys_oper_log` VALUES (86, '宠物', 3, 'com.mmtax.web.controller.business.PetInfoController.editSave()', 1, 'admin', '医疗部门', '/business/petInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"3\" ],\r\n  \"petName\" : [ \"测试\" ],\r\n  \"petType\" : [ \"1\" ],\r\n  \"petSex\" : [ \"0\" ],\r\n  \"petAge\" : [ \"1\" ],\r\n  \"petInfoType\" : [ \"3\" ],\r\n  \"remake\" : [ \"1\" ]\r\n}', 0, NULL, '2021-04-12 19:07:57', 1);
INSERT INTO `sys_oper_log` VALUES (87, '宠物', 3, 'com.mmtax.web.controller.business.PetInfoController.editSave()', 1, 'admin', '医疗部门', '/business/petInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"3\" ],\r\n  \"petName\" : [ \"测试\" ],\r\n  \"petType\" : [ \"1\" ],\r\n  \"petSex\" : [ \"0\" ],\r\n  \"petAge\" : [ \"1\" ],\r\n  \"petInfoType\" : [ \"3\" ],\r\n  \"remake\" : [ \"1\" ]\r\n}', 0, NULL, '2021-04-12 19:08:00', 1);
INSERT INTO `sys_oper_log` VALUES (88, '宠物', 3, 'com.mmtax.web.controller.business.PetInfoController.editSave()', 1, 'admin', '医疗部门', '/business/petInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"3\" ],\r\n  \"petName\" : [ \"测试\" ],\r\n  \"petType\" : [ \"1\" ],\r\n  \"petSex\" : [ \"1\" ],\r\n  \"petAge\" : [ \"1\" ],\r\n  \"petInfoType\" : [ \"0\" ],\r\n  \"remake\" : [ \"1\" ]\r\n}', 0, NULL, '2021-04-12 19:08:04', 1);
INSERT INTO `sys_oper_log` VALUES (89, '宠物', 3, 'com.mmtax.web.controller.business.PetInfoController.editSave()', 1, 'admin', '医疗部门', '/business/petInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"3\" ],\r\n  \"petName\" : [ \"测试\" ],\r\n  \"petType\" : [ \"1\" ],\r\n  \"petSex\" : [ \"0\" ],\r\n  \"petAge\" : [ \"1\" ],\r\n  \"petInfoType\" : [ \"3\" ],\r\n  \"remake\" : [ \"1\" ]\r\n}', 0, NULL, '2021-04-12 19:08:23', 1);
INSERT INTO `sys_oper_log` VALUES (90, '宠物', 3, 'com.mmtax.web.controller.business.PetInfoController.editSave()', 1, 'admin', '医疗部门', '/business/petInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"3\" ],\r\n  \"petName\" : [ \"测试\" ],\r\n  \"petType\" : [ \"1\" ],\r\n  \"petSex\" : [ \"0\" ],\r\n  \"petAge\" : [ \"1\" ],\r\n  \"petInfoType\" : [ \"3\" ],\r\n  \"remake\" : [ \"1\" ]\r\n}', 0, NULL, '2021-04-12 19:09:22', 1);
INSERT INTO `sys_oper_log` VALUES (91, '宠物', 3, 'com.mmtax.web.controller.business.PetInfoController.editSave()', 1, 'admin', '医疗部门', '/business/petInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"3\" ],\r\n  \"petName\" : [ \"测试\" ],\r\n  \"petType\" : [ \"1\" ],\r\n  \"petSex\" : [ \"0\" ],\r\n  \"petAge\" : [ \"1\" ],\r\n  \"petInfoType\" : [ \"1\" ],\r\n  \"remake\" : [ \"1\" ]\r\n}', 0, NULL, '2021-04-12 19:17:12', 1);
INSERT INTO `sys_oper_log` VALUES (92, '宠物', 3, 'com.mmtax.web.controller.business.PetInfoController.editSave()', 1, 'admin', '医疗部门', '/business/petInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"3\" ],\r\n  \"petName\" : [ \"测试\" ],\r\n  \"petType\" : [ \"1\" ],\r\n  \"petSex\" : [ \"0\" ],\r\n  \"petAge\" : [ \"1\" ],\r\n  \"petInfoType\" : [ \"\" ],\r\n  \"remake\" : [ \"1\" ]\r\n}', 0, NULL, '2021-04-12 19:18:06', 1);
INSERT INTO `sys_oper_log` VALUES (93, '宠物', 3, 'com.mmtax.web.controller.business.PetInfoController.editSave()', 1, 'admin', '医疗部门', '/business/petInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"3\" ],\r\n  \"petName\" : [ \"测试\" ],\r\n  \"petType\" : [ \"1\" ],\r\n  \"petSex\" : [ \"0\" ],\r\n  \"petAge\" : [ \"1\" ],\r\n  \"petInfoType\" : [ \"0\" ],\r\n  \"remake\" : [ \"1\" ]\r\n}', 0, NULL, '2021-04-12 19:24:37', 1);
INSERT INTO `sys_oper_log` VALUES (94, '宠物', 3, 'com.mmtax.web.controller.business.PetInfoController.editSave()', 1, 'admin', '医疗部门', '/business/petInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"3\" ],\r\n  \"petName\" : [ \"测试\" ],\r\n  \"petType\" : [ \"1\" ],\r\n  \"petSex\" : [ \"0\" ],\r\n  \"petAge\" : [ \"1\" ],\r\n  \"petInfoType\" : [ \"3\" ],\r\n  \"remake\" : [ \"1\" ]\r\n}', 0, NULL, '2021-04-12 19:24:41', 1);
INSERT INTO `sys_oper_log` VALUES (95, '宠物', 3, 'com.mmtax.web.controller.business.PetInfoController.editSave()', 1, 'admin', '医疗部门', '/business/petInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"3\" ],\r\n  \"petName\" : [ \"测试2\" ],\r\n  \"petType\" : [ \"猪\" ],\r\n  \"petSex\" : [ \"1\" ],\r\n  \"petAge\" : [ \"12\" ],\r\n  \"petInfoType\" : [ \"2\" ],\r\n  \"remake\" : [ \"买100块\" ]\r\n}', 0, NULL, '2021-04-12 19:27:29', 1);
INSERT INTO `sys_oper_log` VALUES (96, '字典数据', 3, 'com.mmtax.web.controller.system.SysDictDataController.editSave()', 1, 'admin', '医疗部门', '/system/dict/data/edit', '127.0.0.1', '内网IP', '{\r\n  \"dictCode\" : [ \"29\" ],\r\n  \"dictLabel\" : [ \"销售宠物\" ],\r\n  \"dictValue\" : [ \"2\" ],\r\n  \"dictType\" : [ \"pet_info_type\" ],\r\n  \"cssClass\" : [ \"\" ],\r\n  \"dictSort\" : [ \"3\" ],\r\n  \"listClass\" : [ \"primary\" ],\r\n  \"isDefault\" : [ \"N\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', 0, NULL, '2021-04-26 14:09:25', 1);
INSERT INTO `sys_oper_log` VALUES (97, '字典数据', 3, 'com.mmtax.web.controller.system.SysDictDataController.editSave()', 1, 'admin', '医疗部门', '/system/dict/data/edit', '127.0.0.1', '内网IP', '{\r\n  \"dictCode\" : [ \"29\" ],\r\n  \"dictLabel\" : [ \"销售宠物\" ],\r\n  \"dictValue\" : [ \"2\" ],\r\n  \"dictType\" : [ \"pet_info_type\" ],\r\n  \"cssClass\" : [ \"\" ],\r\n  \"dictSort\" : [ \"3\" ],\r\n  \"listClass\" : [ \"primary\" ],\r\n  \"isDefault\" : [ \"N\" ],\r\n  \"status\" : [ \"1\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', 0, NULL, '2021-04-26 14:09:47', 1);
INSERT INTO `sys_oper_log` VALUES (98, '字典数据', 3, 'com.mmtax.web.controller.system.SysDictDataController.editSave()', 1, 'admin', '医疗部门', '/system/dict/data/edit', '127.0.0.1', '内网IP', '{\r\n  \"dictCode\" : [ \"29\" ],\r\n  \"dictLabel\" : [ \"销售宠物\" ],\r\n  \"dictValue\" : [ \"2\" ],\r\n  \"dictType\" : [ \"pet_info_type\" ],\r\n  \"cssClass\" : [ \"\" ],\r\n  \"dictSort\" : [ \"3\" ],\r\n  \"listClass\" : [ \"primary\" ],\r\n  \"isDefault\" : [ \"N\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', 0, NULL, '2021-04-26 14:10:10', 1);
INSERT INTO `sys_oper_log` VALUES (99, '字典数据', 3, 'com.mmtax.web.controller.system.SysDictDataController.editSave()', 1, 'admin', '医疗部门', '/system/dict/data/edit', '127.0.0.1', '内网IP', '{\r\n  \"dictCode\" : [ \"27\" ],\r\n  \"dictLabel\" : [ \"店养宠物\" ],\r\n  \"dictValue\" : [ \"4\" ],\r\n  \"dictType\" : [ \"pet_info_type\" ],\r\n  \"cssClass\" : [ \"\" ],\r\n  \"dictSort\" : [ \"1\" ],\r\n  \"listClass\" : [ \"success\" ],\r\n  \"isDefault\" : [ \"Y\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', 0, NULL, '2021-04-26 14:11:32', 1);
INSERT INTO `sys_oper_log` VALUES (100, '字典数据', 3, 'com.mmtax.web.controller.system.SysDictDataController.editSave()', 1, 'admin', '医疗部门', '/system/dict/data/edit', '127.0.0.1', '内网IP', '{\r\n  \"dictCode\" : [ \"27\" ],\r\n  \"dictLabel\" : [ \"店养宠物\" ],\r\n  \"dictValue\" : [ \"0\" ],\r\n  \"dictType\" : [ \"pet_info_type\" ],\r\n  \"cssClass\" : [ \"\" ],\r\n  \"dictSort\" : [ \"1\" ],\r\n  \"listClass\" : [ \"success\" ],\r\n  \"isDefault\" : [ \"Y\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', 0, NULL, '2021-04-26 14:11:54', 1);
INSERT INTO `sys_oper_log` VALUES (101, '字典数据', 3, 'com.mmtax.web.controller.system.SysDictDataController.editSave()', 1, 'admin', '医疗部门', '/system/dict/data/edit', '127.0.0.1', '内网IP', '{\r\n  \"dictCode\" : [ \"27\" ],\r\n  \"dictLabel\" : [ \"店养宠物\" ],\r\n  \"dictValue\" : [ \"4\" ],\r\n  \"dictType\" : [ \"pet_info_type\" ],\r\n  \"cssClass\" : [ \"\" ],\r\n  \"dictSort\" : [ \"1\" ],\r\n  \"listClass\" : [ \"success\" ],\r\n  \"isDefault\" : [ \"Y\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', 0, NULL, '2021-04-26 14:12:13', 1);
INSERT INTO `sys_oper_log` VALUES (102, '宠物', 3, 'com.mmtax.web.controller.business.PetInfoController.editSave()', 1, 'admin', '医疗部门', '/business/petInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"2\" ],\r\n  \"petName\" : [ \"露露\" ],\r\n  \"petType\" : [ \"怪物\" ],\r\n  \"petSex\" : [ \"1\" ],\r\n  \"petAge\" : [ \"2\" ],\r\n  \"petInfoType\" : [ \"4\" ],\r\n  \"remake\" : [ \"1\" ]\r\n}', 0, NULL, '2021-04-26 14:26:27', 1);
INSERT INTO `sys_oper_log` VALUES (103, '宠物', 3, 'com.mmtax.web.controller.business.PetInfoController.editSave()', 1, 'admin', '医疗部门', '/business/petInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"1\" ],\r\n  \"petName\" : [ \"妞妞\" ],\r\n  \"petType\" : [ \"拉布拉多\" ],\r\n  \"petSex\" : [ \"0\" ],\r\n  \"petAge\" : [ \"2\" ],\r\n  \"petInfoType\" : [ \"4\" ],\r\n  \"remake\" : [ \"黑色的拉布拉多\" ]\r\n}', 0, NULL, '2021-04-26 14:26:29', 1);
INSERT INTO `sys_oper_log` VALUES (104, '菜单管理', 3, 'com.mmtax.web.controller.system.SysMenuController.editSave()', 1, 'admin', '医疗部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2253\" ],\r\n  \"parentId\" : [ \"2248\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"宠物信息列表\" ],\r\n  \"url\" : [ \"business/petInfo\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:petInfo:view\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-26 14:27:25', 1);
INSERT INTO `sys_oper_log` VALUES (105, '菜单管理', 2, 'com.mmtax.web.controller.system.SysMenuController.addSave()', 1, 'admin', '医疗部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2248\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"主人信息列表\" ],\r\n  \"url\" : [ \"business/petMasterInfo\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:petMasterInfo:view\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-26 14:29:26', 1);
INSERT INTO `sys_oper_log` VALUES (106, '字典类型', 2, 'com.mmtax.web.controller.system.SysDictTypeController.addSave()', 1, 'admin', '医疗部门', '/system/dict/add', '127.0.0.1', '内网IP', '{\r\n  \"dictName\" : [ \"主人性别\" ],\r\n  \"dictType\" : [ \"masterSex\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', 0, NULL, '2021-04-26 14:44:37', 1);
INSERT INTO `sys_oper_log` VALUES (107, '字典数据', 2, 'com.mmtax.web.controller.system.SysDictDataController.addSave()', 1, 'admin', '医疗部门', '/system/dict/data/add', '127.0.0.1', '内网IP', '{\r\n  \"dictLabel\" : [ \"男\" ],\r\n  \"dictValue\" : [ \"0\" ],\r\n  \"dictType\" : [ \"masterSex\" ],\r\n  \"cssClass\" : [ \"\" ],\r\n  \"dictSort\" : [ \"1\" ],\r\n  \"listClass\" : [ \"default\" ],\r\n  \"isDefault\" : [ \"Y\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', 0, NULL, '2021-04-26 14:45:00', 1);
INSERT INTO `sys_oper_log` VALUES (108, '字典数据', 2, 'com.mmtax.web.controller.system.SysDictDataController.addSave()', 1, 'admin', '医疗部门', '/system/dict/data/add', '127.0.0.1', '内网IP', '{\r\n  \"dictLabel\" : [ \"女\" ],\r\n  \"dictValue\" : [ \"1\" ],\r\n  \"dictType\" : [ \"masterSex\" ],\r\n  \"cssClass\" : [ \"\" ],\r\n  \"dictSort\" : [ \"2\" ],\r\n  \"listClass\" : [ \"primary\" ],\r\n  \"isDefault\" : [ \"Y\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', 0, NULL, '2021-04-26 14:45:12', 1);
INSERT INTO `sys_oper_log` VALUES (109, '字典数据', 2, 'com.mmtax.web.controller.system.SysDictDataController.addSave()', 1, 'admin', '医疗部门', '/system/dict/data/add', '127.0.0.1', '内网IP', '{\r\n  \"dictLabel\" : [ \"未知\" ],\r\n  \"dictValue\" : [ \"2\" ],\r\n  \"dictType\" : [ \"masterSex\" ],\r\n  \"cssClass\" : [ \"\" ],\r\n  \"dictSort\" : [ \"3\" ],\r\n  \"listClass\" : [ \"info\" ],\r\n  \"isDefault\" : [ \"Y\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', 0, NULL, '2021-04-26 14:45:23', 1);
INSERT INTO `sys_oper_log` VALUES (110, '字典数据', 3, 'com.mmtax.web.controller.system.SysDictDataController.editSave()', 1, 'admin', '医疗部门', '/system/dict/data/edit', '127.0.0.1', '内网IP', '{\r\n  \"dictCode\" : [ \"34\" ],\r\n  \"dictLabel\" : [ \"男\" ],\r\n  \"dictValue\" : [ \"0\" ],\r\n  \"dictType\" : [ \"masterSex\" ],\r\n  \"cssClass\" : [ \"\" ],\r\n  \"dictSort\" : [ \"1\" ],\r\n  \"listClass\" : [ \"info\" ],\r\n  \"isDefault\" : [ \"N\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', 0, NULL, '2021-04-26 14:52:21', 1);
INSERT INTO `sys_oper_log` VALUES (111, '字典类型', 3, 'com.mmtax.web.controller.system.SysDictTypeController.editSave()', 1, 'admin', '医疗部门', '/system/dict/edit', '127.0.0.1', '内网IP', '{\r\n  \"dictId\" : [ \"12\" ],\r\n  \"dictName\" : [ \"主人性别\" ],\r\n  \"dictType\" : [ \"master_sex\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', 0, NULL, '2021-04-26 14:56:46', 1);
INSERT INTO `sys_oper_log` VALUES (112, '宠物主人', 2, 'com.mmtax.web.controller.business.PetMasterInfoController.addSave()', 1, 'admin', '医疗部门', '/business/petMasterInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"name\" : [ \"梁凡2\" ],\r\n  \"age\" : [ \"23\" ],\r\n  \"master_sex\" : [ \"0\" ],\r\n  \"phonenumber\" : [ \"13107280913\" ],\r\n  \"email\" : [ \"xxxx@qq.com\" ],\r\n  \"adress\" : [ \"西湖区\" ]\r\n}', 0, NULL, '2021-04-26 15:00:41', 1);
INSERT INTO `sys_oper_log` VALUES (113, '宠物主人', 2, 'com.mmtax.web.controller.business.PetMasterInfoController.addSave()', 1, 'admin', '医疗部门', '/business/petMasterInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"name\" : [ \"梁凡3\" ],\r\n  \"age\" : [ \"23\" ],\r\n  \"master_sex\" : [ \"0\" ],\r\n  \"phonenumber\" : [ \"13107280914\" ],\r\n  \"email\" : [ \"xxxx@qq.com\" ],\r\n  \"adress\" : [ \"西湖区2\" ]\r\n}', 0, NULL, '2021-04-26 15:03:09', 1);
INSERT INTO `sys_oper_log` VALUES (114, '宠物主人', 2, 'com.mmtax.web.controller.business.PetMasterInfoController.addSave()', 1, 'admin', '医疗部门', '/business/petMasterInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"name\" : [ \"梁凡4\" ],\r\n  \"age\" : [ \"23\" ],\r\n  \"sex\" : [ \"0\" ],\r\n  \"phonenumber\" : [ \"13107280914\" ],\r\n  \"email\" : [ \"xxxx@qq.com\" ],\r\n  \"adress\" : [ \"西湖区\" ]\r\n}', 0, NULL, '2021-04-26 15:04:46', 1);
INSERT INTO `sys_oper_log` VALUES (115, '菜单管理', 2, 'com.mmtax.web.controller.system.SysMenuController.addSave()', 1, 'admin', '医疗部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"主人管理\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"0\" ],\r\n  \"icon\" : [ \"fa fa-address-book\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-26 15:06:03', 1);
INSERT INTO `sys_oper_log` VALUES (116, '菜单管理', 3, 'com.mmtax.web.controller.system.SysMenuController.editSave()', 1, 'admin', '医疗部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2254\" ],\r\n  \"parentId\" : [ \"2255\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"主人信息列表\" ],\r\n  \"url\" : [ \"business/petMasterInfo\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:petMasterInfo:view\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-26 15:06:19', 1);
INSERT INTO `sys_oper_log` VALUES (117, '宠物主人', 3, 'com.mmtax.web.controller.business.PetMasterInfoController.editSave()', 1, 'admin', '医疗部门', '/business/petMasterInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"5\" ],\r\n  \"name\" : [ \"梁凡4\" ],\r\n  \"age\" : [ \"23\" ],\r\n  \"sex\" : [ \"0\" ],\r\n  \"phonenumber\" : [ \"13107280914\" ],\r\n  \"email\" : [ \"xxxx@qq.com1\" ],\r\n  \"adress\" : [ \"西湖区\" ],\r\n  \"createTime\" : [ \"Mon Apr 26 15:04:47 CST 2021\" ],\r\n  \"updateTime\" : [ \"Mon Apr 26 15:04:47 CST 2021\" ]\r\n}', 1, '\r\n### Error updating database.  Cause: java.sql.SQLIntegrityConstraintViolationException: Column \'del_status\' cannot be null\r\n### The error may involve com.mmtax.business.mapper.PetMasterInfoMapper.updateByPrimaryKey-Inline\r\n### The error occurred while setting parameters\r\n### SQL: UPDATE tbl_pet_master_info SET id = id, name = ?, age = ?, sex = ?, phonenumber = ?, email = ?, adress = ?, del_status = ?, provider_id = ?, create_time = ?, update_time = ? WHERE tbl_pet_master_info.provider_id = 1 AND id = ?\r\n### Cause: java.sql.SQLIntegrityConstraintViolationException: Column \'del_status\' cannot be null\n; Column \'del_status\' cannot be null; nested exception is java.sql.SQLIntegrityConstraintViolationException: Column \'del_status\' cannot be null', '2021-04-26 15:11:52', 1);
INSERT INTO `sys_oper_log` VALUES (118, '宠物主人', 3, 'com.mmtax.web.controller.business.PetMasterInfoController.editSave()', 1, 'admin', '医疗部门', '/business/petMasterInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"4\" ],\r\n  \"name\" : [ \"梁凡33\" ],\r\n  \"age\" : [ \"23\" ],\r\n  \"sex\" : [ \"0\" ],\r\n  \"phonenumber\" : [ \"13107280914\" ],\r\n  \"email\" : [ \"xxxx@qq.com\" ],\r\n  \"adress\" : [ \"西湖区2\" ]\r\n}', 0, NULL, '2021-04-26 15:16:21', 1);
INSERT INTO `sys_oper_log` VALUES (119, '宠物主人', 4, 'com.mmtax.web.controller.business.PetMasterInfoController.remove()', 1, 'admin', '医疗部门', '/business/petMasterInfo/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"4\" ]\r\n}', 0, NULL, '2021-04-26 15:16:49', 1);
INSERT INTO `sys_oper_log` VALUES (120, '代码生成', 9, 'com.mmtax.generator.controller.GenController.genCode()', 1, 'admin', '医疗部门', '/tool/gen/genCode/tbl_pet_medical_record', '127.0.0.1', '内网IP', '{ }', 0, NULL, '2021-04-26 15:59:46', 1);
INSERT INTO `sys_oper_log` VALUES (121, '菜单管理', 2, 'com.mmtax.web.controller.system.SysMenuController.addSave()', 1, 'admin', '医疗部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2246\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"宠物病历管理\" ],\r\n  \"url\" : [ \"business/petMedicalRecord\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:petMedicalRecord:view\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-26 19:38:05', 1);
INSERT INTO `sys_oper_log` VALUES (122, '菜单管理', 3, 'com.mmtax.web.controller.system.SysMenuController.editSave()', 1, 'admin', '医疗部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2256\" ],\r\n  \"parentId\" : [ \"2246\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"宠物病历管理\" ],\r\n  \"url\" : [ \"business/petMedicalRecord\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:petMedicalRecord:view\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-26 19:45:01', 1);
INSERT INTO `sys_oper_log` VALUES (123, '医疗宠物病历', 4, 'com.mmtax.web.controller.business.PetMedicalRecordController.remove()', 1, 'admin', '医疗部门', '/business/petMedicalRecord/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"5\" ]\r\n}', 0, NULL, '2021-04-26 21:38:22', 1);
INSERT INTO `sys_oper_log` VALUES (124, '医疗宠物病历', 4, 'com.mmtax.web.controller.business.PetMedicalRecordController.remove()', 1, 'admin', '医疗部门', '/business/petMedicalRecord/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"5\" ]\r\n}', 0, NULL, '2021-04-26 21:39:37', 1);
INSERT INTO `sys_oper_log` VALUES (125, '代码生成', 9, 'com.mmtax.generator.controller.GenController.genCode()', 1, 'admin', '医疗部门', '/tool/gen/genCode/tbl_pet_sale_record', '127.0.0.1', '内网IP', '{ }', 0, NULL, '2021-04-27 13:34:36', 1);
INSERT INTO `sys_oper_log` VALUES (126, '菜单管理', 2, 'com.mmtax.web.controller.system.SysMenuController.addSave()', 1, 'admin', '医疗部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2249\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"宠物销售管理\" ],\r\n  \"url\" : [ \"business/petSaleRecord\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:petSaleRecord:view\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-27 14:13:47', 1);
INSERT INTO `sys_oper_log` VALUES (127, '部门管理', 4, 'com.mmtax.web.controller.system.SysDeptController.remove()', 1, 'admin', '医疗部门', '/system/dept/remove/108', '127.0.0.1', '内网IP', '{ }', 0, NULL, '2021-04-27 14:40:36', 1);
INSERT INTO `sys_oper_log` VALUES (128, '部门管理', 4, 'com.mmtax.web.controller.system.SysDeptController.remove()', 1, 'admin', '医疗部门', '/system/dept/remove/102', '127.0.0.1', '内网IP', '{ }', 0, NULL, '2021-04-27 14:40:38', 1);
INSERT INTO `sys_oper_log` VALUES (129, '部门管理', 3, 'com.mmtax.web.controller.system.SysDeptController.editSave()', 1, 'admin', '医疗部门', '/system/dept/edit', '127.0.0.1', '内网IP', '{\r\n  \"deptId\" : [ \"101\" ],\r\n  \"parentId\" : [ \"100\" ],\r\n  \"parentName\" : [ \"petshop\" ],\r\n  \"deptName\" : [ \"怀化宠物店\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"leader\" : [ \"梁凡\" ],\r\n  \"phone\" : [ \"15888888888\" ],\r\n  \"email\" : [ \"lf@qq.com\" ],\r\n  \"status\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-27 14:40:57', 1);
INSERT INTO `sys_oper_log` VALUES (130, '部门管理', 3, 'com.mmtax.web.controller.system.SysDeptController.editSave()', 1, 'admin', '医疗部门', '/system/dept/edit', '127.0.0.1', '内网IP', '{\r\n  \"deptId\" : [ \"103\" ],\r\n  \"parentId\" : [ \"101\" ],\r\n  \"parentName\" : [ \"怀化宠物店\" ],\r\n  \"deptName\" : [ \"医疗部门\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"leader\" : [ \"梁凡\" ],\r\n  \"phone\" : [ \"15888888888\" ],\r\n  \"email\" : [ \"ry@qq.com\" ],\r\n  \"status\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-27 14:41:04', 1);
INSERT INTO `sys_oper_log` VALUES (131, '部门管理', 3, 'com.mmtax.web.controller.system.SysDeptController.editSave()', 1, 'admin', '医疗部门', '/system/dept/edit', '127.0.0.1', '内网IP', '{\r\n  \"deptId\" : [ \"105\" ],\r\n  \"parentId\" : [ \"101\" ],\r\n  \"parentName\" : [ \"怀化宠物店\" ],\r\n  \"deptName\" : [ \"销售部门\" ],\r\n  \"orderNum\" : [ \"3\" ],\r\n  \"leader\" : [ \"梁凡\" ],\r\n  \"phone\" : [ \"15888888888\" ],\r\n  \"email\" : [ \"ry@qq.com\" ],\r\n  \"status\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-27 14:41:07', 1);
INSERT INTO `sys_oper_log` VALUES (132, '部门管理', 3, 'com.mmtax.web.controller.system.SysDeptController.editSave()', 1, 'admin', '医疗部门', '/system/dept/edit', '127.0.0.1', '内网IP', '{\r\n  \"deptId\" : [ \"106\" ],\r\n  \"parentId\" : [ \"101\" ],\r\n  \"parentName\" : [ \"怀化宠物店\" ],\r\n  \"deptName\" : [ \"管理部门\" ],\r\n  \"orderNum\" : [ \"4\" ],\r\n  \"leader\" : [ \"梁凡\" ],\r\n  \"phone\" : [ \"15888888888\" ],\r\n  \"email\" : [ \"ry@qq.com\" ],\r\n  \"status\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-27 14:41:11', 1);
INSERT INTO `sys_oper_log` VALUES (133, '字典类型', 2, 'com.mmtax.web.controller.system.SysDictTypeController.addSave()', 1, 'admin', '医疗部门', '/system/dict/add', '127.0.0.1', '内网IP', '{\r\n  \"dictName\" : [ \"是否状态\" ],\r\n  \"dictType\" : [ \"info_status\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', 0, NULL, '2021-04-27 14:42:39', 1);
INSERT INTO `sys_oper_log` VALUES (134, '字典数据', 2, 'com.mmtax.web.controller.system.SysDictDataController.addSave()', 1, 'admin', '医疗部门', '/system/dict/data/add', '127.0.0.1', '内网IP', '{\r\n  \"dictLabel\" : [ \"是\" ],\r\n  \"dictValue\" : [ \"0\" ],\r\n  \"dictType\" : [ \"info_status\" ],\r\n  \"cssClass\" : [ \"\" ],\r\n  \"dictSort\" : [ \"1\" ],\r\n  \"listClass\" : [ \"success\" ],\r\n  \"isDefault\" : [ \"N\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', 0, NULL, '2021-04-27 14:43:00', 1);
INSERT INTO `sys_oper_log` VALUES (135, '字典数据', 2, 'com.mmtax.web.controller.system.SysDictDataController.addSave()', 1, 'admin', '医疗部门', '/system/dict/data/add', '127.0.0.1', '内网IP', '{\r\n  \"dictLabel\" : [ \"否\" ],\r\n  \"dictValue\" : [ \"1\" ],\r\n  \"dictType\" : [ \"info_status\" ],\r\n  \"cssClass\" : [ \"\" ],\r\n  \"dictSort\" : [ \"2\" ],\r\n  \"listClass\" : [ \"info\" ],\r\n  \"isDefault\" : [ \"N\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', 0, NULL, '2021-04-27 14:43:13', 1);
INSERT INTO `sys_oper_log` VALUES (136, '字典数据', 3, 'com.mmtax.web.controller.system.SysDictDataController.editSave()', 1, 'admin', '医疗部门', '/system/dict/data/edit', '127.0.0.1', '内网IP', '{\r\n  \"dictCode\" : [ \"38\" ],\r\n  \"dictLabel\" : [ \"否\" ],\r\n  \"dictValue\" : [ \"0\" ],\r\n  \"dictType\" : [ \"info_status\" ],\r\n  \"cssClass\" : [ \"\" ],\r\n  \"dictSort\" : [ \"2\" ],\r\n  \"listClass\" : [ \"info\" ],\r\n  \"isDefault\" : [ \"N\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', 0, NULL, '2021-04-27 14:54:50', 1);
INSERT INTO `sys_oper_log` VALUES (137, '字典数据', 3, 'com.mmtax.web.controller.system.SysDictDataController.editSave()', 1, 'admin', '医疗部门', '/system/dict/data/edit', '127.0.0.1', '内网IP', '{\r\n  \"dictCode\" : [ \"37\" ],\r\n  \"dictLabel\" : [ \"是\" ],\r\n  \"dictValue\" : [ \"1\" ],\r\n  \"dictType\" : [ \"info_status\" ],\r\n  \"cssClass\" : [ \"\" ],\r\n  \"dictSort\" : [ \"1\" ],\r\n  \"listClass\" : [ \"success\" ],\r\n  \"isDefault\" : [ \"N\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', 0, NULL, '2021-04-27 14:54:54', 1);
INSERT INTO `sys_oper_log` VALUES (138, '代码生成', 9, 'com.mmtax.generator.controller.GenController.genCode()', 1, 'admin', '医疗部门', '/tool/gen/genCode/tbl_pet_foster_record', '127.0.0.1', '内网IP', '{ }', 0, NULL, '2021-04-27 18:30:06', 1);
INSERT INTO `sys_oper_log` VALUES (139, '菜单管理', 2, 'com.mmtax.web.controller.system.SysMenuController.addSave()', 1, 'admin', '医疗部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2250\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"宠物寄养管理\" ],\r\n  \"url\" : [ \"business/petFosterRecord\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:petFosterRecord:view\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-27 20:09:32', 1);
INSERT INTO `sys_oper_log` VALUES (140, '常见问题', 2, 'com.mmtax.web.controller.business.CommonProblemController.addSave()', 1, 'admin', '医疗部门', '/business/commonProblem/add', '127.0.0.1', '内网IP', '{\r\n  \"problem\" : [ \"梁凡是不是很聪明\" ],\r\n  \"answer\" : [ \"是\" ]\r\n}', 0, NULL, '2021-04-27 21:19:17', 1);
INSERT INTO `sys_oper_log` VALUES (141, '代码生成', 9, 'com.mmtax.generator.controller.GenController.genCode()', 1, 'admin', '医疗部门', '/tool/gen/genCode/tbl_pet_service_record', '127.0.0.1', '内网IP', '{ }', 0, NULL, '2021-04-28 16:30:24', 1);
INSERT INTO `sys_oper_log` VALUES (142, '字典数据', 2, 'com.mmtax.web.controller.system.SysDictDataController.addSave()', 1, 'admin', '医疗部门', '/system/dict/data/add', '127.0.0.1', '内网IP', '{\r\n  \"dictLabel\" : [ \"宠物服务\" ],\r\n  \"dictValue\" : [ \"5\" ],\r\n  \"dictType\" : [ \"pet_info_type\" ],\r\n  \"cssClass\" : [ \"\" ],\r\n  \"dictSort\" : [ \"5\" ],\r\n  \"listClass\" : [ \"warning\" ],\r\n  \"isDefault\" : [ \"N\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', 0, NULL, '2021-04-28 16:38:56', 1);
INSERT INTO `sys_oper_log` VALUES (143, '菜单管理', 2, 'com.mmtax.web.controller.system.SysMenuController.addSave()', 1, 'admin', '医疗部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2252\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"宠物服务管理\" ],\r\n  \"url\" : [ \"business/petServiceRecord\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:petServiceRecord:view\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-28 16:52:42', 1);
INSERT INTO `sys_oper_log` VALUES (144, '字典数据', 3, 'com.mmtax.web.controller.system.SysDictDataController.editSave()', 1, 'admin', '医疗部门', '/system/dict/data/edit', '127.0.0.1', '内网IP', '{\r\n  \"dictCode\" : [ \"39\" ],\r\n  \"dictLabel\" : [ \"宠物服务\" ],\r\n  \"dictValue\" : [ \"5\" ],\r\n  \"dictType\" : [ \"pet_info_type\" ],\r\n  \"cssClass\" : [ \"\" ],\r\n  \"dictSort\" : [ \"5\" ],\r\n  \"listClass\" : [ \"danger\" ],\r\n  \"isDefault\" : [ \"N\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', 0, NULL, '2021-04-28 16:53:35', 1);
INSERT INTO `sys_oper_log` VALUES (145, '代码生成', 9, 'com.mmtax.generator.controller.GenController.genCode()', 1, 'admin', '医疗部门', '/tool/gen/genCode/tbl_pet_toys', '127.0.0.1', '内网IP', '{ }', 0, NULL, '2021-04-29 00:58:44', 1);
INSERT INTO `sys_oper_log` VALUES (146, '菜单管理', 2, 'com.mmtax.web.controller.system.SysMenuController.addSave()', 1, 'admin', '医疗部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2251\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"宠物玩具管理\" ],\r\n  \"url\" : [ \"business/petToys\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:petToys:view\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-29 01:29:33', 1);
INSERT INTO `sys_oper_log` VALUES (147, '代码生成', 9, 'com.mmtax.generator.controller.GenController.genCode()', 1, 'admin', '医疗部门', '/tool/gen/genCode/tbl_pet_foods', '127.0.0.1', '内网IP', '{ }', 0, NULL, '2021-04-29 15:33:39', 1);
INSERT INTO `sys_oper_log` VALUES (148, '宠物', 3, 'com.mmtax.web.controller.business.PetInfoController.editSave()', 1, 'admin', '医疗部门', '/business/petInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"15\" ],\r\n  \"petName\" : [ \"测试\" ],\r\n  \"petType\" : [ \"猫\" ],\r\n  \"petSex\" : [ \"0\" ],\r\n  \"petAge\" : [ \"2\" ],\r\n  \"petInfoType\" : [ \"5\" ],\r\n  \"remake\" : [ \"\" ]\r\n}', 0, NULL, '2021-04-29 16:18:27', 1);
INSERT INTO `sys_oper_log` VALUES (149, '宠物', 3, 'com.mmtax.web.controller.business.PetInfoController.editSave()', 1, 'admin', '医疗部门', '/business/petInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"15\" ],\r\n  \"petName\" : [ \"测试\" ],\r\n  \"petType\" : [ \"猫\" ],\r\n  \"petSex\" : [ \"0\" ],\r\n  \"petAge\" : [ \"2\" ],\r\n  \"petInfoType\" : [ \"5\" ],\r\n  \"remake\" : [ \"123\" ]\r\n}', 0, NULL, '2021-04-29 16:31:18', 1);
INSERT INTO `sys_oper_log` VALUES (150, '宠物', 3, 'com.mmtax.web.controller.business.PetInfoController.editSave()', 1, 'admin', '医疗部门', '/business/petInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"15\" ],\r\n  \"petName\" : [ \"测试\" ],\r\n  \"petType\" : [ \"猫\" ],\r\n  \"petSex\" : [ \"0\" ],\r\n  \"petAge\" : [ \"2\" ],\r\n  \"petInfoType\" : [ \"5\" ],\r\n  \"remake\" : [ \"123\" ]\r\n}', 0, NULL, '2021-04-29 16:38:19', 1);
INSERT INTO `sys_oper_log` VALUES (151, '宠物', 3, 'com.mmtax.web.controller.business.PetInfoController.editSave()', 1, 'admin', '医疗部门', '/business/petInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"15\" ],\r\n  \"petName\" : [ \"测试\" ],\r\n  \"petType\" : [ \"猫\" ],\r\n  \"petSex\" : [ \"0\" ],\r\n  \"petAge\" : [ \"2\" ],\r\n  \"petInfoType\" : [ \"5\" ],\r\n  \"remake\" : [ \"123\" ]\r\n}', 0, NULL, '2021-04-29 18:38:55', 1);
INSERT INTO `sys_oper_log` VALUES (152, '菜单管理', 2, 'com.mmtax.web.controller.system.SysMenuController.addSave()', 1, 'admin', '医疗部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2251\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"宠物食物管理\" ],\r\n  \"url\" : [ \"business/petFoods\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:petFoods:view\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-29 20:12:04', 1);
INSERT INTO `sys_oper_log` VALUES (153, '代码生成', 9, 'com.mmtax.generator.controller.GenController.genCode()', 1, 'admin', '医疗部门', '/tool/gen/genCode/tbl_pet_medicine', '127.0.0.1', '内网IP', '{ }', 0, NULL, '2021-04-29 22:31:51', 1);
INSERT INTO `sys_oper_log` VALUES (154, '菜单管理', 2, 'com.mmtax.web.controller.system.SysMenuController.addSave()', 1, 'admin', '医疗部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2251\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"宠物药品管理\" ],\r\n  \"url\" : [ \"business/petMedicine\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:petMedicine:view\" ],\r\n  \"orderNum\" : [ \"3\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-29 22:50:15', 1);
INSERT INTO `sys_oper_log` VALUES (155, '菜单管理', 2, 'com.mmtax.web.controller.system.SysMenuController.addSave()', 1, 'admin', '医疗部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2251\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"宠物日用品管理\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"4\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-29 22:54:56', 1);
INSERT INTO `sys_oper_log` VALUES (156, '菜单管理', 2, 'com.mmtax.web.controller.system.SysMenuController.addSave()', 1, 'admin', '医疗部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2251\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"宠物服饰管理\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"5\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-29 22:55:16', 1);
INSERT INTO `sys_oper_log` VALUES (157, '代码生成', 9, 'com.mmtax.generator.controller.GenController.genCode()', 1, 'admin', '医疗部门', '/tool/gen/genCode/tbl_pet_daily_necessities', '127.0.0.1', '内网IP', '{ }', 0, NULL, '2021-04-29 23:03:10', 1);
INSERT INTO `sys_oper_log` VALUES (158, '菜单管理', 3, 'com.mmtax.web.controller.system.SysMenuController.editSave()', 1, 'admin', '医疗部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2263\" ],\r\n  \"parentId\" : [ \"2251\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"宠物日用品管理\" ],\r\n  \"url\" : [ \"business/petDailyNecessities\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:petDailyNecessities:view\" ],\r\n  \"orderNum\" : [ \"4\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-29 23:18:49', 1);
INSERT INTO `sys_oper_log` VALUES (159, '代码生成', 9, 'com.mmtax.generator.controller.GenController.genCode()', 1, 'admin', '医疗部门', '/tool/gen/genCode/tbl_pet_clothes', '127.0.0.1', '内网IP', '{ }', 0, NULL, '2021-04-29 23:22:32', 1);
INSERT INTO `sys_oper_log` VALUES (160, '菜单管理', 3, 'com.mmtax.web.controller.system.SysMenuController.editSave()', 1, 'admin', '医疗部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2264\" ],\r\n  \"parentId\" : [ \"2251\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"宠物服饰管理\" ],\r\n  \"url\" : [ \"business/petClothes\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:petClothes:view\" ],\r\n  \"orderNum\" : [ \"5\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-29 23:35:49', 1);
INSERT INTO `sys_oper_log` VALUES (161, '宠物', 3, 'com.mmtax.web.controller.business.PetInfoController.editSave()', 1, 'admin', '医疗部门', '/business/petInfo/edit', '112.10.237.146', 'XX XX', '{\n  \"id\" : [ \"15\" ],\n  \"petName\" : [ \"测试\" ],\n  \"petType\" : [ \"猫\" ],\n  \"petSex\" : [ \"0\" ],\n  \"petAge\" : [ \"2\" ],\n  \"petInfoType\" : [ \"5\" ],\n  \"remake\" : [ \"123\" ]\n}', 0, NULL, '2021-04-30 00:52:39', 1);
INSERT INTO `sys_oper_log` VALUES (162, '宠物', 3, 'com.mmtax.web.controller.business.PetInfoController.editSave()', 1, 'admin', '医疗部门', '/business/petInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"15\" ],\r\n  \"petName\" : [ \"测试\" ],\r\n  \"petType\" : [ \"猫\" ],\r\n  \"petSex\" : [ \"0\" ],\r\n  \"petAge\" : [ \"2\" ],\r\n  \"petInfoType\" : [ \"5\" ],\r\n  \"remake\" : [ \"123\" ],\r\n  \"photo\" : [ \"http://116.62.141.102:8070/80334039-fe8d-405b-a2b9-29f73cce6ce6.jpg\" ]\r\n}', 0, NULL, '2021-04-30 01:11:17', 1);
INSERT INTO `sys_oper_log` VALUES (163, '宠物', 3, 'com.mmtax.web.controller.business.PetInfoController.editSave()', 1, 'admin', '医疗部门', '/business/petInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"14\" ],\r\n  \"petName\" : [ \"测试\" ],\r\n  \"petType\" : [ \"猫\" ],\r\n  \"petSex\" : [ \"0\" ],\r\n  \"petAge\" : [ \"2\" ],\r\n  \"petInfoType\" : [ \"3\" ],\r\n  \"remake\" : [ \"\" ],\r\n  \"photo\" : [ \"\" ]\r\n}', 0, NULL, '2021-04-30 01:11:40', 1);
INSERT INTO `sys_oper_log` VALUES (164, '宠物', 3, 'com.mmtax.web.controller.business.PetInfoController.editSave()', 1, 'admin', '医疗部门', '/business/petInfo/edit', '112.10.237.146', 'XX XX', '{\n  \"id\" : [ \"13\" ],\n  \"petName\" : [ \"1\" ],\n  \"petType\" : [ \"1\" ],\n  \"petSex\" : [ \"0\" ],\n  \"petAge\" : [ \"1\" ],\n  \"petInfoType\" : [ \"2\" ],\n  \"remake\" : [ \"\" ],\n  \"photo\" : [ \"http://116.62.141.102:8070/9fd27024-489f-4e1a-aa52-6c83cbdeaab2.jpg\" ]\n}', 0, NULL, '2021-04-30 01:36:22', 1);
INSERT INTO `sys_oper_log` VALUES (165, '宠物', 3, 'com.mmtax.web.controller.business.PetInfoController.editSave()', 1, 'admin', '医疗部门', '/business/petInfo/edit', '112.10.237.146', 'XX XX', '{\n  \"id\" : [ \"9\" ],\n  \"petName\" : [ \"测试销售\" ],\n  \"petType\" : [ \"猫\" ],\n  \"petSex\" : [ \"0\" ],\n  \"petAge\" : [ \"2\" ],\n  \"petInfoType\" : [ \"2\" ],\n  \"remake\" : [ \"\" ],\n  \"photo\" : [ \"http://116.62.141.102:8070/b5d5f765-fb5c-4fc2-8d30-019536c4b44f.png\" ]\n}', 0, NULL, '2021-04-30 13:08:02', 1);
INSERT INTO `sys_oper_log` VALUES (166, '宠物', 3, 'com.mmtax.web.controller.business.PetInfoController.editSave()', 1, 'admin', '医疗部门', '/business/petInfo/edit', '112.10.237.146', 'XX XX', '{\n  \"id\" : [ \"15\" ],\n  \"petName\" : [ \"测试\" ],\n  \"petType\" : [ \"猫\" ],\n  \"petSex\" : [ \"0\" ],\n  \"petAge\" : [ \"2\" ],\n  \"petInfoType\" : [ \"5\" ],\n  \"remake\" : [ \"123\" ],\n  \"photo\" : [ \"http://116.62.141.102:8070/c96a55b6-6904-464b-ad8a-bc63e3f8ca22.png\" ]\n}', 0, NULL, '2021-04-30 13:19:00', 1);
INSERT INTO `sys_oper_log` VALUES (167, '宠物', 3, 'com.mmtax.web.controller.business.PetInfoController.editSave()', 1, 'admin', '医疗部门', '/business/petInfo/edit', '112.10.237.146', 'XX XX', '{\n  \"id\" : [ \"14\" ],\n  \"petName\" : [ \"测试\" ],\n  \"petType\" : [ \"猫\" ],\n  \"petSex\" : [ \"0\" ],\n  \"petAge\" : [ \"2\" ],\n  \"petInfoType\" : [ \"3\" ],\n  \"remake\" : [ \"\" ],\n  \"photo\" : [ \"http://116.62.141.102:8070/56ef0b2f-9649-4bed-b2fe-fe3a4d15ea78.png\" ]\n}', 0, NULL, '2021-04-30 13:19:05', 1);
INSERT INTO `sys_oper_log` VALUES (168, '宠物', 3, 'com.mmtax.web.controller.business.PetInfoController.editSave()', 1, 'admin', '医疗部门', '/business/petInfo/edit', '112.10.237.146', 'XX XX', '{\n  \"id\" : [ \"13\" ],\n  \"petName\" : [ \"1\" ],\n  \"petType\" : [ \"1\" ],\n  \"petSex\" : [ \"0\" ],\n  \"petAge\" : [ \"1\" ],\n  \"petInfoType\" : [ \"2\" ],\n  \"remake\" : [ \"\" ],\n  \"photo\" : [ \"http://116.62.141.102:8070/00c1f75e-f698-479a-9afc-cb4b4ea74f43.png\" ]\n}', 0, NULL, '2021-04-30 13:19:09', 1);
INSERT INTO `sys_oper_log` VALUES (169, '宠物', 3, 'com.mmtax.web.controller.business.PetInfoController.editSave()', 1, 'admin', '医疗部门', '/business/petInfo/edit', '112.10.237.146', 'XX XX', '{\n  \"id\" : [ \"15\" ],\n  \"petName\" : [ \"测试\" ],\n  \"petType\" : [ \"猫\" ],\n  \"petSex\" : [ \"0\" ],\n  \"petAge\" : [ \"2\" ],\n  \"petInfoType\" : [ \"5\" ],\n  \"remake\" : [ \"123\" ],\n  \"photo\" : [ \"http://116.62.141.102:8070/7ba5819e-d7a9-4d84-8cdd-80e6c664374e.jpg\" ]\n}', 0, NULL, '2021-04-30 13:33:07', 1);
INSERT INTO `sys_oper_log` VALUES (170, '宠物', 3, 'com.mmtax.web.controller.business.PetInfoController.editSave()', 1, 'admin', '医疗部门', '/business/petInfo/edit', '112.10.237.146', 'XX XX', '{\n  \"id\" : [ \"15\" ],\n  \"petName\" : [ \"测试\" ],\n  \"petType\" : [ \"猫\" ],\n  \"petSex\" : [ \"0\" ],\n  \"petAge\" : [ \"2\" ],\n  \"petInfoType\" : [ \"5\" ],\n  \"remake\" : [ \"123\" ],\n  \"photo\" : [ \"http://116.62.141.102:8070/7ba5819e-d7a9-4d84-8cdd-80e6c664374e.jpg\" ]\n}', 0, NULL, '2021-04-30 13:34:20', 1);
INSERT INTO `sys_oper_log` VALUES (171, '菜单管理', 3, 'com.mmtax.web.controller.system.SysMenuController.editSave()', 1, 'admin', '医疗部门', '/system/menu/edit', '112.10.237.146', 'XX XX', '{\n  \"menuId\" : [ \"108\" ],\n  \"parentId\" : [ \"1\" ],\n  \"menuType\" : [ \"M\" ],\n  \"menuName\" : [ \"日志管理\" ],\n  \"url\" : [ \"#\" ],\n  \"target\" : [ \"menuItem\" ],\n  \"perms\" : [ \"\" ],\n  \"orderNum\" : [ \"9\" ],\n  \"icon\" : [ \"#\" ],\n  \"visible\" : [ \"1\" ]\n}', 0, NULL, '2021-04-30 13:35:25', 1);
INSERT INTO `sys_oper_log` VALUES (172, '菜单管理', 3, 'com.mmtax.web.controller.system.SysMenuController.editSave()', 1, 'admin', '医疗部门', '/system/menu/edit', '112.10.237.146', 'XX XX', '{\n  \"menuId\" : [ \"107\" ],\n  \"parentId\" : [ \"1\" ],\n  \"menuType\" : [ \"C\" ],\n  \"menuName\" : [ \"通知公告\" ],\n  \"url\" : [ \"/system/notice\" ],\n  \"target\" : [ \"menuItem\" ],\n  \"perms\" : [ \"system:notice:view\" ],\n  \"orderNum\" : [ \"8\" ],\n  \"icon\" : [ \"#\" ],\n  \"visible\" : [ \"1\" ]\n}', 0, NULL, '2021-04-30 13:35:39', 1);
INSERT INTO `sys_oper_log` VALUES (173, '菜单管理', 3, 'com.mmtax.web.controller.system.SysMenuController.editSave()', 1, 'admin', '医疗部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"106\" ],\r\n  \"parentId\" : [ \"1\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"参数设置\" ],\r\n  \"url\" : [ \"/system/config\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"system:config:view\" ],\r\n  \"orderNum\" : [ \"7\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"1\" ]\r\n}', 0, NULL, '2021-04-30 17:17:27', 1);
INSERT INTO `sys_oper_log` VALUES (174, '菜单管理', 3, 'com.mmtax.web.controller.system.SysMenuController.editSave()', 1, 'admin', '医疗部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"114\" ],\r\n  \"parentId\" : [ \"3\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"代码生成\" ],\r\n  \"url\" : [ \"/tool/gen\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"tool:gen:view\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"1\" ]\r\n}', 0, NULL, '2021-04-30 17:17:46', 1);
INSERT INTO `sys_oper_log` VALUES (175, '菜单管理', 3, 'com.mmtax.web.controller.system.SysMenuController.editSave()', 1, 'admin', '医疗部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"115\" ],\r\n  \"parentId\" : [ \"3\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"系统接口\" ],\r\n  \"url\" : [ \"/tool/swagger\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"tool:swagger:view\" ],\r\n  \"orderNum\" : [ \"3\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"1\" ]\r\n}', 0, NULL, '2021-04-30 17:17:51', 1);
INSERT INTO `sys_oper_log` VALUES (176, '菜单管理', 3, 'com.mmtax.web.controller.system.SysMenuController.editSave()', 1, 'admin', '医疗部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"113\" ],\r\n  \"parentId\" : [ \"3\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"表单构建\" ],\r\n  \"url\" : [ \"/tool/build\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"tool:build:view\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"1\" ]\r\n}', 0, NULL, '2021-04-30 17:18:10', 1);
INSERT INTO `sys_oper_log` VALUES (177, '菜单管理', 3, 'com.mmtax.web.controller.system.SysMenuController.editSave()', 1, 'admin', '医疗部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"115\" ],\r\n  \"parentId\" : [ \"3\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"系统接口\" ],\r\n  \"url\" : [ \"/tool/swagger\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"tool:swagger:view\" ],\r\n  \"orderNum\" : [ \"3\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-30 17:18:16', 1);
INSERT INTO `sys_oper_log` VALUES (178, '用户管理', 3, 'com.mmtax.web.controller.system.SysUserController.editSave()', 1, 'admin', '医疗部门', '/system/user/edit', '127.0.0.1', '内网IP', '{\r\n  \"userId\" : [ \"2\" ],\r\n  \"deptId\" : [ \"106\" ],\r\n  \"userName\" : [ \"梁凡\" ],\r\n  \"dept.deptName\" : [ \"管理部门\" ],\r\n  \"phonenumber\" : [ \"13107280912\" ],\r\n  \"email\" : [ \"851499434@qq.com\" ],\r\n  \"loginName\" : [ \"13107280912\" ],\r\n  \"sex\" : [ \"0\" ],\r\n  \"role\" : [ \"1\" ],\r\n  \"remark\" : [ \"随意\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"roleIds\" : [ \"1\" ],\r\n  \"postIds\" : [ \"1,2\" ]\r\n}', 0, NULL, '2021-04-30 17:21:04', 1);
INSERT INTO `sys_oper_log` VALUES (179, '角色管理', 3, 'com.mmtax.web.controller.system.SysRoleController.authDataScopeSave()', 1, 'admin', '医疗部门', '/system/role/authDataScope', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"1\" ],\r\n  \"roleName\" : [ \"管理员\" ],\r\n  \"roleKey\" : [ \"admin\" ],\r\n  \"dataScope\" : [ \"1\" ],\r\n  \"deptIds\" : [ \"\" ]\r\n}', 0, NULL, '2021-04-30 17:21:14', 1);
INSERT INTO `sys_oper_log` VALUES (180, '角色管理', 3, 'com.mmtax.web.controller.system.SysRoleController.editSave()', 1, 'admin', '医疗部门', '/system/role/edit', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"1\" ],\r\n  \"roleName\" : [ \"管理员\" ],\r\n  \"roleKey\" : [ \"admin\" ],\r\n  \"roleSort\" : [ \"1\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"管理员\" ],\r\n  \"menuIds\" : [ \"2255,2254,2248,2253,2246,2256,2249,2257,2250,2258,2251,2260,2261,2262,2263,2264,2252,2259,2244,2245,2247,100,1000,1001,1002,1003,1004,1005,1006,101,1007,1008,1009,1010,1011,103,1016,1017,1018,1019,104,1020,1021,1022,1023,1024,1,102,1012,1013,1014,1015,105,1025,1026,1027,1028,1029,106,1030,1031,1032,1033,1034,107,1035,1036,1037,1038,108,500,1039,1040,1041,1042,501,1043,1044,1045,3,113,114,1056,1057,115,2,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,1055,111,112\" ]\r\n}', 0, NULL, '2021-04-30 17:22:12', 1);
INSERT INTO `sys_oper_log` VALUES (181, '角色管理', 3, 'com.mmtax.web.controller.system.SysRoleController.editSave()', 1, '13107280912', '管理部门', '/system/role/edit', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"1\" ],\r\n  \"roleName\" : [ \"管理员\" ],\r\n  \"roleKey\" : [ \"admin\" ],\r\n  \"roleSort\" : [ \"1\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"管理员\" ],\r\n  \"menuIds\" : [ \"2254,2248,2253,2246,2256,2249,2257,2250,2258,2251,2260,2261,2262,2263,2264,2252,2259,2244,2245,1,102,1012,1013,1014,1015,105,1025,1026,1027,1028,1029,106,1030,1031,1032,1033,1034,107,1035,1036,1037,1038,108,500,1039,1040,1041,1042,501,1043,1044,1045,3,113,114,1056,1057,115,2,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,1055,112,111\" ]\r\n}', 0, NULL, '2021-04-30 17:22:47', 1);
INSERT INTO `sys_oper_log` VALUES (182, '角色管理', 3, 'com.mmtax.web.controller.system.SysRoleController.editSave()', 1, 'admin', '医疗部门', '/system/role/edit', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"1\" ],\r\n  \"roleName\" : [ \"管理员\" ],\r\n  \"roleKey\" : [ \"admin\" ],\r\n  \"roleSort\" : [ \"1\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"管理员\" ],\r\n  \"menuIds\" : [ \"2254,2248,2253,2246,2256,2249,2257,2250,2258,2251,2260,2261,2262,2263,2264,2252,2259,2244,2245,2247,100,1000,1001,1002,1003,1004,1005,1006,101,1007,1008,1009,1010,1011,103,1016,1017,1018,1019,104,1020,1021,1022,1023,1024,1,102,1012,1013,1014,1015,105,1025,1026,1027,1028,1029,106,1030,1031,1032,1033,1034,107,1035,1036,1037,1038,108,500,1039,1040,1041,1042,501,1043,1044,1045,3,113,114,1056,1057,115,2,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,1055,111,112\" ]\r\n}', 0, NULL, '2021-04-30 17:23:27', 1);
INSERT INTO `sys_oper_log` VALUES (183, '角色管理', 3, 'com.mmtax.web.controller.system.SysRoleController.editSave()', 1, 'admin', '医疗部门', '/system/role/edit', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"3\" ],\r\n  \"roleName\" : [ \"系统管理员\" ],\r\n  \"roleKey\" : [ \"yutou\" ],\r\n  \"roleSort\" : [ \"3\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"\" ],\r\n  \"menuIds\" : [ \"2255,2254,2248,2253,2246,2256,2249,2257,2250,2258,2251,2260,2261,2262,2263,2264,2252,2259,2244,2245\" ]\r\n}', 0, NULL, '2021-04-30 17:23:41', 1);
INSERT INTO `sys_oper_log` VALUES (184, '用户管理', 3, 'com.mmtax.web.controller.system.SysUserController.editSave()', 1, 'admin', '医疗部门', '/system/user/edit', '127.0.0.1', '内网IP', '{\r\n  \"userId\" : [ \"2\" ],\r\n  \"deptId\" : [ \"106\" ],\r\n  \"userName\" : [ \"梁凡\" ],\r\n  \"dept.deptName\" : [ \"管理部门\" ],\r\n  \"phonenumber\" : [ \"13107280912\" ],\r\n  \"email\" : [ \"851499434@qq.com\" ],\r\n  \"loginName\" : [ \"13107280912\" ],\r\n  \"sex\" : [ \"0\" ],\r\n  \"role\" : [ \"3\" ],\r\n  \"remark\" : [ \"随意\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"roleIds\" : [ \"3\" ],\r\n  \"postIds\" : [ \"1,2\" ]\r\n}', 0, NULL, '2021-04-30 17:23:49', 1);
INSERT INTO `sys_oper_log` VALUES (185, '用户管理', 3, 'com.mmtax.web.controller.system.SysUserController.editSave()', 1, 'admin', '医疗部门', '/system/user/edit', '127.0.0.1', '内网IP', '{\r\n  \"userId\" : [ \"2\" ],\r\n  \"deptId\" : [ \"106\" ],\r\n  \"userName\" : [ \"梁凡\" ],\r\n  \"dept.deptName\" : [ \"管理部门\" ],\r\n  \"phonenumber\" : [ \"13107280912\" ],\r\n  \"email\" : [ \"851499434@qq.com\" ],\r\n  \"loginName\" : [ \"13107280912\" ],\r\n  \"sex\" : [ \"0\" ],\r\n  \"role\" : [ \"1\" ],\r\n  \"remark\" : [ \"随意\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"roleIds\" : [ \"1\" ],\r\n  \"postIds\" : [ \"1,2\" ]\r\n}', 0, NULL, '2021-04-30 17:25:08', 1);
INSERT INTO `sys_oper_log` VALUES (186, '菜单管理', 2, 'com.mmtax.web.controller.system.SysMenuController.addSave()', 1, '13107280912', '管理部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2253\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"查询宠物列表\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:petInfo:list\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-30 17:29:49', 1);
INSERT INTO `sys_oper_log` VALUES (187, '菜单管理', 3, 'com.mmtax.web.controller.system.SysMenuController.editSave()', 1, '13107280912', '管理部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2253\" ],\r\n  \"parentId\" : [ \"2248\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"宠物信息列表\" ],\r\n  \"url\" : [ \"business/petInfo\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:petInfo:view\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-30 17:31:04', 1);
INSERT INTO `sys_oper_log` VALUES (188, '菜单管理', 3, 'com.mmtax.web.controller.system.SysMenuController.editSave()', 1, '13107280912', '管理部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2254\" ],\r\n  \"parentId\" : [ \"2248\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"主人信息列表\" ],\r\n  \"url\" : [ \"business/petMasterInfo\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:petMasterInfo:view\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-30 17:31:36', 1);
INSERT INTO `sys_oper_log` VALUES (189, '菜单管理', 3, 'com.mmtax.web.controller.system.SysMenuController.editSave()', 1, 'admin', '医疗部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2254\" ],\r\n  \"parentId\" : [ \"2255\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"主人信息列表\" ],\r\n  \"url\" : [ \"business/petMasterInfo\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:petMasterInfo:view\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-30 17:32:41', 1);
INSERT INTO `sys_oper_log` VALUES (190, '菜单管理', 3, 'com.mmtax.web.controller.system.SysMenuController.editSave()', 1, 'admin', '医疗部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2254\" ],\r\n  \"parentId\" : [ \"2255\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"主人信息列表页面\" ],\r\n  \"url\" : [ \"business/petMasterInfo\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:petMasterInfo:view\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-30 17:34:00', 1);
INSERT INTO `sys_oper_log` VALUES (191, '菜单管理', 2, 'com.mmtax.web.controller.system.SysMenuController.addSave()', 1, 'admin', '医疗部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2255\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"查询宠物主人列表\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:petInfo:list\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-30 17:34:26', 1);
INSERT INTO `sys_oper_log` VALUES (192, '菜单管理', 3, 'com.mmtax.web.controller.system.SysMenuController.editSave()', 1, 'admin', '医疗部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2266\" ],\r\n  \"parentId\" : [ \"2254\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"查询宠物主人列表\" ],\r\n  \"url\" : [ \"#\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:petInfo:list\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-30 17:35:12', 1);
INSERT INTO `sys_oper_log` VALUES (193, '菜单管理', 2, 'com.mmtax.web.controller.system.SysMenuController.addSave()', 1, 'admin', '医疗部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2254\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"新增保存宠物主人\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:petMasterInfo:add\" ],\r\n  \"orderNum\" : [ \"3\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-30 17:35:39', 1);
INSERT INTO `sys_oper_log` VALUES (194, '菜单管理', 2, 'com.mmtax.web.controller.system.SysMenuController.addSave()', 1, 'admin', '医疗部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2254\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"修改保存宠物主人\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:petMasterInfo:edit\" ],\r\n  \"orderNum\" : [ \"4\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-30 17:35:54', 1);
INSERT INTO `sys_oper_log` VALUES (195, '菜单管理', 2, 'com.mmtax.web.controller.system.SysMenuController.addSave()', 1, 'admin', '医疗部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2254\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"删除宠物主人\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:petMasterInfo:remove\" ],\r\n  \"orderNum\" : [ \"5\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-30 17:36:09', 1);
INSERT INTO `sys_oper_log` VALUES (196, '角色管理', 3, 'com.mmtax.web.controller.system.SysRoleController.editSave()', 1, 'admin', '医疗部门', '/system/role/edit', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"1\" ],\r\n  \"roleName\" : [ \"管理员\" ],\r\n  \"roleKey\" : [ \"admin\" ],\r\n  \"roleSort\" : [ \"1\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"管理员\" ],\r\n  \"menuIds\" : [ \"2255,2254,2266,2267,2268,2269,2248,2253,2246,2256,2249,2257,2250,2258,2251,2260,2261,2262,2263,2264,2252,2259,2244,2245,2247,100,1000,1001,1002,1003,1004,1005,1006,101,1007,1008,1009,1010,1011,103,1016,1017,1018,1019,104,1020,1021,1022,1023,1024,1,102,1012,1013,1014,1015,105,1025,1026,1027,1028,1029,106,1030,1031,1032,1033,1034,107,1035,1036,1037,1038,108,500,1039,1040,1041,1042,501,1043,1044,1045,3,113,114,1056,1057,115,2,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,1055,111,112\" ]\r\n}', 0, NULL, '2021-04-30 17:36:25', 1);
INSERT INTO `sys_oper_log` VALUES (197, '角色管理', 3, 'com.mmtax.web.controller.system.SysRoleController.editSave()', 1, 'admin', '医疗部门', '/system/role/edit', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"3\" ],\r\n  \"roleName\" : [ \"系统管理员\" ],\r\n  \"roleKey\" : [ \"yutou\" ],\r\n  \"roleSort\" : [ \"3\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"\" ],\r\n  \"menuIds\" : [ \"2255,2254,2266,2267,2268,2269,2248,2253,2246,2256,2249,2257,2250,2258,2251,2260,2261,2262,2263,2264,2252,2259,2244,2245\" ]\r\n}', 0, NULL, '2021-04-30 17:37:19', 1);
INSERT INTO `sys_oper_log` VALUES (198, '用户管理', 3, 'com.mmtax.web.controller.system.SysUserController.editSave()', 1, '13107280912', '管理部门', '/system/user/edit', '127.0.0.1', '内网IP', '{\r\n  \"userId\" : [ \"2\" ],\r\n  \"deptId\" : [ \"106\" ],\r\n  \"userName\" : [ \"梁凡\" ],\r\n  \"dept.deptName\" : [ \"管理部门\" ],\r\n  \"phonenumber\" : [ \"13107280912\" ],\r\n  \"email\" : [ \"851499434@qq.com\" ],\r\n  \"loginName\" : [ \"13107280912\" ],\r\n  \"sex\" : [ \"0\" ],\r\n  \"role\" : [ \"1\" ],\r\n  \"remark\" : [ \"随意\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"roleIds\" : [ \"1\" ],\r\n  \"postIds\" : [ \"1,2\" ]\r\n}', 0, NULL, '2021-04-30 17:38:03', 1);
INSERT INTO `sys_oper_log` VALUES (199, '用户管理', 3, 'com.mmtax.web.controller.system.SysUserController.editSave()', 1, '13107280912', '管理部门', '/system/user/edit', '127.0.0.1', '内网IP', '{\r\n  \"userId\" : [ \"2\" ],\r\n  \"deptId\" : [ \"106\" ],\r\n  \"userName\" : [ \"梁凡\" ],\r\n  \"dept.deptName\" : [ \"管理部门\" ],\r\n  \"phonenumber\" : [ \"13107280912\" ],\r\n  \"email\" : [ \"851499434@qq.com\" ],\r\n  \"loginName\" : [ \"13107280912\" ],\r\n  \"sex\" : [ \"0\" ],\r\n  \"role\" : [ \"1\" ],\r\n  \"remark\" : [ \"随意\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"roleIds\" : [ \"1\" ],\r\n  \"postIds\" : [ \"1,2\" ]\r\n}', 0, NULL, '2021-04-30 17:39:05', 1);
INSERT INTO `sys_oper_log` VALUES (200, '角色管理', 3, 'com.mmtax.web.controller.system.SysRoleController.authDataScopeSave()', 1, 'admin', '医疗部门', '/system/role/authDataScope', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"1\" ],\r\n  \"roleName\" : [ \"管理员\" ],\r\n  \"roleKey\" : [ \"admin\" ],\r\n  \"dataScope\" : [ \"2\" ],\r\n  \"deptIds\" : [ \"100,101,103,105,106\" ]\r\n}', 0, NULL, '2021-04-30 17:40:18', 1);
INSERT INTO `sys_oper_log` VALUES (201, '菜单管理', 3, 'com.mmtax.web.controller.system.SysMenuController.editSave()', 1, '13107280912', '管理部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2266\" ],\r\n  \"parentId\" : [ \"2254\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"查询宠物主人列表\" ],\r\n  \"url\" : [ \"#\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:petMasterInfo:list\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-30 17:42:33', 1);
INSERT INTO `sys_oper_log` VALUES (202, '菜单管理', 2, 'com.mmtax.web.controller.system.SysMenuController.addSave()', 1, 'admin', '医疗部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2253\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"新增保存宠物\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:petInfo:add\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-30 17:43:29', 1);
INSERT INTO `sys_oper_log` VALUES (203, '菜单管理', 2, 'com.mmtax.web.controller.system.SysMenuController.addSave()', 1, 'admin', '医疗部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2253\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"修改保存宠物\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:petInfo:edit\" ],\r\n  \"orderNum\" : [ \"3\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-30 17:43:47', 1);
INSERT INTO `sys_oper_log` VALUES (204, '菜单管理', 2, 'com.mmtax.web.controller.system.SysMenuController.addSave()', 1, 'admin', '医疗部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2253\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"删除宠物\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:petInfo:remove\" ],\r\n  \"orderNum\" : [ \"4\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-30 17:44:14', 1);
INSERT INTO `sys_oper_log` VALUES (205, '菜单管理', 2, 'com.mmtax.web.controller.system.SysMenuController.addSave()', 1, 'admin', '医疗部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2256\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"查询医疗宠物病历列表\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:petMedicalRecord:list\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-30 17:44:54', 1);
INSERT INTO `sys_oper_log` VALUES (206, '菜单管理', 2, 'com.mmtax.web.controller.system.SysMenuController.addSave()', 1, 'admin', '医疗部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2256\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"新增保存医疗宠物病历\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:petMedicalRecord:add\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-30 17:45:18', 1);
INSERT INTO `sys_oper_log` VALUES (207, '菜单管理', 2, 'com.mmtax.web.controller.system.SysMenuController.addSave()', 1, 'admin', '医疗部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2256\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"修改保存医疗宠物病历\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:petMedicalRecord:edit\" ],\r\n  \"orderNum\" : [ \"3\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-30 17:45:34', 1);
INSERT INTO `sys_oper_log` VALUES (208, '菜单管理', 2, 'com.mmtax.web.controller.system.SysMenuController.addSave()', 1, 'admin', '医疗部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2256\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"删除医疗宠物病历\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:petMedicalRecord:remove\" ],\r\n  \"orderNum\" : [ \"4\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-30 17:45:50', 1);
INSERT INTO `sys_oper_log` VALUES (209, '菜单管理', 2, 'com.mmtax.web.controller.system.SysMenuController.addSave()', 1, 'admin', '医疗部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2257\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"查询销售宠物记录列表\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:petSaleRecord:list\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-30 17:46:16', 1);
INSERT INTO `sys_oper_log` VALUES (210, '菜单管理', 2, 'com.mmtax.web.controller.system.SysMenuController.addSave()', 1, 'admin', '医疗部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2257\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"新增保存销售宠物记录\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:petSaleRecord:add\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-30 17:46:33', 1);
INSERT INTO `sys_oper_log` VALUES (211, '菜单管理', 2, 'com.mmtax.web.controller.system.SysMenuController.addSave()', 1, 'admin', '医疗部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2257\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"修改保存销售宠物记录\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:petSaleRecord:edit\" ],\r\n  \"orderNum\" : [ \"3\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-30 17:46:48', 1);
INSERT INTO `sys_oper_log` VALUES (212, '菜单管理', 2, 'com.mmtax.web.controller.system.SysMenuController.addSave()', 1, 'admin', '医疗部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2257\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"删除销售宠物记录\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:petSaleRecord:remove\" ],\r\n  \"orderNum\" : [ \"4\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-30 17:47:02', 1);
INSERT INTO `sys_oper_log` VALUES (213, '菜单管理', 2, 'com.mmtax.web.controller.system.SysMenuController.addSave()', 1, 'admin', '医疗部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2258\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"查询宠物寄养记录列表\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:petFosterRecord:list\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-30 17:47:32', 1);
INSERT INTO `sys_oper_log` VALUES (214, '菜单管理', 2, 'com.mmtax.web.controller.system.SysMenuController.addSave()', 1, 'admin', '医疗部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2258\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"新增保存宠物寄养记录\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:petFosterRecord:add\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-30 17:47:48', 1);
INSERT INTO `sys_oper_log` VALUES (215, '菜单管理', 2, 'com.mmtax.web.controller.system.SysMenuController.addSave()', 1, 'admin', '医疗部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2258\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"修改保存宠物寄养记录\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:petFosterRecord:edit\" ],\r\n  \"orderNum\" : [ \"3\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-30 17:48:02', 1);
INSERT INTO `sys_oper_log` VALUES (216, '菜单管理', 2, 'com.mmtax.web.controller.system.SysMenuController.addSave()', 1, 'admin', '医疗部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2258\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"删除宠物寄养记录\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:petFosterRecord:remove\" ],\r\n  \"orderNum\" : [ \"4\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-30 17:48:17', 1);
INSERT INTO `sys_oper_log` VALUES (217, '菜单管理', 2, 'com.mmtax.web.controller.system.SysMenuController.addSave()', 1, 'admin', '医疗部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2260\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"查询宠物玩具列表\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:petToys:list\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-30 17:48:54', 1);
INSERT INTO `sys_oper_log` VALUES (218, '菜单管理', 2, 'com.mmtax.web.controller.system.SysMenuController.addSave()', 1, 'admin', '医疗部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2260\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"新增保存宠物玩具\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:petToys:add\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-30 17:49:08', 1);
INSERT INTO `sys_oper_log` VALUES (219, '菜单管理', 2, 'com.mmtax.web.controller.system.SysMenuController.addSave()', 1, 'admin', '医疗部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2260\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"修改保存宠物玩具\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:petToys:edit\" ],\r\n  \"orderNum\" : [ \"3\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-30 17:49:22', 1);
INSERT INTO `sys_oper_log` VALUES (220, '菜单管理', 2, 'com.mmtax.web.controller.system.SysMenuController.addSave()', 1, 'admin', '医疗部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2260\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"删除宠物玩具\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:petToys:remove\" ],\r\n  \"orderNum\" : [ \"4\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-30 17:49:38', 1);
INSERT INTO `sys_oper_log` VALUES (221, '菜单管理', 2, 'com.mmtax.web.controller.system.SysMenuController.addSave()', 1, 'admin', '医疗部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2261\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"查询宠物食品列表\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:petFoods:list\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-30 17:49:55', 1);
INSERT INTO `sys_oper_log` VALUES (222, '菜单管理', 2, 'com.mmtax.web.controller.system.SysMenuController.addSave()', 1, 'admin', '医疗部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2261\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"新增保存宠物食品接口\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:petFoods:add\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-30 17:50:11', 1);
INSERT INTO `sys_oper_log` VALUES (223, '菜单管理', 2, 'com.mmtax.web.controller.system.SysMenuController.addSave()', 1, 'admin', '医疗部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2261\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"修改保存宠物食品\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:petFoods:edit\" ],\r\n  \"orderNum\" : [ \"3\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-30 17:50:23', 1);
INSERT INTO `sys_oper_log` VALUES (224, '菜单管理', 2, 'com.mmtax.web.controller.system.SysMenuController.addSave()', 1, 'admin', '医疗部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2261\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"删除宠物食品\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:petFoods:remove\" ],\r\n  \"orderNum\" : [ \"4\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-30 17:50:36', 1);
INSERT INTO `sys_oper_log` VALUES (225, '菜单管理', 2, 'com.mmtax.web.controller.system.SysMenuController.addSave()', 1, 'admin', '医疗部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2262\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"查询宠物药品列表\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:petMedicine:list\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-30 17:51:08', 1);
INSERT INTO `sys_oper_log` VALUES (226, '菜单管理', 2, 'com.mmtax.web.controller.system.SysMenuController.addSave()', 1, 'admin', '医疗部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2262\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"新增保存宠物药品\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:petMedicine:add\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-30 17:51:21', 1);
INSERT INTO `sys_oper_log` VALUES (227, '菜单管理', 2, 'com.mmtax.web.controller.system.SysMenuController.addSave()', 1, 'admin', '医疗部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2262\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"修改保存宠物药品\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:petMedicine:edit\" ],\r\n  \"orderNum\" : [ \"3\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-30 17:51:36', 1);
INSERT INTO `sys_oper_log` VALUES (228, '菜单管理', 2, 'com.mmtax.web.controller.system.SysMenuController.addSave()', 1, 'admin', '医疗部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2262\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"删除宠物药品\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:petMedicine:remove\" ],\r\n  \"orderNum\" : [ \"4\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-30 17:51:50', 1);
INSERT INTO `sys_oper_log` VALUES (229, '菜单管理', 2, 'com.mmtax.web.controller.system.SysMenuController.addSave()', 1, 'admin', '医疗部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2263\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"查询到宠物日用品列表\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:petDailyNecessities:list\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-30 17:52:03', 1);
INSERT INTO `sys_oper_log` VALUES (230, '菜单管理', 2, 'com.mmtax.web.controller.system.SysMenuController.addSave()', 1, 'admin', '医疗部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2263\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"新增到宠物日用品接口\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:petDailyNecessities:add\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-30 17:52:17', 1);
INSERT INTO `sys_oper_log` VALUES (231, '菜单管理', 2, 'com.mmtax.web.controller.system.SysMenuController.addSave()', 1, 'admin', '医疗部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2263\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"修改宠物日用品接口\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:petDailyNecessities:edit\" ],\r\n  \"orderNum\" : [ \"3\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-30 17:52:31', 1);
INSERT INTO `sys_oper_log` VALUES (232, '菜单管理', 2, 'com.mmtax.web.controller.system.SysMenuController.addSave()', 1, 'admin', '医疗部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2263\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"删除宠物日用品接口\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:petDailyNecessities:remove\" ],\r\n  \"orderNum\" : [ \"4\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-30 17:52:47', 1);
INSERT INTO `sys_oper_log` VALUES (233, '菜单管理', 2, 'com.mmtax.web.controller.system.SysMenuController.addSave()', 1, 'admin', '医疗部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2264\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"查询宠物服饰列表\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:petClothes:list\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-30 17:53:10', 1);
INSERT INTO `sys_oper_log` VALUES (234, '菜单管理', 2, 'com.mmtax.web.controller.system.SysMenuController.addSave()', 1, 'admin', '医疗部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2264\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"跳转到新增宠物服饰页面\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:petClothes:add\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-30 17:53:25', 1);
INSERT INTO `sys_oper_log` VALUES (235, '菜单管理', 2, 'com.mmtax.web.controller.system.SysMenuController.addSave()', 1, 'admin', '医疗部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2264\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"修改保存宠物服饰接口\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:petClothes:edit\" ],\r\n  \"orderNum\" : [ \"3\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-30 17:53:41', 1);
INSERT INTO `sys_oper_log` VALUES (236, '菜单管理', 2, 'com.mmtax.web.controller.system.SysMenuController.addSave()', 1, 'admin', '医疗部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2264\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"删除保存宠物服饰接口\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:petClothes:remove\" ],\r\n  \"orderNum\" : [ \"4\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-30 17:53:59', 1);
INSERT INTO `sys_oper_log` VALUES (237, '菜单管理', 2, 'com.mmtax.web.controller.system.SysMenuController.addSave()', 1, 'admin', '医疗部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2259\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"查询宠物服务记录列表\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:petServiceRecord:list\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-30 17:54:28', 1);
INSERT INTO `sys_oper_log` VALUES (238, '菜单管理', 2, 'com.mmtax.web.controller.system.SysMenuController.addSave()', 1, 'admin', '医疗部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2259\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"新增保存宠物服务记录\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:petServiceRecord:add\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-30 17:54:40', 1);
INSERT INTO `sys_oper_log` VALUES (239, '菜单管理', 2, 'com.mmtax.web.controller.system.SysMenuController.addSave()', 1, 'admin', '医疗部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2259\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"修改保存宠物服务记录\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:petServiceRecord:edit\" ],\r\n  \"orderNum\" : [ \"3\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-30 17:54:51', 1);
INSERT INTO `sys_oper_log` VALUES (240, '菜单管理', 2, 'com.mmtax.web.controller.system.SysMenuController.addSave()', 1, 'admin', '医疗部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2259\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"删除宠物服务记录\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:petServiceRecord:remove\" ],\r\n  \"orderNum\" : [ \"4\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-30 17:55:04', 1);
INSERT INTO `sys_oper_log` VALUES (241, '菜单管理', 2, 'com.mmtax.web.controller.system.SysMenuController.addSave()', 1, 'admin', '医疗部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2245\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"查询问题列表\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:commonProblem:list\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-30 17:55:22', 1);
INSERT INTO `sys_oper_log` VALUES (242, '菜单管理', 2, 'com.mmtax.web.controller.system.SysMenuController.addSave()', 1, 'admin', '医疗部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2245\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"新增保存常见问题\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:commonProblem:add\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-30 17:55:36', 1);
INSERT INTO `sys_oper_log` VALUES (243, '菜单管理', 2, 'com.mmtax.web.controller.system.SysMenuController.addSave()', 1, 'admin', '医疗部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2245\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"修改保存常见问题\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:commonProblem:edit\" ],\r\n  \"orderNum\" : [ \"3\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-30 17:55:53', 1);
INSERT INTO `sys_oper_log` VALUES (244, '菜单管理', 2, 'com.mmtax.web.controller.system.SysMenuController.addSave()', 1, 'admin', '医疗部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2245\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"删除常见问题\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:commonProblem:remove\" ],\r\n  \"orderNum\" : [ \"4\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2021-04-30 17:56:07', 1);
INSERT INTO `sys_oper_log` VALUES (245, '角色管理', 3, 'com.mmtax.web.controller.system.SysRoleController.editSave()', 1, 'admin', '医疗部门', '/system/role/edit', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"1\" ],\r\n  \"roleName\" : [ \"管理员\" ],\r\n  \"roleKey\" : [ \"admin\" ],\r\n  \"roleSort\" : [ \"1\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"管理员\" ],\r\n  \"menuIds\" : [ \"2255,2254,2266,2267,2268,2269,2248,2253,2265,2270,2271,2272,2246,2256,2273,2274,2275,2276,2249,2257,2277,2278,2279,2280,2250,2258,2281,2282,2283,2284,2251,2260,2285,2286,2287,2288,2261,2289,2290,2291,2292,2262,2293,2294,2295,2296,2263,2297,2298,2299,2300,2264,2301,2302,2303,2304,2252,2259,2305,2306,2307,2308,2244,2245,2309,2310,2311,2312,2247,100,1000,1001,1002,1003,1004,1005,1006,101,1007,1008,1009,1010,1011,103,1016,1017,1018,1019,104,1020,1021,1022,1023,1024,1,102,1012,1013,1014,1015,105,1025,1026,1027,1028,1029,106,1030,1031,1032,1033,1034,107,1035,1036,1037,1038,108,500,1039,1040,1041,1042,501,1043,1044,1045,3,113,114,1056,1057,115,2,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,1055,111,112\" ]\r\n}', 0, NULL, '2021-04-30 17:57:16', 1);
INSERT INTO `sys_oper_log` VALUES (246, '重置密码', 3, 'com.mmtax.web.controller.system.SysUserController.resetPwd()', 1, 'admin', '医疗部门', '/system/user/resetPwd/2', '127.0.0.1', '内网IP', '{ }', 0, NULL, '2021-04-30 18:01:14', 1);
INSERT INTO `sys_oper_log` VALUES (247, '用户管理', 3, 'com.mmtax.web.controller.system.SysUserController.editSave()', 1, 'admin', '医疗部门', '/system/user/edit', '112.10.237.146', 'XX XX', '{\n  \"userId\" : [ \"1\" ],\n  \"deptId\" : [ \"106\" ],\n  \"userName\" : [ \"宠物店管理员\" ],\n  \"dept.deptName\" : [ \"管理部门\" ],\n  \"phonenumber\" : [ \"15888888888\" ],\n  \"email\" : [ \"liangfan1104@163.com\" ],\n  \"loginName\" : [ \"admin\" ],\n  \"sex\" : [ \"1\" ],\n  \"role\" : [ \"1\" ],\n  \"remark\" : [ \"管理员\" ],\n  \"status\" : [ \"0\" ],\n  \"roleIds\" : [ \"1\" ],\n  \"postIds\" : [ \"1\" ]\n}', 0, NULL, '2021-05-01 14:27:41', 1);
INSERT INTO `sys_oper_log` VALUES (248, '部门管理', 3, 'com.mmtax.web.controller.system.SysDeptController.editSave()', 1, 'admin', '医疗部门', '/system/dept/edit', '127.0.0.1', '内网IP', '{\r\n  \"deptId\" : [ \"103\" ],\r\n  \"parentId\" : [ \"101\" ],\r\n  \"parentName\" : [ \"怀化宠物店\" ],\r\n  \"deptName\" : [ \"管理部门2\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"leader\" : [ \"梁凡\" ],\r\n  \"phone\" : [ \"15888888888\" ],\r\n  \"email\" : [ \"ry@qq.com\" ],\r\n  \"status\" : [ \"0\" ]\r\n}', 0, NULL, '2021-05-01 14:29:00', 1);
INSERT INTO `sys_oper_log` VALUES (249, '部门管理', 3, 'com.mmtax.web.controller.system.SysDeptController.editSave()', 1, 'admin', '医疗部门', '/system/dept/edit', '127.0.0.1', '内网IP', '{\r\n  \"deptId\" : [ \"106\" ],\r\n  \"parentId\" : [ \"101\" ],\r\n  \"parentName\" : [ \"怀化宠物店\" ],\r\n  \"deptName\" : [ \"医疗部门\" ],\r\n  \"orderNum\" : [ \"4\" ],\r\n  \"leader\" : [ \"梁凡\" ],\r\n  \"phone\" : [ \"15888888888\" ],\r\n  \"email\" : [ \"ry@qq.com\" ],\r\n  \"status\" : [ \"0\" ]\r\n}', 0, NULL, '2021-05-01 14:29:06', 1);
INSERT INTO `sys_oper_log` VALUES (250, '部门管理', 3, 'com.mmtax.web.controller.system.SysDeptController.editSave()', 1, 'admin', '医疗部门', '/system/dept/edit', '127.0.0.1', '内网IP', '{\r\n  \"deptId\" : [ \"103\" ],\r\n  \"parentId\" : [ \"101\" ],\r\n  \"parentName\" : [ \"怀化宠物店\" ],\r\n  \"deptName\" : [ \"管理部门\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"leader\" : [ \"梁凡\" ],\r\n  \"phone\" : [ \"15888888888\" ],\r\n  \"email\" : [ \"ry@qq.com\" ],\r\n  \"status\" : [ \"0\" ]\r\n}', 0, NULL, '2021-05-01 14:29:10', 1);
INSERT INTO `sys_oper_log` VALUES (251, '用户管理', 3, 'com.mmtax.web.controller.system.SysUserController.editSave()', 1, 'admin', '医疗部门', '/system/user/edit', '127.0.0.1', '内网IP', '{\r\n  \"userId\" : [ \"2\" ],\r\n  \"deptId\" : [ \"106\" ],\r\n  \"userName\" : [ \"梁凡\" ],\r\n  \"dept.deptName\" : [ \"医疗部门\" ],\r\n  \"phonenumber\" : [ \"13107280912\" ],\r\n  \"email\" : [ \"851499434@qq.com\" ],\r\n  \"loginName\" : [ \"13107280912\" ],\r\n  \"sex\" : [ \"0\" ],\r\n  \"role\" : [ \"3\" ],\r\n  \"remark\" : [ \"随意\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"roleIds\" : [ \"3\" ],\r\n  \"postIds\" : [ \"1,2\" ]\r\n}', 0, NULL, '2021-05-01 14:29:32', 1);
INSERT INTO `sys_oper_log` VALUES (252, '常见问题', 3, 'com.mmtax.web.controller.business.CommonProblemController.editSave()', 1, 'admin', '管理部门', '/business/commonProblem/edit', '112.10.237.60', 'XX XX', '{\n  \"id\" : [ \"12\" ],\n  \"problem\" : [ \"庄陶泽是不是人\" ],\n  \"answer\" : [ \"是\" ]\n}', 0, NULL, '2021-05-11 22:32:04', 1);
INSERT INTO `sys_oper_log` VALUES (253, '常见问题', 4, 'com.mmtax.web.controller.business.CommonProblemController.remove()', 1, 'admin', '管理部门', '/business/commonProblem/remove', '112.10.237.60', 'XX XX', '{\n  \"ids\" : [ \"12\" ]\n}', 0, NULL, '2021-05-11 22:32:08', 1);
INSERT INTO `sys_oper_log` VALUES (254, '用户管理', 3, 'com.mmtax.web.controller.system.SysUserController.editSave()', 1, 'admin', '管理部门', '/system/user/edit', '127.0.0.1', '内网IP', '{\r\n  \"userId\" : [ \"2\" ],\r\n  \"deptId\" : [ \"106\" ],\r\n  \"userName\" : [ \"梁凡\" ],\r\n  \"dept.deptName\" : [ \"医疗部门\" ],\r\n  \"phonenumber\" : [ \"13107280912\" ],\r\n  \"email\" : [ \"851499434@qq.com\" ],\r\n  \"loginName\" : [ \"13107280912\" ],\r\n  \"sex\" : [ \"0\" ],\r\n  \"role\" : [ \"3\", \"2\" ],\r\n  \"remark\" : [ \"随意\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"roleIds\" : [ \"3,2\" ],\r\n  \"postIds\" : [ \"1,2\" ]\r\n}', 0, NULL, '2021-05-14 13:09:10', 1);
INSERT INTO `sys_oper_log` VALUES (255, '角色管理', 3, 'com.mmtax.web.controller.system.SysRoleController.editSave()', 1, 'admin', '管理部门', '/system/role/edit', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"2\" ],\r\n  \"roleName\" : [ \"普通角色\" ],\r\n  \"roleKey\" : [ \"common\" ],\r\n  \"roleSort\" : [ \"2\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"普通角色\" ],\r\n  \"menuIds\" : [ \"2255,2254,2266,2267,2268,2269,2248,2253,2265,2270,2271,2272,2246,2256,2273,2274,2275,2276,2249,2257,2277,2278,2279,2280,2250,2258,2281,2282,2283,2284,2251,2260,2285,2286,2287,2288,2261,2289,2290,2291,2292,2262,2293,2294,2295,2296,2263,2297,2298,2299,2300,2264,2301,2302,2303,2304,2252,2259,2305,2306,2307,2308\" ]\r\n}', 0, NULL, '2021-05-14 13:09:48', 1);
INSERT INTO `sys_oper_log` VALUES (256, '角色管理', 3, 'com.mmtax.web.controller.system.SysRoleController.editSave()', 1, 'admin', '管理部门', '/system/role/edit', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"2\" ],\r\n  \"roleName\" : [ \"普通角色\" ],\r\n  \"roleKey\" : [ \"common\" ],\r\n  \"roleSort\" : [ \"2\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"普通角色\" ],\r\n  \"menuIds\" : [ \"2255,2254,2266,2267,2268,2269,2248,2253,2265,2270,2271,2272,2246,2256,2273,2274,2275,2276,2249,2257,2277,2278,2279,2280,2250,2258,2281,2282,2283,2284,2251,2260,2285,2286,2287,2288,2261,2289,2290,2291,2292,2262,2293,2294,2295,2296,2263,2297,2298,2299,2300,2264,2301,2302,2303,2304,2252,2259,2305,2306,2307,2308,2244,2245,2309,2310,2311,2312\" ]\r\n}', 0, NULL, '2021-05-14 13:10:28', 1);
INSERT INTO `sys_oper_log` VALUES (257, '角色管理', 3, 'com.mmtax.web.controller.system.SysRoleController.editSave()', 1, 'admin', '管理部门', '/system/role/edit', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"2\" ],\r\n  \"roleName\" : [ \"普通角色\" ],\r\n  \"roleKey\" : [ \"common\" ],\r\n  \"roleSort\" : [ \"2\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"普通角色\" ],\r\n  \"menuIds\" : [ \"2255,2254,2266,2267,2268,2269,2248,2253,2265,2270,2271,2272,2249,2257,2277,2278,2279,2280,2250,2258,2281,2282,2283,2284,2251,2260,2285,2286,2287,2288,2261,2289,2290,2291,2292,2262,2293,2294,2295,2296,2263,2297,2298,2299,2300,2264,2301,2302,2303,2304,2252,2259,2305,2306,2307,2308,2244,2245,2309,2310,2311,2312\" ]\r\n}', 0, NULL, '2021-05-14 13:11:06', 1);
INSERT INTO `sys_oper_log` VALUES (258, '用户管理', 3, 'com.mmtax.web.controller.system.SysUserController.editSave()', 1, 'admin', '管理部门', '/system/user/edit', '127.0.0.1', '内网IP', '{\r\n  \"userId\" : [ \"2\" ],\r\n  \"deptId\" : [ \"106\" ],\r\n  \"userName\" : [ \"梁凡\" ],\r\n  \"dept.deptName\" : [ \"医疗部门\" ],\r\n  \"phonenumber\" : [ \"13107280912\" ],\r\n  \"email\" : [ \"851499434@qq.com\" ],\r\n  \"loginName\" : [ \"13107280912\" ],\r\n  \"sex\" : [ \"0\" ],\r\n  \"role\" : [ \"2\" ],\r\n  \"remark\" : [ \"随意\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"roleIds\" : [ \"2\" ],\r\n  \"postIds\" : [ \"1,2\" ]\r\n}', 0, NULL, '2021-05-14 13:12:56', 1);
INSERT INTO `sys_oper_log` VALUES (259, '角色管理', 3, 'com.mmtax.web.controller.system.SysRoleController.editSave()', 1, 'admin', '管理部门', '/system/role/edit', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"3\" ],\r\n  \"roleName\" : [ \"系统管理员\" ],\r\n  \"roleKey\" : [ \"yutou\" ],\r\n  \"roleSort\" : [ \"3\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"\" ],\r\n  \"menuIds\" : [ \"2255,2254,2266,2267,2268,2269,2248,2253,2265,2270,2271,2272,2246,2256,2273,2274,2275,2276,2249,2257,2277,2278,2279,2280,2250,2258,2281,2282,2283,2284,2251,2260,2285,2286,2287,2288,2261,2289,2290,2291,2292,2262,2293,2294,2295,2296,2263,2297,2298,2299,2300,2264,2301,2302,2303,2304,2252,2259,2305,2306,2307,2308,2244,2245,2309,2310,2311,2312,2247,100,1000,1001,1002,1003,1004,1005,1006,101,1007,1008,1009,1010,1011,103,1016,1017,1018,1019,104,1020,1021,1022,1023,1024,1,102,1012,1013,1014,1015,105,1025,1026,1027,1028,1029,106,1030,1031,1032,1033,1034,107,1035,1036,1037,1038,108,500,1039,1040,1041,1042,501,1043,1044,1045,3,113,114,1056,1057,115,2,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,1055,111,112\" ]\r\n}', 0, NULL, '2021-05-14 13:23:42', 1);
INSERT INTO `sys_oper_log` VALUES (260, '角色管理', 3, 'com.mmtax.web.controller.system.SysRoleController.editSave()', 1, 'admin', '管理部门', '/system/role/edit', '112.10.237.60', 'XX XX', '{\n  \"roleId\" : [ \"2\" ],\n  \"roleName\" : [ \"普通角色\" ],\n  \"roleKey\" : [ \"common\" ],\n  \"roleSort\" : [ \"2\" ],\n  \"status\" : [ \"0\" ],\n  \"remark\" : [ \"普通角色\" ],\n  \"menuIds\" : [ \"2255,2254,2266,2267,2268,2269,2248,2253,2265,2270,2271,2272,2246,2256,2273,2274,2275,2276,2249,2257,2277,2278,2279,2280,2250,2258,2281,2282,2283,2284,2251,2260,2285,2286,2287,2288,2261,2289,2290,2291,2292,2262,2293,2294,2295,2296,2263,2297,2298,2299,2300,2264,2301,2302,2303,2304,2252,2259,2305,2306,2307,2308,2244,2245,2309,2310,2311,2312\" ]\n}', 0, NULL, '2021-05-14 15:56:05', 1);
INSERT INTO `sys_oper_log` VALUES (261, '角色管理', 3, 'com.mmtax.web.controller.system.SysRoleController.editSave()', 1, 'admin', '管理部门', '/system/role/edit', '112.10.237.60', 'XX XX', '{\n  \"roleId\" : [ \"2\" ],\n  \"roleName\" : [ \"普通角色\" ],\n  \"roleKey\" : [ \"common\" ],\n  \"roleSort\" : [ \"2\" ],\n  \"status\" : [ \"0\" ],\n  \"remark\" : [ \"普通角色\" ],\n  \"menuIds\" : [ \"2255,2254,2266,2267,2268,2269,2248,2253,2265,2270,2271,2272,2246,2256,2273,2274,2275,2276,2249,2257,2277,2278,2279,2280,2250,2258,2281,2282,2283,2284,2251,2260,2285,2286,2287,2288,2261,2289,2290,2291,2292,2262,2293,2294,2295,2296,2263,2297,2298,2299,2300,2264,2301,2302,2303,2304,2252,2259,2305,2306,2307,2308,2244,2245,2309,2310,2311,2312\" ]\n}', 0, NULL, '2021-05-14 21:23:09', 1);
INSERT INTO `sys_oper_log` VALUES (262, '角色管理', 3, 'com.mmtax.web.controller.system.SysRoleController.editSave()', 1, 'admin', '管理部门', '/system/role/edit', '112.10.237.60', 'XX XX', '{\n  \"roleId\" : [ \"2\" ],\n  \"roleName\" : [ \"普通角色\" ],\n  \"roleKey\" : [ \"common\" ],\n  \"roleSort\" : [ \"2\" ],\n  \"status\" : [ \"0\" ],\n  \"remark\" : [ \"普通角色\" ],\n  \"menuIds\" : [ \"2255,2254,2266,2267,2268,2269,2248,2253,2265,2270,2271,2272,2246,2256,2273,2274,2275,2276,2249,2257,2277,2278,2279,2280,2250,2258,2281,2282,2283,2284,2251,2260,2285,2286,2287,2288,2261,2289,2290,2291,2292,2262,2293,2294,2295,2296,2263,2297,2298,2299,2300,2264,2301,2302,2303,2304,2252,2259,2305,2306,2307,2308,2244,2245,2309,2310,2311,2312\" ]\n}', 0, NULL, '2021-05-14 21:30:41', 1);
INSERT INTO `sys_oper_log` VALUES (263, '菜单管理', 3, 'com.mmtax.web.controller.system.SysMenuController.editSave()', 1, 'admin', '管理部门', '/system/menu/edit', '58.101.246.70', 'XX XX', '{\n  \"menuId\" : [ \"2\" ],\n  \"parentId\" : [ \"0\" ],\n  \"menuType\" : [ \"M\" ],\n  \"menuName\" : [ \"系统监控\" ],\n  \"url\" : [ \"#\" ],\n  \"target\" : [ \"menuItem\" ],\n  \"perms\" : [ \"\" ],\n  \"orderNum\" : [ \"11\" ],\n  \"icon\" : [ \"fa fa-video-camera\" ],\n  \"visible\" : [ \"0\" ]\n}', 0, NULL, '2022-07-13 19:55:30', 1);
INSERT INTO `sys_oper_log` VALUES (264, '菜单管理', 3, 'com.mmtax.web.controller.system.SysMenuController.editSave()', 1, 'admin', '管理部门', '/system/menu/edit', '58.101.246.70', 'XX XX', '{\n  \"menuId\" : [ \"113\" ],\n  \"parentId\" : [ \"3\" ],\n  \"menuType\" : [ \"C\" ],\n  \"menuName\" : [ \"表单构建\" ],\n  \"url\" : [ \"/tool/build\" ],\n  \"target\" : [ \"menuItem\" ],\n  \"perms\" : [ \"tool:build:view\" ],\n  \"orderNum\" : [ \"1\" ],\n  \"icon\" : [ \"#\" ],\n  \"visible\" : [ \"0\" ]\n}', 0, NULL, '2022-07-13 20:31:37', 1);
INSERT INTO `sys_oper_log` VALUES (265, '菜单管理', 3, 'com.mmtax.web.controller.system.SysMenuController.editSave()', 1, 'admin', '管理部门', '/system/menu/edit', '58.101.246.70', 'XX XX', '{\n  \"menuId\" : [ \"114\" ],\n  \"parentId\" : [ \"3\" ],\n  \"menuType\" : [ \"C\" ],\n  \"menuName\" : [ \"代码生成\" ],\n  \"url\" : [ \"/tool/gen\" ],\n  \"target\" : [ \"menuItem\" ],\n  \"perms\" : [ \"tool:gen:view\" ],\n  \"orderNum\" : [ \"2\" ],\n  \"icon\" : [ \"#\" ],\n  \"visible\" : [ \"0\" ]\n}', 0, NULL, '2022-07-13 20:31:43', 1);
INSERT INTO `sys_oper_log` VALUES (266, '菜单管理', 3, 'com.mmtax.web.controller.system.SysMenuController.editSave()', 1, 'admin', '管理部门', '/system/menu/edit', '58.101.246.70', 'XX XX', '{\n  \"menuId\" : [ \"111\" ],\n  \"parentId\" : [ \"2\" ],\n  \"menuType\" : [ \"C\" ],\n  \"menuName\" : [ \"数据监控\" ],\n  \"url\" : [ \"/monitor/data\" ],\n  \"target\" : [ \"menuItem\" ],\n  \"perms\" : [ \"monitor:data:view\" ],\n  \"orderNum\" : [ \"3\" ],\n  \"icon\" : [ \"#\" ],\n  \"visible\" : [ \"0\" ]\n}', 0, NULL, '2022-07-13 20:31:49', 1);
INSERT INTO `sys_oper_log` VALUES (267, '菜单管理', 3, 'com.mmtax.web.controller.system.SysMenuController.editSave()', 1, 'admin', '管理部门', '/system/menu/edit', '58.101.246.70', 'XX XX', '{\n  \"menuId\" : [ \"108\" ],\n  \"parentId\" : [ \"1\" ],\n  \"menuType\" : [ \"M\" ],\n  \"menuName\" : [ \"日志管理\" ],\n  \"url\" : [ \"#\" ],\n  \"target\" : [ \"menuItem\" ],\n  \"perms\" : [ \"\" ],\n  \"orderNum\" : [ \"9\" ],\n  \"icon\" : [ \"#\" ],\n  \"visible\" : [ \"0\" ]\n}', 0, NULL, '2022-07-13 20:31:58', 1);
INSERT INTO `sys_oper_log` VALUES (268, '菜单管理', 3, 'com.mmtax.web.controller.system.SysMenuController.editSave()', 1, 'admin', '管理部门', '/system/menu/edit', '58.101.246.70', 'XX XX', '{\n  \"menuId\" : [ \"106\" ],\n  \"parentId\" : [ \"1\" ],\n  \"menuType\" : [ \"C\" ],\n  \"menuName\" : [ \"参数设置\" ],\n  \"url\" : [ \"/system/config\" ],\n  \"target\" : [ \"menuItem\" ],\n  \"perms\" : [ \"system:config:view\" ],\n  \"orderNum\" : [ \"7\" ],\n  \"icon\" : [ \"#\" ],\n  \"visible\" : [ \"0\" ]\n}', 0, NULL, '2022-07-13 20:32:07', 1);
INSERT INTO `sys_oper_log` VALUES (269, '菜单管理', 3, 'com.mmtax.web.controller.system.SysMenuController.editSave()', 1, 'admin', '管理部门', '/system/menu/edit', '58.101.246.70', 'XX XX', '{\n  \"menuId\" : [ \"107\" ],\n  \"parentId\" : [ \"1\" ],\n  \"menuType\" : [ \"C\" ],\n  \"menuName\" : [ \"通知公告\" ],\n  \"url\" : [ \"/system/notice\" ],\n  \"target\" : [ \"menuItem\" ],\n  \"perms\" : [ \"system:notice:view\" ],\n  \"orderNum\" : [ \"8\" ],\n  \"icon\" : [ \"#\" ],\n  \"visible\" : [ \"0\" ]\n}', 0, NULL, '2022-07-13 20:32:12', 1);
INSERT INTO `sys_oper_log` VALUES (270, '在线用户', 8, 'com.mmtax.web.controller.monitor.SysUserOnlineController.forceLogout()', 1, 'admin', '管理部门', '/monitor/online/forceLogout', '59.55.48.105', 'XX XX', '{\n  \"sessionId\" : [ \"d0f0f25f-2326-4e8d-8a90-a49bc8ebce97\" ]\n}', 0, NULL, '2022-07-29 05:11:46', 1);
INSERT INTO `sys_oper_log` VALUES (271, '在线用户', 8, 'com.mmtax.web.controller.monitor.SysUserOnlineController.forceLogout()', 1, 'admin', '管理部门', '/monitor/online/forceLogout', '59.55.48.105', 'XX XX', '{\n  \"sessionId\" : [ \"73bc5503-e0f0-4f02-b3f2-f6ea8952293b\" ]\n}', 0, NULL, '2022-07-29 05:11:49', 1);
INSERT INTO `sys_oper_log` VALUES (272, '在线用户', 8, 'com.mmtax.web.controller.monitor.SysUserOnlineController.forceLogout()', 1, 'admin', '管理部门', '/monitor/online/forceLogout', '59.55.48.105', 'XX XX', '{\n  \"sessionId\" : [ \"e3976939-e20d-4619-acec-50aa10433c8e\" ]\n}', 0, NULL, '2022-07-29 05:11:51', 1);

-- ----------------------------
-- Table structure for sys_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post`  (
  `post_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
  `post_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '岗位编码',
  `post_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '岗位名称',
  `post_sort` int(4) NOT NULL COMMENT '显示顺序',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `provider_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`post_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '岗位信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_post
-- ----------------------------
INSERT INTO `sys_post` VALUES (1, 'ceo', '董事长', 1, '0', 'admin', '2020-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_post` VALUES (2, 'se', '项目经理', 2, '0', 'admin', '2020-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_post` VALUES (3, 'hr', '人力资源', 3, '0', 'admin', '2020-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_post` VALUES (4, 'user', '普通员工', 4, '0', 'admin', '2020-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', 1);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色权限字符串',
  `role_sort` int(4) NOT NULL COMMENT '显示顺序',
  `data_scope` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `provider_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '管理员', 'admin', 1, '2', '0', '0', 'admin', '2021-03-16 11:33:00', 'admin', '2021-04-30 17:57:16', '管理员', 1);
INSERT INTO `sys_role` VALUES (2, '普通角色', 'common', 2, '1', '0', '0', 'admin', '2021-03-16 11:33:00', 'admin', '2021-05-14 21:30:41', '普通角色', 1);
INSERT INTO `sys_role` VALUES (3, '系统管理员', 'yutou', 3, '2', '0', '0', 'admin', '2020-08-08 10:02:30', 'admin', '2021-05-14 13:23:42', '', 1);

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept`  (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `dept_id` bigint(20) NOT NULL COMMENT '部门ID',
  `provider_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`role_id`, `dept_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色和部门关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_dept
-- ----------------------------
INSERT INTO `sys_role_dept` VALUES (1, 100, 1);
INSERT INTO `sys_role_dept` VALUES (1, 101, 1);
INSERT INTO `sys_role_dept` VALUES (1, 103, 1);
INSERT INTO `sys_role_dept` VALUES (1, 105, 1);
INSERT INTO `sys_role_dept` VALUES (1, 106, 1);
INSERT INTO `sys_role_dept` VALUES (3, 100, 1);
INSERT INTO `sys_role_dept` VALUES (3, 101, 1);
INSERT INTO `sys_role_dept` VALUES (3, 103, 1);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  `provider_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色和菜单关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (1, 1, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2, 1);
INSERT INTO `sys_role_menu` VALUES (1, 3, 1);
INSERT INTO `sys_role_menu` VALUES (1, 100, 1);
INSERT INTO `sys_role_menu` VALUES (1, 101, 1);
INSERT INTO `sys_role_menu` VALUES (1, 102, 1);
INSERT INTO `sys_role_menu` VALUES (1, 103, 1);
INSERT INTO `sys_role_menu` VALUES (1, 104, 1);
INSERT INTO `sys_role_menu` VALUES (1, 105, 1);
INSERT INTO `sys_role_menu` VALUES (1, 106, 1);
INSERT INTO `sys_role_menu` VALUES (1, 107, 1);
INSERT INTO `sys_role_menu` VALUES (1, 108, 1);
INSERT INTO `sys_role_menu` VALUES (1, 109, 1);
INSERT INTO `sys_role_menu` VALUES (1, 110, 1);
INSERT INTO `sys_role_menu` VALUES (1, 111, 1);
INSERT INTO `sys_role_menu` VALUES (1, 112, 1);
INSERT INTO `sys_role_menu` VALUES (1, 113, 1);
INSERT INTO `sys_role_menu` VALUES (1, 114, 1);
INSERT INTO `sys_role_menu` VALUES (1, 115, 1);
INSERT INTO `sys_role_menu` VALUES (1, 500, 1);
INSERT INTO `sys_role_menu` VALUES (1, 501, 1);
INSERT INTO `sys_role_menu` VALUES (1, 1000, 1);
INSERT INTO `sys_role_menu` VALUES (1, 1001, 1);
INSERT INTO `sys_role_menu` VALUES (1, 1002, 1);
INSERT INTO `sys_role_menu` VALUES (1, 1003, 1);
INSERT INTO `sys_role_menu` VALUES (1, 1004, 1);
INSERT INTO `sys_role_menu` VALUES (1, 1005, 1);
INSERT INTO `sys_role_menu` VALUES (1, 1006, 1);
INSERT INTO `sys_role_menu` VALUES (1, 1007, 1);
INSERT INTO `sys_role_menu` VALUES (1, 1008, 1);
INSERT INTO `sys_role_menu` VALUES (1, 1009, 1);
INSERT INTO `sys_role_menu` VALUES (1, 1010, 1);
INSERT INTO `sys_role_menu` VALUES (1, 1011, 1);
INSERT INTO `sys_role_menu` VALUES (1, 1012, 1);
INSERT INTO `sys_role_menu` VALUES (1, 1013, 1);
INSERT INTO `sys_role_menu` VALUES (1, 1014, 1);
INSERT INTO `sys_role_menu` VALUES (1, 1015, 1);
INSERT INTO `sys_role_menu` VALUES (1, 1016, 1);
INSERT INTO `sys_role_menu` VALUES (1, 1017, 1);
INSERT INTO `sys_role_menu` VALUES (1, 1018, 1);
INSERT INTO `sys_role_menu` VALUES (1, 1019, 1);
INSERT INTO `sys_role_menu` VALUES (1, 1020, 1);
INSERT INTO `sys_role_menu` VALUES (1, 1021, 1);
INSERT INTO `sys_role_menu` VALUES (1, 1022, 1);
INSERT INTO `sys_role_menu` VALUES (1, 1023, 1);
INSERT INTO `sys_role_menu` VALUES (1, 1024, 1);
INSERT INTO `sys_role_menu` VALUES (1, 1025, 1);
INSERT INTO `sys_role_menu` VALUES (1, 1026, 1);
INSERT INTO `sys_role_menu` VALUES (1, 1027, 1);
INSERT INTO `sys_role_menu` VALUES (1, 1028, 1);
INSERT INTO `sys_role_menu` VALUES (1, 1029, 1);
INSERT INTO `sys_role_menu` VALUES (1, 1030, 1);
INSERT INTO `sys_role_menu` VALUES (1, 1031, 1);
INSERT INTO `sys_role_menu` VALUES (1, 1032, 1);
INSERT INTO `sys_role_menu` VALUES (1, 1033, 1);
INSERT INTO `sys_role_menu` VALUES (1, 1034, 1);
INSERT INTO `sys_role_menu` VALUES (1, 1035, 1);
INSERT INTO `sys_role_menu` VALUES (1, 1036, 1);
INSERT INTO `sys_role_menu` VALUES (1, 1037, 1);
INSERT INTO `sys_role_menu` VALUES (1, 1038, 1);
INSERT INTO `sys_role_menu` VALUES (1, 1039, 1);
INSERT INTO `sys_role_menu` VALUES (1, 1040, 1);
INSERT INTO `sys_role_menu` VALUES (1, 1041, 1);
INSERT INTO `sys_role_menu` VALUES (1, 1042, 1);
INSERT INTO `sys_role_menu` VALUES (1, 1043, 1);
INSERT INTO `sys_role_menu` VALUES (1, 1044, 1);
INSERT INTO `sys_role_menu` VALUES (1, 1045, 1);
INSERT INTO `sys_role_menu` VALUES (1, 1046, 1);
INSERT INTO `sys_role_menu` VALUES (1, 1047, 1);
INSERT INTO `sys_role_menu` VALUES (1, 1048, 1);
INSERT INTO `sys_role_menu` VALUES (1, 1049, 1);
INSERT INTO `sys_role_menu` VALUES (1, 1050, 1);
INSERT INTO `sys_role_menu` VALUES (1, 1051, 1);
INSERT INTO `sys_role_menu` VALUES (1, 1052, 1);
INSERT INTO `sys_role_menu` VALUES (1, 1053, 1);
INSERT INTO `sys_role_menu` VALUES (1, 1054, 1);
INSERT INTO `sys_role_menu` VALUES (1, 1055, 1);
INSERT INTO `sys_role_menu` VALUES (1, 1056, 1);
INSERT INTO `sys_role_menu` VALUES (1, 1057, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2244, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2245, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2246, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2247, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2248, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2249, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2250, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2251, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2252, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2253, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2254, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2255, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2256, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2257, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2258, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2259, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2260, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2261, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2262, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2263, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2264, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2265, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2266, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2267, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2268, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2269, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2270, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2271, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2272, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2273, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2274, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2275, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2276, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2277, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2278, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2279, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2280, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2281, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2282, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2283, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2284, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2285, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2286, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2287, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2288, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2289, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2290, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2291, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2292, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2293, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2294, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2295, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2296, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2297, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2298, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2299, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2300, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2301, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2302, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2303, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2304, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2305, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2306, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2307, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2308, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2309, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2310, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2311, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2312, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2244, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2245, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2246, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2248, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2249, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2250, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2251, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2252, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2253, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2254, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2255, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2256, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2257, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2258, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2259, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2260, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2261, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2262, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2263, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2264, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2265, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2266, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2267, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2268, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2269, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2270, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2271, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2272, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2273, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2274, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2275, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2276, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2277, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2278, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2279, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2280, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2281, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2282, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2283, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2284, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2285, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2286, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2287, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2288, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2289, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2290, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2291, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2292, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2293, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2294, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2295, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2296, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2297, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2298, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2299, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2300, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2301, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2302, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2303, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2304, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2305, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2306, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2307, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2308, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2309, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2310, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2311, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2312, 1);
INSERT INTO `sys_role_menu` VALUES (3, 1, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2, 1);
INSERT INTO `sys_role_menu` VALUES (3, 3, 1);
INSERT INTO `sys_role_menu` VALUES (3, 100, 1);
INSERT INTO `sys_role_menu` VALUES (3, 101, 1);
INSERT INTO `sys_role_menu` VALUES (3, 102, 1);
INSERT INTO `sys_role_menu` VALUES (3, 103, 1);
INSERT INTO `sys_role_menu` VALUES (3, 104, 1);
INSERT INTO `sys_role_menu` VALUES (3, 105, 1);
INSERT INTO `sys_role_menu` VALUES (3, 106, 1);
INSERT INTO `sys_role_menu` VALUES (3, 107, 1);
INSERT INTO `sys_role_menu` VALUES (3, 108, 1);
INSERT INTO `sys_role_menu` VALUES (3, 109, 1);
INSERT INTO `sys_role_menu` VALUES (3, 110, 1);
INSERT INTO `sys_role_menu` VALUES (3, 111, 1);
INSERT INTO `sys_role_menu` VALUES (3, 112, 1);
INSERT INTO `sys_role_menu` VALUES (3, 113, 1);
INSERT INTO `sys_role_menu` VALUES (3, 114, 1);
INSERT INTO `sys_role_menu` VALUES (3, 115, 1);
INSERT INTO `sys_role_menu` VALUES (3, 500, 1);
INSERT INTO `sys_role_menu` VALUES (3, 501, 1);
INSERT INTO `sys_role_menu` VALUES (3, 1000, 1);
INSERT INTO `sys_role_menu` VALUES (3, 1001, 1);
INSERT INTO `sys_role_menu` VALUES (3, 1002, 1);
INSERT INTO `sys_role_menu` VALUES (3, 1003, 1);
INSERT INTO `sys_role_menu` VALUES (3, 1004, 1);
INSERT INTO `sys_role_menu` VALUES (3, 1005, 1);
INSERT INTO `sys_role_menu` VALUES (3, 1006, 1);
INSERT INTO `sys_role_menu` VALUES (3, 1007, 1);
INSERT INTO `sys_role_menu` VALUES (3, 1008, 1);
INSERT INTO `sys_role_menu` VALUES (3, 1009, 1);
INSERT INTO `sys_role_menu` VALUES (3, 1010, 1);
INSERT INTO `sys_role_menu` VALUES (3, 1011, 1);
INSERT INTO `sys_role_menu` VALUES (3, 1012, 1);
INSERT INTO `sys_role_menu` VALUES (3, 1013, 1);
INSERT INTO `sys_role_menu` VALUES (3, 1014, 1);
INSERT INTO `sys_role_menu` VALUES (3, 1015, 1);
INSERT INTO `sys_role_menu` VALUES (3, 1016, 1);
INSERT INTO `sys_role_menu` VALUES (3, 1017, 1);
INSERT INTO `sys_role_menu` VALUES (3, 1018, 1);
INSERT INTO `sys_role_menu` VALUES (3, 1019, 1);
INSERT INTO `sys_role_menu` VALUES (3, 1020, 1);
INSERT INTO `sys_role_menu` VALUES (3, 1021, 1);
INSERT INTO `sys_role_menu` VALUES (3, 1022, 1);
INSERT INTO `sys_role_menu` VALUES (3, 1023, 1);
INSERT INTO `sys_role_menu` VALUES (3, 1024, 1);
INSERT INTO `sys_role_menu` VALUES (3, 1025, 1);
INSERT INTO `sys_role_menu` VALUES (3, 1026, 1);
INSERT INTO `sys_role_menu` VALUES (3, 1027, 1);
INSERT INTO `sys_role_menu` VALUES (3, 1028, 1);
INSERT INTO `sys_role_menu` VALUES (3, 1029, 1);
INSERT INTO `sys_role_menu` VALUES (3, 1030, 1);
INSERT INTO `sys_role_menu` VALUES (3, 1031, 1);
INSERT INTO `sys_role_menu` VALUES (3, 1032, 1);
INSERT INTO `sys_role_menu` VALUES (3, 1033, 1);
INSERT INTO `sys_role_menu` VALUES (3, 1034, 1);
INSERT INTO `sys_role_menu` VALUES (3, 1035, 1);
INSERT INTO `sys_role_menu` VALUES (3, 1036, 1);
INSERT INTO `sys_role_menu` VALUES (3, 1037, 1);
INSERT INTO `sys_role_menu` VALUES (3, 1038, 1);
INSERT INTO `sys_role_menu` VALUES (3, 1039, 1);
INSERT INTO `sys_role_menu` VALUES (3, 1040, 1);
INSERT INTO `sys_role_menu` VALUES (3, 1041, 1);
INSERT INTO `sys_role_menu` VALUES (3, 1042, 1);
INSERT INTO `sys_role_menu` VALUES (3, 1043, 1);
INSERT INTO `sys_role_menu` VALUES (3, 1044, 1);
INSERT INTO `sys_role_menu` VALUES (3, 1045, 1);
INSERT INTO `sys_role_menu` VALUES (3, 1046, 1);
INSERT INTO `sys_role_menu` VALUES (3, 1047, 1);
INSERT INTO `sys_role_menu` VALUES (3, 1048, 1);
INSERT INTO `sys_role_menu` VALUES (3, 1049, 1);
INSERT INTO `sys_role_menu` VALUES (3, 1050, 1);
INSERT INTO `sys_role_menu` VALUES (3, 1051, 1);
INSERT INTO `sys_role_menu` VALUES (3, 1052, 1);
INSERT INTO `sys_role_menu` VALUES (3, 1053, 1);
INSERT INTO `sys_role_menu` VALUES (3, 1054, 1);
INSERT INTO `sys_role_menu` VALUES (3, 1055, 1);
INSERT INTO `sys_role_menu` VALUES (3, 1056, 1);
INSERT INTO `sys_role_menu` VALUES (3, 1057, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2244, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2245, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2246, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2247, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2248, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2249, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2250, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2251, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2252, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2253, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2254, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2255, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2256, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2257, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2258, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2259, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2260, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2261, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2262, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2263, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2264, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2265, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2266, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2267, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2268, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2269, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2270, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2271, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2272, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2273, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2274, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2275, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2276, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2277, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2278, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2279, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2280, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2281, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2282, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2283, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2284, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2285, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2286, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2287, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2288, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2289, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2290, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2291, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2292, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2293, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2294, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2295, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2296, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2297, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2298, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2299, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2300, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2301, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2302, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2303, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2304, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2305, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2306, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2307, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2308, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2309, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2310, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2311, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2312, 1);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `dept_id` bigint(20) NULL DEFAULT NULL COMMENT '部门ID',
  `login_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '登录账号',
  `user_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户昵称',
  `user_type` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '00' COMMENT '用户类型（00系统用户）',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '用户邮箱',
  `phonenumber` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '手机号码',
  `sex` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `avatar` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '头像路径',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '密码',
  `salt` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '盐加密',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `login_ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '最后登陆IP',
  `login_date` datetime(0) NULL DEFAULT NULL COMMENT '最后登陆时间',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `provider_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 103, 'admin', '宠物店管理员', '00', 'liangfan1104@163.com', '15888888888', '1', '2019/08/02/983cecb4c24d582f26e2c7cfafb7a0e0.png', '278a0e2f4903d1e685b8bef3761c6102', '60ca28', '0', '0', '127.0.0.1', '2022-08-01 20:38:28', 'admin', '2021-04-05 11:33:00', 'ry', '2022-08-01 20:38:30', '管理员', 1);
INSERT INTO `sys_user` VALUES (2, 106, '13107280912', '梁凡', '00', '851499434@qq.com', '13107280912', '0', '', 'b55b95388ddfa9e527d894dd2aab5fc9', 'b50be7', '0', '0', '112.10.237.60', '2021-05-15 15:28:57', 'admin', '2021-04-06 10:28:49', 'admin', '2021-05-15 15:28:57', '随意', 1);

-- ----------------------------
-- Table structure for sys_user_online
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_online`;
CREATE TABLE `sys_user_online`  (
  `sessionId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户会话id',
  `login_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '登录账号',
  `dept_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '部门名称',
  `ipaddr` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '操作系统',
  `status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '在线状态on_line在线off_line离线',
  `start_timestamp` datetime(0) NULL DEFAULT NULL COMMENT 'session创建时间',
  `last_access_time` datetime(0) NULL DEFAULT NULL COMMENT 'session最后访问时间',
  `expire_time` int(5) NULL DEFAULT 0 COMMENT '超时时间，单位为分钟',
  `provider_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`sessionId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '在线用户记录' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_online
-- ----------------------------
INSERT INTO `sys_user_online` VALUES ('fb916fbd-b5d3-4df4-a92d-0896c04de66f', 'admin', '管理部门', '127.0.0.1', '内网IP', 'Chrome 10', 'Windows 10', 'on_line', '2022-08-01 20:38:25', '2022-08-01 20:43:59', 1800000, NULL);

-- ----------------------------
-- Table structure for sys_user_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_post`;
CREATE TABLE `sys_user_post`  (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `post_id` bigint(20) NOT NULL COMMENT '岗位ID',
  `provider_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`, `post_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户与岗位关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_post
-- ----------------------------
INSERT INTO `sys_user_post` VALUES (1, 1, 1);
INSERT INTO `sys_user_post` VALUES (2, 1, 1);
INSERT INTO `sys_user_post` VALUES (2, 2, 1);
INSERT INTO `sys_user_post` VALUES (3, 4, 1);
INSERT INTO `sys_user_post` VALUES (100, 4, 1);
INSERT INTO `sys_user_post` VALUES (105, 1, 1);
INSERT INTO `sys_user_post` VALUES (106, 2, 1);
INSERT INTO `sys_user_post` VALUES (107, 4, 1);
INSERT INTO `sys_user_post` VALUES (108, 4, 1);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `provider_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户和角色关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1, 1);
INSERT INTO `sys_user_role` VALUES (2, 2, 1);
INSERT INTO `sys_user_role` VALUES (3, 2, 1);

-- ----------------------------
-- Table structure for tbl_address
-- ----------------------------
DROP TABLE IF EXISTS `tbl_address`;
CREATE TABLE `tbl_address`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '详细地址',
  `addressee_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '收件人姓名',
  `mobile` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系人电话',
  `status` int(1) NULL DEFAULT NULL COMMENT '0-正常1-删除',
  `per_master_id` int(11) NULL DEFAULT NULL COMMENT '主人id',
  `provider_id` int(11) NULL DEFAULT NULL,
  `create_time` timestamp(0) NULL DEFAULT NULL,
  `update_time` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '邮寄地址' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tbl_address
-- ----------------------------

-- ----------------------------
-- Table structure for tbl_common_problem
-- ----------------------------
DROP TABLE IF EXISTS `tbl_common_problem`;
CREATE TABLE `tbl_common_problem`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `problem_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '问题编号',
  `problem` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '问题',
  `answer` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '解答',
  `del_status` int(1) NOT NULL DEFAULT 0 COMMENT '删除标识0-未删除1-已删除',
  `provider_id` int(11) NULL DEFAULT NULL,
  `reserved_field_one` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '预留字段1',
  `reserved_field_two` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '预留字段2',
  `reserved_field_three` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '预留字段3',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '常见问题表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tbl_common_problem
-- ----------------------------
INSERT INTO `tbl_common_problem` VALUES (1, 'X20201130090934583', '我是问题1', '袜带i打算可见当年史蒂', 1, 1, NULL, NULL, NULL, '2020-11-29 09:09:30', '2020-11-30 13:47:16');
INSERT INTO `tbl_common_problem` VALUES (2, 'F20201130104711402', '我是问题2', '注定平凡不平庸', 1, 1, NULL, NULL, NULL, '2020-11-29 10:47:11', '2020-11-30 13:51:22');
INSERT INTO `tbl_common_problem` VALUES (3, 'V20201130113326881', '我是问题3', '你是什么问题', 1, 1, NULL, NULL, NULL, '2020-11-30 11:33:26', '2020-11-30 11:40:09');
INSERT INTO `tbl_common_problem` VALUES (4, 'U20201130113859297', '为什么我的眼里常含泪水', '因为我对这土地爱的深沉', 0, 1, NULL, NULL, NULL, '2020-11-30 11:38:59', '2020-11-30 13:39:38');
INSERT INTO `tbl_common_problem` VALUES (5, 'L20201130134304889', '玲珑骰子安红豆 的下一句', '入骨相思君知否', 0, 1, NULL, NULL, NULL, '2020-11-30 13:43:04', '2020-11-30 13:43:04');
INSERT INTO `tbl_common_problem` VALUES (6, 'G20201130134417925', '红豆生南国,春来发几枝。愿君多采撷,此物_____', '最相思', 0, 1, NULL, NULL, NULL, '2020-11-30 13:44:17', '2020-11-30 13:44:17');
INSERT INTO `tbl_common_problem` VALUES (7, 'G2020113014003559', ' 1 2   3', '  0   1', 1, 1, NULL, NULL, NULL, '2020-11-30 14:00:36', '2020-11-30 14:00:44');
INSERT INTO `tbl_common_problem` VALUES (8, 'W2020120310193061', '袅袅娜娜', '你是猪', 0, 1, NULL, NULL, NULL, '2020-12-03 10:19:30', '2021-04-07 08:26:18');
INSERT INTO `tbl_common_problem` VALUES (9, 'Y2020120409154366', '21', '1122', 1, 1, NULL, NULL, NULL, '2020-12-04 09:15:43', '2020-12-04 09:15:43');
INSERT INTO `tbl_common_problem` VALUES (10, 'I2020120409164341', '323', '0111111000000000000000000000000000000000000000000000000000000000000000000', 1, 1, NULL, NULL, NULL, '2020-12-04 09:16:43', '2020-12-04 09:16:43');
INSERT INTO `tbl_common_problem` VALUES (11, 'Q20201207174210627', '注册&注销需要本人到场吗？', '不需要本人到场，也不需要寄送身份证原件。\r\n但需要根据工商、税务等部门的要求，做远程实名认证、电话核实、在线签名。', 1, 1, NULL, NULL, NULL, '2020-12-07 17:42:10', '2020-12-07 17:42:10');
INSERT INTO `tbl_common_problem` VALUES (12, 'S20210410212324872', '庄陶泽是不是人', '是', 1, 1, NULL, NULL, NULL, '2021-04-10 21:23:24', '2021-05-11 22:32:04');
INSERT INTO `tbl_common_problem` VALUES (13, 'A20210427211916669', '梁凡是不是很聪明', '是', 0, 1, NULL, NULL, NULL, '2021-04-27 21:19:17', '2021-04-27 21:19:17');

-- ----------------------------
-- Table structure for tbl_pet_clothes
-- ----------------------------
DROP TABLE IF EXISTS `tbl_pet_clothes`;
CREATE TABLE `tbl_pet_clothes`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `number` int(11) NULL DEFAULT NULL COMMENT '数量',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '价格',
  `cost` decimal(10, 2) NULL DEFAULT NULL COMMENT '成本',
  `photo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `remake` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `factory` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '厂家',
  `production_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生产日期',
  `shelf_life` int(10) NULL DEFAULT NULL COMMENT '保质期/天',
  `del_status` int(2) NULL DEFAULT NULL COMMENT '删除状态',
  `provider_id` int(11) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '宠物服饰表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tbl_pet_clothes
-- ----------------------------
INSERT INTO `tbl_pet_clothes` VALUES (3, '裙子', 1, 1000.00, 5.00, '', '瞎卖', '大萨达', '2009-04-29', 300, 0, 1, '2021-04-29 23:36:43', '2021-05-01 17:27:10');

-- ----------------------------
-- Table structure for tbl_pet_daily_necessities
-- ----------------------------
DROP TABLE IF EXISTS `tbl_pet_daily_necessities`;
CREATE TABLE `tbl_pet_daily_necessities`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `number` int(11) NULL DEFAULT NULL COMMENT '数量',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '价格',
  `cost` decimal(10, 2) NULL DEFAULT NULL COMMENT '成本',
  `photo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `remake` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `factory` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '厂家',
  `production_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生产日期',
  `shelf_life` int(10) NULL DEFAULT NULL COMMENT '保质期/天',
  `del_status` int(2) NULL DEFAULT NULL COMMENT '删除状态',
  `provider_id` int(11) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '宠物日用品表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tbl_pet_daily_necessities
-- ----------------------------
INSERT INTO `tbl_pet_daily_necessities` VALUES (2, '香波', 12, 1000.00, 500.00, NULL, '洗了会掉毛2', '大萨达', '2021-04-29', 10, 0, 1, '2021-04-29 23:19:15', '2021-04-29 23:19:37');

-- ----------------------------
-- Table structure for tbl_pet_foods
-- ----------------------------
DROP TABLE IF EXISTS `tbl_pet_foods`;
CREATE TABLE `tbl_pet_foods`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `number` int(10) NULL DEFAULT NULL COMMENT '数量',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '售价',
  `cost` decimal(10, 2) NULL DEFAULT NULL COMMENT '成本',
  `photo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `remake` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `factory` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '厂家',
  `production_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生产日期',
  `shelf_life` int(10) NULL DEFAULT NULL COMMENT '保质期/天',
  `del_status` int(2) NULL DEFAULT NULL COMMENT '删除状态',
  `provider_id` int(11) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '宠物食品表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tbl_pet_foods
-- ----------------------------
INSERT INTO `tbl_pet_foods` VALUES (1, '鱼罐头', 1, 1000.00, 500.00, NULL, '洗干净了2', '大萨达', '2021-04-25', 10, 1, 1, '2021-04-29 20:12:50', '2021-04-29 21:44:22');
INSERT INTO `tbl_pet_foods` VALUES (2, '骨头', 12, 1000.00, 500.00, NULL, '瞎了', '大萨达', '2021-04-29', 100, 0, 1, '2021-04-29 20:14:09', '2021-04-29 20:16:14');
INSERT INTO `tbl_pet_foods` VALUES (3, '鸡饲料', 12, 10005.00, 500.00, NULL, '瞎了', '大萨达', '2021-04-29', 1, 0, 1, '2021-04-29 21:44:40', '2021-04-29 21:44:40');

-- ----------------------------
-- Table structure for tbl_pet_foster_record
-- ----------------------------
DROP TABLE IF EXISTS `tbl_pet_foster_record`;
CREATE TABLE `tbl_pet_foster_record`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `pet_info_id` int(11) NULL DEFAULT NULL COMMENT '宠物信息id',
  `day` int(5) NULL DEFAULT NULL COMMENT '寄养日期/天',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '寄养价格',
  `status` int(2) NULL DEFAULT 0 COMMENT '是否已经带回家 0 否 1 是',
  `del_status` int(2) NULL DEFAULT 0 COMMENT '删除标识0-未删除1-已删除',
  `remake` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `provider_id` int(11) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '宠物寄养记录表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tbl_pet_foster_record
-- ----------------------------
INSERT INTO `tbl_pet_foster_record` VALUES (1, 14, 100, 1000.00, 0, 0, '瞎了', 1, '2021-04-27 20:29:09', '2021-04-29 21:41:17');

-- ----------------------------
-- Table structure for tbl_pet_info
-- ----------------------------
DROP TABLE IF EXISTS `tbl_pet_info`;
CREATE TABLE `tbl_pet_info`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `master_id` int(11) NULL DEFAULT NULL COMMENT '主人id',
  `pet_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '宠物名字',
  `pet_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '宠物种类',
  `pet_sex` int(1) NULL DEFAULT NULL COMMENT '宠物性别',
  `pet_age` int(3) NULL DEFAULT NULL COMMENT '宠物年龄',
  `pet_info_type` int(1) NULL DEFAULT NULL COMMENT '宠物信息类型 4 店养宠物 1 医疗宠物 2 销售宠物 3寄养宠物 5 宠物服务',
  `photo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '照片',
  `remake` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `del_status` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '删除状态 0 未删除 1 已删除',
  `provider_id` int(11) NOT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '宠物信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tbl_pet_info
-- ----------------------------
INSERT INTO `tbl_pet_info` VALUES (1, 1, '妞妞', '拉布拉多', 0, 2, 1, NULL, '黑色的拉布拉多', '0', 1, '2021-04-10 13:53:20', '2021-04-26 14:26:30');
INSERT INTO `tbl_pet_info` VALUES (2, 1, '露露', '怪物', 1, 2, 4, '', '1', '0', 1, '2021-04-10 15:32:51', '2021-04-26 14:26:27');
INSERT INTO `tbl_pet_info` VALUES (3, 2, '测试2', '猪', 1, 12, 2, NULL, '买100块', '0', 1, '2021-04-10 22:58:00', '2021-04-12 19:27:28');
INSERT INTO `tbl_pet_info` VALUES (4, 1, '医疗宠物测试', '猪', 0, 2, 1, NULL, '不能吃太多', '0', 1, '2021-04-26 21:23:49', '2021-04-26 21:23:49');
INSERT INTO `tbl_pet_info` VALUES (5, 1, '医疗宠物测试', '猪', 0, 2, 1, NULL, '不能吃太多', '0', 1, '2021-04-26 21:26:40', '2021-04-26 21:26:40');
INSERT INTO `tbl_pet_info` VALUES (7, 1, '嘿嘿', '狗', 0, 3, 1, NULL, '没救了', '0', 1, '2021-04-26 21:31:35', '2021-04-26 21:31:35');
INSERT INTO `tbl_pet_info` VALUES (8, 1, '测试333', '猫', 0, 2, 1, NULL, '测试', '0', 1, '2021-04-26 21:33:05', '2021-04-26 21:33:05');
INSERT INTO `tbl_pet_info` VALUES (9, 1, '测试销售', '猫', 0, 2, 2, 'http://116.62.141.102:8070/b5d5f765-fb5c-4fc2-8d30-019536c4b44f.png', '', '0', 1, '2021-04-27 14:17:45', '2021-04-30 13:08:03');
INSERT INTO `tbl_pet_info` VALUES (10, 1, '测试销售', '猫', 0, 2, 2, NULL, NULL, '0', 1, '2021-04-27 14:19:15', '2021-04-27 14:19:15');
INSERT INTO `tbl_pet_info` VALUES (11, 1, '测试', '猫', 0, 1, 1, NULL, '123123', '0', 1, '2021-04-27 14:21:08', '2021-04-27 14:21:08');
INSERT INTO `tbl_pet_info` VALUES (12, 1, '测试销售', '猫', 0, 2, 2, NULL, NULL, '0', 1, '2021-04-27 14:22:10', '2021-04-27 14:22:10');
INSERT INTO `tbl_pet_info` VALUES (13, 1, '1', '1', 0, 1, 2, 'http://116.62.141.102:8070/00c1f75e-f698-479a-9afc-cb4b4ea74f43.png', '', '0', 1, '2021-04-27 15:03:17', '2021-04-30 13:19:10');
INSERT INTO `tbl_pet_info` VALUES (14, 1, '测试', '猫', 0, 2, 3, 'http://116.62.141.102:8070/56ef0b2f-9649-4bed-b2fe-fe3a4d15ea78.png', '瞎了', '0', 1, '2021-04-27 20:29:09', '2021-04-30 15:48:48');
INSERT INTO `tbl_pet_info` VALUES (15, 1, '测试', '猫3', 0, 2, 5, 'http://116.62.141.102:8070/7ba5819e-d7a9-4d84-8cdd-80e6c664374e.jpg', '123', '0', 1, '2021-04-28 16:53:16', '2021-05-14 15:25:57');
INSERT INTO `tbl_pet_info` VALUES (16, 6, '2', '猪', 0, 2, 1, '', '111', '0', 1, '2021-05-14 15:28:52', '2021-05-14 15:29:10');
INSERT INTO `tbl_pet_info` VALUES (17, 6, '小小', '猪', 0, 1, 1, 'http://116.62.141.102:8070/19f44ef4-cf99-4178-95b7-6ff62db1cf71.jpg', 'xxxx', '1', 1, '2021-05-14 15:35:10', '2021-05-14 15:35:33');
INSERT INTO `tbl_pet_info` VALUES (18, 9, '猪猪', '猪', 0, 1, 4, NULL, '22', '0', 1, '2021-05-14 15:48:20', '2021-05-14 15:48:20');
INSERT INTO `tbl_pet_info` VALUES (19, 9, 'zhuzhu', '猪', 0, 1, 4, 'http://116.62.141.102:8070/08128a2c-501b-46bc-a9b2-5e66893a3065.jpg', '11', '1', 1, '2021-05-14 15:52:50', '2021-05-14 15:53:13');
INSERT INTO `tbl_pet_info` VALUES (20, 9, 'zhuzhu', '猪', 0, 1, 2, 'http://116.62.141.102:8070/a4cdf0a0-ab15-4443-b988-a33fdbd666f0.png', 'xxx', '0', 1, '2021-05-14 16:09:56', '2021-06-08 21:59:47');
INSERT INTO `tbl_pet_info` VALUES (21, 9, 'zhuzhu', '猪', 0, 1, 2, 'http://116.62.141.102:8070/4336ccd2-cb41-48e4-bfb2-a1e2f8945d05.jpg', '信息', '0', 1, '2021-05-14 21:20:36', '2022-08-01 20:43:16');
INSERT INTO `tbl_pet_info` VALUES (22, 9, 'zhuzhu', '猪', 0, 5, 2, 'http://116.62.141.102:8070/d0f2d8e3-962a-4693-bd56-a2a492e226b2.jpg', '测试', '1', 1, '2021-05-14 21:27:37', '2021-05-14 21:27:53');
INSERT INTO `tbl_pet_info` VALUES (23, 9, 'zhuzhu', '猪', 0, 6, 2, 'http://116.62.141.102:8070/b535e894-e61c-48e0-bd87-cfbc3a53612c.jpg', '测试', '1', 1, '2021-05-15 15:26:18', '2021-05-15 15:26:38');

-- ----------------------------
-- Table structure for tbl_pet_master_info
-- ----------------------------
DROP TABLE IF EXISTS `tbl_pet_master_info`;
CREATE TABLE `tbl_pet_master_info`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `age` int(11) NULL DEFAULT NULL COMMENT '年龄',
  `sex` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '性别 0 男 1 女 2 未知',
  `phonenumber` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱地址',
  `adress` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '居住地址',
  `del_status` int(2) NOT NULL DEFAULT 0 COMMENT '删除标识 0 未删除 1已删除',
  `provider_id` int(11) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '宠物主人信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tbl_pet_master_info
-- ----------------------------
INSERT INTO `tbl_pet_master_info` VALUES (1, '梁凡', 22, '0', '080912', 'liangfan1104@163.com', '杭州西湖区', 0, 1, '2021-04-10 13:52:21', '2021-04-10 13:52:25');
INSERT INTO `tbl_pet_master_info` VALUES (2, '测试主人', 100, '1', '12312312311', 'xx@qq.com', 'asdasdasd', 1, 1, '2021-04-10 22:58:00', '2021-05-14 15:42:28');
INSERT INTO `tbl_pet_master_info` VALUES (3, '梁凡2', 23, '1', '13107280913', 'xxxx@qq.com', '西湖区', 0, 1, '2021-04-26 15:00:41', '2021-04-26 15:00:41');
INSERT INTO `tbl_pet_master_info` VALUES (4, '梁凡33', 23, '0', '13107280914', 'xxxx@qq.com', '西湖区2', 1, 1, '2021-04-26 15:03:10', '2021-04-26 15:16:50');
INSERT INTO `tbl_pet_master_info` VALUES (5, '梁凡4', 23, '0', '13107280914', 'xxxx@qq.com', '西湖区', 0, 1, '2021-04-26 15:04:47', '2021-04-26 15:04:47');
INSERT INTO `tbl_pet_master_info` VALUES (6, '骨头', 22, '0', '13107280912', '851499434@qq.com', '杭州西湖区', 1, 1, '2021-05-14 14:28:22', '2021-05-14 14:28:31');
INSERT INTO `tbl_pet_master_info` VALUES (7, '梁凡5', 23, '0', '13107280000', 'xxxx@qq.com', '西湖区', 1, 1, '2021-05-14 15:21:33', '2021-05-14 15:21:46');
INSERT INTO `tbl_pet_master_info` VALUES (8, '梁凡5', 23, '0', '13107280001', 'xxxx@qq.com', '西湖区', 1, 1, '2021-05-14 15:34:25', '2021-05-14 15:34:39');
INSERT INTO `tbl_pet_master_info` VALUES (9, '梁凡6', 23, '0', '13107280912', 'xxxx@qq.com', '西湖区', 0, 1, '2021-05-14 15:42:13', '2021-05-14 15:42:13');
INSERT INTO `tbl_pet_master_info` VALUES (10, '梁凡55', 23, '0', '13107281231', 'xxxx@qq.com', '西湖区', 1, 1, '2021-05-14 15:51:55', '2021-05-14 15:52:11');
INSERT INTO `tbl_pet_master_info` VALUES (11, '梁凡8', 23, '0', '13107280000', 'xxxx@qq.com', '西湖区', 1, 1, '2021-05-14 16:08:51', '2021-05-14 16:09:05');
INSERT INTO `tbl_pet_master_info` VALUES (12, '梁凡88', 23, '0', '13107280000', 'xxxx@qq.com', '西湖区', 1, 1, '2021-05-14 21:19:38', '2021-05-14 21:19:49');
INSERT INTO `tbl_pet_master_info` VALUES (13, '梁凡7', 22, '0', '13107280000', 'xxxx@qq.com', '西湖区', 1, 1, '2021-05-14 21:26:45', '2021-05-14 21:26:55');

-- ----------------------------
-- Table structure for tbl_pet_medical_record
-- ----------------------------
DROP TABLE IF EXISTS `tbl_pet_medical_record`;
CREATE TABLE `tbl_pet_medical_record`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `pet_info_id` int(11) NULL DEFAULT NULL COMMENT '宠物信息id',
  `disease` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '疾病',
  `remake` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `method` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '治疗方法',
  `del_status` int(1) NOT NULL DEFAULT 0 COMMENT '删除标识0-未删除1-已删除',
  `provider_id` int(11) NOT NULL,
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '医疗宠物病历表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tbl_pet_medical_record
-- ----------------------------
INSERT INTO `tbl_pet_medical_record` VALUES (1, 1, '胖', '吃多了', '饿着就行了', 0, 1, '2021-04-26 20:58:36', '2021-04-26 20:58:40');
INSERT INTO `tbl_pet_medical_record` VALUES (2, 5, '胖', '不能吃太多', '减肥', 0, 1, '2021-04-26 21:26:40', '2021-04-26 21:26:40');
INSERT INTO `tbl_pet_medical_record` VALUES (4, 7, '细小', '没救了', '吃药', 0, 1, '2021-04-26 21:31:35', '2021-04-26 21:31:35');
INSERT INTO `tbl_pet_medical_record` VALUES (5, 8, '错误测试', '测试', '测试', 1, 1, '2021-04-26 21:33:05', '2021-04-26 21:39:38');
INSERT INTO `tbl_pet_medical_record` VALUES (6, 11, '错误测试', '123123', '123', 0, 1, '2021-04-27 14:21:08', '2021-04-27 14:21:08');

-- ----------------------------
-- Table structure for tbl_pet_medicine
-- ----------------------------
DROP TABLE IF EXISTS `tbl_pet_medicine`;
CREATE TABLE `tbl_pet_medicine`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `number` int(11) NULL DEFAULT NULL COMMENT '数量',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '价格',
  `cost` decimal(10, 2) NULL DEFAULT NULL COMMENT '成本',
  `photo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `remake` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `factory` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '厂家',
  `production_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生产日期',
  `shelf_life` int(10) NULL DEFAULT NULL COMMENT '保质期/天',
  `del_status` int(2) NULL DEFAULT NULL COMMENT '删除状态',
  `provider_id` int(11) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '宠物药品表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tbl_pet_medicine
-- ----------------------------
INSERT INTO `tbl_pet_medicine` VALUES (1, '毒药2', 12, 6666.00, 50.00, '', '吃了就死2', '大萨达', '2021-04-29', 100, 0, 1, '2021-04-29 22:50:50', '2021-05-14 15:54:42');

-- ----------------------------
-- Table structure for tbl_pet_sale_record
-- ----------------------------
DROP TABLE IF EXISTS `tbl_pet_sale_record`;
CREATE TABLE `tbl_pet_sale_record`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `pet_info_id` int(11) NULL DEFAULT NULL COMMENT '宠物信息id',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '售价',
  `cost` decimal(10, 2) NULL DEFAULT NULL COMMENT '成本',
  `remake` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `status` int(2) NULL DEFAULT 0 COMMENT '是否卖出',
  `del_status` int(2) NULL DEFAULT 0 COMMENT '删除标识 0 未删除 1已删除',
  `provider_id` int(11) NOT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '销售宠物信息记录' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tbl_pet_sale_record
-- ----------------------------
INSERT INTO `tbl_pet_sale_record` VALUES (1, 3, 100.00, 20.00, '222', 0, 1, 1, '2021-04-27 14:13:57', '2021-04-27 14:22:52');
INSERT INTO `tbl_pet_sale_record` VALUES (2, 12, 10000.00, 500.00, '纯种波斯猫', 1, 0, 1, '2021-04-27 14:22:15', '2021-04-27 15:01:01');
INSERT INTO `tbl_pet_sale_record` VALUES (3, 13, 1.00, 1.00, '1', 0, 0, 1, '2021-04-27 15:03:17', '2021-04-27 15:03:32');

-- ----------------------------
-- Table structure for tbl_pet_service_record
-- ----------------------------
DROP TABLE IF EXISTS `tbl_pet_service_record`;
CREATE TABLE `tbl_pet_service_record`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `pet_info_id` int(11) NULL DEFAULT NULL,
  `service` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '服务',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '费用',
  `status` int(2) NULL DEFAULT 0 COMMENT '状态',
  `remake` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `del_status` int(1) NULL DEFAULT 0 COMMENT '删除状态',
  `provider_id` int(11) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '宠物服务记录表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tbl_pet_service_record
-- ----------------------------
INSERT INTO `tbl_pet_service_record` VALUES (1, 15, '洗澡2', 10005.00, 0, '洗干净了', 0, 1, '2021-04-28 16:53:16', '2021-05-11 22:30:14');

-- ----------------------------
-- Table structure for tbl_pet_toys
-- ----------------------------
DROP TABLE IF EXISTS `tbl_pet_toys`;
CREATE TABLE `tbl_pet_toys`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '价格',
  `cost` decimal(10, 2) NULL DEFAULT NULL COMMENT '成本',
  `number` int(10) NULL DEFAULT NULL COMMENT '数量',
  `photo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图片',
  `remake` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `factory` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '厂家',
  `production_time` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生产日期',
  `del_status` int(2) NULL DEFAULT NULL COMMENT '删除状态',
  `provider_id` int(11) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '宠物玩具表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tbl_pet_toys
-- ----------------------------
INSERT INTO `tbl_pet_toys` VALUES (1, '玩具车', 100.00, 50.00, 2, 'http://116.62.141.102:8070/12ba59bc-0a0b-4595-b214-7b1687861386.png', '测试', '浪费生产厂家', '2021-04-29', 0, 1, '2021-04-29 01:16:57', '2021-04-30 13:32:19');
INSERT INTO `tbl_pet_toys` VALUES (2, '骨头', 1000.00, 500.00, 12, NULL, '1', '1', '2021-04-29', 1, 1, '2021-04-29 02:03:47', '2021-04-29 02:16:18');
INSERT INTO `tbl_pet_toys` VALUES (3, '骨头2', 1000.00, 50.00, 1, '', '瞎了', '大萨达', '2021-04-30', 0, 1, '2021-04-29 02:19:28', '2021-04-30 13:29:29');

SET FOREIGN_KEY_CHECKS = 1;
