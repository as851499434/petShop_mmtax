-- 2020-7-22
ALTER TABLE `tbl_transfer_order`
MODIFY COLUMN `status` int(1) NULL DEFAULT NULL COMMENT '-1-初始化0-进行中1-已转账2-转账失败3-回退中4-回退成功5-回退失败' AFTER `customer_name`;

-- 2020-7-29
CREATE TABLE `tbl_esign_info` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `tax_source_id` int(11) NOT NULL COMMENT '税源地id',
  `app_id` varchar(30) DEFAULT NULL COMMENT '关联易签宝appId',
  `app_secret` varchar(30) DEFAULT NULL COMMENT '关联易签宝appSecret',
  `provider_id` int(11) DEFAULT NULL,
  `reserved_field_one` varchar(30) DEFAULT NULL,
  `reserved_field_two` varchar(30) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='易签宝税源地关联表';

CREATE TABLE `tbl_customer_esign_info` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `third_party_user_id` varchar(30) NOT NULL COMMENT '员工编号',
  `name` varchar(20) NOT NULL COMMENT '真名',
  `id_type` varchar(20) NOT NULL COMMENT '证件类型,取数据字典',
  `id_number` varchar(50) NOT NULL COMMENT '证件号码',
  `mobile` varchar(20) DEFAULT NULL COMMENT '手机号',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `tax_source_id` int(11) NOT NULL COMMENT '所属税源地id',
  `status` int(4) NOT NULL DEFAULT '0' COMMENT '0未开户 1已开户 2已注销',
  `account_id` varchar(50) DEFAULT NULL COMMENT '个人账号id(e签宝)',
  `color` varchar(10) DEFAULT NULL COMMENT '印章颜色 RED-红色 BLUE-蓝色 BLACK-黑色',
  `type` varchar(10) DEFAULT NULL COMMENT '印章模板类型 见数据字典',
  `seal_status` int(4) NOT NULL DEFAULT '0' COMMENT '印章状态 0未创建 1已创建 2已删除',
  `file_key` varchar(30) DEFAULT NULL COMMENT '印章fileKey',
  `seal_id` varchar(30) DEFAULT NULL COMMENT '印章id',
  `merchant_id` int(11) NOT NULL COMMENT '上传信息的商户id',
  `provider_id` int(11) DEFAULT NULL,
  `reserved_field_one` varchar(30) DEFAULT NULL,
  `reserved_field_two` varchar(30) DEFAULT NULL,
  `reserved_field_three` varchar(30) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `third_party_user_id_index` (`third_party_user_id`) USING BTREE COMMENT '唯一员工编号索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='员工签约信息表';

CREATE TABLE `tbl_contract_info` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `file_name` varchar(30) NOT NULL COMMENT '文件名称',
  `file_id` varchar(50) DEFAULT NULL COMMENT '文件id(e签宝)',
  `tax_source_id` int(11) NOT NULL COMMENT '所属税源地id',
  `provider_id` int(11) DEFAULT NULL,
  `reserved_field_one` varchar(300) DEFAULT NULL COMMENT '文件下载地址',
  `reserved_field_two` varchar(50) DEFAULT NULL,
  `reserved_field_three` varchar(30) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='签约文件表';

CREATE TABLE `tbl_esign_flow` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `cus_esign_id` int(11) NOT NULL COMMENT '签署员工表id',
  `auto_archive` int(4) NOT NULL DEFAULT '0' COMMENT '是否自动归档 0否 1是',
  `business_scene` varchar(50) DEFAULT NULL COMMENT '文件主题',
  `notice_developer_url` varchar(100) DEFAULT NULL COMMENT '回调通知地址',
  `redirect_url` varchar(100) DEFAULT NULL COMMENT '签署完成重定向地址,默认签署完成停在当前页面',
  `notice_type` int(4) NOT NULL DEFAULT '1' COMMENT '通知方式 0不需要 1短信 2邮件',
  `contract_validity` bigint(64) DEFAULT NULL COMMENT '文件有效截止日期,毫秒，默认不失效；若时间到了设置的时间,则触发流程文件过期通知',
  `contract_remind` int(32) DEFAULT NULL COMMENT '文件到期前，提前多少小时提醒续签,小时(时间区间：1小时——15天),默认不提醒',
  `sign_validity` int(64) DEFAULT NULL COMMENT '签署有效截止日期,毫秒,默认不失效',
  `flow_id` varchar(50) DEFAULT NULL COMMENT '流程id(e签宝)',
  `flow_start_time` varchar(20) DEFAULT NULL COMMENT '流程开始时间',
  `flow_end_time` varchar(20) DEFAULT NULL COMMENT '流程结束时间',
  `status` int(4) NOT NULL DEFAULT '0' COMMENT '流程状态 0初始化 1开启中 2-已完成 3-已撤销 5-已过期 7-已拒签',
  `flow_desc` varchar(50) DEFAULT NULL COMMENT '流程说明',
  `tax_source_id` int(11) NOT NULL COMMENT '所属税源地id',
  `provider_id` int(11) DEFAULT NULL,
  `reserved_field_one` varchar(30) DEFAULT NULL,
  `reserved_field_two` varchar(30) DEFAULT NULL,
  `reserved_field_three` varchar(30) DEFAULT NULL,
  `reserved_field_four` varchar(30) DEFAULT NULL,
  `reserved_field_five` varchar(30) DEFAULT NULL,
  `del_status` int(4) NOT NULL DEFAULT '0' COMMENT '记录有效性 0有效 1无效',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='签约流程表';

CREATE TABLE `tbl_esign_flow_doc` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `esign_flow_id` int(11) NOT NULL COMMENT '签约流程表id',
  `flow_id` varchar(50) DEFAULT NULL COMMENT '流程id(e签宝)',
  `contract_info_id` int(11) NOT NULL COMMENT '签约文件id',
  `file_id` varchar(50) DEFAULT NULL COMMENT '文件id(e签宝)',
  `encryption` int(4) NOT NULL DEFAULT '0' COMMENT '是否加密 0-不加密 1-加密',
  `file_name` varchar(50) DEFAULT NULL COMMENT '文件名称(带扩展名)',
  `file_password` varchar(50) DEFAULT NULL COMMENT '文档密码',
  `provider_id` int(11) DEFAULT NULL,
  `reserved_field_one` varchar(30) DEFAULT NULL,
  `reserved_field_two` varchar(30) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='签约流程文档关联表';

CREATE TABLE `tbl_esign_fields` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `esign_flow_id` int(11) NOT NULL COMMENT '签约流程表id',
  `flow_id` varchar(50) DEFAULT NULL COMMENT '流程id(e签宝)',
  `contract_info_id` int(11) NOT NULL COMMENT '签约文件id',
  `file_id` varchar(50) DEFAULT NULL COMMENT '文件id(e签宝)',
  `cus_esign_id` int(11) NOT NULL COMMENT '签署员工表id',
  `authorized_account_id` varchar(50) DEFAULT NULL COMMENT '签署人accountId',
  `order_num` int(4) NOT NULL DEFAULT '1' COMMENT '签署顺序,顺序越小越先处理',
  `pos_page` int(4) DEFAULT NULL COMMENT '页码信息',
  `pos_x` float(20,4) DEFAULT NULL COMMENT 'x坐标',
  `pos_y` float(20,4) DEFAULT NULL COMMENT 'y坐标',
  `width` float(20,4) DEFAULT NULL COMMENT '签署区宽',
  `third_order_no` varchar(30) DEFAULT NULL COMMENT '第三方业务流水号id',
  `provider_id` int(11) DEFAULT NULL,
  `reserved_field_one` varchar(50) DEFAULT NULL,
  `reserved_field_two` varchar(30) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='签约流程签署区记录表';

CREATE TABLE `tbl_customer_protocol` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `batch_no` varchar(20) DEFAULT NULL COMMENT '所属批次号',
  `cus_esign_id` int(11) NOT NULL COMMENT '签约员工id',
  `third_party_user_id` varchar(30) NOT NULL COMMENT '员工编号',
  `name` varchar(20) NOT NULL COMMENT '姓名',
  `con_info_id` int(11) DEFAULT NULL COMMENT '签约文档id',
  `file_name` varchar(30) DEFAULT NULL COMMENT '签约文档名称',
  `send_sign_url_time` timestamp NULL DEFAULT NULL COMMENT '上次发送签约链接时间',
  `send_sign_url_status` int(4) NOT NULL DEFAULT '0' COMMENT '上次发送签约链接状态 0未发送 1已发送',
  `sign_time` timestamp NULL DEFAULT NULL COMMENT '签约时间',
  `sign_status` int(4) NOT NULL DEFAULT '0' COMMENT '签约状态 0初始化 2:签署完成 3:失败 4:拒签',
  `comment` varchar(50) DEFAULT NULL COMMENT '说明',
  `espire_time` timestamp NULL DEFAULT NULL COMMENT '签约到期时间',
  `expire_status` int(2) NOT NULL DEFAULT '0' COMMENT '过期状态0-未过期 1-过期',
  `tax_source_id` int(11) NOT NULL COMMENT '所属税源地id',
  `account_id` varchar(50) DEFAULT NULL COMMENT '个人账号id',
  `provider_id` int(11) DEFAULT NULL,
  `reserved_field_one` varchar(30) DEFAULT NULL,
  `reserved_field_two` varchar(30) DEFAULT NULL,
  `reserved_field_three` varchar(30) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='员工协议表';

CREATE TABLE `tbl_batch_sign_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `trx_external_no` varchar(50) DEFAULT NULL COMMENT '平台订单号',
  `batch_no` varchar(50) DEFAULT NULL COMMENT '批次号',
  `sign_count` int(11) DEFAULT NULL COMMENT '总记录',
  `actual_count` int(11) DEFAULT NULL COMMENT '实际通过记录',
  `creater` varchar(50) DEFAULT NULL COMMENT '创建人',
  `operator` varchar(50) DEFAULT NULL COMMENT '操作人',
  `status` int(1) DEFAULT NULL COMMENT '0-未处理1-已处理',
  `merchant_id` int(11) DEFAULT NULL COMMENT '商户id',
  `provider_id` int(11) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='批量签约记录';

CREATE TABLE `tbl_esign_template` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `template_name` varchar(30) NOT NULL COMMENT '模板文件名称',
  `template_id` varchar(50) DEFAULT NULL COMMENT '模板id(e签宝)',
  `tax_source_id` int(11) NOT NULL COMMENT '所属税源地id',
  `provider_id` int(11) DEFAULT NULL,
  `reserved_field_one` varchar(30) DEFAULT NULL,
  `reserved_field_two` varchar(30) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='签约模板文件表';

CREATE TABLE `tbl_template_component` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `component_name` varchar(30) NOT NULL COMMENT '控件名称',
  `component_id` varchar(50) DEFAULT NULL COMMENT '控件id(e签宝)',
  `esign_template_id` int(11) NOT NULL COMMENT '所属模板表id',
  `template_id` varchar(50) NOT NULL COMMENT '模板id(e签宝)',
  `provider_id` int(11) DEFAULT NULL,
  `reserved_field_one` varchar(30) DEFAULT NULL,
  `reserved_field_two` varchar(30) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='签约模板控件表';

CREATE TABLE `tbl_esign_tax_source` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `third_party_user_id` varchar(30) NOT NULL COMMENT '机构编号',
  `name` varchar(20) NOT NULL COMMENT '机构名',
  `id_type` varchar(20) NOT NULL COMMENT '证件类型,取数据字典',
  `id_number` varchar(50) NOT NULL COMMENT '证件号码',
  `cus_esign_id` int(11) NOT NULL COMMENT '个人账号表id',
  `account_id` varchar(50) DEFAULT NULL COMMENT '对应个人账号id(e签宝)',
  `tax_source_id` int(11) NOT NULL COMMENT '所属税源地id',
  `status` int(4) NOT NULL DEFAULT '0' COMMENT '0未开户 1已开户 2已注销',
  `org_id` varchar(50) DEFAULT NULL COMMENT '机构账号id(e签宝)',
  `color` varchar(10) DEFAULT NULL COMMENT '印章颜色 RED-红色 BLUE-蓝色 BLACK-黑色',
  `type` varchar(10) DEFAULT NULL COMMENT '印章模板类型 见数据字典',
  `seal_status` int(4) NOT NULL DEFAULT '0' COMMENT '印章状态 0未创建 1已创建 2已删除',
  `file_key` varchar(30) DEFAULT NULL COMMENT '印章fileKey',
  `seal_id` varchar(30) DEFAULT NULL COMMENT '印章id',
  `provider_id` int(11) DEFAULT NULL,
  `reserved_field_one` varchar(30) DEFAULT NULL COMMENT '备注',
  `reserved_field_two` varchar(30) DEFAULT NULL,
  `reserved_field_three` varchar(30) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `third_party_user_id_index` (`third_party_user_id`) USING BTREE COMMENT '唯一机构编号索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='机构信息表(e签宝)';

ALTER TABLE `tbl_contract_info`
MODIFY COLUMN `reserved_field_one` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件下载地址' AFTER `provider_id`;

ALTER TABLE `tbl_customer_protocol`
MODIFY COLUMN `con_info_id` int(11) NULL COMMENT '签约文档id' AFTER `name`;

ALTER TABLE `mmtax`.`tbl_contract_info`
MODIFY COLUMN `reserved_field_two` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '对应模板表id' AFTER `reserved_field_one`;

-- 2020-8-11
ALTER TABLE `tbl_customer_protocol`
ADD COLUMN `esign_flow_id` int(11) DEFAULT '0' COMMENT '对应流程表id' AFTER `expire_status`;

update tbl_customer_esign_info set reserved_field_two = '0';

-- 合作信息表更新 start
ALTER TABLE tbl_cooperation ADD `silent_sign_switch` INT (1) NOT NULL DEFAULT '0' COMMENT '用户静默签约开关0-关（非静默），1-开（静默）' AFTER sign_switch;
-- 合作信息表更新 end

-- 2020-8-12
ALTER TABLE `tbl_esign_tax_source`
MODIFY COLUMN `reserved_field_two` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否静默签署 0否 1是' AFTER `reserved_field_one`;

ALTER TABLE `tbl_esign_tax_source`
MODIFY COLUMN `tax_source_id` int(11) NOT NULL COMMENT '所属税源地id 0本公司主体 1无效的记录' AFTER `account_id`;

ALTER TABLE `tbl_notify_send_again`
MODIFY COLUMN `notify_type` int(4) NOT NULL COMMENT '通知类型 1代付 2充值 3签约' AFTER `merchant_id`;

-- 2020-8-13

ALTER TABLE `tbl_customer_protocol`
MODIFY COLUMN `reserved_field_one` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'api签约流水号' AFTER `provider_id`;

ALTER TABLE `tbl_customer_protocol`
ADD COLUMN `merchant_id` int(11) NOT NULL COMMENT '对应商户id' AFTER `esign_flow_id`;

update tbl_customer_protocol t1 set merchant_id = (select merchant_id from tbl_customer_esign_info t2 where t1.cus_esign_id = t2.id)
where (select merchant_id from tbl_customer_esign_info t2 where t1.cus_esign_id = t2.id) is not null;

-- 2020年8月16日 start
-- 添加菜单
INSERT INTO `mmtax`.`sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES ('2144', '企业管理', '0', '1', '#', 'menuItem', 'M', '0', '', 'fa fa-institution', 'admin', '2020-08-14 16:10:03', 'admin', '2020-08-14 16:20:33', '', '1');
INSERT INTO `mmtax`.`sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES ('2145', '企业信息', '2144', '1', '/manager/companyInfo', 'menuItem', 'C', '0', 'manager:companyInfo:view', '#', 'admin', '2020-08-14 16:12:51', '18258170909', '2020-08-16 16:41:01', '', '1');
INSERT INTO `mmtax`.`sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES ('2146', '跳转合同数据', '2145', '1', '#', 'menuItem', 'F', '0', 'manager:companyInfo:contractInfo', '#', 'admin', '2020-08-14 16:14:17', 'admin', '2020-08-14 16:19:52', '', '1');
INSERT INTO `mmtax`.`sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES ('2147', '跳转付款数据', '2145', '1', '#', 'menuItem', 'F', '0', 'manager:companyInfo:payInfo', '#', 'admin', '2020-08-14 16:15:12', 'admin', '2020-08-14 16:20:03', '', '1');
INSERT INTO `mmtax`.`sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES ('2148', '跳转发放数据', '2145', '1', '#', 'menuItem', 'F', '0', 'manager:companyInfo:grantInfo', '#', 'admin', '2020-08-14 16:15:42', 'admin', '2020-08-14 16:20:12', '', '1');
INSERT INTO `mmtax`.`sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES ('2149', '查询合同数据', '2145', '2', '#', 'menuItem', 'F', '0', 'manager:companyInfo:selectContractInfo', '#', 'admin', '2020-08-14 16:16:54', '', NULL, '', '1');
INSERT INTO `mmtax`.`sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES ('2150', '查询付款数据', '2145', '2', '#', 'menuItem', 'F', '0', 'manager:companyInfo:selectPayInfo', '#', 'admin', '2020-08-14 16:17:16', '', NULL, '', '1');
INSERT INTO `mmtax`.`sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES ('2151', '查询发放数据', '2145', '2', '#', 'menuItem', 'F', '0', 'manager:companyInfo:selectGrantInfo', '#', 'admin', '2020-08-14 16:17:47', '', NULL, '', '1');
INSERT INTO `mmtax`.`sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES ('2152', '导出合同数据', '2145', '3', '#', 'menuItem', 'F', '0', 'manager:companyInfo:contractExport', '#', 'admin', '2020-08-14 16:22:24', '', NULL, '', '1');
INSERT INTO `mmtax`.`sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES ('2153', '导出付款数据', '2145', '3', '#', 'menuItem', 'F', '0', 'manager:companyInfo:payExport', '#', 'admin', '2020-08-14 16:22:56', '', NULL, '', '1');
INSERT INTO `mmtax`.`sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES ('2154', '导出发放数据', '2145', '3', '#', 'menuItem', 'F', '0', 'manager:companyInfo:grantExport', '#', 'admin', '2020-08-14 16:23:27', '', NULL, '', '1');
INSERT INTO `mmtax`.`sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES ('2155', '企业信息列表', '2145', '1', '#', 'menuItem', 'F', '0', 'manager:companyInfo:selectAllCompanyInfo', '#', 'admin', '2020-08-16 16:42:42', '', NULL, '', '1');

-- 添加角色
INSERT INTO `mmtax`.`sys_role` (`role_id`, `role_name`, `role_key`, `role_sort`, `data_scope`, `status`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES ('9', '企业数据管理', 'enterpriseDataManagement', '9', '1', '0', '0', 'admin', '2020-08-16 16:22:26', 'admin', '2020-08-16 16:44:13', '查询企业数据', '1');

-- 2020年8月16日 end

-- 2020-08-17 start
ALTER TABLE `tbl_esign_fields`
ADD COLUMN `signer_account_id` varchar(50) NULL COMMENT '签署操作人个人账号标识(手动签署)' AFTER `authorized_account_id`,
MODIFY COLUMN `reserved_field_one` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL AFTER `provider_id`;

update tbl_esign_fields set signer_account_id = reserved_field_one;

ALTER TABLE `tbl_customer_esign_info`
ADD COLUMN `auto_sign_status` int(4) UNSIGNED NULL DEFAULT 0 COMMENT '静默签署状态 0-否 1-是' AFTER `status`,
ADD COLUMN `comment` varchar(50) NULL COMMENT '备注' AFTER `merchant_id`,
MODIFY COLUMN `reserved_field_one` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL AFTER `provider_id`,
MODIFY COLUMN `reserved_field_two` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL AFTER `reserved_field_one`;

update tbl_customer_esign_info set auto_sign_status = IF(reserved_field_two = '0',0,1);
update tbl_customer_esign_info set `comment` = reserved_field_one;
update tbl_customer_esign_info set reserved_field_one = NULL;
update tbl_customer_esign_info set reserved_field_two = NULL;

ALTER TABLE `tbl_esign_tax_source`
ADD COLUMN `auto_sign_status` int(4) NULL DEFAULT 0 COMMENT '是否静默签署 0否 1是' AFTER `status`,
ADD COLUMN `comment` varchar(50) NULL COMMENT '备注' AFTER `seal_id`,
MODIFY COLUMN `reserved_field_one` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL AFTER `provider_id`,
MODIFY COLUMN `reserved_field_two` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL AFTER `reserved_field_one`;

update tbl_esign_tax_source set auto_sign_status = IF(reserved_field_two = '0',0,1);
update tbl_esign_tax_source set `comment` = reserved_field_one;
update tbl_esign_tax_source set reserved_field_one = NULL;
update tbl_esign_tax_source set reserved_field_two = NULL;

ALTER TABLE `tbl_customer_protocol`
ADD COLUMN `merchant_serial_num` varchar(30) NULL COMMENT 'api商户签约流水号' AFTER `account_id`,
ADD COLUMN `sign_serial_num` varchar(30) NULL COMMENT '签约流水号' AFTER `merchant_serial_num`,
MODIFY COLUMN `reserved_field_one` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL AFTER `provider_id`;

update tbl_customer_protocol set merchant_serial_num = reserved_field_one;
-- 2020-08-17 end

-- 2020-08-19 start
CREATE TABLE `tbl_make_up_charge` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `merchant_name` varchar(50) DEFAULT NULL COMMENT '商户名称',
  `customer_name` varchar(50) DEFAULT NULL COMMENT '员工名称',
  `amount` decimal(11,2) DEFAULT NULL COMMENT '补交金额',
  `make_up_serial_num` varchar(100) DEFAULT NULL COMMENT '补交流水号',
  `batch_no` varchar(50) DEFAULT NULL COMMENT '批次号',
  `status` int(1) DEFAULT NULL COMMENT '-1-初始化 0-进行中 1-已补交 2-补交失败 9-失效',
  `batch_record_id` int(11) DEFAULT NULL COMMENT '批量打款记录id',
  `merchant_id` int(11) DEFAULT NULL COMMENT '商户id',
  `customer_id` int(11) DEFAULT NULL COMMENT '员工id',
  `comment` varchar(255) DEFAULT NULL COMMENT '备注',
  `async_status` int(1) DEFAULT '0' COMMENT '后续异步动作是否处理 0否 1是',
  `provider_id` int(11) DEFAULT NULL,
  `reserved_field_one` varchar(30) DEFAULT NULL COMMENT '预留字段1',
  `reserved_field_two` varchar(30) DEFAULT NULL COMMENT '预留字段2',
  `reserved_field_three` varchar(30) DEFAULT NULL COMMENT '预留字段3',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `make_up_serial_num_index` (`make_up_serial_num`) USING BTREE COMMENT '唯一订单号索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='补交服务费总记录';

CREATE TABLE `tbl_make_up_charge_detail` (
  `id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `make_up_charge_id` int(11) DEFAULT NULL COMMENT '补交服务费总记录表id',
  `order_serial_num` varchar(100) DEFAULT NULL COMMENT '订单流水号',
  `order_status` int(1) DEFAULT NULL COMMENT '补交服务费时订单的状态',
  `tax_rate` decimal(11,2) DEFAULT NULL COMMENT '原税率',
  `charge` decimal(11,2) DEFAULT NULL COMMENT '原手续费',
  `tax_rate_now` decimal(11,2) DEFAULT NULL COMMENT '现税率',
  `charge_now` decimal(11,2) DEFAULT NULL COMMENT '现手续费',
  `charge_make_up` decimal(11,2) DEFAULT NULL COMMENT '补交手续费',
  `provider_id` int(11) DEFAULT NULL,
  `reserved_field_one` varchar(30) DEFAULT NULL COMMENT '预留字段1',
  `reserved_field_two` varchar(30) DEFAULT NULL COMMENT '预留字段2',
  `reserved_field_three` varchar(30) DEFAULT NULL COMMENT '预留字段3',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='补交服务费详细记录表';

CREATE TABLE `tbl_return_charge_order` (
  `id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `return_serial_num` varchar(100) DEFAULT NULL COMMENT '退还流水号',
  `order_serial_num` varchar(100) DEFAULT NULL COMMENT '订单流水号',
  `status` int(1) DEFAULT NULL COMMENT '退还状态',
  `return_charge` decimal(11,2) DEFAULT NULL COMMENT '退还手续费金额',
  `merchant_id` int(11) DEFAULT NULL COMMENT '商户id',
  `customer_id` int(11) DEFAULT NULL COMMENT '员工id',
  `async_status` int(1) DEFAULT '0' COMMENT '后续异步动作是否处理 0否 1是',
  `provider_id` int(11) DEFAULT NULL,
  `reserved_field_one` varchar(30) DEFAULT NULL COMMENT '预留字段1',
  `reserved_field_two` varchar(30) DEFAULT NULL COMMENT '预留字段2',
  `reserved_field_three` varchar(30) DEFAULT NULL COMMENT '预留字段3',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='退还补交服务费记录表';

ALTER TABLE `tbl_trx_order`
ADD COLUMN `trx_rate_big` decimal(11, 2) NULL COMMENT '大额费率' AFTER `charge`,
ADD COLUMN `charge_big` decimal(11, 2) NULL COMMENT '大额手续费' AFTER `trx_rate_big`,
ADD COLUMN `use_big_rate` int(1) NULL DEFAULT 0 COMMENT '使用大额费率 0否 1是' AFTER `async_status`;

ALTER TABLE `tbl_cooperation`
ADD COLUMN `big_rate_switch` int(1) NOT NULL DEFAULT 0 COMMENT '大额费率开关 0关 1开' AFTER `silent_sign_switch`,
ADD COLUMN `single_service_big_rate` decimal(11, 5) NULL COMMENT '商户单笔大额服务费费率' AFTER `big_rate_switch`;

ALTER TABLE `tbl_customer_info`
ADD COLUMN `month_use_rate` int(1) NOT NULL DEFAULT 0 COMMENT '当月使用费率 0普通 1大额' AFTER `effective`;

ALTER TABLE `tbl_make_up_charge`
ADD COLUMN `month_pay_amount` decimal(11, 5) NULL COMMENT '当月打款金额' AFTER `customer_name`;

ALTER TABLE `tbl_make_up_charge`
ADD COLUMN `order_serial_num` varchar(100) NULL COMMENT '对应记录创建时的关联订单' AFTER `customer_name`;

ALTER TABLE `tbl_online_account_config`
ADD COLUMN `register_email` varchar(50) NULL COMMENT '网商注册邮箱号' AFTER `tax_sounrce_company_name`;

update tbl_online_account_config set register_email = '2112147887@qq.com' where tax_sounrce_id = 3;
update tbl_online_account_config set register_email = '2534342620@qq.com' where tax_sounrce_id = 4;
update tbl_online_account_config set register_email = '2534342620@qq.com' where tax_sounrce_id = 5;

ALTER TABLE `tbl_return_charge_order`
ADD COLUMN `comment` varchar(100) NULL COMMENT '备注' AFTER `async_status`;

INSERT INTO `sys_config` (`config_id`, `config_name`, `config_key`, `config_value`, `config_type`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES ('11', '单人每月大额限额', 'single_month_amount_large', '400000', 'Y', 'admin', '2020-08-24 15:23:07', '', NULL, '单个员工单个税源地单月发佣大额限额金额', '1');

ALTER TABLE `tbl_trx_order`
MODIFY COLUMN `tax_rate` decimal(11, 5) NULL DEFAULT NULL COMMENT '税率' AFTER `amount`,
MODIFY COLUMN `trx_rate_big` decimal(11, 5) NULL DEFAULT NULL COMMENT '大额费率' AFTER `charge`;

ALTER TABLE `tbl_make_up_charge_detail`
MODIFY COLUMN `tax_rate` decimal(11, 5) NULL DEFAULT NULL COMMENT '原税率' AFTER `order_status`,
MODIFY COLUMN `tax_rate_now` decimal(11, 5) NULL DEFAULT NULL COMMENT '现税率' AFTER `charge`;

update tbl_trx_order a set a.tax_rate = (select b.single_service_rate from tbl_cooperation b where a.merchant_id = b.merchant_id);

INSERT INTO `sys_job`(`job_id`, `job_name`, `job_group`, `invoke_target`, `cron_expression`, `misfire_policy`, `concurrent`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (null, 'HandleMakeUpChargeJob', 'test', 'HandleMakeUpChargeJob.handleMakeUpCharge()', '0 0/5 * * * ?', '1', '0', '0', 'admin', '2020-08-26 15:42:51', 'admin', '2020-08-28 15:11:30', '', NULL);
INSERT INTO `sys_job`(`job_id`, `job_name`, `job_group`, `invoke_target`, `cron_expression`, `misfire_policy`, `concurrent`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (null, 'HandleReturnChargeJob', 'test', 'HandleReturnChargeJob.handleReturnCharge()', '0 0/10 * * * ?', '1', '0', '0', 'admin', '2020-08-26 15:44:50', '', '2020-08-27 14:04:23', '处理退还服务费记录成功或失败的后续处理', NULL);
INSERT INTO `sys_job`(`job_id`, `job_name`, `job_group`, `invoke_target`, `cron_expression`, `misfire_policy`, `concurrent`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (null, 'ReturnSurplusChargeJob', 'test', 'ReturnSurplusChargeJob.surplusCharge()', '0 0 2 * * ?', '1', '0', '1', 'admin', '2020-08-26 15:46:11', 'admin', '2020-08-26 15:46:48', '回退多补交的服务费', NULL);

-- 大额需求添加菜单 start
INSERT INTO `mmtax`.`sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES ('2161', '导出服务费补交记录', '2159', '2', '#', 'menuItem', 'F', '0', 'manager:companyInfo:makeUpListTrxOrderExport', '#', 'admin', '2020-08-27 11:45:35', '', NULL, '', '1');
INSERT INTO `mmtax`.`sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES ('2160', '补交记列表', '2159', '1', '#', 'menuItem', 'F', '0', 'manager:trxOrder:makeUpListTrxOrder', '#', 'admin', '2020-08-27 11:45:07', '', NULL, '', '1');
INSERT INTO `mmtax`.`sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES ('2159', '服务费补交记录', '2000', '6', 'manager/trxOrder/makeUpListTrxOrderView', 'menuItem', 'C', '0', 'manager:trxOrder:makeUpListTrxOrderView', '#', 'admin', '2020-08-27 11:44:39', '', NULL, '', '1');
INSERT INTO `mmtax`.`sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES ('2164', '导出服务费退还记录', '2162', '2', '#', 'menuItem', 'F', '0', 'manager:companyInfo:returnListTrxOrderExport', '#', 'admin', '2020-08-27 11:43:53', '', NULL, '', '1');
INSERT INTO `mmtax`.`sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES ('2163', '退还记录列表', '2162', '1', '#', 'menuItem', 'F', '0', 'manager:trxOrder:returnListTrxOrder', '#', 'admin', '2020-08-27 11:43:19', '', NULL, '', '1');
INSERT INTO `mmtax`.`sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES ('2162', '服务费退还记录', '2000', '6', 'manager/trxOrder/returnListTrxOrderView', 'menuItem', 'C', '0', 'manager:trxOrder:returnListTrxOrderView', '#', 'admin', '2020-08-27 11:42:41', '', NULL, '', '1');
-- 大额需求添加菜单 end
-- 2020-08-19 end

-- 内扣外扣需求start
ALTER TABLE `tbl_cooperation`
ADD COLUMN `in_or_out_deduction` int(1) NOT NULL DEFAULT 1 COMMENT '0-内扣 1-外扣' AFTER `single_service_rate`;

ALTER TABLE `tbl_trx_order`
ADD COLUMN `in_out_deduction` int(1) NOT NULL DEFAULT 1 COMMENT '0-普通费率内扣 1-普通费率外扣' AFTER `charge`,
ADD COLUMN `big_in_out_deduction` int(1) NOT NULL DEFAULT 1 COMMENT '0-大额费率内扣 1-大额费率外扣' AFTER `charge_big`;

ALTER TABLE `tbl_make_up_charge`
ADD COLUMN `in_out_deduction` int(1) NOT NULL DEFAULT 1 COMMENT '0-内扣 1-外扣' AFTER `amount`;
-- 内扣外扣需求end
-- 2020-09-08 start
CREATE TABLE `tbl_batch_payment_detail` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `batch_payment_record_id` int(11) DEFAULT NULL COMMENT '批量打款记录id',
  `batch_no` varchar(50) DEFAULT NULL COMMENT '批次号',
  `order_serial_num` varchar(100) DEFAULT NULL COMMENT '订单流水号',
  `amount` decimal(11,2) DEFAULT NULL COMMENT '打款时请求打款金额',
  `tax_rate` decimal(11,5) DEFAULT NULL COMMENT '打款时税率',
  `charge` decimal(11,2) DEFAULT NULL COMMENT '打款时手续费',
  `actual_amount` decimal(11,2) DEFAULT NULL COMMENT '打款时实发金额',
  `provider_id` int(11) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `order_serial_num_index` (`order_serial_num`) USING BTREE COMMENT '唯一订单号索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='批量打款详细订单记录';

insert into tbl_batch_payment_detail (batch_payment_record_id,batch_no,order_serial_num,amount,tax_rate,charge,actual_amount,provider_id)
 SELECT batch_payment_record_id,batch_no,order_serial_num,amount,tax_rate,charge,actual_amount,provider_id from tbl_trx_order;
-- 2020-09-08 end
--2020-09-09 start
ALTER TABLE `tbl_trx_order`
ADD COLUMN `remark` varchar(200) NULL COMMENT '打款备注' AFTER `customer_id`;
--2020-09-09 end

-- 发票需求数据库更新 start 2020年9月2日
-- 添加表 start

-- 发票记录表 tbl_invoice_record
CREATE TABLE `tbl_invoice_record` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `merchant_id` int(11) NOT NULL COMMENT '商户id',
  `tax_source_id` int(11) NOT NULL COMMENT '税源地id,对应表tbl_tax_sounrce_company.id',
  `behalf_main_body` varchar(30) NOT NULL COMMENT '代征主体（税源地名称）',
  `invoice_amount` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '发票金额',
  `tax_rate` decimal(20,2) NOT NULL COMMENT '税率',
  `tax_amount` decimal(20,2) NOT NULL COMMENT '税额',
  `unit_price` decimal(20,2) DEFAULT NULL COMMENT '单价',
  `specification` varchar(100) DEFAULT NULL COMMENT '规格型号',
  `invoice_nature` int(2) NOT NULL DEFAULT '1' COMMENT '发票性质 1-纸质发票 2-电子发票',
  `invoice_status` int(2) NOT NULL DEFAULT '1' COMMENT '1-申请开票 2-已完成 3-已驳回（申请开票驳回） 4-退票申请中 5-退票已完成 6-退票已驳回 7-等待开票 8-已作废',
  `invoice_type` int(1) NOT NULL DEFAULT '0' COMMENT '发票类型 0-增值税专用发票1-普通发票',
  `invoice_serial_num` varchar(50) NOT NULL COMMENT '发票申请编号（全局唯一，本地生成）',
  `invoice_no` varchar(50) DEFAULT NULL COMMENT '发票编号（对应发票上的编号）',
  `invoice_title` varchar(40) NOT NULL COMMENT '发票抬头',
  `taxpayer_identification_number` varchar(40) NOT NULL COMMENT '纳税人识别号',
  `company_address` varchar(400) NOT NULL COMMENT '单位注册地址',
  `invoice_mobile` varchar(20) NOT NULL COMMENT '单位手机号',
  `bank_name` varchar(40) NOT NULL COMMENT '开户银行名称',
  `bank_account_no` varchar(20) NOT NULL COMMENT '开户账号',
  `invoice_subject_id` int(11) NOT NULL COMMENT '发票科目id，对应tbl_invoice_subject.id',
  `invoice_content` varchar(300) NOT NULL COMMENT '发票内容，对应tbl_invoice_subject.content',
  `address_id` int(11) NOT NULL COMMENT '收件地址id，对应tbl_address.id',
  `addressee_name` varchar(30) NOT NULL COMMENT '收件人姓名',
  `address_mobile` varchar(20) NOT NULL COMMENT '收件人电话',
  `address` varchar(400) NOT NULL COMMENT '收件地址',
  `express_company_name` varchar(40) DEFAULT NULL COMMENT '快递公司名称（税源地寄出）',
  `express_no` varchar(40) DEFAULT NULL COMMENT '快递单号（税源地寄出）',
  `express_company_name_return` varchar(40) DEFAULT NULL COMMENT '快递公司名称（商户退票寄出）',
  `express_no_return` varchar(40) DEFAULT NULL COMMENT '快递单号（商户退票寄出）',
  `return_reason` varchar(255) DEFAULT NULL COMMENT '退票原因',
  `rejected_reason` varchar(255) DEFAULT NULL COMMENT '驳回原因',
  `audit_user_id` int(11) DEFAULT NULL COMMENT '审核账号id，对应表sys_user.user_id',
  `audit_user_name` varchar(30) DEFAULT NULL COMMENT '审核人姓名，对应sys_user.user_name',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `return_remark` varchar(255) DEFAULT NULL COMMENT '退票备注',
  `del_status` int(1) NOT NULL DEFAULT '0' COMMENT '删除标识0-未删除1-已删除',
  `provider_id` int(11) NOT NULL,
  `reserved_field_one` varchar(30) DEFAULT NULL COMMENT '预留字段1',
  `reserved_field_two` varchar(30) DEFAULT NULL COMMENT '预留字段2',
  `reserved_field_three` varchar(30) DEFAULT NULL COMMENT '预留字段3',
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk-invoice_serial_num` (`invoice_serial_num`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='发票记录表';




-- 发票记录和充值记录关联表 tbl_invoice_recharge_correlation
CREATE TABLE `tbl_invoice_recharge_correlation` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `merchant_id` int(11) NOT NULL COMMENT '商户id',
  `invoice_record_id` int(11) NOT NULL COMMENT '发票记录id，对应表tbl_invoice_record.id',
  `recharge_record_id` int(11) NOT NULL COMMENT '充值记录id，对应tbl_recharge_record.id',
  `del_status` int(1) NOT NULL DEFAULT '0' COMMENT '删除标识0-未删除1-已删除',
  `provider_id` int(11) NOT NULL,
  `reserved_field_one` varchar(30) DEFAULT NULL COMMENT '预留字段1',
  `reserved_field_two` varchar(30) DEFAULT NULL COMMENT '预留字段2',
  `reserved_field_three` varchar(30) DEFAULT NULL COMMENT '预留字段3',
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='发票记录和充值记录关联表';

-- 发票科目表 tbl_invoice_subject
CREATE TABLE `tbl_invoice_subject` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL COMMENT '科目内容（用于发票内容）',
  `provider_id` int(11) NOT NULL,
  `reserved_field_one` varchar(30) DEFAULT NULL COMMENT '预留字段1',
  `reserved_field_two` varchar(30) DEFAULT NULL COMMENT '预留字段2',
  `reserved_field_three` varchar(30) DEFAULT NULL COMMENT '预留字段3',
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='发票科目表';

-- 岗位表 tbl_post
CREATE TABLE `tbl_post` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL COMMENT '岗位title',
  `provider_id` int(11) NOT NULL,
  `reserved_field_one` varchar(30) DEFAULT NULL COMMENT '预留字段1',
  `reserved_field_two` varchar(30) DEFAULT NULL COMMENT '预留字段2',
  `reserved_field_three` varchar(30) DEFAULT NULL COMMENT '预留字段3',
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='岗位表，用于C端签约';

-- 税源地和发票科目关联表 tbl_tax_source_invoice_subject_correlation
CREATE TABLE `tbl_tax_source_invoice_subject_correlation` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `tax_sounrce_company_id` int(11) NOT NULL COMMENT '税源地id，对应tbl_tax_sounrce_company.id',
  `invoice_subject_id` int(11) NOT NULL COMMENT '发票科目id，对应tbl_invoice_subject.id',
  `del_status` int(1) NOT NULL DEFAULT '0' COMMENT '删除标识0-未删除1-已删除',
  `provider_id` int(11) NOT NULL,
  `reserved_field_one` varchar(30) DEFAULT NULL COMMENT '预留字段1',
  `reserved_field_two` varchar(30) DEFAULT NULL COMMENT '预留字段2',
  `reserved_field_three` varchar(30) DEFAULT NULL COMMENT '预留字段3',
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='税源地和发票科目关联表';

-- 税源地和岗位关联表 tbl_tax_source_post_correlation
CREATE TABLE `tbl_tax_source_post_correlation` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `tax_sounrce_company_id` int(11) NOT NULL COMMENT '税源地id，对应tbl_tax_sounrce_company.id',
  `post_id` int(11) NOT NULL COMMENT '岗位id，对应tbl_post.id',
  `del_status` int(1) NOT NULL DEFAULT '0' COMMENT '删除标识0-未删除1-已删除',
  `provider_id` int(11) NOT NULL,
  `reserved_field_one` varchar(30) DEFAULT NULL COMMENT '预留字段1',
  `reserved_field_two` varchar(30) DEFAULT NULL COMMENT '预留字段2',
  `reserved_field_three` varchar(30) DEFAULT NULL COMMENT '预留字段3',
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='税源地和岗位关联表';


-- 商户和发票科目关联表 tbl_merchant_invoice_subject_correlation
CREATE TABLE `tbl_merchant_invoice_subject_correlation` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `merchant_info_id` int(11) NOT NULL COMMENT '商户id，对应tbl_tax_merchant_info.id',
  `invoice_subject_id` int(11) NOT NULL COMMENT '发票科目id，对应tbl_invoice_subject.id',
  `is_default` int(1) DEFAULT NULL COMMENT '是否默认 0-否 1-是',
  `del_status` int(1) NOT NULL DEFAULT '0' COMMENT '删除标识0-未删除1-已删除',
  `provider_id` int(11) NOT NULL,
  `reserved_field_one` varchar(30) DEFAULT NULL COMMENT '预留字段1',
  `reserved_field_two` varchar(30) DEFAULT NULL COMMENT '预留字段2',
  `reserved_field_three` varchar(30) DEFAULT NULL COMMENT '预留字段3',
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='商户和发票科目关联表';
-- 添加表 end

-- 添加发票科目数据 start
-- ----------------------------
-- tbl_invoice_subject
-- ----------------------------
INSERT INTO `tbl_invoice_subject` VALUES (1, '*物流辅助服务*装卸搬运服务', 1, NULL, NULL, NULL, '2020-09-11 21:41:06', '2020-09-11 21:41:10');
INSERT INTO `tbl_invoice_subject` VALUES (2, '*现代服务*市场推广', 1, NULL, NULL, NULL, '2020-09-11 21:41:10', '2020-09-11 21:41:12');
INSERT INTO `tbl_invoice_subject` VALUES (3, '*现代服务*信息服务费', 1, NULL, NULL, NULL, '2020-09-11 21:41:13', '2020-09-11 21:41:14');
INSERT INTO `tbl_invoice_subject` VALUES (4, '*现代服务*营销服务费', 1, NULL, NULL, NULL, '2020-09-11 21:41:16', '2020-09-11 21:41:17');
INSERT INTO `tbl_invoice_subject` VALUES (5, '*现代服务*音视频服务', 1, NULL, NULL, NULL, '2020-09-11 21:41:18', '2020-09-11 21:41:19');
INSERT INTO `tbl_invoice_subject` VALUES (6, '*信息技术服务*信息服务费', 1, NULL, NULL, NULL, '2020-09-11 09:02:13', '2020-09-11 21:41:30');
INSERT INTO `tbl_invoice_subject` VALUES (7, '*现代服务费*互联网营销推广费', 1, NULL, NULL, NULL, '2020-09-11 09:02:16', '2020-09-11 21:41:36');

-- tbl_tax_source_invoice_subject_correlation
-- ----------------------------
INSERT INTO `tbl_tax_source_invoice_subject_correlation` VALUES (1, 3, 6, 0, 1, NULL, NULL, NULL, '2020-09-11 21:45:53', '2020-09-11 21:45:55');
INSERT INTO `tbl_tax_source_invoice_subject_correlation` VALUES (2, 3, 7, 0, 1, NULL, NULL, NULL, '2020-09-11 21:45:49', '2020-09-11 21:45:51');
INSERT INTO `tbl_tax_source_invoice_subject_correlation` VALUES (3, 3, 4, 0, 1, NULL, NULL, NULL, '2020-09-11 21:45:46', '2020-09-11 21:45:48');
INSERT INTO `tbl_tax_source_invoice_subject_correlation` VALUES (4, 4, 1, 0, 1, NULL, NULL, NULL, '2020-09-11 21:45:42', '2020-09-11 21:45:44');
INSERT INTO `tbl_tax_source_invoice_subject_correlation` VALUES (5, 4, 2, 0, 1, NULL, NULL, NULL, '2020-09-11 21:45:40', '2020-09-11 21:45:41');
INSERT INTO `tbl_tax_source_invoice_subject_correlation` VALUES (6, 4, 3, 0, 1, NULL, NULL, NULL, '2020-09-11 21:45:36', '2020-09-11 21:45:38');
INSERT INTO `tbl_tax_source_invoice_subject_correlation` VALUES (7, 4, 5, 0, 1, NULL, NULL, NULL, '2020-09-11 21:45:34', '2020-09-11 21:45:35');
INSERT INTO `tbl_tax_source_invoice_subject_correlation` VALUES (8, 4, 6, 0, 1, NULL, NULL, NULL, '2020-09-11 21:45:32', '2020-09-11 21:45:33');
INSERT INTO `tbl_tax_source_invoice_subject_correlation` VALUES (9, 4, 4, 0, 1, NULL, NULL, NULL, '2020-09-11 21:45:28', '2020-09-11 21:45:30');

-- 添加发票科目数据 end
-- 发票需求数据库更新 end 2020年9月2日

-- 2020-09-07 start
CREATE TABLE `tbl_customer_promotion` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `customer_id` int(11) NOT NULL COMMENT '员工id 对应tbl_customer_info中的id',
  `province` varchar(20) NOT NULL COMMENT '省',
  `city` varchar(20) NOT NULL COMMENT '市',
  `area` varchar(20) NOT NULL COMMENT '区',
  `street` varchar(50) NOT NULL COMMENT '街道',
  `community` varchar(50) NOT NULL COMMENT '社区',
  `unit_price` decimal(11,2) NOT NULL COMMENT '单价',
  `quantity` int(11) NOT NULL COMMENT '数量',
  `promotion_start_time` varchar(10) NOT NULL COMMENT '推广开始时间',
  `promotion_end_time` varchar(10) NOT NULL COMMENT '推广结束时间',
  `merchant_id` int(11) NOT NULL COMMENT '商户id',
  `trx_order_id` int(11) DEFAULT NULL COMMENT '交易订单id',
  `provider_id` int(11) NOT NULL,
  `reserved_field_one` varchar(30) DEFAULT NULL COMMENT '预留字段1',
  `reserved_field_two` varchar(30) DEFAULT NULL COMMENT '预留字段2',
  `reserved_field_three` varchar(30) DEFAULT NULL COMMENT '预留字段3',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='商户对应的员工推广信息';

ALTER TABLE `tbl_cooperation`
ADD COLUMN `promotion_switch` int(1) NOT NULL DEFAULT '0' COMMENT '用户是否推广开关 0-关 1-开' AFTER `single_service_big_rate`;

-- 添加发票状态字典数据
DELETE from sys_dict_data WHERE dict_type='invoice_status';

INSERT INTO `mmtax`.`sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (NULL , '1', '已开具', '2', 'invoice_status', NULL, 'primary', 'N', '0', 'admin', '2020-09-02 13:46:54', '', NULL, NULL, NULL);
INSERT INTO `mmtax`.`sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (NULL, '2', '已退票', '5', 'invoice_status', NULL, 'warning', 'N', '0', 'admin', '2020-09-02 13:47:16', '', NULL, NULL, NULL);
INSERT INTO `mmtax`.`sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (NULL, '3', '已开具', '6', 'invoice_status', '', 'primary', 'N', '0', 'admin', '2020-09-03 11:56:20', 'admin', '2020-09-03 11:56:32', '', NULL);

-- 添加发票开票状态字典数据
INSERT INTO `mmtax`.`sys_dict_type` (`dict_id`, `dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (NULL, '开票状态', 'billing_status', '0', 'admin', '2020-09-02 13:47:49', '', NULL, NULL, NULL);

INSERT INTO `mmtax`.`sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (NULL, '1', '已申请', '1', 'billing_status', NULL, 'primary', 'N', '0', 'admin', '2020-09-02 13:48:12', '', NULL, NULL, NULL);
INSERT INTO `mmtax`.`sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (NULL, '2', '已完成', '2', 'billing_status', NULL, 'success', 'N', '0', 'admin', '2020-09-02 13:48:29', '', NULL, NULL, NULL);
INSERT INTO `mmtax`.`sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (NULL, '3', '已驳回', '3', 'billing_status', NULL, 'danger', 'N', '0', 'admin', '2020-09-02 13:48:56', '', NULL, NULL, NULL);
INSERT INTO `mmtax`.`sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (NULL, '4', '退票处理中', '4', 'billing_status', NULL, 'info', 'N', '0', 'admin', '2020-09-02 13:49:12', '', NULL, NULL, NULL);
INSERT INTO `mmtax`.`sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (NULL, '5', '退票已完成', '5', 'billing_status', NULL, 'success', 'N', '0', 'admin', '2020-09-02 13:49:31', '', NULL, NULL, NULL);
INSERT INTO `mmtax`.`sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (NULL, '6', '退票已驳回', '6', 'billing_status', NULL, 'danger', 'N', '0', 'admin', '2020-09-02 13:49:58', '', NULL, NULL, NULL);
INSERT INTO `mmtax`.`sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (NULL, '7', '开票中', '7', 'billing_status', NULL, 'info', 'N', '0', 'admin', '2020-09-02 13:50:38', '', NULL, NULL, NULL);
INSERT INTO `mmtax`.`sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (NULL, '8', '已作废', '8', 'billing_status', NULL, 'warning', 'N', '0', 'admin', '2020-09-02 13:50:56', '', NULL, NULL, NULL);

-- 推广列表菜单
INSERT INTO `mmtax`.`sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES ('2180', '推广列表', '2000', '7', 'manager/trxOrder/customerPromotion', 'menuItem', 'C', '0', 'manager:trxOrder:customerPromotion', '#', 'admin', '2020-09-07 17:10:24', '', NULL, '', '1');
INSERT INTO `mmtax`.`sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES ('2181', '导出推广列表记录', '2180', '1', '#', 'menuItem', 'F', '0', 'manager:companyInfo:customerPromotionExport', '#', 'admin', '2020-09-07 17:20:22', '', NULL, '', '1');

-- 更新表字段
ALTER TABLE tbl_invoice_apply CHANGE invice_serial_num invoice_serial_num  varchar(50);
ALTER TABLE tbl_invoice_apply_amount CHANGE invice_serial_name invoice_serial_name  varchar(255);
-- 2020-09-07 推广需求end

-- 在tbl_invoice_info表中添加字段bank_name
alter table tbl_invoice_info add column bank_name varchar(50) comment '开户行';
-- 2020-09-16

--关闭税源地管理菜单
UPDATE sys_menu SET visible = 1 WHERE menu_id = 2105;(线上库2091)
-- 2020-09-22关闭税源地管理菜单end


-- 2020-09-16 start
ALTER TABLE tbl_customer_protocol MODIFY COLUMN `comment` VARCHAR ( 100 ) DEFAULT NULL COMMENT '失败原因';
ALTER TABLE `tbl_customer_protocol` ADD COLUMN `remark` VARCHAR ( 100 ) NULL COMMENT '签约备注' AFTER `comment`;
-- 2020-09-16 修改失败原因需求end

-- 2020-9-22 系统管理-->角色管理-->销售-->数据权限-->数据范围-->仅本人数据权限 start
update sys_role set data_scope = 5 where role_key = 'sale';
-- 2020-9-22 end

-- 2020-09-23 企业需求 staet
-- 梁凡需求 开始
-- 菜单
UPDATE `sys_menu` SET `menu_name` = '合同数据', `parent_id` = 2144, `order_num` = 2, `url` = 'manager/companyInfo/contractInfo', `target` = 'menuItem', `menu_type` = 'C', `visible` = '0', `perms` = 'manager:companyInfo:contractInfo', `icon` = '#', `create_by` = 'admin', `create_time` = '2020-08-14 16:14:17', `update_by` = 'admin', `update_time` = '2020-09-23 18:20:28', `remark` = '', `provider_id` = 1 WHERE `menu_id` = 2146;
UPDATE `sys_menu` SET `menu_name` = '付款详情', `parent_id` = 2144, `order_num` = 3, `url` = 'manager/companyInfo/payInfo', `target` = 'menuItem', `menu_type` = 'C', `visible` = '0', `perms` = 'manager:companyInfo:payInfo', `icon` = '#', `create_by` = 'admin', `create_time` = '2020-08-14 16:15:12', `update_by` = 'admin', `update_time` = '2020-09-23 18:21:25', `remark` = '', `provider_id` = 1 WHERE `menu_id` = 2147;
UPDATE `sys_menu` SET `menu_name` = '发放数据', `parent_id` = 2144, `order_num` = 4, `url` = 'manager/companyInfo/grantInfo', `target` = 'menuItem', `menu_type` = 'C', `visible` = '0', `perms` = 'manager:companyInfo:grantInfo', `icon` = '#', `create_by` = 'admin', `create_time` = '2020-08-14 16:15:42', `update_by` = 'admin', `update_time` = '2020-09-23 18:41:58', `remark` = '', `provider_id` = 1 WHERE `menu_id` = 2148;
UPDATE `sys_menu` SET `menu_name` = '查询合同数据', `parent_id` = 2146, `order_num` = 2, `url` = '#', `target` = 'menuItem', `menu_type` = 'F', `visible` = '0', `perms` = 'manager:companyInfo:selectContractInfo', `icon` = '#', `create_by` = 'admin', `create_time` = '2020-08-14 16:16:54', `update_by` = 'admin', `update_time` = '2020-09-23 13:41:12', `remark` = '', `provider_id` = 1 WHERE `menu_id` = 2149;
UPDATE `sys_menu` SET `menu_name` = '查询付款数据', `parent_id` = 2147, `order_num` = 2, `url` = '#', `target` = 'menuItem', `menu_type` = 'F', `visible` = '0', `perms` = 'manager:companyInfo:selectPayInfo', `icon` = '#', `create_by` = 'admin', `create_time` = '2020-08-14 16:17:16', `update_by` = 'admin', `update_time` = '2020-09-23 18:21:37', `remark` = '', `provider_id` = 1 WHERE `menu_id` = 2150;
UPDATE `sys_menu` SET `menu_name` = '查询发放数据', `parent_id` = 2148, `order_num` = 1, `url` = '#', `target` = 'menuItem', `menu_type` = 'F', `visible` = '0', `perms` = 'manager:companyInfo:selectGrantInfo', `icon` = '#', `create_by` = 'admin', `create_time` = '2020-08-14 16:17:47', `update_by` = 'admin', `update_time` = '2020-09-23 18:42:18', `remark` = '', `provider_id` = 1 WHERE `menu_id` = 2151;
UPDATE `sys_menu` SET `menu_name` = '导出合同数据', `parent_id` = 2146, `order_num` = 3, `url` = '#', `target` = 'menuItem', `menu_type` = 'F', `visible` = '0', `perms` = 'manager:companyInfo:contractExport', `icon` = '#', `create_by` = 'admin', `create_time` = '2020-08-14 16:22:24', `update_by` = 'admin', `update_time` = '2020-09-23 13:42:03', `remark` = '', `provider_id` = 1 WHERE `menu_id` = 2152;
UPDATE `sys_menu` SET `menu_name` = '导出付款数据', `parent_id` = 2147, `order_num` = 3, `url` = '#', `target` = 'menuItem', `menu_type` = 'F', `visible` = '0', `perms` = 'manager:companyInfo:payExport', `icon` = '#', `create_by` = 'admin', `create_time` = '2020-08-14 16:22:56', `update_by` = 'admin', `update_time` = '2020-09-23 18:21:46', `remark` = '', `provider_id` = 1 WHERE `menu_id` = 2153;
UPDATE `sys_menu` SET `menu_name` = '导出发放数据', `parent_id` = 2148, `order_num` = 3, `url` = '#', `target` = 'menuItem', `menu_type` = 'F', `visible` = '0', `perms` = 'manager:companyInfo:grantExport', `icon` = '#', `create_by` = 'admin', `create_time` = '2020-08-14 16:23:27', `update_by` = 'admin', `update_time` = '2020-09-23 18:42:28', `remark` = '', `provider_id` = 1 WHERE `menu_id` = 2154;

INSERT INTO `sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (2193, '企业账户资金流水', 2144, 6, 'manager/companyInfo/companyReceiptInfo', 'menuItem', 'C', '0', 'manager:companyInfo:companyReceiptInfo', '#', 'admin', '2020-09-25 11:10:49', '', NULL, '', 1);
INSERT INTO `sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (2194, '企业资金流水列表', 2193, 1, '#', 'menuItem', 'F', '0', 'manager:companyInfo:selectCompanyReceiptInfo', '#', 'admin', '2020-09-25 11:12:10', '', NULL, '', 1);
INSERT INTO `sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (2195, '导出企业账户资金流水信息', 2193, 2, '#', 'menuItem', 'F', '0', 'manager:companyInfo:companyReceiptInfoExport', '#', 'admin', '2020-09-25 15:01:18', '', NULL, '', 1);

-- 字典
INSERT INTO `sys_dict_type`(`dict_id`, `dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (138, '签约过期状态', 'expire_status', '0', 'admin', '2020-09-24 14:16:44', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_type`(`dict_id`, `dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (139, '变动状态', 'change_status', '0', 'admin', '2020-09-25 14:02:14', 'admin', '2020-09-25 14:03:13', '余额变动明细的变动状态', NULL);
INSERT INTO `sys_dict_type`(`dict_id`, `dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (140, '资金变动交易类型', 'account_detail_type', '0', 'admin', '2020-09-25 16:34:37', '', NULL, '商户账户余额变动明细交易类型', NULL);

INSERT INTO `sys_dict_data`(`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (263, 1, '已签', '0', 'expire_status', '', 'success', 'N', '0', 'admin', '2020-09-24 14:17:07', 'admin', '2020-09-24 14:17:52', '', NULL);
INSERT INTO `sys_dict_data`(`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (264, 2, '已过期', '1', 'expire_status', '', 'warning', 'N', '0', 'admin', '2020-09-24 14:17:28', 'admin', '2020-09-24 14:17:43', '', NULL);
INSERT INTO `sys_dict_data`(`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (265, 1, '失败', '0', 'change_status', '', 'warning', 'N', '0', 'admin', '2020-09-25 14:04:19', 'admin', '2020-09-25 14:04:23', '', NULL);
INSERT INTO `sys_dict_data`(`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (266, 2, '成功', '1', 'change_status', NULL, 'success', 'N', '0', 'admin', '2020-09-25 14:04:39', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_data`(`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (267, 1, '发放佣金', '0', 'account_detail_type', '', 'info', 'N', '0', 'admin', '2020-09-25 16:35:06', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_data`(`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (268, 2, '账户付款', '1', 'account_detail_type', NULL, 'success', 'N', '0', 'admin', '2020-09-25 16:35:31', '', NULL, NULL, NULL);

-- 在tbl_merchant_account_detail表中添加 资金明细类型（0-发放佣金 1-付款）
ALTER TABLE `tbl_merchant_account_detail`
ADD COLUMN `payment_type` int(2) DEFAULT NULL COMMENT '资金明细类型（0-发放佣金 1-付款  2-大额补交服务费 3-大额退还服务费 4-退回）' AFTER `type`;
-- 梁凡需求 结束

-- 远鹏需求 开始
-- 在tbl_batch_payment_record表中添加字段task_name
alter table tbl_batch_payment_record add column task_name varchar(30) comment '任务名称';

-- 在tbl_customer_info表中添加字段task_name
alter table tbl_customer_info add column task_name varchar(30) comment '任务名称';

-- 新增菜单
INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES ('2187', '个人收款', '2144', '5', '/manager/companyInfo/personalReceipt', 'menuItem', 'C', '0', 'manager:companyInfo:personalReceipt', '#', 'admin', '2020-09-24 09:16:20', 'admin', '2020-09-24 16:38:59', '', '1');
INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES ('2188', '个人账户资金流水', '2144', '6', '/manager/companyInfo/personalAccountFundChange', 'menuItem', 'C', '0', 'manager:companyInfo:personalAccountFundChange', '#', 'admin', '2020-09-24 09:23:19', 'admin', '2020-09-24 09:27:22', '', '1');
INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES ('2189', '发票管理', '2144', '7', '/manager/companyInfo/invoiceInfo', 'menuItem', 'C', '0', 'manager:companyInfo:invoiceInfo', '#', 'admin', '2020-09-24 09:25:08', '', NULL, '', '1');

-- 在tbl_customer_account_detail表中添加字段payment_type
alter table tbl_customer_account_detail add column payment_type varchar(4) comment '交易类型：0-账户收款 1-收取佣金';

-- 新添加的字典数据（sys_dict_data）
INSERT INTO `sys_dict_data`(`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (272, 1, '账户收款', '0', 'personal_trx_type', '', 'primary', 'Y', '0', 'admin', '2020-09-27 09:55:43', 'admin', '2020-09-27 10:04:16', '', NULL);
INSERT INTO `sys_dict_data`(`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (273, 2, '收取佣金', '1', 'personal_trx_type', '', 'success', 'Y', '0', 'admin', '2020-09-27 09:56:05', 'admin', '2020-09-27 10:47:37', '', NULL);

-- 新添加的字典类型（sys_dict_type）
INSERT INTO `sys_dict_type`(`dict_id`, `dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (143, '个人交易类型', 'personal_trx_type', '0', 'admin', '2020-09-27 09:55:17', 'admin', '2020-09-27 10:03:42', '个人交易类型', NULL);

-- 远鹏需求 结束
-- 2020-09-28 企业需求 end


-- 2020-09-11 小程序公众号需求 start
CREATE TABLE `tbl_customer_withdraw`  (
  `id` int(11) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `customer_id` int(11) NULL DEFAULT NULL COMMENT '灵工id',
  `merchant_id` int(11) NULL DEFAULT NULL COMMENT '商户id',
  `amount` decimal(11, 2) NULL DEFAULT NULL COMMENT '提现金额',
  `withdraw_account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '提现账号',
  `withdraw_channel` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '提现渠道 BANK-银行 ALIPAY-支付宝',
  `customer_serial_num` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '灵工流水号',
  `withdraw_serial_num` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '提现流水号',
  `comment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '提现备注',
  `withdraw_status` int(1) NULL DEFAULT NULL COMMENT '-1-初始化 0-进行中 1-提现成功 2-提现失败',
  `provider_id` int(11) NULL DEFAULT NULL,
  `async_status` int(1) NULL DEFAULT 0 COMMENT '后续异步动作是否处理 0否 1是',
  `reserved_field_one` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段1',
  `reserved_field_two` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段2',
  `reserved_field_three` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段3',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '灵工提现记录表' ROW_FORMAT = Dynamic;

ALTER TABLE `tbl_customer_withdraw`
ADD COLUMN `bank_id` varchar(30) NULL COMMENT '对应网商bankId' AFTER `withdraw_account`;

ALTER TABLE `tbl_customer_info`
ADD COLUMN `withdraw_pass` varchar(100) NULL COMMENT '提现密码' AFTER `email`;

ALTER TABLE `tbl_customer_info`
ADD COLUMN `salt` varchar(100) NULL COMMENT '盐' AFTER `withdraw_pass`;

ALTER TABLE `tbl_trx_order`
ADD COLUMN `need_pay_card` int(1) NOT NULL DEFAULT 1 COMMENT '是否需要直接打到卡 0-否 1-是' AFTER `use_big_rate`;

CREATE TABLE `tbl_customer_wechat_info`(
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `customer_id` INT(11) NOT NULL COMMENT '灵工id',
  `wechat_number` VARCHAR(20) NOT NULL COMMENT '微信号',
  `wechat_name` VARCHAR(32) NOT NULL COMMENT '微信名称',
  `provider_id` INT(11),
  `reserved_field_one` VARCHAR(30),
  `reserved_field_two` VARCHAR(30),
  `reserved_field_three` VARCHAR(30),
  `create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `update_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=INNODB CHARSET=utf8 COLLATE=utf8_general_ci
COMMENT='灵工与灵工微信关联表';

ALTER TABLE `tbl_cooperation`
  ADD COLUMN `customer_withdraw_switch` INT(1) DEFAULT 0  NOT NULL   COMMENT '灵工提现开关 0关 1开' AFTER `big_rate_switch`;

  ALTER TABLE `tbl_customer_wechat_info`
ADD COLUMN `login_mobile` varchar(20) NULL COMMENT '登录手机号(实名认证)' AFTER `wechat_name`;

CREATE TABLE `tbl_trade_refund_order` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
	`link_record_type` int(1) DEFAULT NULL COMMENT '1-tbl_transfer_order表',
  `link_serial_num` varchar(100) DEFAULT NULL COMMENT '关联的原始单号',
  `amount` decimal(11,2) DEFAULT NULL COMMENT '退款金额',
  `charge` decimal(11,2) DEFAULT NULL COMMENT '退款手续费(若有)',
  `actual_amount` decimal(11,2) DEFAULT NULL COMMENT '实际退款金额',
  `refund_serial_num` varchar(100) DEFAULT NULL COMMENT '转账流水号(可查网商)',
  `status` int(1) DEFAULT NULL COMMENT '-1-初始化0-进行中1-已退款2-退款失败',
  `comment` varchar(255) DEFAULT NULL COMMENT '备注',
  `async_status` int(1) DEFAULT '0' COMMENT '后续异步动作是否处理 0否 1是',
  `provider_id` int(11) DEFAULT NULL,
  `reserved_field_one` varchar(30) DEFAULT NULL COMMENT '预留字段1',
  `reserved_field_two` varchar(30) DEFAULT NULL COMMENT '预留字段2',
  `reserved_field_three` varchar(30) DEFAULT NULL COMMENT '预留字段3',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `refund_serial_num_index` (`refund_serial_num`) USING BTREE COMMENT '唯一退款单号索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='退款记录表';

ALTER TABLE `tbl_trade_refund_order`
ADD COLUMN `tax_source_id` int(11) NULL COMMENT '税源地id' AFTER `async_status`;

CREATE TABLE `tbl_customer_day_amount_change` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `merchant_id` int(11) NOT NULL COMMENT '商户id',
  `customer_id` int(11) NOT NULL COMMENT '灵工id',
  `period` varchar(20) NOT NULL COMMENT '当日',
  `start_amount` decimal(11,2) NOT NULL COMMENT '当日起始金额',
  `end_amoutnt` decimal(11,2) NOT NULL COMMENT '当日结束金额',
  `diff_amount` decimal(11,2) NOT NULL COMMENT '当日相差金额,结束金额-起始金额',
  `provider_id` int(11) DEFAULT NULL,
  `reserved_field_one` varchar(30) DEFAULT NULL,
  `reserved_field_two` varchar(30) DEFAULT NULL,
  `reserved_field_three` varchar(30) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='灵工每日金额变化表';

ALTER TABLE `tbl_customer_wechat_info`
ADD COLUMN `open_id` varchar(100) NULL COMMENT '微信对应的openId' AFTER `login_mobile`;

ALTER TABLE `tbl_customer_wechat_info`
MODIFY COLUMN `wechat_number` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '微信号' AFTER `customer_id`;

-- 提现记录 sql
INSERT INTO `sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (2182, '提现管理', 0, 4, '#', 'menuItem', 'M', '0', '', 'fa fa-database', 'admin', '2020-09-11 11:53:36', 'admin', '2020-09-11 11:53:48', '', 1);
INSERT INTO `sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (2183, '支付宝提现记录', 2182, 1, 'business/customerWithdraw/customerWithdrawAlipayInfoView', 'menuItem', 'C', '0', 'business:customerWithdraw:customerWithdrawAlipayInfoView', '#', 'admin', '2020-09-11 13:57:35', 'admin', '2020-09-15 11:59:25', '', 1);
INSERT INTO `sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (2184, '银行卡提现记录', 2182, 2, 'business/customerWithdraw/customerWithdrawBankInfoView', 'menuItem', 'C', '0', 'business:customerWithdraw:customerWithdrawBankInfoView', '#', 'admin', '2020-09-11 13:58:58', '', NULL, '', 1);

INSERT INTO `sys_dict_type`(`dict_id`, `dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (137, '提现状态', 'withdraw_status', '0', 'admin', '2020-09-16 17:32:17', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_data`(`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (259, 1, '待处理', '-1', 'withdraw_status', NULL, 'info', 'N', '0', 'admin', '2020-09-16 17:32:48', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_data`(`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (260, 2, '处理中', '0', 'withdraw_status', NULL, 'primary', 'N', '0', 'admin', '2020-09-16 17:33:02', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_data`(`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (261, 3, '成功', '1', 'withdraw_status', NULL, 'success', 'N', '0', 'admin', '2020-09-16 17:33:17', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_data`(`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (262, 4, '失败', '2', 'withdraw_status', NULL, 'warning', 'N', '0', 'admin', '2020-09-16 17:33:36', '', NULL, NULL, NULL);

-- 2020-09-11 小程序公众号需求 end

--下载凭证 开始
INSERT INTO `sys_job`(`job_id`, `job_name`, `job_group`, `invoke_target`, `cron_expression`, `misfire_policy`, `concurrent`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (null, 'HandleTransactionVoucherJob', 'test', 'HandleTransactionVoucherJob.handleTransactionVoucherJob()', '0 0 2 * * ?', '1', '0', '1', 'admin', '2020-10-10 16:06:59', '', NULL, '', NULL);
--下载凭证 结束 2020-10-10

-- 2020-9-30 start 我方补偿服务费算法
ALTER TABLE `tbl_batch_payment_record`
ADD COLUMN `over_charge` decimal(20, 2) NULL COMMENT '此次批量打款我方补偿的服务费' AFTER `payment_actual_amount`;

CREATE TABLE `tbl_order_reimburse` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `amount` decimal(11,2) DEFAULT NULL COMMENT '补偿金额',
  `order_serial_num` varchar(50) DEFAULT NULL COMMENT '关联订单号',
  `provider_id` int(11) DEFAULT NULL,
  `reserved_field_one` varchar(30) DEFAULT NULL COMMENT '预留字段1',
  `reserved_field_two` varchar(30) DEFAULT NULL COMMENT '预留字段2',
  `reserved_field_three` varchar(30) DEFAULT NULL COMMENT '预留字段3',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单服务费补偿表';
-- 2020-9-30 end

-- 2020-10-09 海宁商户签约按钮 需求start
ALTER TABLE tbl_cooperation MODIFY COLUMN signing_type INT ( 1 ) DEFAULT NULL COMMENT '签约类型0-微信签约1-线上签约2-纸质签约3-线上签约(可打款)';
-- 2020-10-09 海宁商户签约按钮 需求end

-- 添加江西启博税源地发票科目
INSERT INTO `tbl_tax_source_invoice_subject_correlation` (`id`, `tax_sounrce_company_id`, `invoice_subject_id`, `del_status`, `provider_id`, `reserved_field_one`, `reserved_field_two`, `reserved_field_three`, `create_time`, `update_time`) VALUES (NULL, '5', '1', '0', '1', NULL, NULL, NULL, '2020-09-11 21:45:42', '2020-09-11 21:45:44');
INSERT INTO `tbl_tax_source_invoice_subject_correlation` (`id`, `tax_sounrce_company_id`, `invoice_subject_id`, `del_status`, `provider_id`, `reserved_field_one`, `reserved_field_two`, `reserved_field_three`, `create_time`, `update_time`) VALUES (NULL, '5', '2', '0', '1', NULL, NULL, NULL, '2020-09-11 21:45:40', '2020-09-11 21:45:41');
INSERT INTO `tbl_tax_source_invoice_subject_correlation` (`id`, `tax_sounrce_company_id`, `invoice_subject_id`, `del_status`, `provider_id`, `reserved_field_one`, `reserved_field_two`, `reserved_field_three`, `create_time`, `update_time`) VALUES (NULL, '5', '3', '0', '1', NULL, NULL, NULL, '2020-09-11 21:45:36', '2020-09-11 21:45:38');
INSERT INTO `tbl_tax_source_invoice_subject_correlation` (`id`, `tax_sounrce_company_id`, `invoice_subject_id`, `del_status`, `provider_id`, `reserved_field_one`, `reserved_field_two`, `reserved_field_three`, `create_time`, `update_time`) VALUES (NULL, '5', '5', '0', '1', NULL, NULL, NULL, '2020-09-11 21:45:34', '2020-09-11 21:45:35');
INSERT INTO `tbl_tax_source_invoice_subject_correlation` (`id`, `tax_sounrce_company_id`, `invoice_subject_id`, `del_status`, `provider_id`, `reserved_field_one`, `reserved_field_two`, `reserved_field_three`, `create_time`, `update_time`) VALUES (NULL, '5', '6', '0', '1', NULL, NULL, NULL, '2020-09-11 21:45:32', '2020-09-11 21:45:33');
INSERT INTO `tbl_tax_source_invoice_subject_correlation` (`id`, `tax_sounrce_company_id`, `invoice_subject_id`, `del_status`, `provider_id`, `reserved_field_one`, `reserved_field_two`, `reserved_field_three`, `create_time`, `update_time`) VALUES (NULL, '5', '4', '0', '1', NULL, NULL, NULL, '2020-09-11 21:45:28', '2020-09-11 21:45:30');
-- 运营需求20201013 end

-- 员工可在多商户下修改 开始
CREATE TABLE `tbl_cus_link_mer_info` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `customer_id` int(11) DEFAULT NULL COMMENT '员工id',
  `real_name` varchar(20) DEFAULT NULL COMMENT '员工真名',
  `cus_esign_id` int(11) DEFAULT NULL COMMENT '员工签约id',
  `certificate_no` varchar(50) DEFAULT NULL COMMENT '证件号码',
  `merchant_id` int(11) NOT NULL COMMENT '商户id',
  `merchant_name` varchar(50) DEFAULT NULL COMMENT '商户名称',
  `tax_source_id` int(11) NOT NULL COMMENT '所属税源地id',
  `month_use_rate` int(1) NOT NULL DEFAULT '0' COMMENT '当月使用费率 0普通 1大额',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='员工关联商户信息表';

ALTER TABLE `tbl_customer_info`
  ADD COLUMN `customer_warn` INT(1) DEFAULT 0  NULL   COMMENT '0为不预警 1为预警' AFTER `month_use_rate`;

UPDATE `sys_dict_data` SET `dict_sort` = 2, `dict_label` = '未签', `dict_value` = '1', `dict_type` = 'sign_status', `css_class` = '', `list_class` = 'primary', `is_default` = 'N', `status` = '0', `create_by` = 'admin', `create_time` = '2020-08-05 09:17:38', `update_by` = 'admin', `update_time` = '2020-10-15 11:40:02', `remark` = '', `provider_id` = NULL WHERE `dict_code` = 245;

-- 员工可在多商户下修改 结束

--2020-10-15 派单需求开始
CREATE TABLE `tbl_batch_task_record` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `batch_no` varchar(50) DEFAULT NULL COMMENT '批次号',
  `task_total_num` int(11) DEFAULT NULL COMMENT '任务总数',
  `task_amount` decimal(11,2) DEFAULT NULL COMMENT '该批次任务总金额',
  `task_status` int(1) not null DEFAULT '0' COMMENT '任务状态 0-待完成 1-已完成 2-未完成',
  `task_info_id` int(10) DEFAULT NULL COMMENT '任务信息id',
  `task_name` varchar(50) DEFAULT NULL COMMENT '任务名称',
  `task_begin_time` varchar(20) DEFAULT NULL COMMENT '任务发布时间',
  `task_complete_time` varchar(20) DEFAULT NULL COMMENT '任务完成时间',
  `task_require_complete_time` varchar(20) DEFAULT NULL COMMENT '规定完成时间',
  `merchant_id` int(11) not NULL COMMENT '商户id',
  `provider_id` int(11) DEFAULT NULL,
  `reserved_field_one` varchar(30) DEFAULT NULL COMMENT '预留字段1',
  `reserved_field_two` varchar(30) DEFAULT NULL COMMENT '预留字段2',
  `reserved_field_three` varchar(30) DEFAULT NULL COMMENT '预留字段3',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务批次记录表';


CREATE TABLE `tbl_batch_task_detail` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `batch_task_record_id` int(11) not NULL COMMENT '批次id',
  `batch_no` varchar(50) DEFAULT NULL COMMENT '批次号',
  `task_serial_num` varchar(50) not NULL COMMENT '任务编号',
  `task_info_id` int(10) DEFAULT NULL COMMENT '任务信息id',
  `task_name` varchar(100) DEFAULT NULL COMMENT '任务名称',
  `task_num` int(10) DEFAULT NULL COMMENT '任务数量',
  `amount` decimal(11,2) DEFAULT NULL COMMENT '任务单价',
  `all_amount` decimal(11,2) DEFAULT NULL COMMENT '任务总价',
  `task_status` int(1) not null DEFAULT '0' COMMENT '任务状态 0-待完成 1-已完成 2-未完成',
  `task_complete_time` varchar(20) DEFAULT NULL COMMENT '任务完成时间',
  `task_require_complete_time` varchar(20) DEFAULT NULL COMMENT '规定完成时间',
  `merchant_id` int(11) not NULL COMMENT '商户id',
  `tax_source_id` varchar(255) DEFAULT NULL COMMENT '税源地(承接方)',
  `remark` varchar(255) DEFAULT NULL COMMENT '额外备注',
  `provider_id` int(11) DEFAULT NULL,
  `reserved_field_one` varchar(30) DEFAULT NULL,
  `reserved_field_two` varchar(30) DEFAULT NULL,
  `reserved_field_three` varchar(30) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `task_serial_num_index` (`task_serial_num`) USING BTREE COMMENT '唯一任务编号索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务详细记录表';


CREATE TABLE `tbl_customer_task` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `task_detail_id` int(11) DEFAULT NULL COMMENT '详细任务id',
  `customer_id` varchar(20) DEFAULT NULL COMMENT '接任务人id',
  `name` varchar(20) DEFAULT NULL COMMENT '接任务人的姓名',
  `mobile_no` varchar(20) DEFAULT NULL COMMENT '接任务人的联系电话',
  `certificate_no` varchar(50) DEFAULT NULL COMMENT '接任务人的身份证号',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `provider_id` int(11) DEFAULT NULL,
  `reserved_field_one` varchar(30) DEFAULT NULL,
  `reserved_field_two` varchar(30) DEFAULT NULL,
  `reserved_field_three` varchar(30) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务接单记录表';

-- 合作信息表更新 start
ALTER TABLE tbl_cooperation ADD `send_order_switch` INT (1) NOT NULL DEFAULT '0' COMMENT '是否为派单商户开关 0-关（非派单），1-开（派单）' AFTER merchant_id;
ALTER TABLE tbl_cooperation ADD `send_order_mode` INT (1) NOT NULL DEFAULT '1' COMMENT '派单模式 0-手动，1-自动' AFTER send_order_switch;
-- 合作信息表更新 end
--
--  交易订单表更新 start
ALTER TABLE tbl_trx_order ADD `task_record_batch_no` varchar(50) DEFAULT NULL COMMENT '任务批次记录批次号' AFTER need_pay_card;
--  交易订单表更新 end

ALTER TABLE `tbl_customer_wechat_info`
ADD COLUMN `has_quit` int(1) DEFAULT '0' COMMENT '是否退出 0-未退出 1-已退出' AFTER `open_id`;

ALTER TABLE `tbl_batch_task_detail`
MODIFY COLUMN `tax_source_id` int(11) NULL DEFAULT NULL COMMENT '税源地(承接方)' AFTER `merchant_id`;

ALTER TABLE `tbl_customer_task`
MODIFY COLUMN `customer_id` int(11) NULL DEFAULT NULL COMMENT '接任务人id' AFTER `task_detail_id`;

ALTER TABLE `tbl_batch_task_detail`
ADD COLUMN `take_task_status` int(1) NOT NULL DEFAULT '0' COMMENT '是否接单（0-未结单 1-已接单）' AFTER `tax_source_id`;

-- 商户任务信息表，2020/10/13 start
create table tbl_merchant_task_info(
	`id` int primary key auto_increment comment '任务id主键',
	`merchant_id` int not null comment '商户id',
	`task_id` int not null comment '任务初始化id',
	`task_name` varchar(50) not null comment '任务名',
	`task_info` varchar(255) null default null comment '任务简介',
	`del_status` int(1) NOT NULL DEFAULT '0' COMMENT '删除标识0-未删除1-已删除',
	`provider_id` int(11) DEFAULT NULL,
  `reserved_field_one` varchar(30) DEFAULT NULL COMMENT '预留字段1',
  `reserved_field_two` varchar(30) DEFAULT NULL COMMENT '预留字段2',
  `reserved_field_three` varchar(30) DEFAULT NULL COMMENT '预留字段3',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC comment '任务信息记录';
-- 商户任务信息表，2020/10/13 end

-- 任务信息表，2020/10/13 start
create table tbl_task_info(
	`id` int primary key auto_increment comment '任务初始化id主键',
	`task_name` varchar(50) not null comment '任务名',
	`task_info` varchar(255) null default null comment '任务简介',
	`provider_id` int(11) DEFAULT NULL,
  `reserved_field_one` varchar(30) DEFAULT NULL COMMENT '预留字段1',
  `reserved_field_two` varchar(30) DEFAULT NULL COMMENT '预留字段2',
  `reserved_field_three` varchar(30) DEFAULT NULL COMMENT '预留字段3',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC comment '任务信息初始化记录';
-- 任务信息表，2020/10/13 end

-- 新增任务管理菜单 2020/10/16 start
INSERT INTO `sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (2196, '任务管理', 0, 7, '#', 'menuItem', 'M', '0', NULL, 'fa fa-map', 'admin', '2020-10-13 13:32:46', '', NULL, '', 1);
INSERT INTO `sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (2198, '发布记录', 2196, 2, '/manager/merchantTaskInfo/publishRecord', 'menuItem', 'C', '0', 'manager:merchantTaskInfo:publishRecord', '#', 'admin', '2020-10-13 15:00:38', 'admin', '2020-10-13 16:48:11', '', 1);
INSERT INTO `sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (2199, '任务完成记录', 2196, 3, '/manager/merchantTaskInfo/taskCompleted', 'menuItem', 'C', '0', 'manager:merchantTaskInfo:taskCompleted', '#', 'admin', '2020-10-13 15:02:23', 'admin', '2020-10-13 16:48:40', '', 1);
INSERT INTO `sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (2201, '任务完成记录', 2199, 1, '#', 'menuItem', 'F', '0', 'manager:merchantTaskInfo:selectMerchantTaskCompleted', '#', 'admin', '2020-10-15 17:19:29', '', NULL, '', 1);
INSERT INTO `sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (2202, '导出任务完成记录', 2199, 2, '#', 'menuItem', 'F', '0', 'manager:merchantTaskInfo:exportMerchantTaskCompleted', '#', 'admin', '2020-10-16 09:26:40', '', NULL, '', 1);
-- 新增任务管理菜单 2020/10/16 end

-- 任务信息初始化 2020/10/16 start

insert into tbl_task_info(task_name,task_info) values('软件开发服务','对基础软件、应用软件、嵌入式软件等软件提供的开发服务。');

insert into tbl_task_info(task_name,task_info) values('软件维护服务','对基础软件、应用软件、嵌入式软件等软件提供的维护服务。');

insert into tbl_task_info(task_name,task_info) values('软件测试服务','对基础软件、应用软件、嵌入式软件等软件提供的测试服务。');

insert into tbl_task_info(task_name,task_info) values('电路设计服务','对集成电路、电子电路产品等提供的设计服务。');

insert into tbl_task_info(task_name,task_info) values('电路测试服务','对集成电路、电子电路产品等提供的测试服务。');

insert into tbl_task_info(task_name,task_info) values('信息系统服务','提供信息系统集成、网络管理、网站内容维护、桌面管理与维护、信息系统应用、基础信息技术管理平台整合、信息技术基础设施管理、数据中心、托管中心、信息安全服务、在线杀毒、虚拟主机等业务活动。包括网站对非自有的网络游戏提供的网络运营服务。');

insert into tbl_task_info(task_name,task_info) values('业务流程管理服务','依托信息技术提供的人力资源管理、财务经济管理、审计管理、税务管理、物流信息管理、经营信息管理和呼叫中心等服务的活动。');

insert into tbl_task_info(task_name,task_info) values('信息系统增值服务','利用信息系统资源为用户附加提供的信息技术服务。包括数据处理、分析和整合、数据库管理、数据备份、数据存储、容灾服务、电子商务平台等。');

insert into tbl_task_info(task_name,task_info) values('专业设计服务','把计划、规划、设想，通过文字、语言、图画、声音、视觉等形式传递出来的业务活动。包括工业设计、造型设计、服装设计、环境设计、平面设计、包装设计、动漫设计、网游设计、展示设计、网站设计、机械设计、广告设计等。');

insert into tbl_task_info(task_name,task_info) values('文印晒图服务','提供文印晒图服务');

insert into tbl_task_info(task_name,task_info) values('装卸搬运服务','使用装卸搬运工具或人力、畜力将货物在运输工具之间、装卸现场之间或者运输工具与装卸现场之间进行装卸和搬运的业务活动。（高空作业除外，高空作业指脚底离地面2米或以上的作业）');

insert into tbl_task_info(task_name,task_info) values('其他企业管理服务','提供总部管理、市场管理、日常综合管理等服务。');

insert into tbl_task_info(task_name,task_info) values('人才委托招聘','乙方根据甲方提供的岗位要求，协助招聘符合甲方要求的员工');

insert into tbl_task_info(task_name) values('其他人力资源服务');

insert into tbl_task_info(task_name,task_info) values('音视频服务','主播挑选、主播培训、输出直播内容。甲方有权要求乙方自行提供、配置与直播服务有关的环境和设备并承担与此直接相关的费用，包括但不限于网络、电脑、麦克风、音视频装置、设备等');

insert into tbl_task_info(task_name,task_info) values('市场推广','通过线上推广和线下推广等方式提高甲方（包括但不限于甲方App\网站等）知名度和点击量，其中线上推广方式包括但不限于在各类社交平台或线上社群发帖、转帖、发优惠券等，线下推广方式包括但不限于贴海报、发传单、发赠品/试用品等');

insert into tbl_task_info(task_name) values('房屋销售代理服务');

insert into tbl_task_info(task_name,task_info) values('渠道搭建及使用','乙方按照甲方约定具体服务事项和成果要求，将渠道搭建及使用事项进行精准配置和安排，包含但不限于协调安排项目服务人员通过个人对特定市场的影响力，使用其个人渠道资源根据甲方要求促成指定项目的具体成果。');

insert into tbl_task_info(task_name,task_info) values('家政服务（非员工制）','提供居家清洁护理服务(高空作业除外，高空作业指脚底离地面2米或以上的作业）');

insert into tbl_task_info(task_name,task_info) values('技术咨询','提供信息、建议、策划、顾问等服务的活动，包括软件、技术等方面的咨询，对技术项目提供可行性论证、技术预测和测试、技术培训、专题技术调查、分析报告和专业知识咨询等。');

insert into tbl_task_info(task_name,task_info) values('咨询服务','提供信息、建议、策划、顾问等服务的活动。包括翻译服务、市场调查服务、金融、软件、技术、财务、税收、法律、内部管理、业务运作、流程管理、健康等方面的咨询。');

insert into tbl_task_info(task_name,task_info) values('家居产品维保','家居产品维护、保养服务（如家电燃气产品、电脑等家居产品的维护保养，不涉及零部件销售）(高空作业除外，高空作业指脚底离地面2 米或以上的作业');

insert into tbl_task_info(task_name,task_info) values('教育信息咨询','提供教育文化领域的专业信息咨询、指导建议、策划教育资讯活动等。');

update tbl_task_info set provider_id=1;

ALTER TABLE `tbl_contract_info`
  CHANGE `file_name` `file_name` VARCHAR(30) CHARSET utf8 COLLATE utf8_general_ci NULL   COMMENT '文件名称';
-- 任务信息初始化 2020/10/16 end

-- 字典数据 2020/10/16 start
INSERT INTO `sys_dict_data`(`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (279, 1, '待完成', '0', 'task_info_status', NULL, 'danger', 'Y', '0', 'admin', '2020-10-16 13:45:40', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_data`(`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (280, 2, '已完成', '1', 'task_info_status', NULL, 'success', 'Y', '0', 'admin', '2020-10-16 13:46:16', '', NULL, NULL, NULL);
-- 字典数据 2020/10/16 end

-- 字典类型 2020/10/16 start
INSERT INTO `sys_dict_type`(`dict_id`, `dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (144, '任务单状态', 'task_info_status', '0', 'admin', '2020-10-16 13:43:21', '', NULL, '任务状态 0-待完成 1-已完成', NULL);
-- 字典类型 2020/10/16 end
--2020-10-15 派单需求结束



-- 2020/10/28 协议岗位需求 start
ALTER TABLE `tbl_customer_protocol`
ADD COLUMN `post` varchar(50) DEFAULT NULL COMMENT '签约岗位' AFTER `sign_serial_num`;

ALTER TABLE `tbl_customer_protocol`
ADD COLUMN `post_id` int(11) DEFAULT NULL COMMENT '签约岗位id' AFTER `sign_serial_num`;

ALTER TABLE `tbl_batch_sign_record`
ADD COLUMN `post` varchar(50) DEFAULT NULL COMMENT '签约岗位' AFTER `merchant_id`;

ALTER TABLE `tbl_batch_sign_record`
ADD COLUMN `post_id` int(11) DEFAULT NULL COMMENT '签约岗位id' AFTER `merchant_id`;

-- ** 江西启博协议 海南浚聚协议 修改 添加岗位信息（上线用）**
UPDATE `tbl_esign_template` SET `template_name` = '江西启博协议', `template_id` = '5eeddfd6daa646c5a84ac7cf25fbb9da', `tax_source_id` = 5, `provider_id` = 1, `reserved_field_one` = NULL, `reserved_field_two` = NULL, `create_time` = '2020-08-04 20:58:07', `update_time` = '2020-10-30 16:14:31' WHERE `id` = 4;
UPDATE `tbl_esign_template` SET `template_name` = '海南浚聚协议', `template_id` = 'f1378a16666c4bc3b5c58f15401810b2', `tax_source_id` = 4, `provider_id` = 1, `reserved_field_one` = NULL, `reserved_field_two` = NULL, `create_time` = '2020-08-04 20:59:58', `update_time` = '2020-10-30 16:14:23' WHERE `id` = 5;

UPDATE `tbl_template_component` SET `component_name` = '合作商户公司全称', `component_id` = '248092a4f6b44fe0a821304e1188fb9b', `esign_template_id` = 5, `template_id` = 'f1378a16666c4bc3b5c58f15401810b2', `provider_id` = 1, `reserved_field_one` = NULL, `reserved_field_two` = NULL, `create_time` = '2020-08-04 21:03:01', `update_time` = '2020-10-30 16:16:26' WHERE esign_template_id = '5' and component_name = '合作商户公司全称';

UPDATE `tbl_template_component` SET `component_name` = '身份证号', `component_id` = 'a6a61edade0644bdb0bfb3936a36a1f4', `esign_template_id` = 5, `template_id` = 'f1378a16666c4bc3b5c58f15401810b2', `provider_id` = 1, `reserved_field_one` = NULL, `reserved_field_two` = NULL, `create_time` = '2020-08-04 21:03:01', `update_time` = '2020-10-30 16:16:27' WHERE esign_template_id = '5' and component_name = '身份证号';

INSERT INTO `tbl_template_component`(`id`, `component_name`, `component_id`, `esign_template_id`, `template_id`, `provider_id`, `reserved_field_one`, `reserved_field_two`, `create_time`, `update_time`) VALUES (null, '任务名称一', 'ecadc05299c54b12a172541b4e80f234', 5, 'f1378a16666c4bc3b5c58f15401810b2', 1, NULL, NULL, '2020-10-30 16:17:40', '2020-10-30 16:17:40');
INSERT INTO `tbl_template_component`(`id`, `component_name`, `component_id`, `esign_template_id`, `template_id`, `provider_id`, `reserved_field_one`, `reserved_field_two`, `create_time`, `update_time`) VALUES (null, '任务名称二', '555c6f5051454e779a92b0d5e394724c', 5, 'f1378a16666c4bc3b5c58f15401810b2', 1, NULL, NULL, '2020-10-30 16:18:02', '2020-10-30 16:18:04');

UPDATE `tbl_template_component` SET `component_name` = '合作商户公司全称', `component_id` = '9e6e9d2dfe2b4cd69be3b76479874e9e', `esign_template_id` = 4, `template_id` = '5eeddfd6daa646c5a84ac7cf25fbb9da', `provider_id` = 1, `reserved_field_one` = NULL, `reserved_field_two` = NULL, `create_time` = '2020-08-04 21:02:33', `update_time` = '2020-10-30 16:18:44' WHERE esign_template_id = '4' and component_name = '合作商户公司全称';

UPDATE `tbl_template_component` SET `component_name` = '身份证号', `component_id` = 'd336cf2296a94bed8db73936ed81da34', `esign_template_id` = 4, `template_id` = '5eeddfd6daa646c5a84ac7cf25fbb9da', `provider_id` = 1, `reserved_field_one` = NULL, `reserved_field_two` = NULL, `create_time` = '2020-08-04 21:02:33', `update_time` = '2020-10-30 16:18:46' WHERE esign_template_id = '4' and component_name = '身份证号';

INSERT INTO `tbl_template_component`(`id`, `component_name`, `component_id`, `esign_template_id`, `template_id`, `provider_id`, `reserved_field_one`, `reserved_field_two`, `create_time`, `update_time`) VALUES (null, '任务名称一', '053a5c8314ca43c7844f9f04a8ca2851', 4, '5eeddfd6daa646c5a84ac7cf25fbb9da', 1, NULL, NULL, '2020-10-30 16:19:39', '2020-10-30 16:19:39');
INSERT INTO `tbl_template_component`(`id`, `component_name`, `component_id`, `esign_template_id`, `template_id`, `provider_id`, `reserved_field_one`, `reserved_field_two`, `create_time`, `update_time`) VALUES (null, '任务名称二', '38a912414c864e7ab1b5822f57fa5fe2', 4, '5eeddfd6daa646c5a84ac7cf25fbb9da', 1, NULL, NULL, '2020-10-30 16:19:56', '2020-10-30 16:19:56');
-- ** 江西启博协议 海南浚聚协议 修改 添加岗位信息（上线用）**

INSERT INTO `sys_config`(`config_id`, `config_name`, `config_key`, `config_value`, `config_type`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (null, '公众号发送短信是否为测试', 'is_sms_test', 'Y', 'Y', 'admin', '2020-11-02 17:11:43', '', NULL, 'Y-是测试 N-不是测试', 1);

-- 2020/10/28 协议岗位需求 end

-- 打款逻辑修改需求开始
CREATE TABLE `tbl_pay_request_data` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `batch_payment_record_id` int(11) DEFAULT NULL COMMENT '批量打款记录id',
  `merchant_id` int(11) DEFAULT NULL COMMENT '商户id',
  `amount` decimal(11,2) DEFAULT NULL COMMENT '请求打款金额',
  `tax_rate` decimal(11,5) DEFAULT NULL COMMENT '税率',
  `charge` decimal(11,2) DEFAULT NULL COMMENT '手续费',
  `in_out_deduction` int(1) NOT NULL DEFAULT '1' COMMENT '0-内扣 1-外扣',
  `merchant_serial_num` varchar(100) DEFAULT NULL COMMENT '商户流水号',
  `name` varchar(255) DEFAULT NULL COMMENT '姓名',
  `mobile` varchar(255) DEFAULT NULL COMMENT '手机号',
  `bank_card` varchar(255) DEFAULT NULL COMMENT '银行卡号',
  `id_card_no` varchar(255) DEFAULT NULL COMMENT '收款人身份证号',
  `status` int(1) DEFAULT '0' COMMENT '0-校验中 1-成功 2-失败',
  `payment_channel` varchar(10) DEFAULT NULL COMMENT 'BANK-银行ALIPAY-支付宝WECHAT-微信打款渠道',
  `remark` varchar(200) DEFAULT NULL COMMENT '打款备注',
  `comment` varchar(255) DEFAULT NULL COMMENT '备注',
  `use_big_rate` int(1) DEFAULT '0' COMMENT '使用大额费率 0否 1是',
  `provider_id` int(11) DEFAULT NULL,
  `del_status` int(1) DEFAULT '0' COMMENT '0-未删除 1-已删除',
  `promotion_status` int(1) NOT NULL DEFAULT '0' COMMENT '是否是上传模板 0 不是 1 是',
  `province` varchar(20) DEFAULT NULL COMMENT '省',
  `city` varchar(20) DEFAULT NULL COMMENT '市',
  `area` varchar(20) DEFAULT NULL COMMENT '区',
  `street` varchar(50) DEFAULT NULL COMMENT '街道',
  `community` varchar(50) DEFAULT NULL COMMENT '社区',
  `unit_price` decimal(11,2) DEFAULT NULL COMMENT '单价',
  `quantity` int(11) DEFAULT NULL COMMENT '数量',
  `promotion_start_time` varchar(10) DEFAULT NULL COMMENT '推广开始时间',
  `promotion_end_tine` varchar(10) DEFAULT NULL COMMENT '推广结束时间',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='打款请求数据表';

-- 2020/11/3 tbl_cooperation start

alter table tbl_cooperation add column `money_model` int(11) default 1 comment '打款模式：0-普通模式 1-自动模式' after `business_type`;

update tbl_cooperation set `money_model` = 1;
-- 2020/11/3 tbl_cooperation end

ALTER TABLE `tbl_batch_payment_record`
ADD COLUMN `confirm_status` int(1) NOT NULL DEFAULT 0 COMMENT '确认执行打款 0-未确认 1-已确认 2-已取消' AFTER `status`;

ALTER TABLE `tbl_batch_payment_record`
ADD COLUMN `pay_status` int(1) NULL DEFAULT 0 COMMENT '是否已打款 0-否 1-是 2-已取消' AFTER `confirm_status`;

ALTER TABLE `tbl_batch_payment_record`
ADD COLUMN `check_status` int(1) NULL DEFAULT 0 COMMENT '是否已发送校验 0-否 1-是' AFTER `status`;

update tbl_batch_payment_record set confirm_status = 1,pay_status = 1,check_status = 1;

-- 打款逻辑修改需求结束


--2020/11/09 异步通知开关 start


ALTER TABLE `tbl_cooperation`
ADD COLUMN `asyn_switch` int(1) NULL DEFAULT 1 COMMENT '异步通知开关 0关 1开 默认开' AFTER `send_order_mode`,
ADD COLUMN `asyn_sign_switch` int(1) NULL DEFAULT 0 COMMENT '异步验签通知开关 0关 1开 默认关' AFTER `asynSwitch`;
--2020/11/09 异步通知开关 end

-- 2020/11/16 新增发票科目 start

-- 新增发票科目“*现代服务*技术服务费”
insert into tbl_invoice_subject(content,provider_id,create_time,update_time) values('*现代服务*技术服务费',1,sysdate(),sysdate());

-- 关联税原地
insert into tbl_tax_source_invoice_subject_correlation(tax_sounrce_company_id,invoice_subject_id,del_status,provider_id,create_time,update_time) values(4,9,0,1,sysdate(),sysdate());

-- 2020/11/16 新增发票科目 end

-- 2020/11/23 订单状态修改 start
UPDATE `sys_dict_data` SET `dict_sort` = 1, `dict_label` = '已取消', `dict_value` = '3', `dict_type` = 'order_status', `css_class` = '', `list_class` = 'warning', `is_default` = 'Y', `status` = '0', `create_by` = 'admin', `create_time` = '2019-07-24 15:38:08', `update_by` = 'admin', `update_time` = '2020-11-24 16:46:10', `remark` = '', `provider_id` = NULL WHERE `dict_code` = 117;
UPDATE `sys_dict_data` SET `dict_sort` = 2, `dict_label` = '待打款', `dict_value` = '0', `dict_type` = 'order_status', `css_class` = '', `list_class` = 'danger', `is_default` = 'Y', `status` = '0', `create_by` = 'admin', `create_time` = '2019-07-24 15:38:23', `update_by` = 'admin', `update_time` = '2020-11-24 16:47:50', `remark` = '', `provider_id` = NULL WHERE `dict_code` = 118;


-- INSERT INTO `sys_dict_data`(`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (117, 1, '已取消', '1', 'order_status', '', 'warning', 'Y', '0', 'admin', '2019-07-24 15:38:08', 'admin', '2020-11-24 16:46:10', '', NULL);
-- INSERT INTO `sys_dict_data`(`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (118, 2, '待打款', '0', 'order_status', '', 'default', 'Y', '0', 'admin', '2019-07-24 15:38:23', 'admin', '2020-11-24 16:47:50', '', NULL);
INSERT INTO `sys_dict_data`(`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (null, 2, '打款中', '2', 'order_status', NULL, 'primary', 'Y', '0', 'admin', '2020-11-24 16:46:59', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_data`(`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (null, 3, '已完成', '1', 'order_status', NULL, 'success', 'Y', '0', 'admin', '2020-11-24 16:49:40', '', NULL, NULL, NULL);

alter table tbl_batch_payment_record modify column `status` int(1) DEFAULT NULL COMMENT '0-待打款 3-已取消 2-打款中 1-已完成';

alter table tbl_batch_payment_record add column payment_complete_count int(11) DEFAULT NULL COMMENT '已完成的打款条数' after `payment_count`;

-- 2020/11/23 订单状态修改 end
-- 2020-11-25 合同数据详情 start
CREATE TABLE `tbl_mer_cus_post` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `certificate_no` varchar(50) DEFAULT NULL COMMENT '证件号码',
  `merchant_id` int(11) NOT NULL COMMENT '商户id',
	`post_name` varchar(30) DEFAULT NULL COMMENT '岗位名称',
  `provider_id` int(11) DEFAULT NULL,
  `reserved_field_one` varchar(30) DEFAULT NULL,
  `reserved_field_two` varchar(30) DEFAULT NULL,
  `reserved_field_three` varchar(30) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='商户灵工岗位表';

ALTER TABLE `tbl_mer_cus_post`
ADD COLUMN `item_one` varchar(20) NOT NULL DEFAULT '' COMMENT '第一项中的空' AFTER `post_name`,
ADD COLUMN `item_two` varchar(20) NOT NULL DEFAULT '' COMMENT '第二项中的空' AFTER `item_one`;
-- 2020-11-25 合同数据详情 end

-- 2020-11-17 悟空云创新增内容 start
CREATE TABLE `tbl_notify_wkyc` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `merchant_id` int(11) NOT NULL COMMENT '商户id',
  `notify_status` int(4) NOT NULL DEFAULT '2' COMMENT '通知状态 0-待发送 1成功 2失败',
  `send_method` int(4) DEFAULT '1' COMMENT '发送方式 1post-json 2get',
  `serial_num` varchar(100) DEFAULT NULL COMMENT '代付订单号',
  `fail_num` int(4) DEFAULT NULL COMMENT '失败次数',
  `last_send_time` timestamp NULL DEFAULT NULL COMMENT '上次发送的时间',
  `provider_id` int(11) DEFAULT NULL,
  `reserved_field_one` varchar(50) DEFAULT NULL COMMENT '预留字段1',
  `reserved_field_two` varchar(50) DEFAULT NULL COMMENT '预留字段2',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='悟空云创需求代付信息发送表';

ALTER TABLE `tbl_notify_wkyc`
ADD COLUMN `order_type` int(1) NOT NULL DEFAULT 1 COMMENT '订单类型 1-普通 2-退款' AFTER `send_method`;


alter table tbl_merchant_info add column `agent_number` varchar(20) default null comment '代理商编号' after `belong_saler` ;
alter table tbl_merchant_info add column `agent_name` varchar(50) default null comment '代理商' after `agent_number` ;

INSERT INTO `sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (2205, '费率变更审核', 2020, 7, '/manager/merchant/chackAndChangeRate', 'menuItem', 'C', '0', 'manager:merchant:chackAndChangeRate', '#', 'admin', '2020-11-17 18:06:31', '', NULL, '', 1);


create table tbl_check_and_change_rate(
  `id` int(11) primary key auto_increment,
  `merchant_id` int(11) comment '商户id',
  `merchant_name` varchar(50) not null comment '商户名',
  `merchant_code` varchar(50) not null comment '商户编号',
  `agent_name` varchar(50) not null comment '直属代理商名',
  `agent_number` varchar(20) not null comment '直属代理商编号',
  `check_state` int(4) default 0 comment '0-待审核, 1-审核通过, 2-驳回审核',
  `old_ordinary_service_rate` decimal(11,5) default null comment '原单笔普通服务费费率',
  `new_ordinary_service_rate` decimal(11,5) default null comment '新单笔普通服务费费率',
  `old_service_big_rate` decimal(11,5) default null comment '原单笔大额服务费费率',
  `new_service_big_rate` decimal(11,5) default null comment '新单笔大额服务费费率',
  `provider_id` int(11) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='费率变更表';

INSERT INTO `sys_dict_data`(`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (282, 0, '代理商编号', '111', 'Its_sales', NULL, 'default', 'Y', '0', 'admin', '2020-11-23 09:55:41', '', NULL, NULL, NULL);
ALTER TABLE `tbl_pay_request_data`
ADD COLUMN `task_record_batch_no` varchar(50) NULL COMMENT '任务批次记录批次号' AFTER `id`;

-- 2020-11-17 悟空云创新增内容 end

-- 2020-12-01 服务费补交记录状态字段添加 start
INSERT INTO `sys_dict_type`(`dict_id`, `dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (null, '补交状态', 'make_up_status', '0', 'admin', '2020-12-01 13:36:15', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_data`(`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (null, 1, '待处理', '-1', 'make_up_status', NULL, 'default', 'Y', '0', 'admin', '2020-12-01 13:37:48', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_data`(`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (null, 1, '待处理', '0', 'make_up_status', NULL, 'default', 'Y', '0', 'admin', '2020-12-01 13:38:25', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_data`(`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (null, 2, '补交成功', '1', 'make_up_status', NULL, 'success', 'Y', '0', 'admin', '2020-12-01 13:39:27', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_data`(`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (null, 3, '补交失败', '2', 'make_up_status', NULL, 'danger', 'Y', '0', 'admin', '2020-12-01 13:40:01', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_data`(`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (null, 4, '已退回', '3', 'make_up_status', NULL, 'warning', 'Y', '0', 'admin', '2020-12-01 13:40:45', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_data`(`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (null, 5, '失效', '9', 'make_up_status', NULL, 'info', 'Y', '0', 'admin', '2020-12-01 13:41:18', '', NULL, NULL, NULL);
-- 2020-12-01 服务费补交记录状态字段添加 end
-- 2020-11-26 个体工商户 start

create table tbl_personal_merchant(
  `id` int(11) not null auto_increment,
  `wechat_info_id` int(11) not null comment '个人微信关联id',
  `tax_type_id` int(11) not null comment '税务类型关联id',
  `apply_number` varchar(50) not null comment '申请编号',
  `apply_name` varchar(50) not null comment '申请人姓名',
  `id_card_number` varchar(30) not null comment '身份证号',
  `bank_no` varchar(50) not null comment '银行卡号',
  `mobile_number` varchar(20) not null comment '手机号',
  `tax_sounrce_company_id` int(11) not null comment '税源地Id',
  `tax_sounrce_company_name` varchar(255) not null comment '税源地公司名称',
  `id_card_picture_front` varchar(255) not null comment '身份证人像面存储地址',
  `id_card_picture_behind` varchar(255) not null comment '身份证国徽面存储地址',
  `id_card_picture_front_with_people` varchar(255) not null comment '手持身份证存储地址',
  `business_license` varchar(255) default null comment '营业执照存储地址',
  `business_license_register_time` varchar(50) default null comment '营业执照注册时间',
  `business_license_name` varchar(255) not null unique comment '营业执照名称',
  `business_license_type` varchar(255) default null comment '营业执照类型',
  `organization_type` varchar(255) default null comment '组织形式',
  `premises` varchar(255) not null comment '经营场所',
  `del_status` int(2) default 0 comment '0-未删除 1-已删除',
  `provider_id` int(11) DEFAULT NULL,
  `reserved_field_one` varchar(30) DEFAULT NULL,
  `reserved_field_two` varchar(30) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  unique(`apply_number` )
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='个体工商户信息';

create table tbl_order_status_info(
  `id` int(11) not null auto_increment,
  `apply_id` int(11) not null comment '申请表关联Id',
  `apply_time` varchar(50) default null comment '提交申请时间',
  `handle_time` varchar(50) default null comment '审核通过时间',
  `reject_time` varchar(50) default null comment '驳回时间',
  `complete_time` varchar(50) default null comment '执照办理完成时间',
  `order_status` int(2) default 0 comment '订单状态 -1 - 待处理 0-办理中 1-已办理 2-驳回申请 3-申请失败 4-办理完成',
  `remark` varchar(255) default null comment '驳回理由',
  `provider_id` int(11) DEFAULT NULL,
  `reserved_field_one` varchar(30) DEFAULT NULL,
  `reserved_field_two` varchar(30) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='订单状态信息';

CREATE TABLE `tbl_wechat_info` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `open_id` varchar(100) DEFAULT NULL COMMENT '微信对应的openId',
  `provider_id` int(11) DEFAULT NULL,
  `reserved_field_one` varchar(30) DEFAULT NULL,
  `reserved_field_two` varchar(30) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='个人微信信息表';


create table tbl_tax_type(
  `id` int(11) not null auto_increment,
  `tax_type_number` varchar(50) not null comment '编号',
  `tax_sounrce_company_id` int(11) not null comment '税源地Id',
  `tax_sounrce_company_name` varchar(255) not null comment '税源地公司名称',
  `business_type` varchar(255) default null comment '行业类型',
  `tax_type` varchar(255) not null comment  '税率类型',
  `invoice_content` varchar(255) default null comment '科目内容（用于发票内容）',
  `business_scope` varchar(255) default null comment '经营范围',
  `tax_person` varchar(200)  default '' comment '个体工商户税率',
  `del_status` int(2) default 0 comment '0-未删除 1-已删除',
  `provider_id` int(11) DEFAULT NULL,
  `reserved_field_one` varchar(30) DEFAULT NULL,
  `reserved_field_two` varchar(30) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='税务类型表';


CREATE TABLE `tbl_common_problem` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `problem_no` varchar(50) DEFAULT NULL COMMENT '问题编号',
  `problem` varchar(255) DEFAULT NULL COMMENT '问题',
  `answer` varchar(500) DEFAULT NULL COMMENT '解答',
  `del_status` int(1) NOT NULL DEFAULT '0' COMMENT '删除标识0-未删除1-已删除',
  `provider_id` int(11) DEFAULT NULL,
  `reserved_field_one` varchar(30) DEFAULT NULL COMMENT '预留字段1',
  `reserved_field_two` varchar(30) DEFAULT NULL COMMENT '预留字段2',
  `reserved_field_three` varchar(30) DEFAULT NULL COMMENT '预留字段3',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='常见问题表';

CREATE TABLE `tbl_personal_mer_protocol` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `cus_esign_id` int(11) NOT NULL COMMENT '签约员工id',
  `name` varchar(20) NOT NULL COMMENT '姓名',
  `con_info_id` int(11) DEFAULT NULL COMMENT '签约文档id',
  `file_name` varchar(30) DEFAULT NULL COMMENT '签约文档名称',
  `sign_time` varchar(20) DEFAULT NULL COMMENT '签约时间',
  `sign_status` int(4) NOT NULL DEFAULT '0' COMMENT '签约状态 0初始化 2:签署完成 3:失败 4:拒签',
  `comment` varchar(100) DEFAULT NULL COMMENT '失败原因',
  `espire_time` timestamp NULL DEFAULT NULL COMMENT '签约到期时间',
  `expire_status` int(2) NOT NULL DEFAULT '0' COMMENT '过期状态0-未过期 1-过期',
  `esign_flow_id` int(11) NOT NULL DEFAULT '0' COMMENT '对应流程表id',
  `tax_source_id` int(11) NOT NULL COMMENT '所属税源地id',
  `sign_serial_num` varchar(30) DEFAULT NULL COMMENT '签约流水号',
	`apply_number` varchar(50) DEFAULT NULL COMMENT '对应个体工商户的申请编号',
  `provider_id` int(11) DEFAULT NULL,
  `reserved_field_one` varchar(30) DEFAULT NULL,
  `reserved_field_two` varchar(30) DEFAULT NULL,
  `reserved_field_three` varchar(30) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='个体工商户协议表';

-- 测试数据，上显示修改为正式
INSERT INTO `tbl_esign_template`(`id`, `template_name`, `template_id`, `tax_source_id`, `provider_id`, `reserved_field_one`, `reserved_field_two`, `create_time`, `update_time`) VALUES (8, '友加云商——C端个体户间商事服务协议', 'c75e0e66b2d945c5bf4ec55cde677f06', 0, 1, NULL, NULL, '2020-11-27 16:58:12', '2020-11-27 16:58:12');
INSERT INTO `tbl_template_component`(`id`, `component_name`, `component_id`, `esign_template_id`, `template_id`, `provider_id`, `reserved_field_one`, `reserved_field_two`, `create_time`, `update_time`) VALUES (33, '身份证号', 'aa9f82fc73fd428da2113e88c914b631', 8, 'c75e0e66b2d945c5bf4ec55cde677f06', 1, NULL, NULL, '2020-11-27 17:02:24', '2020-11-27 17:02:24');
INSERT INTO `tbl_template_component`(`id`, `component_name`, `component_id`, `esign_template_id`, `template_id`, `provider_id`, `reserved_field_one`, `reserved_field_two`, `create_time`, `update_time`) VALUES (34, '税源地公司全称', '23333e97821c4326939d3255820877a2', 8, 'c75e0e66b2d945c5bf4ec55cde677f06', 1, NULL, NULL, '2020-11-27 17:03:15', '2020-11-27 17:03:15');

-- 个体户菜单
INSERT INTO `sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (2210, '个体户管理', 0, 7, '#', 'menuItem', 'M', '0', NULL, 'fa fa-user-circle', 'admin', '2020-11-30 10:01:50', '', NULL, '', 1);
INSERT INTO `sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (2211, '常见问题', 2210, 6, '/business/commonProblem', 'menuItem', 'C', '0', 'business:commonProblem:view', '#', 'admin', '2020-11-30 10:02:58', 'admin', '2020-11-30 10:07:37', '', 1);
INSERT INTO `sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (2212, '新增保存常见问题', 2211, 1, '/business/commonProblem/add', 'menuItem', 'F', '0', 'business:commonProblem:add', '#', 'admin', '2020-11-30 10:26:40', 'admin', '2020-11-30 10:54:38', '', 1);
INSERT INTO `sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (2213, '删除常见问题', 2211, 2, '#', 'menuItem', 'F', '0', 'business:commonProblem:remove', '#', 'admin', '2020-11-30 10:53:54', 'admin', '2020-11-30 10:54:28', '', 1);
INSERT INTO `sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (2214, '修改保存常见问题', 2211, 3, '#', 'menuItem', 'F', '0', 'business:commonProblem:edit', '#', 'admin', '2020-11-30 10:54:15', 'admin', '2020-11-30 10:54:57', '', 1);

INSERT INTO `sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (null, '入网申请', 2206, 1, '/business/personalMerchant/personalMerchantListView', 'menuItem', 'C', '0', 'business:personalMerchant:personalMerchantListView', '#', 'admin', '2020-12-01 09:38:14', '', NULL, '', 1);
INSERT INTO `sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (null, '营业执照办理', 2206, 2, '/business/personalMerchant/applyBusinessLicenseInfo', 'menuItem', 'C', '0', 'business:personalMerchant:applyBusinessLicenseInfo', '#', 'admin', '2020-12-01 09:39:54', '', NULL, '', 1);
INSERT INTO `sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (null, '个体户执照', 2206, 3, '/business/personalMerchant/personalLicenseList', 'menuItem', 'C', '0', 'business:personalMerchant:personalLicenseList', '#', 'admin', '2020-12-01 09:41:28', '', NULL, '', 1);
INSERT INTO `sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (null, '税务类型', 2206, 4, '/business/personalMerchant/taxTypeList', 'menuItem', 'C', '0', 'business:personalMerchant:taxTypeList', '#', 'admin', '2020-12-01 09:42:22', '', NULL, '', 1);

-- 入网申请
INSERT INTO `sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (null, '查询入网申请列表', , 1, '#', 'menuItem', 'F', '0', 'business:personalMerchant:personalMerchantList', '#', 'admin', '2020-12-03 10:11:05', '', NULL, '', 1);
-- 税务类型
INSERT INTO `sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (null, '新增税务类型', , 1, '#', 'menuItem', 'F', '0', 'business:personalMerchant:addTaxTypeInfo', '#', 'admin', '2020-12-03 14:45:17', '', NULL, '', 1);
INSERT INTO `sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (null, '修改税务类型', , 2, '#', 'menuItem', 'F', '0', 'business:personalMerchant:updateTaxTypeInfo', '#', 'admin', '2020-12-03 14:45:51', '', NULL, '', 1);
INSERT INTO `sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (null, '批量删除税务类型', , 3, '#', 'menuItem', 'F', '0', 'business:personalMerchant:batchDeleteTaxTypeInfo', '#', 'admin', '2020-12-03 14:46:25', '', NULL, '', 1);

-- 菜单 （上线时注意线上库的主键）

CREATE TABLE `tbl_per_mer_apply_schedule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `per_mer_id` int(11) NOT NULL COMMENT '申请id',
  `apply_number` varchar(50) NOT NULL COMMENT '申请编号',
  `submit_time` varchar(255) NOT NULL COMMENT '时间',
  `action` varchar(255) NOT NULL COMMENT '动作(写死)',
  `del_status` int(2) DEFAULT '0' COMMENT '第几个步骤 0-未知 1-提交资料审核 2-办理营业执照',
  `provider_id` int(11) DEFAULT NULL,
  `reserved_field_one` varchar(30) DEFAULT NULL,
  `reserved_field_two` varchar(30) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='个体工商户申请流程日志';

ALTER TABLE `tbl_per_mer_apply_schedule`
CHANGE COLUMN `del_status` `type` int(1) NULL DEFAULT 0 COMMENT '第几个步骤 0-未知 1-提交资料审核 2-办理营业执照' AFTER `action`,
MODIFY COLUMN `submit_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '时间' AFTER `apply_number`,
MODIFY COLUMN `action` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '动作(写死)' AFTER `submit_time`;

ALTER TABLE `tbl_tax_type`
CHANGE COLUMN `tax_sounrce_commpany_id` `tax_sounrce_company_id` int(11) NOT NULL COMMENT '税源地Id' AFTER `id`;

alter table tbl_personal_merchant change column `tax_sounrce_commpany_id` `tax_sounrce_company_id` int(11) NOT NULL COMMENT '税源地Id' AFTER `id`;

INSERT INTO `sys_dict_type`(`dict_id`, `dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (null, '审核状态', 'check_status', '0', 'admin', '2020-12-03 18:17:43', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_data`(`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (null, 1, '待审核', '0', 'check_status', NULL, 'primary', 'Y', '0', 'admin', '2020-12-03 18:18:51', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_data`(`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (null, 2, '已驳回', '2', 'check_status', NULL, 'danger', 'Y', '0', 'admin', '2020-12-03 18:19:12', '', NULL, NULL, NULL);

ALTER TABLE `tbl_per_mer_apply_schedule`
MODIFY COLUMN `type` int(1) NULL DEFAULT 0 COMMENT '第几个步骤 0-未知 1-提交资料审核 2-办理营业执照 3-办理完成' AFTER `action`;

update tbl_tax_sounrce_company set tax_sounrce_company_name = '泰宁县博瑞企业管理有限公司' where id = 3;
update tbl_tax_sounrce_company set tax_sounrce_company_name = '海南浚聚科技有限公司' where id = 4;
update tbl_tax_sounrce_company set tax_sounrce_company_name = '江西启博信息科技有限公司' where id = 5;

ALTER TABLE `tbl_online_customer_info`
ADD INDEX `customer_id_ref`(`customer_id`) USING BTREE;

-- 2020-11-26 个体工商户 end

--2020-12-03 服务费退回、补偿记录添加状态字段 start
ALTER TABLE `tbl_return_charge_order`
MODIFY COLUMN `status` int(1) NULL DEFAULT NULL COMMENT '退还状态 -1 初始化 0 进行中 1 成功 2失败 ' AFTER `order_serial_num`;
INSERT INTO `sys_dict_type`(`dict_id`, `dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (null, '退回状态', 'return_charge_status', '0', 'admin', '2020-12-03 10:03:03', '', NULL, '退回状态 -1 初始化 0进行中 1成功 2失败', NULL);
INSERT INTO `sys_dict_data`(`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (null, 1, '初始化', '-1', 'return_charge_status', NULL, 'default', 'Y', '0', 'admin', '2020-12-03 10:04:32', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_data`(`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (null, 2, '进行中', '0', 'return_charge_status', NULL, 'primary', 'Y', '0', 'admin', '2020-12-03 10:05:07', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_data`(`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (null, 3, '成功', '1', 'return_charge_status', '', 'success', 'Y', '0', 'admin', '2020-12-03 10:05:25', 'admin', '2020-12-03 10:05:53', '', NULL);
INSERT INTO `sys_dict_data`(`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (null, 4, '失败', '2', 'return_charge_status', NULL, 'danger', 'Y', '0', 'admin', '2020-12-03 10:06:12', '', NULL, NULL, NULL);
--2020-12-03 服务费退回、补偿记录添加状态字段 end

-- 2020-12-11 个体工商户2 start
CREATE TABLE `tbl_work_order` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `work_order_serial_number` varchar(50) DEFAULT NULL COMMENT '工单编号',
  `apply_number` varchar(50) DEFAULT NULL COMMENT '入网申请编号',
  `name` varchar(30) DEFAULT NULL COMMENT '名字',
  `certificate_no` varchar(18) DEFAULT NULL COMMENT '身份证号',
  `title` varchar(40) DEFAULT NULL COMMENT '标题',
  `content` varchar(200) DEFAULT NULL COMMENT '内容',
  `status` int(1) DEFAULT NULL COMMENT '0 已发布，1已反馈，2已处理，3关闭工单',
  `txt` varchar(200) DEFAULT NULL COMMENT '文本',
  `pdf_address` varchar(255) DEFAULT NULL COMMENT 'pdf地址',
  `img_address` varchar(255) DEFAULT NULL COMMENT '图片地址',
  `video_address` varchar(255) DEFAULT NULL COMMENT '视频地址',
  `publish_time` datetime DEFAULT NULL COMMENT '发布时间',
  `feedback_time` datetime DEFAULT NULL COMMENT '反馈时间',
  `del_status` int(1) DEFAULT NULL COMMENT '0未删除，1已删除',
  `tax_source_id` int(11) DEFAULT NULL COMMENT '税源地id',
  `provider_id` int(11) NOT NULL,
  `reserved_field_one` varchar(255) DEFAULT NULL,
  `reserved_field_two` varchar(255) DEFAULT NULL,
  `reserved_field_three` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='工单表';

INSERT INTO `sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (null, '补充工单', pid, 7, '/business/personalMerchant/workOrderList', 'menuItem', 'C', '0', 'business:personalMerchant:workOrderList', '#', 'admin', '2020-12-11 10:57:38', '', NULL, '', 1);
INSERT INTO `sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (null, '发布', pid, 1, '/business/personalMerchant/workOrderPublish', 'menuItem', 'C', '1', 'business:personalMerchant:workOrderPublish', '#', 'admin', '2020-12-11 11:05:40', 'admin', '2020-12-11 11:07:12', '', 1);
INSERT INTO `sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (null, '工单详情', pid, 2, '/business/personalMerchant/workOrderDetail', 'menuItem', 'C', '1', 'business:personalMerchant:workOrderDetail', '#', 'admin', '2020-12-11 11:08:01', '', NULL, '', 1);

ALTER TABLE`tbl_order_status_info`
MODIFY COLUMN `order_status` int(2) NULL DEFAULT 0 COMMENT '订单状态 -1 - 待处理 0-办理中 1-已办理 2-驳回申请 3-申请失败 4-办理完成 5-提交工单' AFTER `complete_time`;

INSERT INTO `sys_dict_type`(`dict_id`, `dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (null, '工单状态', 'work_order_status', '0', 'admin', '2020-12-11 17:07:34', '', NULL, '2已处理 1待处理 3关闭 0待反馈', NULL);
INSERT INTO `sys_dict_data`(`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (null, 3, '已处理', '2', 'work_order_status', NULL, 'primary', 'Y', '0', 'admin', '2020-12-11 17:08:03', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_data`(`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (null, 2, '待处理', '1', 'work_order_status', NULL, 'success', 'Y', '0', 'admin', '2020-12-11 17:08:20', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_data`(`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (null, 4, '关闭', '3', 'work_order_status', NULL, 'info', 'Y', '0', 'admin', '2020-12-11 17:08:41', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_data`(`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (null, 1, '待反馈', '0', 'work_order_status', NULL, 'warning', 'Y', '0', 'admin', '2020-12-11 17:09:02', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_data`(`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (null, 1, '工单', '5', 'check_status', '', 'primary', 'Y', '0', 'admin', '2020-12-15 11:05:10', 'admin', '2020-12-15 11:24:05', '', NULL);


-- 2020-12-11 个体工商户2 end
-- 2020年12月15日 交易记录表添加备注字段 start
ALTER TABLE `tbl_trx_order`
ADD COLUMN `memo` varchar(64) NULL COMMENT '备注信息，支付宝打款非空，显示为订单商品说明' AFTER `task_record_batch_no`;
-- 2020年12月15日 交易记录表添加备注字段 end

-- 2020-12-11 bug修改 start

ALTER  TABLE  `tbl_cus_link_mer_info` ADD INDEX cus_esign_id_ (`cus_esign_id`) COMMENT '员工签约id索引';

-- 上线注意
UPDATE `sys_dict_data` SET `dict_sort` = 1, `dict_label` = '待处理', `dict_value` = '0', `dict_type` = 'make_up_status', `css_class` = '', `list_class` = 'primary', `is_default` = 'N', `status` = '0', `create_by` = 'admin', `create_time` = '2020-12-01 13:38:25', `update_by` = 'admin', `update_time` = '2020-12-14 16:54:24', `remark` = '', `provider_id` = NULL WHERE `dict_code` = 284;

INSERT INTO `sys_dict_data`(`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (null, 3, '退款', '4', 'trx_type', NULL, 'warning', 'N', '0', 'admin', '2020-12-15 10:01:24', '', NULL, NULL, NULL);

INSERT INTO `sys_dict_data`(`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (null, 4, '打款', '2', 'trx_type', NULL, 'success', 'Y', '0', 'admin', '2020-12-15 11:44:18', '', NULL, NULL, NULL);

INSERT INTO `sys_dict_data`(`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (null, 5, '退款', '3', 'trx_type', NULL, 'warning', 'Y', '0', 'admin', '2020-12-15 11:44:40', '', NULL, NULL, NULL);

alter table tbl_merchant_account_detail modify column `payment_type` int(2) DEFAULT NULL COMMENT '0-发放佣金 1-充值  2-大额补交服务费 3-大额退还服务费 4-退回';

-- 上线注意
INSERT INTO `sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (null, '跳转到退款界面', pid, 3, '#', 'menuItem', 'F', '0', 'manager:account:merchantDrawback', '#', 'admin', '2020-12-21 16:10:24', 'admin', '2020-12-21 16:10:37', '', 1);

INSERT INTO `sys_dict_data`(`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (null, 5, '取款', '5', 'trx_type', NULL, 'info', 'N', '0', 'admin', '2020-12-22 11:27:53', '', NULL, NULL, NULL);

-- 2020-12-11 bug修改 end
