package cn.wnhyang.okay.framework.mybatis.core.handler;

import cn.hutool.core.util.ObjectUtil;
import cn.wnhyang.okay.framework.common.core.Login;
import cn.wnhyang.okay.framework.satoken.core.util.LoginUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;

/**
 * @author wnhyang
 * @date 2023/4/11
 **/
@Slf4j
@Setter
public class MybatisPlusMetaObjectHandler implements MetaObjectHandler {

    private Boolean login;

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());

        if (login) {
            this.strictInsertFill(metaObject, "creator", String.class, getLoginUsername());
            this.strictInsertFill(metaObject, "updater", String.class, getLoginUsername());

        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());

        if (login) {
            this.strictInsertFill(metaObject, "updater", String.class, getLoginUsername());

        }
    }

    /**
     * 获取登录用户名
     */
    private String getLoginUsername() {
        Login loginUser;
        try {
            loginUser = LoginUtil.getLoginUser();
        } catch (Exception e) {
            log.warn("自动注入警告 => 用户未登录");
            return null;
        }
        return ObjectUtil.isNotNull(loginUser) ? loginUser.getUsername() : null;
    }

}
