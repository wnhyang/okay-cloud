package cn.wnhyang.okay.system.dto;

import cn.wnhyang.okay.framework.common.util.CollectionUtils;
import cn.wnhyang.okay.system.dto.user.RoleSimpleRespVO;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * @author wnhyang
 * @date 2023/6/15
 **/
@Data
public class LoginUser implements Serializable {

    private static final long serialVersionUID = -1621620881010359627L;

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
     * 备注
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
    private Integer status;

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
    private List<RoleSimpleRespVO> roles;

    public Set<Long> getRoleIdList() {
        return CollectionUtils.convertSet(roles, RoleSimpleRespVO::getId);
    }

    public Set<String> getRoleNameList() {
        return CollectionUtils.convertSet(roles, RoleSimpleRespVO::getName);
    }

    public Set<String> getRoleValueList() {
        return CollectionUtils.convertSet(roles, RoleSimpleRespVO::getValue);
    }


    /**
     * 菜单权限
     */
    private Set<String> permissions;

}
