package cn.wnhyang.okay.system.entity;

import cn.wnhyang.okay.framework.mybatis.core.base.BasePO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * 操作日志记录
 *
 * @author wnhyang
 * @since 2023/06/05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_operate_log")
public class OperateLogPO extends BasePO {

    /**
     * {@link #javaMethodArgs} 的最大长度
     */
    public static final Integer JAVA_METHOD_ARGS_MAX_LENGTH = 8000;

    /**
     * {@link #resultData} 的最大长度
     */
    public static final Integer RESULT_MAX_LENGTH = 4000;

    private static final long serialVersionUID = 1797386582905133955L;


    /**
     * 日志主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户编号
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 模块标题
     */
    @TableField("module")
    private String module;

    /**
     * 操作名
     */
    @TableField("name")
    private String name;

    /**
     * 操作分类
     */
    @TableField("type")
    private Integer type;

    /**
     * 操作内容
     */
    @TableField("content")
    private String content;

    /**
     * 拓展字段
     */
    @TableField(value = "exts", typeHandler = JacksonTypeHandler.class)
    private Map<String, Object> exts;

    /**
     * 请求方法名
     */
    @TableField("request_method")
    private String requestMethod;

    /**
     * 请求地址
     */
    @TableField("request_url")
    private String requestUrl;

    /**
     * 用户 IP
     */
    @TableField("user_ip")
    private String userIp;

    /**
     * 浏览器 UA
     */
    @TableField("user_agent")
    private String userAgent;

    /**
     * Java 方法名
     */
    @TableField("java_method")
    private String javaMethod;

    /**
     * Java 方法的参数
     */
    @TableField("java_method_args")
    private String javaMethodArgs;

    /**
     * 操作时间
     */
    @TableField("start_time")
    private LocalDateTime startTime;

    /**
     * 执行时长
     */
    @TableField("duration")
    private Integer duration;

    /**
     * 结果码
     */
    @TableField("result_code")
    private Integer resultCode;

    /**
     * 结果提示
     */
    @TableField("result_msg")
    private String resultMsg;

    /**
     * 结果数据
     */
    @TableField("result_data")
    private String resultData;
}
