package cn.wnhyang.okay.system.service;

import cn.wnhyang.okay.system.vo.dicttype.DictTypeCreateReqVO;
import cn.wnhyang.okay.system.vo.dicttype.DictTypeUpdateReqVO;

/**
 * 字典类型表 服务类
 *
 * @author wnhyang
 * @since 2023/09/13
 */
public interface DictTypeService {

    /**
     * 创建字典类型
     *
     * @param reqVO 字典类型
     * @return 字典类型id
     */
    Long createDictType(DictTypeCreateReqVO reqVO);

    /**
     * 更新字典类型
     *
     * @param reqVO 字典类型
     */
    void updateDictType(DictTypeUpdateReqVO reqVO);

    /**
     * 删除字典类型
     *
     * @param id 字典类型id
     */
    void deleteDictType(Long id);
}
