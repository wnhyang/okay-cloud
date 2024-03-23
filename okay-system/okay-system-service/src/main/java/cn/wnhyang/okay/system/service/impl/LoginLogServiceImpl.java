package cn.wnhyang.okay.system.service.impl;

import cn.wnhyang.okay.framework.common.pojo.PageResult;
import cn.wnhyang.okay.system.convert.LoginLogConvert;
import cn.wnhyang.okay.system.dto.LoginLogCreateDTO;
import cn.wnhyang.okay.system.entity.LoginLogPO;
import cn.wnhyang.okay.system.mapper.LoginLogMapper;
import cn.wnhyang.okay.system.service.LoginLogService;
import cn.wnhyang.okay.system.vo.loginlog.LoginLogPageVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 登录日志
 *
 * @author wnhyang
 * @since 2023/07/25
 */
@Service
@RequiredArgsConstructor
public class LoginLogServiceImpl implements LoginLogService {

    private final LoginLogMapper loginLogMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createLoginLog(LoginLogCreateDTO reqDTO) {
        LoginLogPO loginLog = LoginLogConvert.INSTANCE.convert(reqDTO);
        loginLogMapper.insert(loginLog);
    }

    @Override
    public PageResult<LoginLogPO> getLoginLogPage(LoginLogPageVO reqVO) {
        return loginLogMapper.selectPage(reqVO);
    }
}
