package cn.wnhyang.okay.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.wnhyang.okay.framework.common.pojo.CommonResult;
import cn.wnhyang.okay.framework.log.core.annotation.OperateLog;
import cn.wnhyang.okay.system.service.PermissionService;
import cn.wnhyang.okay.system.vo.permission.RoleMenuVO;
import cn.wnhyang.okay.system.vo.permission.UserRoleVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

/**
 * 权限
 *
 * @author wnhyang
 * @date 2023/11/13
 **/
@RestController
@RequestMapping("/system/permission")
@RequiredArgsConstructor
public class PermissionController {

    private final PermissionService permissionService;

    /**
     * 给角色赋予菜单
     *
     * @param reqVO 请求对象，包含角色ID和菜单ID列表
     * @return 结果
     */
    @PostMapping("/roleMenu")
    @OperateLog(module = "后台-权限", name = "给角色赋予菜单")
    @SaCheckPermission("system:permission:roleMenu")
    public CommonResult<Boolean> roleMenu(@Valid @RequestBody RoleMenuVO reqVO) {
        permissionService.roleMenu(reqVO.getRoleId(), reqVO.getMenuIds());
        return CommonResult.success(true);
    }

    /**
     * 查询角色菜单列表
     *
     * @param roleId 角色ID
     * @return 角色对应的菜单ID集合
     */
    @GetMapping("/getRoleMenuList")
    @OperateLog(module = "后台-权限", name = "查询角色菜单列表")
    @SaCheckPermission("system:permission:roleMenuList")
    public CommonResult<Set<Long>> getRoleMenuList(@RequestParam("roleId") Long roleId) {
        return CommonResult.success(permissionService.getRoleMenuListByRoleId(roleId));
    }

    /**
     * 给用户赋予角色
     *
     * @param reqVO 请求对象，包含用户ID和角色ID列表
     * @return 结果
     */
    @PostMapping("/userRole")
    @OperateLog(module = "后台-权限", name = "给角色赋予菜单")
    @SaCheckPermission("system:permission:userRole")
    public CommonResult<Boolean> roleMenu(@Valid @RequestBody UserRoleVO reqVO) {
        permissionService.userRole(reqVO.getUserId(), reqVO.getRoleIds());
        return CommonResult.success(true);
    }

    /**
     * 查询用户角色列表
     *
     * @param userId 用户ID
     * @return 用户对应的角色ID集合
     */
    @GetMapping("/getUserRoleList")
    @OperateLog(module = "后台-权限", name = "查询用户角色列表")
    @SaCheckPermission("system:permission:userRoleList")
    public CommonResult<Set<Long>> getUserRoleList(@RequestParam("userId") Long userId) {
        return CommonResult.success(permissionService.getUserRoleIdListByUserId(userId));
    }


}
