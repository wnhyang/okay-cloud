package cn.wnhyang.okay.system.enums;

import cn.wnhyang.okay.framework.common.enums.RpcConstants;

/**
 * @author wnhyang
 * @date 2023/5/31
 **/
public interface ApiConstants {

    /**
     * 服务名
     * 注意，需要保证和 spring.application.name 保持一致
     */
    String NAME = "okay-system-service";

    String PREFIX = RpcConstants.RPC_API_PREFIX + "/system";
}
