package cn.wnhyang.okay.system.api;

import cn.wnhyang.okay.framework.common.pojo.CommonResult;
import cn.wnhyang.okay.system.dto.LoginUser;
import cn.wnhyang.okay.system.enums.ApiConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * @author wnhyang
 * @date 2023/5/12
 **/
@FeignClient(name = ApiConstants.NAME)
public interface UserApi {

    String PREFIX = ApiConstants.PREFIX + "/user";

    /**
     * 根据用户名获取用户信息
     *
     * @param username 用户名
     * @return 用户信息
     */
    @GetMapping(PREFIX + "/getUserByUsername")
    CommonResult<LoginUser> getUserByUsername(@Valid @RequestParam("username") String username);

    /**
     * 更新用户登录信息
     *
     * @param userId  用户id
     * @param loginIp 登录ip
     */
    @PutMapping(PREFIX + "/updateUserLogin")
    void updateUserLogin(@Valid @RequestParam("userId") Long userId, @Valid @RequestParam("loginIp") String loginIp);
}