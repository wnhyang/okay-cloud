package cn.wnhyang.okay.system.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.wnhyang.okay.framework.common.pojo.PageResult;
import cn.wnhyang.okay.system.convert.DictTypeConvert;
import cn.wnhyang.okay.system.entity.DictDataPO;
import cn.wnhyang.okay.system.entity.DictTypePO;
import cn.wnhyang.okay.system.mapper.DictDataMapper;
import cn.wnhyang.okay.system.mapper.DictTypeMapper;
import cn.wnhyang.okay.system.service.DictTypeService;
import cn.wnhyang.okay.system.vo.dicttype.DictTypeCreateVO;
import cn.wnhyang.okay.system.vo.dicttype.DictTypePageVO;
import cn.wnhyang.okay.system.vo.dicttype.DictTypeUpdateVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static cn.wnhyang.okay.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.wnhyang.okay.system.enums.ErrorCodes.*;

/**
 * 字典类型
 *
 * @author wnhyang
 * @since 2023/09/13
 */
@Service
@RequiredArgsConstructor
public class DictTypeServiceImpl implements DictTypeService {

    private final DictTypeMapper dictTypeMapper;

    private final DictDataMapper dictDataMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createDictType(DictTypeCreateVO reqVO) {
        // 校验正确性
        validateDictTypeForCreateOrUpdate(null, reqVO.getName(), reqVO.getType());

        // 插入字典类型
        DictTypePO dictType = DictTypeConvert.INSTANCE.convert(reqVO);
        dictTypeMapper.insert(dictType);
        return dictType.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateDictType(DictTypeUpdateVO reqVO) {
        // 校验正确性
        validateDictTypeForCreateOrUpdate(reqVO.getId(), reqVO.getName(), null);

        DictTypePO oldDictType = dictTypeMapper.selectById(reqVO.getId());
        // 更新字典类型
        DictTypePO dictType = DictTypeConvert.INSTANCE.convert(reqVO);
        dictTypeMapper.updateById(dictType);
        // 字典类型更新时同时更新关联的字段数据
        if (StrUtil.isNotBlank(dictType.getType())) {
            List<DictDataPO> dictDataList = dictDataMapper.selectListByDictType(oldDictType.getType());
            for (DictDataPO dictData : dictDataList) {
                dictData.setDictType(dictType.getType());
            }
            dictDataMapper.updateBatch(dictDataList);
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteDictType(Long id) {
        // 校验是否存在
        DictTypePO dictType = validateDictTypeExists(id);
        // 校验是否有字典数据
        if (dictDataMapper.selectCountByDictType(dictType.getType()) > 0) {
            throw exception(DICT_TYPE_HAS_CHILDREN);
        }
        // 删除字典类型
        dictTypeMapper.deleteById(id);
    }

    @Override
    public PageResult<DictTypePO> getDictTypePage(DictTypePageVO reqVO) {
        return dictTypeMapper.selectPage(reqVO);
    }

    @Override
    public DictTypePO getDictType(Long id) {
        return dictTypeMapper.selectById(id);
    }

    @Override
    public List<DictTypePO> getDictTypeList() {
        return dictTypeMapper.selectList();
    }

    private void validateDictTypeForCreateOrUpdate(Long id, String name, String type) {
        // 校验自己存在
        validateDictTypeExists(id);
        // 校验字典类型的名字的唯一性
        validateDictTypeNameUnique(id, name);
        // 校验字典类型的类型的唯一性
        validateDictTypeUnique(id, type);
    }

    void validateDictTypeNameUnique(Long id, String name) {
        DictTypePO dictType = dictTypeMapper.selectByName(name);
        if (dictType == null) {
            return;
        }
        // 如果 id 为空，说明不用比较是否为相同 id 的字典类型
        if (id == null) {
            throw exception(DICT_TYPE_NAME_DUPLICATE);
        }
        if (!dictType.getId().equals(id)) {
            throw exception(DICT_TYPE_NAME_DUPLICATE);
        }
    }

    void validateDictTypeUnique(Long id, String type) {
        if (StrUtil.isEmpty(type)) {
            return;
        }
        DictTypePO dictType = dictTypeMapper.selectByType(type);
        if (dictType == null) {
            return;
        }
        // 如果 id 为空，说明不用比较是否为相同 id 的字典类型
        if (id == null) {
            throw exception(DICT_TYPE_TYPE_DUPLICATE);
        }
        if (!dictType.getId().equals(id)) {
            throw exception(DICT_TYPE_TYPE_DUPLICATE);
        }
    }

    DictTypePO validateDictTypeExists(Long id) {
        if (id == null) {
            return null;
        }
        DictTypePO dictType = dictTypeMapper.selectById(id);
        if (dictType == null) {
            throw exception(DICT_TYPE_NOT_EXISTS);
        }
        if (dictType.getStandard()) {
            throw exception(DICT_TYPE_IS_STANDARD);
        }
        return dictType;
    }

}
