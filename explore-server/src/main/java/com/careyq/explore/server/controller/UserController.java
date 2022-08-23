package com.careyq.explore.server.controller;

import com.careyq.explore.common.annotation.IgnoreLogin;
import com.careyq.explore.common.vo.Result;
import com.careyq.explore.server.dto.LoginDTO;
import com.careyq.explore.server.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 控制层
 * </p>
 *
 * @author CareyQ
 * @since 2022-08-19
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    /**
     * 登录接口
     *
     * @param dto LoginDTO
     * @return token
     */
    @IgnoreLogin
    @PostMapping("/login")
    public Result<String> login(@RequestBody @Validated LoginDTO dto) {
        return userService.login(dto);
    }

}

