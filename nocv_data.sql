/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : nocv

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 04/06/2022 15:17:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for nocv_data
-- ----------------------------
DROP TABLE IF EXISTS `nocv_data`;
CREATE TABLE `nocv_data`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `value` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 36 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of nocv_data
-- ----------------------------
INSERT INTO `nocv_data` VALUES (1, '上海', 65);
INSERT INTO `nocv_data` VALUES (2, '澳门', 95);
INSERT INTO `nocv_data` VALUES (3, '厦门', 65);
INSERT INTO `nocv_data` VALUES (4, '台湾', 153);
INSERT INTO `nocv_data` VALUES (5, '云南', 1111);
INSERT INTO `nocv_data` VALUES (6, '贵州', 741);
INSERT INTO `nocv_data` VALUES (7, '四川', 963);
INSERT INTO `nocv_data` VALUES (8, '广东', 25);
INSERT INTO `nocv_data` VALUES (9, '山东', 285);
INSERT INTO `nocv_data` VALUES (10, '江西', 126);
INSERT INTO `nocv_data` VALUES (11, '福建', 2550);
INSERT INTO `nocv_data` VALUES (12, '重庆', 369);
INSERT INTO `nocv_data` VALUES (13, '海南', 245);
INSERT INTO `nocv_data` VALUES (14, '广西', 123);
INSERT INTO `nocv_data` VALUES (15, '湖南', 98);
INSERT INTO `nocv_data` VALUES (16, '湖北', 255);
INSERT INTO `nocv_data` VALUES (17, '河南', 255);
INSERT INTO `nocv_data` VALUES (18, '新疆', 25);
INSERT INTO `nocv_data` VALUES (19, '香港', 1456);
INSERT INTO `nocv_data` VALUES (20, '宁夏', 12);
INSERT INTO `nocv_data` VALUES (21, '青海', 258);
INSERT INTO `nocv_data` VALUES (22, '甘肃', 153);
INSERT INTO `nocv_data` VALUES (23, '陕西', 357);
INSERT INTO `nocv_data` VALUES (24, '西藏', 456);
INSERT INTO `nocv_data` VALUES (25, '安徽', 4587);
INSERT INTO `nocv_data` VALUES (26, '浙江', 45);
INSERT INTO `nocv_data` VALUES (27, '江苏', 4);
INSERT INTO `nocv_data` VALUES (28, '黑龙江', 1625);
INSERT INTO `nocv_data` VALUES (29, '吉林', 796);
INSERT INTO `nocv_data` VALUES (30, '辽宁', 785);
INSERT INTO `nocv_data` VALUES (31, '内蒙古', 86);
INSERT INTO `nocv_data` VALUES (32, '天津', 15);
INSERT INTO `nocv_data` VALUES (33, '北京', 12);
INSERT INTO `nocv_data` VALUES (34, '河北', 7865);
INSERT INTO `nocv_data` VALUES (35, '山西', 1235);

SET FOREIGN_KEY_CHECKS = 1;
