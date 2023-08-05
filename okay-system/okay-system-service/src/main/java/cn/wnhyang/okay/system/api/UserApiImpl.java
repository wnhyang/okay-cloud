package cn.wnhyang.okay.system.api;

import cn.wnhyang.okay.framework.common.pojo.CommonResult;
import cn.wnhyang.okay.system.convert.user.UserConvert;
import cn.wnhyang.okay.system.dto.LoginUser;
import cn.wnhyang.okay.system.dto.user.UserCreateReqDTO;
import cn.wnhyang.okay.system.entity.UserDO;
import cn.wnhyang.okay.system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import static cn.wnhyang.okay.framework.common.pojo.CommonResult.success;

/**
 * 用户api
 *
 * @author wnhyang
 * @date 2023/7/26
 **/
@RestController
@Validated
@RequiredArgsConstructor
public class UserApiImpl implements UserApi {

    private final UserService userService;

    /**
     * 根据用户名获取用户信息
     *
     * @param username 用户名
     * @return 用户信息
     */
    @Override
    public CommonResult<LoginUser> getUserByUsername(String username) {
        UserDO userDO = userService.getUserByUsername(username);
        return success(UserConvert.INSTANCE.convert(userDO));
    }

    /**
     *
     * @param userId  用户id
     * @param loginIp 登录ip
     */
    @Override
    public void updateUserLogin(Long userId, String loginIp) {
        userService.updateUserLogin(userId, loginIp);
    }

    /**
     * 创建用户
     *
     * @param reqDTO 用户信息
     */
    @Override
    public void registerUser(UserCreateReqDTO reqDTO) {
        userService.registerUser(reqDTO);
    }

    /**
     * 根据用户名/手机号/邮箱获取用户信息
     *
     * @param username 用户名
     * @param mobile   手机号
     * @param email    邮箱
     * @return loginUser
     */
    @Override
    public CommonResult<LoginUser> getUserInfo(String username, String mobile, String email) {
        return success(userService.getUserInfo(username, mobile, email));
    }

}
