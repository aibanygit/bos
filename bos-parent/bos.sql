
#创建数据库
CREATE DATABASE IF NOT EXISTS bos DEFAULT CHARSET utf8 COLLATE utf8_general_ci;
create user 'libo'@'%' identified by 'libo888';
GRANT ALL PRIVILEGES ON bos.* TO 'libo'@'%' IDENTIFIED BY 'libo888';

use bos;