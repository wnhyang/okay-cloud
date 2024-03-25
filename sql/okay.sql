create table if not exists sys_dict_data
(
    id          bigint auto_increment comment '字典数据id' primary key,
    sort        int         default 99                not null comment '字典排序',
    label       varchar(30) default ''                not null comment '字典标签',
    value       varchar(30) default ''                not null comment '字典键值',
    dict_type   varchar(30)                           not null comment '字典类型',
    color       varchar(20) default 'default'         not null comment '颜色',
    status      bit         default b'0'              not null comment '状态（0正常 1停用）',
    remark      varchar(100)                          null comment '备注',
    creator     varchar(30) default ''                null comment '创建者',
    create_time datetime    default CURRENT_TIMESTAMP not null comment '创建时间',
    updater     varchar(30) default ''                null comment '更新者',
    update_time datetime    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    deleted     bit         default b'0'              not null comment '是否删除'
) comment '字典数据表' charset = utf8mb4;

create table if not exists sys_dict_type
(
    id          bigint auto_increment comment '字典类型id' primary key,
    name        varchar(30) default ''                not null comment '字典名称',
    type        varchar(30) default ''                not null comment '字典类型',
    status      bit         default b'0'              not null comment '状态（0正常 1停用）',
    standard    bit         default b'0'              null comment '是否内置',
    remark      varchar(100)                          null comment '备注',
    creator     varchar(30) default ''                null comment '创建者',
    create_time datetime    default CURRENT_TIMESTAMP not null comment '创建时间',
    updater     varchar(30) default ''                null comment '更新者',
    update_time datetime    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    deleted     bit         default b'0'              not null comment '是否删除',
    constraint dict_type unique (type)
) comment '字典类型表' charset = utf8mb4;

create table if not exists sys_login_log
(
    id          bigint auto_increment comment '登录日志id' primary key,
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
) comment '系统访问记录' charset = utf8mb4;

create table if not exists sys_menu
(
    id                  bigint auto_increment comment '菜单id' primary key,
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
) comment '菜单权限表' charset = utf8mb4;

create table if not exists sys_operate_log
(
    id               bigint auto_increment comment '操作日志id' primary key,
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
) comment '操作日志记录' charset = utf8mb4;

create table if not exists sys_role
(
    id          bigint auto_increment comment '角色id' primary key,
    name        varchar(30)                           not null comment '角色名称',
    value       varchar(30)                           not null comment '角色权限字符串',
    sort        int         default 99                not null comment '显示顺序',
    status      bit         default b'0'              not null comment '角色状态（0正常 1停用）',
    remark      varchar(200)                          null comment '备注',
    creator     varchar(30) default ''                null comment '创建者',
    create_time datetime    default CURRENT_TIMESTAMP not null comment '创建时间',
    updater     varchar(30) default ''                null comment '更新者',
    update_time datetime    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    deleted     bit         default b'0'              not null comment '是否删除'
) comment '角色信息表' charset = utf8mb4;

create table if not exists sys_role_menu
(
    id          bigint auto_increment comment '角色菜单id' primary key,
    role_id     bigint                                not null comment '角色ID',
    menu_id     bigint                                not null comment '菜单ID',
    creator     varchar(30) default ''                null comment '创建者',
    create_time datetime    default CURRENT_TIMESTAMP not null comment '创建时间',
    updater     varchar(30) default ''                null comment '更新者',
    update_time datetime    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    deleted     bit         default b'0'              not null comment '是否删除'
) comment '角色和菜单关联表' charset = utf8mb4;

create table if not exists sys_rsa
(
    id          bigint                                not null comment '密钥id' primary key,
    private_key varchar(255)                          null comment '私钥',
    public_key  varchar(255)                          null comment '公钥',
    remark      varchar(200)                          null comment '备注',
    creator     varchar(30) default ''                null comment '创建者',
    create_time datetime    default CURRENT_TIMESTAMP not null comment '创建时间',
    updater     varchar(30) default ''                null comment '更新者',
    update_time datetime    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    deleted     bit         default b'0'              not null comment '是否删除'
) comment 'Rsa密钥表' charset = utf8mb4;

create table if not exists sys_user
(
    id          bigint auto_increment comment '用户id' primary key,
    username    varchar(30)                            not null comment '用户账号',
    password    varchar(100) default ''                not null comment '密码',
    nickname    varchar(30)                            not null comment '用户昵称',
    type        tinyint      default 0                 not null comment '用户类型',
    remark      varchar(200)                           null comment '描述',
    email       varchar(50)  default ''                null comment '用户邮箱',
    mobile      varchar(11)  default ''                null comment '手机号码',
    sex         tinyint      default 0                 null comment '用户性别',
    avatar      varchar(200) default ''                null comment '头像地址',
    status      bit          default b'0'              not null comment '帐号状态（0正常 1停用）',
    login_ip    varchar(50)  default ''                null comment '最后登录IP',
    login_date  datetime                               null comment '最后登录时间',
    creator     varchar(30)  default ''                null comment '创建者',
    create_time datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    updater     varchar(30)  default ''                null comment '更新者',
    update_time datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    deleted     bit          default b'0'              not null comment '是否删除',
    constraint uk_email unique (email),
    constraint uk_mobile unique (mobile),
    constraint uk_username unique (username, update_time)
) comment '用户信息表' charset = utf8mb4;

create table if not exists sys_user_role
(
    id          bigint auto_increment comment '自增编号' primary key,
    user_id     bigint                                not null comment '用户ID',
    role_id     bigint                                not null comment '角色ID',
    creator     varchar(30) default ''                null comment '创建者',
    create_time datetime    default CURRENT_TIMESTAMP null comment '创建时间',
    updater     varchar(30) default ''                null comment '更新者',
    update_time datetime    default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    deleted     bit         default b'0'              null comment '是否删除'
) comment '用户和角色关联表' charset = utf8mb4;


INSERT INTO okay.sys_dict_data (label, value, dict_type, color, status, remark)
VALUES ('开启', 'false', 'common_status', 'green', 0, '通用状态-开启'),
       ('关闭', 'true', 'common_status', 'red', 0, '通用状态-关闭'),
       ('目录', '0', 'system_menu_type', 'blue', 0, NULL),
       ('菜单', '1', 'system_menu_type', 'green', 0, NULL),
       ('按钮', '2', 'system_menu_type', 'red', 0, NULL),
       ('男', '1', 'common_sex', 'blue', 0, NULL),
       ('女', '2', 'common_sex', 'red', 0, NULL),
       ('未知', '0', 'common_sex', 'default', 0, NULL),
       ('账号密码登录', '100', 'system_login_type', 'pink', 0, NULL),
       ('手机号密码登录', '101', 'system_login_type', 'red', 0, NULL),
       ('邮箱密码登录', '102', 'system_login_type', 'orange', 0, NULL),
       ('手机号验证码登录', '103', 'system_login_type', 'green', 0, NULL),
       ('邮箱验证码登录', '104', 'system_login_type', 'cyan', 0, NULL),
       ('自己退出', '120', 'system_login_type', 'purple', 0, NULL),
       ('登录成功', '0', 'system_login_result', 'green', 0, NULL),
       ('账号或密码不正确', '10', 'system_login_result', 'pink', 0, NULL),
       ('手机验证码不正确', '11', 'system_login_result', 'red', 0, NULL),
       ('邮箱验证码不正确', '12', 'system_login_result', 'orange', 0, NULL),
       ('未知异常', '100', 'system_login_result', 'default', 0, NULL),
       ('用户被禁用', '20', 'system_login_result', 'blue', 0, NULL),
       ('新增', '2', 'system_operate_type', 'cyan', 0, NULL),
       ('修改', '3', 'system_operate_type', 'blue', 0, NULL),
       ('查询', '1', 'system_operate_type', 'green', 0, NULL),
       ('删除', '4', 'system_operate_type', 'red', 0, NULL),
       ('导出', '5', 'system_operate_type', 'pink', 0, NULL),
       ('导入', '6', 'system_operate_type', 'orange', 0, NULL),
       ('其他', '0', 'system_operate_type', 'default', 0, NULL);
INSERT INTO okay.sys_dict_type (name, `type`, status, standard, remark)
VALUES ('通用状态', 'common_status', 0, 0, '通用状态'),
       ('菜单类型', 'system_menu_type', 0, 0, '菜单类型'),
       ('通用性别', 'common_sex', 0, 0, '通用性别'),
       ('登录类型', 'system_login_type', 0, 0, NULL),
       ('登录结果', 'system_login_result', 0, 0, NULL),
       ('操作类型', 'system_operate_type', 0, 0, NULL);
INSERT INTO okay.sys_menu (name, permission, `type`, order_no, parent_id, `path`, icon, component, hide_breadcrumb,
                           current_active_menu, keepalive, title, redirect, status, is_show, is_ext)
VALUES ('System', NULL, 0, 0, 0, '/system', 'ant-design:audit-outlined', NULL, 0, NULL, 0, '系统管理', '/system/user',
        0, 0, 0),
       ('User', 'system:user:list', 1, 0, 1, 'user', 'ant-design:user-outlined', 'system/user/index', 0, NULL, 0,
        '用户管理', NULL, 0, 0, 0),
       ('Role', 'system:role:list', 1, 1, 1, 'role', 'ant-design:crown-outlined', 'system/role/index', 0, NULL, 0,
        '角色管理', NULL, 0, 0, 0),
       ('Menu', 'system:menu:list', 1, 2, 1, 'menu', 'ant-design:menu-fold-outlined', 'system/menu/index', 0, NULL, 0,
        '菜单管理', NULL, 0, 0, 0),
       ('Password', NULL, 1, 97, 1, 'password', 'ant-design:unlock-outlined', 'system/password/index', 0, NULL, 0,
        '修改密码', NULL, 0, 0, 0),
       ('Log', NULL, 0, 99, 1, 'log', 'ant-design:ordered-list-outlined', NULL, 0, NULL, 0, '日志管理',
        '/system/log/loginlog', 0, 0, 0),
       ('Dict', NULL, 0, 4, 1, 'dict', 'ant-design:medicine-box-outlined', NULL, 0, NULL, 0, '字典管理',
        '/system/dict/dicttype', 0, 0, 0),
       ('Loginlog', NULL, 1, 0, 104, 'loginlog', 'ant-design:login-outlined', 'system/loginLog/index', 0, NULL, 0,
        '登录日志', NULL, 0, 0, 0),
       ('Operatelog', NULL, 1, 0, 104, 'operatelog', 'ant-design:logout-outlined', 'system/operateLog/index', 0, NULL,
        0, '操作日志', NULL, 0, 0, 0),
       (NULL, 'system:user:create', 2, 0, 100, '', '', '', 0, NULL, 0, '用户新增', NULL, 0, 0, 0),
       (NULL, 'system:user:update', 2, 0, 100, '', '', '', 0, NULL, 0, '用户更新', NULL, 0, 0, 0),
       (NULL, 'system:user:delete', 2, 0, 100, '', '', '', 0, NULL, 0, '用户删除', NULL, 0, 0, 0),
       (NULL, 'system:user:query', 2, 0, 100, '', '', '', 0, NULL, 0, '用户查询', NULL, 0, 0, 0),
       (NULL, 'system:menu:create', 2, 0, 102, '', '', '', 0, NULL, 0, '菜单新增', NULL, 0, 0, 0),
       (NULL, 'system:menu:update', 2, 0, 102, '', '', '', 0, NULL, 0, '菜单更新', NULL, 0, 0, 0),
       (NULL, 'system:menu:delete', 2, 0, 102, '', '', '', 0, NULL, 0, '菜单删除', NULL, 0, 0, 0),
       (NULL, 'system:menu:query', 2, 0, 102, '', '', '', 0, NULL, 0, '菜单查询', NULL, 0, 0, 0),
       (NULL, 'system:role:create', 2, 0, 101, '', '', '', 0, NULL, 0, '角色新增', NULL, 0, 0, 0),
       (NULL, 'system:role:update', 2, 0, 101, '', '', '', 0, NULL, 0, '角色更新', NULL, 0, 0, 0),
       (NULL, 'system:role:delete', 2, 0, 101, '', '', '', 0, NULL, 0, '角色删除', NULL, 0, 0, 0),
       (NULL, 'system:role:query', 2, 0, 101, '', '', '', 0, NULL, 0, '角色查询', NULL, 0, 0, 0),
       ('DictType', NULL, 1, 0, 105, 'dicttype', 'ant-design:book-outlined', 'system/dictType/index', 0, NULL, 0,
        '字典类型', NULL, 0, 0, 0),
       ('DictData', NULL, 1, 0, 105, 'dictdata', 'ant-design:behance-outlined', 'system/dictData/index', 0, NULL, 0,
        '字典数据', NULL, 0, 0, 0),
       ('UserDetail', NULL, 1, 0, 1, 'userDetail/:id', 'ant-design:carry-out-twotone', 'system/user/UserDetail', 0,
        '/system/user', 0, '用户详情', NULL, 0, 1, 0);

INSERT INTO okay.sys_user (id, username, password, nickname, `type`, remark, email, mobile, sex, avatar, status)
VALUES (1, 'admin', '$2a$10$FjEJ6Kg6NTPEU8yBjQQcR.IN4q/UNDseTs4GqwyO8gi9I2bJQ.ZZi', 'admin', 0, NULL, 'wnhyang@qq.com',
        '17317430552', 0, '', 0);
INSERT INTO okay.sys_role (id, name, value, sort, status, remark)
VALUES (1, '超级管理员', 'administrator', 0, 0, NULL);
INSERT INTO okay.sys_user_role (user_id, role_id)
VALUES (1, 1);

