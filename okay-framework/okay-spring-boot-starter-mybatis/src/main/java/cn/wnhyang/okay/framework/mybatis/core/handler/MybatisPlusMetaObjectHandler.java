package cn.wnhyang.okay.framework.mybatis.core.handler;

import cn.hutool.core.util.ObjectUtil;
import cn.wnhyang.okay.framework.common.core.Login;
import cn.wnhyang.okay.framework.mybatis.core.base.BasePO;
import cn.wnhyang.okay.framework.satoken.core.util.LoginUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;
import java.util.Objects;

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
        if (Objects.nonNull(metaObject) && metaObject.getOriginalObject() instanceof BasePO) {
            BasePO basePO = (BasePO) metaObject.getOriginalObject();

            LocalDateTime current = LocalDateTime.now();
            // 创建时间为空，则以当前时间为插入时间
            if (Objects.isNull(basePO.getCreateTime())) {
                basePO.setCreateTime(current);
            }
            // 更新时间为空，则以当前时间为更新时间
            if (Objects.isNull(basePO.getUpdateTime())) {
                basePO.setUpdateTime(current);
            }
            if (login) {
                String username = getLoginUsername();
                // 当前登录用户不为空，创建人为空，则当前登录用户为创建人
                if (Objects.nonNull(username) && Objects.isNull(basePO.getCreator())) {
                    basePO.setCreator(username);
                }
                // 当前登录用户不为空，更新人为空，则当前登录用户为更新人
                if (Objects.nonNull(username) && Objects.isNull(basePO.getUpdater())) {
                    basePO.setUpdater(username);
                }
            }
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // 更新时间为空，则以当前时间为更新时间
        Object modifyTime = getFieldValByName("updateTime", metaObject);
        if (Objects.isNull(modifyTime)) {
            setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        }

        if (login) {
            // 当前登录用户不为空，更新人为空，则当前登录用户为更新人
            Object modifier = getFieldValByName("updater", metaObject);
            String username = getLoginUsername();
            if (Objects.nonNull(username) && Objects.isNull(modifier)) {
                setFieldValByName("updater", username, metaObject);
            }
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
