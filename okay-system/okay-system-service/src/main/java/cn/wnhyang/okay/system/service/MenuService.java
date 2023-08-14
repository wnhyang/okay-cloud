package cn.wnhyang.okay.system.service;

import cn.wnhyang.okay.system.entity.MenuDO;
import cn.wnhyang.okay.system.vo.menu.MenuCreateReqVO;
import cn.wnhyang.okay.system.vo.menu.MenuListReqVO;
import cn.wnhyang.okay.system.vo.menu.MenuUpdateReqVO;

import java.util.List;

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

    /**
     * 更新菜单
     *
     * @param reqVO 菜单信息
     */
    void updateMenu(MenuUpdateReqVO reqVO);

    /**
     * 删除菜单
     *
     * @param id 菜单id
     */
    void deleteMenu(Long id);

    /**
     * 查询菜单列表
     *
     * @param reqVO 菜单
     * @return 菜单列表
     */
    List<MenuDO> getMenuList(MenuListReqVO reqVO);
}
