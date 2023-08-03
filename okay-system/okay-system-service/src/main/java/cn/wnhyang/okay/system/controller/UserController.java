package cn.wnhyang.okay.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.wnhyang.okay.framework.common.pojo.CommonResult;
import cn.wnhyang.okay.system.service.UserService;
import cn.wnhyang.okay.system.vo.user.UserCreateReqVO;
import cn.wnhyang.okay.system.vo.user.UserUpdatePasswordReqVO;
import cn.wnhyang.okay.system.vo.user.UserUpdateReqVO;
import cn.wnhyang.okay.system.vo.user.UserUpdateStatusReqVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static cn.wnhyang.okay.framework.common.pojo.CommonResult.success;

/**
 * 用户信息表
 *
 * @author wnhyang
 * @since 2023/05/14
 */
@RestController
@RequestMapping("/system/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 创建用户
     *
     * @param reqVO 用户信息
     * @return 用户id
     */
    @PostMapping("/create")
    @SaCheckPermission("system:user:create")
    public CommonResult<Long> createUser(@RequestBody UserCreateReqVO reqVO) {
        Long id = userService.createUser(reqVO);
        return success(id);
    }

    /**
     * 修改用户信息
     *
     * @param reqVO 用户信息
     * @return 结果
     */
    @PutMapping("/update")
    @SaCheckPermission("system:user:update")
    public CommonResult<Boolean> updateUser(@RequestBody UserUpdateReqVO reqVO) {
        userService.updateUser(reqVO);
        return success(true);
    }

    /**
     * 删除用户
     *
     * @param id 用户id
     * @return 结果
     */
    @DeleteMapping("/delete")
    @SaCheckPermission("system:user:delete")
    public CommonResult<Boolean> deleteUser(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return success(true);
    }

    /**
     * 更新用户密码
     *
     * @param reqVO id+密码
     * @return 结果
     */
    @PutMapping("/update-password")
    @SaCheckPermission("system:user:update-password")
    public CommonResult<Boolean> updateUserPassword(@RequestBody UserUpdatePasswordReqVO reqVO) {
        userService.updateUserPassword(reqVO);
        return success(true);
    }

    /**
     * 更新用户状态
     *
     * @param reqVO id+状态
     * @return 结果
     */
    @PutMapping("/update-satus")
    @SaCheckPermission("system:user:update-status")
    private CommonResult<Boolean> updateUserStatus(@RequestBody UserUpdateStatusReqVO reqVO) {
        userService.updateUserStatus(reqVO);
        return success(true);
    }

}
