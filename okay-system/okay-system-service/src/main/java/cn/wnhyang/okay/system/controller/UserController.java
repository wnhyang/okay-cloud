package cn.wnhyang.okay.system.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.wnhyang.okay.framework.common.core.Login;
import cn.wnhyang.okay.framework.common.pojo.CommonResult;
import cn.wnhyang.okay.framework.common.pojo.PageResult;
import cn.wnhyang.okay.framework.log.core.annotation.OperateLog;
import cn.wnhyang.okay.framework.satoken.core.util.LoginUtil;
import cn.wnhyang.okay.system.convert.UserConvert;
import cn.wnhyang.okay.system.entity.RolePO;
import cn.wnhyang.okay.system.entity.UserPO;
import cn.wnhyang.okay.system.service.MenuService;
import cn.wnhyang.okay.system.service.PermissionService;
import cn.wnhyang.okay.system.service.RoleService;
import cn.wnhyang.okay.system.service.UserService;
import cn.wnhyang.okay.system.vo.user.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static cn.wnhyang.okay.framework.common.exception.GlobalErrorCode.UNAUTHORIZED;
import static cn.wnhyang.okay.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.wnhyang.okay.framework.common.pojo.CommonResult.success;

/**
 * 用户
 *
 * @author wnhyang
 * @since 2023/05/14
 */
@RestController
@RequestMapping("/system/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final RoleService roleService;

    private final MenuService menuService;

    private final PermissionService permissionService;

    /**
     * 创建用户
     *
     * @param reqVO 用户信息
     * @return 用户id
     */
    @PostMapping
    @OperateLog(module = "后台-用户", name = "创建用户")
    @SaCheckPermission("system:user:create")
    public CommonResult<Long> createUser(@Valid @RequestBody UserCreateVO reqVO) {
        Long id = userService.createUser(reqVO);
        return success(id);
    }

    /**
     * 修改用户信息
     *
     * @param reqVO 用户信息
     * @return 结果
     */
    @PutMapping
    @OperateLog(module = "后台-用户", name = "修改用户信息")
    @SaCheckPermission("system:user:update")
    public CommonResult<Boolean> updateUser(@Valid @RequestBody UserUpdateVO reqVO) {
        userService.updateUser(reqVO);
        return success(true);
    }

    /**
     * 删除用户
     *
     * @param id 用户id
     * @return 结果
     */
    @DeleteMapping
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
    public CommonResult<Boolean> updateUserPassword(@Valid @RequestBody UserUpdatePasswordVO reqVO) {
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
    public CommonResult<Boolean> updateUserStatus(@Valid @RequestBody UserUpdateStatusVO reqVO) {
        userService.updateUserStatus(reqVO.getId(), reqVO.getStatus());
        return success(true);
    }

    /**
     * 查询用户信息
     *
     * @param id id
     * @return 用户
     */
    @GetMapping
    @OperateLog(module = "后台-用户", name = "查询用户")
    @SaCheckPermission("system:user:query")
    public CommonResult<UserRespVO> getUser(@RequestParam("id") Long id) {
        UserPO user = userService.getUserById(id);
        Set<Long> roleIds = permissionService.getRoleIdListByUserId(user.getId());

        List<RolePO> roleList = roleService.getRoleList(roleIds);
        UserRespVO respVO = UserConvert.INSTANCE.convert(user, roleList);
        respVO.setRoleIds(roleIds);

        return success(respVO);
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
    public CommonResult<PageResult<UserRespVO>> getUserPage(@Valid UserPageVO reqVO) {
        PageResult<UserPO> pageResult = userService.getUserPage(reqVO);

        List<UserRespVO> userRespVOList = pageResult.getList().stream().map(user -> {
            Set<Long> roleIds = permissionService.getRoleIdListByUserId(user.getId());
            List<RolePO> roleList = roleService.getRoleList(roleIds);
            UserRespVO respVO = UserConvert.INSTANCE.convert(user, roleList);
            respVO.setRoleIds(roleIds);
            return respVO;
        }).collect(Collectors.toList());

        return success(new PageResult<>(userRespVOList, pageResult.getTotal()));
    }

    /**
     * 查询用户信息(登录成功后调用)
     *
     * @return 用户信息
     */
    @GetMapping("/info")
    @OperateLog(module = "后台-用户", name = "查询用户信息")
    @SaCheckLogin
    public CommonResult<UserInfoVO> getUserInfo() {

        Login loginUser = LoginUtil.getLoginUser();

        if (loginUser == null) {
            throw exception(UNAUTHORIZED);
        }
        Long id = loginUser.getId();

        UserPO user = userService.getUserById(id);
        UserInfoVO respVO = new UserInfoVO();
        UserInfoVO.UserVO userVO = UserConvert.INSTANCE.convert03(user);
        respVO.setUser(userVO);
        respVO.setRoles(loginUser.getRoleValues());
        respVO.setPermissions(loginUser.getPermissions());

        List<UserInfoVO.MenuVO> userMenuTreeList = menuService.getLoginUserMenuTreeList(true);
        respVO.setMenus(userMenuTreeList);
        return success(respVO);
    }
}
