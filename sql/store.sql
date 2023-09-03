drop database if exists  store_carousel;

CREATE DATABASE store_carousel;

USE store_carousel;

CREATE TABLE `carousel` (
  `carousel_id` INT(11) NOT NULL AUTO_INCREMENT,
  `img_path` CHAR(50) NOT NULL,
  `describes` CHAR(50) NOT NULL,
  `product_id` INT(11) DEFAULT NULL COMMENT '广告关联的商品图片',
  `priority` INT DEFAULT 10 COMMENT '优先级',
  PRIMARY KEY (`carousel_id`)
) ENGINE=INNODB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;




drop database if exists  store_cart;

CREATE DATABASE store_cart;

USE store_cart;

CREATE TABLE `cart` (
  `id` INT(11) PRIMARY KEY  AUTO_INCREMENT,
  `user_id` INT(11) NOT NULL,
  `product_id` INT(11) NOT NULL,
  `num` INT(11) NOT NULL
) ENGINE=INNODB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;


drop database if exists  store_category;
CREATE DATABASE store_category;

USE store_category;


DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `category_id` INT(11) NOT NULL AUTO_INCREMENT,
  `category_name` CHAR(20) NOT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=INNODB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;




drop database if exists  store_collect;

CREATE DATABASE store_collect;

USE store_collect;



CREATE TABLE `collect` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) NOT NULL,
  `product_id` INT(11) NOT NULL,
  `collect_time` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
# 收藏夹

drop database if exists  store_order;

CREATE DATABASE store_order;

USE store_order;

DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) NOT NULL,
  `user_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `product_num` int(11) NOT NULL,
  `product_price` double NOT NULL,
  `order_time` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

drop database if exists store_product;

CREATE DATABASE store_product;

USE store_product;


CREATE TABLE `product` (
  `product_id` INT(11) NOT NULL AUTO_INCREMENT,
  `product_name` CHAR(100) NOT NULL,
  `category_id` INT(11) NOT NULL,
  `product_title` CHAR(30) NOT NULL,
  `product_intro` TEXT NOT NULL,
  `product_picture` CHAR(200) DEFAULT NULL,
  `product_price` DOUBLE NOT NULL,
  `product_selling_price` DOUBLE NOT NULL,
  `product_num` INT(11) NOT NULL,
  `product_sales` INT(11) NOT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=INNODB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;




# 商品对应普通表
CREATE TABLE product_picture(
  id INT PRIMARY KEY AUTO_INCREMENT,
  product_id INT NOT NULL,
  product_picture CHAR (200),
  intro TEXT NULL
);



drop database if exists store_user;

CREATE DATABASE store_user;

USE store_user;


DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_name` CHAR(40) NOT NULL,
  `PASSWORD` CHAR(40) NOT NULL,
  `user_phonenumber` CHAR(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_name` (`user_name`)
) ENGINE=INNODB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;




/*Table structure for table `address` */

DROP TABLE IF EXISTS `address`;

CREATE TABLE `address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '地址主键',
  `linkman` varchar(20) NOT NULL COMMENT '联系人',
  `phone` varchar(13) NOT NULL COMMENT '手机号',
  `address` varchar(200) NOT NULL COMMENT '详细地址',
  `user_id` int(11) DEFAULT NULL COMMENT '用户主键',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


drop database if exists store_admin;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`store_admin` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `store_admin`;

/*Table structure for table `admin_user` */

DROP TABLE IF EXISTS `admin_user`;

CREATE TABLE `admin_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '管理员主键',
  `user_name` varchar(20) DEFAULT NULL COMMENT '管理员昵称',
  `user_account` varchar(40) NOT NULL COMMENT '管理员账号',
  `user_password` varchar(64) NOT NULL COMMENT '管理员密码',
  `user_phone` varchar(13) NOT NULL COMMENT '管理员手机号',
  `create_time` date DEFAULT NULL COMMENT '账号创建时间',
  `user_role` int(11) DEFAULT '0' COMMENT '账号角色编号,用于后期进行权限扩展,前期先为0',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_account` (`user_account`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


