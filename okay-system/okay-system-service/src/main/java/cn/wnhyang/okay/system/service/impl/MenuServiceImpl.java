package cn.wnhyang.okay.system.service.impl;

import cn.wnhyang.okay.system.mapper.MenuMapper;
import cn.wnhyang.okay.system.service.MenuService;
import cn.wnhyang.okay.system.vo.menu.MenuCreateReqVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 菜单权限表
 *
 * @author wnhyang
 * @since 2023/05/14
 */
@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuMapper menuMapper;

    @Override
    public Long createMenu(MenuCreateReqVO reqVO) {

        return null;
    }
}
