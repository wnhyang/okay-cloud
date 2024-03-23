package cn.wnhyang.okay.system.vo.operatelog;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author wnhyang
 * @date 2023/8/15
 **/
@Data
public class OperateLogVO {

    private Long id;

    private String userNickname;

    @NotNull(message = "用户编号不能为空")
    private Long userId;

    @NotEmpty(message = "操作模块不能为空")
    private String module;

    @NotEmpty(message = "操作名")
    private String name;

    @NotNull(message = "操作分类不能为空")
    private Integer type;

    private String content;

    private Map<String, Object> exts;

    @NotEmpty(message = "请求方法名不能为空")
    private String requestMethod;

    @NotEmpty(message = "请求地址不能为空")
    private String requestUrl;

    @NotEmpty(message = "用户 IP 不能为空")
    private String userIp;

    @NotEmpty(message = "浏览器 UserAgent 不能为空")
    private String userAgent;

    @NotEmpty(message = "Java 方法名不能为空")
    private String javaMethod;

    private String javaMethodArgs;

    @NotNull(message = "开始时间不能为空")
    private LocalDateTime startTime;

    @NotNull(message = "执行时长不能为空")
    private Integer duration;

    @NotNull(message = "结果码不能为空")
    private Integer resultCode;

    private String resultMsg;

    private String resultData;
}
