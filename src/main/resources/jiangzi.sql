/*
 Navicat Premium Data Transfer

 Source Server         : jiangzi
 Source Server Type    : MySQL
 Source Server Version : 100406
 Source Host           : localhost:3306
 Source Schema         : jiangzi

 Target Server Type    : MySQL
 Target Server Version : 100406
 File Encoding         : 65001

 Date: 02/05/2020 14:12:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for persistent_logins
-- ----------------------------
DROP TABLE IF EXISTS `persistent_logins`;
CREATE TABLE `persistent_logins`  (
  `username` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `series` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `token` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `last_used` datetime(0) NOT NULL DEFAULT current_timestamp() ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`series`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名',
  `describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, 'ADMIN', '管理员', '2020-04-09 16:51:32', NULL, 0);
INSERT INTO `sys_role` VALUES (2, 'COMMON', '普通用户', '2020-04-09 16:51:51', NULL, 0);
INSERT INTO `sys_role` VALUES (3, 'VIP', '会员用户', '2020-04-09 16:52:02', NULL, 0);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户姓名',
  `account` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账号',
  `gender` char(4) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '性别',
  `birthday` datetime(0) NULL DEFAULT NULL COMMENT '出生日期',
  `email` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话号码',
  `create` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) NOT NULL DEFAULT 0,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', 'ADMIN', ' 男', NULL, '297304819@qq.com', '15574800565', '2020-04-09 16:49:52', NULL, 0, '$2a$10$KXEYoW5K.N86zNNU7ewlwOShxiC4eIoMsv57kqrMhldaI6bW9b/J6');
INSERT INTO `sys_user` VALUES (2, 'user', 'JZ100002', ' 女', NULL, NULL, '17222388943', '2020-04-09 16:50:11', NULL, 1, '$2a$10$buLM6aGVf8r1yhfV9HAkS.zoKmvMMFzUVaZv6MPzo8dCi.SsNRcYu');
INSERT INTO `sys_user` VALUES (3, 'vipUser', 'JZ100003', '男', NULL, NULL, '13687332911', '2020-04-09 16:53:35', NULL, 0, '$2a$10$buLM6aGVf8r1yhfV9HAkS.zoKmvMMFzUVaZv6MPzo8dCi.SsNRcYu');
INSERT INTO `sys_user` VALUES (10, '测试用户', 'JZ100004', '男', NULL, '297304818@qq.com', NULL, '2020-04-20 11:22:19', NULL, 0, '$2a$10$qBjX1qHQskWJy5oQPOQOlOi2bUQxk89YWF03kkT2lMGgaZYzi7W.O');
INSERT INTO `sys_user` VALUES (11, '测试用户2', 'JZ100005', '女', NULL, '297304817@qq.com', NULL, '2020-04-27 15:46:49', NULL, 0, '$2a$10$oEqG9lPO5MFsJQzvjT26me8e..L7Ew3jFk5AmjunR4DSIPXRvE5Q.');
INSERT INTO `sys_user` VALUES (12, '测试用户3', 'JZ100006', '男', NULL, '297304816@qq.com', NULL, '2020-04-27 16:31:11', NULL, 0, '$2a$10$HEZF.YmTQgetDN6u.OIt5OkHw1eDwcRpfRG111Fqyi.GCakXnaw0S');
INSERT INTO `sys_user` VALUES (13, '测试用户4', 'JZ100007', '女', NULL, '297304815@qq.com', '15274800577', '2020-04-27 16:35:56', NULL, 0, '$2a$10$QgyKKF5chVg/KTba0bQSd.4Pt2CIwLv4L.A5FcJm8GKfa8Y34NpAi');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1, 1);
INSERT INTO `sys_user_role` VALUES (2, 2, 2);
INSERT INTO `sys_user_role` VALUES (3, 3, 3);
INSERT INTO `sys_user_role` VALUES (4, 1, 2);
INSERT INTO `sys_user_role` VALUES (5, 1, 3);
INSERT INTO `sys_user_role` VALUES (10, 10, 2);
INSERT INTO `sys_user_role` VALUES (11, 11, 2);
INSERT INTO `sys_user_role` VALUES (12, 12, 2);
INSERT INTO `sys_user_role` VALUES (13, 13, 2);

SET FOREIGN_KEY_CHECKS = 1;
