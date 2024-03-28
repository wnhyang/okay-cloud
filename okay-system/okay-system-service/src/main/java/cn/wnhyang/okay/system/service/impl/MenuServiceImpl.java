package cn.wnhyang.okay.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.wnhyang.okay.framework.common.core.Login;
import cn.wnhyang.okay.framework.satoken.core.util.LoginUtil;
import cn.wnhyang.okay.system.convert.MenuConvert;
import cn.wnhyang.okay.system.entity.MenuPO;
import cn.wnhyang.okay.system.entity.RoleMenuPO;
import cn.wnhyang.okay.system.enums.permission.MenuType;
import cn.wnhyang.okay.system.mapper.MenuMapper;
import cn.wnhyang.okay.system.mapper.RoleMenuMapper;
import cn.wnhyang.okay.system.service.MenuService;
import cn.wnhyang.okay.system.vo.menu.*;
import cn.wnhyang.okay.system.vo.user.UserInfoVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import static cn.wnhyang.okay.framework.common.exception.GlobalErrorCode.UNAUTHORIZED;
import static cn.wnhyang.okay.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.wnhyang.okay.framework.common.util.CollectionUtils.convertSet;
import static cn.wnhyang.okay.system.entity.MenuPO.ID_ROOT;
import static cn.wnhyang.okay.system.enums.ErrorCodes.*;


/**
 * 菜单
 *
 * @author wnhyang
 * @since 2023/05/14
 */
@Slf4j
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

        // 2、查询满足条件的菜单
        List<MenuPO> menus = menuMapper.selectList(reqVO);
        Set<Long> menuIds = menus.stream().map(MenuPO::getId).collect(Collectors.toSet());

        Set<MenuPO> menuSet = findMenusWithParentsOrChildrenByIds(all, menuIds, true, true);

        // 3、形成树形结合
        return buildMenuTree(new ArrayList<>(menuSet), false);
    }

    @Override
    public List<UserInfoVO.MenuVO> getLoginUserMenuTreeList(boolean removeButton) {
        Login loginUser = LoginUtil.getLoginUser();

        if (loginUser == null) {
            throw exception(UNAUTHORIZED);
        }
        Long id = loginUser.getId();

        List<MenuPO> all = menuMapper.selectList();
        if (LoginUtil.isAdministrator(id)) {
            return buildUserMenuTree(all, removeButton);
        }
        Set<Long> menuIds = convertSet(roleMenuMapper.selectListByRoleId(loginUser.getRoleIds()), RoleMenuPO::getMenuId);
        Set<MenuPO> menuSet = findMenusWithParentsOrChildrenByIds(all, menuIds, true, false);

        return buildUserMenuTree(new ArrayList<>(menuSet), removeButton);
    }

    public List<MenuTreeRespVO> buildMenuTree(List<MenuPO> menuList, boolean removeButton) {

        if (removeButton) {
            // 移除按钮
            menuList.removeIf(menu -> menu.getType().equals(MenuType.BUTTON.getType()));
        }

        List<MenuTreeRespVO> convert = MenuConvert.INSTANCE.convert2TreeRespList(menuList);

        Map<Long, MenuTreeRespVO> menuTreeMap = new HashMap<>();
        for (MenuTreeRespVO menu : convert) {
            menuTreeMap.put(menu.getId(), menu);
        }

        menuTreeMap.values().stream().filter(menu -> !ID_ROOT.equals(menu.getParentId())).forEach(childMenu -> {
                    MenuTreeRespVO parentMenu = menuTreeMap.get(childMenu.getParentId());
                    if (parentMenu == null) {
                        log.info("id:{} 找不到父菜单 parentId:{}", childMenu.getId(), childMenu.getParentId());
                        return;
                    }
                    // 将自己添加到父节点中
                    if (parentMenu.getChildren() == null) {
                        parentMenu.setChildren(new ArrayList<>());
                    }
                    parentMenu.getChildren().add(childMenu);
                }

        );

        return menuTreeMap.values().stream().filter(menu -> ID_ROOT.equals(menu.getParentId())).collect(Collectors.toList());
    }

    public List<UserInfoVO.MenuVO> buildUserMenuTree(List<MenuPO> menuList, boolean removeButton) {

        if (removeButton) {
            // 移除按钮
            menuList.removeIf(menu -> menu.getType().equals(MenuType.BUTTON.getType()));
        }
        List<UserInfoVO.MenuVO> convert = MenuConvert.INSTANCE.convert2UserMenuVOList(menuList);

        Map<Long, UserInfoVO.MenuVO> menuTreeMap = new HashMap<>();
        for (UserInfoVO.MenuVO menu : convert) {
            menuTreeMap.put(menu.getId(), menu);
        }

        menuTreeMap.values().stream().filter(menu -> !ID_ROOT.equals(menu.getParentId())).forEach(childMenu -> {
                    UserInfoVO.MenuVO parentMenu = menuTreeMap.get(childMenu.getParentId());
                    if (parentMenu == null) {
                        log.info("id:{} 找不到父菜单 parentId:{}", childMenu.getId(), childMenu.getParentId());
                        return;
                    }
                    // 将自己添加到父节点中
                    if (parentMenu.getChildren() == null) {
                        parentMenu.setChildren(new ArrayList<>());
                    }
                    parentMenu.getChildren().add(childMenu);
                }

        );

        return menuTreeMap.values().stream().filter(menu -> ID_ROOT.equals(menu.getParentId())).collect(Collectors.toList());
    }

    public List<MenuSimpleTreeVO> buildMenuSimpleTree(List<MenuPO> menuList, boolean removeButton) {

        if (removeButton) {
            // 移除按钮
            menuList.removeIf(menu -> menu.getType().equals(MenuType.BUTTON.getType()));
        }

        List<MenuSimpleTreeVO> convert = MenuConvert.INSTANCE.convert02(menuList);

        Map<Long, MenuSimpleTreeVO> menuTreeMap = new HashMap<>();
        for (MenuSimpleTreeVO menu : convert) {
            menuTreeMap.put(menu.getId(), menu);
        }

        menuTreeMap.values().stream().filter(menu -> !ID_ROOT.equals(menu.getParentId())).forEach(childMenu -> {
                    MenuSimpleTreeVO parentMenu = menuTreeMap.get(childMenu.getParentId());
                    if (parentMenu == null) {
                        log.info("id:{} 找不到父菜单 parentId:{}", childMenu.getId(), childMenu.getParentId());
                        return;
                    }
                    // 将自己添加到父节点中
                    if (parentMenu.getChildren() == null) {
                        parentMenu.setChildren(new ArrayList<>());
                    }
                    parentMenu.getChildren().add(childMenu);
                }

        );

        return menuTreeMap.values().stream().filter(menu -> ID_ROOT.equals(menu.getParentId())).collect(Collectors.toList());
    }

    /**
     * 查找菜单的父/子菜单集合
     *
     * @param all          所有菜单
     * @param menuIds      需要的菜单集合
     * @param withParent   是否包含父菜单
     * @param withChildren 是否包含子菜单
     * @return 结果
     */
    private Set<MenuPO> findMenusWithParentsOrChildrenByIds(List<MenuPO> all, Set<Long> menuIds, boolean withParent, boolean withChildren) {
        Map<Long, MenuPO> menuMap = new HashMap<>();
        for (MenuPO menu : all) {
            menuMap.put(menu.getId(), menu);
        }

        // 使用LinkedHashSet保持插入顺序
        Set<MenuPO> result = new LinkedHashSet<>();
        // 存储已处理过的菜单ID
        Set<Long> processedIds = new HashSet<>();
        for (Long menuId : menuIds) {
            if (withParent) {
                collectMenuParents(result, menuMap, menuId, processedIds);
            }
            if (withChildren) {
                collectMenuChildren(result, menuMap, menuId);
            }
        }

        return result;
    }

    /**
     * 递归查找当前菜单的所有父菜单
     *
     * @param resultSet    结果
     * @param menuMap      menuMap
     * @param menuId       需要的菜单id
     * @param processedIds 存储已处理过的菜单id
     */
    private void collectMenuParents(Set<MenuPO> resultSet, Map<Long, MenuPO> menuMap, Long menuId, Set<Long> processedIds) {
        if (processedIds.contains(menuId)) {
            return; // 如果已经处理过此菜单，则不再处理
        }

        processedIds.add(menuId);
        MenuPO menu = menuMap.get(menuId);
        if (menu != null) {
            resultSet.add(menu);

            // 如果当前菜单不是根节点（即parentId不为0），继续查找其父菜单
            if (menu.getParentId() != 0L && !processedIds.contains(menu.getParentId())) {
                collectMenuParents(resultSet, menuMap, menu.getParentId(), processedIds);
            }
        }
    }

    /**
     * 递归查找当前菜单的所有子菜单
     *
     * @param resultSet 结果
     * @param menuMap   menuMap
     * @param menuId    需要的菜单id
     */
    private void collectMenuChildren(Set<MenuPO> resultSet, Map<Long, MenuPO> menuMap, Long menuId) {
        MenuPO menu = menuMap.get(menuId);
        if (menu != null) {
            resultSet.add(menu);

            // 添加当前菜单的所有子菜单
            for (MenuPO child : menuMap.values()) {
                if (child.getParentId().equals(menu.getId())) {
                    collectMenuChildren(resultSet, menuMap, child.getId());
                }
            }
        }
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
        List<MenuPO> all = menuMapper.selectList();

        List<MenuSimpleTreeVO> menuSimpleTreeList = buildMenuSimpleTree(all, false);

        if (withRoot) {
            MenuSimpleTreeVO result = new MenuSimpleTreeVO();
            result.setId(ID_ROOT).setTitle("根目录").setChildren(menuSimpleTreeList);
            return Collections.singletonList(result);
        }

        // 返回result
        return menuSimpleTreeList;
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
