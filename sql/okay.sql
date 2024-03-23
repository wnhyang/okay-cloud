create table if not exists sys_dict_data
(
    id          bigint auto_increment comment '字典数据主键' primary key,
    sort        int          default 99                not null comment '字典排序',
    label       varchar(100) default ''                not null comment '字典标签',
    value       varchar(100) default ''                not null comment '字典键值',
    dict_type   varchar(20)                            not null comment '字典类型',
    color       varchar(20)  default 'default'         not null comment '颜色',
    status      bit          default b'0'              not null comment '状态（0正常 1停用）',
    remark      varchar(500)                           null comment '备注',
    creator     varchar(64)  default ''                null comment '创建者',
    create_time datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    updater     varchar(64)  default ''                null comment '更新者',
    update_time datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    deleted     bit          default b'0'              not null comment '是否删除'
) comment '字典数据表' charset = utf8mb4;

create table if not exists sys_dict_type
(
    id          bigint auto_increment comment '字典类型主键' primary key,
    name        varchar(100) default ''                not null comment '字典名称',
    type        varchar(100) default ''                not null comment '字典类型',
    status      bit          default b'0'              not null comment '状态（0正常 1停用）',
    standard    bit          default b'0'              null comment '是否内置',
    remark      varchar(500)                           null comment '备注',
    creator     varchar(64)  default ''                null comment '创建者',
    create_time datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    updater     varchar(64)  default ''                null comment '更新者',
    update_time datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    deleted     bit          default b'0'              not null comment '是否删除',
    constraint dict_type
        unique (type)
) comment '字典类型表' charset = utf8mb4;

create table if not exists sys_login_log
(
    id          bigint auto_increment comment '访问ID' primary key,
    login_type  bigint                                not null comment '登录类型',
    user_id     bigint      default 0                 not null comment '用户编号',
    user_type   tinyint     default 0                 not null comment '用户类型',
    account     varchar(50) default ''                not null comment '用户账号',
    result      tinyint                               not null comment '登录结果',
    user_ip     varchar(50)                           not null comment '用户 IP',
    user_agent  varchar(512)                          not null comment '浏览器 UA',
    creator     varchar(64) default ''                null comment '创建者',
    create_time datetime    default CURRENT_TIMESTAMP not null comment '创建时间',
    updater     varchar(64) default ''                null comment '更新者',
    update_time datetime    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    deleted     bit         default b'0'              not null comment '是否删除'
) comment '系统访问记录' charset = utf8mb4;

create table if not exists sys_menu
(
    id                  bigint auto_increment comment '菜单ID' primary key,
    name                varchar(50)                            null comment '菜单名称',
    permission          varchar(100)                           null comment '权限标识',
    type                tinyint                                not null comment '菜单类型',
    order_no            int          default 0                 not null comment '显示顺序',
    parent_id           bigint       default 0                 not null comment '父菜单ID',
    path                varchar(200) default ''                null comment '路由地址',
    icon                varchar(100) default '#'               null comment '菜单图标',
    component           varchar(255)                           null comment '组件路径',
    hide_breadcrumb     bit          default b'0'              null comment '隐藏面包屑',
    current_active_menu varchar(50)                            null comment '当前激活菜单',
    keepalive           bit          default b'0'              null comment '缓存',
    title               varchar(255)                           null comment '组件名',
    redirect            varchar(50)                            null comment '可点击',
    status              bit          default b'0'              not null comment '菜单状态',
    is_show             bit          default b'0'              not null comment '是否可见',
    creator             varchar(64)  default ''                null comment '创建者',
    create_time         datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    updater             varchar(64)  default ''                null comment '更新者',
    update_time         datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    deleted             bit          default b'0'              not null comment '是否删除',
    is_ext              bit          default b'0'              null comment '是否外链'
) comment '菜单权限表' charset = utf8mb4;

create table if not exists sys_operate_log
(
    id               bigint auto_increment comment '日志主键' primary key,
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
    creator          varchar(64)   default ''                null comment '创建者',
    create_time      datetime      default CURRENT_TIMESTAMP not null comment '创建时间',
    updater          varchar(64)   default ''                null comment '更新者',
    update_time      datetime      default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    deleted          bit           default b'0'              not null comment '是否删除'
) comment '操作日志记录' charset = utf8mb4;

create table if not exists sys_role
(
    id          bigint auto_increment comment '角色ID' primary key,
    name        varchar(30)                           not null comment '角色名称',
    value       varchar(100)                          not null comment '角色权限字符串',
    sort        int         default 99                not null comment '显示顺序',
    status      bit         default b'0'              not null comment '角色状态（0正常 1停用）',
    remark      varchar(500)                          null comment '备注',
    creator     varchar(64) default ''                null comment '创建者',
    create_time datetime    default CURRENT_TIMESTAMP not null comment '创建时间',
    updater     varchar(64) default ''                null comment '更新者',
    update_time datetime    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    deleted     bit         default b'0'              not null comment '是否删除'
) comment '角色信息表' charset = utf8mb4;

create table if not exists sys_role_menu
(
    id          bigint auto_increment comment '自增编号' primary key,
    role_id     bigint                                not null comment '角色ID',
    menu_id     bigint                                not null comment '菜单ID',
    creator     varchar(64) default ''                null comment '创建者',
    create_time datetime    default CURRENT_TIMESTAMP not null comment '创建时间',
    updater     varchar(64) default ''                null comment '更新者',
    update_time datetime    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    deleted     bit         default b'0'              not null comment '是否删除'
) comment '角色和菜单关联表' charset = utf8mb4;

create table if not exists sys_rsa
(
    id          bigint                                not null comment '密钥主键' primary key,
    private_key varchar(255)                          null comment '私钥',
    public_key  varchar(255)                          null comment '公钥',
    remark      varchar(500)                          null comment '备注',
    creator     varchar(64) default ''                null comment '创建者',
    create_time datetime    default CURRENT_TIMESTAMP not null comment '创建时间',
    updater     varchar(64) default ''                null comment '更新者',
    update_time datetime    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    deleted     bit         default b'0'              not null comment '是否删除'
) comment 'Rsa密钥表' charset = utf8mb4;

create table if not exists sys_user
(
    id          bigint auto_increment comment '用户ID' primary key,
    username    varchar(30)                            not null comment '用户账号',
    password    varchar(100) default ''                not null comment '密码',
    nickname    varchar(30)                            not null comment '用户昵称',
    type        tinyint      default 0                 not null comment '用户类型',
    remark      varchar(500)                           null comment '描述',
    email       varchar(50)  default ''                null comment '用户邮箱',
    mobile      varchar(11)  default ''                null comment '手机号码',
    sex         tinyint      default 0                 null comment '用户性别',
    avatar      varchar(512) default ''                null comment '头像地址',
    status      bit          default b'0'              not null comment '帐号状态（0正常 1停用）',
    login_ip    varchar(50)  default ''                null comment '最后登录IP',
    login_date  datetime                               null comment '最后登录时间',
    creator     varchar(64)  default ''                null comment '创建者',
    create_time datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    updater     varchar(64)  default ''                null comment '更新者',
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
    creator     varchar(64) default ''                null comment '创建者',
    create_time datetime    default CURRENT_TIMESTAMP null comment '创建时间',
    updater     varchar(64) default ''                null comment '更新者',
    update_time datetime    default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    deleted     bit         default b'0'              null comment '是否删除'
) comment '用户和角色关联表' charset = utf8mb4;

