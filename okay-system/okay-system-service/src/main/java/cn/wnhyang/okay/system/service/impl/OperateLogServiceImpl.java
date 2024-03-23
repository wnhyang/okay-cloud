package cn.wnhyang.okay.system.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.wnhyang.okay.framework.common.pojo.PageResult;
import cn.wnhyang.okay.system.convert.OperateLogConvert;
import cn.wnhyang.okay.system.dto.OperateLogCreateDTO;
import cn.wnhyang.okay.system.entity.OperateLogPO;
import cn.wnhyang.okay.system.entity.UserPO;
import cn.wnhyang.okay.system.mapper.OperateLogMapper;
import cn.wnhyang.okay.system.service.OperateLogService;
import cn.wnhyang.okay.system.service.UserService;
import cn.wnhyang.okay.system.vo.operatelog.OperateLogPageVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

import static cn.wnhyang.okay.framework.common.util.CollectionUtils.convertSet;
import static cn.wnhyang.okay.system.entity.OperateLogPO.JAVA_METHOD_ARGS_MAX_LENGTH;
import static cn.wnhyang.okay.system.entity.OperateLogPO.RESULT_MAX_LENGTH;

/**
 * 操作日志
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
    @Transactional(rollbackFor = Exception.class)
    public void createOperateLog(OperateLogCreateDTO createReqDTO) {
        OperateLogPO logPO = OperateLogConvert.INSTANCE.convert(createReqDTO);
        logPO.setJavaMethodArgs(StrUtil.subPre(logPO.getJavaMethodArgs(), JAVA_METHOD_ARGS_MAX_LENGTH));
        logPO.setResultData(StrUtil.subPre(logPO.getResultData(), RESULT_MAX_LENGTH));
        operateLogMapper.insert(logPO);
    }

    @Override
    public PageResult<OperateLogPO> getOperateLogPage(OperateLogPageVO reqVO) {
        // 处理基于用户昵称的查询
        Collection<Long> userIds = null;
        if (StrUtil.isNotEmpty(reqVO.getUserNickname())) {
            userIds = convertSet(userService.getUserListByNickname(reqVO.getUserNickname()), UserPO::getId);
            if (CollUtil.isEmpty(userIds)) {
                return PageResult.empty();
            }
        }
        // 查询分页
        return operateLogMapper.selectPage(reqVO, userIds);
    }
}
