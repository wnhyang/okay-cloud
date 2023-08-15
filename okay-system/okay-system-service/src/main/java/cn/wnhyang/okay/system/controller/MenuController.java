package cn.wnhyang.okay.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.wnhyang.okay.framework.common.pojo.CommonResult;
import cn.wnhyang.okay.framework.operatelog.core.annotation.OperateLog;
import cn.wnhyang.okay.system.convert.menu.MenuConvert;
import cn.wnhyang.okay.system.entity.MenuDO;
import cn.wnhyang.okay.system.service.MenuService;
import cn.wnhyang.okay.system.vo.menu.MenuCreateReqVO;
import cn.wnhyang.okay.system.vo.menu.MenuListReqVO;
import cn.wnhyang.okay.system.vo.menu.MenuRespVO;
import cn.wnhyang.okay.system.vo.menu.MenuUpdateReqVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

import static cn.wnhyang.okay.framework.common.pojo.CommonResult.success;

/**
 * 菜单权限表
 *
 * @author wnhyang
 * @since 2023/05/14
 */
@RestController
@RequestMapping("/system/menu")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    /**
     * 创建菜单
     *
     * @param reqVO 菜单数据
     * @return 菜单id
     */
    @PostMapping("/create")
    @OperateLog(module = "后台-菜单", name = "创建菜单")
    @SaCheckPermission("system:menu:create")
    public CommonResult<Long> createMenu(@RequestBody MenuCreateReqVO reqVO) {
        return success(menuService.createMenu(reqVO));
    }

    /**
     * 更新菜单
     *
     * @param reqVO 菜单数据
     * @return 结果
     */
    @PutMapping("/update")
    @OperateLog(module = "后台-菜单", name = "更新菜单")
    @SaCheckPermission("system:menu:update")
    public CommonResult<Boolean> updateMenu(@RequestBody MenuUpdateReqVO reqVO) {
        menuService.updateMenu(reqVO);
        return success(true);
    }

    /**
     * 删除菜单
     *
     * @param id 菜单id
     * @return 结果
     */
    @DeleteMapping("/delete")
    @OperateLog(module = "后台-菜单", name = "删除菜单")
    @SaCheckPermission("system:menu:delete")
    public CommonResult<Boolean> deleteMenu(@RequestParam("id") Long id) {
        menuService.deleteMenu(id);
        return success(true);
    }

    /**
     * 查询菜单列表
     *
     * @param reqVO 菜单数据
     * @return 菜单列表
     */
    @GetMapping("/list")
    @OperateLog(module = "后台-菜单", name = "查询菜单列表")
    @SaCheckPermission("system:menu:query")
    public CommonResult<List<MenuRespVO>> getMenuList(@RequestBody MenuListReqVO reqVO) {
        List<MenuDO> list = menuService.getMenuList(reqVO);
        list.sort(Comparator.comparing(MenuDO::getSort));
        return success(MenuConvert.INSTANCE.convertList(list));
    }

    /**
     * 查询菜单
     *
     * @param id 菜单id
     * @return 菜单
     */
    @GetMapping("/get")
    @OperateLog(module = "后台-菜单", name = "查询菜单")
    @SaCheckPermission("system:menu:query")
    public CommonResult<MenuRespVO> getMenu(@RequestParam("id") Long id) {
        MenuDO menu = menuService.getMenu(id);
        return success(MenuConvert.INSTANCE.convert(menu));
    }
}
