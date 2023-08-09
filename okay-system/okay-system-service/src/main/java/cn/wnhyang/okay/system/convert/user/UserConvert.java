package cn.wnhyang.okay.system.convert.user;

import cn.wnhyang.okay.framework.common.pojo.PageResult;
import cn.wnhyang.okay.system.dto.LoginUser;
import cn.wnhyang.okay.system.dto.user.UserCreateReqDTO;
import cn.wnhyang.okay.system.entity.UserDO;
import cn.wnhyang.okay.system.vo.user.UserCreateReqVO;
import cn.wnhyang.okay.system.vo.user.UserRespVO;
import cn.wnhyang.okay.system.vo.user.UserUpdateReqVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author wnhyang
 * @date 2023/7/26
 **/
@Mapper
public interface UserConvert {
    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    UserRespVO convert02(UserDO userDO);

    LoginUser convert(UserDO userDO);

    UserDO convert(UserCreateReqDTO reqDTO);

    UserDO convert(UserCreateReqVO reqVO);

    UserDO convert(UserUpdateReqVO reqVO);

    PageResult<UserRespVO> convert(PageResult<UserDO> pageResult);
}
