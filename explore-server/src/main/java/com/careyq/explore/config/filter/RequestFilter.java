package com.careyq.explore.config.filter;

import com.careyq.explore.config.MyRequestContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 * 请求过滤器
 * </p>
 *
 * @author CareyQ
 * @since 2022-10-30
 */
@Component
public class RequestFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        MyRequestContextHolder.setRequest(request);
        chain.doFilter(request, response);
        MyRequestContextHolder.cleanAll();
    }
}
