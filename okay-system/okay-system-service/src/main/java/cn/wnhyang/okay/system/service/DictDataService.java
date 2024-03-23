package cn.wnhyang.okay.system.service;


import cn.wnhyang.okay.framework.common.pojo.PageResult;
import cn.wnhyang.okay.system.entity.DictDataPO;
import cn.wnhyang.okay.system.vo.dictdata.DictDataCreateVO;
import cn.wnhyang.okay.system.vo.dictdata.DictDataPageVO;
import cn.wnhyang.okay.system.vo.dictdata.DictDataUpdateVO;

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
    Long createDictData(DictDataCreateVO reqVO);

    /**
     * 更新字典数据
     *
     * @param reqVO 字典数据
     */
    void updateDictData(DictDataUpdateVO reqVO);

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
    List<DictDataPO> getDictDataList();

    /**
     * 分页查询字段数据
     *
     * @param reqVO 分页请求
     * @return 字典数据
     */
    PageResult<DictDataPO> getDictDataPage(DictDataPageVO reqVO);

    /**
     * 查询详细字典数据
     *
     * @param id 字典数据id
     * @return 字典数据
     */
    DictDataPO getDictData(Long id);

    /**
     * 通过字典类型和字典数据获取字典
     *
     * @param dictType 字典类型
     * @param value    字典数据
     * @return 字典
     */
    DictDataPO getDictData(String dictType, String value);

    /**
     * 通过字典类型获取字典数据列表
     *
     * @param type 字典类型
     * @return 字典数据列表
     */
    List<DictDataPO> getDictDataListByDictType(String type);
}
