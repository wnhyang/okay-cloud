package cn.wnhyang.okay.system.mapper;


import cn.wnhyang.okay.framework.common.pojo.PageResult;
import cn.wnhyang.okay.framework.mybatis.core.mapper.BaseMapperX;
import cn.wnhyang.okay.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.wnhyang.okay.system.entity.DictDataPO;
import cn.wnhyang.okay.system.vo.dictdata.DictDataPageVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Arrays;
import java.util.List;

/**
 * 字典数据表 Mapper 接口
 *
 * @author wnhyang
 * @since 2023/09/13
 */
@Mapper
public interface DictDataMapper extends BaseMapperX<DictDataPO> {

    default PageResult<DictDataPO> selectPage(DictDataPageVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DictDataPO>()
                .likeIfPresent(DictDataPO::getLabel, reqVO.getLabel())
                .eqIfPresent(DictDataPO::getDictType, reqVO.getDictType())
                .eqIfPresent(DictDataPO::getStatus, reqVO.getStatus())
                .orderByDesc(Arrays.asList(DictDataPO::getDictType, DictDataPO::getSort)));
    }

    default long selectCountByDictType(String dictType) {
        return selectCount(DictDataPO::getDictType, dictType);
    }

    default DictDataPO selectByDictTypeAndValue(String dictType, String value) {
        return selectOne(DictDataPO::getDictType, dictType, DictDataPO::getValue, value);
    }

    default List<DictDataPO> selectListByDictType(String type) {
        return selectList(DictDataPO::getDictType, type);
    }
}
