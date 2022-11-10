package com.careyq.explore.config.interceptor;

import com.alibaba.fastjson2.JSON;
import com.careyq.explore.common.annotation.IgnoreLogin;
import com.careyq.explore.common.util.DigestUtil;
import com.careyq.explore.common.util.RedisUtil;
import com.careyq.explore.common.util.StrUtil;
import com.careyq.explore.common.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * Token 拦截器
 * </p>
 *
 * @author CareyQ
 * @since 2022-08-21
 */
@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {

    private static final long REST_TIME = 900;

    @Override
    public boolean preHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) throws Exception {
        if(true) {
            return true;
        }
        // 检查忽略登录注解
        if (handler instanceof HandlerMethod handlerMethod) {
            if (handlerMethod.hasMethodAnnotation(IgnoreLogin.class) || handlerMethod.getBeanType().isAnnotationPresent(IgnoreLogin.class)) {
                return true;
            }
        } else {
            return true;
        }

        String token = request.getHeader("Authorization");
        log.info("拦截到 Token: " + token);

        String result = JSON.toJSONString(Result.unLogin());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        if (StrUtil.isBlank(token)) {
            response.getWriter().write(result);
            return false;
        }

        String ua = request.getHeader("User-Agent");
        String encryptUa = DigestUtil.encrypt(ua);
        String key = token + "." + encryptUa;
        String cache = RedisUtil.get(key);
        if (StrUtil.isBlank(cache)) {
            response.getWriter().write(result);
            return false;
        }
        // 检查 token 有效期，自动延长
        Long expire = RedisUtil.expire(key);
        if (expire != null && expire < REST_TIME) {
            RedisUtil.expire(key, 1800 - expire);
        }
        return true;
    }
}
