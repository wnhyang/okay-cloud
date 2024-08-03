create table okay.sys_dict_data
(
    id          bigint auto_increment comment '字典数据id'
        primary key,
    sort        int         default 99                not null comment '字典排序',
    label       varchar(30) default ''                not null comment '字典标签',
    value       varchar(30) default ''                not null comment '字典键值',
    dict_type   varchar(30)                           not null comment '字典类型',
    color       varchar(20) default 'default'         not null comment '颜色',
    status      bit         default b'0'              not null comment '状态',
    remark      varchar(100)                          null comment '备注',
    creator     varchar(30) default ''                null comment '创建者',
    create_time datetime    default CURRENT_TIMESTAMP not null comment '创建时间',
    updater     varchar(30) default ''                null comment '更新者',
    update_time datetime    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    deleted     bit         default b'0'              not null comment '是否删除'
)
    comment '字典数据表' charset = utf8mb4;

create table okay.sys_dict_type
(
    id          bigint auto_increment comment '字典类型id'
        primary key,
    name        varchar(30) default ''                not null comment '字典名称',
    type        varchar(30) default ''                not null comment '字典类型',
    status      bit         default b'0'              not null comment '状态',
    standard    bit         default b'0'              null comment '是否内置',
    remark      varchar(100)                          null comment '备注',
    creator     varchar(30) default ''                null comment '创建者',
    create_time datetime    default CURRENT_TIMESTAMP not null comment '创建时间',
    updater     varchar(30) default ''                null comment '更新者',
    update_time datetime    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    deleted     bit         default b'0'              not null comment '是否删除',
    constraint dict_type
        unique (type)
)
    comment '字典类型表' charset = utf8mb4;

create table okay.sys_login_log
(
    id          bigint auto_increment comment '登录日志id'
        primary key,
    login_type  bigint                                not null comment '登录类型',
    user_id     bigint      default 0                 not null comment '用户编号',
    user_type   tinyint     default 0                 not null comment '用户类型',
    account     varchar(30) default ''                not null comment '用户账号',
    result      tinyint                               not null comment '登录结果',
    user_ip     varchar(50)                           not null comment '用户 IP',
    user_agent  varchar(200)                          not null comment '浏览器 UA',
    creator     varchar(30) default ''                null comment '创建者',
    create_time datetime    default CURRENT_TIMESTAMP not null comment '创建时间',
    updater     varchar(30) default ''                null comment '更新者',
    update_time datetime    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    deleted     bit         default b'0'              not null comment '是否删除'
)
    comment '系统访问记录' charset = utf8mb4;

create table okay.sys_menu
(
    id                  bigint auto_increment comment '菜单id'
        primary key,
    name                varchar(30)                            null comment '菜单名称',
    permission          varchar(30)                            null comment '权限标识',
    type                tinyint                                not null comment '菜单类型',
    order_no            int          default 0                 not null comment '显示顺序',
    parent_id           bigint       default 0                 not null comment '父菜单ID',
    path                varchar(50)  default ''                null comment '路由地址',
    icon                varchar(100) default '#'               null comment '菜单图标',
    component           varchar(100)                           null comment '组件路径',
    hide_breadcrumb     bit          default b'0'              null comment '隐藏面包屑',
    current_active_menu varchar(50)                            null comment '当前激活菜单',
    keepalive           bit          default b'0'              null comment '缓存',
    title               varchar(50)                            null comment '组件名',
    redirect            varchar(50)                            null comment '可点击',
    status              bit          default b'0'              not null comment '菜单状态',
    is_show             bit          default b'0'              not null comment '是否可见',
    creator             varchar(30)  default ''                null comment '创建者',
    create_time         datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    updater             varchar(30)  default ''                null comment '更新者',
    update_time         datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    deleted             bit          default b'0'              not null comment '是否删除',
    is_ext              bit          default b'0'              null comment '是否外链'
)
    comment '菜单权限表' charset = utf8mb4;

create table okay.sys_operate_log
(
    id               bigint auto_increment comment '操作日志id'
        primary key,
    user_id          bigint                                  not null comment '用户编号',
    module           varchar(50)                             not null comment '模块标题',
    name             varchar(50)                             not null comment '操作名',
    type             int           default 0                 not null comment '操作分类',
    content          varchar(2000) default ''                not null comment '操作内容',
    exts             varchar(512)  default ''                not null comment '拓展字段',
    request_method   varchar(16)   default ''                null comment '请求方法名',
    request_url      varchar(255)  default ''                null comment '请求地址',
    user_ip          varchar(50)                             null comment '用户 IP',
    user_agent       varchar(200)                            null comment '浏览器 UA',
    java_method      varchar(512)  default ''                not null comment 'Java 方法名',
    java_method_args varchar(8000) default ''                null comment 'Java 方法的参数',
    start_time       datetime                                not null comment '操作时间',
    duration         int                                     not null comment '执行时长',
    result_code      int           default 0                 not null comment '结果码',
    result_msg       varchar(512)  default ''                null comment '结果提示',
    result_data      varchar(4000) default ''                null comment '结果数据',
    creator          varchar(30)   default ''                null comment '创建者',
    create_time      datetime      default CURRENT_TIMESTAMP not null comment '创建时间',
    updater          varchar(30)   default ''                null comment '更新者',
    update_time      datetime      default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    deleted          bit           default b'0'              not null comment '是否删除'
)
    comment '操作日志记录' charset = utf8mb4;

create table okay.sys_role
(
    id          bigint auto_increment comment '角色id'
        primary key,
    name        varchar(30)                           not null comment '角色名称',
    value       varchar(30)                           not null comment '角色权限字符串',
    sort        int         default 99                not null comment '显示顺序',
    status      bit         default b'0'              not null comment '角色状态',
    remark      varchar(200)                          null comment '备注',
    creator     varchar(30) default ''                null comment '创建者',
    create_time datetime    default CURRENT_TIMESTAMP not null comment '创建时间',
    updater     varchar(30) default ''                null comment '更新者',
    update_time datetime    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    deleted     bit         default b'0'              not null comment '是否删除'
)
    comment '角色信息表' charset = utf8mb4;

create table okay.sys_role_menu
(
    id          bigint auto_increment comment '角色菜单id'
        primary key,
    role_id     bigint                                not null comment '角色ID',
    menu_id     bigint                                not null comment '菜单ID',
    creator     varchar(30) default ''                null comment '创建者',
    create_time datetime    default CURRENT_TIMESTAMP not null comment '创建时间',
    updater     varchar(30) default ''                null comment '更新者',
    update_time datetime    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    deleted     bit         default b'0'              not null comment '是否删除'
)
    comment '角色和菜单关联表' charset = utf8mb4;

create table okay.sys_rsa
(
    id          bigint                                not null comment '密钥id'
        primary key,
    private_key varchar(255)                          null comment '私钥',
    public_key  varchar(255)                          null comment '公钥',
    remark      varchar(200)                          null comment '描述',
    creator     varchar(30) default ''                null comment '创建者',
    create_time datetime    default CURRENT_TIMESTAMP not null comment '创建时间',
    updater     varchar(30) default ''                null comment '更新者',
    update_time datetime    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    deleted     bit         default b'0'              not null comment '是否删除'
)
    comment 'Rsa密钥表' charset = utf8mb4;

create table okay.sys_user
(
    id          bigint auto_increment comment '用户id'
        primary key,
    username    varchar(30)                            not null comment '用户账号',
    password    varchar(100) default ''                not null comment '密码',
    nickname    varchar(30)                            not null comment '用户昵称',
    type        tinyint      default 0                 not null comment '用户类型',
    remark      varchar(200)                           null comment '描述',
    email       varchar(50)  default ''                null comment '用户邮箱',
    mobile      varchar(11)  default ''                null comment '手机号码',
    sex         tinyint      default 0                 null comment '用户性别',
    avatar      varchar(200) default ''                null comment '头像地址',
    status      bit          default b'0'              not null comment '帐号状态',
    login_ip    varchar(50)  default ''                null comment '最后登录IP',
    login_date  datetime                               null comment '最后登录时间',
    creator     varchar(30)  default ''                null comment '创建者',
    create_time datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    updater     varchar(30)  default ''                null comment '更新者',
    update_time datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    deleted     bit          default b'0'              not null comment '是否删除',
    constraint uk_email
        unique (email),
    constraint uk_mobile
        unique (mobile),
    constraint uk_username
        unique (username, update_time)
)
    comment '用户信息表' charset = utf8mb4;

create table okay.sys_user_role
(
    id          bigint auto_increment comment '自增编号'
        primary key,
    user_id     bigint                                not null comment '用户ID',
    role_id     bigint                                not null comment '角色ID',
    creator     varchar(30) default ''                null comment '创建者',
    create_time datetime    default CURRENT_TIMESTAMP null comment '创建时间',
    updater     varchar(30) default ''                null comment '更新者',
    update_time datetime    default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    deleted     bit         default b'0'              null comment '是否删除'
)
    comment '用户和角色关联表' charset = utf8mb4;

insert into okay.sys_menu (id, name, permission, type, order_no, parent_id, path, icon, component, hide_breadcrumb, current_active_menu, keepalive, title, redirect, status, is_show, creator, create_time, updater, update_time, deleted, is_ext)
values  (1, 'System', null, 0, 0, 0, '/system', 'ant-design:audit-outlined', null, false, null, false, '系统管理', '/system/user', false, false, '', '2024-03-25 10:12:09', '', '2024-03-25 10:12:09', false, false),
        (100, 'User', 'system:user:list', 1, 1, 1, 'user', 'ant-design:user-outlined', 'system/user/index', false, null, false, '用户管理', null, false, false, '', '2024-03-25 10:12:09', 'admin', '2024-06-30 15:46:04', false, false),
        (101, 'Role', 'system:role:list', 1, 2, 1, 'role', 'ant-design:crown-outlined', 'system/role/index', false, null, false, '角色管理', null, false, false, '', '2024-03-25 10:12:09', 'admin', '2024-06-30 15:46:17', false, false),
        (102, 'Menu', 'system:menu:list', 1, 3, 1, 'menu', 'ant-design:menu-fold-outlined', 'system/menu/index', false, null, false, '菜单管理', null, false, false, '', '2024-03-25 10:12:09', 'admin', '2024-06-30 15:46:40', false, false),
        (103, 'Password', null, 1, 97, 1, 'password', 'ant-design:unlock-outlined', 'system/password/index', false, null, false, '修改密码', null, false, false, '', '2024-03-25 10:12:09', '', '2024-03-26 11:06:45', true, false),
        (104, 'Log', null, 0, 99, 1, 'log', 'ant-design:ordered-list-outlined', null, false, null, false, '日志管理', '/system/log/loginlog', false, false, '', '2024-03-25 10:12:09', '', '2024-03-25 10:12:09', false, false),
        (105, 'Dict', null, 0, 4, 1, 'dict', 'ant-design:medicine-box-outlined', null, false, null, false, '字典管理', '/system/dict/dicttype', false, false, '', '2024-03-25 10:12:09', '', '2024-03-25 10:12:09', false, false),
        (106, 'UserDetail', null, 1, 0, 1, 'userDetail/:id', 'ant-design:carry-out-twotone', 'system/user/UserDetail', false, '/system/user', false, '用户详情', null, false, true, '', '2024-03-25 10:12:09', '', '2024-03-25 10:12:09', false, false),
        (1001, null, 'system:user:create', 2, 0, 100, '', '', '', false, null, false, '用户新增', null, false, false, '', '2024-03-25 10:12:09', '', '2024-03-25 10:12:09', false, false),
        (1002, null, 'system:user:update', 2, 0, 100, '', '', '', false, null, false, '用户更新', null, false, false, '', '2024-03-25 10:12:09', '', '2024-03-25 10:12:09', false, false),
        (1003, null, 'system:user:delete', 2, 0, 100, '', '', '', false, null, false, '用户删除', null, false, false, '', '2024-03-25 10:12:09', '', '2024-03-25 10:12:09', false, false),
        (1004, null, 'system:user:query', 2, 0, 100, '', '', '', false, null, false, '用户查询', null, false, false, '', '2024-03-25 10:12:09', '', '2024-03-25 10:12:09', false, false),
        (1011, null, 'system:role:create', 2, 0, 101, '', '', '', false, null, false, '角色新增', null, false, false, '', '2024-03-25 10:12:09', '', '2024-03-25 10:12:09', false, false),
        (1012, null, 'system:role:update', 2, 0, 101, '', '', '', false, null, false, '角色更新', null, false, false, '', '2024-03-25 10:12:09', '', '2024-03-25 10:12:09', false, false),
        (1013, null, 'system:role:delete', 2, 0, 101, '', '', '', false, null, false, '角色删除', null, false, false, '', '2024-03-25 10:12:09', '', '2024-03-25 10:12:09', false, false),
        (1014, null, 'system:role:query', 2, 0, 101, '', '', '', false, null, false, '角色查询', null, false, false, '', '2024-03-25 10:12:09', '', '2024-03-25 10:12:09', false, false),
        (1021, null, 'system:menu:create', 2, 0, 102, '', '', '', false, null, false, '菜单新增', null, false, false, '', '2024-03-25 10:12:09', '', '2024-03-25 10:12:09', false, false),
        (1022, null, 'system:menu:update', 2, 0, 102, '', '', '', false, null, false, '菜单更新', null, false, false, '', '2024-03-25 10:12:09', '', '2024-03-25 10:12:09', false, false),
        (1023, null, 'system:menu:delete', 2, 0, 102, '', '', '', false, null, false, '菜单删除', null, false, false, '', '2024-03-25 10:12:09', '', '2024-03-25 10:12:09', false, false),
        (1024, null, 'system:menu:query', 2, 0, 102, '', '', '', false, null, false, '菜单查询', null, false, false, '', '2024-03-25 10:12:09', '', '2024-03-25 10:12:09', false, false),
        (1041, 'Loginlog', null, 1, 0, 104, 'loginlog', 'ant-design:login-outlined', 'system/loginLog/index', false, null, false, '登录日志', null, false, false, '', '2024-03-25 10:12:09', '', '2024-03-25 10:12:09', false, false),
        (1042, 'Operatelog', null, 1, 0, 104, 'operatelog', 'ant-design:logout-outlined', 'system/operateLog/index', false, null, false, '操作日志', null, false, false, '', '2024-03-25 10:12:09', '', '2024-03-25 10:12:09', false, false),
        (1051, 'DictType', null, 1, 0, 105, 'dicttype', 'ant-design:book-outlined', 'system/dictType/index', false, null, false, '字典类型', null, false, false, '', '2024-03-25 10:12:09', '', '2024-03-25 10:12:09', false, false),
        (1052, 'DictData', null, 1, 0, 105, 'dictdata', 'ant-design:behance-outlined', 'system/dictData/index', false, null, false, '字典数据', null, false, false, '', '2024-03-25 10:12:09', '', '2024-03-25 10:12:09', false, false),
        (1053, null, 'system:user:import', 2, 0, 100, '', '', '', false, null, false, '用户导入', null, false, false, 'admin', '2024-06-30 15:35:33', 'admin', '2024-06-30 15:35:33', false, false),
        (1054, null, 'system:user:export', 2, 0, 100, '', '', '', false, null, false, '用户导出', null, false, false, 'admin', '2024-06-30 15:36:12', 'admin', '2024-06-30 15:36:12', false, false),
        (1055, null, 'system:role:import', 2, 0, 101, '', '', '', false, null, false, '角色导入', null, false, false, 'admin', '2024-06-30 15:37:01', 'admin', '2024-06-30 15:37:01', false, false),
        (1056, null, 'system:role:export', 2, 0, 101, '', '', '', false, null, false, '角色导出', null, false, false, 'admin', '2024-06-30 15:37:33', 'admin', '2024-06-30 15:37:33', false, false),
        (1057, null, 'system:menu:export', 2, 0, 102, '', '', '', false, null, false, '菜单导出', null, false, false, 'admin', '2024-06-30 15:38:11', 'admin', '2024-06-30 15:38:11', false, false);

insert into okay.sys_role (id, name, value, sort, status, remark, creator, create_time, updater, update_time, deleted)
values  (1, '超级管理员', 'administrator', 0, false, null, '', '2024-03-25 08:59:56', '', '2024-03-25 08:59:56', false),
        (2, '操作员', 'operator', 99, false, null, 'admin', '2024-03-26 20:45:35', 'admin', '2024-06-30 16:26:00', false),
        (3, '管理员', 'admin', 99, false, null, 'admin', '2024-06-30 15:27:44', 'admin', '2024-06-30 16:28:19', false);

insert into okay.sys_role_menu (id, role_id, menu_id, creator, create_time, updater, update_time, deleted)
values  (1, 2, 104, 'admin', '2024-03-26 20:45:36', 'admin', '2024-03-26 21:35:57', true),
        (2, 2, 1041, 'admin', '2024-03-26 20:45:36', 'admin', '2024-03-26 21:35:57', true),
        (3, 2, 1042, 'admin', '2024-03-26 20:45:36', 'admin', '2024-03-26 21:35:57', true),
        (4, 2, 1041, 'admin', '2024-03-26 21:35:58', 'admin', '2024-06-30 16:25:59', true),
        (5, 2, 1042, 'admin', '2024-03-26 21:35:58', 'admin', '2024-06-30 16:25:59', true),
        (6, 2, 104, 'admin', '2024-03-26 21:35:58', 'admin', '2024-06-30 16:25:59', true),
        (7, 2, 105, 'admin', '2024-03-26 21:35:58', 'admin', '2024-06-30 16:25:59', true),
        (8, 2, 1051, 'admin', '2024-03-26 21:35:58', 'admin', '2024-06-30 16:25:59', true),
        (9, 2, 1052, 'admin', '2024-03-26 21:35:58', 'admin', '2024-06-30 16:25:59', true),
        (10, 3, 1024, 'admin', '2024-06-30 15:27:44', 'admin', '2024-06-30 16:28:18', true),
        (11, 3, 1, 'admin', '2024-06-30 15:27:44', 'admin', '2024-06-30 16:28:18', true),
        (12, 3, 100, 'admin', '2024-06-30 15:27:44', 'admin', '2024-06-30 16:28:18', true),
        (13, 3, 101, 'admin', '2024-06-30 15:27:44', 'admin', '2024-06-30 16:28:18', true),
        (14, 3, 102, 'admin', '2024-06-30 15:27:44', 'admin', '2024-06-30 16:28:18', true),
        (15, 3, 104, 'admin', '2024-06-30 15:27:44', 'admin', '2024-06-30 16:28:18', true),
        (16, 3, 105, 'admin', '2024-06-30 15:27:44', 'admin', '2024-06-30 16:28:18', true),
        (17, 3, 1001, 'admin', '2024-06-30 15:27:44', 'admin', '2024-06-30 16:28:18', true),
        (18, 3, 106, 'admin', '2024-06-30 15:27:44', 'admin', '2024-06-30 16:28:18', true),
        (19, 3, 1002, 'admin', '2024-06-30 15:27:44', 'admin', '2024-06-30 16:28:18', true),
        (20, 3, 1003, 'admin', '2024-06-30 15:27:44', 'admin', '2024-06-30 16:28:18', true),
        (21, 3, 1004, 'admin', '2024-06-30 15:27:44', 'admin', '2024-06-30 16:28:18', true),
        (22, 3, 1041, 'admin', '2024-06-30 15:27:44', 'admin', '2024-06-30 16:28:18', true),
        (23, 3, 1042, 'admin', '2024-06-30 15:27:44', 'admin', '2024-06-30 16:28:18', true),
        (24, 3, 1011, 'admin', '2024-06-30 15:27:44', 'admin', '2024-06-30 16:28:18', true),
        (25, 3, 1012, 'admin', '2024-06-30 15:27:44', 'admin', '2024-06-30 16:28:18', true),
        (26, 3, 1013, 'admin', '2024-06-30 15:27:44', 'admin', '2024-06-30 16:28:18', true),
        (27, 3, 1014, 'admin', '2024-06-30 15:27:44', 'admin', '2024-06-30 16:28:18', true),
        (28, 3, 1051, 'admin', '2024-06-30 15:27:44', 'admin', '2024-06-30 16:28:18', true),
        (29, 3, 1052, 'admin', '2024-06-30 15:27:44', 'admin', '2024-06-30 16:28:18', true),
        (30, 3, 1021, 'admin', '2024-06-30 15:27:44', 'admin', '2024-06-30 16:28:18', true),
        (31, 3, 1022, 'admin', '2024-06-30 15:27:44', 'admin', '2024-06-30 16:28:18', true),
        (32, 3, 1023, 'admin', '2024-06-30 15:27:44', 'admin', '2024-06-30 16:28:18', true),
        (33, 2, 1041, 'admin', '2024-06-30 16:25:59', 'admin', '2024-06-30 16:25:59', false),
        (34, 2, 1051, 'admin', '2024-06-30 16:26:00', 'admin', '2024-06-30 16:26:00', false),
        (35, 3, 1024, 'admin', '2024-06-30 16:28:19', 'admin', '2024-06-30 16:28:19', false),
        (36, 3, 104, 'admin', '2024-06-30 16:28:19', 'admin', '2024-06-30 16:28:19', false),
        (37, 3, 105, 'admin', '2024-06-30 16:28:19', 'admin', '2024-06-30 16:28:19', false),
        (38, 3, 1001, 'admin', '2024-06-30 16:28:19', 'admin', '2024-06-30 16:28:19', false),
        (39, 3, 106, 'admin', '2024-06-30 16:28:19', 'admin', '2024-06-30 16:28:19', false),
        (40, 3, 1002, 'admin', '2024-06-30 16:28:19', 'admin', '2024-06-30 16:28:19', false),
        (41, 3, 1003, 'admin', '2024-06-30 16:28:19', 'admin', '2024-06-30 16:28:19', false),
        (42, 3, 1004, 'admin', '2024-06-30 16:28:19', 'admin', '2024-06-30 16:28:19', false),
        (43, 3, 1041, 'admin', '2024-06-30 16:28:19', 'admin', '2024-06-30 16:28:19', false),
        (44, 3, 1042, 'admin', '2024-06-30 16:28:19', 'admin', '2024-06-30 16:28:19', false),
        (45, 3, 1011, 'admin', '2024-06-30 16:28:19', 'admin', '2024-06-30 16:28:19', false),
        (46, 3, 1012, 'admin', '2024-06-30 16:28:19', 'admin', '2024-06-30 16:28:19', false),
        (47, 3, 1013, 'admin', '2024-06-30 16:28:19', 'admin', '2024-06-30 16:28:19', false),
        (48, 3, 1014, 'admin', '2024-06-30 16:28:19', 'admin', '2024-06-30 16:28:19', false),
        (49, 3, 1051, 'admin', '2024-06-30 16:28:19', 'admin', '2024-06-30 16:28:19', false),
        (50, 3, 1052, 'admin', '2024-06-30 16:28:19', 'admin', '2024-06-30 16:28:19', false),
        (51, 3, 1021, 'admin', '2024-06-30 16:28:19', 'admin', '2024-06-30 16:28:19', false),
        (52, 3, 1022, 'admin', '2024-06-30 16:28:19', 'admin', '2024-06-30 16:28:19', false),
        (53, 3, 1023, 'admin', '2024-06-30 16:28:19', 'admin', '2024-06-30 16:28:19', false);

insert into okay.sys_user (id, username, password, nickname, type, remark, email, mobile, sex, avatar, status, login_ip, login_date, creator, create_time, updater, update_time, deleted)
values  (1, 'admin', '$2a$10$YxtQzqA1SX6M2rov0DLDveyLa1a7K737k46MxMGUlOqY46nW4Vz02', 'admin', 0, null, 'wnhyang@qq.com', '17317430552', 0, '', false, '127.0.0.1', '2024-07-05 19:46:03', '', '2024-03-25 08:59:56', 'admin', '2024-07-05 19:46:03', false),
        (2, 'zhangsan', '$2a$10$TtOM8M7KW0MXapaN6a3awOxko9AdPoFAMk7EM6.esMtv46D/8/IDG', '张三', 0, null, '3423@qq.com', '13423454567', 0, '', false, '127.0.0.1', '2024-03-26 21:57:05', 'admin', '2024-03-26 13:40:16', null, '2024-03-26 21:57:05', false),
        (3, 'lisi', '$2a$10$q8HB8XHmmDvHaFC4L4LMze3bWVH.JFkpaSf123Vz.iHWc9Ag.z9de', '李四', 0, null, '325423@qq.com', '17485984758', 1, '', false, '127.0.0.1', '2024-03-28 16:23:44', 'admin', '2024-03-26 20:53:32', null, '2024-03-28 16:23:44', false),
        (4, 'wnhyang', '$2a$10$uqU5jmFmn6BoFa2vraUXyuiwXfgB23LRK7OrSWhHCOROK4qxyEifi', '无奈何杨', 0, null, 'wnhyang@163.com', '17317430553', 1, '', false, '127.0.0.1', '2024-06-30 15:22:04', 'admin', '2024-06-30 15:11:38', 'admin', '2024-06-30 15:27:55', false);

insert into okay.sys_user_role (id, user_id, role_id, creator, create_time, updater, update_time, deleted)
values  (1, 1, 1, '', '2024-03-25 08:59:56', '', '2024-03-25 08:59:56', false),
        (2, 2, 1, 'admin', '2024-03-26 13:40:16', 'admin', '2024-03-26 20:50:28', true),
        (3, 2, 2, 'admin', '2024-03-26 20:50:29', 'admin', '2024-03-26 20:50:29', false),
        (4, 3, 2, 'admin', '2024-03-26 20:53:32', 'admin', '2024-03-26 20:53:32', false),
        (5, 4, 1, 'admin', '2024-06-30 15:11:38', 'admin', '2024-06-30 15:22:37', true),
        (6, 4, 2, 'admin', '2024-06-30 15:22:38', 'admin', '2024-06-30 15:27:55', true),
        (7, 4, 3, 'admin', '2024-06-30 15:27:55', 'admin', '2024-06-30 15:27:55', false);

insert into okay.sys_dict_type (id, name, type, status, standard, remark, creator, create_time, updater, update_time, deleted)
values  (1, '通用状态', 'common_status', false, true, '通用状态', '', '2024-03-25 08:59:56', '', '2024-06-30 14:53:13', false),
        (2, '菜单类型', 'system_menu_type', false, true, '菜单类型', '', '2024-03-25 08:59:56', '', '2024-06-30 14:53:13', false),
        (3, '通用性别', 'common_sex', false, true, '通用性别', '', '2024-03-25 08:59:56', '', '2024-06-30 14:53:13', false),
        (4, '登录类型', 'system_login_type', false, true, null, '', '2024-03-25 08:59:56', '', '2024-06-30 14:53:13', false),
        (5, '登录结果', 'system_login_result', false, true, null, '', '2024-03-25 08:59:56', '', '2024-06-30 14:53:13', false),
        (6, '操作类型', 'system_operate_type', false, true, null, '', '2024-03-25 08:59:56', '', '2024-06-30 14:53:13', false),
        (7, '系统标准', 'common_standard', false, true, null, 'admin', '2024-06-30 14:57:16', 'admin', '2024-06-30 15:04:44', false);

insert into okay.sys_dict_data (id, sort, label, value, dict_type, color, status, remark, creator, create_time, updater, update_time, deleted)
values  (1, 99, '开启', 'false', 'common_status', 'green', false, '通用状态-开启', '', '2024-03-25 08:59:56', '', '2024-03-25 08:59:56', false),
        (2, 99, '关闭', 'true', 'common_status', 'red', false, '通用状态-关闭', '', '2024-03-25 08:59:56', '', '2024-03-25 08:59:56', false),
        (3, 99, '目录', '0', 'system_menu_type', 'blue', false, null, '', '2024-03-25 08:59:56', '', '2024-03-25 08:59:56', false),
        (4, 99, '菜单', '1', 'system_menu_type', 'green', false, null, '', '2024-03-25 08:59:56', '', '2024-03-25 08:59:56', false),
        (5, 99, '按钮', '2', 'system_menu_type', 'red', false, null, '', '2024-03-25 08:59:56', '', '2024-03-25 08:59:56', false),
        (6, 99, '男', '1', 'common_sex', 'blue', false, null, '', '2024-03-25 08:59:56', '', '2024-03-25 08:59:56', false),
        (7, 99, '女', '2', 'common_sex', 'red', false, null, '', '2024-03-25 08:59:56', '', '2024-03-25 08:59:56', false),
        (8, 99, '未知', '0', 'common_sex', 'default', false, null, '', '2024-03-25 08:59:56', '', '2024-03-25 08:59:56', false),
        (9, 99, '账号密码登录', '100', 'system_login_type', 'pink', false, null, '', '2024-03-25 08:59:56', '', '2024-03-25 08:59:56', false),
        (10, 99, '手机号密码登录', '101', 'system_login_type', 'red', false, null, '', '2024-03-25 08:59:56', '', '2024-03-25 08:59:56', false),
        (11, 99, '邮箱密码登录', '102', 'system_login_type', 'orange', false, null, '', '2024-03-25 08:59:56', '', '2024-03-25 08:59:56', false),
        (12, 99, '手机号验证码登录', '103', 'system_login_type', 'green', false, null, '', '2024-03-25 08:59:56', '', '2024-03-25 08:59:56', false),
        (13, 99, '邮箱验证码登录', '104', 'system_login_type', 'cyan', false, null, '', '2024-03-25 08:59:56', '', '2024-03-25 08:59:56', false),
        (14, 99, '自己退出', '120', 'system_login_type', 'purple', false, null, '', '2024-03-25 08:59:56', '', '2024-03-25 08:59:56', false),
        (15, 99, '登录成功', '0', 'system_login_result', 'green', false, null, '', '2024-03-25 08:59:56', '', '2024-03-25 08:59:56', false),
        (16, 99, '账号或密码不正确', '10', 'system_login_result', 'pink', false, null, '', '2024-03-25 08:59:56', '', '2024-03-25 08:59:56', false),
        (17, 99, '手机验证码不正确', '11', 'system_login_result', 'red', false, null, '', '2024-03-25 08:59:56', '', '2024-03-25 08:59:56', false),
        (18, 99, '邮箱验证码不正确', '12', 'system_login_result', 'orange', false, null, '', '2024-03-25 08:59:56', '', '2024-03-25 08:59:56', false),
        (19, 99, '未知异常', '100', 'system_login_result', 'default', false, null, '', '2024-03-25 08:59:56', '', '2024-03-25 08:59:56', false),
        (20, 99, '用户被禁用', '20', 'system_login_result', 'blue', false, null, '', '2024-03-25 08:59:56', '', '2024-03-25 08:59:56', false),
        (21, 99, '新增', '2', 'system_operate_type', 'cyan', false, null, '', '2024-03-25 08:59:56', '', '2024-03-25 08:59:56', false),
        (22, 99, '修改', '3', 'system_operate_type', 'blue', false, null, '', '2024-03-25 08:59:56', '', '2024-03-25 08:59:56', false),
        (23, 99, '查询', '1', 'system_operate_type', 'green', false, null, '', '2024-03-25 08:59:56', '', '2024-03-25 08:59:56', false),
        (24, 99, '删除', '4', 'system_operate_type', 'red', false, null, '', '2024-03-25 08:59:56', '', '2024-03-25 08:59:56', false),
        (25, 99, '导出', '5', 'system_operate_type', 'pink', false, null, '', '2024-03-25 08:59:56', '', '2024-03-25 08:59:56', false),
        (26, 99, '导入', '6', 'system_operate_type', 'orange', false, null, '', '2024-03-25 08:59:56', '', '2024-03-25 08:59:56', false),
        (27, 99, '其他', '0', 'system_operate_type', 'default', false, null, '', '2024-03-25 08:59:56', '', '2024-03-25 08:59:56', false),
        (28, 99, '是', 'true', 'common_standard', 'red', false, null, 'admin', '2024-06-30 14:59:53', 'admin', '2024-06-30 14:59:53', false),
        (29, 99, '否', 'false', 'common_standard', 'green', false, null, 'admin', '2024-06-30 15:00:11', 'admin', '2024-06-30 15:00:11', false);