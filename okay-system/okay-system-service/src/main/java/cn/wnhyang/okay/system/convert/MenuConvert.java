package cn.wnhyang.okay.system.convert;

import cn.hutool.core.collection.CollUtil;
import cn.wnhyang.okay.system.entity.MenuPO;
import cn.wnhyang.okay.system.enums.permission.MenuType;
import cn.wnhyang.okay.system.vo.menu.*;
import cn.wnhyang.okay.system.vo.user.UserInfoVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.slf4j.LoggerFactory;

import java.util.*;

import static cn.wnhyang.okay.framework.common.util.CollectionUtils.filterList;
import static cn.wnhyang.okay.system.entity.MenuPO.ID_ROOT;


/**
 * @author wnhyang
 * @date 2023/8/14
 **/
@Mapper
public interface MenuConvert {

    MenuConvert INSTANCE = Mappers.getMapper(MenuConvert.class);

    MenuPO convert(MenuCreateVO reqVO);

    MenuPO convert(MenuUpdateVO reqVO);

    List<MenuRespVO> convertList(List<MenuPO> list);

    MenuRespVO convert(MenuPO menu);

    MenuTreeRespVO convert01(MenuPO menu);

    /**
     * 将菜单列表，构建成菜单树
     *
     * @param menuList 菜单列表
     * @return 菜单树
     */
    default List<UserInfoVO.MenuVO> buildMenuTree(List<MenuPO> menuList) {
        if (CollUtil.isEmpty(menuList)) {
            return Collections.emptyList();
        }
        // 移除按钮
        menuList.removeIf(menu -> menu.getType().equals(MenuType.BUTTON.getType()));

        // 排序，保证菜单的有序性
        menuList.sort(Comparator.comparing(MenuPO::getOrderNo));

        // 构建菜单树
        // 使用 LinkedHashMap 的原因，是为了排序 。实际也可以用 Stream API ，就是太丑了。
        Map<Long, UserInfoVO.MenuVO> treeNodeMap = new LinkedHashMap<>();
        menuList.forEach(menu -> treeNodeMap.put(menu.getId(), MenuConvert.INSTANCE.convertTreeNode(menu)));
        // 处理父子关系
        treeNodeMap.values().stream().filter(node -> !node.getParentId().equals(ID_ROOT)).forEach(childNode -> {
            // 获得父节点
            UserInfoVO.MenuVO parentNode = treeNodeMap.get(childNode.getParentId());
            if (parentNode == null) {
                LoggerFactory.getLogger(getClass()).error("[buildRouterTree][resource({}) 找不到父资源({})]",
                        childNode.getId(), childNode.getParentId());
                return;
            }
            // 将自己添加到父节点中
            if (parentNode.getChildren() == null) {
                parentNode.setChildren(new ArrayList<>());
            }
            parentNode.getChildren().add(childNode);
        });
        // 获得到所有的根节点
        return filterList(treeNodeMap.values(), node -> ID_ROOT.equals(node.getParentId()));
    }

    UserInfoVO.MenuVO convertTreeNode(MenuPO menu);

    List<MenuSimpleTreeVO> convert02(List<MenuPO> list);

    List<MenuTreeRespVO> convert(List<MenuPO> menus);
}
