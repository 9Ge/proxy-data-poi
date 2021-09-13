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

 Date: 10/09/2021 16:30:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dict
-- ----------------------------
DROP TABLE IF EXISTS `dict`;
CREATE TABLE `dict`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '编码',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dict
-- ----------------------------
INSERT INTO `dict` VALUES ('2222', '0', '女');
INSERT INTO `dict` VALUES ('3333', '1', '男');

SET FOREIGN_KEY_CHECKS = 1;
