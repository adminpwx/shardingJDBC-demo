create table t_city
(
    id       bigint(30)   not null
        primary key,
    name     varchar(256) null,
    province varchar(256) null
)
    charset = utf8mb4;


create table t_config
(
    id               bigint(30)                          not null
        primary key,
    remark           varchar(50)                         null,
    create_time      timestamp default CURRENT_TIMESTAMP not null,
    last_modify_time timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP
)
    charset = utf8;


create table t_order_0
(
    id          bigint(200)                         not null comment '订单id'
        primary key,
    order_no    varchar(100)                        null comment '订单编号',
    user_id     bigint(200)                         null comment '用户',
    price       decimal(10, 2)                      null comment '订单价格',
    create_time timestamp default CURRENT_TIMESTAMP not null comment '订单创建时间',
    update_time timestamp                           null on update CURRENT_TIMESTAMP comment '订单更新时间'
)
    charset = utf8;

create table t_order_1
(
    id          bigint(200)                         not null comment '订单id'
        primary key,
    order_no    varchar(100)                        null comment '订单编号',
    user_id     bigint(200)                         null comment '用户',
    price       decimal(10, 2)                      null comment '订单价格',
    create_time timestamp default CURRENT_TIMESTAMP not null comment '订单创建时间',
    update_time timestamp                           null on update CURRENT_TIMESTAMP comment '订单更新时间'
)
    charset = utf8;


create table t_order_2
(
    id          bigint(200)                         not null comment '订单id'
        primary key,
    order_no    varchar(100)                        null comment '订单编号',
    user_id     bigint(200)                         null comment '用户',
    price       decimal(10, 2)                      null comment '订单价格',
    create_time timestamp default CURRENT_TIMESTAMP not null comment '订单创建时间',
    update_time timestamp                           null on update CURRENT_TIMESTAMP comment '订单更新时间'
)
    charset = utf8;

create table t_order_item_0
(
    order_id    bigint(100)                         not null comment '订单id'
        primary key,
    order_no    varchar(200)                        not null comment '订单编号',
    item_name   varchar(50)                         null comment '商品名称',
    item_desc   varchar(50)                         null comment '商品描述',
    create_time timestamp default CURRENT_TIMESTAMP not null comment '订单创建时间',
    update_time timestamp                           null on update CURRENT_TIMESTAMP comment '订单更新时间'
)
    charset = utf8;

    create table t_order_item_1
(
    order_id    bigint(100)                         not null comment '订单id'
        primary key,
    order_no    varchar(200)                        not null comment '订单编号',
    item_name   varchar(50)                         null comment '商品名称',
    item_desc   varchar(50)                         null comment '商品描述',
    create_time timestamp default CURRENT_TIMESTAMP not null comment '订单创建时间',
    update_time timestamp                           null on update CURRENT_TIMESTAMP comment '订单更新时间'
)
    charset = utf8;

create table t_order_item_2
(
    order_id    bigint(100)                         not null comment '订单id'
        primary key,
    order_no    varchar(200)                        not null comment '订单编号',
    item_name   varchar(50)                         null comment '商品名称',
    item_desc   varchar(50)                         null comment '商品描述',
    create_time timestamp default CURRENT_TIMESTAMP not null comment '订单创建时间',
    update_time timestamp                           null on update CURRENT_TIMESTAMP comment '订单更新时间'
)
    charset = utf8;