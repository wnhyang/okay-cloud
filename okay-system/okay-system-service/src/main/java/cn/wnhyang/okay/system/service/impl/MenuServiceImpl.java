package cn.wnhyang.okay.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.wnhyang.okay.system.convert.MenuConvert;
import cn.wnhyang.okay.system.entity.MenuPO;
import cn.wnhyang.okay.system.enums.permission.MenuType;
import cn.wnhyang.okay.system.mapper.MenuMapper;
import cn.wnhyang.okay.system.mapper.RoleMenuMapper;
import cn.wnhyang.okay.system.service.MenuService;
import cn.wnhyang.okay.system.vo.menu.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import static cn.wnhyang.okay.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.wnhyang.okay.system.entity.MenuPO.ID_ROOT;
import static cn.wnhyang.okay.system.enums.ErrorCodes.*;


/**
 * 菜单
 *
 * @author wnhyang
 * @since 2023/05/14
 */
@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuMapper menuMapper;

    private final RoleMenuMapper roleMenuMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createMenu(MenuCreateVO reqVO) {
        // 校验父菜单存在
        validateParentMenu(reqVO.getParentId(), null);
        // 校验菜单（自己）
        validateMenu(reqVO.getParentId(), reqVO.getName(), null);

        // 插入数据库
        MenuPO menu = MenuConvert.INSTANCE.convert(reqVO);
        initMenuProperty(menu);
        menuMapper.insert(menu);
        // 返回
        return menu.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateMenu(MenuUpdateVO reqVO) {
        // 校验更新的菜单是否存在
        if (menuMapper.selectById(reqVO.getId()) == null) {
            throw exception(MENU_NOT_EXISTS);
        }
        // 校验父菜单存在
        validateParentMenu(reqVO.getParentId(), reqVO.getId());
        // 校验菜单（自己）
        validateMenu(reqVO.getParentId(), reqVO.getName(), reqVO.getId());

        // 更新到数据库
        MenuPO updateObject = MenuConvert.INSTANCE.convert(reqVO);
        initMenuProperty(updateObject);
        menuMapper.updateById(updateObject);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteMenu(Long id) {
        // 校验是否还有子菜单
        if (menuMapper.selectCountByParentId(id) > 0) {
            throw exception(MENU_EXISTS_CHILDREN);
        }
        // 校验删除的菜单是否存在
        if (menuMapper.selectById(id) == null) {
            throw exception(MENU_NOT_EXISTS);
        }
        if (roleMenuMapper.selectCountByMenuId(id) > 0) {
            throw exception(MENU_HAS_ROLE);
        }
        // 标记删除
        menuMapper.deleteById(id);
    }

    @Override
    public List<MenuPO> getMenuList(MenuListVO reqVO) {
        return menuMapper.selectList(reqVO);
    }

    @Override
    public MenuPO getMenu(Long id) {
        return menuMapper.selectById(id);
    }

    @Override
    public List<MenuPO> getMenuList(Set<Long> ids) {
        if (CollectionUtil.isEmpty(ids)) {
            return Collections.emptyList();
        }
        return menuMapper.selectBatchIds(ids);
    }

    @Override
    public List<MenuPO> getMenuList() {
        return menuMapper.selectList();
    }

    @Override
    public List<MenuTreeRespVO> getMenuTreeList(MenuListVO reqVO) {

        // 1、查询所有菜单
        List<MenuPO> all = menuMapper.selectList();

        // 1、查询满足条件的菜单
        List<MenuPO> menus = menuMapper.selectList(reqVO);

        Set<Long> menuIds = menus.stream().map(MenuPO::getId).collect(Collectors.toSet());

        // 2、查询满足条件的的菜单的所有父菜单、父祖菜单的id set，直到父菜单的parentId等于0
        Set<Long> parentIds = getParentIds(menus, all);

        Set<Long> childrenIds = getChildrenIds(menus, all);

        menuIds.addAll(parentIds);
        menuIds.addAll(childrenIds);

        List<MenuTreeRespVO> allMenus = all.stream().filter(menu -> menuIds.contains(menu.getId()))
                .map(MenuConvert.INSTANCE::convert01).collect(Collectors.toList());

        // 2、形成树形结合
        return allMenus.stream().filter((menu) ->
                menu.getParentId().equals(ID_ROOT)
        ).peek((menu) ->
                menu.setChildren(getChildren01(menu.getId(), allMenus))
        ).sorted(
                Comparator.comparingInt(MenuTreeRespVO::getOrderNo)
        ).collect(Collectors.toList());
    }

    private Set<Long> getChildrenIds(List<MenuPO> menus, List<MenuPO> all) {
        // 获取menus在all中的子菜单id集合
        return menus.stream().map(MenuPO::getId).map(id ->
                all.stream().filter(menu ->
                        menu.getParentId().equals(id)
                ).map(MenuPO::getId).collect(Collectors.toSet())
        ).flatMap(Collection::stream).collect(Collectors.toSet());
    }

    /**
     * 获取所有父菜单的id
     */
    private Set<Long> getParentIds(List<MenuPO> menus, List<MenuPO> all) {

        // 当menus的parentId不为0，取parentId的set集合
        Set<Long> parentIds = menus.stream().map(MenuPO::getParentId).filter(parentId ->
                !parentId.equals(ID_ROOT)
        ).collect(Collectors.toSet());

        if (parentIds.isEmpty()) {
            return Collections.emptySet();
        }

        Set<Long> result = new HashSet<>(parentIds);

        // 从所有菜单all中查询id等于parentIds的set集合中任意的菜单，直到顶级菜单，顶级菜单的parentId等于0
        for (MenuPO menu : all) {
            if (parentIds.contains(menu.getId())) {
                result.addAll(getParentIds(Collections.singletonList(menu), all));
            }
        }
        return result;
    }


    @Override
    public List<MenuSimpleTreeVO> getMenuSimpleTreeList() {
        return getMenuSimpleTreeList(false);
    }

    @Override
    public List<MenuSimpleTreeVO> getMenuSimpleTreeListA() {
        return getMenuSimpleTreeList(true);
    }

    private List<MenuSimpleTreeVO> getMenuSimpleTreeList(boolean withRoot) {
        // 1、查询所有的菜单
        List<MenuPO> menus = menuMapper.selectList();

        List<MenuSimpleTreeVO> allMenus = MenuConvert.INSTANCE.convert02(menus);

        // 2、形成树形结合
        List<MenuSimpleTreeVO> collect = allMenus.stream().filter((menu) ->
                menu.getParentId().equals(ID_ROOT)
        ).peek((menu) ->
                menu.setChildren(getChildren02(menu.getId(), allMenus))
        ).sorted(
                Comparator.comparingInt(MenuSimpleTreeVO::getOrderNo)
        ).collect(Collectors.toList());

        if (withRoot) {
            MenuSimpleTreeVO result = new MenuSimpleTreeVO();
            result.setId(ID_ROOT).setTitle("根目录").setChildren(collect);
            return Collections.singletonList(result);
        }

        // 返回result
        return collect;
    }

    private List<MenuTreeRespVO> getChildren01(Long parentId, List<MenuTreeRespVO> all) {
        return all.stream().filter((menu ->
                menu.getParentId().equals(parentId))
        ).peek((menu ->
                menu.setChildren(getChildren01(menu.getId(), all)))
        ).sorted(
                Comparator.comparingInt(MenuTreeRespVO::getOrderNo)
        ).collect(Collectors.toList());
    }

    private List<MenuSimpleTreeVO> getChildren02(Long parentId, List<MenuSimpleTreeVO> all) {
        return all.stream().filter((menu ->
                menu.getParentId().equals(parentId))
        ).peek((menu ->
                menu.setChildren(getChildren02(menu.getId(), all)))
        ).sorted(
                Comparator.comparingInt(MenuSimpleTreeVO::getOrderNo)
        ).collect(Collectors.toList());
    }


    /**
     * 校验父菜单是否合法
     * <p>
     * 1. 不能设置自己为父菜单
     * 2. 父菜单不存在
     * 3. 父菜单必须是 {@link MenuType#MENU} 菜单类型
     *
     * @param parentId 父菜单编号
     * @param childId  当前菜单编号
     */
    void validateParentMenu(Long parentId, Long childId) {
        if (parentId == null || ID_ROOT.equals(parentId)) {
            return;
        }
        // 不能设置自己为父菜单
        if (parentId.equals(childId)) {
            throw exception(MENU_PARENT_ERROR);
        }
        MenuPO menu = menuMapper.selectById(parentId);
        // 父菜单不存在
        if (menu == null) {
            throw exception(MENU_PARENT_NOT_EXISTS);
        }
        // 父菜单必须是目录或者菜单类型
        if (!MenuType.DIR.getType().equals(menu.getType())
                && !MenuType.MENU.getType().equals(menu.getType())) {
            throw exception(MENU_PARENT_NOT_DIR_OR_MENU);
        }
    }

    /**
     * 校验菜单是否合法
     * <p>
     * 1. 校验相同父菜单编号下，是否存在相同的菜单名
     *
     * @param name     菜单名字
     * @param parentId 父菜单编号
     * @param id       菜单编号
     */
    void validateMenu(Long parentId, String name, Long id) {
        MenuPO menu = menuMapper.selectByParentIdAndName(parentId, name);
        if (menu == null) {
            return;
        }
        // 如果 id 为空，说明不用比较是否为相同 id 的菜单
        if (id == null) {
            throw exception(MENU_NAME_DUPLICATE);
        }
        if (!menu.getId().equals(id)) {
            throw exception(MENU_NAME_DUPLICATE);
        }
    }

    /**
     * 初始化菜单的通用属性。
     * <p>
     * 例如说，只有目录或者菜单类型的菜单，才设置 icon
     *
     * @param menu 菜单
     */
    private void initMenuProperty(MenuPO menu) {
        // 菜单为按钮类型时，无需 component、icon、path 属性，进行置空
        if (MenuType.BUTTON.getType().equals(menu.getType())) {
            menu.setComponent("");
            menu.setIcon("");
            menu.setPath("");
        }
    }
}
