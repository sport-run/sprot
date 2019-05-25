/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50717
Source Host           : 127.0.0.1:3306
Source Database       : footpath

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2019-05-07 20:30:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `aid` int(11) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`aid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `baiduUid` int(11) NOT NULL DEFAULT 0,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `state` int(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `score` int(255) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'test', null, null, null, null, null, null);







CREATE TABLE t_park
(
  f_id           INT AUTO_INCREMENT
    PRIMARY KEY,
  f_name         VARCHAR(20) DEFAULT ''              NOT NULL
  COMMENT '公园名称',
  f_perimeter    DECIMAL DEFAULT '0'                 NOT NULL
  COMMENT '跑到周长',
  f_camera_start VARCHAR(20) DEFAULT ''              NOT NULL
  COMMENT '起点摄像机',
  f_camera_end   VARCHAR(20) DEFAULT ''              NOT NULL
  COMMENT '终点摄像机',
  f_camera_other VARCHAR(100) DEFAULT ''             NOT NULL
  COMMENT '其他摄像机',
  f_park_grade   DECIMAL DEFAULT '0'                 NOT NULL
  COMMENT '坡度',
  f_create_time  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
  COMMENT '创建时间',
  f_update_time  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
  COMMENT '修改时间',
  CONSTRAINT t_park_f_id_f_camera_start_f_camera_end_uindex
  UNIQUE (f_id, f_camera_start, f_camera_end)
)
  COMMENT '公园数据'
  ENGINE = InnoDB;

  CREATE TABLE t_sport_data_statistics_line
(
  f_id          INT AUTO_INCREMENT
    PRIMARY KEY,
  f_baidu_uid   VARCHAR(20) DEFAULT ''              NOT NULL
  COMMENT '百度用户id',
  f_uid         VARCHAR(20) DEFAULT ''              NOT NULL
  COMMENT '步道用户id',
  f_time        TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
  COMMENT '采集时间',
  f_park_id     INT DEFAULT '0'                     NOT NULL
  COMMENT '公园id',
  f_is_start    INT DEFAULT '0'                     NOT NULL
  COMMENT '是否起点摄像机0:否 1:是',
  f_create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
  COMMENT '创建时间',
  f_update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
  COMMENT '修改时间'
)
  COMMENT '运动统计流水表'
  ENGINE = InnoDB;


CREATE TABLE t_sport_date_statistics
(
  f_id          INT AUTO_INCREMENT
    PRIMARY KEY,
  f_baidu_uid   VARCHAR(20) DEFAULT ''              NOT NULL
  COMMENT 'baidu uid',
  f_uid         VARCHAR(20) DEFAULT ''              NOT NULL
  COMMENT '步道uid',
  f_date        TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
  COMMENT '日期时间',
  f_count       INT DEFAULT '0'                     NOT NULL
  COMMENT '运动圈数',
  f_time        BIGINT DEFAULT '0'                  NOT NULL
  COMMENT '运动时间(分钟)',
  f_perimeter   DECIMAL DEFAULT '0'                 NOT NULL
  COMMENT '一圈的周长',
  f_update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  f_create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
)
  COMMENT '运动统计表'
  ENGINE = InnoDB;

CREATE TABLE t_sport_data
(
  f_id                 INT DEFAULT '0'                         NOT NULL
    PRIMARY KEY,
  f_uid                VARCHAR(20) DEFAULT ''                  NOT NULL
  COMMENT '用户id',
  f_distance           DECIMAL DEFAULT '0'                     NOT NULL
  COMMENT '跑步距离',
  f_date               TIMESTAMP DEFAULT '1970-01-01 08:00:01' NOT NULL
  COMMENT '时间',
  f_sport_time         BIGINT DEFAULT '0'                      NOT NULL
  COMMENT '运动时长(单位分钟)',
  f_max_speed          DECIMAL DEFAULT '0'                     NOT NULL
  COMMENT '最大速度',
  f_energy_metabolism  DECIMAL DEFAULT '0'                     NOT NULL
  COMMENT '能量代谢当量',
  f_energy_expenditure DECIMAL DEFAULT '0'                     NOT NULL
  COMMENT '消耗热量',
  f_create_time        TIMESTAMP DEFAULT CURRENT_TIMESTAMP     NOT NULL
  COMMENT '创建时间',
  f_update_time        TIMESTAMP DEFAULT CURRENT_TIMESTAMP     NOT NULL
  COMMENT '修改时间'
)
  COMMENT '运动数据表'
  ENGINE = InnoDB;


