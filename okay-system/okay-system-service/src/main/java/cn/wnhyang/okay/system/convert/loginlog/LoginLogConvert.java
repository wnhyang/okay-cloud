package cn.wnhyang.okay.system.convert.loginlog;

import cn.wnhyang.okay.framework.common.pojo.PageResult;
import cn.wnhyang.okay.system.dto.loginlog.LoginLogCreateReqDTO;
import cn.wnhyang.okay.system.entity.LoginLogDO;
import cn.wnhyang.okay.system.vo.loginlog.LoginLogRespVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author wnhyang
 * @date 2023/7/25
 **/
@Mapper
public interface LoginLogConvert {
    LoginLogConvert INSTANCE = Mappers.getMapper(LoginLogConvert.class);

    LoginLogDO convert(LoginLogCreateReqDTO reqDTO);

    PageResult<LoginLogRespVO> convertPage(PageResult<LoginLogDO> page);
}
