/*
Navicat MySQL Data Transfer

Source Server         : fantuango
Source Server Version : 50626
Source Host           : 10.5.102.42:3317
Source Database       : fantuan

Target Server Type    : MYSQL
Target Server Version : 50626
File Encoding         : 65001

Date: 2018-02-28 17:49:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_consume`
-- ----------------------------
DROP TABLE IF EXISTS `t_consume`;
CREATE TABLE `t_consume` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `consume_price` decimal(13,2) DEFAULT '0.00',
  `user_code` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `consume_detail` varchar(1024) COLLATE utf8_bin DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=147 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_consume
-- ----------------------------

-- ----------------------------
-- Table structure for `t_exchange`
-- ----------------------------
DROP TABLE IF EXISTS `t_exchange`;
CREATE TABLE `t_exchange` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `source_user_code` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `des_user_code` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `exchange_detail` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `exchange_price` decimal(10,0) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=95 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_exchange
-- ----------------------------

-- ----------------------------
-- Table structure for `t_operate_log`
-- ----------------------------
DROP TABLE IF EXISTS `t_operate_log`;
CREATE TABLE `t_operate_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `operate_ip` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `operate_detail` varchar(512) COLLATE utf8_bin DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `operate_type` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_operate_log_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=125 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='操作日志';

-- ----------------------------
-- Records of t_operate_log
-- ----------------------------

-- ----------------------------
-- Table structure for `t_recharge`
-- ----------------------------
DROP TABLE IF EXISTS `t_recharge`;
CREATE TABLE `t_recharge` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_code` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `recharge_detail` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `recharge_price` decimal(13,2) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_recharge
-- ----------------------------

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_code` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `user_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_user
-- ----------------------------

-- ----------------------------
-- Table structure for `t_user_leader`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_leader`;
CREATE TABLE `t_user_leader` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_code` varchar(64) COLLATE utf8_bin NOT NULL,
  `leader_key` varchar(32) COLLATE utf8_bin NOT NULL,
  `leader_start_time` bigint(20) NOT NULL,
  `leader_end_time` bigint(20) NOT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_use_leader_id_uindex` (`id`),
  UNIQUE KEY `t_use_leader_leader_key_uindex` (`leader_key`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_user_leader
-- ----------------------------

-- ----------------------------
-- Table structure for `t_user_price`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_price`;
CREATE TABLE `t_user_price` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_code` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `remain_price` decimal(13,2) DEFAULT '0.00',
  `exchange_price` decimal(13,2) DEFAULT NULL,
  `consume_price` decimal(13,2) DEFAULT '0.00' COMMENT '消费金额',
  `create_time` bigint(20) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_user_price
-- ----------------------------
INSERT INTO `t_user_price` VALUES ('1', 'admin', '0.00', '0.00', '0.00', '1477364380000', '1481695692494');

INSERT INTO `t_user` VALUES ('1', 'admin', '管理员', '4297f44b13955235245b2497399d7a93', '', '', '1477364380000', '1477884008349');

INSERT INTO `t_user_leader` VALUES ('1', 'admin', '1', '1477756800000', '1580521600000', '1477878761000', '1477878761000');

--
-- password --123123
-- 4297f44b13955235245b2497399d7a93
--
