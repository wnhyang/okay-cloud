package cn.wnhyang.okay.system.mapper;


import cn.wnhyang.okay.framework.common.pojo.PageResult;
import cn.wnhyang.okay.framework.mybatis.core.mapper.BaseMapperX;
import cn.wnhyang.okay.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.wnhyang.okay.system.entity.DictTypePO;
import cn.wnhyang.okay.system.vo.dicttype.DictTypePageVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 字典类型表 Mapper 接口
 *
 * @author wnhyang
 * @since 2023/09/13
 */
@Mapper
public interface DictTypeMapper extends BaseMapperX<DictTypePO> {

    default DictTypePO selectByType(String type) {
        return selectOne(DictTypePO::getType, type);
    }

    default DictTypePO selectByName(String name) {
        return selectOne(DictTypePO::getName, name);
    }

    default PageResult<DictTypePO> selectPage(DictTypePageVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DictTypePO>()
                .likeIfPresent(DictTypePO::getName, reqVO.getName())
                .likeIfPresent(DictTypePO::getType, reqVO.getType())
                .eqIfPresent(DictTypePO::getStatus, reqVO.getStatus())
                .betweenIfPresent(DictTypePO::getCreateTime, reqVO.getStartTime(), reqVO.getEndTime())
                .orderByDesc(DictTypePO::getId));
    }
}
