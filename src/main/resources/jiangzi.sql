/*
Navicat MySQL Data Transfer

Source Host           : localhost:3306
Source Database       : jiangzi
*/
CREATE DATABASE `jiangzi` DEFAULT CHARACTER SET utf8;

USE `jiangzi`;
SET FOREIGN_KEY_CHECKS=0;

CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `name` varchar(10) NOT NULL COMMENT '用户姓名',
  `gender` char(4) NOT NULL COMMENT '性别',
  `birthday` datetime DEFAULT NULL COMMENT '出生日期',
  `email` varchar(20) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(11) DEFAULT NULL COMMENT '电话号码',
  `create` datetime DEFAULT NULL COMMENT '创建时间',
  `update` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
) ENGINE=InnoDB AUTO_INCREMENT=1519 DEFAULT CHARSET=utf8;

CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `name` varchar(10) NOT NULL COMMENT '角色名',
  `describe` varchar(255) DEFAULT NULL COMMENT '描述',
  `create` datetime DEFAULT NULL COMMENT '创建时间',
  `update` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
) ENGINE=InnoDB AUTO_INCREMENT=1519 DEFAULT CHARSET=utf8;

CREATE TABLE `sys_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `role_id` int(11) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`),
) ENGINE=InnoDB AUTO_INCREMENT=1519 DEFAULT CHARSET=utf8;
