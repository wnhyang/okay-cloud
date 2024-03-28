package cn.wnhyang.okay.system.convert;

import cn.wnhyang.okay.system.entity.MenuPO;
import cn.wnhyang.okay.system.vo.menu.*;
import cn.wnhyang.okay.system.vo.user.UserInfoVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


/**
 * @author wnhyang
 * @date 2023/8/14
 **/
@Mapper
public interface MenuConvert {

    MenuConvert INSTANCE = Mappers.getMapper(MenuConvert.class);

    MenuPO convert(MenuCreateVO reqVO);

    MenuPO convert(MenuUpdateVO reqVO);

    MenuRespVO convert2RespVO(MenuPO menu);

    UserInfoVO.MenuVO convert2UserMenuVO(MenuPO menu);

    List<MenuRespVO> convertList(List<MenuPO> list);

    List<MenuSimpleTreeVO> convert02(List<MenuPO> list);

    List<MenuTreeRespVO> convert2TreeRespList(List<MenuPO> menus);

    List<UserInfoVO.MenuVO> convert2UserMenuVOList(List<MenuPO> menus);

}
