
#创建数据库
CREATE DATABASE IF NOT EXISTS bos DEFAULT CHARSET utf8 COLLATE utf8_general_ci;
create user 'libo'@'%' identified by 'libo888';
GRANT ALL PRIVILEGES ON bos.* TO 'libo'@'%' IDENTIFIED BY 'libo888';

use bos;

#用户表
create table t_user (
  id varchar(32) PRIMARY KEY NOT NULL ,
  username varchar(32) NOT NULL ,
  password varchar(32) NOT NULL ,
  salary double DEFAULT 0,
  birthday date,
  gender varchar(10),
  station varchar(40),
  telephone varchar(13),
  remark varchar(255),
  KEY `index_query` (`username`,`gender`,`telephone`)
);

#show index from t_user;

insert into t_user (id,username,password) values ('1','admin',md5('admin'));