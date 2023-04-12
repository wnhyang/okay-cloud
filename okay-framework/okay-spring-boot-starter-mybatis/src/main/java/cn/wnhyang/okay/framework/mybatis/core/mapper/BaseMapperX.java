package cn.wnhyang.okay.framework.mybatis.core.mapper;

import cn.wnhyang.okay.framework.common.pojo.PageParam;
import cn.wnhyang.okay.framework.common.pojo.PageResult;
import cn.wnhyang.okay.framework.mybatis.core.util.MyBatisUtils;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;

/**
 * @author wnhyang
 * @date 2023/4/11
 **/
public interface BaseMapperX<T> extends BaseMapper<T> {
    /**
     * 分页查询
     *
     * @param pageParam    分页参数
     * @param queryWrapper 查询条件
     * @return 分页结果
     */
    default PageResult<T> selectPage(PageParam pageParam, @Param("ew") Wrapper<T> queryWrapper) {
        // MyBatis Plus 查询
        IPage<T> mpPage = MyBatisUtils.buildPage(pageParam);
        selectPage(mpPage, queryWrapper);
        // 转换返回
        return new PageResult<>(mpPage.getRecords(), mpPage.getTotal());
    }
}
