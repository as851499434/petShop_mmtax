ALTER TABLE `mmtax`.`tbl_merchant_info`
  ADD COLUMN `belong_saler` int(11) NULL COMMENT '所属销售' AFTER `inviter`;
-- 2020-03-26
ALTER TABLE `mmtax`.`tbl_trx_order`
    ADD COLUMN `order_info` varchar(500) NULL COMMENT '天津渠道打款时保存的账号和供应商id，挂起订单再次打款时直接从该字段取' AFTER `comment`;


--2020-05-20
CREATE TABLE `tbl_online_account_config` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `tax_sounrce_id` int(11) NOT NULL COMMENT '税源地id',
  `tax_sounrce_company_name` varchar(255) DEFAULT NULL COMMENT '税源地公司名称',
  `account_prefix` varchar(20) DEFAULT NULL COMMENT '充值账号前缀',
  `partner_id` varchar(30) DEFAULT NULL COMMENT '合作id',
  `white_channel_code` varchar(30) DEFAULT NULL COMMENT '渠道代码',
  `wallet_public_key` varchar(500) DEFAULT NULL COMMENT 'rsa公钥',
  `key_store_name` varchar(20) DEFAULT NULL COMMENT '加载证书名称',
  `sftp_ip` varchar(20) DEFAULT NULL COMMENT '调用sftp的IP地址',
  `sftp_port` varchar(10) DEFAULT NULL COMMENT '调用sftp的端口',
  `file_directory` varchar(50) DEFAULT NULL COMMENT 'sfpt文件目录',
  `sftp_user` varchar(20) DEFAULT NULL COMMENT 'sftp用户名',
  `sftp_pass` varchar(30) DEFAULT NULL COMMENT 'sftp密码',
  `pid` varchar(30) DEFAULT NULL COMMENT '相关pid',
  `charge_cost_mer_id` int(11) DEFAULT NULL COMMENT '服务费成本收取商户id',
  `charge_service_cost_rate` decimal(11,3) DEFAULT NULL COMMENT '服务费成本收取比例(不加%)',
  `charge_interest_mer_id` int(11) DEFAULT NULL COMMENT '服务费利率收取商户id',
  `provider_id` int(11) DEFAULT NULL,
  `reserved_field_one` varchar(30) DEFAULT NULL COMMENT '预留字段1',
  `reserved_field_two` varchar(30) DEFAULT NULL COMMENT '预留字段2',
  `reserved_field_three` varchar(30) DEFAULT NULL COMMENT '预留字段3',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='网商渠道税源地配置表';

CREATE TABLE `tbl_merchant_tax_source_correlation` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `merchant_id` int(11) NOT NULL COMMENT '商户id',
  `tax_sounrce_id` int(11) NOT NULL COMMENT '税源地id',
  `is_delete` int(4) NOT NULL DEFAULT '0' COMMENT '是否删除 1是 0否',
  `provider_id` int(11) NOT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商户税源地关联表';

INSERT INTO `tbl_tax_sounrce_company`(`id`, `merchant_no`, `secret_key`, `public_key`, `tax_sounrce_company_name`, `channel`, `provider_id`, `create_time`, `update_time`) VALUES (NULL, NULL, NULL, NULL, '海南浚聚', 'ONLINE', 1, '2020-05-20 17:54:51', NULL);

INSERT INTO `tbl_online_account_config`(`id`, `tax_sounrce_id`, `tax_sounrce_company_name`, `account_prefix`, `partner_id`, `white_channel_code`, `wallet_public_key`, `key_store_name`, `sftp_ip`, `sftp_port`, `file_directory`, `sftp_user`, `sftp_pass`, `pid`, `charge_cost_mer_id`, `charge_service_cost_rate`, `charge_interest_mer_id`, `provider_id`, `reserved_field_one`, `reserved_field_two`, `reserved_field_three`, `create_time`, `update_time`) VALUES (NULL, 3, '泰宁博瑞', '58027604410', '200034688788', 'MYBANK00347', 'MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCqtUCsiOf67Gln0VmKDTp6W7dBVUBcgD/ZNpbXptcLRgCSgWG7S2pocIBbBk1kQyDtFzMWIMQXpzM4Cjt05FY0fQDrVLOUcrS3euJy3JT4Z1nj0WHyF6uMZAwB1R0VeyKBKM3vVPGZWhvPT/C57WAiZ7Rbt3i2PPcxDn0yXhBUaQIDAQAB', 'testKeyStore', '120.77.249.10', '12222', '/opt/sftp/226610000425664524252', 'fhaijurvxq', 'l2@5(PPLh@Qw', '226610000425664524252', 46, 0.055, 47, 1, NULL, NULL, NULL, NULL, NULL);

INSERT INTO `tbl_online_account_config`(`id`, `tax_sounrce_id`, `tax_sounrce_company_name`, `account_prefix`, `partner_id`, `white_channel_code`, `wallet_public_key`, `key_store_name`, `sftp_ip`, `sftp_port`, `file_directory`, `sftp_user`, `sftp_pass`, `pid`, `charge_cost_mer_id`, `charge_service_cost_rate`, `charge_interest_mer_id`, `provider_id`, `reserved_field_one`, `reserved_field_two`, `reserved_field_three`, `create_time`, `update_time`) VALUES (NULL, 4, '海南浚聚', '58741330383', '200034988449', 'MYBANK00358', 'MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCqtUCsiOf67Gln0VmKDTp6W7dBVUBcgD/ZNpbXptcLRgCSgWG7S2pocIBbBk1kQyDtFzMWIMQXpzM4Cjt05FY0fQDrVLOUcrS3euJy3JT4Z1nj0WHyF6uMZAwB1R0VeyKBKM3vVPGZWhvPT/C57WAiZ7Rbt3i2PPcxDn0yXhBUaQIDAQAB', 'testKeyStore2', '120.77.249.10', '12222', '/opt/sftp/226610000430780600885', 'btrgthbvbg', 'rE3dt^FkdeEl', '226610000430780600885', NULL, 0.055, NULL, 1, NULL, NULL, NULL, NULL ,NULL);

2020-6-1
CREATE TABLE `tbl_notify_send_again` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `merchant_id` int(11) NOT NULL COMMENT '商户id',
  `notify_type` int(4) NOT NULL COMMENT '通知类型 1代付 2充值',
  `notify_status` int(4) NOT NULL DEFAULT '2' COMMENT '通知状态 1成功 2失败',
  `send_method` int(4) DEFAULT '1' COMMENT '发送方式 1post-json 2get',
  `notify_content` varchar(500) DEFAULT NULL COMMENT '通知内容',
  `fail_num` int(4) DEFAULT NULL COMMENT '失败次数',
  `last_send_time` timestamp NULL DEFAULT NULL COMMENT '上次发送的时间',
  `provider_id` int(11) DEFAULT NULL,
  `reserved_field_one` varchar(50) DEFAULT NULL COMMENT '预留字段1',
  `reserved_field_two` varchar(50) DEFAULT NULL COMMENT '预留字段2',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='异步通知重发送记录表';

-- 2020-6-29

ALTER TABLE `mmtax`.`tbl_trx_order`
ADD COLUMN `async_status` int(1) NULL DEFAULT 0 COMMENT '后续异步动作是否处理 0否 1是' AFTER `subject_conscription`;

CREATE TABLE `tbl_tax_withdraw_account` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `charge_merchant_id` int(11) NOT NULL COMMENT '对应merchantId',
  `company_name` varchar(50) DEFAULT NULL COMMENT '公司账户名称',
  `tax_source_company_id` int(11) DEFAULT NULL COMMENT '税源地公司id',
  `tax_source_name` varchar(50) DEFAULT NULL COMMENT '税源地名称',
  `tax_id_number` varchar(50) DEFAULT NULL COMMENT '税号',
  `company_address` varchar(100) DEFAULT NULL COMMENT '地址',
  `company_tel` varchar(20) DEFAULT NULL COMMENT '电话',
  `bank_public` varchar(50) DEFAULT NULL COMMENT '对公银行名称',
  `bank_name_public` varchar(50) NOT NULL COMMENT '对公开户行',
  `bank_account_public` varchar(50) DEFAULT NULL COMMENT '对公银行户名',
  `bank_card_public` varchar(50) NOT NULL COMMENT '对公银行账号',
  `bank_private` varchar(50) DEFAULT NULL COMMENT '对私银行名称',
  `bank_name_private` varchar(50) DEFAULT NULL COMMENT '对私开户行',
  `bank_account_private` varchar(50) DEFAULT NULL COMMENT '对私银行户名',
  `bank_card_private` varchar(50) DEFAULT NULL COMMENT '对私银行账号',
  `mobile_private` varchar(20) DEFAULT NULL COMMENT '对私银行卡户主手机号',
  `id_card_private` varchar(50) DEFAULT NULL COMMENT '对私身份证号',
  `allow_withdraw` int(4) NOT NULL DEFAULT '0' COMMENT '是否允许提现 0否 1是',
  `public_or_private` int(4) DEFAULT '0' COMMENT '对公或对私 0对公 1对私',
  `reserved_field_one` varchar(200) DEFAULT NULL COMMENT '对公银行分支行号',
  `reserved_field_two` varchar(200) DEFAULT NULL COMMENT '预留字段二',
  `reserved_field_three` varchar(200) DEFAULT NULL COMMENT '预留字段三',
  `provider_id` int(11) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='税源地提现账户表';

-- 需求0706
CREATE TABLE `tbl_customer_info` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `customer_no` varchar(30) NOT NULL COMMENT '员工编号',
  `member_name` varchar(20) NOT NULL COMMENT '昵称',
  `real_name` varchar(20) NOT NULL COMMENT '真名',
  `certificate_type` varchar(10) NOT NULL COMMENT '证件类型,取数据字典',
  `certificate_no` varchar(50) NOT NULL COMMENT '证件号码',
  `mobile` varchar(20) DEFAULT NULL COMMENT '手机号',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `merchant_id` int(11) NOT NULL COMMENT '所属商户id',
  `verify_status` int(4) NOT NULL DEFAULT '0' COMMENT '是否认证 0否 1是（已认证通过）',
  `effective` int(4) NOT NULL DEFAULT '0' COMMENT '是否有效 0否 1是（成功开户即为有效）',
  `provider_id` int(11) DEFAULT NULL,
  `reserved_field_one` varchar(30) DEFAULT NULL,
  `reserved_field_two` varchar(30) DEFAULT NULL,
  `reserved_field_three` varchar(30) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `customer_no_index` (`customer_no`) USING BTREE COMMENT '唯一员工编号索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='员工信息表';

DROP TABLE if EXISTS tbl_online_customer_info;
CREATE TABLE `tbl_online_customer_info` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `customer_id` int(11) NOT NULL COMMENT '员工id',
  `customer_no` varchar(50) NOT NULL COMMENT '对应uid(员工编码)',
  `sub_account_no` varchar(15) DEFAULT NULL COMMENT '智能识别码',
  `tax_source_id` int(11) NOT NULL COMMENT '税源地id',
  `provider_id` int(11) DEFAULT NULL,
  `reserved_field_one` varchar(30) DEFAULT NULL COMMENT '预留字段1',
  `reserved_field_two` varchar(30) DEFAULT NULL COMMENT '预留字段2',
  `reserved_field_three` varchar(30) DEFAULT NULL COMMENT '预留字段3',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='网商关联员工信息';

DROP TABLE if EXISTS tbl_transfer_order;
CREATE TABLE `tbl_transfer_order` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `merchant_name` varchar(50) DEFAULT NULL COMMENT '商户名称',
  `amount` decimal(11,2) DEFAULT NULL COMMENT '打款金额',
  `tax_rate` decimal(11,2) DEFAULT NULL COMMENT '税率',
  `charge` decimal(11,2) DEFAULT NULL COMMENT '手续费',
  `actual_amount` decimal(11,2) DEFAULT NULL COMMENT '转账总金额',
  `transfer_serial_num` varchar(100) DEFAULT NULL COMMENT '转账流水号(可查网商)',
  `order_serial_num` varchar(100) DEFAULT NULL COMMENT '关联订单号',
  `batch_no` varchar(50) DEFAULT NULL COMMENT '批次号',
  `customer_name` varchar(255) DEFAULT NULL COMMENT '员工姓名',
  `status` int(1) DEFAULT NULL COMMENT '-1-初始化0-进行中1-已转账2-转账失败',
  `batch_payment_record_id` int(11) DEFAULT NULL COMMENT '批量打款记录id',
  `payment_channel` varchar(10) DEFAULT NULL COMMENT 'BANK-银行ALIPAY-支付宝WECHAT-微信',
  `merchant_id` int(11) DEFAULT NULL COMMENT '商户id',
  `customer_id` int(11) DEFAULT NULL COMMENT '员工id',
  `comment` varchar(255) DEFAULT NULL COMMENT '备注',
  `async_status` int(1) DEFAULT '0' COMMENT '后续异步动作是否处理 0否 1是',
  `provider_id` int(11) DEFAULT NULL,
  `reserved_field_one` varchar(30) DEFAULT NULL COMMENT '预留字段1',
  `reserved_field_two` varchar(30) DEFAULT NULL COMMENT '预留字段2',
  `reserved_field_three` varchar(30) DEFAULT NULL COMMENT '预留字段3',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `transfer_serial_num_index` (`transfer_serial_num`) USING BTREE COMMENT '唯一转账号索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商户转账员工记录表';

DROP TABLE if EXISTS tbl_customer_account_detail;
CREATE TABLE `tbl_customer_account_detail` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `payment_amount_before` decimal(15,2) DEFAULT NULL COMMENT '交易之前总余额',
  `payment_amount_after` decimal(15,2) DEFAULT NULL COMMENT '交易之后总余额',
  `payment_frozen_amount_before` decimal(15,2) DEFAULT NULL COMMENT '交易之前冻结金额',
  `payment_frozen_amount_after` decimal(15,2) DEFAULT NULL COMMENT '交易之后冻结金额',
  `payment_usable_amount_before` decimal(15,2) DEFAULT NULL COMMENT '交易之前可用余额',
  `payment_usable_amount_after` decimal(15,2) DEFAULT NULL COMMENT '交易之后可用余额',
  `status` int(2) DEFAULT NULL COMMENT '变动状态 0-失败 1-成功',
  `type` int(2) DEFAULT NULL COMMENT '变动类型 0-出账冻结 1-入账冻结 2出账解冻 3入账解冻',
  `payment_amount` decimal(15,2) DEFAULT NULL COMMENT '变动金额',
  `order_serial_num` varchar(50) DEFAULT NULL COMMENT '订单表流水号',
  `customer_id` int(11) DEFAULT NULL COMMENT '员工id',
  `provider_id` int(11) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='员工账户余额变动明细';

DROP TABLE if EXISTS tbl_customer_account;
CREATE TABLE `tbl_customer_account` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `customer_id` int(11) DEFAULT NULL COMMENT '员工id',
  `amount` decimal(20,2) DEFAULT NULL COMMENT '总余额',
  `frozen_amount` decimal(20,2) DEFAULT NULL COMMENT '冻结金额',
  `usable_amount` decimal(20,2) DEFAULT NULL COMMENT '可用余额',
  `status` varchar(10) DEFAULT 'ACTIVE' COMMENT '状态 ACTIVE-激活状态，正常使用FROZED-冻结（不可提现）CANCEL-注销',
  `version` int(11) DEFAULT '1' COMMENT '版本号',
  `provider_id` int(11) DEFAULT NULL,
  `reserved_field_one` varchar(30) DEFAULT NULL COMMENT '预留字段1',
  `reserved_field_two` varchar(30) DEFAULT NULL COMMENT '预留字段2',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='员工账户';

DROP TABLE if EXISTS tbl_customer_alipay_info;
CREATE TABLE `tbl_customer_alipay_info` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `customer_id` int(11) NOT NULL COMMENT '员工id',
  `customer_no` varchar(20) NOT NULL COMMENT '员工编号',
  `account_no` varchar(30) DEFAULT NULL COMMENT '支付宝账号',
  `account_name` varchar(20) DEFAULT NULL COMMENT '支付宝对应的真实姓名',
  `certificate_no` varchar(30) NOT NULL COMMENT '身份证',
  `mobile_no` varchar(20) DEFAULT NULL COMMENT '预留手机号',
  `verify_status` int(4) DEFAULT NULL COMMENT '是否认证 0否 1是',
  `bind_status` int(4) DEFAULT NULL COMMENT '是否绑定网商 0否 1是',
  `bank_id` varchar(32) DEFAULT NULL COMMENT '网商绑定id',
  `provider_id` int(11) DEFAULT NULL,
  `reserved_field_one` varchar(30) DEFAULT NULL,
  `reserved_field_two` varchar(30) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='员工绑定支付宝信息表';

DROP TABLE if EXISTS tbl_customer_bankcard_info;
CREATE TABLE `tbl_customer_bankcard_info` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `customer_id` int(11) NOT NULL COMMENT '员工id',
  `customer_no` varchar(20) NOT NULL COMMENT '员工编号',
  `bank_name` varchar(30) DEFAULT NULL COMMENT '银行全称',
  `card_type` varchar(5) DEFAULT NULL COMMENT '卡类型 DC-借记卡 CC-信用卡',
  `bank_account_no` varchar(30) DEFAULT NULL COMMENT '银行账号/卡号',
  `account_name` varchar(20) DEFAULT NULL COMMENT '银行开户名',
  `certificate_type` varchar(10) NOT NULL COMMENT '证件类型,取数据字典',
  `certificate_no` varchar(30) DEFAULT NULL COMMENT '证件号码',
  `reserved_mobile` varchar(20) DEFAULT NULL COMMENT '银行预留手机号',
  `verify_type` int(4) DEFAULT NULL COMMENT '认证类型 3三要素 4四要素',
  `verify_status` int(4) DEFAULT NULL COMMENT '是否认证 0否 1是',
  `bind_status` int(4) DEFAULT NULL COMMENT '是否绑定网商 0否 1是',
  `bank_id` varchar(32) DEFAULT NULL COMMENT '网商绑卡id',
  `provider_id` int(11) DEFAULT NULL,
  `reserved_field_one` varchar(30) DEFAULT NULL,
  `reserved_field_two` varchar(30) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='员工绑定银行卡信息表';

ALTER TABLE `tbl_trx_order`
ADD COLUMN `bank_id` varchar(20) NULL COMMENT '网商提现银行卡或支付宝id' AFTER `payment_channel`,
ADD COLUMN `customer_id` int(11) NULL COMMENT '员工id' AFTER `merchant_id`;

-- 字典 绑定状态数据 start
INSERT INTO `sys_dict_type` (
	`dict_id`,
	`dict_name`,
	`dict_type`,
	`status`,
	`create_by`,
	`create_time`,
	`update_by`,
	`update_time`,
	`remark`,
	`provider_id`
)
VALUES
	(
		'130',
		'绑定状态',
		'bind_status',
		'0',
		'admin',
		'2020-07-13 10:30:26',
		'',
		NULL,
		'员工银行卡，支付宝绑定状态',
		NULL
	);

INSERT INTO `sys_dict_data` (
	`dict_code`,
	`dict_sort`,
	`dict_label`,
	`dict_value`,
	`dict_type`,
	`css_class`,
	`list_class`,
	`is_default`,
	`status`,
	`create_by`,
	`create_time`,
	`update_by`,
	`update_time`,
	`remark`,
	`provider_id`
)
VALUES
	(
		'227',
		'2',
		'未绑定',
		'N',
		'bind_status',
		'',
		'success',
		'Y',
		'0',
		'admin',
		'2020-07-13 10:34:26',
		'admin',
		'2020-07-13 10:43:33',
		'',
		NULL
	);

INSERT INTO `sys_dict_data` (
	`dict_code`,
	`dict_sort`,
	`dict_label`,
	`dict_value`,
	`dict_type`,
	`css_class`,
	`list_class`,
	`is_default`,
	`status`,
	`create_by`,
	`create_time`,
	`update_by`,
	`update_time`,
	`remark`,
	`provider_id`
)
VALUES
	(
		'226',
		'1',
		'已绑定',
		'Y',
		'bind_status',
		'',
		'info',
		'Y',
		'0',
		'admin',
		'2020-07-13 10:34:05',
		'admin',
		'2020-07-13 10:43:38',
		'',
		NULL
	);


INSERT INTO `sys_dict_type`(`dict_id`, `dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (131, '商户打款状态', 'transfer_status', '0', 'admin', now(), '', NULL, 'transferOrder表的记录状态', NULL);

INSERT INTO `sys_dict_data`(`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (null, 1, '进行中', '0', 'transfer_status', '', 'warning', 'N', '0', 'admin', now(), 'admin', now(), '', NULL);
INSERT INTO `sys_dict_data`(`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (null, 1, '已打款', '1', 'transfer_status', '', 'success', 'N', '0', 'admin', now(), 'admin', now(), '', NULL);
INSERT INTO `sys_dict_data`(`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (null, 2, '打款挂起', '2', 'transfer_status', '', 'warning', 'N', '0', 'admin', now(), 'admin', now(), '', NULL);
INSERT INTO `sys_dict_data`(`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (null, 3, '初始化', '-1', 'transfer_status', '', 'warning', 'N', '0', 'admin', now(), 'admin', now(), '', NULL);

INSERT INTO `sys_dict_data`(`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (null, 5, '待处理', '-1', 'trxorder_status', NULL, 'info', 'Y', '0', 'admin', '2020-07-15 11:50:25', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_data`(`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (null, 5, '失败', '4', 'trxorder_status', NULL, 'info', 'Y', '0', 'admin', '2020-07-14 14:32:09', '', NULL, '订单失败', NULL);

-- 字典 绑定状态数据 end

INSERT INTO `sys_config`(`config_id`, `config_name`, `config_key`, `config_value`, `config_type`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (7, '注册个人账户时是否实名认证', 'verify_realname', 'N', 'Y', 'admin', '2020-07-14 16:47:42', 'admin', '2020-07-14 16:53:23', 'Y-实名认证 N-不实名认证', 1);
INSERT INTO `sys_config`(`config_id`, `config_name`, `config_key`, `config_value`, `config_type`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES (8, '绑定银行卡是的3要素四要素验证', 'verify_type', '3', 'Y', 'admin', '2020-07-14 16:51:39', '', NULL, '3-三要素验证 4-四要素验证 0-不验证', 1);

ALTER TABLE `tbl_cooperation`
ADD COLUMN `bank_channel` int(4) NULL COMMENT '银行渠道是否支持 0-否 1-是' AFTER `single_quota`,
ADD COLUMN `bank_single_quota` decimal(11, 5) NULL COMMENT '银行渠道单笔限额' AFTER `bank_channel`,
ADD COLUMN `alipay_channel` int(4) NULL COMMENT '支付宝渠道是否支持 0-否 1-是' AFTER `bank_single_quota`,
ADD COLUMN `alipay_single_quota` decimal(11, 5) NULL COMMENT '支付宝渠道单笔限额' AFTER `alipay_channel`,
ADD COLUMN `wechat_channel` int(4) NULL COMMENT '微信渠道是否支持 0-否 1-是' AFTER `alipay_single_quota`,
ADD COLUMN `wechat_single_quota` decimal(11, 5) NULL COMMENT '微信渠道单笔限额' AFTER `wechat_channel`;

-- 初始化合作表
update tbl_cooperation set bank_channel = 1,alipay_channel = 0,wechat_channel = 0,bank_single_quota = single_quota where payment_channel = 'BANK' and bank_channel is NULL;
update tbl_cooperation set bank_channel = 0,alipay_channel = 1,wechat_channel = 0,alipay_single_quota = single_quota where payment_channel = 'ALIPAY' and alipay_channel is NULL;
update tbl_cooperation set bank_channel = 0,alipay_channel = 0,wechat_channel = 1,wechat_single_quota = single_quota where payment_channel = 'WECHAT' and wechat_channel is NULL;

--  2020-7-20
 ALTER TABLE `mmtax`.`tbl_trx_order`
MODIFY COLUMN `order_status` int(1) NULL DEFAULT NULL COMMENT '-1-初始化 0-进行中 \r\n1-已打款 2-转账成功但打款失败未处理 4-转账成功打款失败已处理 9-失败订单结束' AFTER `payee_id_card_no`;

UPDATE `mmtax`.`sys_dict_data` SET `dict_code`='100', `dict_sort`='1', `dict_label`='处理中', `dict_value`='0', `dict_type`='trxorder_status', `css_class`='', `list_class`='warning', `is_default`='N', `status`='0', `create_by`='admin', `create_time`='2019-07-22 16:21:35', `update_by`='admin', `update_time`='2020-07-20 16:49:04', `remark`='', `provider_id`=NULL WHERE (`dict_code`='100');
UPDATE `mmtax`.`sys_dict_data` SET `dict_code`='101', `dict_sort`='1', `dict_label`='打款成功', `dict_value`='1', `dict_type`='trxorder_status', `css_class`='', `list_class`='success', `is_default`='N', `status`='0', `create_by`='admin', `create_time`='2019-07-22 16:22:00', `update_by`='admin', `update_time`='2020-07-20 16:49:17', `remark`='', `provider_id`=NULL WHERE (`dict_code`='101');
UPDATE `mmtax`.`sys_dict_data` SET `dict_code`='102', `dict_sort`='2', `dict_label`='打款挂起', `dict_value`='2', `dict_type`='trxorder_status', `css_class`='', `list_class`='warning', `is_default`='N', `status`='0', `create_by`='admin', `create_time`='2019-07-22 16:22:30', `update_by`='admin', `update_time`='2019-07-22 16:44:36', `remark`='', `provider_id`=NULL WHERE (`dict_code`='102');
UPDATE `mmtax`.`sys_dict_data` SET `dict_code`='232', `dict_sort`='5', `dict_label`='失败', `dict_value`='9', `dict_type`='trxorder_status', `css_class`='', `list_class`='info', `is_default`='Y', `status`='0', `create_by`='admin', `create_time`='2020-07-14 14:32:09', `update_by`='admin', `update_time`='2020-07-20 16:49:34', `remark`='订单失败', `provider_id`=NULL WHERE (`dict_code`='232');
UPDATE `mmtax`.`sys_dict_data` SET `dict_code`='233', `dict_sort`='5', `dict_label`='待处理', `dict_value`='-1', `dict_type`='trxorder_status', `css_class`=NULL, `list_class`='info', `is_default`='Y', `status`='0', `create_by`='admin', `create_time`='2020-07-15 11:50:25', `update_by`='', `update_time`=NULL, `remark`=NULL, `provider_id`=NULL WHERE (`dict_code`='233');
-- 页面手动删除调单状态

-- 0724 需求 start

-- 员工签约新增菜单数据 start
INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES ('2141', '列表导出', '2139', '2', '#', 'menuItem', 'F', '0', 'manager:sign:customerInfo:agreementExport', '#', 'admin', '2020-08-01 16:49:23', '', NULL, '', '1');
INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES ('2140', '协议管理列表', '2139', '1', '#', 'menuItem', 'F', '0', 'manager:sign:customerInfo:agreementList', '#', 'admin', '2020-08-01 16:48:54', '', NULL, '', '1');
INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES ('2139', '协议管理', '2105', '6', '/manager/signCustomerInfo/agreement', 'menuItem', 'C', '0', 'manager:sign:customerInfo:agreement', '#', 'admin', '2020-08-01 16:48:22', '', NULL, '', '1');
INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES ('2138', '导出列表', '2136', '2', '#', 'menuItem', 'F', '0', 'manager:sign:customerInfo:export', '#', 'admin', '2020-08-01 16:41:09', '', NULL, '', '1');
INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES ('2137', '员工签约列表', '2136', '1', '#', 'menuItem', 'F', '0', 'manager:sign:customerInfo:list', '#', 'admin', '2020-08-01 16:40:14', '', NULL, '', '1');
INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES ('2136', '员工签约', '2105', '5', '/manager/signCustomerInfo', 'menuItem', 'C', '0', 'manager:sign:customerInfo:view', '#', 'admin', '2020-08-01 16:38:04', '', NULL, '', '1');
-- 员工签约新增菜单数据 end

-- 字典更新 start
UPDATE `sys_dict_data` SET `dict_code`='229', `dict_sort`='1', `dict_label`='已转账', `dict_value`='1', `dict_type`='transfer_status', `css_class`='', `list_class`='success', `is_default`='N', `status`='0', `create_by`='admin', `create_time`='2020-07-14 10:53:34', `update_by`='admin', `update_time`='2020-07-29 16:12:24', `remark`='', `provider_id`=NULL WHERE (`dict_code`='229');
UPDATE `sys_dict_data` SET `dict_code`='230', `dict_sort`='2', `dict_label`='转账失败', `dict_value`='2', `dict_type`='transfer_status', `css_class`='', `list_class`='danger', `is_default`='N', `status`='0', `create_by`='admin', `create_time`='2020-07-14 10:53:34', `update_by`='admin', `update_time`='2020-07-29 16:12:07', `remark`='', `provider_id`=NULL WHERE (`dict_code`='230');
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES ('236', '3', '回退中', '3', 'transfer_status', NULL, 'info', 'N', '0', 'admin', '2020-07-29 16:13:06', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES ('237', '3', '回退成功', '4', 'transfer_status', NULL, 'success', 'N', '0', 'admin', '2020-07-29 16:14:57', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES ('238', '3', '回退失败', '5', 'transfer_status', NULL, 'warning', 'N', '0', 'admin', '2020-07-29 16:15:17', '', NULL, NULL, NULL);

-- 更新提现状态
UPDATE `sys_dict_data` SET `dict_code`='215', `dict_sort`='3', `dict_label`='提现失败', `dict_value`='9', `dict_type`='cashout_status', `css_class`=NULL, `list_class`='info', `is_default`='Y', `status`='0', `create_by`='admin', `create_time`='2020-07-03 17:10:23', `update_by`='', `update_time`=NULL, `remark`=NULL, `provider_id`=NULL WHERE (`dict_code`='215');

-- 协议状态字典新增
INSERT INTO `sys_dict_type` (`dict_id`, `dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES ('133', '协议状态', 'agreement_status', '0', 'admin', '2020-08-03 20:19:36', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES ('239', '1', '已过期', '1', 'agreement_status', NULL, 'danger', 'N', '0', 'admin', '2020-08-03 20:20:40', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES ('240', '0', '正常', '0', 'agreement_status', NULL, 'success', 'N', '0', 'admin', '2020-08-03 20:20:57', '', NULL, NULL, NULL);

-- 签约状态字典新增
INSERT INTO `sys_dict_type` (`dict_id`, `dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES ('134', '签约状态', 'sign_status', '0', 'admin', '2020-08-03 20:19:59', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES ('241', '0', '已签', '2', 'sign_status', '', 'success', 'N', '0', 'admin', '2020-08-03 20:21:29', 'admin', '2020-08-04 15:05:06', '', NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES ('242', '1', '失败', '3', 'sign_status', '', 'danger', 'N', '0', 'admin', '2020-08-03 20:21:52', 'admin', '2020-08-04 15:05:24', '', NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES ('243', '2', '拒签', '4', 'sign_status', '', 'warning', 'N', '0', 'admin', '2020-08-03 20:22:11', 'admin', '2020-08-04 15:05:33', '', NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES ('244', '2', '待签', '0', 'sign_status', NULL, 'info', 'N', '0', 'admin', '2020-08-04 15:05:47', '', NULL, NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `provider_id`) VALUES ('245', '2', '未签', '1', 'sign_status', NULL, 'warning', 'N', '0', 'admin', '2020-08-05 09:17:38', '', NULL, NULL, NULL);
-- 字典更新 end

-- 合作信息表更新 start
ALTER TABLE tbl_cooperation ADD `sign_switch` INT (1) NOT NULL DEFAULT '0' COMMENT '用户是否签约开关0-关，1-开' AFTER rate_switch;
-- 合作信息表更新 end

-- 新增表 start
-- tbl_batch_sign_record
-- tbl_customer_esign_info
-- tbl_customer_protocol
-- tbl_esign_fields
-- tbl_esign_flow
-- tbl_esign_flow_doc
-- tbl_esign_info
-- tbl_esign_template

-- 新增表 end

-- 0724 需求 end

-- 0811 需求 start
-- 合作信息表更新 start
ALTER TABLE tbl_cooperation ADD `silent_sign_switch` INT (1) NOT NULL DEFAULT '0' COMMENT '用户静默签约开关0-关（非静默），1-开（静默）' AFTER sign_switch;
-- 合作信息表更新 end
-- 0811 需求 end

-- 运营需求20201013 start

-- 添加江西启博税源地发票科目
INSERT INTO `tbl_tax_source_invoice_subject_correlation` (`id`, `tax_sounrce_company_id`, `invoice_subject_id`, `del_status`, `provider_id`, `reserved_field_one`, `reserved_field_two`, `reserved_field_three`, `create_time`, `update_time`) VALUES (NULL, '5', '1', '0', '1', NULL, NULL, NULL, '2020-09-11 21:45:42', '2020-09-11 21:45:44');
INSERT INTO `tbl_tax_source_invoice_subject_correlation` (`id`, `tax_sounrce_company_id`, `invoice_subject_id`, `del_status`, `provider_id`, `reserved_field_one`, `reserved_field_two`, `reserved_field_three`, `create_time`, `update_time`) VALUES (NULL, '5', '2', '0', '1', NULL, NULL, NULL, '2020-09-11 21:45:40', '2020-09-11 21:45:41');
INSERT INTO `tbl_tax_source_invoice_subject_correlation` (`id`, `tax_sounrce_company_id`, `invoice_subject_id`, `del_status`, `provider_id`, `reserved_field_one`, `reserved_field_two`, `reserved_field_three`, `create_time`, `update_time`) VALUES (NULL, '5', '3', '0', '1', NULL, NULL, NULL, '2020-09-11 21:45:36', '2020-09-11 21:45:38');
INSERT INTO `tbl_tax_source_invoice_subject_correlation` (`id`, `tax_sounrce_company_id`, `invoice_subject_id`, `del_status`, `provider_id`, `reserved_field_one`, `reserved_field_two`, `reserved_field_three`, `create_time`, `update_time`) VALUES (NULL, '5', '5', '0', '1', NULL, NULL, NULL, '2020-09-11 21:45:34', '2020-09-11 21:45:35');
INSERT INTO `tbl_tax_source_invoice_subject_correlation` (`id`, `tax_sounrce_company_id`, `invoice_subject_id`, `del_status`, `provider_id`, `reserved_field_one`, `reserved_field_two`, `reserved_field_three`, `create_time`, `update_time`) VALUES (NULL, '5', '6', '0', '1', NULL, NULL, NULL, '2020-09-11 21:45:32', '2020-09-11 21:45:33');
INSERT INTO `tbl_tax_source_invoice_subject_correlation` (`id`, `tax_sounrce_company_id`, `invoice_subject_id`, `del_status`, `provider_id`, `reserved_field_one`, `reserved_field_two`, `reserved_field_three`, `create_time`, `update_time`) VALUES (NULL, '5', '4', '0', '1', NULL, NULL, NULL, '2020-09-11 21:45:28', '2020-09-11 21:45:30');
-- 运营需求20201013 end