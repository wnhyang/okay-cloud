package cn.wnhyang.okay.system.entity;

import cn.wnhyang.okay.framework.mybatis.core.base.BaseDO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * <p>
 * 系统访问记录
 * </p>
 *
 * @author wnhyang
 * @since 2023/07/25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_login_log")
public class LoginLogDO extends BaseDO {

    /**
     * 访问ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 登录类型
     */
    @TableField("login_type")
    private Long loginType;

    /**
     * 用户编号
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 用户类型
     */
    @TableField("user_type")
    private Byte userType;

    /**
     * 用户账号
     */
    @TableField("username")
    private String username;

    /**
     * 登录结果
     */
    @TableField("result")
    private Byte result;

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
}