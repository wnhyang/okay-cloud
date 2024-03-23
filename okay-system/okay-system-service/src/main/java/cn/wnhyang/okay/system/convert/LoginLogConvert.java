package cn.wnhyang.okay.system.convert;


import cn.wnhyang.okay.framework.common.pojo.PageResult;
import cn.wnhyang.okay.system.dto.LoginLogCreateDTO;
import cn.wnhyang.okay.system.entity.LoginLogPO;
import cn.wnhyang.okay.system.vo.loginlog.LoginLogVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author wnhyang
 * @date 2023/7/25
 **/
@Mapper
public interface LoginLogConvert {
    LoginLogConvert INSTANCE = Mappers.getMapper(LoginLogConvert.class);

    LoginLogPO convert(LoginLogCreateDTO reqDTO);

    PageResult<LoginLogVO> convertPage(PageResult<LoginLogPO> page);
}
