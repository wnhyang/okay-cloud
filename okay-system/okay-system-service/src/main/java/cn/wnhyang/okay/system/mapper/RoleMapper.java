package cn.wnhyang.okay.system.mapper;


import cn.wnhyang.okay.framework.common.pojo.PageResult;
import cn.wnhyang.okay.framework.mybatis.core.mapper.BaseMapperX;
import cn.wnhyang.okay.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.wnhyang.okay.system.entity.RolePO;
import cn.wnhyang.okay.system.vo.role.RolePageVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色信息表
 *
 * @author wnhyang
 * @since 2023/05/14
 */
@Mapper
public interface RoleMapper extends BaseMapperX<RolePO> {

    default RolePO selectByName(String name) {
        return selectOne(RolePO::getName, name);
    }

    default RolePO selectByValue(String value) {
        return selectOne(RolePO::getValue, value);
    }

    default PageResult<RolePO> selectPage(RolePageVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<RolePO>()
                .likeIfPresent(RolePO::getName, reqVO.getName())
                .likeIfPresent(RolePO::getValue, reqVO.getValue())
                .eqIfPresent(RolePO::getStatus, reqVO.getStatus())
                .betweenIfPresent(RolePO::getCreateTime, reqVO.getStartTime(), reqVO.getEndTime())
                .orderByDesc(RolePO::getId));
    }
}
