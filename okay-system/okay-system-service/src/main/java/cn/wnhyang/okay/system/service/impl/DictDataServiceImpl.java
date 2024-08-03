package cn.wnhyang.okay.system.service.impl;

import cn.wnhyang.okay.framework.common.pojo.PageResult;
import cn.wnhyang.okay.system.convert.DictDataConvert;
import cn.wnhyang.okay.system.entity.DictDataPO;
import cn.wnhyang.okay.system.entity.DictTypePO;
import cn.wnhyang.okay.system.mapper.DictDataMapper;
import cn.wnhyang.okay.system.mapper.DictTypeMapper;
import cn.wnhyang.okay.system.service.DictDataService;
import cn.wnhyang.okay.system.vo.dictdata.DictDataCreateVO;
import cn.wnhyang.okay.system.vo.dictdata.DictDataPageVO;
import cn.wnhyang.okay.system.vo.dictdata.DictDataUpdateVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

import static cn.wnhyang.okay.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.wnhyang.okay.system.enums.ErrorCodes.*;


/**
 * 字典数据
 *
 * @author wnhyang
 * @since 2023/09/13
 */
@Service
@RequiredArgsConstructor
public class DictDataServiceImpl implements DictDataService {

    private final DictDataMapper dictDataMapper;

    private final DictTypeMapper dictTypeMapper;

    /**
     * 排序 dictType > sort
     */
    private static final Comparator<DictDataPO> COMPARATOR_TYPE_AND_SORT = Comparator
            .comparing(DictDataPO::getDictType)
            .thenComparingInt(DictDataPO::getSort);

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createDictData(DictDataCreateVO reqVO) {
        // 校验正确性
        validateDictDataForCreateOrUpdate(null, reqVO.getValue(), reqVO.getDictType());

        // 插入字典类型
        DictDataPO dictData = DictDataConvert.INSTANCE.convert(reqVO);
        dictDataMapper.insert(dictData);
        return dictData.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateDictData(DictDataUpdateVO reqVO) {
        // 校验正确性
        validateDictDataForCreateOrUpdate(reqVO.getId(), reqVO.getValue(), reqVO.getDictType());

        // 更新字典类型
        DictDataPO dictData = DictDataConvert.INSTANCE.convert(reqVO);
        dictDataMapper.updateById(dictData);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteDictData(Long id) {
        // 校验是否存在
        validateDictDataExists(id);

        // 删除字典数据
        dictDataMapper.deleteById(id);
    }

    @Override
    public List<DictDataPO> getDictDataList() {
        List<DictDataPO> list = dictDataMapper.selectList();
        list.sort(COMPARATOR_TYPE_AND_SORT);
        return list;
    }

    @Override
    public PageResult<DictDataPO> getDictDataPage(DictDataPageVO reqVO) {
        return dictDataMapper.selectPage(reqVO);
    }

    @Override
    public DictDataPO getDictData(Long id) {
        return dictDataMapper.selectById(id);
    }

    @Override
    public DictDataPO getDictData(String dictType, String value) {
        return dictDataMapper.selectByDictTypeAndValue(dictType, value);
    }

    @Override
    public List<DictDataPO> getDictDataListByDictType(String type) {
        return dictDataMapper.selectListByDictType(type);
    }

    private void validateDictDataForCreateOrUpdate(Long id, String value, String dictType) {
        // 校验自己存在
        validateDictDataExists(id);
        // 校验字典类型有效
        validateDictTypeExists(dictType);
        // 校验字典数据的值的唯一性
        validateDictDataValueUnique(id, dictType, value);
    }

    public void validateDictDataValueUnique(Long id, String dictType, String value) {
        DictDataPO dictData = dictDataMapper.selectByDictTypeAndValue(dictType, value);
        if (dictData == null) {
            return;
        }
        // 如果 id 为空，说明不用比较是否为相同 id 的字典数据
        if (id == null) {
            throw exception(DICT_DATA_VALUE_DUPLICATE);
        }
        if (!dictData.getId().equals(id)) {
            throw exception(DICT_DATA_VALUE_DUPLICATE);
        }
    }

    public void validateDictDataExists(Long id) {
        if (id == null) {
            return;
        }
        DictDataPO dictData = dictDataMapper.selectById(id);
        if (dictData == null) {
            throw exception(DICT_DATA_NOT_EXISTS);
        }
    }

    public void validateDictTypeExists(String type) {
        DictTypePO dictType = dictTypeMapper.selectByType(type);
        if (dictType == null) {
            throw exception(DICT_TYPE_NOT_EXISTS);
        }
        if (!Boolean.TRUE.equals(dictType.getStatus())) {
            throw exception(DICT_TYPE_NOT_ENABLE);
        }
    }
}
