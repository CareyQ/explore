package com.careyq.explore.config;

import com.alibaba.ttl.TransmittableThreadLocal;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 请求变量共享
 * </p>
 *
 * @author CareyQ
 * @since 2022-10-30
 */
public class MyRequestContextHolder {

    /**
     * request
     */
    private static final TransmittableThreadLocal<HttpServletRequest> REQUEST_TTL = new TransmittableThreadLocal<>();
    /**
     * token
     */
    private static final TransmittableThreadLocal<String> TOKEN_TTL = new TransmittableThreadLocal<>();

    /**
     * 清除所有
     */
    public static void cleanAll() {
        REQUEST_TTL.remove();
        TOKEN_TTL.remove();
    }

    /**
     * 设置 request
     *
     * @param request HttpServletRequest
     */
    public static void setRequest(HttpServletRequest request) {
        REQUEST_TTL.set(request);
    }

    /**
     * 获取 request
     *
     * @return HttpServletRequest
     */
    public static HttpServletRequest getRequest() {
        HttpServletRequest request = REQUEST_TTL.get();
        if (request == null && RequestContextHolder.getRequestAttributes() != null) {
            request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        }
        return request;
    }
}
