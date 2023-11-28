
-- okay.sys_dict_data definition

CREATE TABLE `sys_dict_data` (
                                 `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典数据主键',
                                 `sort` int(11) NOT NULL DEFAULT '0' COMMENT '字典排序',
                                 `label` varchar(100) NOT NULL DEFAULT '' COMMENT '字典标签',
                                 `value` varchar(100) NOT NULL DEFAULT '' COMMENT '字典键值',
                                 `dict_type` varchar(100) NOT NULL DEFAULT '' COMMENT '字典类型',
                                 `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
                                 `remark` varchar(500) DEFAULT NULL COMMENT '备注',
                                 `creator` varchar(64) DEFAULT '' COMMENT '创建者',
                                 `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                 `updater` varchar(64) DEFAULT '' COMMENT '更新者',
                                 `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                 `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
                                 PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1238 DEFAULT CHARSET=utf8mb4 COMMENT='字典数据表';


-- okay.sys_dict_type definition

CREATE TABLE `sys_dict_type` (
                                 `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典类型主键',
                                 `name` varchar(100) NOT NULL DEFAULT '' COMMENT '字典名称',
                                 `type` varchar(100) NOT NULL DEFAULT '' COMMENT '字典类型',
                                 `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
                                 `remark` varchar(500) DEFAULT NULL COMMENT '备注',
                                 `creator` varchar(64) DEFAULT '' COMMENT '创建者',
                                 `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                 `updater` varchar(64) DEFAULT '' COMMENT '更新者',
                                 `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                 `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
                                 PRIMARY KEY (`id`) USING BTREE,
                                 UNIQUE KEY `dict_type` (`type`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=171 DEFAULT CHARSET=utf8mb4 COMMENT='字典类型表';


-- okay.sys_login_log definition

CREATE TABLE `sys_login_log` (
                                 `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '访问ID',
                                 `login_type` bigint(20) NOT NULL COMMENT '登录类型',
                                 `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户编号',
                                 `user_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '用户类型',
                                 `account` varchar(50) NOT NULL DEFAULT '' COMMENT '用户账号',
                                 `result` tinyint(4) NOT NULL COMMENT '登录结果',
                                 `user_ip` varchar(50) NOT NULL COMMENT '用户 IP',
                                 `user_agent` varchar(512) NOT NULL COMMENT '浏览器 UA',
                                 `creator` varchar(64) DEFAULT '' COMMENT '创建者',
                                 `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                 `updater` varchar(64) DEFAULT '' COMMENT '更新者',
                                 `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                 `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
                                 PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=271 DEFAULT CHARSET=utf8mb4 COMMENT='系统访问记录';


-- okay.sys_menu definition

CREATE TABLE `sys_menu` (
                            `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
                            `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
                            `permission` varchar(100) DEFAULT NULL COMMENT '权限标识',
                            `type` tinyint(4) NOT NULL COMMENT '菜单类型',
                            `order_no` int(11) NOT NULL DEFAULT '0' COMMENT '显示顺序',
                            `parent_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '父菜单ID',
                            `path` varchar(200) DEFAULT '' COMMENT '路由地址',
                            `icon` varchar(100) DEFAULT '#' COMMENT '菜单图标',
                            `component` varchar(255) DEFAULT NULL COMMENT '组件路径',
                            `hide_breadcrumb` bit(1) DEFAULT b'0' COMMENT '隐藏面包屑',
                            `current_active_menu` varchar(50) DEFAULT NULL COMMENT '当前激活菜单',
                            `keepalive` bit(1) DEFAULT b'0' COMMENT '缓存',
                            `title` varchar(255) DEFAULT NULL COMMENT '组件名',
                            `redirect` varchar(50) DEFAULT NULL COMMENT '可点击',
                            `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '菜单状态',
                            `is_show` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否可见',
                            `creator` varchar(64) DEFAULT '' COMMENT '创建者',
                            `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                            `updater` varchar(64) DEFAULT '' COMMENT '更新者',
                            `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                            `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
                            `is_ext` bit(1) DEFAULT b'0' COMMENT '是否外链',
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2212 DEFAULT CHARSET=utf8mb4 COMMENT='菜单权限表';


-- okay.sys_operate_log definition

CREATE TABLE `sys_operate_log` (
                                   `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志主键',
                                   `user_id` bigint(20) NOT NULL COMMENT '用户编号',
                                   `module` varchar(50) NOT NULL COMMENT '模块标题',
                                   `name` varchar(50) NOT NULL COMMENT '操作名',
                                   `type` int(11) NOT NULL DEFAULT '0' COMMENT '操作分类',
                                   `content` varchar(2000) NOT NULL DEFAULT '' COMMENT '操作内容',
                                   `exts` varchar(512) NOT NULL DEFAULT '' COMMENT '拓展字段',
                                   `request_method` varchar(16) DEFAULT '' COMMENT '请求方法名',
                                   `request_url` varchar(255) DEFAULT '' COMMENT '请求地址',
                                   `user_ip` varchar(50) DEFAULT NULL COMMENT '用户 IP',
                                   `user_agent` varchar(200) DEFAULT NULL COMMENT '浏览器 UA',
                                   `java_method` varchar(512) NOT NULL DEFAULT '' COMMENT 'Java 方法名',
                                   `java_method_args` varchar(8000) DEFAULT '' COMMENT 'Java 方法的参数',
                                   `start_time` datetime NOT NULL COMMENT '操作时间',
                                   `duration` int(11) NOT NULL COMMENT '执行时长',
                                   `result_code` int(11) NOT NULL DEFAULT '0' COMMENT '结果码',
                                   `result_msg` varchar(512) DEFAULT '' COMMENT '结果提示',
                                   `result_data` varchar(4000) DEFAULT '' COMMENT '结果数据',
                                   `creator` varchar(64) DEFAULT '' COMMENT '创建者',
                                   `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                   `updater` varchar(64) DEFAULT '' COMMENT '更新者',
                                   `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                   `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
                                   PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8647 DEFAULT CHARSET=utf8mb4 COMMENT='操作日志记录';


-- okay.sys_role definition

CREATE TABLE `sys_role` (
                            `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
                            `name` varchar(30) NOT NULL COMMENT '角色名称',
                            `value` varchar(100) NOT NULL COMMENT '角色权限字符串',
                            `sort` int(11) NOT NULL COMMENT '显示顺序',
                            `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '角色状态（0正常 1停用）',
                            `remark` varchar(500) DEFAULT NULL COMMENT '备注',
                            `creator` varchar(64) DEFAULT '' COMMENT '创建者',
                            `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                            `updater` varchar(64) DEFAULT '' COMMENT '更新者',
                            `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                            `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=140 DEFAULT CHARSET=utf8mb4 COMMENT='角色信息表';


-- okay.sys_role_menu definition

CREATE TABLE `sys_role_menu` (
                                 `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增编号',
                                 `role_id` bigint(20) NOT NULL COMMENT '角色ID',
                                 `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
                                 `creator` varchar(64) DEFAULT '' COMMENT '创建者',
                                 `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                 `updater` varchar(64) DEFAULT '' COMMENT '更新者',
                                 `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                 `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
                                 PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2900 DEFAULT CHARSET=utf8mb4 COMMENT='角色和菜单关联表';


-- okay.sys_rsa definition

CREATE TABLE `sys_rsa` (
                           `id` bigint(20) NOT NULL COMMENT '密钥主键',
                           `private_key` varchar(255) DEFAULT NULL COMMENT '私钥',
                           `public_key` varchar(255) DEFAULT NULL COMMENT '公钥',
                           `remark` varchar(500) DEFAULT NULL COMMENT '备注',
                           `creator` varchar(64) DEFAULT '' COMMENT '创建者',
                           `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                           `updater` varchar(64) DEFAULT '' COMMENT '更新者',
                           `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                           `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Rsa密钥表';


-- okay.sys_short_link definition

CREATE TABLE `sys_short_link` (
                                  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '短链接主键',
                                  `link` varchar(255) NOT NULL COMMENT '长链接',
                                  `code` varchar(8) NOT NULL COMMENT '短链接',
                                  `type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '链接类型',
                                  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '说明',
                                  `expire_time` datetime NOT NULL COMMENT '过期时间',
                                  `email` varchar(50) DEFAULT NULL COMMENT '通知邮件',
                                  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
                                  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
                                  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
                                  PRIMARY KEY (`id`),
                                  UNIQUE KEY `uk_code` (`code`) USING BTREE COMMENT '短链接唯一索引',
                                  UNIQUE KEY `uk_link` (`link`) USING BTREE COMMENT '长链接唯一索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='短链接表';


-- okay.sys_user definition

CREATE TABLE `sys_user` (
                            `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
                            `username` varchar(30) NOT NULL COMMENT '用户账号',
                            `password` varchar(100) NOT NULL DEFAULT '' COMMENT '密码',
                            `nickname` varchar(30) NOT NULL COMMENT '用户昵称',
                            `type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '用户类型',
                            `remark` varchar(500) DEFAULT NULL COMMENT '描述',
                            `email` varchar(50) DEFAULT '' COMMENT '用户邮箱',
                            `mobile` varchar(11) DEFAULT '' COMMENT '手机号码',
                            `sex` tinyint(4) DEFAULT '0' COMMENT '用户性别',
                            `avatar` varchar(512) DEFAULT '' COMMENT '头像地址',
                            `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
                            `login_ip` varchar(50) DEFAULT '' COMMENT '最后登录IP',
                            `login_date` datetime DEFAULT NULL COMMENT '最后登录时间',
                            `creator` varchar(64) DEFAULT '' COMMENT '创建者',
                            `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                            `updater` varchar(64) DEFAULT '' COMMENT '更新者',
                            `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                            `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
                            PRIMARY KEY (`id`) USING BTREE,
                            UNIQUE KEY `uk_username` (`username`,`update_time`) USING BTREE,
                            UNIQUE KEY `uk_mobile` (`mobile`) USING BTREE,
                            UNIQUE KEY `uk_email` (`email`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=130 DEFAULT CHARSET=utf8mb4 COMMENT='用户信息表';


-- okay.sys_user_role definition

CREATE TABLE `sys_user_role` (
                                 `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增编号',
                                 `user_id` bigint(20) NOT NULL COMMENT '用户ID',
                                 `role_id` bigint(20) NOT NULL COMMENT '角色ID',
                                 `creator` varchar(64) DEFAULT '' COMMENT '创建者',
                                 `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                 `updater` varchar(64) DEFAULT '' COMMENT '更新者',
                                 `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                 `deleted` bit(1) DEFAULT b'0' COMMENT '是否删除',
                                 PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COMMENT='用户和角色关联表';


-- okay.system_menu definition

CREATE TABLE `system_menu` (
                               `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
                               `name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '菜单名称',
                               `permission` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '权限标识',
                               `type` tinyint(4) NOT NULL COMMENT '菜单类型',
                               `sort` int(11) NOT NULL DEFAULT '0' COMMENT '显示顺序',
                               `parent_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '父菜单ID',
                               `path` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '路由地址',
                               `icon` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT '#' COMMENT '菜单图标',
                               `component` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '组件路径',
                               `component_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '组件名',
                               `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '菜单状态',
                               `visible` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否可见',
                               `keep_alive` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否缓存',
                               `always_show` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否总是显示',
                               `creator` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '创建者',
                               `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                               `updater` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '更新者',
                               `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                               `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2162 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='菜单权限表';