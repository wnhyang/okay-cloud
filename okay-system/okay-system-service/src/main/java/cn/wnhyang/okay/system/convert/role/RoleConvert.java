package cn.wnhyang.okay.system.convert.role;

import cn.wnhyang.okay.framework.common.pojo.PageResult;
import cn.wnhyang.okay.system.dto.user.RoleSimpleRespVO;
import cn.wnhyang.okay.system.entity.RoleDO;
import cn.wnhyang.okay.system.vo.role.RoleCreateReqVO;
import cn.wnhyang.okay.system.vo.role.RoleRespVO;
import cn.wnhyang.okay.system.vo.role.RoleUpdateReqVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author wnhyang
 * @date 2023/8/10
 **/
@Mapper
public interface RoleConvert {
    RoleConvert INSTANCE = Mappers.getMapper(RoleConvert.class);

    RoleDO convert(RoleCreateReqVO reqVO);

    RoleDO convert(RoleUpdateReqVO reqVO);

    RoleRespVO convert(RoleDO role);

    PageResult<RoleRespVO> convert(PageResult<RoleDO> pageResult);

    List<RoleSimpleRespVO> convert02(List<RoleDO> roleList);
}
