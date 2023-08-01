package cn.wnhyang.okay.system.service.impl;

import cn.wnhyang.okay.system.mapper.UserRoleMapper;
import cn.wnhyang.okay.system.service.UserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 用户和角色关联表
 *
 * @author wnhyang
 * @since 2023/05/14
 */
@Service
@RequiredArgsConstructor
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleMapper userRoleMapper;
}
