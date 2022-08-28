package com.careyq.explore.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.careyq.explore.common.util.RedisUtil;
import com.careyq.explore.common.util.StrUtil;
import com.careyq.explore.common.vo.Result;
import com.careyq.explore.server.dto.LoginDTO;
import com.careyq.explore.server.entity.User;
import com.careyq.explore.server.mapper.UserMapper;
import com.careyq.explore.server.service.UserService;
import com.careyq.explore.server.vo.LoginUserVO;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author CareyQ
 * @since 2022-08-19
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public Result<LoginUserVO> login(HttpServletRequest request, LoginDTO dto) {
        User user = this.lambdaQuery()
                .eq(User::getUsername, dto.getUsername())
                .eq(User::getPassword, StrUtil.encrypt(dto.getPassword()))
                .one();
        if (user == null) {
            return Result.fail("用户名或密码有误");
        }
        String token = StrUtil.encrypt(dto.getUsername() + LocalDateTime.now());
        LoginUserVO vo = new LoginUserVO();
        vo.setUsername(user.getUsername())
                .setAvatar(user.getAvatar())
                .setToken(token);

        String ua = request.getHeader("User-Agent");
        RedisUtil.set(token + "." + StrUtil.encrypt(ua), vo, 1800);
        return Result.success(vo, "登录成功");
    }
}
