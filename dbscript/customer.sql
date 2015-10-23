/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50620
Source Host           : localhost:3306
Source Database       : suri_new

Target Server Type    : MYSQL
Target Server Version : 50620
File Encoding         : 65001

Date: 2015-10-23 19:18:15
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `calendar`
-- ----------------------------
DROP TABLE IF EXISTS `calendar`;
CREATE TABLE `calendar` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `color` varchar(20) DEFAULT NULL,
  `urgent` tinyint(4) NOT NULL COMMENT '1:正常，2：紧急',
  `status` tinyint(4) NOT NULL COMMENT '0:未确认,1:已确认,2:已删除',
  `client_id` int(11) NOT NULL,
  `admin_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of calendar
-- ----------------------------

-- ----------------------------
-- Table structure for `company`
-- ----------------------------
DROP TABLE IF EXISTS `company`;
CREATE TABLE `company` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `status` tinyint(8) NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of company
-- ----------------------------
INSERT INTO `company` VALUES ('1', '1', '0', '2015-10-15 17:22:27', '0000-00-00 00:00:00');

-- ----------------------------
-- Table structure for `contact`
-- ----------------------------
DROP TABLE IF EXISTS `contact`;
CREATE TABLE `contact` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(1000) NOT NULL,
  `attachment` varchar(1000) NOT NULL,
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0:not read,1:has read',
  `client_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of contact
-- ----------------------------
INSERT INTO `contact` VALUES ('6', 'test insert contents', 'http://localhost', '2015-10-21 14:36:19', null, '1', '63');
INSERT INTO `contact` VALUES ('7', 'test insert contents', 'http://localhost', '2015-10-21 14:37:30', null, '0', '63');
INSERT INTO `contact` VALUES ('8', 'contents', 'attachment', '2015-10-22 15:38:30', null, '0', '64');

-- ----------------------------
-- Table structure for `factory`
-- ----------------------------
DROP TABLE IF EXISTS `factory`;
CREATE TABLE `factory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `client_id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `status` tinyint(8) DEFAULT '0',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of factory
-- ----------------------------

-- ----------------------------
-- Table structure for `message`
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `send_time` datetime NOT NULL COMMENT '创建时间',
  `receive_time` varchar(50) NOT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0:not read,1:has read',
  `subject` varchar(1000) NOT NULL,
  `content` text NOT NULL,
  `attachment` varchar(1000) NOT NULL,
  `from` int(11) NOT NULL,
  `to` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------

-- ----------------------------
-- Table structure for `progress`
-- ----------------------------
DROP TABLE IF EXISTS `progress`;
CREATE TABLE `progress` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` int(11) NOT NULL,
  `short_name` text NOT NULL,
  `priority` int(11) NOT NULL DEFAULT '5',
  `status` tinyint(8) NOT NULL DEFAULT '0',
  `remarks` text NOT NULL,
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of progress
-- ----------------------------

-- ----------------------------
-- Table structure for `project`
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `factory_id` int(11) NOT NULL,
  `next_step` varchar(500) DEFAULT NULL,
  `remarks` text,
  `name` varchar(500) NOT NULL,
  `address` varchar(500) DEFAULT NULL,
  `status` tinyint(8) DEFAULT '0',
  `steps` text NOT NULL,
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of project
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` char(32) NOT NULL,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `telephone` varchar(30) DEFAULT NULL,
  `register_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `register_ip` varchar(50) DEFAULT NULL COMMENT '注册IP',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `last_login_ip` varchar(50) DEFAULT NULL COMMENT '最后登录IP',
  `status` tinyint(8) NOT NULL DEFAULT '0' COMMENT '0:正常 1:禁用',
  `type` tinyint(8) NOT NULL DEFAULT '0' COMMENT '0:客户 1:管理员 2:超级管理员',
  `other_company` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('63', 'username', '12345678901234567890123456789012', 'name1', 'email@email.com', '1233333', '2015-10-21 10:39:18', '2015-10-21 10:45:49', '127.0.0.1', null, null, '0', '0', null);
INSERT INTO `user` VALUES ('64', 'username', '12345678901234567890123456789012', 'name', 'email@email.com', '1233333', '2015-10-21 10:44:51', null, '127.0.0.1', null, null, '0', '0', null);
INSERT INTO `user` VALUES ('65', 'username1', '12345678901234567890123456789012', 'name', 'email@email.com', '1233333', '2015-10-21 10:45:00', null, '127.0.0.1', null, null, '0', '0', null);
INSERT INTO `user` VALUES ('66', 'username1', '12345678901234567890123456789012', 'name1', 'email@email.com', '1233333', '2015-10-21 10:45:30', null, '127.0.0.1', null, null, '0', '0', null);

-- ----------------------------
-- Table structure for `user_company`
-- ----------------------------
DROP TABLE IF EXISTS `user_company`;
CREATE TABLE `user_company` (
  `company_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`company_id`,`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_company
-- ----------------------------
INSERT INTO `user_company` VALUES ('1', '63');
INSERT INTO `user_company` VALUES ('1', '64');
