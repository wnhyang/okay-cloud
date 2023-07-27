package cn.wnhyang.okay.auth.controller;

import cn.dev33.satoken.secure.BCrypt;
import cn.wnhyang.okay.auth.service.AuthService;
import cn.wnhyang.okay.auth.vo.AuthLoginReqVO;
import cn.wnhyang.okay.framework.common.pojo.CommonResult;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static cn.wnhyang.okay.framework.common.pojo.CommonResult.success;

/**
 * @author wnhyang
 * @date 2023/7/26
 **/
@RestController
@RequiredArgsConstructor
@Validated
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public CommonResult<String> login(@RequestBody @Valid AuthLoginReqVO reqVO) {
        return success(authService.login(reqVO));
    }

    @GetMapping("/register")
    public CommonResult<String> register(@RequestParam("username") String username) {
        return success(BCrypt.hashpw("123456"));
    }
}
