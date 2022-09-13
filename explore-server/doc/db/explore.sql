create table if not exists user
(
    id          bigint unsigned not null auto_increment comment '主键',
    username    varchar(255)    not null comment '用户名',
    password    varchar(255)    not null comment '密码',
    email       varchar(255)    not null comment '邮箱',
    avatar      varchar(50)     null     default null comment '头像',
    summary     varchar(255)    not null default '这个人很懒，什么都没有留下～' comment '个人简介',
    is_del      tinyint(1)      not null default 0 comment '是否删除',
    create_time datetime        not null default current_timestamp comment '创建时间',
    update_time datetime        not null default current_timestamp on update current_timestamp comment '更新时间',
    primary key (id)
) comment '用户表';

create table if not exists menu
(
    id          bigint unsigned not null auto_increment comment '主键',
    name        varchar(255)    not null comment '菜单名称',
    type        int             not null default 0 comment '菜单类型 0：后台，1：前台',
    parent_id   bigint          not null default 0 comment '父级 ID',
    route       varchar(255)    not null comment '路由地址',
    icon        varchar(255)    null     default null comment '菜单图标',
    sort        int             not null comment '菜单排序，值越小越靠前',
    is_del      tinyint(1)      not null default 0 comment '是否删除',
    create_time datetime        not null default current_timestamp comment '创建时间',
    update_time datetime        not null default current_timestamp on update current_timestamp comment '更新时间',
    primary key (id)
) comment '菜单表';

create table if not exists article
(
    id            bigint unsigned not null auto_increment comment '主键',
    alias         varchar(25)     not null comment '文章别名，路径标识',
    title         varchar(255)    not null comment '文章标题',
    status        int             not null default 0 comment '文章状态 0：草稿 1：发布',
    category_id   bigint          not null comment '分类 ID',
    summary       varchar(255)    not null comment '文章摘要',
    allow_comment tinyint(1)      not null default 1 comment '允许评论 0：禁止 1：允许',
    is_top        tinyint(1)      not null default 0 comment '是否置顶 0：否 1：是',
    is_original   tinyint(1)      not null default 1 comment '是否是原创 0：否 1：是',
    source        varchar(500)    null     default null comment '非原创时来源',
    password      varchar(50)     null     default null comment '私密文章密码',
    thumbnail     varchar(50)     null     default null comment '封面图',
    seo_keywords  varchar(500)    null     default null comment 'SEO 关键字',
    seo_desc      varchar(500)    not null comment 'SEO 描述',
    hits          bigint          not null default 0 comment '文章浏览量',
    likes         bigint          not null default 0 comment '文章点赞数',
    word_count    bigint          not null default 0 comment '文章内容字数统计',
    is_del        tinyint(1)      not null default 0 comment '是否删除',
    create_time   datetime        not null default current_timestamp comment '创建时间',
    update_time   datetime        not null default current_timestamp on update current_timestamp comment '更新时间',
    primary key (id),
    key idx_categoryId (category_id) using btree
) comment '文章表';

create table if not exists article_content
(
    id          bigint unsigned not null auto_increment comment '主键',
    article_id  bigint          not null comment '文章 ID',
    content     longtext        not null comment '文章内容',
    is_del      tinyint(1)      not null default 0 comment '是否删除',
    create_time datetime        not null default current_timestamp comment '创建时间',
    update_time datetime        not null default current_timestamp on update current_timestamp comment '更新时间',
    primary key (id),
    key idx_articleId (article_id) using btree
) comment '文章内容表';

create table if not exists category
(
    id          bigint unsigned not null auto_increment comment '主键',
    name        varchar(50)     not null comment '分类名称',
    alias       varchar(25)     not null comment '分类别名，路径标识',
    parent_id   bigint          null     default null comment '父级 ID',
    sort        int             not null default 0 comment '排序',
    is_del      tinyint(1)      not null default 0 comment '是否删除',
    create_time datetime        not null default current_timestamp comment '创建时间',
    update_time datetime        not null default current_timestamp on update current_timestamp comment '更新时间',
    primary key (id)
) comment '分类表';

create table if not exists tag
(
    id          bigint unsigned not null auto_increment comment '主键',
    name        varchar(50)     not null comment '标签名称',
    alias       varchar(25)     not null comment '标签别名，路径标识',
    is_del      tinyint(1)      not null default 0 comment '是否删除',
    create_time datetime        not null default current_timestamp comment '创建时间',
    update_time datetime        not null default current_timestamp on update current_timestamp comment '更新时间',
    primary key (id)
) comment '标签表';

create table if not exists article_tag
(
    id          bigint unsigned not null auto_increment comment '主键',
    article_id  bigint          not null comment '文章 ID',
    tag_id      bigint          not null comment '标签 ID',
    is_del      tinyint(1)      not null default 0 comment '是否删除',
    create_time datetime        not null default current_timestamp comment '创建时间',
    update_time datetime        not null default current_timestamp on update current_timestamp comment '更新时间',
    primary key (id),
    key idx_articleId (article_id) using btree,
    key idx_tagId (tag_id) using btree
) comment '文章标签关联表';

create table if not exists attachment_category
(
    id            bigint unsigned not null auto_increment comment '主键',
    name          varchar(50)     not null comment '文件分类名称',
    category_desc varchar(255)    not null comment '分类描述',
    password      varchar(50)     null     default null comment '私密分类密码',
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
    name        varchar(50)     not null comment '文件名称',
    type        varchar(50)     not null comment '文件类型',
    path        varchar(255)    not null comment '文件路径',
    size        bigint          not null comment '文件大小',
    height      int             null     default null comment '图片高度',
    weight      int             null     default null comment '图片宽度',
    is_del      tinyint(1)      not null default 0 comment '是否删除',
    create_time datetime        not null default current_timestamp comment '创建时间',
    update_time datetime        not null default current_timestamp on update current_timestamp comment '更新时间',
    primary key (id)
) comment '文件表';

create table if not exists comment
(
    id          bigint unsigned not null auto_increment comment '主键',
    source      int             not null default 0 comment '评论来源 0：文章 1：独立页面 2：自定义页面',
    source_id   bigint          not null comment '来源 ID',
    author      varchar(50)     not null comment '评论人名称',
    email       varchar(255)    not null comment '评论人邮箱',
    url         varchar(255)    null     default null comment '评论人 url',
    status      int             not null default 0 comment '评论状态 0：待审核 1：已审核 2：回收',
    content     varchar(1000)   not null comment '评论内容',
    user_agent  varchar(255)    not null comment '评论人设备',
    is_admin    tinyint(1)      not null default 0 comment '是否是管理员（作者）',
    parent_id   bigint          null     default null comment '父级 ID',
    is_del      tinyint(1)      not null default 0 comment '是否删除',
    create_time datetime        not null default current_timestamp comment '创建时间',
    update_time datetime        not null default current_timestamp on update current_timestamp comment '更新时间',
    primary key (id),
    key idx_source (source, source_id) using btree
) comment '评论留言表';

create table if not exists config
(
    id          bigint unsigned not null auto_increment comment '主键',
    code        int unique      not null comment '配置 code',
    config_desc varchar(50)     not null comment '配置描述',
    value       varchar(255)    not null comment '配置值',
    is_del      tinyint(1)      not null default 0 comment '是否删除',
    create_time datetime        not null default current_timestamp comment '创建时间',
    update_time datetime        not null default current_timestamp on update current_timestamp comment '更新时间',
    primary key (id)
) comment '配置表';