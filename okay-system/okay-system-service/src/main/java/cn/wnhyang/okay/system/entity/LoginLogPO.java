package cn.wnhyang.okay.system.entity;

import cn.wnhyang.okay.framework.mybatis.core.base.BasePO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 系统访问记录
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
public class LoginLogPO extends BasePO {

    private static final long serialVersionUID = 8801638334750361566L;

    /**
     * 访问ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 登录类型
     */
    @TableField("login_type")
    private Integer loginType;

    /**
     * 用户编号
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 用户类型
     */
    @TableField("user_type")
    private Integer userType;

    /**
     * 用户账号
     */
    @TableField("account")
    private String account;

    /**
     * 登录结果
     */
    @TableField("result")
    private Integer result;

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
