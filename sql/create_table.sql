create database crud;

use crud;

-- 用户表
create table user
(
    id          bigint auto_increment
        primary key,
    name        varchar(20)  null comment '姓名',
    age         int          null comment '年龄',
    status      int          null comment '状态（1有效，0无效）',
    sex         varchar(1)   null comment '性别',
    address     varchar(255) null comment '地址',
    phone       varchar(20)  null comment '电话',
    create_time datetime     null comment '创建时间',
    update_time datetime     null comment '修改时间'
)
    engine = InnoDB
    charset = utf8;

-- 操作日志表
create table operate_log
(
    id            int unsigned primary key auto_increment comment 'ID',
    operate_user  int unsigned comment '操作人',
    operate_time  datetime comment '操作时间',
    class_name    varchar(100) comment '操作的类名',
    method_name   varchar(100) comment '操作的方法名',
    method_params varchar(1000) comment '方法参数',
    return_value  varchar(2000) comment '返回值',
    cost_time     bigint comment '方法执行耗时, 单位:ms'
) comment '操作日志表';