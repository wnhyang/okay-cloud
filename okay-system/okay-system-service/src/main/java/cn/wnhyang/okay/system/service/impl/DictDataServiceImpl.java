package cn.wnhyang.okay.system.service.impl;

import cn.wnhyang.okay.system.mapper.DictDataMapper;
import cn.wnhyang.okay.system.service.DictDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 字典数据表 服务实现类
 *
 * @author wnhyang
 * @since 2023/09/13
 */
@Service
@RequiredArgsConstructor
public class DictDataServiceImpl implements DictDataService {

    private DictDataMapper dictDataMapper;

}
