package cn.wnhyang.okay.system.service;

import cn.wnhyang.okay.system.entity.ResourceDO;

/**
 * 资源权限表
 *
 * @author wnhyang
 * @since 2023/05/14
 */
public interface ResourceService {

    /**
     * 获取一个对象
     *
     * @return #ResourceDO
     */
    ResourceDO getOne();

}
