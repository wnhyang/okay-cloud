package cn.wnhyang.okay.system.login;

import cn.wnhyang.okay.framework.common.core.Login;
import cn.wnhyang.okay.framework.common.util.CollectionUtils;
import cn.wnhyang.okay.system.dto.RoleSimpleVO;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * @author wnhyang
 * @date 2024/1/5
 **/
@Data
public class LoginUser implements Login, Serializable {

    private static final long serialVersionUID = 7457433906219919160L;
    /**
     * 用户ID
     */
    private Long id;

    /**
     * 用户账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户类型
     */
    private Integer type;

    /**
     * 描述
     */
    private String remark;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 用户性别
     */
    private Integer sex;

    /**
     * 头像地址
     */
    private String avatar;

    /**
     * 帐号状态（0正常 1停用）
     */
    private Boolean status;

    /**
     * 最后登录IP
     */
    private String loginIp;

    /**
     * 最后登录时间
     */
    private LocalDateTime loginDate;

    /**
     * 角色集合
     */
    private List<RoleSimpleVO> roles;

    /**
     * 菜单权限
     */
    private Set<String> permissions;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public Integer getType() {
        return type;
    }

    @Override
    public Set<Long> getRoleIds() {
        return CollectionUtils.convertSet(roles, RoleSimpleVO::getId);
    }

    @Override
    public Set<String> getRoleValues() {
        return CollectionUtils.convertSet(roles, RoleSimpleVO::getValue);
    }

    @Override
    public Set<String> getPermissions() {
        return permissions;
    }
}
