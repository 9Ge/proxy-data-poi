/*
 Navicat Premium Data Transfer

 Source Server         : shenjianchao
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : 192.168.3.34:3306
 Source Schema         : enercomndeviced

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 10/09/2021 16:30:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `sex` int(255) DEFAULT NULL COMMENT '性别',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '姓名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('2', 0, '杨成');
INSERT INTO `user` VALUES ('333', 1, '赫斯器');

SET FOREIGN_KEY_CHECKS = 1;
