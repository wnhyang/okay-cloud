package cn.wnhyang.okay.system.api;

import cn.wnhyang.okay.framework.common.pojo.CommonResult;
import cn.wnhyang.okay.system.convert.user.UserConvert;
import cn.wnhyang.okay.system.dto.LoginUser;
import cn.wnhyang.okay.system.entity.UserDO;
import cn.wnhyang.okay.system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import static cn.wnhyang.okay.framework.common.pojo.CommonResult.success;

/**
 * @author wnhyang
 * @date 2023/7/26
 **/
@RestController
@Validated
@RequiredArgsConstructor
public class UserApiImpl implements UserApi {

    private final UserService userService;

    @Override
    public CommonResult<LoginUser> getUserByUsername(String username) {
        UserDO userDO = userService.getUserByUsername(username);
        return success(UserConvert.INSTANCE.convert(userDO));
    }

    @Override
    public void updateUserLogin(Long userId, String loginIp) {
        userService.updateUserLogin(userId, loginIp);
    }
}
