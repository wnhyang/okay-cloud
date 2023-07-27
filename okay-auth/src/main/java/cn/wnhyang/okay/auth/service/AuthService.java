package cn.wnhyang.okay.auth.service;

import cn.wnhyang.okay.auth.vo.AuthLoginReqVO;
import cn.wnhyang.okay.system.dto.LoginUser;

/**
 * @author wnhyang
 * @date 2023/7/26
 **/
public interface AuthService {

    /**
     * 验证账号 + 密码。如果通过，则返回用户
     *
     * @param username 账号
     * @param password 密码
     * @return 用户
     */
    LoginUser authenticate(String username, String password);

    /**
     * 用户登录
     *
     * @param reqVO 登录信息
     * @return 登录结果
     */
    String login(AuthLoginReqVO reqVO);
}
