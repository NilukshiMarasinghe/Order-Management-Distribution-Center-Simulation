/*
Navicat MySQL Data Transfer

Source Server         : WorkerDB
Source Server Version : 50723
Source Host           : localhost:3306
Source Database       : worker_db

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2020-07-21 22:40:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `authority`
-- ----------------------------
DROP TABLE IF EXISTS `authority`;
CREATE TABLE `authority` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of authority
-- ----------------------------

-- ----------------------------
-- Table structure for `item`
-- ----------------------------
DROP TABLE IF EXISTS `item`;
CREATE TABLE `item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=49 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of item
-- ----------------------------
INSERT INTO `item` VALUES ('1', 'kitkat');
INSERT INTO `item` VALUES ('2', 'chocola');
INSERT INTO `item` VALUES ('3', 'promises');
INSERT INTO `item` VALUES ('4', 'kitkat');
INSERT INTO `item` VALUES ('5', 'chocola');
INSERT INTO `item` VALUES ('6', 'promises');
INSERT INTO `item` VALUES ('7', 'kitkat');
INSERT INTO `item` VALUES ('8', 'chocola');
INSERT INTO `item` VALUES ('9', 'promises');
INSERT INTO `item` VALUES ('10', 'kitkat');
INSERT INTO `item` VALUES ('11', 'chocola');
INSERT INTO `item` VALUES ('12', 'promises');
INSERT INTO `item` VALUES ('13', 'kitkat');
INSERT INTO `item` VALUES ('14', 'chocola');
INSERT INTO `item` VALUES ('15', 'promises');
INSERT INTO `item` VALUES ('16', 'kitkat');
INSERT INTO `item` VALUES ('17', 'chocola');
INSERT INTO `item` VALUES ('18', 'promises');
INSERT INTO `item` VALUES ('19', 'kitkat');
INSERT INTO `item` VALUES ('20', 'chocola');
INSERT INTO `item` VALUES ('21', 'promises');
INSERT INTO `item` VALUES ('22', 'kitkat');
INSERT INTO `item` VALUES ('23', 'chocola');
INSERT INTO `item` VALUES ('24', 'promises');
INSERT INTO `item` VALUES ('25', 'kitkat');
INSERT INTO `item` VALUES ('26', 'chocola');
INSERT INTO `item` VALUES ('27', 'promises');
INSERT INTO `item` VALUES ('28', 'kitkat');
INSERT INTO `item` VALUES ('29', 'chocola');
INSERT INTO `item` VALUES ('30', 'promises');
INSERT INTO `item` VALUES ('31', 'kitkat');
INSERT INTO `item` VALUES ('32', 'chocola');
INSERT INTO `item` VALUES ('33', 'promises');
INSERT INTO `item` VALUES ('34', 'kitkat');
INSERT INTO `item` VALUES ('35', 'chocola');
INSERT INTO `item` VALUES ('36', 'promises');
INSERT INTO `item` VALUES ('37', 'kitkat');
INSERT INTO `item` VALUES ('38', 'chocola');
INSERT INTO `item` VALUES ('39', 'promises');
INSERT INTO `item` VALUES ('40', 'kitkat');
INSERT INTO `item` VALUES ('41', 'chocola');
INSERT INTO `item` VALUES ('42', 'promises');
INSERT INTO `item` VALUES ('43', 'kitkat');
INSERT INTO `item` VALUES ('44', 'chocola');
INSERT INTO `item` VALUES ('45', 'promises');
INSERT INTO `item` VALUES ('46', 'kitkat');
INSERT INTO `item` VALUES ('47', 'chocola');
INSERT INTO `item` VALUES ('48', 'promises');

-- ----------------------------
-- Table structure for `oauth_client_details`
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(256) NOT NULL,
  `resource_ids` varchar(256) DEFAULT NULL,
  `client_secret` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `authorized_grant_types` varchar(256) DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO `oauth_client_details` VALUES ('ASP', null, 'V1I6r%a)^4jfFG7g34', 'AP', 'client_credentials', null, null, '86400', null, null, null);
INSERT INTO `oauth_client_details` VALUES ('CPAP', 'AUS,SS,PS', 'Cp43&$^fdgd*+!!@#Agdo4Ged', 'AP', 'password,refresh_token', null, null, '1800', '86400', null, null);
INSERT INTO `oauth_client_details` VALUES ('GSP', '', 'v1i6R%6aVtutr*&^ghdd', 'AP', 'client_credentials', '', null, '86400', null, null, null);

-- ----------------------------
-- Table structure for `order_item`
-- ----------------------------
DROP TABLE IF EXISTS `order_item`;
CREATE TABLE `order_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `qty` int(11) NOT NULL,
  `item_id` bigint(20) DEFAULT NULL,
  `task_record_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKija6hjjiit8dprnmvtvgdp6ru` (`item_id`),
  KEY `FKa2pfbjwx28wa677a6ua2e2jvt` (`task_record_id`)
) ENGINE=MyISAM AUTO_INCREMENT=49 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of order_item
-- ----------------------------
INSERT INTO `order_item` VALUES ('1', '3', '1', '1');
INSERT INTO `order_item` VALUES ('2', '5', '2', '1');
INSERT INTO `order_item` VALUES ('3', '5', '3', '1');
INSERT INTO `order_item` VALUES ('4', '3', '4', '2');
INSERT INTO `order_item` VALUES ('5', '5', '5', '2');
INSERT INTO `order_item` VALUES ('6', '5', '6', '2');
INSERT INTO `order_item` VALUES ('7', '3', '7', '3');
INSERT INTO `order_item` VALUES ('8', '5', '8', '3');
INSERT INTO `order_item` VALUES ('9', '5', '9', '3');
INSERT INTO `order_item` VALUES ('10', '3', '10', '4');
INSERT INTO `order_item` VALUES ('11', '5', '11', '4');
INSERT INTO `order_item` VALUES ('12', '5', '12', '4');
INSERT INTO `order_item` VALUES ('13', '3', '13', '5');
INSERT INTO `order_item` VALUES ('14', '5', '14', '5');
INSERT INTO `order_item` VALUES ('15', '5', '15', '5');
INSERT INTO `order_item` VALUES ('16', '3', '16', '6');
INSERT INTO `order_item` VALUES ('17', '5', '17', '6');
INSERT INTO `order_item` VALUES ('18', '5', '18', '6');
INSERT INTO `order_item` VALUES ('19', '3', '19', '7');
INSERT INTO `order_item` VALUES ('20', '5', '20', '7');
INSERT INTO `order_item` VALUES ('21', '5', '21', '7');
INSERT INTO `order_item` VALUES ('22', '3', '22', '8');
INSERT INTO `order_item` VALUES ('23', '5', '23', '8');
INSERT INTO `order_item` VALUES ('24', '5', '24', '8');
INSERT INTO `order_item` VALUES ('25', '3', '25', '9');
INSERT INTO `order_item` VALUES ('26', '5', '26', '9');
INSERT INTO `order_item` VALUES ('27', '5', '27', '9');
INSERT INTO `order_item` VALUES ('28', '3', '28', '10');
INSERT INTO `order_item` VALUES ('29', '5', '29', '10');
INSERT INTO `order_item` VALUES ('30', '5', '30', '10');
INSERT INTO `order_item` VALUES ('31', '3', '31', '11');
INSERT INTO `order_item` VALUES ('32', '5', '32', '11');
INSERT INTO `order_item` VALUES ('33', '5', '33', '11');
INSERT INTO `order_item` VALUES ('34', '3', '34', '12');
INSERT INTO `order_item` VALUES ('35', '5', '35', '12');
INSERT INTO `order_item` VALUES ('36', '5', '36', '12');
INSERT INTO `order_item` VALUES ('37', '3', '37', '13');
INSERT INTO `order_item` VALUES ('38', '5', '38', '13');
INSERT INTO `order_item` VALUES ('39', '5', '39', '13');
INSERT INTO `order_item` VALUES ('40', '3', '40', '14');
INSERT INTO `order_item` VALUES ('41', '5', '41', '14');
INSERT INTO `order_item` VALUES ('42', '5', '42', '14');
INSERT INTO `order_item` VALUES ('43', '3', '43', '15');
INSERT INTO `order_item` VALUES ('44', '5', '44', '15');
INSERT INTO `order_item` VALUES ('45', '5', '45', '15');
INSERT INTO `order_item` VALUES ('46', '3', '46', '16');
INSERT INTO `order_item` VALUES ('47', '5', '47', '16');
INSERT INTO `order_item` VALUES ('48', '5', '48', '16');

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of role
-- ----------------------------

-- ----------------------------
-- Table structure for `role_authority`
-- ----------------------------
DROP TABLE IF EXISTS `role_authority`;
CREATE TABLE `role_authority` (
  `role_id` bigint(20) NOT NULL,
  `authority_id` bigint(20) NOT NULL,
  PRIMARY KEY (`role_id`,`authority_id`),
  KEY `FKqbri833f7xop13bvdje3xxtnw` (`authority_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of role_authority
-- ----------------------------

-- ----------------------------
-- Table structure for `task_record`
-- ----------------------------
DROP TABLE IF EXISTS `task_record`;
CREATE TABLE `task_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` varchar(255) DEFAULT NULL,
  `packing_station_id` varchar(255) DEFAULT NULL,
  `shorted_path` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `task_id` varchar(255) DEFAULT NULL,
  `worker_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of task_record
-- ----------------------------
INSERT INTO `task_record` VALUES ('1', '5f15be2e82e8a312446e8321', '2', '(a1.0 : a1.1)|(a1.1 : a1.2)|(a1.2 : a1.3)|(a1.3 : a2.3)|(a2.3 : a3.3)|(a3.3 : a3.4)|(a3.4 : PA3)|(PA3 : PA2)', 'PROCESSING', '5f15be2e82e8a312446e8321', '1');
INSERT INTO `task_record` VALUES ('2', '5f15be2e82e8a312446e8321', '2', '(a1.0 : a1.1)|(a1.1 : a1.2)|(a1.2 : a1.3)|(a1.3 : a2.3)|(a2.3 : a3.3)|(a3.3 : a3.4)|(a3.4 : PA3)|(PA3 : PA2)', 'PROCESSING', '5f15be2e82e8a312446e8321', '1');
INSERT INTO `task_record` VALUES ('3', '5f15be2e82e8a312446e8321', '2', '(a1.0 : a1.1)|(a1.1 : a1.2)|(a1.2 : a1.3)|(a1.3 : a2.3)|(a2.3 : a3.3)|(a3.3 : a3.4)|(a3.4 : PA3)|(PA3 : PA2)', 'PROCESSING', '5f15be2e82e8a312446e8321', '1');
INSERT INTO `task_record` VALUES ('4', '5f15be2e82e8a312446e8321', '2', '(a1.0 : a1.1)|(a1.1 : a1.2)|(a1.2 : a1.3)|(a1.3 : a2.3)|(a2.3 : a3.3)|(a3.3 : a3.4)|(a3.4 : PA3)|(PA3 : PA2)', 'PROCESSING', '5f15be2e82e8a312446e8321', '1');
INSERT INTO `task_record` VALUES ('5', '5f15be2e82e8a312446e8321', '2', '(a1.0 : a1.1)|(a1.1 : a1.2)|(a1.2 : a1.3)|(a1.3 : a2.3)|(a2.3 : a3.3)|(a3.3 : a3.4)|(a3.4 : PA3)|(PA3 : PA2)', 'PROCESSING', '5f15be2e82e8a312446e8321', '1');
INSERT INTO `task_record` VALUES ('6', '5f15be2e82e8a312446e8321', '2', '(a1.0 : a1.1)|(a1.1 : a1.2)|(a1.2 : a1.3)|(a1.3 : a2.3)|(a2.3 : a3.3)|(a3.3 : a3.4)|(a3.4 : PA3)|(PA3 : PA2)', 'PROCESSING', '5f15be2e82e8a312446e8321', '1');
INSERT INTO `task_record` VALUES ('7', '5f15be2e82e8a312446e8321', '2', '(a1.0 : a1.1)|(a1.1 : a1.2)|(a1.2 : a1.3)|(a1.3 : a2.3)|(a2.3 : a3.3)|(a3.3 : a3.4)|(a3.4 : PA3)|(PA3 : PA2)', 'PROCESSING', '5f15be2e82e8a312446e8321', '1');
INSERT INTO `task_record` VALUES ('8', '5f15be2e82e8a312446e8321', '2', '(a1.0 : a1.1)|(a1.1 : a1.2)|(a1.2 : a1.3)|(a1.3 : a2.3)|(a2.3 : a3.3)|(a3.3 : a3.4)|(a3.4 : PA3)|(PA3 : PA2)', 'PROCESSING', '5f15be2e82e8a312446e8321', '1');
INSERT INTO `task_record` VALUES ('9', '5f15be2e82e8a312446e8321', '2', '(a1.0 : a1.1)|(a1.1 : a1.2)|(a1.2 : a1.3)|(a1.3 : a2.3)|(a2.3 : a3.3)|(a3.3 : a3.4)|(a3.4 : PA3)|(PA3 : PA2)', 'PROCESSING', '5f15be2e82e8a312446e8321', '1');
INSERT INTO `task_record` VALUES ('10', '5f15be2e82e8a312446e8321', '2', '(a1.0 : a1.1)|(a1.1 : a1.2)|(a1.2 : a1.3)|(a1.3 : a2.3)|(a2.3 : a3.3)|(a3.3 : a3.4)|(a3.4 : PA3)|(PA3 : PA2)', 'PROCESSING', '5f15be2e82e8a312446e8321', '1');
INSERT INTO `task_record` VALUES ('11', '5f15be2e82e8a312446e8321', '2', '(a1.0 : a1.1)|(a1.1 : a1.2)|(a1.2 : a1.3)|(a1.3 : a2.3)|(a2.3 : a3.3)|(a3.3 : a3.4)|(a3.4 : PA3)|(PA3 : PA2)', 'PROCESSING', '5f15be2e82e8a312446e8321', '1');
INSERT INTO `task_record` VALUES ('12', '5f15be2e82e8a312446e8321', '2', '(a1.0 : a1.1)|(a1.1 : a1.2)|(a1.2 : a1.3)|(a1.3 : a2.3)|(a2.3 : a3.3)|(a3.3 : a3.4)|(a3.4 : PA3)|(PA3 : PA2)', 'PROCESSING', '5f15be2e82e8a312446e8321', '1');
INSERT INTO `task_record` VALUES ('13', '5f15be2e82e8a312446e8321', '2', '(a1.0 : a1.1)|(a1.1 : a1.2)|(a1.2 : a1.3)|(a1.3 : a2.3)|(a2.3 : a3.3)|(a3.3 : a3.4)|(a3.4 : PA3)|(PA3 : PA2)', 'PROCESSING', '5f15be2e82e8a312446e8321', '1');
INSERT INTO `task_record` VALUES ('14', '5f15be2e82e8a312446e8321', '2', '(a1.0 : a1.1)|(a1.1 : a1.2)|(a1.2 : a1.3)|(a1.3 : a2.3)|(a2.3 : a3.3)|(a3.3 : a3.4)|(a3.4 : PA3)|(PA3 : PA2)', 'PROCESSING', '5f15be2e82e8a312446e8321', '1');
INSERT INTO `task_record` VALUES ('15', '5f15be2e82e8a312446e8321', '2', '(a1.0 : a1.1)|(a1.1 : a1.2)|(a1.2 : a1.3)|(a1.3 : a2.3)|(a2.3 : a3.3)|(a3.3 : a3.4)|(a3.4 : PA3)|(PA3 : PA2)', 'PROCESSING', '5f15be2e82e8a312446e8321', '1');
INSERT INTO `task_record` VALUES ('16', '5f15be2e82e8a312446e8321', '2', '(a1.0 : a1.1)|(a1.1 : a1.2)|(a1.2 : a1.3)|(a1.3 : a2.3)|(a2.3 : a3.3)|(a3.3 : a3.4)|(a3.4 : PA3)|(PA3 : PA2)', 'PROCESSING', '5f15be2e82e8a312446e8321', '1');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKn82ha3ccdebhokx3a8fgdqeyy` (`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of user
-- ----------------------------

-- ----------------------------
-- Table structure for `worker`
-- ----------------------------
DROP TABLE IF EXISTS `worker`;
CREATE TABLE `worker` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `age` int(11) DEFAULT NULL,
  `capacity` int(11) DEFAULT NULL,
  `is_available` bit(1) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of worker
-- ----------------------------
