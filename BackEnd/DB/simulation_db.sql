/*
Navicat MySQL Data Transfer

Source Server         : SimlationDB
Source Server Version : 50723
Source Host           : localhost:3306
Source Database       : simulation_db

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2020-07-21 22:40:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `aisle`
-- ----------------------------
DROP TABLE IF EXISTS `aisle`;
CREATE TABLE `aisle` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `packing_station_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbeecs71i2y5mi4nfykm9e15n` (`packing_station_id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of aisle
-- ----------------------------
INSERT INTO `aisle` VALUES ('1', 'a1', '1');
INSERT INTO `aisle` VALUES ('2', 'a2', '2');
INSERT INTO `aisle` VALUES ('3', 'a3', '3');
INSERT INTO `aisle` VALUES ('4', 'a4', '4');

-- ----------------------------
-- Table structure for `authority`
-- ----------------------------
DROP TABLE IF EXISTS `authority`;
CREATE TABLE `authority` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of authority
-- ----------------------------
INSERT INTO `authority` VALUES ('1', 'u-c', 'User Create');
INSERT INTO `authority` VALUES ('2', 'u-v', 'User View');
INSERT INTO `authority` VALUES ('3', 'r-c', 'Role Create');
INSERT INTO `authority` VALUES ('4', 'r-v', 'Role View');

-- ----------------------------
-- Table structure for `item`
-- ----------------------------
DROP TABLE IF EXISTS `item`;
CREATE TABLE `item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `product_id` varchar(255) DEFAULT NULL,
  `supplier` varchar(255) DEFAULT NULL,
  `weight` int(11) DEFAULT NULL,
  `shelf_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2eu1b1viw48cnc2ei5prordq5` (`shelf_id`)
) ENGINE=MyISAM AUTO_INCREMENT=41 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of item
-- ----------------------------
INSERT INTO `item` VALUES ('1', 'Kit Kat', 'kitkat', 'Nestle', '2', '1');
INSERT INTO `item` VALUES ('2', 'Choco La', 'chocola', 'Ritzbury', '1', '2');
INSERT INTO `item` VALUES ('3', 'Munchi Super Cream Cracker', 'munchiscc', 'Munchi', '3', '3');
INSERT INTO `item` VALUES ('4', 'Promises', 'promises', 'Kandos', '2', '4');
INSERT INTO `item` VALUES ('5', 'Slide Bread', 'sbread', 'Keels', '4', '5');
INSERT INTO `item` VALUES ('6', 'Flavoured Slide Bread', 'sfbread', 'keels', '2', '6');
INSERT INTO `item` VALUES ('7', 'Astra Butter', 'abutter', 'Unilever', '1', '7');
INSERT INTO `item` VALUES ('8', 'Medoli', 'mbutter', 'Medoli', '1', '8');
INSERT INTO `item` VALUES ('9', 'Coca Cola', 'coke', 'CocaCola', '4', '9');
INSERT INTO `item` VALUES ('10', 'Sprite', 'sprite', 'Sprite', '4', '10');
INSERT INTO `item` VALUES ('11', 'Farm Eggs Pack', 'feggs', 'Central Farm', '2', '11');
INSERT INTO `item` VALUES ('12', 'Farm Chickan', 'fchickan', 'Crisbo', '5', '12');
INSERT INTO `item` VALUES ('13', 'White Suger', 'wsuger', 'CeylonSuger', '3', '13');
INSERT INTO `item` VALUES ('14', 'Brown Suger', 'bsuger', 'CeylonSuger', '3', '14');
INSERT INTO `item` VALUES ('15', 'Ceylonta Tea', 'cteapowder', 'Ceylonta', '2', '15');
INSERT INTO `item` VALUES ('16', 'Kotagala Tea', 'kteapowder', 'Kotagala', '2', '16');
INSERT INTO `item` VALUES ('17', 'Red Rice', 'redrice', 'Araliya', '10', '17');
INSERT INTO `item` VALUES ('18', 'White Rice', 'whiterice', 'Nipuna', '10', '18');
INSERT INTO `item` VALUES ('19', 'Basmathi', 'basmathi', 'Araliya', '15', '19');
INSERT INTO `item` VALUES ('20', 'Dhal Budget Pack', 'dhal', 'Mayisoor', '4', '20');
INSERT INTO `item` VALUES ('21', 'Product21', 'prcode21', 'supplier21', '21', '21');
INSERT INTO `item` VALUES ('22', 'Product22', 'prcode22', 'supplier22', '22', '22');
INSERT INTO `item` VALUES ('23', 'Product23', 'prcode23', 'supplier23', '23', '23');
INSERT INTO `item` VALUES ('24', 'Product24', 'prcode24', 'supplier24', '24', '24');
INSERT INTO `item` VALUES ('25', 'Product25', 'prcode25', 'supplier25', '21', '25');
INSERT INTO `item` VALUES ('26', 'Product26', 'prcode26', 'supplier26', '23', '26');
INSERT INTO `item` VALUES ('27', 'Product27', 'prcode27', 'supplier27', '22', '27');
INSERT INTO `item` VALUES ('28', 'Product28', 'prcode28', 'supplier28', '22', '28');
INSERT INTO `item` VALUES ('29', 'Product29', 'prcode29', 'supplier29', '21', '29');
INSERT INTO `item` VALUES ('30', 'Product30', 'prcode30', 'supplier30', '25', '30');
INSERT INTO `item` VALUES ('31', 'Product31', 'prcode31', 'supplier31', '21', '31');
INSERT INTO `item` VALUES ('32', 'Product32', 'prcode32', 'supplier32', '22', '32');
INSERT INTO `item` VALUES ('33', 'Product33', 'prcode33', 'supplier33', '5', '33');
INSERT INTO `item` VALUES ('34', 'Product34', 'prcode34', 'supplier34', '10', '34');
INSERT INTO `item` VALUES ('35', 'Product35', 'prcode35', 'supplier35', '15', '35');
INSERT INTO `item` VALUES ('36', 'Product36', 'prcode36', 'supplier36', '20', '36');
INSERT INTO `item` VALUES ('37', 'Product37', 'prcode37', 'supplier37', '25', '37');
INSERT INTO `item` VALUES ('38', 'Product38', 'prcode38', 'supplier38', '12', '38');
INSERT INTO `item` VALUES ('39', 'Product39', 'prcode39', 'supplier39', '14', '39');
INSERT INTO `item` VALUES ('40', 'Product40', 'prcode40', 'supplier40', '8', '40');

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
-- Table structure for `packing_station`
-- ----------------------------
DROP TABLE IF EXISTS `packing_station`;
CREATE TABLE `packing_station` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of packing_station
-- ----------------------------
INSERT INTO `packing_station` VALUES ('1', 'PA1');
INSERT INTO `packing_station` VALUES ('2', 'PA2');
INSERT INTO `packing_station` VALUES ('3', 'PA3');
INSERT INTO `packing_station` VALUES ('4', 'PA4');

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'Admin Role', 'Admin');
INSERT INTO `role` VALUES ('2', ' Picker Role', 'Picker');
INSERT INTO `role` VALUES ('3', 'Packer Role', 'Packer');

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
INSERT INTO `role_authority` VALUES ('1', '1');
INSERT INTO `role_authority` VALUES ('1', '2');
INSERT INTO `role_authority` VALUES ('1', '3');
INSERT INTO `role_authority` VALUES ('1', '4');
INSERT INTO `role_authority` VALUES ('2', '2');

-- ----------------------------
-- Table structure for `section`
-- ----------------------------
DROP TABLE IF EXISTS `section`;
CREATE TABLE `section` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `section_id` varchar(255) DEFAULT NULL,
  `aisle_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmy502ljtxn6hun832lhvtw6cj` (`aisle_id`)
) ENGINE=MyISAM AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of section
-- ----------------------------
INSERT INTO `section` VALUES ('1', 'a1.0', '1');
INSERT INTO `section` VALUES ('2', 'a1.1', '1');
INSERT INTO `section` VALUES ('3', 'a1.2', '1');
INSERT INTO `section` VALUES ('4', 'a1.3', '1');
INSERT INTO `section` VALUES ('5', 'a1.4', '1');
INSERT INTO `section` VALUES ('6', 'a2.0', '2');
INSERT INTO `section` VALUES ('7', 'a2.1', '2');
INSERT INTO `section` VALUES ('8', 'a2.2', '2');
INSERT INTO `section` VALUES ('9', 'a2.3', '2');
INSERT INTO `section` VALUES ('10', 'a2.4', '2');
INSERT INTO `section` VALUES ('11', 'a3.0', '3');
INSERT INTO `section` VALUES ('12', 'a3.1', '3');
INSERT INTO `section` VALUES ('13', 'a3.2', '3');
INSERT INTO `section` VALUES ('14', 'a3.3', '3');
INSERT INTO `section` VALUES ('15', 'a3.4', '3');
INSERT INTO `section` VALUES ('16', 'a4.0', '4');
INSERT INTO `section` VALUES ('17', 'a4.1', '4');
INSERT INTO `section` VALUES ('18', 'a4.2', '4');
INSERT INTO `section` VALUES ('19', 'a4.3', '4');
INSERT INTO `section` VALUES ('20', 'a4.4', '4');

-- ----------------------------
-- Table structure for `shelf`
-- ----------------------------
DROP TABLE IF EXISTS `shelf`;
CREATE TABLE `shelf` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `label` varchar(255) DEFAULT NULL,
  `section_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqqiyfbbunamkx0dauqviijifw` (`section_id`)
) ENGINE=MyISAM AUTO_INCREMENT=41 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of shelf
-- ----------------------------
INSERT INTO `shelf` VALUES ('1', 'a1.0/A', '1');
INSERT INTO `shelf` VALUES ('2', 'a1.0/B', '1');
INSERT INTO `shelf` VALUES ('3', 'a1.1/A', '2');
INSERT INTO `shelf` VALUES ('4', 'a1.1/B', '2');
INSERT INTO `shelf` VALUES ('5', 'a1.2/A', '3');
INSERT INTO `shelf` VALUES ('6', 'a1.2/B', '3');
INSERT INTO `shelf` VALUES ('7', 'a1.3/A', '4');
INSERT INTO `shelf` VALUES ('8', 'a1.3/B', '4');
INSERT INTO `shelf` VALUES ('9', 'a1.4/A', '5');
INSERT INTO `shelf` VALUES ('10', 'a1.4/B', '5');
INSERT INTO `shelf` VALUES ('11', 'a2.0/A', '6');
INSERT INTO `shelf` VALUES ('12', 'a2.0/B', '6');
INSERT INTO `shelf` VALUES ('13', 'a2.1/A', '7');
INSERT INTO `shelf` VALUES ('14', 'a2.1/B', '7');
INSERT INTO `shelf` VALUES ('15', 'a2.2/A', '8');
INSERT INTO `shelf` VALUES ('16', 'a2.2/B', '8');
INSERT INTO `shelf` VALUES ('17', 'a2.3/A', '9');
INSERT INTO `shelf` VALUES ('18', 'a2.3/B', '9');
INSERT INTO `shelf` VALUES ('19', 'a2.4/A', '10');
INSERT INTO `shelf` VALUES ('20', 'a2.4/B', '10');
INSERT INTO `shelf` VALUES ('21', 'a3.0/A', '11');
INSERT INTO `shelf` VALUES ('22', 'a3.0/B', '11');
INSERT INTO `shelf` VALUES ('23', 'a3.1/A', '12');
INSERT INTO `shelf` VALUES ('24', 'a3.1/B', '12');
INSERT INTO `shelf` VALUES ('25', 'a3.2/A', '13');
INSERT INTO `shelf` VALUES ('26', 'a3.2/B', '13');
INSERT INTO `shelf` VALUES ('27', 'a3.3/A', '14');
INSERT INTO `shelf` VALUES ('28', 'a3.3/B', '14');
INSERT INTO `shelf` VALUES ('29', 'a3.4/A', '15');
INSERT INTO `shelf` VALUES ('30', 'a3.4/B', '15');
INSERT INTO `shelf` VALUES ('31', 'a4.0/A', '16');
INSERT INTO `shelf` VALUES ('32', 'a4.0/B', '16');
INSERT INTO `shelf` VALUES ('33', 'a4.1/A', '17');
INSERT INTO `shelf` VALUES ('34', 'a4.1/B', '17');
INSERT INTO `shelf` VALUES ('35', 'a4.2/A', '18');
INSERT INTO `shelf` VALUES ('36', 'a4.2/B', '18');
INSERT INTO `shelf` VALUES ('37', 'a4.3/A', '19');
INSERT INTO `shelf` VALUES ('38', 'a4.3/B', '19');
INSERT INTO `shelf` VALUES ('39', 'a4.4/A', '20');
INSERT INTO `shelf` VALUES ('40', 'a4.4/B', '20');

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
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'Mr Admin', 'raveen@gmail.com', '{bcrypt}$2a$10$nJOEN8c7eDwVJkIexoPocuyHFVsd/x2uLYhkaAys6rOApIYDkLoTO', 'A', '1');
INSERT INTO `user` VALUES ('3', 'Mr Picker', 'picker@gmail.com', '{bcrypt}$2a$10$434aPt9UTJ00xQtn.Nvt6ud4706ZdzS/96KVSDbmU1rpNLM.ik4EC', 'A', '2');
INSERT INTO `user` VALUES ('4', 'Mr Packer', 'packer@gmail.com', '{bcrypt}$2a$10$HpdHTyecR29ogO1Ss807zOqJN9YQs1d9ej0MWgVnbUMS3HPvmR4fm', 'A', '3');

-- ----------------------------
-- Table structure for `worker`
-- ----------------------------
DROP TABLE IF EXISTS `worker`;
CREATE TABLE `worker` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `capacity` int(11) DEFAULT NULL,
  `is_available` bit(1) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of worker
-- ----------------------------
INSERT INTO `worker` VALUES ('1', '20', '', 'bob', 'PI', '25');
INSERT INTO `worker` VALUES ('2', '10', '', 'rem', 'PI', '45');
INSERT INTO `worker` VALUES ('3', '20', '', 'steve', 'PA', '25');
INSERT INTO `worker` VALUES ('4', '25', '', 'john', 'PI', '35');
INSERT INTO `worker` VALUES ('5', '20', '', 'kusalka', 'PI', '25');
INSERT INTO `worker` VALUES ('6', '10', '', 'shane', 'PI', '45');
INSERT INTO `worker` VALUES ('7', '20', '', 'nilushi', 'PA', '25');
INSERT INTO `worker` VALUES ('8', '25', '', 'Mike', 'PI', '35');
INSERT INTO `worker` VALUES ('9', '20', '', 'jenifer', 'PI', '15');
INSERT INTO `worker` VALUES ('10', '10', '', 'Singer', 'PA', '45');
INSERT INTO `worker` VALUES ('11', '20', '', 'Ajay', 'PA', '25');
INSERT INTO `worker` VALUES ('12', '25', '', 'Kingston', 'PI', '35');
INSERT INTO `worker` VALUES ('13', '20', '', 'Max', 'PI', '15');
INSERT INTO `worker` VALUES ('14', '10', '', 'Dexter', 'PI', '15');
INSERT INTO `worker` VALUES ('15', '20', '', 'Jonny', 'PI', '5');
INSERT INTO `worker` VALUES ('16', '25', '', 'Morgan', 'PI', '45');
