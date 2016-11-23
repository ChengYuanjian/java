/*
 Navicat MySQL Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50714
 Source Host           : localhost
 Source Database       : paas

 Target Server Type    : MySQL
 Target Server Version : 50714
 File Encoding         : utf-8

 Date: 10/26/2016 10:17:48 AM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `t_paas_container_log`
-- ----------------------------
DROP TABLE IF EXISTS `t_paas_container_log`;
CREATE TABLE `t_paas_container_log` (
  `s_org_id` varchar(64) DEFAULT NULL COMMENT '组织ID',
  `s_org_name` varchar(64) DEFAULT NULL COMMENT '组织名称',
  `s_space_id` varchar(64) DEFAULT NULL COMMENT '空间ID',
  `s_space_name` varchar(64) DEFAULT NULL COMMENT '空间名称',
  `s_app_id` varchar(64) DEFAULT NULL COMMENT '应用ID',
  `s_app_name` varchar(64) DEFAULT NULL COMMENT '应用名称',
  `n_cpu_percentage` double(4,2) DEFAULT NULL COMMENT 'CPU使用率',
  `n_disk_mbs` int(11) DEFAULT NULL COMMENT '磁盘使用情况',
  `n_disk_percentage` double(4,2) DEFAULT NULL COMMENT '磁盘使用率',
  `n_mem_mbs` int(11) DEFAULT NULL COMMENT '内存使用情况',
  `n_mem_percentage` double(4,2) DEFAULT NULL COMMENT '内存使用率',
  `d_datetime` datetime DEFAULT NULL,
  KEY `idx_org_id` (`s_org_id`),
  KEY `idx_space_id` (`s_space_id`),
  KEY `idx_app_id` (`s_app_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_paas_http_log`
-- ----------------------------
DROP TABLE IF EXISTS `t_paas_http_log`;
CREATE TABLE `t_paas_http_log` (
  `s_org_id` varchar(64) DEFAULT NULL COMMENT '组织ID',
  `s_org_name` varchar(64) DEFAULT NULL COMMENT '组织名称',
  `s_space_id` varchar(64) DEFAULT NULL COMMENT '空间ID',
  `s_space_name` varchar(64) DEFAULT NULL COMMENT '空间名称',
  `s_app_id` varchar(64) DEFAULT NULL COMMENT '应用ID',
  `s_app_name` varchar(64) DEFAULT NULL COMMENT '应用名称',
  `s_uri` varchar(512) DEFAULT NULL COMMENT '访问URI',
  `d_datetime` datetime DEFAULT NULL,
  `s_instance_id` varchar(64) DEFAULT NULL COMMENT '实例ID',
  `n_instance_index` int(11) DEFAULT NULL COMMENT '实例序号',
  `s_ip` varchar(16) DEFAULT NULL COMMENT '访问IP',
  `s_usragent` varchar(512) DEFAULT NULL COMMENT '访问信息',
  `d_starttime` datetime DEFAULT NULL COMMENT '开始时间',
  `d_endtime` datetime DEFAULT NULL COMMENT '结束时间',
  KEY `idx_org_id` (`s_org_id`),
  KEY `idx_space_id` (`s_space_id`),
  KEY `idx_app_id` (`s_app_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
/*!50100 PARTITION BY RANGE (to_days(d_datetime))
(PARTITION p20161030 VALUES LESS THAN (736633) ENGINE = InnoDB,
 PARTITION p20161031 VALUES LESS THAN (736634) ENGINE = InnoDB,
 PARTITION p20161101 VALUES LESS THAN (736635) ENGINE = InnoDB,
 PARTITION p20161102 VALUES LESS THAN (736636) ENGINE = InnoDB,
 PARTITION p20161103 VALUES LESS THAN (736637) ENGINE = InnoDB) */;

-- ----------------------------
--  Table structure for `t_paas_http_statistics`
-- ----------------------------
DROP TABLE IF EXISTS `t_paas_http_statistics`;
CREATE TABLE `t_paas_http_statistics` (
  `s_org_id` varchar(64) DEFAULT NULL,
  `s_space_id` varchar(64) DEFAULT NULL,
  `s_app_id` varchar(64) DEFAULT NULL,
  `s_app_name` varchar(64) DEFAULT NULL,
  `s_indicator` varchar(64) DEFAULT NULL COMMENT '指标名称',
  `s_indicator_value` varchar(32) DEFAULT NULL COMMENT '指标值',
  `d_datetime` datetime DEFAULT NULL,
  `s_indicator_desc` varchar(512) DEFAULT NULL COMMENT '指标描述',
  KEY `idx_app_id` (`s_app_id`),
  KEY `idx_indicator` (`s_indicator`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_paas_oper_log`
-- ----------------------------
DROP TABLE IF EXISTS `t_paas_oper_log`;
CREATE TABLE `t_paas_oper_log` (
  `s_org_id` varchar(64) DEFAULT NULL COMMENT '组织ID',
  `s_org_name` varchar(64) DEFAULT NULL COMMENT '组织名称',
  `s_space_id` varchar(64) DEFAULT NULL COMMENT '空间ID',
  `s_space_name` varchar(64) DEFAULT NULL COMMENT '空间名称',
  `s_app_id` varchar(64) DEFAULT NULL COMMENT '应用ID',
  `s_app_name` varchar(64) DEFAULT NULL COMMENT '应用名称',
  `s_msg` varchar(4000) DEFAULT NULL COMMENT '操作内容',
  `d_datetime` datetime DEFAULT NULL,
  KEY `idx_org_id` (`s_org_id`),
  KEY `idx_space_id` (`s_space_id`),
  KEY `idx_app_id` (`s_app_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Procedure structure for `pr_partition_auto_increase_decrease`
-- ----------------------------
DROP PROCEDURE IF EXISTS `pr_partition_auto_increase_decrease`;
delimiter ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pr_partition_auto_increase_decrease`()
    COMMENT '存储过程，针对t_paas_http_log。用来每天创建一个新分区，存储当天的数据；同时删除最旧的分区，一般保存最近三天的数据。'
BEGIN

START TRANSACTION;

SELECT REPLACE(partition_name,'p','') INTO @P12_Name FROM INFORMATION_SCHEMA.PARTITIONS 
WHERE table_name='t_paas_http_log' ORDER BY partition_ordinal_position DESC LIMIT 1;

set @max_date = date(date_add(@P12_Name+0,INTERVAL 1 day))+0;

set @s1 = CONCAT('ALTER TABLE t_paas_http_log ADD PARTITION (PARTITION p',@Max_date,' VALUES LESS THAN (TO_DAYS (''',DATE(@Max_date+1),''')))');

select @s1;
prepare stmt1 from @s1;
EXECUTE stmt1;
DEALLOCATE PREPARE stmt1;

select partition_name into @P0_Name from INFORMATION_SCHEMA.PARTITIONS 
where table_name='t_paas_http_log' order by partition_ordinal_position limit 1;
SET @s=concat('ALTER TABLE t_paas_http_log DROP PARTITION ',@P0_Name);
PREPARE stmt2 FROM @s; 
EXECUTE stmt2; 
DEALLOCATE PREPARE stmt2;

COMMIT;
END
 ;;
delimiter ;

-- ----------------------------
--  Event structure for `ev_increate_decrease`
-- ----------------------------
DROP EVENT IF EXISTS `ev_increate_decrease`;
delimiter ;;
CREATE DEFINER=`root`@`localhost` EVENT `ev_increate_decrease` ON SCHEDULE EVERY 1 DAY STARTS '2016-10-25 23:59:59' ON COMPLETION NOT PRESERVE ENABLE DO begin 
call pr_partiton_auto_increase_decrease();
END
 ;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
