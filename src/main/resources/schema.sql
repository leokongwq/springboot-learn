
SET MODE MySQL;

drop SCHEMA if EXISTS blog;

create SCHEMA blog;

drop table if EXISTS posts;

create  table posts (
  `id` bigint(19) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `content` varchar(1024) DEFAULT NULL COMMENT '内容',
   PRIMARY KEY (`id`),
   UNIQUE KEY `idx_title` (`title`)
) DEFAULT CHARSET=utf8;

drop table if EXISTS post_comment;

create  table post_comment (
  `id` bigint(19) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `post_id` bigint(19) unsigned NOT NULL AUTO_INCREMENT COMMENT 'post_id',
  `content` varchar(1024) DEFAULT NULL COMMENT '内容',
   PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8;

