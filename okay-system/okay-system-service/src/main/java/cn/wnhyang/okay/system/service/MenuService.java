package cn.wnhyang.okay.system.service;

import cn.wnhyang.okay.system.vo.menu.MenuCreateReqVO;

/**
 * 菜单权限表
 *
 * @author wnhyang
 * @since 2023/05/14
 */
public interface MenuService {

    /**
     * 创建菜单
     *
     * @param reqVO 菜单信息
     * @return 菜单id
     */
    Long createMenu(MenuCreateReqVO reqVO);
}
