package cn.wnhyang.okay.system.service.impl;

import cn.wnhyang.okay.system.mapper.RoleMapper;
import cn.wnhyang.okay.system.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 角色信息表
 *
 * @author wnhyang
 * @since 2023/05/14
 */
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleMapper roleMapper;
}
