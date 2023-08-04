package cn.wnhyang.okay.system.api;

import cn.wnhyang.okay.framework.common.enums.ApiConstants;
import cn.wnhyang.okay.framework.common.pojo.CommonResult;
import cn.wnhyang.okay.system.dto.LoginUser;
import cn.wnhyang.okay.system.dto.user.UserCreateReqDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * @author wnhyang
 * @date 2023/5/12
 **/
@FeignClient(name = ApiConstants.OKAY_SYSTEM_NAME)
public interface UserApi {

    String PREFIX = ApiConstants.OKAY_SYSTEM_PREFIX + "/user";

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

    /**
     * 创建用户
     *
     * @param reqDTO 用户信息
     */
    @PostMapping(PREFIX + "/register")
    void registerUser(UserCreateReqDTO reqDTO);

    /**
     * 根据用户名/手机号/邮箱获取用户信息
     *
     * @param username 用户名
     * @param mobile   手机号
     * @param email    邮箱
     * @return loginUser
     */
    @GetMapping(PREFIX + "/getUserInfo")
    CommonResult<LoginUser> getUserInfo(@RequestParam("username") String username, @RequestParam("mobile") String mobile, @RequestParam("email") String email);
}
