/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50540
Source Host           : 127.0.0.1:3306
Source Database       : sample

Target Server Type    : MYSQL
Target Server Version : 50540
File Encoding         : 65001

Date: 2017-10-30 11:56:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tr_manager_role
-- ----------------------------
DROP TABLE IF EXISTS `tr_manager_role`;
CREATE TABLE `tr_manager_role` (
  `manager_id` varchar(64) NOT NULL COMMENT '管理人员id',
  `role_id` varchar(64) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`manager_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tr_manager_role
-- ----------------------------
INSERT INTO `tr_manager_role` VALUES ('df3718a8c5b811e6b901c85b763a0be0', 'df4f768ec5b811e6b901c85b763a0be0');

-- ----------------------------
-- Table structure for tr_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `tr_role_resource`;
CREATE TABLE `tr_role_resource` (
  `role_id` varchar(64) NOT NULL COMMENT '角色id',
  `resource_id` varchar(64) NOT NULL COMMENT '资源id',
  PRIMARY KEY (`resource_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tr_role_resource
-- ----------------------------
INSERT INTO `tr_role_resource` VALUES ('72470da57ce74cb5ab18ef643dd3567d', '08bfa3d3f96b4951a3f58d99392350ad');
INSERT INTO `tr_role_resource` VALUES ('d4cb669bafca11e7ad6dc85b763a0be0', '08bfa3d3f96b4951a3f58d99392350ad');
INSERT INTO `tr_role_resource` VALUES ('df4f768ec5b811e6b901c85b763a0be0', '0c116a41ef7f4a74acb68ce68b8c38c1');
INSERT INTO `tr_role_resource` VALUES ('414e209eafcc11e7ad6dc85b763a0be0', '0d9174a1af2211e7ad6dc85b763a0be0');
INSERT INTO `tr_role_resource` VALUES ('ac956d18afcb11e7ad6dc85b763a0be0', '0d9174a1af2211e7ad6dc85b763a0be0');
INSERT INTO `tr_role_resource` VALUES ('bf24f0a3ebed4ed0b8fca9eecf149461', '0d9174a1af2211e7ad6dc85b763a0be0');
INSERT INTO `tr_role_resource` VALUES ('df4f768ec5b811e6b901c85b763a0be0', '12197264f52e41cd90c8e2b7894e55ef');
INSERT INTO `tr_role_resource` VALUES ('414e209eafcc11e7ad6dc85b763a0be0', '160930be8355459995d3f7e7c9e91b52');
INSERT INTO `tr_role_resource` VALUES ('72470da57ce74cb5ab18ef643dd3567d', '160930be8355459995d3f7e7c9e91b52');
INSERT INTO `tr_role_resource` VALUES ('ac956d18afcb11e7ad6dc85b763a0be0', '160930be8355459995d3f7e7c9e91b52');
INSERT INTO `tr_role_resource` VALUES ('bf24f0a3ebed4ed0b8fca9eecf149461', '160930be8355459995d3f7e7c9e91b52');
INSERT INTO `tr_role_resource` VALUES ('d4cb669bafca11e7ad6dc85b763a0be0', '160930be8355459995d3f7e7c9e91b52');
INSERT INTO `tr_role_resource` VALUES ('133d192fafce11e7ad6dc85b763a0be0', '1a58e848af2211e7ad6dc85b763a0be0');
INSERT INTO `tr_role_resource` VALUES ('414e209eafcc11e7ad6dc85b763a0be0', '1a58e848af2211e7ad6dc85b763a0be0');
INSERT INTO `tr_role_resource` VALUES ('9a961e9417394287a118a0e8a93ec62b', '1a58e848af2211e7ad6dc85b763a0be0');
INSERT INTO `tr_role_resource` VALUES ('df4f768ec5b811e6b901c85b763a0be0', '1d4aece6ca7244d4be3d6b4aa289e11e');
INSERT INTO `tr_role_resource` VALUES ('df4f768ec5b811e6b901c85b763a0be0', '27bcf167efaf4db897b288588ac36b92');
INSERT INTO `tr_role_resource` VALUES ('414e209eafcc11e7ad6dc85b763a0be0', '3515463ce0764449a99e2f6d9a9875ee');
INSERT INTO `tr_role_resource` VALUES ('ac956d18afcb11e7ad6dc85b763a0be0', '3515463ce0764449a99e2f6d9a9875ee');
INSERT INTO `tr_role_resource` VALUES ('bf24f0a3ebed4ed0b8fca9eecf149461', '3515463ce0764449a99e2f6d9a9875ee');
INSERT INTO `tr_role_resource` VALUES ('133d192fafce11e7ad6dc85b763a0be0', '426133d3c1a54a0e88d66088329c8dc2');
INSERT INTO `tr_role_resource` VALUES ('414e209eafcc11e7ad6dc85b763a0be0', '426133d3c1a54a0e88d66088329c8dc2');
INSERT INTO `tr_role_resource` VALUES ('72470da57ce74cb5ab18ef643dd3567d', '426133d3c1a54a0e88d66088329c8dc2');
INSERT INTO `tr_role_resource` VALUES ('9a961e9417394287a118a0e8a93ec62b', '426133d3c1a54a0e88d66088329c8dc2');
INSERT INTO `tr_role_resource` VALUES ('ac956d18afcb11e7ad6dc85b763a0be0', '426133d3c1a54a0e88d66088329c8dc2');
INSERT INTO `tr_role_resource` VALUES ('bf24f0a3ebed4ed0b8fca9eecf149461', '426133d3c1a54a0e88d66088329c8dc2');
INSERT INTO `tr_role_resource` VALUES ('d4cb669bafca11e7ad6dc85b763a0be0', '426133d3c1a54a0e88d66088329c8dc2');
INSERT INTO `tr_role_resource` VALUES ('df4f768ec5b811e6b901c85b763a0be0', '44fc287a026711e7a225c85b763a0be0');
INSERT INTO `tr_role_resource` VALUES ('df4f768ec5b811e6b901c85b763a0be0', '45f21f05270d4ecab278193246072ee8');
INSERT INTO `tr_role_resource` VALUES ('df4f768ec5b811e6b901c85b763a0be0', '4aefaf45026711e7a225c85b763a0be0');
INSERT INTO `tr_role_resource` VALUES ('df4f768ec5b811e6b901c85b763a0be0', '4f76d29f026711e7a225c85b763a0be0');
INSERT INTO `tr_role_resource` VALUES ('414e209eafcc11e7ad6dc85b763a0be0', '534c81ae9f6f11e7a9d200163e2e8c1c');
INSERT INTO `tr_role_resource` VALUES ('ac956d18afcb11e7ad6dc85b763a0be0', '534c81ae9f6f11e7a9d200163e2e8c1c');
INSERT INTO `tr_role_resource` VALUES ('bf24f0a3ebed4ed0b8fca9eecf149461', '534c81ae9f6f11e7a9d200163e2e8c1c');
INSERT INTO `tr_role_resource` VALUES ('414e209eafcc11e7ad6dc85b763a0be0', '5638e02495594ff99ac7e2ae60f54b84');
INSERT INTO `tr_role_resource` VALUES ('ac956d18afcb11e7ad6dc85b763a0be0', '5638e02495594ff99ac7e2ae60f54b84');
INSERT INTO `tr_role_resource` VALUES ('bf24f0a3ebed4ed0b8fca9eecf149461', '5638e02495594ff99ac7e2ae60f54b84');
INSERT INTO `tr_role_resource` VALUES ('414e209eafcc11e7ad6dc85b763a0be0', '59cd2a7eafdc11e7ad6dc85b763a0be0');
INSERT INTO `tr_role_resource` VALUES ('ac956d18afcb11e7ad6dc85b763a0be0', '59cd2a7eafdc11e7ad6dc85b763a0be0');
INSERT INTO `tr_role_resource` VALUES ('d4cb669bafca11e7ad6dc85b763a0be0', '59cd2a7eafdc11e7ad6dc85b763a0be0');
INSERT INTO `tr_role_resource` VALUES ('133d192fafce11e7ad6dc85b763a0be0', '6222f7274f31445fa65d170200574344');
INSERT INTO `tr_role_resource` VALUES ('414e209eafcc11e7ad6dc85b763a0be0', '6222f7274f31445fa65d170200574344');
INSERT INTO `tr_role_resource` VALUES ('72470da57ce74cb5ab18ef643dd3567d', '6222f7274f31445fa65d170200574344');
INSERT INTO `tr_role_resource` VALUES ('9a961e9417394287a118a0e8a93ec62b', '6222f7274f31445fa65d170200574344');
INSERT INTO `tr_role_resource` VALUES ('ac956d18afcb11e7ad6dc85b763a0be0', '6222f7274f31445fa65d170200574344');
INSERT INTO `tr_role_resource` VALUES ('bf24f0a3ebed4ed0b8fca9eecf149461', '6222f7274f31445fa65d170200574344');
INSERT INTO `tr_role_resource` VALUES ('d4cb669bafca11e7ad6dc85b763a0be0', '6222f7274f31445fa65d170200574344');
INSERT INTO `tr_role_resource` VALUES ('72470da57ce74cb5ab18ef643dd3567d', '666b7248997c48c5a56863f31817d3f9');
INSERT INTO `tr_role_resource` VALUES ('d4cb669bafca11e7ad6dc85b763a0be0', '666b7248997c48c5a56863f31817d3f9');
INSERT INTO `tr_role_resource` VALUES ('414e209eafcc11e7ad6dc85b763a0be0', '6a28c7b8fca847d9be7ea225ddda00dc');
INSERT INTO `tr_role_resource` VALUES ('72470da57ce74cb5ab18ef643dd3567d', '6a28c7b8fca847d9be7ea225ddda00dc');
INSERT INTO `tr_role_resource` VALUES ('ac956d18afcb11e7ad6dc85b763a0be0', '6a28c7b8fca847d9be7ea225ddda00dc');
INSERT INTO `tr_role_resource` VALUES ('bf24f0a3ebed4ed0b8fca9eecf149461', '6a28c7b8fca847d9be7ea225ddda00dc');
INSERT INTO `tr_role_resource` VALUES ('d4cb669bafca11e7ad6dc85b763a0be0', '6a28c7b8fca847d9be7ea225ddda00dc');
INSERT INTO `tr_role_resource` VALUES ('414e209eafcc11e7ad6dc85b763a0be0', '6ecbf430afdc11e7ad6dc85b763a0be0');
INSERT INTO `tr_role_resource` VALUES ('ac956d18afcb11e7ad6dc85b763a0be0', '6ecbf430afdc11e7ad6dc85b763a0be0');
INSERT INTO `tr_role_resource` VALUES ('d4cb669bafca11e7ad6dc85b763a0be0', '6ecbf430afdc11e7ad6dc85b763a0be0');
INSERT INTO `tr_role_resource` VALUES ('414e209eafcc11e7ad6dc85b763a0be0', '70c856f10af842b194089075cb4971ba');
INSERT INTO `tr_role_resource` VALUES ('72470da57ce74cb5ab18ef643dd3567d', '70c856f10af842b194089075cb4971ba');
INSERT INTO `tr_role_resource` VALUES ('ac956d18afcb11e7ad6dc85b763a0be0', '70c856f10af842b194089075cb4971ba');
INSERT INTO `tr_role_resource` VALUES ('bf24f0a3ebed4ed0b8fca9eecf149461', '70c856f10af842b194089075cb4971ba');
INSERT INTO `tr_role_resource` VALUES ('d4cb669bafca11e7ad6dc85b763a0be0', '70c856f10af842b194089075cb4971ba');
INSERT INTO `tr_role_resource` VALUES ('72470da57ce74cb5ab18ef643dd3567d', '7194ae6c4b5544a79c77892a921fbc3b');
INSERT INTO `tr_role_resource` VALUES ('d4cb669bafca11e7ad6dc85b763a0be0', '7194ae6c4b5544a79c77892a921fbc3b');
INSERT INTO `tr_role_resource` VALUES ('72470da57ce74cb5ab18ef643dd3567d', '72292fea00af4fa9a99e10a8fd1f95b5');
INSERT INTO `tr_role_resource` VALUES ('d4cb669bafca11e7ad6dc85b763a0be0', '72292fea00af4fa9a99e10a8fd1f95b5');
INSERT INTO `tr_role_resource` VALUES ('72470da57ce74cb5ab18ef643dd3567d', '78ac4ccb6e14478f8ca08f10414e1c9d');
INSERT INTO `tr_role_resource` VALUES ('d4cb669bafca11e7ad6dc85b763a0be0', '78ac4ccb6e14478f8ca08f10414e1c9d');
INSERT INTO `tr_role_resource` VALUES ('133d192fafce11e7ad6dc85b763a0be0', '81d2f9b6af2311e7ad6dc85b763a0be0');
INSERT INTO `tr_role_resource` VALUES ('133d192fafce11e7ad6dc85b763a0be0', '8df2c91bafdb11e7ad6dc85b763a0be0');
INSERT INTO `tr_role_resource` VALUES ('414e209eafcc11e7ad6dc85b763a0be0', '8df2c91bafdb11e7ad6dc85b763a0be0');
INSERT INTO `tr_role_resource` VALUES ('ac956d18afcb11e7ad6dc85b763a0be0', '8df2c91bafdb11e7ad6dc85b763a0be0');
INSERT INTO `tr_role_resource` VALUES ('d4cb669bafca11e7ad6dc85b763a0be0', '8df2c91bafdb11e7ad6dc85b763a0be0');
INSERT INTO `tr_role_resource` VALUES ('72470da57ce74cb5ab18ef643dd3567d', '9a4a61e10ebd4b5bbee86b3a92761a8e');
INSERT INTO `tr_role_resource` VALUES ('d4cb669bafca11e7ad6dc85b763a0be0', '9a4a61e10ebd4b5bbee86b3a92761a8e');
INSERT INTO `tr_role_resource` VALUES ('414e209eafcc11e7ad6dc85b763a0be0', 'a339010cd78a470d8ec4e47102312c90');
INSERT INTO `tr_role_resource` VALUES ('ac956d18afcb11e7ad6dc85b763a0be0', 'a339010cd78a470d8ec4e47102312c90');
INSERT INTO `tr_role_resource` VALUES ('bf24f0a3ebed4ed0b8fca9eecf149461', 'a339010cd78a470d8ec4e47102312c90');
INSERT INTO `tr_role_resource` VALUES ('414e209eafcc11e7ad6dc85b763a0be0', 'b561de6fae5f11e7ad6dc85b763a0be0');
INSERT INTO `tr_role_resource` VALUES ('72470da57ce74cb5ab18ef643dd3567d', 'b561de6fae5f11e7ad6dc85b763a0be0');
INSERT INTO `tr_role_resource` VALUES ('ac956d18afcb11e7ad6dc85b763a0be0', 'b561de6fae5f11e7ad6dc85b763a0be0');
INSERT INTO `tr_role_resource` VALUES ('bf24f0a3ebed4ed0b8fca9eecf149461', 'b561de6fae5f11e7ad6dc85b763a0be0');
INSERT INTO `tr_role_resource` VALUES ('d4cb669bafca11e7ad6dc85b763a0be0', 'b561de6fae5f11e7ad6dc85b763a0be0');
INSERT INTO `tr_role_resource` VALUES ('df4f768ec5b811e6b901c85b763a0be0', 'bef6e61e4cde4209aca6a38546fd6e53');
INSERT INTO `tr_role_resource` VALUES ('df4f768ec5b811e6b901c85b763a0be0', 'c3fc4993e3454852b6cbc69099033b4c');
INSERT INTO `tr_role_resource` VALUES ('414e209eafcc11e7ad6dc85b763a0be0', 'cc5287d8afde11e7ad6dc85b763a0be0');
INSERT INTO `tr_role_resource` VALUES ('ac956d18afcb11e7ad6dc85b763a0be0', 'cc5287d8afde11e7ad6dc85b763a0be0');
INSERT INTO `tr_role_resource` VALUES ('df4f768ec5b811e6b901c85b763a0be0', 'df26f4ceab2f49908fd841dc9b032353');
INSERT INTO `tr_role_resource` VALUES ('133d192fafce11e7ad6dc85b763a0be0', 'e6bfd5c9af2111e7ad6dc85b763a0be0');
INSERT INTO `tr_role_resource` VALUES ('414e209eafcc11e7ad6dc85b763a0be0', 'e6bfd5c9af2111e7ad6dc85b763a0be0');
INSERT INTO `tr_role_resource` VALUES ('72470da57ce74cb5ab18ef643dd3567d', 'e6bfd5c9af2111e7ad6dc85b763a0be0');
INSERT INTO `tr_role_resource` VALUES ('9a961e9417394287a118a0e8a93ec62b', 'e6bfd5c9af2111e7ad6dc85b763a0be0');
INSERT INTO `tr_role_resource` VALUES ('ac956d18afcb11e7ad6dc85b763a0be0', 'e6bfd5c9af2111e7ad6dc85b763a0be0');
INSERT INTO `tr_role_resource` VALUES ('bf24f0a3ebed4ed0b8fca9eecf149461', 'e6bfd5c9af2111e7ad6dc85b763a0be0');
INSERT INTO `tr_role_resource` VALUES ('d4cb669bafca11e7ad6dc85b763a0be0', 'e6bfd5c9af2111e7ad6dc85b763a0be0');
INSERT INTO `tr_role_resource` VALUES ('414e209eafcc11e7ad6dc85b763a0be0', 'e7a81f06f93e4db39f742a8c1424c247');
INSERT INTO `tr_role_resource` VALUES ('72470da57ce74cb5ab18ef643dd3567d', 'e7a81f06f93e4db39f742a8c1424c247');
INSERT INTO `tr_role_resource` VALUES ('ac956d18afcb11e7ad6dc85b763a0be0', 'e7a81f06f93e4db39f742a8c1424c247');
INSERT INTO `tr_role_resource` VALUES ('bf24f0a3ebed4ed0b8fca9eecf149461', 'e7a81f06f93e4db39f742a8c1424c247');
INSERT INTO `tr_role_resource` VALUES ('d4cb669bafca11e7ad6dc85b763a0be0', 'e7a81f06f93e4db39f742a8c1424c247');
INSERT INTO `tr_role_resource` VALUES ('df4f768ec5b811e6b901c85b763a0be0', 'e9bf7d34026511e7945400163e2e8c1c');
INSERT INTO `tr_role_resource` VALUES ('72470da57ce74cb5ab18ef643dd3567d', 'eace040b6f23468687cf81d854d40736');
INSERT INTO `tr_role_resource` VALUES ('d4cb669bafca11e7ad6dc85b763a0be0', 'eace040b6f23468687cf81d854d40736');
INSERT INTO `tr_role_resource` VALUES ('df4f768ec5b811e6b901c85b763a0be0', 'efe4acd9026511e7945400163e2e8c1c');
INSERT INTO `tr_role_resource` VALUES ('414e209eafcc11e7ad6dc85b763a0be0', 'f436755aafdb11e7ad6dc85b763a0be0');
INSERT INTO `tr_role_resource` VALUES ('ac956d18afcb11e7ad6dc85b763a0be0', 'f436755aafdb11e7ad6dc85b763a0be0');
INSERT INTO `tr_role_resource` VALUES ('d4cb669bafca11e7ad6dc85b763a0be0', 'f436755aafdb11e7ad6dc85b763a0be0');
INSERT INTO `tr_role_resource` VALUES ('414e209eafcc11e7ad6dc85b763a0be0', 'f60a73d265e148ff93c47dcb94b6f6e4');
INSERT INTO `tr_role_resource` VALUES ('ac956d18afcb11e7ad6dc85b763a0be0', 'f60a73d265e148ff93c47dcb94b6f6e4');
INSERT INTO `tr_role_resource` VALUES ('bf24f0a3ebed4ed0b8fca9eecf149461', 'f60a73d265e148ff93c47dcb94b6f6e4');
INSERT INTO `tr_role_resource` VALUES ('df4f768ec5b811e6b901c85b763a0be0', 'f6af7431026511e7945400163e2e8c1c');
INSERT INTO `tr_role_resource` VALUES ('df4f768ec5b811e6b901c85b763a0be0', 'fb016209026511e7945400163e2e8c1c');
INSERT INTO `tr_role_resource` VALUES ('df4f768ec5b811e6b901c85b763a0be0', 'fd9162096d6140459104977c9fed3696');

-- ----------------------------
-- Table structure for t_config
-- ----------------------------
DROP TABLE IF EXISTS `t_config`;
CREATE TABLE `t_config` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `ckey` varchar(50) NOT NULL COMMENT '键',
  `cvalue` varchar(1000) NOT NULL COMMENT '值',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '是否有效（0：否；1：是）',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统配置表';

-- ----------------------------
-- Records of t_config
-- ----------------------------
INSERT INTO `t_config` VALUES ('935ceadccbe611e6a55400163e0001e0', 'ENT_DEFAULT_COPYRIGHT', 'Copyright &copy; 2015-2017 梅肯（上海）网络科技有限公司 版权所有', '1', '默认版权信息');
INSERT INTO `t_config` VALUES ('e508a07dc5b811e6b901c85b763a0be0', 'ENT_DEFAULT_NAME', '梅肯（上海）网络科技有限公司', '1', '默认公司名称');
INSERT INTO `t_config` VALUES ('e5c6f64dc5b811e6b901c85b763a0be0', 'ENTER_SYMBOL', '_@', '1', '系统通用换行符');

-- ----------------------------
-- Table structure for t_manager
-- ----------------------------
DROP TABLE IF EXISTS `t_manager`;
CREATE TABLE `t_manager` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `user_name` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `real_name` varchar(50) DEFAULT NULL COMMENT '真实姓名',
  `mobile` varchar(50) DEFAULT NULL COMMENT '手机',
  `telephone` varchar(50) DEFAULT NULL COMMENT '座机',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '是否有效（0：否；1：是）',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='管理人员表';

-- ----------------------------
-- Records of t_manager
-- ----------------------------
INSERT INTO `t_manager` VALUES ('df3718a8c5b811e6b901c85b763a0be0', 'admin', 'admin', '管理员', null, null, null, '1', '2016-12-19 15:00:22');

-- ----------------------------
-- Table structure for t_resource
-- ----------------------------
DROP TABLE IF EXISTS `t_resource`;
CREATE TABLE `t_resource` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `parent_id` varchar(64) DEFAULT NULL COMMENT '父资源id',
  `name` varchar(50) NOT NULL COMMENT '资源名称',
  `type` int(11) NOT NULL COMMENT '类型（0：目录；1：菜单；2：权限）',
  `link` varchar(100) DEFAULT NULL COMMENT '链接',
  `idx` int(11) NOT NULL COMMENT '排序索引',
  `is_show` int(11) NOT NULL DEFAULT '1' COMMENT '是否显示（0：否；1：是）',
  `shiro_permission` varchar(50) DEFAULT NULL COMMENT 'shiro权限代码',
  `platform` int(11) NOT NULL DEFAULT '0' COMMENT '所属平台（0：管理后台；1：企业端；2：个人端web）',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `creator` varchar(64) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modifier` varchar(64) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
-- Records of t_resource
-- ----------------------------
INSERT INTO `t_resource` VALUES ('0c116a41ef7f4a74acb68ce68b8c38c1', 'bef6e61e4cde4209aca6a38546fd6e53', '系统人员管理', '1', 'managerController/managerList', '20', '1', '', '0', null, 'df3718a8c5b811e6b901c85b763a0be0', '2016-12-19 15:00:23', null, null);
INSERT INTO `t_resource` VALUES ('12197264f52e41cd90c8e2b7894e55ef', 'bef6e61e4cde4209aca6a38546fd6e53', '资源管理', '1', 'resourceController/resourceList', '60', '1', '', '0', null, 'df3718a8c5b811e6b901c85b763a0be0', '2016-12-19 15:00:23', null, null);
INSERT INTO `t_resource` VALUES ('1d4aece6ca7244d4be3d6b4aa289e11e', '0c116a41ef7f4a74acb68ce68b8c38c1', '授权', '2', '', '80', '1', 'MANAGER_GRANT_ROLE', '0', null, 'df3718a8c5b811e6b901c85b763a0be0', '2016-12-19 15:00:23', null, null);
INSERT INTO `t_resource` VALUES ('27bcf167efaf4db897b288588ac36b92', '0c116a41ef7f4a74acb68ce68b8c38c1', '删除', '2', '', '40', '1', 'MANAGER_DELETE', '0', null, 'df3718a8c5b811e6b901c85b763a0be0', '2016-12-19 15:00:23', null, null);
INSERT INTO `t_resource` VALUES ('44fc287a026711e7a225c85b763a0be0', '12197264f52e41cd90c8e2b7894e55ef', '新增', '2', '', '20', '1', 'RESOURCE_ADD', '0', null, 'df3718a8c5b811e6b901c85b763a0be0', '2016-12-19 15:00:23', null, null);
INSERT INTO `t_resource` VALUES ('45f21f05270d4ecab278193246072ee8', 'bef6e61e4cde4209aca6a38546fd6e53', '角色管理', '1', 'roleController/roleList', '40', '1', '', '0', null, 'df3718a8c5b811e6b901c85b763a0be0', '2016-12-19 15:00:23', null, null);
INSERT INTO `t_resource` VALUES ('4aefaf45026711e7a225c85b763a0be0', '12197264f52e41cd90c8e2b7894e55ef', '删除', '2', '', '40', '1', 'RESOURCE_DELETE', '0', null, 'df3718a8c5b811e6b901c85b763a0be0', '2016-12-19 15:00:23', null, null);
INSERT INTO `t_resource` VALUES ('4f76d29f026711e7a225c85b763a0be0', '12197264f52e41cd90c8e2b7894e55ef', '编辑', '2', '', '60', '1', 'RESOURCE_EDIT', '0', null, 'df3718a8c5b811e6b901c85b763a0be0', '2016-12-19 15:00:23', null, null);
INSERT INTO `t_resource` VALUES ('bef6e61e4cde4209aca6a38546fd6e53', '', '系统管理', '0', '', '300', '1', '', '0', null, 'df3718a8c5b811e6b901c85b763a0be0', '2016-12-19 15:00:23', null, null);
INSERT INTO `t_resource` VALUES ('c3fc4993e3454852b6cbc69099033b4c', '0c116a41ef7f4a74acb68ce68b8c38c1', '新增', '2', '', '20', '1', 'MANAGER_ADD', '0', null, 'df3718a8c5b811e6b901c85b763a0be0', '2016-12-19 15:00:23', null, null);
INSERT INTO `t_resource` VALUES ('df26f4ceab2f49908fd841dc9b032353', 'bef6e61e4cde4209aca6a38546fd6e53', '配置管理', '1', 'configController/configList', '80', '1', '', '0', null, 'df3718a8c5b811e6b901c85b763a0be0', '2016-12-19 15:00:24', null, null);
INSERT INTO `t_resource` VALUES ('e9bf7d34026511e7945400163e2e8c1c', '45f21f05270d4ecab278193246072ee8', '新增', '2', '', '20', '1', 'ROLE_ADD', '0', null, 'df3718a8c5b811e6b901c85b763a0be0', '2016-12-19 15:00:23', null, null);
INSERT INTO `t_resource` VALUES ('efe4acd9026511e7945400163e2e8c1c', '45f21f05270d4ecab278193246072ee8', '删除', '2', '', '40', '1', 'ROLE_DELETE', '0', null, 'df3718a8c5b811e6b901c85b763a0be0', '2016-12-19 15:00:23', null, null);
INSERT INTO `t_resource` VALUES ('f6af7431026511e7945400163e2e8c1c', '45f21f05270d4ecab278193246072ee8', '编辑', '2', '', '60', '1', 'ROLE_EDIT', '0', null, 'df3718a8c5b811e6b901c85b763a0be0', '2016-12-19 15:00:23', null, null);
INSERT INTO `t_resource` VALUES ('fb016209026511e7945400163e2e8c1c', '45f21f05270d4ecab278193246072ee8', '授权', '2', '', '80', '1', 'ROLE_GRANT_RESOURCE', '0', null, 'df3718a8c5b811e6b901c85b763a0be0', '2016-12-19 15:00:23', null, null);
INSERT INTO `t_resource` VALUES ('fd9162096d6140459104977c9fed3696', '0c116a41ef7f4a74acb68ce68b8c38c1', '编辑', '2', '', '60', '1', 'MANAGER_EDIT', '0', null, 'df3718a8c5b811e6b901c85b763a0be0', '2016-12-19 15:00:23', null, null);

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `name` varchar(50) NOT NULL COMMENT '角色名',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '是否有效（0：否；1：是）',
  `platform` int(11) NOT NULL DEFAULT '0' COMMENT '所属平台（0：管理端；1：企业端；2：个人端）',
  `enterprise_id` varchar(64) DEFAULT NULL COMMENT '企业id',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `creator` varchar(64) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modifier` varchar(64) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('133d192fafce11e7ad6dc85b763a0be0', 'ent_finance', '1', '1', null, '企业财务', '1', '2016-12-19 15:00:22', null, null);
INSERT INTO `t_role` VALUES ('414e209eafcc11e7ad6dc85b763a0be0', 'ent_transactor_leader', '1', '1', null, '企业经办人主管', '1', '2016-12-19 15:00:22', null, null);
INSERT INTO `t_role` VALUES ('ac956d18afcb11e7ad6dc85b763a0be0', 'ent_transactor', '1', '1', null, '企业经办人', '1', '2016-12-19 15:00:22', null, null);
INSERT INTO `t_role` VALUES ('c035ab83afca11e7ad6dc85b763a0be0', 'agency_admin', '1', '1', null, '经纪公司管理员', '1', '2016-12-19 15:00:22', null, null);
INSERT INTO `t_role` VALUES ('d4cb669bafca11e7ad6dc85b763a0be0', 'ent_admin', '1', '1', null, '企业管理员', '1', '2016-12-19 15:00:22', null, null);
INSERT INTO `t_role` VALUES ('df4f768ec5b811e6b901c85b763a0be0', 'admin', '1', '0', null, '系统管理员', '1', '2016-12-19 15:00:22', null, null);

-- ----------------------------
-- Function structure for deleteResourceChildList
-- ----------------------------
DROP FUNCTION IF EXISTS `deleteResourceChildList`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `deleteResourceChildList`(resourceId varchar(255)) RETURNS int(11)
BEGIN
DECLARE
		sTemp VARCHAR (1000);
	DECLARE
		sTempChd VARCHAR (1000);
	SET sTemp = '$';
	SET sTempChd = resourceId;
	WHILE sTempChd IS NOT NULL DO
		SET sTemp = concat(sTemp, ',', sTempChd);
		SELECT
			group_concat(DISTINCT id) INTO sTempChd
		FROM
			t_resource
		WHERE
		FIND_IN_SET(parent_id,sTempChd)>0;
	END WHILE;
  DELETE FROM t_resource WHERE FIND_IN_SET(id, sTemp);
	RETURN 0;
END
;;
DELIMITER ;

-- ----------------------------
-- Function structure for getResourceChildList
-- ----------------------------
DROP FUNCTION IF EXISTS `getResourceChildList`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `getResourceChildList`(resourceId varchar(255)) RETURNS varchar(1000) CHARSET utf8
BEGIN
DECLARE
		sTemp VARCHAR (1000);
	DECLARE
		sTempChd VARCHAR (1000);
	SET sTemp = '$';
	SET sTempChd = resourceId;
	WHILE sTempChd IS NOT NULL DO
		SET sTemp = concat(sTemp, ',', sTempChd);
		SELECT
			group_concat(DISTINCT id) INTO sTempChd
		FROM
			t_resource
		WHERE
		FIND_IN_SET(parent_id,sTempChd)>0;
	END WHILE;
	RETURN sTemp;
END
;;
DELIMITER ;
