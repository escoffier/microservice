DROP TABLE IF EXISTS `t_licenses`;
CREATE TABLE `t_licenses` (
  `license_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `organization_id` bigint(20) not null,
  `product_name` varchar(32) DEFAULT NULL,
  `license_type` varchar(32) DEFAULT NULL COMMENT '类型',
  `license_max` bigint(20) DEFAULT NULL COMMENT '密码',
  `license_allocated` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`license_id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;