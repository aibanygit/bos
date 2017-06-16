
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

#定区
create table bc_decidedzone(
   id                   varchar(32) not null,
   name                 varchar(30),
   staff_id             varchar(32),
   primary key (id)
);

#区域
create table bc_region(
   id                   varchar(32) not null,
   province             varchar(50),
   city                 varchar(50),
   district             varchar(50),
   postcode             varchar(50),
   shortcode            varchar(30),
   citycode             varchar(30),
   primary key (id)
);

#取派员
create table bc_staff(
   id                   varchar(32) not null,
   name                 varchar(20),
   telephone            varchar(20),
   haspda               char(1),
   deltag               char(1),
   station              varchar(40),
   standard             varchar(100),
   primary key (id)
);

#分区
create table bc_subarea(
   id                   varchar(32) not null,
   decidedzone_id       varchar(32),
   region_id            varchar(32),
   addresskey           varchar(100),
   startnum             varchar(30),
   endnum               varchar(30),
   single               char(1),
   position             varchar(255),
   primary key (id)
);

alter table bc_decidedzone add constraint FK_decidedzone_staff foreign key (staff_id)
      references bc_staff (id) on delete restrict on update restrict;
alter table bc_subarea add constraint FK_area_decidedzone foreign key (decidedzone_id)
      references bc_decidedzone (id) on delete restrict on update restrict;
alter table bc_subarea add constraint FK_area_region foreign key (region_id)
      references bc_region (id) on delete restrict on update restrict;