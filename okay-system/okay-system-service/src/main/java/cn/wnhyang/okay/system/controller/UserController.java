package cn.wnhyang.okay.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.wnhyang.okay.framework.common.pojo.CommonResult;
import cn.wnhyang.okay.framework.common.pojo.PageResult;
import cn.wnhyang.okay.framework.operatelog.core.annotation.OperateLog;
import cn.wnhyang.okay.system.convert.user.UserConvert;
import cn.wnhyang.okay.system.entity.UserDO;
import cn.wnhyang.okay.system.service.UserService;
import cn.wnhyang.okay.system.vo.user.*;
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
    @OperateLog(module = "后台-用户", name = "创建用户")
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
    @OperateLog(module = "后台-用户", name = "修改用户信息")
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
    @OperateLog(module = "后台-用户", name = "删除用户")
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
    @PutMapping("/updatePassword")
    @OperateLog(module = "后台-用户", name = "更新用户密码")
    @SaCheckPermission("system:user:updatePassword")
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
    @PutMapping("/updateStatus")
    @OperateLog(module = "后台-用户", name = "更新用户状态")
    @SaCheckPermission("system:user:update")
    public CommonResult<Boolean> updateUserStatus(@RequestBody UserUpdateStatusReqVO reqVO) {
        userService.updateUserStatus(reqVO.getId(), reqVO.getStatus());
        return success(true);
    }

    /**
     * 查询用户信息
     *
     * @param id id
     * @return 用户
     */
    @GetMapping("/get")
    @OperateLog(module = "后台-用户", name = "查询用户")
    @SaCheckPermission("system:user:query")
    public CommonResult<UserRespVO> getUser(@RequestParam("id") Long id) {
        UserDO user = userService.getUser(id);
        return success(UserConvert.INSTANCE.convert02(user));
    }

    /**
     * 查询用户信息列表
     *
     * @param reqVO 查询条件
     * @return 用户列表
     */
    @GetMapping("/page")
    @OperateLog(module = "后台-用户", name = "查询用户列表")
    @SaCheckPermission("system:user:list")
    public CommonResult<PageResult<UserRespVO>> getUserPage(@RequestBody UserPageReqVO reqVO) {
        PageResult<UserDO> pageResult = userService.getUserPage(reqVO);
        return success(UserConvert.INSTANCE.convert(pageResult));
    }
}
