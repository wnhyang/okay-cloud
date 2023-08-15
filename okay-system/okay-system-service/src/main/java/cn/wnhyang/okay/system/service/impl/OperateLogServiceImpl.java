package cn.wnhyang.okay.system.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.wnhyang.okay.framework.common.pojo.PageResult;
import cn.wnhyang.okay.system.convert.operatelog.OperateLogConvert;
import cn.wnhyang.okay.system.dto.operatelog.OperateLogCreateReqDTO;
import cn.wnhyang.okay.system.entity.OperateLogDO;
import cn.wnhyang.okay.system.entity.UserDO;
import cn.wnhyang.okay.system.mapper.OperateLogMapper;
import cn.wnhyang.okay.system.service.OperateLogService;
import cn.wnhyang.okay.system.service.UserService;
import cn.wnhyang.okay.system.vo.operatelog.OperateLogPageReqVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

import static cn.wnhyang.okay.framework.common.util.CollectionUtils.convertSet;
import static cn.wnhyang.okay.system.entity.OperateLogDO.JAVA_METHOD_ARGS_MAX_LENGTH;
import static cn.wnhyang.okay.system.entity.OperateLogDO.RESULT_MAX_LENGTH;

/**
 * 操作日志记录
 *
 * @author wnhyang
 * @since 2023/06/05
 */
@Service
@RequiredArgsConstructor
public class OperateLogServiceImpl implements OperateLogService {

    private final OperateLogMapper operateLogMapper;

    private final UserService userService;

    /**
     * 记录操作日志
     *
     * @param createReqDTO 操作日志请求
     */
    @Override
    public void createOperateLog(OperateLogCreateReqDTO createReqDTO) {
        OperateLogDO logDO = OperateLogConvert.INSTANCE.convert(createReqDTO);
        logDO.setJavaMethodArgs(StrUtil.subPre(logDO.getJavaMethodArgs(), JAVA_METHOD_ARGS_MAX_LENGTH));
        logDO.setResultData(StrUtil.subPre(logDO.getResultData(), RESULT_MAX_LENGTH));
        operateLogMapper.insert(logDO);
    }

    @Override
    public PageResult<OperateLogDO> getOperateLogPage(OperateLogPageReqVO reqVO) {
        // 处理基于用户昵称的查询
        Collection<Long> userIds = null;
        if (StrUtil.isNotEmpty(reqVO.getUserNickname())) {
            userIds = convertSet(userService.getUserListByNickname(reqVO.getUserNickname()), UserDO::getId);
            if (CollUtil.isEmpty(userIds)) {
                return PageResult.empty();
            }
        }
        // 查询分页
        return operateLogMapper.selectPage(reqVO, userIds);
    }
}
