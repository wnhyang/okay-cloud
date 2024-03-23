package cn.wnhyang.okay.system.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.wnhyang.okay.framework.common.pojo.CommonResult;
import cn.wnhyang.okay.framework.log.core.annotation.OperateLog;
import cn.wnhyang.okay.framework.satoken.core.util.LoginUtil;
import cn.wnhyang.okay.system.convert.UserConvert;
import cn.wnhyang.okay.system.entity.UserPO;
import cn.wnhyang.okay.system.service.UserService;
import cn.wnhyang.okay.system.vo.userprofile.UserProfileUpdatePasswordVO;
import cn.wnhyang.okay.system.vo.userprofile.UserProfileUpdateVO;
import cn.wnhyang.okay.system.vo.userprofile.UserProfileVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    @GetMapping
    @OperateLog(module = "后台-用户设置", name = "查询登录用户信息")
    @SaCheckLogin
    public CommonResult<UserProfileVO> getUserProfile() {
        UserPO user = userService.getUserById(LoginUtil.getUserId());

        return CommonResult.success(UserConvert.INSTANCE.convert04(user));
    }

    /**
     * 修改用户信息
     */
    @PostMapping
    @OperateLog(module = "后台-用户设置", name = "修改用户信息")
    @SaCheckLogin
    public CommonResult<Boolean> updateUserProfile(@Valid @RequestBody UserProfileUpdateVO reqVO) {
        userService.updateUserProfile(LoginUtil.getUserId(), reqVO);
        return CommonResult.success(true);
    }

    /**
     * 修改用户密码
     *
     * @param reqVO 新旧密码
     * @return 结果
     */
    @PutMapping("/updatePassword")
    @OperateLog(module = "后台-用户设置", name = "修改用户密码")
    @SaCheckLogin
    public CommonResult<Boolean> updateUserPassword(@Valid @RequestBody UserProfileUpdatePasswordVO reqVO) {
        userService.updateUserPassword(LoginUtil.getUserId(), reqVO);
        return CommonResult.success(true);
    }


}
