package cn.wnhyang.okay.system.service;

import cn.wnhyang.okay.framework.common.pojo.PageResult;
import cn.wnhyang.okay.system.dto.loginlog.LoginLogCreateReqDTO;
import cn.wnhyang.okay.system.entity.LoginLogDO;
import cn.wnhyang.okay.system.vo.loginlog.LoginLogPageReqVO;

import javax.validation.Valid;

/**
 * 系统访问记录
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

    /**
     * 分页查询登录日志
     *
     * @param reqVO 分页请求
     * @return 登录日志分页
     */
    PageResult<LoginLogDO> getLoginLogPage(LoginLogPageReqVO reqVO);
}
