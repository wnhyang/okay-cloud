package cn.wnhyang.okay.system.service.impl;

import cn.wnhyang.okay.system.mapper.UserMapper;
import cn.wnhyang.okay.system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author wnhyang
 * @since 2023/05/14
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

}
