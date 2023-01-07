/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:3306
 Source Schema         : db_test

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 07/01/2023 09:33:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(11) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `desciption` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (0, '超级管理员', '拥有所有用户的操作权限');
INSERT INTO `role` VALUES (1, '宿舍管理员', '拥有学生管理、缺勤记录、修改密码、退出系统的功能');
INSERT INTO `role` VALUES (2, '学生', '拥有缺勤记录、修改密码、退出系统的功能');

-- ----------------------------
-- Table structure for tb_dormbuild
-- ----------------------------
DROP TABLE IF EXISTS `tb_dormbuild`;
CREATE TABLE `tb_dormbuild`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `disabled` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_dormbuild
-- ----------------------------
INSERT INTO `tb_dormbuild` VALUES (1, '1栋宿舍楼', '1栋宿舍楼是教师宿舍', 1);
INSERT INTO `tb_dormbuild` VALUES (2, '2栋宿舍楼', '2栋宿舍楼', 0);
INSERT INTO `tb_dormbuild` VALUES (3, '3栋宿舍楼', '3栋宿舍楼，是小学生住的', 0);
INSERT INTO `tb_dormbuild` VALUES (4, '6号楼', '专升本居住的宿舍', 1);
INSERT INTO `tb_dormbuild` VALUES (6, '研究生宿舍', '研究生居住的宿舍', 0);
INSERT INTO `tb_dormbuild` VALUES (8, '12栋宿舍楼', '这是武汉软件工程职业学院的12栋宿舍楼', 1);
INSERT INTO `tb_dormbuild` VALUES (11, '2023-01-05栋', '测试宿舍楼', 1);

-- ----------------------------
-- Table structure for tb_manage_dormbuild
-- ----------------------------
DROP TABLE IF EXISTS `tb_manage_dormbuild`;
CREATE TABLE `tb_manage_dormbuild`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT NULL,
  `dormBuild_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `dormBuild_id`(`dormBuild_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 109 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_manage_dormbuild
-- ----------------------------
INSERT INTO `tb_manage_dormbuild` VALUES (4, 12, 1);
INSERT INTO `tb_manage_dormbuild` VALUES (5, 12, 2);
INSERT INTO `tb_manage_dormbuild` VALUES (6, 20, 1);
INSERT INTO `tb_manage_dormbuild` VALUES (7, 21, 1);
INSERT INTO `tb_manage_dormbuild` VALUES (8, 21, 2);
INSERT INTO `tb_manage_dormbuild` VALUES (18, 22, 1);
INSERT INTO `tb_manage_dormbuild` VALUES (19, 22, 2);
INSERT INTO `tb_manage_dormbuild` VALUES (29, 11, 1);
INSERT INTO `tb_manage_dormbuild` VALUES (30, 11, 2);
INSERT INTO `tb_manage_dormbuild` VALUES (47, 1, 1);
INSERT INTO `tb_manage_dormbuild` VALUES (48, 1, 2);
INSERT INTO `tb_manage_dormbuild` VALUES (49, 1, 3);
INSERT INTO `tb_manage_dormbuild` VALUES (50, 1, 4);
INSERT INTO `tb_manage_dormbuild` VALUES (51, 42, 1);
INSERT INTO `tb_manage_dormbuild` VALUES (52, 44, 3);
INSERT INTO `tb_manage_dormbuild` VALUES (53, 45, 2);
INSERT INTO `tb_manage_dormbuild` VALUES (56, 47, 1);
INSERT INTO `tb_manage_dormbuild` VALUES (57, 46, 1);
INSERT INTO `tb_manage_dormbuild` VALUES (58, 46, 2);
INSERT INTO `tb_manage_dormbuild` VALUES (59, 46, 3);
INSERT INTO `tb_manage_dormbuild` VALUES (60, 46, 6);
INSERT INTO `tb_manage_dormbuild` VALUES (61, NULL, 6);
INSERT INTO `tb_manage_dormbuild` VALUES (63, 57, 1);
INSERT INTO `tb_manage_dormbuild` VALUES (64, 57, 6);
INSERT INTO `tb_manage_dormbuild` VALUES (65, 58, 4);
INSERT INTO `tb_manage_dormbuild` VALUES (66, 59, 4);
INSERT INTO `tb_manage_dormbuild` VALUES (67, NULL, 1);
INSERT INTO `tb_manage_dormbuild` VALUES (68, NULL, 4);
INSERT INTO `tb_manage_dormbuild` VALUES (70, 52, 1);
INSERT INTO `tb_manage_dormbuild` VALUES (71, 52, 2);
INSERT INTO `tb_manage_dormbuild` VALUES (72, 52, 3);
INSERT INTO `tb_manage_dormbuild` VALUES (73, 52, 4);
INSERT INTO `tb_manage_dormbuild` VALUES (74, 52, 6);
INSERT INTO `tb_manage_dormbuild` VALUES (84, 63, 1);
INSERT INTO `tb_manage_dormbuild` VALUES (85, 63, 2);
INSERT INTO `tb_manage_dormbuild` VALUES (86, 63, 6);
INSERT INTO `tb_manage_dormbuild` VALUES (92, 66, 4);
INSERT INTO `tb_manage_dormbuild` VALUES (93, 67, 4);
INSERT INTO `tb_manage_dormbuild` VALUES (94, 68, 2);
INSERT INTO `tb_manage_dormbuild` VALUES (96, 39, 6);
INSERT INTO `tb_manage_dormbuild` VALUES (103, 60, 1);
INSERT INTO `tb_manage_dormbuild` VALUES (104, 60, 2);
INSERT INTO `tb_manage_dormbuild` VALUES (105, 60, 3);
INSERT INTO `tb_manage_dormbuild` VALUES (106, 10, 1);
INSERT INTO `tb_manage_dormbuild` VALUES (107, 10, 2);
INSERT INTO `tb_manage_dormbuild` VALUES (108, 10, 3);

-- ----------------------------
-- Table structure for tb_record
-- ----------------------------
DROP TABLE IF EXISTS `tb_record`;
CREATE TABLE `tb_record`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) NULL DEFAULT NULL COMMENT '学生id',
  `date` date NULL DEFAULT NULL COMMENT '缺勤时间',
  `remark` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `disabled` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `student_id`(`student_id`) USING BTREE,
  CONSTRAINT `tb_record_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `tb_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_record
-- ----------------------------
INSERT INTO `tb_record` VALUES (4, 36, '2020-11-05', '腿骨折,请假', 0);
INSERT INTO `tb_record` VALUES (5, 38, '2022-12-29', '发烧', 0);
INSERT INTO `tb_record` VALUES (7, 38, '2022-12-30', '阳性隔离中', 0);
INSERT INTO `tb_record` VALUES (8, 38, '2022-12-20', '蓝桥杯参赛中', 0);
INSERT INTO `tb_record` VALUES (10, 38, '2022-12-28', '写实验报告', 0);
INSERT INTO `tb_record` VALUES (11, 32, '2023-01-02', '熬夜重新写项目', 1);
INSERT INTO `tb_record` VALUES (12, 39, '2022-12-14', '又羊了', 1);
INSERT INTO `tb_record` VALUES (15, 39, '2023-01-05', '2023-01-05添加的熬夜写两个项目', 1);
INSERT INTO `tb_record` VALUES (16, 39, '2022-12-24', '12 24出去了', 1);

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `passWord` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `stu_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学号',
  `dorm_Code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '宿舍编号',
  `sex` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tel` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dormBuildId` int(11) NULL DEFAULT NULL COMMENT '宿舍楼id',
  `role_id` int(11) NULL DEFAULT NULL COMMENT '0 表示超级管理员 1宿舍管理员 2学生',
  `create_user_id` int(11) NULL DEFAULT NULL COMMENT '创建人id',
  `disabled` int(11) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `stu_code`(`stu_code`) USING BTREE,
  INDEX `dormBuildId`(`dormBuildId`) USING BTREE,
  INDEX `create_user_id`(`create_user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 77 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (1, '超级管理员', '1234', 'admin', NULL, '男', 'aikun', NULL, 0, NULL, 0);
INSERT INTO `tb_user` VALUES (10, '2222', '2222', '2222', NULL, '男', '13539909242', NULL, 1, 1, 0);
INSERT INTO `tb_user` VALUES (11, '管理员31', '123456', '0003', NULL, '女', '13532342341', NULL, 1, 1, 0);
INSERT INTO `tb_user` VALUES (12, '管理员4', '123456', '0004', NULL, '男', '13543257654', NULL, 1, 1, 0);
INSERT INTO `tb_user` VALUES (20, '管理员20', '123456', '0005', NULL, '女', '13539909242', NULL, 1, 1, 0);
INSERT INTO `tb_user` VALUES (32, '学生1', '123', '0008', '3-201', '女', '13532342341', 1, 2, 11, 1);
INSERT INTO `tb_user` VALUES (33, '学生2', '123456', '0009', '1-201', '男', '13532342342', 1, 2, 11, 0);
INSERT INTO `tb_user` VALUES (34, '学生3', '123456', '0010', '3-101', '男', '13532342342', 2, 2, 20, 0);
INSERT INTO `tb_user` VALUES (35, '学生4', '123456', '0011', '2-201', '女', '13532342342', 3, 2, 1, 0);
INSERT INTO `tb_user` VALUES (36, '学生5', '123456', '0012', '1-101', '男', '13532342342', 1, 2, 1, 0);
INSERT INTO `tb_user` VALUES (37, '学生6', '123456', '0013', '2-201', '女', '13532342346', 3, 2, 1, 0);
INSERT INTO `tb_user` VALUES (38, '学生7', '0014', '0014', '5-201', '女', '13532342347', 6, 2, 1, 0);
INSERT INTO `tb_user` VALUES (39, '学生55555', '1555', '5555', '101', '男', '13532342342', 6, 2, 1, 1);
INSERT INTO `tb_user` VALUES (52, '李知恩', '123', 'iu', NULL, '女', 'iu123', NULL, 1, 1, 0);
INSERT INTO `tb_user` VALUES (57, '尼古拉斯赵四', '123', 'zhaosi123', NULL, '男', '123444444', NULL, 1, 1, 0);
INSERT INTO `tb_user` VALUES (58, '尼古拉斯刘能', '123', 'liu123', NULL, '男', 'liu666666', NULL, 1, 1, 0);
INSERT INTO `tb_user` VALUES (59, '尼古拉斯谢广坤', '123', 'kunkun521', NULL, '男', 'jiji12345', NULL, 1, 1, 0);
INSERT INTO `tb_user` VALUES (60, '张三四', '1234', 'zhangsan444', NULL, '男', '333334', NULL, 1, 1, 0);
INSERT INTO `tb_user` VALUES (63, '王小明', 'dq123', 'decade10', NULL, '男', '1521667737362', NULL, 1, 1, 0);
INSERT INTO `tb_user` VALUES (69, '邱翔宇', '1532', '2022215071532', '403', '男', '18279061609', 4, 2, 1, 0);
INSERT INTO `tb_user` VALUES (70, '一天写完项目的学生', 'day01student', 'day01student', '111', '男', '11111111', 1, 2, 1, 0);

SET FOREIGN_KEY_CHECKS = 1;
