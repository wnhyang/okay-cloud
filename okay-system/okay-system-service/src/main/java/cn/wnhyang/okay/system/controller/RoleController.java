package cn.wnhyang.okay.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.wnhyang.okay.framework.common.pojo.CommonResult;
import cn.wnhyang.okay.framework.common.pojo.PageResult;
import cn.wnhyang.okay.framework.operatelog.core.annotation.OperateLog;
import cn.wnhyang.okay.system.convert.role.RoleConvert;
import cn.wnhyang.okay.system.entity.RoleDO;
import cn.wnhyang.okay.system.service.RoleService;
import cn.wnhyang.okay.system.vo.role.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static cn.wnhyang.okay.framework.common.pojo.CommonResult.success;

/**
 * 角色信息表
 *
 * @author wnhyang
 * @since 2023/05/14
 */
@RestController
@RequestMapping("/system/role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    /**
     * 创建角色
     *
     * @param reqVO 角色信息
     * @return id
     */
    @PostMapping("/create")
    @OperateLog(module = "后台-角色", name = "创建角色")
    @SaCheckPermission("system:role:create")
    public CommonResult<Long> createRole(@RequestBody RoleCreateReqVO reqVO) {
        return success(roleService.createRole(reqVO));
    }

    /**
     * 更新角色
     *
     * @param reqVO 角色信息
     * @return 结果
     */
    @PutMapping("/update")
    @OperateLog(module = "后台-角色", name = "更新角色")
    @SaCheckPermission("system:role:update")
    public CommonResult<Boolean> updateRole(@RequestBody RoleUpdateReqVO reqVO) {
        roleService.updateRole(reqVO);
        return success(true);
    }

    /**
     * 更新角色状态
     *
     * @param reqVO id+状态
     * @return 结果
     */
    @PutMapping("/updateStatus")
    @OperateLog(module = "后台-角色", name = "更新角色状态")
    @SaCheckPermission("system:role:update")
    public CommonResult<Boolean> updateRoleStatus(@RequestBody RoleUpdateStatusReqVO reqVO) {
        roleService.updateRoleStatus(reqVO.getId(), reqVO.getStatus());
        return success(true);
    }

    /**
     * 删除角色
     *
     * @param id 角色id
     * @return 结果
     */
    @DeleteMapping("/delete")
    @OperateLog(module = "后台-角色", name = "删除角色")
    @SaCheckPermission("system:role:delete")
    public CommonResult<Boolean> deleteRole(@RequestParam("id") Long id) {
        roleService.deleteRole(id);
        return success(true);
    }

    /**
     * 查询角色信息
     *
     * @param id id
     * @return 角色信息
     */
    @GetMapping("/get")
    @OperateLog(module = "后台-角色", name = "查询角色")
    @SaCheckPermission("system:role:query")
    public CommonResult<RoleRespVO> getRole(@RequestParam("id") Long id) {
        RoleDO role = roleService.getRole(id);
        return success(RoleConvert.INSTANCE.convert(role));
    }

    /**
     * 查询角色列表
     *
     * @param reqVO 请求数据
     * @return 角色列表
     */
    @GetMapping("/page")
    @OperateLog(module = "后台-角色", name = "查询角色列表")
    @SaCheckPermission("system:role:list")
    public CommonResult<PageResult<RoleRespVO>> getRolePage(RolePageReqVO reqVO) {
        PageResult<RoleDO> pageResult = roleService.getRolePage(reqVO);
        return success(RoleConvert.INSTANCE.convert(pageResult));
    }
}
