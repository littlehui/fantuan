/*
 Navicat Premium Data Transfer

 Source Server         : fantuan
 Source Server Type    : MySQL
 Source Server Version : 50520
 Source Host           : 10.5.32.97
 Source Database       : fantuan

 Target Server Type    : MySQL
 Target Server Version : 50520
 File Encoding         : utf-8

 Date: 04/28/2018 16:01:29 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `t_consume`
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
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Table structure for `t_exchange`
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
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Table structure for `t_operate_log`
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
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='操作日志';

-- ----------------------------
--  Table structure for `t_recharge`
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
--  Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_code` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `user_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `mobile` varchar(11) COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  `god_flag` bit(1) DEFAULT b'0' COMMENT '是否为超管',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Records of `t_user`
-- ----------------------------
BEGIN;
INSERT INTO `t_user` VALUES ('100', 'admin', '管理员', '4297f44b13955235245b2497399d7a93', null, null, '1477364380000', '1477884008349', b'1');
COMMIT;

-- ----------------------------
--  Table structure for `t_user_leader`
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
) ENGINE=InnoDB AUTO_INCREMENT=112 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Records of `t_user_leader`
-- ----------------------------
BEGIN;
INSERT INTO `t_user_leader` VALUES ('100', 'admin', '100', '1477756800000', '1580521600000', '1477878761000', '1477878761000');
COMMIT;

-- ----------------------------
--  Table structure for `t_user_price`
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
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Records of `t_user_price`
-- ----------------------------
BEGIN;
INSERT INTO `t_user_price` VALUES ('100', 'admin', '0.00', '0.00', '0.00', '1477364380000', '1524738262492');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;

-- 初始化 后的用户名 admin,密码：123123
-- 添加新用户的初始化密码也是123123
-- password --123123
-- 4297f44b13955235245b2497399d7a93
--