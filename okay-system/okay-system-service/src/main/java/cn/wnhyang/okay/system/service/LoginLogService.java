package cn.wnhyang.okay.system.service;

import cn.wnhyang.okay.system.dto.loginlog.LoginLogCreateReqDTO;

import javax.validation.Valid;

/**
 * <p>
 * 系统访问记录 服务类
 * </p>
 *
 * @author wnhyang
 * @since 2023/07/25
 */
public interface LoginLogService {

    /**
     * 创建登录日志
     *
     * @param reqDTO 日志信息
     */
    void createLoginLog(@Valid LoginLogCreateReqDTO reqDTO);
}