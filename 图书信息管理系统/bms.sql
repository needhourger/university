/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50726
Source Host           : localhost:3306
Source Database       : bms

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2019-06-21 00:26:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for administrator
-- ----------------------------
DROP TABLE IF EXISTS `administrator`;
CREATE TABLE `administrator` (
  `Admin_id` int(11) NOT NULL AUTO_INCREMENT,
  `Admin_name` varchar(40) NOT NULL,
  `Admin_pwd` varchar(40) NOT NULL,
  `Admin_code` varchar(6) NOT NULL,
  PRIMARY KEY (`Admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of administrator
-- ----------------------------
INSERT INTO `administrator` VALUES ('1', 'root', 'root', '990920');
INSERT INTO `administrator` VALUES ('2', 'lisi', '123456', '970673');

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `Book_id` varchar(8) NOT NULL,
  `Book_name` varchar(20) NOT NULL,
  `Book_author` varchar(20) NOT NULL,
  `Book_price` decimal(10,2) NOT NULL,
  `Book_type` varchar(20) NOT NULL,
  `Book_isbn` varchar(20) NOT NULL,
  `Book_publisher` varchar(40) DEFAULT NULL,
  `Book_allnum` int(10) NOT NULL,
  `Book_surplus` int(10) NOT NULL,
  PRIMARY KEY (`Book_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('1ELCPI8O', '白夜行', '东野圭吾', '59.60', '推理', '9787544258609', '南海出版公司', '1000', '1000');
INSERT INTO `book` VALUES ('9GX7I1UJ', '小王子', '圣埃克苏佩里', '49.80', '童话', '9787569922783', '北京时代华文书局', '1000', '1000');
INSERT INTO `book` VALUES ('CI90TWK4', '观山海', '杉泽、梁超', '168.00', '神话', '9787540485696', '湖南文艺出版社', '1000', '1000');
INSERT INTO `book` VALUES ('D9VIK37T', '瓦尔登湖', '亨利·卢梭', '28.00', '散文集', '9787802236516', '中国三峡出版社', '1000', '1000');
INSERT INTO `book` VALUES ('JXB798ZT', '云边有个小卖部', '张嘉佳', '42.00', '小说', '9787540487645', '湖南文艺出版社', '1000', '1000');
INSERT INTO `book` VALUES ('KUKPPC26', '人类群星闪耀时', '茨威格', '69.00', '传记', '9787559433626', '江苏文艺出版社', '9999', '999');
INSERT INTO `book` VALUES ('M624E7O6', '你是人间四月天', '林徽因', '28.00', '散文集', '9787550238770', '北京联合出版公司', '1000', '1000');
INSERT INTO `book` VALUES ('Y50O1D8F', '巴黎圣母院', '雨果', '46.00', '小说', '9787560575667', '西安交通大学出版社', '1000', '1000');

-- ----------------------------
-- Table structure for record
-- ----------------------------
DROP TABLE IF EXISTS `record`;
CREATE TABLE `record` (
  `Record_id` int(8) NOT NULL AUTO_INCREMENT,
  `Admin_id` int(8) NOT NULL,
  `Book_id` char(8) NOT NULL,
  `Record_msg` varchar(40) NOT NULL,
  PRIMARY KEY (`Record_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of record
-- ----------------------------
INSERT INTO `record` VALUES ('1', '1', 'CI90TWK4', '增加图书');
INSERT INTO `record` VALUES ('2', '1', '9GX7I1UJ', '增加图书');
INSERT INTO `record` VALUES ('3', '1', 'D9VIK37T', '增加图书');
INSERT INTO `record` VALUES ('4', '1', '1ELCPI8O', '增加图书');
INSERT INTO `record` VALUES ('5', '1', 'Y50O1D8F', '增加图书');
INSERT INTO `record` VALUES ('6', '1', 'JXB798ZT', '增加图书');
INSERT INTO `record` VALUES ('7', '1', 'M624E7O6', '增加图书');
INSERT INTO `record` VALUES ('8', '1', 'KUKPPC26', '增加图书');
INSERT INTO `record` VALUES ('9', '1', 'KUKPPC26', '编辑图书');
DROP TRIGGER IF EXISTS `tri_book`;
DELIMITER ;;
CREATE TRIGGER `tri_book` BEFORE INSERT ON `book` FOR EACH ROW BEGIN
	IF new.Book_price < 0 THEN	
		DELETE FROM book where book_price = new.book_price;
	END IF;

              IF new.book_allnum < 0 THEN
		delete from book where book_allnum = new.book_allnum;
	end IF;

              IF NEW.book_surplus<0 OR NEW.book_surplus>NEW.book_allnum THEN
		DELETE FROM book WHERE book_surplus = NEW.book_surplus;
	END IF;
END
;;
DELIMITER ;
