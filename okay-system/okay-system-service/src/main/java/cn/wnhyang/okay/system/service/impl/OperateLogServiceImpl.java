package cn.wnhyang.okay.system.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.wnhyang.okay.system.convert.OperateLogConvert;
import cn.wnhyang.okay.system.dto.OperateLogCreateReqDTO;
import cn.wnhyang.okay.system.entity.OperateLogDO;
import cn.wnhyang.okay.system.mapper.OperateLogMapper;
import cn.wnhyang.okay.system.service.OperateLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static cn.wnhyang.okay.system.entity.OperateLogDO.JAVA_METHOD_ARGS_MAX_LENGTH;
import static cn.wnhyang.okay.system.entity.OperateLogDO.RESULT_MAX_LENGTH;

/**
 * <p>
 * 操作日志记录 服务实现类
 * </p>
 *
 * @author wnhyang
 * @since 2023/06/05
 */
@Service
@RequiredArgsConstructor
public class OperateLogServiceImpl implements OperateLogService {

    private final OperateLogMapper operateLogMapper;

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
}
