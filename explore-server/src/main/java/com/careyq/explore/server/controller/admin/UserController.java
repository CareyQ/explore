package com.careyq.explore.server.controller.admin;

import com.careyq.explore.common.annotation.IgnoreLogin;
import com.careyq.explore.common.vo.Result;
import com.careyq.explore.server.dto.LoginDTO;
import com.careyq.explore.server.service.UserService;
import com.careyq.explore.server.vo.LoginUserVO;
import jakarta.servlet.http.HttpServletRequest;
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
     * @return LoginUserVO
     */
    @IgnoreLogin
    @PostMapping("/login")
    public Result<LoginUserVO> login(HttpServletRequest request, @RequestBody @Validated LoginDTO dto) {
        return userService.login(request, dto);
    }

}

