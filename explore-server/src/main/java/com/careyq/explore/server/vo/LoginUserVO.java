package com.careyq.explore.server.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 登录返回用户数据 VO
 * </p>
 *
 * @author CareyQ
 * @since 2022-08-21
 */
@Data
@Accessors(chain = true)
public class LoginUserVO {

    /**
     * 用户名
     */
    private String username;

    /**
     * 头像
     */
    private String avatar;

    /**
     * Token
     */
    private String token;
}
