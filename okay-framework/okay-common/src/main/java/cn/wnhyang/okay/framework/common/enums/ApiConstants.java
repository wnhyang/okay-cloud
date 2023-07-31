package cn.wnhyang.okay.framework.common.enums;

/**
 * @author wnhyang
 * @date 2023/5/31
 **/
public interface ApiConstants {

    /**
     * 服务名
     * 注意，需要保证和 spring.application.name 保持一致
     */
    String OKAY_SYSTEM_NAME = "okay-system-service";

    String OKAY_SHORTLINK_NAME = "okay-shortlink-service";

    String OKAY_SYSTEM_PREFIX = RpcConstants.RPC_API_PREFIX + "/system";

    String OKAY_SHORTLINK_PREFIX = RpcConstants.RPC_API_PREFIX + "/shortlink";
}
