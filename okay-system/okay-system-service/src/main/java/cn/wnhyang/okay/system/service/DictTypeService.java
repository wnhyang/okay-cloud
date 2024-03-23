package cn.wnhyang.okay.system.service;


import cn.wnhyang.okay.framework.common.pojo.PageResult;
import cn.wnhyang.okay.system.entity.DictTypePO;
import cn.wnhyang.okay.system.vo.dicttype.DictTypeCreateVO;
import cn.wnhyang.okay.system.vo.dicttype.DictTypePageVO;
import cn.wnhyang.okay.system.vo.dicttype.DictTypeUpdateVO;

import java.util.List;

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
    Long createDictType(DictTypeCreateVO reqVO);

    /**
     * 更新字典类型
     *
     * @param reqVO 字典类型
     */
    void updateDictType(DictTypeUpdateVO reqVO);

    /**
     * 删除字典类型
     *
     * @param id 字典类型id
     */
    void deleteDictType(Long id);

    /**
     * 分页查询字典类型
     *
     * @param reqVO 分页请求
     * @return 分页结果
     */
    PageResult<DictTypePO> getDictTypePage(DictTypePageVO reqVO);

    /**
     * 字典类型
     *
     * @param id 字典类型id
     * @return 字典类型
     */
    DictTypePO getDictType(Long id);

    /**
     * 获取简单字典类型
     *
     * @return 字典类型列表
     */
    List<DictTypePO> getDictTypeList();
}
