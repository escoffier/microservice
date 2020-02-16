-- DROP DATABASE IF EXISTS organizations;
CREATE DATABASE IF NOT EXISTS organizations;
USE organizations;

DROP TABLE IF EXISTS `t_organizations`;
CREATE TABLE `t_organizations` (
                                   `id` int NOT NULL AUTO_INCREMENT COMMENT '主键id',
                                   `name` varchar(32) not null,
                                   `contact_name` varchar(32) DEFAULT NULL,
                                   `contact_email` varchar(32) DEFAULT NULL COMMENT 'email',
                                   `contact_phone` varchar(20) DEFAULT NULL COMMENT 'phone number',
                                   PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;