/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50540
Source Host           : localhost:3306
Source Database       : sample

Target Server Type    : MYSQL
Target Server Version : 50540
File Encoding         : 65001

Date: 2017-11-21 10:31:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_fproduct_info
-- ----------------------------
DROP TABLE IF EXISTS `t_fproduct_info`;
CREATE TABLE `t_fproduct_info` (
  `t_FProd_ID` varchar(64) NOT NULL,
  `t_FProd_Name` varchar(50) NOT NULL,
  `t_FProd_Interest` decimal(10,6) NOT NULL,
  `t_FProd_OverdueInt` decimal(10,6) NOT NULL,
  `t_FProd_Status` varchar(8) NOT NULL,
  `t_FProd_SysupdateDate` datetime NOT NULL,
  `t_FProd_category` varchar(64) DEFAULT NULL,
  `platform` varchar(11) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `creator` varchar(64) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL,
  `modifier` varchar(64) DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`t_FProd_ID`,`t_FProd_Name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_fproduct_info
-- ----------------------------
INSERT INTO `t_fproduct_info` VALUES ('032cff6579144436a94375200ceb4e7c', '随薪得', '0.000051', '0.002100', 'on', '2017-11-19 08:27:22', null, '0', null, 'df3718a8c5b811e6b901c85b763a0be0', '2017-11-19 08:27:22', 'df3718a8c5b811e6b901c85b763a0be0', '2017-11-19 23:50:04');
INSERT INTO `t_fproduct_info` VALUES ('0a86f61723d84b5a90a4a809629cca09', '随薪得三期', '0.000324', '0.000101', 'on', '2017-11-20 16:50:23', null, '0', null, 'df3718a8c5b811e6b901c85b763a0be0', '2017-11-20 16:50:23', null, null);
INSERT INTO `t_fproduct_info` VALUES ('1587d2a8dd014f9ca6987001fe1e2b93', '随薪得', '0.000050', '0.000100', 'off', '2017-11-18 20:42:45', null, '0', null, 'df3718a8c5b811e6b901c85b763a0be0', '2017-11-18 20:42:45', 'df3718a8c5b811e6b901c85b763a0be0', '2017-11-19 08:27:31');
INSERT INTO `t_fproduct_info` VALUES ('2904e81511644e12b68b862f418ac750', '随薪得三期', '0.000050', '0.000105', 'on', '2017-11-20 16:53:11', null, '0', null, 'df3718a8c5b811e6b901c85b763a0be0', '2017-11-20 16:53:11', null, null);
INSERT INTO `t_fproduct_info` VALUES ('2cf68a1cd5f54871a4fabca299624da7', '随薪得', '0.000051', '0.000103', 'on', '2017-11-20 16:45:01', null, '0', null, 'df3718a8c5b811e6b901c85b763a0be0', '2017-11-20 16:45:01', null, null);
INSERT INTO `t_fproduct_info` VALUES ('597e796973764253b04e5a801f950c70', '随薪得二额二期', '0.012345', '0.001234', 'on', '2017-11-18 21:57:24', null, '0', null, 'df3718a8c5b811e6b901c85b763a0be0', '2017-11-18 21:57:24', 'df3718a8c5b811e6b901c85b763a0be0', '2017-11-19 08:26:24');
INSERT INTO `t_fproduct_info` VALUES ('6c300410ba29463ea516284387c32888', '随薪得', '0.000051', '0.000105', 'on', '2017-11-20 17:00:54', null, '0', null, 'df3718a8c5b811e6b901c85b763a0be0', '2017-11-20 17:00:54', null, null);
INSERT INTO `t_fproduct_info` VALUES ('741e890068db4c8bb481bf61eb5851e3', '随薪得二期', '0.000050', '0.000100', 'on', '2017-11-18 20:43:21', null, '0', null, 'df3718a8c5b811e6b901c85b763a0be0', '2017-11-18 20:43:21', null, null);
INSERT INTO `t_fproduct_info` VALUES ('7cac132fc9ea48eaa12595deaaade690', '随薪得二期', '0.000050', '0.000100', 'on', '2017-11-18 21:57:09', null, '0', null, 'df3718a8c5b811e6b901c85b763a0be0', '2017-11-18 21:57:09', null, null);
INSERT INTO `t_fproduct_info` VALUES ('7e7f0247dfa0478f850746a5d64d5f67', '随薪得二五期', '0.000324', '0.000112', 'on', '2017-11-20 16:44:52', null, '0', null, 'df3718a8c5b811e6b901c85b763a0be0', '2017-11-20 16:44:52', null, null);
INSERT INTO `t_fproduct_info` VALUES ('9a66ed733b654d1ea988055128357e54', '随薪得二五期', '0.000050', '0.000100', 'on', '2017-11-20 16:41:31', null, '0', null, 'df3718a8c5b811e6b901c85b763a0be0', '2017-11-20 16:41:31', null, null);
INSERT INTO `t_fproduct_info` VALUES ('c27b7f815f9a4ea6b356d8104bd69d2f', '随薪得', '0.000050', '0.000101', 'on', '2017-11-20 16:51:04', null, '0', null, 'df3718a8c5b811e6b901c85b763a0be0', '2017-11-20 16:51:04', null, null);
INSERT INTO `t_fproduct_info` VALUES ('c69ca9af8f8f4ed2b9c911265a17cce5', '随薪得三期', '0.000051', '0.000100', 'on', '2017-11-20 16:53:23', null, '0', null, 'df3718a8c5b811e6b901c85b763a0be0', '2017-11-20 16:53:23', null, null);
INSERT INTO `t_fproduct_info` VALUES ('d27db6e6ad484c84832a21edd155c5de', '随薪得二五期', '0.000051', '0.000105', 'on', '2017-11-20 16:53:04', null, '0', null, 'df3718a8c5b811e6b901c85b763a0be0', '2017-11-20 16:53:04', null, null);
INSERT INTO `t_fproduct_info` VALUES ('d371fdc3200542b8ba941db48bc92db1', '随薪得三期', '0.000050', '0.000100', 'on', '2017-11-18 20:47:26', null, '0', null, 'df3718a8c5b811e6b901c85b763a0be0', '2017-11-18 20:47:26', null, null);
INSERT INTO `t_fproduct_info` VALUES ('e7f9949d585b4df9a36c6de77585b4eb', '随薪得', '0.012345', '0.000100', 'on', '2017-11-20 17:11:05', null, '0', null, 'df3718a8c5b811e6b901c85b763a0be0', '2017-11-20 17:11:05', null, null);
INSERT INTO `t_fproduct_info` VALUES ('eb8000a92d25471ca7975b22329d27ab', '随薪得二五期', '0.000050', '0.000100', 'on', '2017-11-18 22:53:19', null, '0', null, 'df3718a8c5b811e6b901c85b763a0be0', '2017-11-18 22:53:19', null, null);
INSERT INTO `t_fproduct_info` VALUES ('f195e7a28e8547a2aecd6897925a36bb', '随薪得三期', '0.000051', '0.000101', 'on', '2017-11-20 16:44:43', null, '0', null, 'df3718a8c5b811e6b901c85b763a0be0', '2017-11-20 16:44:43', null, null);

/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50540
Source Host           : localhost:3306
Source Database       : sample

Target Server Type    : MYSQL
Target Server Version : 50540
File Encoding         : 65001

Date: 2017-11-15 00:35:45
*/

-- ----------------------------
-- Table structure for t_personal_info
-- ----------------------------
DROP TABLE IF EXISTS `t_personal_info`;
CREATE TABLE `t_personal_info` (
  `t_P_Num` int(10) NOT NULL AUTO_INCREMENT,
  `t_P_Name` varchar(20) NOT NULL,
  `t_P_Sex` varchar(8) NOT NULL,
  `t_P_Age` int(3) NOT NULL,
  `t_P_PID` int(18) NOT NULL,
  `t_P_Marriage` varchar(8) NOT NULL,
  `t_P_HomeAddress` varchar(80) NOT NULL,
  `t_P_Mobil` varchar(13) NOT NULL DEFAULT '',
  `t_P_Phone` varchar(8) NOT NULL,
  `t_P_Spouse` varchar(20) DEFAULT NULL,
  `t_P_Spouse_Phone` varchar(13) DEFAULT NULL,
  `t_P_Contact1` varchar(20) NOT NULL,
  `t_P_Contact1Mobile` varchar(13) NOT NULL,
  `t_P_Payroll` varchar(20) DEFAULT NULL,
  `t_P_ContactMobile2` varchar(13) DEFAULT NULL,
  `t_P_Company` varchar(60) NOT NULL,
  `t_P_CompanyNum` varchar(30) DEFAULT NULL,
  `t_P_SocialSecurityBaseAmount` varchar(30) NOT NULL,
  `t_P_ProvidentFund` varchar(30) DEFAULT NULL,
  `t_P_Employmentstatus` varchar(20) NOT NULL,
  `t_P_EmploymentCategory` varchar(20) NOT NULL,
  `t_P_WorkYears` int(2) NOT NULL,
  `t_P_Probation` varchar(4) NOT NULL,
  `t_P_VendorEmployment` varchar(4) NOT NULL,
  `t_P_VendorEmployeeName` varchar(60) DEFAULT NULL,
  `t_P_PayrollDebitcardBankName` varchar(40) DEFAULT NULL,
  `t_P_PayrollDebitcardNum` int(30) NOT NULL,
  `t_P_NetBaseSalary` decimal(10,2) NOT NULL,
  `t_P_CreditPrepaySalaryAmount` decimal(10,2) NOT NULL,
  `t_P_PayrollDate` datetime NOT NULL,
  `t_P_NetMonthlyBonusAmount` decimal(10,2) DEFAULT NULL,
  `t_P_NetBonusPayDate` datetime NOT NULL,
  `t_P_SocialSecurityDate` datetime DEFAULT NULL,
  `t_P_SysUpdateDate` datetime NOT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `creator` varchar(64) NOT NULL,
  `create_time` datetime NOT NULL,
  `modifier` varchar(64) DEFAULT NULL,
  `modify_time` datetime NOT NULL,
  PRIMARY KEY (`t_P_Num`,`t_P_PID`),
  KEY `t_P_Num` (`t_P_PID`) USING BTREE,
  KEY `t_P_PID` (`t_P_PID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_personal_info
-- ----------------------------

