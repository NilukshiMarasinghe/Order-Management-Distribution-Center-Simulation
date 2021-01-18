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
  `access_token_validity` int DEFAULT NULL,
  `refresh_token_validity` int DEFAULT NULL,
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO `oauth_client_details` VALUES ('ASP', null, 'V1I6r%a)^4jfFG7g34', 'AP', 'client_credentials', null, null, '86400', null, null, null);
INSERT INTO `oauth_client_details` VALUES ('CPAP', 'AUS,SS,PS', 'Cp43&$^fdgd*+!!@#Agdo4Ged', 'AP', 'password,refresh_token', null, null, '1800', '86400', null, null);
INSERT INTO `oauth_client_details` VALUES ('GSP', '', 'v1i6R%6aVtutr*&^ghdd', 'AP', 'client_credentials', '', null, '86400', null, null, null);

-- Create New Authority

INSERT INTO `authority` VALUES ('1', 'u-c', 'User Create');
INSERT INTO `authority` VALUES ('2', 'u-v', 'User View');
INSERT INTO `authority` VALUES ('3', 'r-c', 'Role Create');
INSERT INTO `authority` VALUES ('4', 'r-v', 'Role View');

-- Create New Role
INSERT INTO `role` VALUES ('1', 'Admin Role', 'Admin');


-- Adding authorities to role (M:N)
INSERT INTO `role_authority` VALUES ('1','1');
INSERT INTO `role_authority` VALUES ('1','2');
INSERT INTO `role_authority` VALUES ('1','3');
INSERT INTO `role_authority` VALUES ('1','4');


