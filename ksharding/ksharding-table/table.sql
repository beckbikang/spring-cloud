create database db0;
CREATE TABLE `order_1` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `uid` bigint NOT NULL COMMENT 'uid',
  `name` varchar(30) DEFAULT NULL COMMENT '姓名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `order_2` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `uid` bigint NOT NULL COMMENT 'uid',
  `name` varchar(30) DEFAULT NULL COMMENT '姓名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `order_3` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `uid` bigint NOT NULL COMMENT 'uid',
  `name` varchar(30) DEFAULT NULL COMMENT '姓名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;