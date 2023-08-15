package cn.wnhyang.okay.system.service.impl;

import cn.wnhyang.okay.framework.common.pojo.PageResult;
import cn.wnhyang.okay.system.convert.loginlog.LoginLogConvert;
import cn.wnhyang.okay.system.dto.loginlog.LoginLogCreateReqDTO;
import cn.wnhyang.okay.system.entity.LoginLogDO;
import cn.wnhyang.okay.system.mapper.LoginLogMapper;
import cn.wnhyang.okay.system.service.LoginLogService;
import cn.wnhyang.okay.system.vo.loginlog.LoginLogPageReqVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 系统访问记录
 *
 * @author wnhyang
 * @since 2023/07/25
 */
@Service
@RequiredArgsConstructor
public class LoginLogServiceImpl implements LoginLogService {

    private final LoginLogMapper loginLogMapper;

    @Override
    public void createLoginLog(LoginLogCreateReqDTO reqDTO) {
        LoginLogDO loginLog = LoginLogConvert.INSTANCE.convert(reqDTO);
        loginLogMapper.insert(loginLog);
    }

    @Override
    public PageResult<LoginLogDO> getLoginLogPage(LoginLogPageReqVO reqVO) {
        return loginLogMapper.selectPage(reqVO);
    }
}
