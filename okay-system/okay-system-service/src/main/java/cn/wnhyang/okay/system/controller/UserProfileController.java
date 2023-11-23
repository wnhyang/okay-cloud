package cn.wnhyang.okay.system.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.wnhyang.okay.framework.common.pojo.CommonResult;
import cn.wnhyang.okay.framework.operatelog.core.annotation.OperateLog;
import cn.wnhyang.okay.framework.satoken.utils.LoginHelper;
import cn.wnhyang.okay.system.convert.user.UserConvert;
import cn.wnhyang.okay.system.dto.LoginUser;
import cn.wnhyang.okay.system.entity.UserDO;
import cn.wnhyang.okay.system.service.UserService;
import cn.wnhyang.okay.system.vo.userprofile.UserProfileRespVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static cn.wnhyang.okay.framework.common.exception.enums.GlobalErrorCodeConstants.UNAUTHORIZED;

/**
 * 用户设置
 *
 * @author wnhyang
 * @date 2023/11/23
 **/
@RestController
@RequestMapping("/system/user/profile")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserService userService;

    /**
     * 查询登录用户信息
     *
     * @return 用户信息
     */
    @GetMapping("/get")
    @OperateLog(module = "后台-用户设置", name = "查询登录用户信息")
    @SaCheckLogin
    public CommonResult<UserProfileRespVO> getUserProfile() {
        LoginUser loginUser = LoginHelper.getLoginUser();
        if (loginUser == null) {
            return CommonResult.error(UNAUTHORIZED);
        }
        Long id = loginUser.getId();
        UserDO user = userService.getUserById(id);

        return CommonResult.success(UserConvert.INSTANCE.convert04(user));
    }


}
