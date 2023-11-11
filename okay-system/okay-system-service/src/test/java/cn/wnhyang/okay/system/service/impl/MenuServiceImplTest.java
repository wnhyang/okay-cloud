package cn.wnhyang.okay.system.service.impl;

import cn.wnhyang.okay.system.service.MenuService;
import cn.wnhyang.okay.system.vo.menu.MenuListReqVO;
import cn.wnhyang.okay.system.vo.menu.MenuTreeRespVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
@Slf4j
class MenuServiceImplTest {

    @Resource
    private MenuService menuService;

    @Test
    void getMenuTreeList() {
        MenuListReqVO reqVO = new MenuListReqVO();
        reqVO.setTitle("管理");
        List<MenuTreeRespVO> menuTreeList = menuService.getMenuTreeList(reqVO);
        log.info("menuTreeList: {}", menuTreeList);
    }
}