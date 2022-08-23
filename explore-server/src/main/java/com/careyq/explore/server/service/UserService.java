package com.careyq.explore.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.careyq.explore.common.vo.Result;
import com.careyq.explore.server.dto.LoginDTO;
import com.careyq.explore.server.entity.User;
import com.careyq.explore.server.vo.LoginUserVO;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author CareyQ
 * @since 2022-08-19
 */
public interface UserService extends IService<User> {

    /**
     * 用户登录
     *
     * @param dto LoginDTO
     * @return LoginUserVO
     */
    Result<LoginUserVO> login(LoginDTO dto);

}
