package cn.wnhyang.okay.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.wnhyang.okay.framework.common.pojo.CommonResult;
import cn.wnhyang.okay.framework.common.pojo.PageResult;
import cn.wnhyang.okay.framework.common.util.CollectionUtils;
import cn.wnhyang.okay.framework.operatelog.core.annotation.OperateLog;
import cn.wnhyang.okay.system.convert.operatelog.OperateLogConvert;
import cn.wnhyang.okay.system.entity.OperateLogDO;
import cn.wnhyang.okay.system.entity.UserDO;
import cn.wnhyang.okay.system.service.OperateLogService;
import cn.wnhyang.okay.system.service.UserService;
import cn.wnhyang.okay.system.vo.operatelog.OperateLogPageReqVO;
import cn.wnhyang.okay.system.vo.operatelog.OperateLogRespVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 操作日志
 *
 * @author wnhyang
 * @since 2023/06/05
 */
@RestController
@RequestMapping("/system/operateLog")
@RequiredArgsConstructor
public class OperateLogController {

    private final OperateLogService operateLogService;

    private final UserService userService;

    @GetMapping("/page")
    @OperateLog(module = "后台-操作日志", name = "分页查询操作日志")
    @SaCheckPermission("system:operateLog:query")
    public CommonResult<PageResult<OperateLogRespVO>> getOperateLogPage(OperateLogPageReqVO reqVO) {
        PageResult<OperateLogDO> pageResult = operateLogService.getOperateLogPage(reqVO);

        // 获得拼接需要的数据
        Collection<Long> userIds = CollectionUtils.convertList(pageResult.getList(), OperateLogDO::getUserId);
        Map<Long, UserDO> userMap = userService.getUserMap(userIds);
        // 拼接数据
        List<OperateLogRespVO> list = new ArrayList<>(pageResult.getList().size());
        pageResult.getList().forEach(operateLog -> {
            OperateLogRespVO respVO = OperateLogConvert.INSTANCE.convert(operateLog);
            respVO.setUserNickname(userMap.get(operateLog.getUserId()).getNickname());
            list.add(respVO);
        });
        return CommonResult.success(new PageResult<>(list, pageResult.getTotal()));
    }
}
