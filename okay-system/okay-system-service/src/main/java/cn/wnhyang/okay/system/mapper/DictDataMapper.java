package cn.wnhyang.okay.system.mapper;

import cn.wnhyang.okay.framework.common.pojo.PageResult;
import cn.wnhyang.okay.framework.mybatis.core.mapper.BaseMapperX;
import cn.wnhyang.okay.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.wnhyang.okay.system.entity.DictDataDO;
import cn.wnhyang.okay.system.vo.dictdata.DictDataPageReqVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Arrays;

/**
 * 字典数据表 Mapper 接口
 *
 * @author wnhyang
 * @since 2023/09/13
 */
@Mapper
public interface DictDataMapper extends BaseMapperX<DictDataDO> {

    default PageResult<DictDataDO> selectPage(DictDataPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DictDataDO>()
                .likeIfPresent(DictDataDO::getLabel, reqVO.getLabel())
                .eqIfPresent(DictDataDO::getDictType, reqVO.getDictType())
                .eqIfPresent(DictDataDO::getStatus, reqVO.getStatus())
                .orderByDesc(Arrays.asList(DictDataDO::getDictType, DictDataDO::getSort)));
    }

    default long selectCountByDictType(String dictType) {
        return selectCount(DictDataDO::getDictType, dictType);
    }

    default DictDataDO selectByDictTypeAndValue(String dictType, String value) {
        return selectOne(DictDataDO::getDictType, dictType, DictDataDO::getValue, value);
    }
}
