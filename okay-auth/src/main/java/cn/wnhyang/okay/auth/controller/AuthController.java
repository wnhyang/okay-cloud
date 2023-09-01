package cn.wnhyang.okay.auth.controller;

import cn.wnhyang.okay.auth.service.AuthService;
import cn.wnhyang.okay.auth.vo.EmailLoginReqVO;
import cn.wnhyang.okay.auth.vo.LoginReqVO;
import cn.wnhyang.okay.auth.vo.RegisterReqVO;
import cn.wnhyang.okay.framework.common.pojo.CommonResult;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static cn.wnhyang.okay.framework.common.pojo.CommonResult.success;

/**
 * 授权服务
 *
 * @author wnhyang
 * @date 2023/7/26
 **/
@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    /**
     * 登录
     *
     * @param reqVO 登录请求
     * @return token
     */
    @PostMapping("/login")
    public CommonResult<String> login(@RequestBody @Valid LoginReqVO reqVO) {
        return success(authService.login(reqVO));
    }

    /**
     * 通过邮箱+验证码登录
     *
     * @param reqVO 邮箱+验证码
     * @return token
     */
    @PostMapping("/loginByEmail")
    public CommonResult<String> login(@RequestBody @Valid EmailLoginReqVO reqVO) {
        return success(authService.login(reqVO));
    }

    /**
     * 获取验证码
     *
     * @return true
     */
    @PostMapping("/generate/EmailCode")
    public CommonResult<Boolean> generateCode(@RequestParam("account") String account) {
        authService.generateEmailCode(account);
        return success(true);
    }

    @DeleteMapping("/logout")
    public CommonResult<Boolean> logout() {
        authService.logout();
        return success(true);
    }

    /**
     * 注册
     *
     * @param reqVO 注册信息
     * @return 加密数据
     */
    @GetMapping("/register")
    public CommonResult<Boolean> register(@RequestBody RegisterReqVO reqVO) {
        authService.register(reqVO);
        return success(true);
    }
}
