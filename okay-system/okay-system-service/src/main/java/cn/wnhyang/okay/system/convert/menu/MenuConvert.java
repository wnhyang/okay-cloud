package cn.wnhyang.okay.system.convert.menu;

import cn.wnhyang.okay.framework.common.pojo.PageResult;
import cn.wnhyang.okay.system.entity.MenuDO;
import cn.wnhyang.okay.system.vo.menu.MenuCreateReqVO;
import cn.wnhyang.okay.system.vo.menu.MenuRespVO;
import cn.wnhyang.okay.system.vo.menu.MenuUpdateReqVO;
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

    MenuDO convert(MenuCreateReqVO reqVO);

    MenuDO convert(MenuUpdateReqVO reqVO);

    PageResult<MenuRespVO> convertList(List<MenuDO> list);
}
