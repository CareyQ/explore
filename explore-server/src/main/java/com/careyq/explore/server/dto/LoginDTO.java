package com.careyq.explore.server.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 登录 DTO
 * </p>
 *
 * @author CareyQ
 * @since 2022-08-21
 */
@Data
public class LoginDTO {

    /**
     * 用户名
     */
    @NotBlank(message = "用户名和密码不能为空")
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "用户名和密码不能为空")
    private String password;

}
