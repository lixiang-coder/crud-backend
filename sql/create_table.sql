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

INSERT INTO user (name, age, status, sex, address, phone, create_time, update_time)
SELECT
    CONCAT('姓名', FLOOR(RAND() * 100)), -- 随机生成姓名
    FLOOR(RAND() * 80) + 1, -- 随机生成年龄，范围1-80
    FLOOR(RAND() * 2) + 1, -- 随机生成状态，1有效，0无效
    CASE FLOOR(RAND() * 2) WHEN 0 THEN '男' ELSE '女' END, -- 随机生成性别
    CONCAT('地址', FLOOR(RAND() * 100)), -- 随机生成地址
    CONCAT('电话', LPAD(FLOOR(RAND() * 10000000000), 11, '0')), -- 随机生成电话
    NOW(), -- 创建时间
    NOW() -- 修改时间
FROM
    (SELECT @rownum:=0) r
        JOIN
    (SELECT @rownum:=@rownum+1 AS n FROM information_schema.tables LIMIT 100) t; -- 生成100行