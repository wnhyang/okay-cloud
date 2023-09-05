package cn.wnhyang.okay.system.convert.menu;

import cn.wnhyang.okay.system.entity.MenuDO;
import cn.wnhyang.okay.system.vo.menu.MenuCreateReqVO;
import cn.wnhyang.okay.system.vo.menu.MenuRespVO;
import cn.wnhyang.okay.system.vo.menu.MenuUpdateReqVO;
import cn.wnhyang.okay.system.vo.user.UserInfoRespVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.slf4j.LoggerFactory;

import java.util.*;

import static cn.wnhyang.okay.framework.common.util.CollectionUtils.filterList;
import static cn.wnhyang.okay.system.entity.MenuDO.ID_ROOT;


/**
 * @author wnhyang
 * @date 2023/8/14
 **/
@Mapper
public interface MenuConvert {

    MenuConvert INSTANCE = Mappers.getMapper(MenuConvert.class);

    MenuDO convert(MenuCreateReqVO reqVO);

    MenuDO convert(MenuUpdateReqVO reqVO);

    List<MenuRespVO> convertList(List<MenuDO> list);

    MenuRespVO convert(MenuDO menu);

    /**
     * 将菜单列表，构建成菜单树
     *
     * @param menuList 菜单列表
     * @return 菜单树
     */
    default List<UserInfoRespVO.MenuVO> buildMenuTree(List<MenuDO> menuList) {
        // 排序，保证菜单的有序性
        menuList.sort(Comparator.comparing(MenuDO::getSort));

        // 构建菜单树
        // 使用 LinkedHashMap 的原因，是为了排序 。实际也可以用 Stream API ，就是太丑了。
        Map<Long, UserInfoRespVO.MenuVO> treeNodeMap = new LinkedHashMap<>();
        menuList.forEach(menu -> treeNodeMap.put(menu.getId(), MenuConvert.INSTANCE.convertTreeNode(menu)));
        // 处理父子关系
        treeNodeMap.values().stream().filter(node -> !node.getParentId().equals(ID_ROOT)).forEach(childNode -> {
            // 获得父节点
            UserInfoRespVO.MenuVO parentNode = treeNodeMap.get(childNode.getParentId());
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

    UserInfoRespVO.MenuVO convertTreeNode(MenuDO menu);
}
