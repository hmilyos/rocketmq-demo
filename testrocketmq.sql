/*
Navicat MySQL Data Transfer

Source Server         : 120.79.151.179
Source Server Version : 50718
Source Host           : 120.79.151.179:3306
Source Database       : testrocketmq

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2018-11-20 08:41:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `broker_message_log`
-- ----------------------------
DROP TABLE IF EXISTS `broker_message_log`;
CREATE TABLE `broker_message_log` (
  `message_id` varchar(32) NOT NULL,
  `message` varchar(400) DEFAULT NULL,
  `try_count` int(5) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `next_retry` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`message_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of broker_message_log
-- ----------------------------

-- ----------------------------
-- Table structure for `t_customer_account`
-- ----------------------------
DROP TABLE IF EXISTS `t_customer_account`;
CREATE TABLE `t_customer_account` (
  `account_id` varchar(32) NOT NULL,
  `account_no` varchar(32) DEFAULT NULL,
  `date_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `current_balance` decimal(15,2) DEFAULT NULL,
  `version` int(10) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of t_customer_account
-- ----------------------------

-- ----------------------------
-- Table structure for `t_order`
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `order_id` varchar(32) NOT NULL,
  `order_type` varchar(10) DEFAULT NULL,
  `city_id` varchar(32) DEFAULT NULL,
  `platform_id` varchar(32) DEFAULT NULL,
  `user_id` varchar(32) DEFAULT NULL,
  `supplier_id` varchar(32) DEFAULT NULL,
  `goods_id` varchar(32) DEFAULT NULL,
  `order_status` varchar(32) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `create_by` varchar(50) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_by` varchar(50) NOT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`order_id`),
  UNIQUE KEY `order_index` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of t_order
-- ----------------------------

-- ----------------------------
-- Table structure for `t_package`
-- ----------------------------
DROP TABLE IF EXISTS `t_package`;
CREATE TABLE `t_package` (
  `package_id` varchar(32) NOT NULL,
  `order_id` varchar(32) DEFAULT NULL,
  `supplier_id` varchar(32) DEFAULT NULL,
  `address_id` varchar(32) DEFAULT NULL,
  `remark` varchar(40) DEFAULT NULL,
  `package_status` varchar(10) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`package_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of t_package
-- ----------------------------

-- ----------------------------
-- Table structure for `t_platform_account`
-- ----------------------------
DROP TABLE IF EXISTS `t_platform_account`;
CREATE TABLE `t_platform_account` (
  `account_id` varchar(32) NOT NULL,
  `account_no` varchar(32) DEFAULT NULL,
  `date_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `current_balance` decimal(15,2) DEFAULT NULL,
  `version` int(10) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of t_platform_account
-- ----------------------------

-- ----------------------------
-- Table structure for `t_store`
-- ----------------------------
DROP TABLE IF EXISTS `t_store`;
CREATE TABLE `t_store` (
  `store_id` varchar(32) NOT NULL,
  `goods_id` varchar(32) DEFAULT NULL,
  `supplier_id` varchar(32) DEFAULT NULL,
  `goods_name` varchar(40) DEFAULT NULL,
  `store_count` int(10) NOT NULL,
  `version` int(10) NOT NULL,
  `create_by` varchar(50) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_by` varchar(50) NOT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`store_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of t_store
-- ----------------------------
