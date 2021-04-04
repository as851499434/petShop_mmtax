/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : mmtax

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 09/09/2019 17:16:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for institution_info
-- ----------------------------
DROP TABLE IF EXISTS `institution_info`;
CREATE TABLE `institution_info`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `provider_id` int(10) NOT NULL COMMENT '机构编码',
  `institute_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '机构名称',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`, `provider_id`) USING BTREE,
  UNIQUE INDEX `provider_id_index`(`provider_id`) USING BTREE,
  INDEX `institute_name_index`(`institute_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '机构信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of institution_info
-- ----------------------------
INSERT INTO `institution_info` VALUES (3, 1, '测试', '2019-07-08 13:30:41', '2019-07-08 13:30:44');

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers`  (
  `sched_name` varchar(120) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `trigger_name` varchar(200) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `blob_data` blob NULL,
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars`  (
  `sched_name` varchar(120) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `calendar_name` varchar(200) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `calendar` blob NOT NULL,
  PRIMARY KEY (`sched_name`, `calendar_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers`  (
  `sched_name` varchar(120) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `trigger_name` varchar(200) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `cron_expression` varchar(200) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `time_zone_id` varchar(80) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers`  (
  `sched_name` varchar(120) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `entry_id` varchar(95) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `trigger_name` varchar(200) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `instance_name` varchar(200) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `fired_time` bigint(13) NOT NULL,
  `sched_time` bigint(13) NOT NULL,
  `priority` int(11) NOT NULL,
  `state` varchar(16) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `job_name` varchar(200) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `job_group` varchar(200) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `is_nonconcurrent` varchar(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `requests_recovery` varchar(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`sched_name`, `entry_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details`  (
  `sched_name` varchar(120) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `job_name` varchar(200) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `job_group` varchar(200) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `description` varchar(250) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `job_class_name` varchar(250) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `is_durable` varchar(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `is_nonconcurrent` varchar(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `is_update_data` varchar(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `requests_recovery` varchar(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `job_data` blob NULL,
  PRIMARY KEY (`sched_name`, `job_name`, `job_group`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks`  (
  `sched_name` varchar(120) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `lock_name` varchar(40) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  PRIMARY KEY (`sched_name`, `lock_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

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
  `sched_name` varchar(120) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  PRIMARY KEY (`sched_name`, `trigger_group`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state`  (
  `sched_name` varchar(120) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `instance_name` varchar(200) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `last_checkin_time` bigint(13) NOT NULL,
  `checkin_interval` bigint(13) NOT NULL,
  PRIMARY KEY (`sched_name`, `instance_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------
INSERT INTO `qrtz_scheduler_state` VALUES ('mmciScheduler', 'DESKTOP-EQ56DB11567994145151', 1567994150760, 15000);
INSERT INTO `qrtz_scheduler_state` VALUES ('mmtaxScheduler', 'DESKTOP-EQ56DB11568019979767', 1568020339097, 15000);

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers`  (
  `sched_name` varchar(120) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `trigger_name` varchar(200) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `repeat_count` bigint(7) NOT NULL,
  `repeat_interval` bigint(12) NOT NULL,
  `times_triggered` bigint(10) NOT NULL,
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers`  (
  `sched_name` varchar(120) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `trigger_name` varchar(200) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `str_prop_1` varchar(512) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `str_prop_2` varchar(512) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `str_prop_3` varchar(512) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `int_prop_1` int(11) NULL DEFAULT NULL,
  `int_prop_2` int(11) NULL DEFAULT NULL,
  `long_prop_1` bigint(20) NULL DEFAULT NULL,
  `long_prop_2` bigint(20) NULL DEFAULT NULL,
  `dec_prop_1` decimal(13, 4) NULL DEFAULT NULL,
  `dec_prop_2` decimal(13, 4) NULL DEFAULT NULL,
  `bool_prop_1` varchar(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `bool_prop_2` varchar(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers`  (
  `sched_name` varchar(120) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `trigger_name` varchar(200) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `job_name` varchar(200) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `job_group` varchar(200) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `description` varchar(250) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `next_fire_time` bigint(13) NULL DEFAULT NULL,
  `prev_fire_time` bigint(13) NULL DEFAULT NULL,
  `priority` int(11) NULL DEFAULT NULL,
  `trigger_state` varchar(16) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `trigger_type` varchar(8) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `start_time` bigint(13) NOT NULL,
  `end_time` bigint(13) NULL DEFAULT NULL,
  `calendar_name` varchar(200) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `misfire_instr` smallint(2) NULL DEFAULT NULL,
  `job_data` blob NULL,
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  INDEX `sched_name`(`sched_name`, `job_name`, `job_group`) USING BTREE,
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `job_name`, `job_group`) REFERENCES `qrtz_job_details` (`sched_name`, `job_name`, `job_group`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`  (
  `config_id` int(5) NOT NULL AUTO_INCREMENT COMMENT '参数主键',
  `config_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '参数名称',
  `config_key` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '参数键名',
  `config_value` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '参数键值',
  `config_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `provider_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`config_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '参数配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES (1, '主框架页-默认皮肤样式名称', 'sys.index.skinName', 'skin-blue', 'Y', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow', 1);
INSERT INTO `sys_config` VALUES (2, '用户管理-账号初始密码', 'sys.user.initPassword', '123456', 'Y', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '初始化密码 123456', 1);

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `dept_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `parent_id` bigint(20) NULL DEFAULT 0 COMMENT '父部门id',
  `ancestors` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '祖级列表',
  `dept_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '部门名称',
  `order_num` int(4) NULL DEFAULT 0 COMMENT '显示顺序',
  `leader` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '负责人',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `provider_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 110 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (100, 0, '0', '若依科技', 0, 'mmtax', '15888888888', 'mmtax@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', 1);
INSERT INTO `sys_dept` VALUES (101, 100, '0,100', '深圳总公司', 1, 'mmtax', '15888888888', 'mmtax@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', 1);
INSERT INTO `sys_dept` VALUES (102, 100, '0,100', '长沙分公司', 2, 'mmtax', '15888888888', 'mmtax@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', 1);
INSERT INTO `sys_dept` VALUES (103, 101, '0,100,101', '研发部门', 1, 'mmtax', '15888888888', 'mmtax@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', 1);
INSERT INTO `sys_dept` VALUES (104, 101, '0,100,101', '市场部门', 2, 'mmtax', '15888888888', 'mmtax@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', 1);
INSERT INTO `sys_dept` VALUES (105, 101, '0,100,101', '测试部门', 3, 'mmtax', '15888888888', 'mmtax@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', 1);
INSERT INTO `sys_dept` VALUES (106, 101, '0,100,101', '财务部门', 4, 'mmtax', '15888888888', 'mmtax@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', 1);
INSERT INTO `sys_dept` VALUES (107, 101, '0,100,101', '运维部门', 5, 'mmtax', '15888888888', 'mmtax@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', 1);
INSERT INTO `sys_dept` VALUES (108, 102, '0,100,102', '市场部门', 1, 'mmtax', '15888888888', 'mmtax@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', 1);
INSERT INTO `sys_dept` VALUES (109, 102, '0,100,102', '财务部门', 2, 'mmtax', '15888888888', 'mmtax@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', 1);

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data`  (
  `dict_code` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  `dict_sort` int(4) NULL DEFAULT 0 COMMENT '字典排序',
  `dict_label` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '表格回显样式',
  `is_default` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `provider_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`dict_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 149 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典数据表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------
INSERT INTO `sys_dict_data` VALUES (1, 1, '男', '0', 'sys_user_sex', '', '', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '性别男', 1);
INSERT INTO `sys_dict_data` VALUES (2, 2, '女', '1', 'sys_user_sex', '', '', 'N', '0', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '性别女', 1);
INSERT INTO `sys_dict_data` VALUES (3, 3, '未知', '2', 'sys_user_sex', '', '', 'N', '0', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '性别未知', 1);
INSERT INTO `sys_dict_data` VALUES (4, 1, '显示', '0', 'sys_show_hide', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '显示菜单', 1);
INSERT INTO `sys_dict_data` VALUES (5, 2, '隐藏', '1', 'sys_show_hide', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '隐藏菜单', 1);
INSERT INTO `sys_dict_data` VALUES (6, 1, '正常', '0', 'sys_normal_disable', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '正常状态', 1);
INSERT INTO `sys_dict_data` VALUES (7, 2, '停用', '1', 'sys_normal_disable', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '停用状态', 1);
INSERT INTO `sys_dict_data` VALUES (8, 1, '正常', '0', 'sys_job_status', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '正常状态', 1);
INSERT INTO `sys_dict_data` VALUES (9, 2, '暂停', '1', 'sys_job_status', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '停用状态', 1);
INSERT INTO `sys_dict_data` VALUES (10, 1, '是', 'Y', 'sys_yes_no', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '系统默认是', 1);
INSERT INTO `sys_dict_data` VALUES (11, 2, '否', 'N', 'sys_yes_no', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '系统默认否', 1);
INSERT INTO `sys_dict_data` VALUES (12, 1, '通知', '1', 'sys_notice_type', '', 'warning', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '通知', 1);
INSERT INTO `sys_dict_data` VALUES (13, 2, '公告', '2', 'sys_notice_type', '', 'success', 'N', '0', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '公告', 1);
INSERT INTO `sys_dict_data` VALUES (14, 1, '正常', '0', 'sys_notice_status', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '正常状态', 1);
INSERT INTO `sys_dict_data` VALUES (15, 2, '关闭', '1', 'sys_notice_status', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '关闭状态', 1);
INSERT INTO `sys_dict_data` VALUES (16, 1, '新增', '1', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '新增操作', 1);
INSERT INTO `sys_dict_data` VALUES (17, 2, '修改', '2', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '修改操作', 1);
INSERT INTO `sys_dict_data` VALUES (18, 3, '删除', '3', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '删除操作', 1);
INSERT INTO `sys_dict_data` VALUES (19, 4, '授权', '4', 'sys_oper_type', '', 'primary', 'N', '0', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '授权操作', 1);
INSERT INTO `sys_dict_data` VALUES (20, 5, '导出', '5', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '导出操作', 1);
INSERT INTO `sys_dict_data` VALUES (21, 6, '导入', '6', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '导入操作', 1);
INSERT INTO `sys_dict_data` VALUES (22, 7, '强退', '7', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '强退操作', 1);
INSERT INTO `sys_dict_data` VALUES (23, 8, '生成代码', '8', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '生成操作', 1);
INSERT INTO `sys_dict_data` VALUES (24, 9, '清空数据', '9', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '清空操作', 1);
INSERT INTO `sys_dict_data` VALUES (25, 1, '成功', '0', 'sys_common_status', '', 'primary', 'N', '0', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '正常状态', 1);
INSERT INTO `sys_dict_data` VALUES (26, 2, '失败', '1', 'sys_common_status', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '停用状态', 1);
INSERT INTO `sys_dict_data` VALUES (100, 1, '未打款', '0', 'trxorder_status', '', 'warning', 'N', '0', 'admin', '2019-07-22 16:21:35', 'admin', '2019-07-22 16:44:44', '', NULL);
INSERT INTO `sys_dict_data` VALUES (101, 1, '已打款', '1', 'trxorder_status', '', 'success', 'N', '0', 'admin', '2019-07-22 16:22:00', 'admin', '2019-07-22 16:44:41', '', NULL);
INSERT INTO `sys_dict_data` VALUES (102, 2, '打款挂起', '2', 'trxorder_status', '', 'warning', 'N', '0', 'admin', '2019-07-22 16:22:30', 'admin', '2019-07-22 16:44:36', '', NULL);
INSERT INTO `sys_dict_data` VALUES (103, 3, '调单', '3', 'trxorder_status', '', 'danger', 'N', '0', 'admin', '2019-07-22 16:22:47', 'admin', '2019-07-22 16:44:32', '', NULL);
INSERT INTO `sys_dict_data` VALUES (104, 1, '银行卡', 'BANK', 'pay_channel', NULL, 'primary', 'Y', '0', 'admin', '2019-07-22 16:56:29', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (105, 2, '支付宝', 'ALIPAY', 'pay_channel', NULL, 'primary', 'Y', '0', 'admin', '2019-07-22 16:56:47', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (106, 3, '微信', 'WECHAT', 'pay_channel', NULL, 'primary', 'Y', '0', 'admin', '2019-07-22 16:57:01', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (107, 1, '一般纳税人', '0', 'taxpayer_type', NULL, NULL, 'Y', '0', 'admin', '2019-07-22 17:53:36', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (108, 2, '小规模纳税人', '1', 'taxpayer_type', NULL, NULL, 'Y', '0', 'admin', '2019-07-22 17:54:18', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (109, 1, '增值税专用发票', '0', 'invoice_type', NULL, 'primary', 'Y', '0', 'admin', '2019-07-23 09:59:00', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (110, 2, '增值税普通发票', '1', 'invoice_type', '', 'primary', 'Y', '0', 'admin', '2019-07-23 10:00:09', 'admin', '2019-08-21 17:03:21', '', NULL);
INSERT INTO `sys_dict_data` VALUES (111, 1, '申请中', 'APPLY', 'invoice_status', NULL, 'info', 'Y', '0', 'admin', '2019-07-23 10:04:06', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (112, 2, '失效', 'INVALID', 'invoice_status', '', 'info', 'Y', '0', 'admin', '2019-07-23 10:04:20', 'admin', '2019-08-13 20:56:17', '', NULL);
INSERT INTO `sys_dict_data` VALUES (113, 3, '已寄出', 'POSTED', 'invoice_status', NULL, 'info', 'Y', '0', 'admin', '2019-07-23 10:04:33', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (114, 4, '已拒绝', 'REFUSE', 'invoice_status', NULL, 'info', 'Y', '0', 'admin', '2019-07-23 10:04:48', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (115, 1, '打款', '0', 'trx_type', '', 'success', 'Y', '0', 'admin', '2019-07-24 15:11:21', 'admin', '2019-07-24 15:24:09', '', NULL);
INSERT INTO `sys_dict_data` VALUES (116, 2, '充值', '1', 'trx_type', '', 'info', 'Y', '0', 'admin', '2019-07-24 15:11:50', 'admin', '2019-07-24 15:17:45', '', NULL);
INSERT INTO `sys_dict_data` VALUES (117, 1, '已处理', '1', 'order_status', '', 'success', 'Y', '0', 'admin', '2019-07-24 15:38:08', 'admin', '2019-07-30 17:32:18', '', NULL);
INSERT INTO `sys_dict_data` VALUES (118, 2, '未处理', '0', 'order_status', '', 'warning', 'Y', '0', 'admin', '2019-07-24 15:38:23', 'admin', '2019-07-30 17:32:11', '', NULL);
INSERT INTO `sys_dict_data` VALUES (119, 4, '畅捷支付', 'CHANPAY', 'pay_channel', '', 'primary', 'Y', '0', 'admin', '2019-08-02 09:53:09', 'admin', '2019-08-02 09:53:45', '', NULL);
INSERT INTO `sys_dict_data` VALUES (120, 1, '成功', '1', 'account_state', '', 'success', 'Y', '0', 'admin', '2019-08-15 10:41:36', 'admin', '2019-08-15 17:23:08', '', NULL);
INSERT INTO `sys_dict_data` VALUES (121, 2, '失败', '0', 'account_state', '', 'danger', 'Y', '0', 'admin', '2019-08-15 10:41:47', 'admin', '2019-08-15 17:23:02', '', NULL);
INSERT INTO `sys_dict_data` VALUES (122, 1, '代付', '0', 'account_pay_type', NULL, 'default', 'Y', '0', 'admin', '2019-08-15 10:42:40', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (123, 2, '充值', '1', 'account_pay_type', NULL, 'default', 'Y', '0', 'admin', '2019-08-15 10:42:54', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (124, 3, '返点', '2', 'account_pay_type', NULL, 'default', 'Y', '0', 'admin', '2019-08-15 10:43:07', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (125, 4, '转入', '3', 'account_pay_type', NULL, 'default', 'Y', '0', 'admin', '2019-08-15 10:43:20', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (126, 5, '转出', '4', 'account_pay_type', NULL, 'default', 'Y', '0', 'admin', '2019-08-15 10:43:29', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (127, 1, '未打款', '0', 'money_status', NULL, 'default', 'Y', '0', 'admin', '2019-08-15 11:59:36', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (128, 2, '已打款', '1', 'money_status', NULL, 'default', 'Y', '0', 'admin', '2019-08-15 11:59:50', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (129, 3, '打款挂起', '2', 'money_status', NULL, 'default', 'Y', '0', 'admin', '2019-08-15 12:00:05', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (130, 4, '调单状态', '3', 'money_status', NULL, 'default', 'Y', '0', 'admin', '2019-08-15 12:00:19', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (131, 1, '支付宝', 'ALIPAY', 'money_channel', NULL, 'default', 'Y', '0', 'admin', '2019-08-15 13:34:52', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (132, 2, '微信打款', 'WECHAT', 'money_channel', NULL, 'default', 'Y', '0', 'admin', '2019-08-15 13:35:45', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (133, 3, '银行卡', 'BANK', 'money_channel', NULL, 'default', 'Y', '0', 'admin', '2019-08-15 13:36:02', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (134, 1, '未作废', '0', 'Invalid_id', NULL, 'default', 'Y', '0', 'admin', '2019-08-16 13:49:43', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (135, 2, '已作废', '1', 'Invalid_id', NULL, 'default', 'Y', '0', 'admin', '2019-08-16 13:49:56', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (136, 3, '作废中', '2', 'Invalid_id', NULL, 'default', 'Y', '0', 'admin', '2019-08-16 13:50:08', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (137, 4, '已重开', '4', 'Invalid_id', NULL, 'default', 'Y', '0', 'admin', '2019-08-16 13:50:29', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (138, 5, '待重开', '3', 'Invalid_id', NULL, 'default', 'Y', '0', 'admin', '2019-08-19 17:15:05', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (139, 1, '激活', 'ACTIVE', 'merchants_state', NULL, 'info', 'Y', '0', 'admin', '2019-08-21 17:15:34', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (140, 2, '冻结', 'FROZED', 'merchants_state', NULL, 'warning', 'Y', '0', 'admin', '2019-08-21 17:15:52', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (141, 3, '注销', 'CANCEL', 'merchants_state', NULL, 'danger', 'Y', '0', 'admin', '2019-08-21 17:16:10', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (142, 1, '激活', 'ACTIVATED', 'account_status', NULL, 'info', 'Y', '0', 'admin', '2019-08-21 17:33:39', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (143, 1, '未加入', '0', 'Inspection_status', NULL, 'default', 'Y', '0', 'admin', '2019-08-22 09:14:12', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (144, 2, '已加入', '1', 'Inspection_status', NULL, 'default', 'Y', '0', 'admin', '2019-08-22 09:14:27', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (145, 3, '已拒绝', '2', 'Inspection_status', NULL, 'default', 'Y', '0', 'admin', '2019-08-22 09:14:44', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (146, 1, '未下载', '0', 'delete_state', NULL, 'success', 'Y', '0', 'admin', '2019-08-30 09:42:37', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (147, 2, '已下载', '1', 'delete_state', NULL, 'info', 'Y', '0', 'admin', '2019-08-30 09:42:52', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (148, 3, '已删除', '2', 'delete_state', NULL, 'danger', 'Y', '0', 'admin', '2019-08-30 09:43:09', '', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type`  (
  `dict_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典主键',
  `dict_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典名称',
  `dict_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `provider_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`dict_id`) USING BTREE,
  UNIQUE INDEX `dict_type`(`dict_type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 116 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典类型表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
INSERT INTO `sys_dict_type` VALUES (1, '用户性别', 'sys_user_sex', '0', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '用户性别列表', 1);
INSERT INTO `sys_dict_type` VALUES (2, '菜单状态', 'sys_show_hide', '0', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '菜单状态列表', 1);
INSERT INTO `sys_dict_type` VALUES (3, '系统开关', 'sys_normal_disable', '0', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '系统开关列表', 1);
INSERT INTO `sys_dict_type` VALUES (4, '任务状态', 'sys_job_status', '0', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '任务状态列表', 1);
INSERT INTO `sys_dict_type` VALUES (5, '系统是否', 'sys_yes_no', '0', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '系统是否列表', 1);
INSERT INTO `sys_dict_type` VALUES (6, '通知类型', 'sys_notice_type', '0', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '通知类型列表', 1);
INSERT INTO `sys_dict_type` VALUES (7, '通知状态', 'sys_notice_status', '0', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '通知状态列表', 1);
INSERT INTO `sys_dict_type` VALUES (8, '操作类型', 'sys_oper_type', '0', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '操作类型列表', 1);
INSERT INTO `sys_dict_type` VALUES (9, '系统状态', 'sys_common_status', '0', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '登录状态列表', 1);
INSERT INTO `sys_dict_type` VALUES (100, '打款状态', 'trxorder_status', '0', 'admin', '2019-07-22 16:20:25', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (101, '支付渠道', 'pay_channel', '0', 'admin', '2019-07-22 16:54:51', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (102, '纳税人类型', 'taxpayer_type', '0', 'admin', '2019-07-22 17:53:10', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (103, '发票类型', 'invoice_type', '0', 'admin', '2019-07-23 09:56:53', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (104, '发票状态', 'invoice_status', '0', 'admin', '2019-07-23 10:03:27', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (105, '交易类型', 'trx_type', '0', 'admin', '2019-07-24 15:10:44', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (106, '订单状态', 'order_status', '0', 'admin', '2019-07-24 15:37:36', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (107, '账户订单', 'account_state', '0', 'admin', '2019-08-15 10:41:01', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (108, '账户交易', 'account_pay_type', '0', 'admin', '2019-08-15 10:42:19', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (109, '打款状态', 'money_status', '0', 'admin', '2019-08-15 10:58:22', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (110, '打款渠道', 'money_channel', '0', 'admin', '2019-08-15 11:59:08', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (111, '作废标识', 'Invalid_id', '0', 'admin', '2019-08-16 13:49:21', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (112, '商户状态', 'merchants_state', '0', 'admin', '2019-08-21 17:14:50', 'admin', '2019-08-21 17:31:46', '', NULL);
INSERT INTO `sys_dict_type` VALUES (113, '账户状态', 'account_status', '0', 'admin', '2019-08-21 17:33:18', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (114, '免验状态', 'Inspection_status', '0', 'admin', '2019-08-22 09:13:02', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (115, '删除状态', 'delete_state', '0', 'admin', '2019-08-30 09:41:58', '', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_job`;
CREATE TABLE `sys_job`  (
  `job_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务ID',
  `job_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '任务名称',
  `job_group` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '任务组名',
  `method_name` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '任务方法',
  `method_params` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '方法参数',
  `cron_expression` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'cron执行表达式',
  `misfire_policy` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '3' COMMENT '计划执行错误策略（1立即执行 2执行一次 3放弃执行）',
  `concurrent` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '是否并发执行（0允许 1禁止）',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1暂停）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注信息',
  `provider_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`job_id`, `job_name`, `job_group`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '定时任务调度表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_job_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_job_log`;
CREATE TABLE `sys_job_log`  (
  `job_log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务日志ID',
  `job_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '任务名称',
  `job_group` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '任务组名',
  `method_name` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务方法',
  `method_params` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '方法参数',
  `job_message` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '日志信息',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '执行状态（0正常 1失败）',
  `exception_info` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '异常信息',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `provider_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`job_log_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '定时任务调度日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_job_log
-- ----------------------------
INSERT INTO `sys_job_log` VALUES (1, 'ryTask', '系统默认（有参）', 'ryParams', 'mmtax', 'ryTask 总共耗时：1毫秒', '0', '', '2019-07-10 14:26:35', 1);

-- ----------------------------
-- Table structure for sys_logininfor
-- ----------------------------
DROP TABLE IF EXISTS `sys_logininfor`;
CREATE TABLE `sys_logininfor`  (
  `info_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '访问ID',
  `login_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '登录账号',
  `ipaddr` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '操作系统',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
  `msg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '提示消息',
  `login_time` datetime(0) NULL DEFAULT NULL COMMENT '访问时间',
  `provider_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`info_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1099 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统访问记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_logininfor
-- ----------------------------
INSERT INTO `sys_logininfor` VALUES (1099, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-09-09 17:08:17', 1);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名称',
  `parent_id` bigint(20) NULL DEFAULT 0 COMMENT '父菜单ID',
  `order_num` int(4) NULL DEFAULT 0 COMMENT '显示顺序',
  `url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '#' COMMENT '请求地址',
  `target` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '打开方式（menuItem页签 menuBlank新窗口）',
  `menu_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `perms` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '#' COMMENT '菜单图标',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  `provider_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2066 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '系统管理', 0, 99, '#', 'menuItem', 'M', '0', '', 'fa fa-gear', 'admin', '2018-03-16 11:33:00', 'admin', '2019-07-19 15:07:04', '系统管理目录', 1);
INSERT INTO `sys_menu` VALUES (2, '系统监控', 0, 111, '#', 'menuItem', 'M', '0', '', 'fa fa-video-camera', 'admin', '2018-03-16 11:33:00', 'admin', '2019-07-19 15:06:47', '系统监控目录', 1);
INSERT INTO `sys_menu` VALUES (3, '系统工具', 0, 100, '#', 'menuItem', 'M', '0', '', 'fa fa-bars', 'admin', '2018-03-16 11:33:00', 'admin', '2019-07-19 15:06:58', '系统工具目录', 1);
INSERT INTO `sys_menu` VALUES (100, '用户管理', 1, 1, '/system/user', '', 'C', '0', 'system:user:view', '#', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '用户管理菜单', 1);
INSERT INTO `sys_menu` VALUES (101, '角色管理', 1, 2, '/system/role', '', 'C', '0', 'system:role:view', '#', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '角色管理菜单', 1);
INSERT INTO `sys_menu` VALUES (102, '菜单管理', 1, 3, '/system/menu', '', 'C', '0', 'system:menu:view', '#', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '菜单管理菜单', 1);
INSERT INTO `sys_menu` VALUES (103, '部门管理', 1, 4, '/system/dept', '', 'C', '0', 'system:dept:view', '#', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '部门管理菜单', 1);
INSERT INTO `sys_menu` VALUES (104, '岗位管理', 1, 5, '/system/post', '', 'C', '0', 'system:post:view', '#', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '岗位管理菜单', 1);
INSERT INTO `sys_menu` VALUES (105, '字典管理', 1, 6, '/system/dict', '', 'C', '0', 'system:dict:view', '#', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '字典管理菜单', 1);
INSERT INTO `sys_menu` VALUES (106, '参数设置', 1, 7, '/system/config', '', 'C', '0', 'system:config:view', '#', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '参数设置菜单', 1);
INSERT INTO `sys_menu` VALUES (107, '通知公告', 1, 8, '/system/notice', '', 'C', '0', 'system:notice:view', '#', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '通知公告菜单', 1);
INSERT INTO `sys_menu` VALUES (108, '日志管理', 1, 9, '#', '', 'M', '0', '', '#', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '日志管理菜单', 1);
INSERT INTO `sys_menu` VALUES (109, '在线用户', 2, 1, '/monitor/online', '', 'C', '0', 'monitor:online:view', '#', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '在线用户菜单', 1);
INSERT INTO `sys_menu` VALUES (110, '定时任务', 2, 2, '/monitor/job', '', 'C', '0', 'monitor:job:view', '#', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '定时任务菜单', 1);
INSERT INTO `sys_menu` VALUES (111, '数据监控', 2, 3, '/monitor/data', '', 'C', '0', 'monitor:data:view', '#', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '数据监控菜单', 1);
INSERT INTO `sys_menu` VALUES (112, '服务监控', 2, 3, '/monitor/server', '', 'C', '0', 'monitor:server:view', '#', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '服务监控菜单', 1);
INSERT INTO `sys_menu` VALUES (113, '表单构建', 3, 1, '/tool/build', '', 'C', '0', 'tool:build:view', '#', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '表单构建菜单', 1);
INSERT INTO `sys_menu` VALUES (114, '代码生成', 3, 2, '/tool/gen', '', 'C', '0', 'tool:gen:view', '#', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '代码生成菜单', 1);
INSERT INTO `sys_menu` VALUES (115, '系统接口', 3, 3, '/tool/swagger', '', 'C', '0', 'tool:swagger:view', '#', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '系统接口菜单', 1);
INSERT INTO `sys_menu` VALUES (500, '操作日志', 108, 1, '/monitor/operlog', '', 'C', '0', 'monitor:operlog:view', '#', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '操作日志菜单', 1);
INSERT INTO `sys_menu` VALUES (501, '登录日志', 108, 2, '/monitor/logininfor', '', 'C', '0', 'monitor:logininfor:view', '#', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '登录日志菜单', 1);
INSERT INTO `sys_menu` VALUES (1000, '用户查询', 100, 1, '#', '', 'F', '0', 'system:user:list', '#', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1001, '用户新增', 100, 2, '#', '', 'F', '0', 'system:user:add', '#', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1002, '用户修改', 100, 3, '#', '', 'F', '0', 'system:user:edit', '#', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1003, '用户删除', 100, 4, '#', '', 'F', '0', 'system:user:remove', '#', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1004, '用户导出', 100, 5, '#', '', 'F', '0', 'system:user:export', '#', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1005, '用户导入', 100, 6, '#', '', 'F', '0', 'system:user:import', '#', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1006, '重置密码', 100, 7, '#', '', 'F', '0', 'system:user:resetPwd', '#', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1007, '角色查询', 101, 1, '#', '', 'F', '0', 'system:role:list', '#', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1008, '角色新增', 101, 2, '#', '', 'F', '0', 'system:role:add', '#', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1009, '角色修改', 101, 3, '#', '', 'F', '0', 'system:role:edit', '#', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1010, '角色删除', 101, 4, '#', '', 'F', '0', 'system:role:remove', '#', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1011, '角色导出', 101, 5, '#', '', 'F', '0', 'system:role:export', '#', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1012, '菜单查询', 102, 1, '#', '', 'F', '0', 'system:menu:list', '#', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1013, '菜单新增', 102, 2, '#', '', 'F', '0', 'system:menu:add', '#', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1014, '菜单修改', 102, 3, '#', '', 'F', '0', 'system:menu:edit', '#', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1015, '菜单删除', 102, 4, '#', '', 'F', '0', 'system:menu:remove', '#', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1016, '部门查询', 103, 1, '#', '', 'F', '0', 'system:dept:list', '#', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1017, '部门新增', 103, 2, '#', '', 'F', '0', 'system:dept:add', '#', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1018, '部门修改', 103, 3, '#', '', 'F', '0', 'system:dept:edit', '#', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1019, '部门删除', 103, 4, '#', '', 'F', '0', 'system:dept:remove', '#', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1020, '岗位查询', 104, 1, '#', '', 'F', '0', 'system:post:list', '#', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1021, '岗位新增', 104, 2, '#', '', 'F', '0', 'system:post:add', '#', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1022, '岗位修改', 104, 3, '#', '', 'F', '0', 'system:post:edit', '#', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1023, '岗位删除', 104, 4, '#', '', 'F', '0', 'system:post:remove', '#', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1024, '岗位导出', 104, 5, '#', '', 'F', '0', 'system:post:export', '#', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1025, '字典查询', 105, 1, '#', '', 'F', '0', 'system:dict:list', '#', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1026, '字典新增', 105, 2, '#', '', 'F', '0', 'system:dict:add', '#', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1027, '字典修改', 105, 3, '#', '', 'F', '0', 'system:dict:edit', '#', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1028, '字典删除', 105, 4, '#', '', 'F', '0', 'system:dict:remove', '#', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1029, '字典导出', 105, 5, '#', '', 'F', '0', 'system:dict:export', '#', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1030, '参数查询', 106, 1, '#', '', 'F', '0', 'system:config:list', '#', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1031, '参数新增', 106, 2, '#', '', 'F', '0', 'system:config:add', '#', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1032, '参数修改', 106, 3, '#', '', 'F', '0', 'system:config:edit', '#', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1033, '参数删除', 106, 4, '#', '', 'F', '0', 'system:config:remove', '#', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1034, '参数导出', 106, 5, '#', '', 'F', '0', 'system:config:export', '#', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1035, '公告查询', 107, 1, '#', '', 'F', '0', 'system:notice:list', '#', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1036, '公告新增', 107, 2, '#', '', 'F', '0', 'system:notice:add', '#', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1037, '公告修改', 107, 3, '#', '', 'F', '0', 'system:notice:edit', '#', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1038, '公告删除', 107, 4, '#', '', 'F', '0', 'system:notice:remove', '#', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1039, '操作查询', 500, 1, '#', '', 'F', '0', 'monitor:operlog:list', '#', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1040, '操作删除', 500, 2, '#', '', 'F', '0', 'monitor:operlog:remove', '#', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1041, '详细信息', 500, 3, '#', '', 'F', '0', 'monitor:operlog:detail', '#', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1042, '日志导出', 500, 4, '#', '', 'F', '0', 'monitor:operlog:export', '#', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1043, '登录查询', 501, 1, '#', '', 'F', '0', 'monitor:logininfor:list', '#', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1044, '登录删除', 501, 2, '#', '', 'F', '0', 'monitor:logininfor:remove', '#', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1045, '日志导出', 501, 3, '#', '', 'F', '0', 'monitor:logininfor:export', '#', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1046, '在线查询', 109, 1, '#', '', 'F', '0', 'monitor:online:list', '#', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1047, '批量强退', 109, 2, '#', '', 'F', '0', 'monitor:online:batchForceLogout', '#', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1048, '单条强退', 109, 3, '#', '', 'F', '0', 'monitor:online:forceLogout', '#', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1049, '任务查询', 110, 1, '#', '', 'F', '0', 'monitor:job:list', '#', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1050, '任务新增', 110, 2, '#', '', 'F', '0', 'monitor:job:add', '#', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1051, '任务修改', 110, 3, '#', '', 'F', '0', 'monitor:job:edit', '#', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1052, '任务删除', 110, 4, '#', '', 'F', '0', 'monitor:job:remove', '#', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1053, '状态修改', 110, 5, '#', '', 'F', '0', 'monitor:job:changeStatus', '#', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1054, '任务详细', 110, 6, '#', '', 'F', '0', 'monitor:job:detail', '#', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1055, '任务导出', 110, 7, '#', '', 'F', '0', 'monitor:job:export', '#', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1056, '生成查询', 114, 1, '#', '', 'F', '0', 'tool:gen:list', '#', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (1057, '生成代码', 114, 2, '#', '', 'F', '0', 'tool:gen:code', '#', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_menu` VALUES (2000, '交易管理', 0, 4, '#', 'menuItem', 'M', '0', '', 'fa fa-desktop', 'admin', '2019-07-19 09:55:23', 'admin', '2019-08-28 10:12:02', '', 1);
INSERT INTO `sys_menu` VALUES (2001, '批量打款', 2000, 1, '/manager/batchPaymentRecord', 'menuItem', 'C', '0', 'manager:batchPayment:view', '#', 'admin', '2019-07-19 09:56:38', 'admin', '2019-07-19 09:58:52', '', 1);
INSERT INTO `sys_menu` VALUES (2002, '批量打款列表', 2001, 1, '#', 'menuItem', 'F', '0', 'manager:batchPayment:getBatchRecord', '#', 'admin', '2019-07-19 09:59:33', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2003, '交易订单', 2000, 2, '/manager/trxOrder', 'menuItem', 'C', '0', 'manager:trxOrder:view', '#', 'admin', '2019-07-19 10:45:24', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2004, '交易订单列表', 2003, 2, '#', 'menuItem', 'F', '0', 'manager:trxOrder:getListTrxOrder', '#', 'admin', '2019-07-19 10:46:04', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2005, '挂起订单', 2000, 3, '/manager/trxOrder/hangingTrxorder', 'menuItem', 'C', '0', 'manager:trxOrder:hangingTrxorder', '#', 'admin', '2019-07-19 14:24:18', 'admin', '2019-07-22 16:19:19', '', 1);
INSERT INTO `sys_menu` VALUES (2006, '挂起订单列表', 2005, 1, '#', 'menuItem', 'F', '0', 'manager:trxOrder:getListTrxOrder', '#', 'admin', '2019-07-19 14:24:45', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2007, '账户管理', 0, 2, '#', 'menuItem', 'M', '0', '', 'fa fa-credit-card', 'admin', '2019-07-19 15:05:54', 'admin', '2019-07-19 17:55:18', '', 1);
INSERT INTO `sys_menu` VALUES (2008, '账户列表', 2007, 1, '/manager/account', 'menuItem', 'C', '0', 'manager:account:view', '#', 'admin', '2019-07-19 15:06:34', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2010, '发票管理', 0, 3, '#', 'menuItem', 'M', '0', NULL, 'fa fa-map-o', 'admin', '2019-07-19 15:53:58', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2012, '发票申请', 2010, 1, 'manager/invoice', 'menuItem', 'C', '0', 'manager:invoice:view', '#', 'admin', '2019-07-19 15:56:40', 'admin', '2019-07-23 11:14:31', '', 1);
INSERT INTO `sys_menu` VALUES (2013, '发票申请列表', 2012, 1, '#', 'menuItem', 'F', '0', 'manager:invoice:queryInvoice', '#', 'admin', '2019-07-19 15:57:49', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2014, '发票详情', 2010, 2, 'manager/invoice/detail', 'menuItem', 'C', '0', 'manager:invoice:detail', '#', 'admin', '2019-07-19 16:23:56', 'admin', '2019-07-19 16:28:49', '', 1);
INSERT INTO `sys_menu` VALUES (2015, '开票详情', 2014, 1, '#', 'menuItem', 'F', '0', 'manager:invoice:querySuccessInvoices', '#', 'admin', '2019-07-19 16:29:45', 'admin', '2019-07-19 16:41:54', '', 1);
INSERT INTO `sys_menu` VALUES (2016, '发票信息', 2010, 3, 'manager/invoice/info', 'menuItem', 'C', '0', 'manager:invoice:info', '#', 'admin', '2019-07-19 17:24:20', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2017, '发票信息', 2016, 1, '#', 'menuItem', 'F', '0', 'manager:merchant:queryMerchantList', '#', 'admin', '2019-07-19 17:26:32', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2020, '商户管理', 0, 1, '#', 'menuItem', 'M', '0', '', 'fa fa-users', 'admin', '2019-07-19 17:43:58', 'admin', '2019-08-05 17:16:30', '', 1);
INSERT INTO `sys_menu` VALUES (2021, '商户秘钥', 0, 5, '#', 'menuItem', 'M', '0', NULL, 'fa fa-refresh', 'admin', '2019-07-19 17:49:19', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2022, '对接信息', 2021, 1, 'manager/secret', 'menuItem', 'C', '0', 'manager:secret:view', '#', 'admin', '2019-07-19 17:50:02', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2023, '商户管理', 2020, 1, 'manager/merchant', 'menuItem', 'C', '0', 'manager:merchant:view', '#', 'admin', '2019-07-19 17:50:18', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2024, '对接信息列表', 2022, 1, '#', 'menuItem', 'F', '0', 'manager:secret:getListKey', '#', 'admin', '2019-07-19 17:50:21', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2025, '商户信息列表', 2023, 1, '#', 'menuItem', 'F', '0', 'manager:merchant:queryMerchantList', '#', 'admin', '2019-07-19 17:51:08', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2026, '账户列表', 2008, 1, '#', 'menuItem', 'F', '0', 'manager:account:listAccount', '#', 'admin', '2019-07-19 17:56:30', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2027, '合作信息', 2020, 2, 'manager/merchant/cooperation', 'menuItem', 'C', '0', 'manager:merchant:cooperation', '#', 'admin', '2019-07-20 09:42:12', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2028, '合作信息列表', 2027, 1, '#', 'menuItem', 'F', '0', 'manager:merchant:queryCooperationInfo', '#', 'admin', '2019-07-20 09:43:26', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2029, '账户设置', 2020, 3, 'manager/merchant/account', 'menuItem', 'C', '0', 'manager:merchant:account', '#', 'admin', '2019-07-20 10:02:06', 'admin', '2019-07-20 10:08:18', '', 1);
INSERT INTO `sys_menu` VALUES (2030, '账户设置列表', 2029, 1, '#', 'menuItem', 'F', '0', 'manager:merchant:queryMerchantList', '#', 'admin', '2019-07-20 10:02:45', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2031, '重置密码', 2020, 4, 'manager/merchant/password', 'menuItem', 'C', '0', 'manager:merchant:password', '#', 'admin', '2019-07-20 10:21:27', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2032, '重置密码列表', 2031, 1, '#', 'menuItem', 'F', '0', 'queryMerchantList', '#', 'admin', '2019-07-20 10:22:33', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2034, '交易订单详情', 2003, 3, '#', 'menuItem', 'F', '0', 'manager:trxOrder:detail', '#', 'admin', '2019-07-22 10:16:27', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2035, '详情查看', 2023, 2, '#', 'menuItem', 'F', '0', 'manager:merchant:info', '#', 'admin', '2019-07-22 17:41:35', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2036, '合作信息编辑', 2028, 1, '#', 'menuItem', 'F', '0', 'manager:merchant:cooperationDetail', '#', 'admin', '2019-07-22 19:58:00', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2038, '审核发票申请', 2012, 2, '#', 'menuItem', 'F', '0', 'manager:invoice:checkInvoice', '#', 'admin', '2019-07-23 10:47:34', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2039, '资金流水', 2007, 2, '/manager/account/listCapitalFlow', 'menuItem', 'C', '0', 'manager:account:listCapitalFlow', '#', 'admin', '2019-07-24 14:54:58', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2041, '调单管理', 2000, 4, '/manager/trxOrder/orderSheet', 'menuItem', 'C', '0', 'manager:trxOrder:orderSheet', '#', 'admin', '2019-07-30 13:41:22', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2042, '账户详情', 2008, 2, '#', 'menuItem', 'F', '0', 'manager:account:detail', '#', 'admin', '2019-07-30 13:45:31', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2044, '抵扣账户', 2007, 3, '/manager/account/deduction', 'menuItem', 'C', '0', 'manager:account:deduction', '#', 'admin', '2019-07-30 13:50:22', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2045, '发票详情', 2012, 3, 'manager/invoice/invoiceApplyView', 'menuItem', 'F', '0', 'manager:invoice:view', '#', 'admin', '2019-07-30 14:11:32', 'admin', '2019-07-30 14:12:14', '', 1);
INSERT INTO `sys_menu` VALUES (2046, '批量打款详情', 2001, 2, '#', 'menuItem', 'F', '0', 'manager:batchPayment:detail', '#', 'admin', '2019-07-31 09:29:22', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2051, '错误重开', 2010, 4, 'manager/invoice/restart', 'menuItem', 'C', '0', 'system:menu:restart', '#', 'admin', '2019-08-07 15:45:19', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2052, '免验审核', 2007, 4, '/manager/roster', 'menuItem', 'C', '0', 'manager:roster:view', '#', 'admin', '2019-08-08 15:26:59', 'admin', '2019-08-08 15:31:47', '', 1);
INSERT INTO `sys_menu` VALUES (2053, '免验审核列表', 2052, 1, '#', 'menuItem', 'F', '0', 'manager:roster:list', '#', 'admin', '2019-08-08 15:29:29', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2054, '审核', 2052, 2, '#', 'menuItem', 'F', '0', 'manager:roster:status', '#', 'admin', '2019-08-08 15:30:37', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2055, '完税证明', 0, 6, '#', 'menuItem', 'M', '0', NULL, 'fa fa-coffee', 'admin', '2019-08-15 09:44:01', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2056, '完税证明', 2055, 1, '/manager/taxPaymentCertificate', 'menuItem', 'C', '0', 'manager:taxPaymentCertificate:view', '#', 'admin', '2019-08-15 09:44:54', 'admin', '2019-08-15 09:45:44', '', 1);
INSERT INTO `sys_menu` VALUES (2057, '完税证明列表', 2056, 1, '#', 'menuItem', 'F', '0', 'manager:taxPaymentCertificate:list', '#', 'admin', '2019-08-15 09:46:18', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2058, '完税证明添加', 2056, 2, '#', 'menuItem', 'F', '0', 'manager:taxPaymentCertificate:add', '#', 'admin', '2019-08-15 09:48:02', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2059, '结算信息', 2020, 4, 'manager/merchant/settleMentInfo', 'menuItem', 'C', '0', 'manager:merchant:settleMentInfo', '#', 'admin', '2019-08-22 20:44:07', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2060, '列表', 2059, 1, '#', 'menuItem', 'F', '0', 'manager:merchant:settleMentInfoList', '#', 'admin', '2019-08-22 20:45:09', 'admin', '2019-08-22 20:52:45', '', 1);
INSERT INTO `sys_menu` VALUES (2061, '更新', 2059, 2, '#', 'menuItem', 'F', '0', 'manager:merchant:updateSettleMentInfo', '#', 'admin', '2019-08-22 20:45:29', 'admin', '2019-08-22 20:53:00', '', 1);
INSERT INTO `sys_menu` VALUES (2062, '客户支持', 2020, 5, 'manager/merchant/customerSupport', 'menuItem', 'C', '0', 'manager:merchant:customerSupport', '#', 'admin', '2019-08-22 20:47:19', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2063, '列表', 2062, 1, '#', 'menuItem', 'F', '0', 'manager:merchant:customerSupportList', '#', 'admin', '2019-08-22 20:47:46', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2064, '更新', 2062, 2, '#', 'menuItem', 'F', '0', 'manager:merchant:updateCustomerSupport', '#', 'admin', '2019-08-22 20:48:00', '', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (2065, '更新', 2056, 3, '#', 'menuItem', 'F', '0', 'manager:taxPaymentCertificate:update', '#', 'admin', '2019-08-28 16:38:42', '', NULL, '', 1);

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice`  (
  `notice_id` int(4) NOT NULL AUTO_INCREMENT COMMENT '公告ID',
  `notice_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公告标题',
  `notice_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公告类型（1通知 2公告）',
  `notice_content` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公告内容',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `provider_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`notice_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '通知公告表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_notice
-- ----------------------------
INSERT INTO `sys_notice` VALUES (1, '温馨提醒：2018-07-01 若依新版本发布啦', '2', '新版本内容', '0', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '管理员', 1);
INSERT INTO `sys_notice` VALUES (2, '维护通知：2018-07-01 若依系统凌晨维护', '1', '维护内容', '0', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '管理员', 1);

-- ----------------------------
-- Table structure for sys_oper_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_oper_log`;
CREATE TABLE `sys_oper_log`  (
  `oper_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '模块标题',
  `business_type` int(2) NULL DEFAULT 0 COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `method` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '方法名称',
  `operator_type` int(1) NULL DEFAULT 0 COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
  `oper_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '操作人员',
  `dept_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '部门名称',
  `oper_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '请求URL',
  `oper_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '主机地址',
  `oper_location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '操作地点',
  `oper_param` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '请求参数',
  `status` int(1) NULL DEFAULT 0 COMMENT '操作状态（0正常 1异常）',
  `error_msg` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '错误消息',
  `oper_time` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
  `provider_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`oper_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 34345 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '操作日志记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_oper_log
-- ----------------------------
INSERT INTO `sys_oper_log` VALUES (34345, '商户列表查询', 1, 'com.mmtax.web.controller.business.ManagerMerchantController.query()', 1, 'admin', '研发部门', '/manager/merchant/query', '127.0.0.1', '内网IP', '{\r\n  \"pageSize\" : [ \"10\" ],\r\n  \"pageNum\" : [ \"1\" ],\r\n  \"orderByColumn\" : [ \"\" ],\r\n  \"isAsc\" : [ \"asc\" ],\r\n  \"merchantName\" : [ \"\" ],\r\n  \"contactsMobile\" : [ \"\" ],\r\n  \"startDate\" : [ \"\" ],\r\n  \"endDate\" : [ \"\" ]\r\n}', 0, NULL, '2019-09-09 17:08:30', 1);
INSERT INTO `sys_oper_log` VALUES (34346, '商户合作信息列表', 1, 'com.mmtax.web.controller.business.ManagerMerchantController.queryCooperationInfo()', 1, 'admin', '研发部门', '/manager/merchant/queryCooperationInfo', '127.0.0.1', '内网IP', '{\r\n  \"pageSize\" : [ \"10\" ],\r\n  \"pageNum\" : [ \"1\" ],\r\n  \"orderByColumn\" : [ \"\" ],\r\n  \"isAsc\" : [ \"asc\" ],\r\n  \"merchantName\" : [ \"\" ],\r\n  \"startDate\" : [ \"\" ],\r\n  \"endDate\" : [ \"\" ]\r\n}', 0, NULL, '2019-09-09 17:08:32', 1);
INSERT INTO `sys_oper_log` VALUES (34347, '商户列表查询', 1, 'com.mmtax.web.controller.business.ManagerMerchantController.query()', 1, 'admin', '研发部门', '/manager/merchant/query', '127.0.0.1', '内网IP', '{\r\n  \"pageSize\" : [ \"10\" ],\r\n  \"pageNum\" : [ \"1\" ],\r\n  \"orderByColumn\" : [ \"\" ],\r\n  \"isAsc\" : [ \"asc\" ],\r\n  \"merchantName\" : [ \"\" ],\r\n  \"contactsMobile\" : [ \"\" ],\r\n  \"startDate\" : [ \"\" ],\r\n  \"endDate\" : [ \"\" ]\r\n}', 0, NULL, '2019-09-09 17:08:33', 1);
INSERT INTO `sys_oper_log` VALUES (34348, '商户列表查询', 1, 'com.mmtax.web.controller.business.ManagerMerchantController.query()', 1, 'admin', '研发部门', '/manager/merchant/query', '127.0.0.1', '内网IP', '{\r\n  \"pageSize\" : [ \"10\" ],\r\n  \"pageNum\" : [ \"1\" ],\r\n  \"orderByColumn\" : [ \"\" ],\r\n  \"isAsc\" : [ \"asc\" ],\r\n  \"merchantName\" : [ \"\" ],\r\n  \"contactsMobile\" : [ \"\" ],\r\n  \"startDate\" : [ \"\" ],\r\n  \"endDate\" : [ \"\" ]\r\n}', 0, NULL, '2019-09-09 17:08:33', 1);
INSERT INTO `sys_oper_log` VALUES (34349, '结算信息列表', 1, 'com.mmtax.web.controller.business.ManagerMerchantController.settleMentInfoList()', 1, 'admin', '研发部门', '/manager/merchant/settleMentInfoList', '127.0.0.1', '内网IP', '{\r\n  \"pageSize\" : [ \"10\" ],\r\n  \"pageNum\" : [ \"1\" ],\r\n  \"orderByColumn\" : [ \"\" ],\r\n  \"isAsc\" : [ \"asc\" ],\r\n  \"accountName\" : [ \"\" ],\r\n  \"accountNo\" : [ \"\" ],\r\n  \"bankName\" : [ \"\" ],\r\n  \"merchantId\" : [ \"\" ]\r\n}', 0, NULL, '2019-09-09 17:08:34', 1);
INSERT INTO `sys_oper_log` VALUES (34350, '商户合作信息列表', 1, 'com.mmtax.web.controller.business.ManagerMerchantController.customerSupportList()', 1, 'admin', '研发部门', '/manager/merchant/customerSupportList', '127.0.0.1', '内网IP', '{\r\n  \"pageSize\" : [ \"10\" ],\r\n  \"pageNum\" : [ \"1\" ],\r\n  \"orderByColumn\" : [ \"\" ],\r\n  \"isAsc\" : [ \"asc\" ],\r\n  \"customerManager\" : [ \"\" ],\r\n  \"customerManagerMobile\" : [ \"\" ],\r\n  \"email\" : [ \"\" ],\r\n  \"successCustomerManager\" : [ \"\" ],\r\n  \"successCustomerManagerMobile\" : [ \"\" ],\r\n  \"successCustomerEmail\" : [ \"\" ],\r\n  \"businessSupport\" : [ \"\" ]\r\n}', 0, NULL, '2019-09-09 17:08:35', 1);

-- ----------------------------
-- Table structure for sys_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post`  (
  `post_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
  `post_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '岗位编码',
  `post_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '岗位名称',
  `post_sort` int(4) NOT NULL COMMENT '显示顺序',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `provider_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`post_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '岗位信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_post
-- ----------------------------
INSERT INTO `sys_post` VALUES (1, 'ceo', '董事长', 1, '0', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_post` VALUES (2, 'se', '项目经理', 2, '0', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_post` VALUES (3, 'hr', '人力资源', 3, '0', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '', 1);
INSERT INTO `sys_post` VALUES (4, 'user', '普通员工', 4, '0', 'admin', '2018-03-16 11:33:00', 'mmtax', '2018-03-16 11:33:00', '', 1);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色权限字符串',
  `role_sort` int(4) NOT NULL COMMENT '显示顺序',
  `data_scope` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `provider_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '管理员', 'admin', 1, '2', '0', '0', 'admin', '2018-03-16 11:33:00', 'admin', '2019-08-08 15:36:32', '管理员', 1);
INSERT INTO `sys_role` VALUES (2, '普通角色', 'common', 2, '2', '0', '0', 'admin', '2018-03-16 11:33:00', 'admin', '2019-08-08 15:36:37', '普通角色', 1);
INSERT INTO `sys_role` VALUES (3, '系统管理员', 'yutou', 3, '2', '0', '0', 'admin', '2019-08-08 10:02:30', 'admin', '2019-08-08 15:45:21', NULL, 1);

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept`  (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `dept_id` bigint(20) NOT NULL COMMENT '部门ID',
  `provider_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`role_id`, `dept_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色和部门关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_dept
-- ----------------------------
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
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色和菜单关联表' ROW_FORMAT = Dynamic;

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
INSERT INTO `sys_role_menu` VALUES (2, 1, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2, 1);
INSERT INTO `sys_role_menu` VALUES (2, 3, 1);
INSERT INTO `sys_role_menu` VALUES (2, 100, 1);
INSERT INTO `sys_role_menu` VALUES (2, 101, 1);
INSERT INTO `sys_role_menu` VALUES (2, 102, 1);
INSERT INTO `sys_role_menu` VALUES (2, 103, 1);
INSERT INTO `sys_role_menu` VALUES (2, 104, 1);
INSERT INTO `sys_role_menu` VALUES (2, 105, 1);
INSERT INTO `sys_role_menu` VALUES (2, 106, 1);
INSERT INTO `sys_role_menu` VALUES (2, 107, 1);
INSERT INTO `sys_role_menu` VALUES (2, 108, 1);
INSERT INTO `sys_role_menu` VALUES (2, 109, 1);
INSERT INTO `sys_role_menu` VALUES (2, 110, 1);
INSERT INTO `sys_role_menu` VALUES (2, 111, 1);
INSERT INTO `sys_role_menu` VALUES (2, 112, 1);
INSERT INTO `sys_role_menu` VALUES (2, 113, 1);
INSERT INTO `sys_role_menu` VALUES (2, 114, 1);
INSERT INTO `sys_role_menu` VALUES (2, 115, 1);
INSERT INTO `sys_role_menu` VALUES (2, 500, 1);
INSERT INTO `sys_role_menu` VALUES (2, 501, 1);
INSERT INTO `sys_role_menu` VALUES (2, 1000, 1);
INSERT INTO `sys_role_menu` VALUES (2, 1001, 1);
INSERT INTO `sys_role_menu` VALUES (2, 1002, 1);
INSERT INTO `sys_role_menu` VALUES (2, 1003, 1);
INSERT INTO `sys_role_menu` VALUES (2, 1004, 1);
INSERT INTO `sys_role_menu` VALUES (2, 1005, 1);
INSERT INTO `sys_role_menu` VALUES (2, 1006, 1);
INSERT INTO `sys_role_menu` VALUES (2, 1007, 1);
INSERT INTO `sys_role_menu` VALUES (2, 1008, 1);
INSERT INTO `sys_role_menu` VALUES (2, 1009, 1);
INSERT INTO `sys_role_menu` VALUES (2, 1010, 1);
INSERT INTO `sys_role_menu` VALUES (2, 1011, 1);
INSERT INTO `sys_role_menu` VALUES (2, 1012, 1);
INSERT INTO `sys_role_menu` VALUES (2, 1013, 1);
INSERT INTO `sys_role_menu` VALUES (2, 1014, 1);
INSERT INTO `sys_role_menu` VALUES (2, 1015, 1);
INSERT INTO `sys_role_menu` VALUES (2, 1016, 1);
INSERT INTO `sys_role_menu` VALUES (2, 1017, 1);
INSERT INTO `sys_role_menu` VALUES (2, 1018, 1);
INSERT INTO `sys_role_menu` VALUES (2, 1019, 1);
INSERT INTO `sys_role_menu` VALUES (2, 1020, 1);
INSERT INTO `sys_role_menu` VALUES (2, 1021, 1);
INSERT INTO `sys_role_menu` VALUES (2, 1022, 1);
INSERT INTO `sys_role_menu` VALUES (2, 1023, 1);
INSERT INTO `sys_role_menu` VALUES (2, 1024, 1);
INSERT INTO `sys_role_menu` VALUES (2, 1025, 1);
INSERT INTO `sys_role_menu` VALUES (2, 1026, 1);
INSERT INTO `sys_role_menu` VALUES (2, 1027, 1);
INSERT INTO `sys_role_menu` VALUES (2, 1028, 1);
INSERT INTO `sys_role_menu` VALUES (2, 1029, 1);
INSERT INTO `sys_role_menu` VALUES (2, 1030, 1);
INSERT INTO `sys_role_menu` VALUES (2, 1031, 1);
INSERT INTO `sys_role_menu` VALUES (2, 1032, 1);
INSERT INTO `sys_role_menu` VALUES (2, 1033, 1);
INSERT INTO `sys_role_menu` VALUES (2, 1034, 1);
INSERT INTO `sys_role_menu` VALUES (2, 1035, 1);
INSERT INTO `sys_role_menu` VALUES (2, 1036, 1);
INSERT INTO `sys_role_menu` VALUES (2, 1037, 1);
INSERT INTO `sys_role_menu` VALUES (2, 1038, 1);
INSERT INTO `sys_role_menu` VALUES (2, 1039, 1);
INSERT INTO `sys_role_menu` VALUES (2, 1040, 1);
INSERT INTO `sys_role_menu` VALUES (2, 1041, 1);
INSERT INTO `sys_role_menu` VALUES (2, 1042, 1);
INSERT INTO `sys_role_menu` VALUES (2, 1043, 1);
INSERT INTO `sys_role_menu` VALUES (2, 1044, 1);
INSERT INTO `sys_role_menu` VALUES (2, 1045, 1);
INSERT INTO `sys_role_menu` VALUES (2, 1046, 1);
INSERT INTO `sys_role_menu` VALUES (2, 1047, 1);
INSERT INTO `sys_role_menu` VALUES (2, 1048, 1);
INSERT INTO `sys_role_menu` VALUES (2, 1049, 1);
INSERT INTO `sys_role_menu` VALUES (2, 1050, 1);
INSERT INTO `sys_role_menu` VALUES (2, 1051, 1);
INSERT INTO `sys_role_menu` VALUES (2, 1052, 1);
INSERT INTO `sys_role_menu` VALUES (2, 1053, 1);
INSERT INTO `sys_role_menu` VALUES (2, 1054, 1);
INSERT INTO `sys_role_menu` VALUES (2, 1055, 1);
INSERT INTO `sys_role_menu` VALUES (2, 1056, 1);
INSERT INTO `sys_role_menu` VALUES (2, 1057, 1);
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
INSERT INTO `sys_role_menu` VALUES (3, 2000, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2001, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2002, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2003, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2004, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2005, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2006, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2007, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2008, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2010, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2012, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2013, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2014, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2015, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2016, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2017, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2020, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2021, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2022, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2023, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2024, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2025, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2026, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2027, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2028, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2029, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2030, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2031, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2032, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2034, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2035, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2036, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2038, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2039, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2041, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2042, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2044, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2045, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2046, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2051, 1);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `dept_id` bigint(20) NULL DEFAULT NULL COMMENT '部门ID',
  `login_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录账号',
  `user_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户昵称',
  `user_type` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '00' COMMENT '用户类型（00系统用户）',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '用户邮箱',
  `phonenumber` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '手机号码',
  `sex` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `avatar` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '头像路径',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '密码',
  `salt` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '盐加密',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `login_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '最后登陆IP',
  `login_date` datetime(0) NULL DEFAULT NULL COMMENT '最后登陆时间',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `provider_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 102 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 103, 'admin', '美喵优税管理员', '00', 'mmtax@163.com', '15888888888', '1', '2019/08/02/983cecb4c24d582f26e2c7cfafb7a0e0.png', 'c3918dcc1a7152e9191d12a184de584c', '92e2f3', '0', '0', '127.0.0.1', '2019-09-09 17:08:17', 'admin', '2018-03-16 11:33:00', 'mmtax', '2019-09-09 17:08:17', '管理员', 1);
INSERT INTO `sys_user` VALUES (100, 103, '18288888888', 'mmtax', '00', '121212@qq.com', '18288888888', '0', '', '8d3cdb6e365e8b48a0e0635fa55f3700', '234beb', '0', '0', '127.0.0.1', '2019-07-08 13:55:27', 'admin', '2019-07-08 13:53:59', 'admin', '2019-07-23 09:29:46', '', 1);
INSERT INTO `sys_user` VALUES (101, 106, '13344313433', '11', '00', '111@qq.com', '13344313433', '0', '', '041b643fc7567d7b809cb21343de1add', '24c53b', '0', '0', '', NULL, 'admin', '2019-08-07 13:36:05', '', NULL, NULL, 1);

-- ----------------------------
-- Table structure for sys_user_online
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_online`;
CREATE TABLE `sys_user_online`  (
  `sessionId` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '用户会话id',
  `login_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '登录账号',
  `dept_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '部门名称',
  `ipaddr` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '操作系统',
  `status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '在线状态on_line在线off_line离线',
  `start_timestamp` datetime(0) NULL DEFAULT NULL COMMENT 'session创建时间',
  `last_access_time` datetime(0) NULL DEFAULT NULL COMMENT 'session最后访问时间',
  `expire_time` int(5) NULL DEFAULT 0 COMMENT '超时时间，单位为分钟',
  `provider_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`sessionId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '在线用户记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_online
-- ----------------------------
INSERT INTO `sys_user_online` VALUES ('98a4bc62-d791-4062-97a3-02444fdb6d29', 'admin', '研发部门', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', 'on_line', '2019-09-09 17:08:13', '2019-09-09 17:08:18', 1800000, NULL);

-- ----------------------------
-- Table structure for sys_user_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_post`;
CREATE TABLE `sys_user_post`  (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `post_id` bigint(20) NOT NULL COMMENT '岗位ID',
  `provider_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`, `post_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户与岗位关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_post
-- ----------------------------
INSERT INTO `sys_user_post` VALUES (1, 1, 1);
INSERT INTO `sys_user_post` VALUES (2, 2, 1);
INSERT INTO `sys_user_post` VALUES (100, 4, 1);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `provider_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户和角色关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1, 1);
INSERT INTO `sys_user_role` VALUES (2, 2, 1);
INSERT INTO `sys_user_role` VALUES (100, 1, 1);

-- ----------------------------
-- Table structure for tbl_address
-- ----------------------------
DROP TABLE IF EXISTS `tbl_address`;
CREATE TABLE `tbl_address`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '详细地址',
  `addressee_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收件人姓名',
  `mobile` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人电话',
  `status` int(1) NULL DEFAULT NULL COMMENT '0-正常1-删除',
  `merchant_id` int(11) NULL DEFAULT NULL COMMENT '商户id',
  `provider_id` int(11) NULL DEFAULT NULL,
  `create_time` timestamp(0) NULL DEFAULT NULL,
  `update_time` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 152 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '邮寄地址' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tbl_batch_payment_record
-- ----------------------------
DROP TABLE IF EXISTS `tbl_batch_payment_record`;
CREATE TABLE `tbl_batch_payment_record`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `trx_external_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '平台订单号',
  `batch_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '批次号',
  `payment_channel` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '打款渠道BANK-银行ALIPAY-支付宝WECHAT-微信',
  `payment_count` int(11) NULL DEFAULT NULL COMMENT '打款记录',
  `payment_amount` decimal(20, 2) NULL DEFAULT NULL COMMENT '商户打款请求总额',
  `payment_actual_amount` decimal(20, 2) NULL DEFAULT NULL COMMENT '实际打款总额',
  `creater` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `operator` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '打款操作人',
  `status` int(1) NULL DEFAULT NULL COMMENT '0-未处理1-已处理',
  `merchant_id` int(11) NULL DEFAULT NULL COMMENT '商户id',
  `provider_id` int(11) NULL DEFAULT NULL,
  `create_time` timestamp(0) NULL DEFAULT NULL,
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 184 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '批量打款记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tbl_certificate_type
-- ----------------------------
DROP TABLE IF EXISTS `tbl_certificate_type`;
CREATE TABLE `tbl_certificate_type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '证件名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '证件类型表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_certificate_type
-- ----------------------------
INSERT INTO `tbl_certificate_type` VALUES (1, '居民身份证');
INSERT INTO `tbl_certificate_type` VALUES (2, '军官证');
INSERT INTO `tbl_certificate_type` VALUES (3, '护照');
INSERT INTO `tbl_certificate_type` VALUES (4, '港澳居民来往内地通行证（回乡证）');
INSERT INTO `tbl_certificate_type` VALUES (5, '台湾同胞来往内地通行证');
INSERT INTO `tbl_certificate_type` VALUES (6, '警官证');
INSERT INTO `tbl_certificate_type` VALUES (7, '士兵证');
INSERT INTO `tbl_certificate_type` VALUES (8, '户口簿');
INSERT INTO `tbl_certificate_type` VALUES (9, '临时身份证');
INSERT INTO `tbl_certificate_type` VALUES (10, '外国人居留证');
INSERT INTO `tbl_certificate_type` VALUES (11, '其他证件');

-- ----------------------------
-- Table structure for tbl_cooperation
-- ----------------------------
DROP TABLE IF EXISTS `tbl_cooperation`;
CREATE TABLE `tbl_cooperation`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `signing_type` int(1) NULL DEFAULT NULL COMMENT '签约类型0-微信签约1-线上签约2纸质签约',
  `payment_adjust_type` int(1) NULL DEFAULT NULL COMMENT '0-手动调整',
  `settle_type` int(1) NULL DEFAULT NULL COMMENT '0-只能结算',
  `business_type` int(1) NULL DEFAULT NULL COMMENT '0-业务类型',
  `payment_model` int(1) NULL DEFAULT NULL COMMENT '0-实时付费',
  `payment_requirement` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '付款要求',
  `single_service_rate` decimal(11, 2) NULL DEFAULT NULL COMMENT '商户单笔服务费费率',
  `meal_info_id` int(11) NULL DEFAULT NULL COMMENT '套餐',
  `rate_switch` int(1) NULL DEFAULT NULL COMMENT '用户服务费费率开关',
  `payment_channel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'BANK-银行ALIPAY-支付宝WECHAT-微信',
  `single_quota` decimal(11, 2) NULL DEFAULT NULL COMMENT '用户单笔限额',
  `merchant_id` int(11) NULL DEFAULT NULL COMMENT '商户主键',
  `provider_id` int(11) NULL DEFAULT NULL,
  `create_time` timestamp(0) NULL DEFAULT NULL,
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 46 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '合作信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tbl_customer_support
-- ----------------------------
DROP TABLE IF EXISTS `tbl_customer_support`;
CREATE TABLE `tbl_customer_support`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `customer_manager` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户经理',
  `customer_manager_mobile` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `success_customer_manager` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户成功经理',
  `success_customer_manager_mobile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户成功经理手机号',
  `success_customer_email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户成功经理邮箱',
  `business_support` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商户支持邮箱',
  `merchant_id` int(11) NULL DEFAULT NULL COMMENT '商户id',
  `provider_id` int(11) NULL DEFAULT NULL,
  `create_time` timestamp(0) NULL DEFAULT NULL,
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '客户支持' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tbl_factor_certification
-- ----------------------------
DROP TABLE IF EXISTS `tbl_factor_certification`;
CREATE TABLE `tbl_factor_certification`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `order_serial_num` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '平台订单号',
  `merchant_serial_num` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商户订单号',
  `type` int(1) NULL DEFAULT NULL COMMENT '0-三要素认证',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `id_card_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证号',
  `bank_card_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '银行卡号',
  `status` int(1) NULL DEFAULT NULL COMMENT '0-同意1-拒绝',
  `factor_order_id` int(11) NULL DEFAULT NULL COMMENT '要素认证订单号',
  `merchant_id` int(11) NULL DEFAULT NULL,
  `provider_id` int(11) NULL DEFAULT NULL,
  `create_time` timestamp(0) NULL DEFAULT NULL,
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `factor_order_id_index`(`factor_order_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '要素认证信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tbl_factor_order
-- ----------------------------
DROP TABLE IF EXISTS `tbl_factor_order`;
CREATE TABLE `tbl_factor_order`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `order_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账单编号',
  `order_num` int(11) NULL DEFAULT NULL COMMENT '订单笔数',
  `service_amount` decimal(11, 2) NULL DEFAULT NULL COMMENT '服务费实收金额',
  `offset_amount` decimal(11, 2) NULL DEFAULT 0.00 COMMENT '冲补金额',
  `total_amount` decimal(11, 2) NULL DEFAULT 0.00 COMMENT '订单总额',
  `merchant_id` int(11) NULL DEFAULT NULL,
  `provider_id` int(11) NULL DEFAULT NULL,
  `create_time` timestamp(0) NULL DEFAULT NULL,
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '要素认证订单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tbl_invoice_apply
-- ----------------------------
DROP TABLE IF EXISTS `tbl_invoice_apply`;
CREATE TABLE `tbl_invoice_apply`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `invoice_amount` decimal(20, 2) NULL DEFAULT NULL COMMENT '开票金额',
  `invoice_info_id` int(11) NULL DEFAULT NULL COMMENT '发票信息',
  `invoice_status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发票状态APPLY-申请中POSTED-已寄出REFUSE-已拒绝',
  `merchant_id` int(11) NULL DEFAULT NULL COMMENT '商户id',
  `invoice_type` int(1) NULL DEFAULT NULL COMMENT '0-增值税专用发票1-普通发票',
  `invoice_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发票代码',
  `invoice_num` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发票号码',
  `invoice_time` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '开票日期',
  `invoice_month` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '开票月份',
  `operator` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人',
  `to_void` int(1) NULL DEFAULT NULL COMMENT '作废标识 0-未作废 1-已作废 2-作废中 3-待重开 4-已重开',
  `invoice_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发票图片',
  `bank_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '开户行',
  `invoice_serial_num` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发票申请编号',
  `instruction` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '开票说明',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发票备注',
  `generate_id` int(11) NULL DEFAULT NULL COMMENT '被重开发票ID',
  `address_id` int(11) NULL DEFAULT NULL COMMENT '开票地址',
  `provider_id` int(11) NULL DEFAULT NULL,
  `create_time` timestamp(0) NULL DEFAULT NULL,
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `merchant_id_index`(`merchant_id`) USING BTREE,
  INDEX `invoice_info_id_index`(`invoice_info_id`) USING BTREE,
  INDEX `invoice_serial_num_index`(`invoice_serial_num`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 260 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '开票申请' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tbl_invoice_apply_amount
-- ----------------------------
DROP TABLE IF EXISTS `tbl_invoice_apply_amount`;
CREATE TABLE `tbl_invoice_apply_amount`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `invoice_id` int(11) NULL DEFAULT NULL COMMENT '对应发票ID',
  `invoice_serial_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '应税劳务服务名',
  `invoice_amount` decimal(20, 2) NULL DEFAULT NULL COMMENT '发票金额',
  `tax_rate` decimal(5, 2) NULL DEFAULT NULL COMMENT '税率',
  `tax_amount` decimal(15, 2) NULL DEFAULT NULL COMMENT '发票税额',
  `unit_price` decimal(15, 2) NULL DEFAULT NULL COMMENT '单价',
  `status` int(1) NULL DEFAULT 0 COMMENT '0-未删除1-已删除',
  `provider_id` int(2) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 337 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '发票申请金额表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tbl_invoice_apply_detail
-- ----------------------------
DROP TABLE IF EXISTS `tbl_invoice_apply_detail`;
CREATE TABLE `tbl_invoice_apply_detail`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `invoice_apply_id` int(11) NULL DEFAULT NULL COMMENT '申请记录id',
  `recharge_record` int(11) NULL DEFAULT NULL COMMENT '充值记录id',
  `del_status` int(1) NULL DEFAULT NULL COMMENT '删除状态 0-未删除1-已删除',
  `provider_id` int(11) NULL DEFAULT NULL,
  `create_time` timestamp(0) NULL DEFAULT NULL,
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 246 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '发票申请详情表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tbl_invoice_fail
-- ----------------------------
DROP TABLE IF EXISTS `tbl_invoice_fail`;
CREATE TABLE `tbl_invoice_fail`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `invoice_id` int(11) NULL DEFAULT NULL COMMENT '作废发票ID',
  `status` int(2) NULL DEFAULT NULL COMMENT '发票认证状态 0-已认证 1-未认证',
  `red_invoice_info` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '红冲发票信息表',
  `red_invoice` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '红冲发票信息表文件存储 已认证',
  `seal_explain` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '盖章版退票说明',
  `is_check` int(2) NULL DEFAULT NULL COMMENT '是否处理 0-未处理 1-已处理',
  `del_status` int(1) NULL DEFAULT NULL COMMENT '删除状态 0-未删除1-已删除',
  `provider_id` int(11) NULL DEFAULT NULL,
  `create_time` timestamp(0) NULL DEFAULT NULL,
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 46 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '发票重开信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tbl_invoice_info
-- ----------------------------
DROP TABLE IF EXISTS `tbl_invoice_info`;
CREATE TABLE `tbl_invoice_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `company_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业名称',
  `taxpayer_type` int(1) NULL DEFAULT NULL COMMENT '0-一般纳税人1小规模纳税人',
  `taxpayer_identification_number` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '纳税人识别号',
  `company_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单位注册地址',
  `invoice_mobile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单位手机号',
  `bank_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '开户行及账号',
  `invoice_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发票内容',
  `invoice_default_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '默认发票内容',
  `merchant_id` int(11) NULL DEFAULT NULL COMMENT '商户id',
  `provider_id` int(11) NULL DEFAULT NULL,
  `create_time` timestamp(0) NULL DEFAULT NULL,
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 46 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '发票信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tbl_meal_detail
-- ----------------------------
DROP TABLE IF EXISTS `tbl_meal_detail`;
CREATE TABLE `tbl_meal_detail`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `start_amount` decimal(15, 2) NULL DEFAULT NULL COMMENT '区间起始金额',
  `end_amount` decimal(15, 2) NULL DEFAULT NULL COMMENT '区间结束金额',
  `back_rate` decimal(15, 2) NULL DEFAULT NULL COMMENT '返回服务费费率',
  `meal_info_id` int(11) NULL DEFAULT NULL COMMENT '套餐信息表id',
  `provider_id` int(11) NULL DEFAULT NULL,
  `create_time` timestamp(0) NULL DEFAULT NULL,
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '套餐内容' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_meal_detail
-- ----------------------------
INSERT INTO `tbl_meal_detail` VALUES (1, 1000000.00, 10000000.00, 0.01, 1, 1, '2019-08-20 15:34:33', '2019-08-20 15:34:36');

-- ----------------------------
-- Table structure for tbl_meal_info
-- ----------------------------
DROP TABLE IF EXISTS `tbl_meal_info`;
CREATE TABLE `tbl_meal_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `meal_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '套餐名称',
  `meal_description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '套餐描述',
  `provider_id` int(11) NULL DEFAULT NULL,
  `create_time` timestamp(0) NULL DEFAULT NULL,
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '套餐信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tbl_merchant_account
-- ----------------------------
DROP TABLE IF EXISTS `tbl_merchant_account`;
CREATE TABLE `tbl_merchant_account`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `merchant_id` int(11) NULL DEFAULT NULL COMMENT '商户id',
  `amount` decimal(20, 2) NULL DEFAULT NULL COMMENT '总余额',
  `frozen_amount` decimal(20, 2) NULL DEFAULT NULL COMMENT '冻结金额',
  `usable_amount` decimal(20, 2) NULL DEFAULT NULL COMMENT '可用余额',
  `accumulated_return_amount` decimal(20, 2) NULL DEFAULT NULL COMMENT '累计返点金额',
  `usable_accumulated_return_amount` decimal(20, 2) NULL DEFAULT NULL COMMENT '可用累计返点金额',
  `status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态 ACTIVE-激活状态，正常使用FROZED-冻结（不可提现）CANCEL-注销',
  `provider_id` int(11) NULL DEFAULT NULL,
  `create_time` timestamp(0) NULL DEFAULT NULL,
  `update_time` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 72 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商户账户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tbl_merchant_account_config
-- ----------------------------
DROP TABLE IF EXISTS `tbl_merchant_account_config`;
CREATE TABLE `tbl_merchant_account_config`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `balance_remind_switch` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '余额提醒开关ON-开OFF-关',
  `balance_email_remind_switch` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '余额邮件提醒开关ON-开OFF-关',
  `balance_sms_remind_switch` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '余额短信开关ON-开OFF-关',
  `bank_remind_amount` decimal(15, 2) NOT NULL COMMENT '银行卡余额提醒阈值',
  `alipay_remind_amount` decimal(15, 2) NULL DEFAULT NULL COMMENT '支付宝余额提醒阈值',
  `wechat_remind_amount` decimal(15, 2) NULL DEFAULT NULL COMMENT '微信余额提醒阈值',
  `recharge_remind_switch` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '线下充值到账提醒开关ON-开OFF-关',
  `recharge_email_remind_switch` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '线下充值到账邮件提醒开关ON-开OFF-关',
  `recharge_sms_remind_switch` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '线下充值到账短信提醒开关ON-开OFF-关',
  `comment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `balance_remind_email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预警及提醒类邮件发送邮箱地址',
  `recharge_remind_email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账单类邮件发送范围',
  `lending_authority` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商户放款权限ON-开OFF-关',
  `merchant_id` int(11) NULL DEFAULT NULL,
  `provider_id` int(11) NULL DEFAULT NULL,
  `create_time` timestamp(0) NULL DEFAULT NULL,
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '账户配置' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tbl_merchant_account_detail
-- ----------------------------
DROP TABLE IF EXISTS `tbl_merchant_account_detail`;
CREATE TABLE `tbl_merchant_account_detail`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `payment_amount_before` decimal(15, 2) NULL DEFAULT NULL COMMENT '交易之前总余额',
  `payment_amount_after` decimal(15, 2) NULL DEFAULT NULL COMMENT '交易之后总余额',
  `payment_frozen_amount_before` decimal(15, 2) NULL DEFAULT NULL COMMENT '交易之前冻结金额',
  `payment_frozen_amount_after` decimal(15, 2) NULL DEFAULT NULL COMMENT '交易之后冻结金额',
  `payment_usable_amount_before` decimal(15, 2) NULL DEFAULT NULL COMMENT '交易之前可用余额',
  `payment_usable_amount_after` decimal(15, 2) NULL DEFAULT NULL COMMENT '交易之后可用余额',
  `payment_channel` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '代付渠道BANK-银行ALIPAY-支付宝WECHAT-微信',
  `status` int(2) NULL DEFAULT NULL COMMENT '订单状态 0-失败 1-成功',
  `type` int(2) NULL DEFAULT NULL COMMENT '交易类型 0-代付 1-充值 2-返点 3-转入 4-转出 ',
  `payment_amount` decimal(15, 2) NULL DEFAULT NULL COMMENT '交易金额',
  `order_serial_num` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单表流水号',
  `merchant_id` int(11) NULL DEFAULT NULL COMMENT '商户id',
  `provider_id` int(11) NULL DEFAULT NULL,
  `create_time` timestamp(0) NULL DEFAULT NULL,
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 293 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商户账户余额变动明细' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tbl_merchant_info
-- ----------------------------
DROP TABLE IF EXISTS `tbl_merchant_info`;
CREATE TABLE `tbl_merchant_info`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `merchant_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商户编码',
  `merchant_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商户名称',
  `merchant_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商户经营地址',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '盐',
  `status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户状态，激活ACTIVE锁定LOCKED注销CANCEL',
  `contacts` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人',
  `contacts_mobile` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人手机号',
  `contacts_id_card_front` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证正面',
  `contacts_id_card_back` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证背面',
  `contacts_address` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人地址',
  `invoice_email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接收报税文件邮箱',
  `legal_person_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '法人名字',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮件',
  `business_license_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '营业执照编码',
  `business_license_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '营业执照图片',
  `taxpayer_type` int(1) NULL DEFAULT NULL COMMENT '0-一般纳税人1-小规模纳税人',
  `job` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '岗位',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `belong_company_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '归属商户名称',
  `company_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业名称',
  `contract_img_url` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '合同图片',
  `contract_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '合同名称',
  `contract_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '合同编号',
  `business_places_proof` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '经营场所产权证明',
  `apply_regist_business` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '个体工商户设立登记申请书',
  `manager_operator_letter` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '经营者签署委托代理书',
  `individual_business_notice` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '个体工商户名称预先核准通知书',
  `type` int(1) NULL DEFAULT NULL COMMENT '0-个体工商户1-正式商户',
  `wx_open_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微信授权登录openid',
  `audit_status` int(1) NULL DEFAULT NULL COMMENT '0-提交资料1-平台审核2-工商注册3-注册完成',
  `inviter` int(11) NULL DEFAULT NULL COMMENT '邀请人id',
  `provider_id` int(11) NULL DEFAULT NULL,
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 82 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商户信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tbl_merchant_key
-- ----------------------------
DROP TABLE IF EXISTS `tbl_merchant_key`;
CREATE TABLE `tbl_merchant_key`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `merchant_id` int(11) NULL DEFAULT NULL COMMENT '商户id',
  `app_key` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接口秘钥',
  `des_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '3重加密之后的秘钥',
  `provider_id` int(11) NULL DEFAULT NULL,
  `create_time` timestamp(0) NULL DEFAULT NULL,
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商户秘钥' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tbl_order_sheet
-- ----------------------------
DROP TABLE IF EXISTS `tbl_order_sheet`;
CREATE TABLE `tbl_order_sheet`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `order_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '调单流水号',
  `risk_point` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '风险点',
  `order_note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '调单说明',
  `order_num` int(11) NULL DEFAULT NULL COMMENT '调单数量',
  `trx_order_id` int(11) NULL DEFAULT NULL COMMENT '订单id',
  `audit_result` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审核结论',
  `audit_note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审核说明',
  `file_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '附件名称',
  `feed_back_comment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '反馈意见',
  `feed_back_time` timestamp(0) NULL DEFAULT NULL COMMENT '反馈时间',
  `status` int(1) NULL DEFAULT NULL COMMENT '0-未处理1-已处理2-已拒绝',
  `merchant_id` int(11) NULL DEFAULT NULL,
  `provider_id` int(11) NULL DEFAULT NULL,
  `create_time` timestamp(0) NULL DEFAULT NULL,
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci COMMENT = '调单订单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_order_sheet
-- ----------------------------
INSERT INTO `tbl_order_sheet` VALUES (1, '123', '收款人疑为企业董高监', '请核实收款人信息', 1, 196, '未审核', '收款人疑为企业董高监', '', '', NULL, 0, 32, 1, '2019-08-21 17:56:30', '2019-08-29 09:32:10');

-- ----------------------------
-- Table structure for tbl_payment_merchant_info
-- ----------------------------
DROP TABLE IF EXISTS `tbl_payment_merchant_info`;
CREATE TABLE `tbl_payment_merchant_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `merchant_id` int(11) NULL DEFAULT NULL COMMENT '系统商户id',
  `secret_key` varchar(4000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '私钥',
  `public_key` varchar(4000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公钥',
  `merchant_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '代付通道的商户号',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'CHANPAY-畅捷',
  `tax_source_id` int(11) NULL DEFAULT NULL COMMENT '所属税源地公司id',
  `provider_id` int(11) NULL DEFAULT NULL,
  `create_time` timestamp(0) NULL DEFAULT NULL,
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '代付平台商户信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tbl_recharge_record
-- ----------------------------
DROP TABLE IF EXISTS `tbl_recharge_record`;
CREATE TABLE `tbl_recharge_record`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `merchant_id` int(11) NULL DEFAULT NULL,
  `amount` decimal(15, 2) NULL DEFAULT NULL COMMENT '充值金额',
  `recharge_amount_before` decimal(15, 2) NULL DEFAULT NULL COMMENT '充值前余额',
  `recharge_amount_after` decimal(15, 2) NULL DEFAULT NULL COMMENT '充值后余额',
  `recharge_usable_amount_before` decimal(15, 2) NULL DEFAULT NULL COMMENT '充值前可用余额',
  `recharge_usable_amount_after` decimal(15, 2) NULL DEFAULT NULL COMMENT '充值后可用余额',
  `recharge_channel` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '充值渠道BANK-银行ALIPAY-支付宝WECHAT-微信',
  `recharge_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'ONLINE-线上UNDERLINE-线下',
  `invoice_status` int(1) NULL DEFAULT NULL COMMENT '0-未开发票1-已开发票',
  `order_serial_num` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '充值记录流水号',
  `status` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '充值状态SUCCESS-成功FAIL-失败',
  `provider_id` int(11) NULL DEFAULT NULL,
  `create_time` timestamp(0) NULL DEFAULT NULL,
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 96 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '充值记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tbl_risk_management_config
-- ----------------------------
DROP TABLE IF EXISTS `tbl_risk_management_config`;
CREATE TABLE `tbl_risk_management_config`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `quota_config` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '全网限额ON-开OFF-关',
  `single_quota_config` decimal(11, 2) NULL DEFAULT NULL COMMENT '全网单人月累计金额限制',
  `temporary_quota` decimal(11, 2) NULL DEFAULT NULL COMMENT '临时额度',
  `temporary_quota_begin` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '临时额度开始时间',
  `temporary_quota_end` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '临时额度结束时间',
  `merchant_id` int(11) NULL DEFAULT NULL COMMENT '商户id',
  `single_day_quota` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单商户日限额ON开OFF关',
  `cumulative_quota` decimal(15, 2) NULL DEFAULT NULL COMMENT '单商户累计金额限制',
  `provider_id` int(11) NULL DEFAULT NULL,
  `create_time` timestamp(0) NULL DEFAULT NULL,
  `update_time` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 47 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '风险管理配置' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tbl_settlement_info
-- ----------------------------
DROP TABLE IF EXISTS `tbl_settlement_info`;
CREATE TABLE `tbl_settlement_info`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `account_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '开户名称',
  `account_no` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '对公账户',
  `bank_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '开户银行名称',
  `merchant_id` int(11) NULL DEFAULT NULL COMMENT '商户id',
  `provider_id` int(11) NULL DEFAULT NULL,
  `create_time` timestamp(0) NULL DEFAULT NULL,
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '结算信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tbl_tax_payment_certificate
-- ----------------------------
DROP TABLE IF EXISTS `tbl_tax_payment_certificate`;
CREATE TABLE `tbl_tax_payment_certificate`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `file_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '系统生成文件名称',
  `status` int(1) NULL DEFAULT NULL COMMENT '0-未下载1-已下载2-已删除',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理员上传输入的文件名称',
  `download_time` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '下载时间',
  `merchant_id` int(11) NULL DEFAULT NULL,
  `provider_id` int(11) NULL DEFAULT NULL,
  `create_time` timestamp(0) NULL DEFAULT NULL,
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '完税证明' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tbl_tax_sounrce_company
-- ----------------------------
DROP TABLE IF EXISTS `tbl_tax_sounrce_company`;
CREATE TABLE `tbl_tax_sounrce_company`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `merchant_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '税源地公司商户号',
  `secret_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '私钥',
  `public_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公钥',
  `tax_sounrce_company_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '税源地公司名称',
  `channel` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '渠道CHANPAY-畅捷',
  `provider_id` int(11) NULL DEFAULT NULL,
  `create_time` timestamp(0) NULL DEFAULT NULL,
  `update_time` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '税源地公司信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tbl_transfer_accounts
-- ----------------------------
DROP TABLE IF EXISTS `tbl_transfer_accounts`;
CREATE TABLE `tbl_transfer_accounts`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `applicant` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '转账申请人',
  `trx_extenl_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请单号',
  `payee_account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收款账户',
  `transfer_merchants` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '转出商户',
  `payee_merchants` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收款商户',
  `transfer_account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '转出账户',
  `amount` decimal(15, 2) NULL DEFAULT NULL COMMENT '转账金额',
  `status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'SUCCESS-成功FAIL-失败',
  `type` int(1) NULL DEFAULT NULL COMMENT '0-转入1-转出',
  `provider_id` int(11) NULL DEFAULT NULL,
  `merchant_id` int(11) NULL DEFAULT NULL,
  `create_time` timestamp(0) NULL DEFAULT NULL,
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '转账记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tbl_trx_order
-- ----------------------------
DROP TABLE IF EXISTS `tbl_trx_order`;
CREATE TABLE `tbl_trx_order`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `merchant_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商户名称',
  `amount` decimal(11, 2) NULL DEFAULT NULL COMMENT '请求打款金额',
  `tax_rate` decimal(11, 2) NULL DEFAULT NULL COMMENT '税率',
  `charge` decimal(11, 2) NULL DEFAULT NULL COMMENT '手续费',
  `actual_amount` decimal(11, 2) NULL DEFAULT NULL COMMENT '实发金额',
  `order_serial_num` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单流水号',
  `merchant_serial_num` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商户流水号',
  `batch_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '批次号',
  `payment_voucher` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '打款凭证',
  `payee_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收款人姓名',
  `payee_mobile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收款人手机号',
  `payee_bank_card` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '银行卡号',
  `bank_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '银行名称',
  `payee_id_card_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收款人身份证号',
  `order_status` int(1) NULL DEFAULT NULL COMMENT '0-未打款1-已打款2-打款挂起3-调单状态',
  `batch_payment_record_id` int(11) NULL DEFAULT NULL COMMENT '批量打款记录id',
  `payment_channel` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'BANK-银行ALIPAY-支付宝WECHAT-微信打款渠道',
  `merchant_id` int(11) NULL DEFAULT NULL COMMENT '商户id',
  `comment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `subject_conscription` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '代征主体默认为云账户CLOUDACCOUNT',
  `provider_id` int(11) NULL DEFAULT NULL,
  `create_time` timestamp(0) NULL DEFAULT NULL,
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 220 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '交易订单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tbl_user_list
-- ----------------------------
DROP TABLE IF EXISTS `tbl_user_list`;
CREATE TABLE `tbl_user_list`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名字',
  `id_card_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证号码',
  `bank_card_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '银行卡号',
  `mobile_no` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `country` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '国家',
  `status` int(1) NULL DEFAULT NULL COMMENT '免验状态0-未加入1-已加入2-已拒绝',
  `type` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名单类型WHITE-白名单BLACK-黑名单',
  `certificate_type_id` int(11) NULL DEFAULT NULL COMMENT '证件类型',
  `id_card_front` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '证件正面',
  `id_card_back` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '证件背面',
  `comment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `merchant_id` int(11) NULL DEFAULT NULL COMMENT '商户id',
  `provider_id` int(11) NULL DEFAULT NULL,
  `create_time` timestamp(0) NULL DEFAULT NULL,
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `name_index`(`name`) USING BTREE,
  INDEX `type_index`(`type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户信息' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
