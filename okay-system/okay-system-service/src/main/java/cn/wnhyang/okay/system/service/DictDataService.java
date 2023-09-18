package cn.wnhyang.okay.system.service;

import cn.wnhyang.okay.framework.common.pojo.PageResult;
import cn.wnhyang.okay.system.entity.DictDataDO;
import cn.wnhyang.okay.system.vo.dictdata.DictDataCreateReqVO;
import cn.wnhyang.okay.system.vo.dictdata.DictDataPageReqVO;
import cn.wnhyang.okay.system.vo.dictdata.DictDataUpdateReqVO;

import java.util.List;

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

    /**
     * 查询字典数据列表
     *
     * @return 字典数据列表
     */
    List<DictDataDO> getDictDataList();

    /**
     * 分页查询字段数据
     *
     * @param reqVO 分页请求
     * @return 字典数据
     */
    PageResult<DictDataDO> getDictDataPage(DictDataPageReqVO reqVO);

    /**
     * 查询详细字典数据
     *
     * @param id 字典数据id
     * @return 字典数据
     */
    DictDataDO getDictData(Long id);
}
