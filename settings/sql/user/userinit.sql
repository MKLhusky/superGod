create schema super_user collate utf8mb4_bin;

create table super_user.user_base
(
    user_id               bigint collate utf8mb4_bin                                                         not null comment '主键',
    user_name         varchar(50) collate utf8mb4_bin                                                    not null comment '用户姓名',
    user_photo        varchar(200) collate utf8mb4_bin                                                   null comment '用户照片或头像',
    user_nick         varchar(20) collate utf8mb4_bin                                                    null comment '用户昵称',
    user_account      varchar(30) collate utf8mb4_bin                                                    not null comment '用户账号(登陆)',
    password          varchar(100) collate utf8mb4_bin                                                   null comment '密码',
    password_salt     varchar(50) collate utf8mb4_bin                                                    null comment '密码盐',
    id_card           char(18) collate utf8mb4_bin                                                       null comment '身份证',
    sex               tinyint collate utf8mb4_bin                                                        null comment '性别(0 女 1男  2未知)',
    identity          tinyint collate utf8mb4_bin                                                        not null comment '系统身份 (0 超管 1 管理  2 普通用户)',
    business_identity bigint collate utf8mb4_bin                                                         null comment '业务身份标识',
    business_unique   bigint collate utf8mb4_bin                                                         null comment '业务唯一标识',
    status            tinyint collate utf8mb4_bin  default 0                                             not null comment '账号状态 (0 正常  1删除   2冻结  3异常)',
    remark            varchar(200) collate utf8mb4_bin                                                   null comment '简要备注',
    create_time       datetime collate utf8mb4_bin default current_timestamp                             null comment '创建时间',
    create_by         bigint collate utf8mb4_bin                                                         not null comment '创建人',
    update_time       datetime collate utf8mb4_bin default current_timestamp on update current_timestamp null comment '更新时间',
    update_by         bigint collate utf8mb4_bin                                                         not null comment '更新人',
    version           bigint collate utf8mb4_bin   default 0                                             null comment '版本号'
)
    comment '用户基本信息表';


create table super_user.user_contact
(
    contact_id    bigint collate utf8mb4_bin      not null comment '主键',
    user_id       bigint collate utf8mb4_bin      not null comment '关联用户基础表主键',
    contact_type  tinyint collate utf8mb4_bin     null comment '联系类型(0 手机号  1 座机  2 邮箱  3 社交账号  4地址)',
    contact_value varchar(50) collate utf8mb4_bin null comment '联系方式(值)',
    status            tinyint collate utf8mb4_bin  default 0                                             not null comment '数据状态 (0 正常  1删除)',
    remark            varchar(200) collate utf8mb4_bin                                                   null comment '简要备注',
    create_time       datetime collate utf8mb4_bin default current_timestamp                             null comment '创建时间',
    create_by         bigint collate utf8mb4_bin                                                         not null comment '创建人',
    update_time       datetime collate utf8mb4_bin default current_timestamp on update current_timestamp null comment '更新时间',
    update_by         bigint collate utf8mb4_bin                                                         not null comment '更新人',
    version           bigint collate utf8mb4_bin   default 0                                             null comment '版本号'
)
    comment '用户联系方式';



