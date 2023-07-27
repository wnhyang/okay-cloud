package cn.wnhyang.okay.system.service.impl;

import cn.wnhyang.okay.system.convert.loginlog.LoginLogConvert;
import cn.wnhyang.okay.system.dto.loginlog.LoginLogCreateReqDTO;
import cn.wnhyang.okay.system.entity.LoginLogDO;
import cn.wnhyang.okay.system.mapper.LoginLogMapper;
import cn.wnhyang.okay.system.service.LoginLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统访问记录 服务实现类
 * </p>
 *
 * @author wnhyang
 * @since 2023/07/25
 */
@Service
@RequiredArgsConstructor
public class LoginLogServiceImpl implements LoginLogService {

    final LoginLogMapper loginLogMapper;

    @Override
    public void createLoginLog(LoginLogCreateReqDTO reqDTO) {
        LoginLogDO loginLog = LoginLogConvert.INSTANCE.convert(reqDTO);
        loginLogMapper.insert(loginLog);
    }
}