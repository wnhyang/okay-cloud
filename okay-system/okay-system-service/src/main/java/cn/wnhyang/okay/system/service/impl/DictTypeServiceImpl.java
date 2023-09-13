package cn.wnhyang.okay.system.service.impl;

import cn.wnhyang.okay.system.mapper.DictTypeMapper;
import cn.wnhyang.okay.system.service.DictTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 字典类型表 服务实现类
 *
 * @author wnhyang
 * @since 2023/09/13
 */
@Service
@RequiredArgsConstructor
public class DictTypeServiceImpl implements DictTypeService {

    private final DictTypeMapper dictTypeMapper;

}
