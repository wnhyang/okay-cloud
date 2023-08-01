package cn.wnhyang.okay.system.service.impl;

import cn.wnhyang.okay.system.entity.ResourceDO;
import cn.wnhyang.okay.system.mapper.ResourceMapper;
import cn.wnhyang.okay.system.service.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 资源权限表
 *
 * @author wnhyang
 * @since 2023/05/14
 */
@Service
@RequiredArgsConstructor
public class ResourceServiceImpl implements ResourceService {

    private final ResourceMapper resourceMapper;

    /**
     * 获取一个对象
     *
     * @return #ResourceDO
     */
    @Override
    public ResourceDO getOne() {

        return null;
    }
}
