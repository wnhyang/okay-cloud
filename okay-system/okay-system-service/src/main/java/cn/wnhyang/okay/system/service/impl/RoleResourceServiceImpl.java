package cn.wnhyang.okay.system.service.impl;

import cn.wnhyang.okay.system.mapper.RoleResourceMapper;
import cn.wnhyang.okay.system.service.RoleResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 角色和资源关联表
 *
 * @author wnhyang
 * @since 2023/05/14
 */
@Service
@RequiredArgsConstructor
public class RoleResourceServiceImpl implements RoleResourceService {

    private final RoleResourceMapper roleResourceMapper;
}
