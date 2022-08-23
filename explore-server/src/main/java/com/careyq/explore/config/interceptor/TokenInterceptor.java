package com.careyq.explore.config.interceptor;

import com.alibaba.fastjson2.JSON;
import com.careyq.explore.common.annotation.IgnoreLogin;
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

    @Override
    public boolean preHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) throws Exception {
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
        String cache = RedisUtil.get("admin_" + token);
        if (StrUtil.isBlank(cache)) {
            response.getWriter().write(result);
            return false;
        }
        return true;
    }
}
