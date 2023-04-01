/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80025
 Source Host           : localhost:3306
 Source Schema         : super-daily-attendance

 Target Server Type    : MySQL
 Target Server Version : 80025
 File Encoding         : 65001

 Date: 01/04/2023 17:18:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for daily_attendance
-- ----------------------------
DROP TABLE IF EXISTS `daily_attendance`;
CREATE TABLE `daily_attendance`  (
  `id` bigint(0) NOT NULL COMMENT '主键',
  `cust_id` bigint(0) NULL DEFAULT NULL COMMENT '客户id',
  `cust_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户姓名',
  `attendance_type` tinyint(1) NULL DEFAULT NULL COMMENT '打卡类型',
  `start_time` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '结束时间',
  `supplement_flag` tinyint(1) NULL DEFAULT NULL COMMENT '补录标识',
  `del_flag` tinyint(1) NULL DEFAULT NULL COMMENT '删除标识',
  `create_by` bigint(0) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '打卡表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
