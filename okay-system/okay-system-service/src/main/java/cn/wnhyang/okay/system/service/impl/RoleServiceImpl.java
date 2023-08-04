package cn.wnhyang.okay.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.wnhyang.okay.system.entity.RoleDO;
import cn.wnhyang.okay.system.mapper.RoleMapper;
import cn.wnhyang.okay.system.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

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

    @Override
    public List<RoleDO> getRoleList(Collection<Long> ids) {
        if (CollectionUtil.isEmpty(ids)) {
            return Collections.emptyList();
        }
        return roleMapper.selectBatchIds(ids);
    }
}
