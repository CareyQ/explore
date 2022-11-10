create schema explore collate utf8mb4_general_ci;

use explore;

create table if not exists sys_user
(
    id          bigint unsigned not null auto_increment comment '主键',
    username    varchar(255)    not null default '' comment '用户名',
    `password`  varchar(255)    not null default '' comment '密码',
    email       varchar(255)    not null default '' comment '邮箱',
    avatar      varchar(50)     not null default '' comment '头像',
    summary     varchar(255)    not null default '这个人很懒，什么都没有留下～' comment '个人简介',
    is_del      tinyint(1)      not null default 0 comment '是否删除',
    create_time datetime        not null default current_timestamp comment '创建时间',
    update_time datetime        not null default current_timestamp on update current_timestamp comment '更新时间',
    primary key (id)
) comment '用户表';

create table if not exists sys_menu
(
    id          bigint unsigned not null auto_increment comment '主键',
    `name`      varchar(255)    not null default '' comment '菜单名称',
    type        int             not null default 0 comment '菜单类型 0：后台，1：前台',
    parent_id   bigint          not null default 0 comment '父级 ID',
    route       varchar(255)    not null comment '路由地址',
    icon        varchar(255)    not null default '' comment '菜单图标',
    sort        int             not null comment '菜单排序，值越小越靠前',
    is_del      tinyint(1)      not null default 0 comment '是否删除',
    create_time datetime        not null default current_timestamp comment '创建时间',
    update_time datetime        not null default current_timestamp on update current_timestamp comment '更新时间',
    primary key (id)
) comment '菜单表';

create table if not exists sys_config
(
    id          bigint unsigned not null auto_increment comment '主键',
    `code`      int unique      not null comment '配置 code',
    `desc`      varchar(50)     not null default '' comment '配置描述',
    `value`     varchar(255)    not null default '' comment '配置值',
    is_del      tinyint(1)      not null default 0 comment '是否删除',
    create_time datetime        not null default current_timestamp comment '创建时间',
    update_time datetime        not null default current_timestamp on update current_timestamp comment '更新时间',
    primary key (id)
) comment '配置表';

create table if not exists blog_post
(
    id            bigint unsigned not null auto_increment comment '主键',
    type          int             not null default 0 comment '博客类型 0：文章 1：片刻',
    slug          varchar(25)     not null default '' comment '文章别名，路径标识',
    title         varchar(255)    not null default '' comment '文章标题',
    `status`      int             not null default 0 comment '文章状态 0：草稿 1：发布',
    category_id   bigint          not null default 0 comment '分类 ID',
    `desc`        varchar(500)    not null default '' comment '文章摘要/片刻内容',
    allow_comment tinyint(1)      not null default 1 comment '允许评论 0：禁止 1：允许',
    is_top        tinyint(1)      not null default 0 comment '是否置顶 0：否 1：是',
    is_original   tinyint(1)      not null default 1 comment '是否是原创 0：否 1：是',
    location      varchar(50)     not null default '' comment '归属地',
    `source`      varchar(500)    not null default '' comment '非原创时来源',
    `password`    varchar(50)     not null default '' comment '私密文章密码',
    file_id       varchar(200)    not null default '' comment '关联附件 文章：封面图 片刻：附件',
    seo_keywords  varchar(500)    not null default '' comment 'SEO 关键字',
    seo_desc      varchar(500)    not null default '' comment 'SEO 描述',
    hits          bigint          not null default 0 comment '文章浏览量',
    likes         bigint          not null default 0 comment '文章点赞数',
    word_count    bigint          not null default 0 comment '文章内容字数统计',
    is_del        tinyint(1)      not null default 0 comment '是否删除',
    create_time   datetime        not null default current_timestamp comment '创建时间',
    update_time   datetime        not null default current_timestamp on update current_timestamp comment '更新时间',
    version       int             not null default 0 comment '乐观锁',
    primary key (id),
    key idx_categoryId (category_id) using btree
) comment '博客表';

create table if not exists article_content
(
    id          bigint unsigned not null auto_increment comment '主键',
    article_id  bigint          not null comment '文章 ID',
    original    longtext        not null comment '文章源',
    content     longtext        not null comment '文章内容',
    is_del      tinyint(1)      not null default 0 comment '是否删除',
    create_time datetime        not null default current_timestamp comment '创建时间',
    update_time datetime        not null default current_timestamp on update current_timestamp comment '更新时间',
    primary key (id),
    key idx_articleId (article_id) using btree
) comment '文章内容表';

create table if not exists post_meta
(
    id          bigint unsigned not null auto_increment comment '主键',
    type        int             not null default 0 comment '类型 0：分类 1：文章标签 2：片刻标签',
    `name`      varchar(50)     not null default '' comment '文章分类/标签名称',
    slug        varchar(25)     not null default '' comment '文章分类/标签别名，路径标识',
    quantity    bigint          not null default 0 comment '文章数量',
    is_del      tinyint(1)      not null default 0 comment '是否删除',
    create_time datetime        not null default current_timestamp comment '创建时间',
    update_time datetime        not null default current_timestamp on update current_timestamp comment '更新时间',
    primary key (id)
) comment '博客分类/标签表';

create table if not exists post_tag_merge
(
    id          bigint unsigned not null auto_increment comment '主键',
    post_id     bigint          not null comment '文章 ID',
    tag_id      bigint          not null comment '标签 ID',
    is_del      tinyint(1)      not null default 0 comment '是否删除',
    create_time datetime        not null default current_timestamp comment '创建时间',
    update_time datetime        not null default current_timestamp on update current_timestamp comment '更新时间',
    primary key (id),
    key idx_postId (post_id) using btree
) comment '博客标签关联表';

create table if not exists attachment_category
(
    id            bigint unsigned not null auto_increment comment '主键',
    `name`        varchar(50)     not null default '' comment '文件分类名称',
    category_desc varchar(255)    not null default '' comment '文件分类描述',
    `password`    varchar(50)     not null default '' comment '文件分类密码',
    is_show       tinyint(1)      not null default 0 comment '是否展示',
    is_del        tinyint(1)      not null default 0 comment '是否删除',
    create_time   datetime        not null default current_timestamp comment '创建时间',
    update_time   datetime        not null default current_timestamp on update current_timestamp comment '更新时间',
    primary key (id)
) comment '文件分类表';

create table if not exists attachment
(
    id          bigint unsigned not null auto_increment comment '主键',
    category_id bigint          not null comment '文件分类 ID',
    `name`      varchar(50)     not null default '' comment '文件名称',
    type        varchar(50)     not null default '' comment '文件类型',
    `path`      varchar(255)    not null default '' comment '文件路径',
    size        bigint          not null default 0 comment '文件大小',
    height      int             null     default null comment '图片高度',
    weight      int             null     default null comment '图片宽度',
    is_del      tinyint(1)      not null default 0 comment '是否删除',
    create_time datetime        not null default current_timestamp comment '创建时间',
    update_time datetime        not null default current_timestamp on update current_timestamp comment '更新时间',
    primary key (id)
) comment '文件表';

create table if not exists blog_comment
(
    id          bigint unsigned not null auto_increment comment '主键',
    type        int             not null default 0 comment '评论类型 0：文章 1：独立页面 2：自定义页面',
    link_id     bigint          not null comment '关联 ID',
    author      varchar(50)     not null default '' comment '评论人名称',
    email       varchar(255)    not null default '' comment '评论人邮箱',
    url         varchar(255)    not null default '' comment '评论人 url',
    content     varchar(1000)   not null default '' comment '评论内容',
    user_agent  varchar(255)    not null default '' comment '评论人设备',
    location    varchar(50)     not null default '' comment '归属地',
    is_admin    tinyint(1)      not null default 0 comment '是否是管理员（作者）',
    parent_id   bigint          null     default null comment '父级 ID',
    is_del      tinyint(1)      not null default 0 comment '是否删除',
    create_time datetime        not null default current_timestamp comment '创建时间',
    update_time datetime        not null default current_timestamp on update current_timestamp comment '更新时间',
    primary key (id)
) comment '评论留言表';

create table if not exists blog_link
(
    id          bigint unsigned not null auto_increment comment '主键',
    type        int             not null default 0 comment '友链类型 0：申请 1：自主',
    `status`    int             not null default 0 comment '评论状态 0：待审核 1：已审核 2：已失效',
    title       varchar(50)     not null default '' comment '站点名称',
    logo        varchar(255)    not null default '' comment '站点 logo',
    url         varchar(255)    not null default '' comment '站点地址',
    `desc`      varchar(255)    not null default '' comment '站点描述',
    email       varchar(255)    not null default '' comment '提交人邮箱',
    is_del      tinyint(1)      not null default 0 comment '是否删除',
    create_time datetime        not null default current_timestamp comment '创建时间',
    update_time datetime        not null default current_timestamp on update current_timestamp comment '更新时间',
    primary key (id)
) comment '友链表';