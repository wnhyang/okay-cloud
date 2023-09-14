package cn.wnhyang.okay.system.service;

import cn.wnhyang.okay.system.vo.dictdata.DictDataCreateReqVO;
import cn.wnhyang.okay.system.vo.dictdata.DictDataUpdateReqVO;

/**
 * 字典数据表 服务类
 *
 * @author wnhyang
 * @since 2023/09/13
 */
public interface DictDataService {

    /**
     * 创建字典数据
     *
     * @param reqVO 字典数据
     * @return 字典数据id
     */
    Long createDictData(DictDataCreateReqVO reqVO);

    /**
     * 更新字典数据
     *
     * @param reqVO 字典数据
     */
    void updateDictData(DictDataUpdateReqVO reqVO);

    /**
     * 删除字典数据
     *
     * @param id 字典数据id
     */
    void deleteDictData(Long id);
}
