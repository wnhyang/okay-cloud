package cn.wnhyang.okay.system.service;

import cn.wnhyang.okay.framework.common.pojo.PageResult;
import cn.wnhyang.okay.system.entity.DictTypeDO;
import cn.wnhyang.okay.system.vo.dicttype.DictTypeCreateReqVO;
import cn.wnhyang.okay.system.vo.dicttype.DictTypePageReqVO;
import cn.wnhyang.okay.system.vo.dicttype.DictTypeUpdateReqVO;

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

    /**
     * 分页查询字典类型
     *
     * @param reqVO 分页请求
     * @return 分页结果
     */
    PageResult<DictTypeDO> getDictTypePage(DictTypePageReqVO reqVO);

    /**
     * 字典类型
     *
     * @param id 字典类型id
     * @return 字典类型
     */
    DictTypeDO getDictType(Long id);

    /**
     * 获取简单字典类型
     *
     * @return 字典类型列表
     */
    List<DictTypeDO> getDictTypeList();
}
