package com.careyq.explore.common.exception;

import com.careyq.explore.common.enums.ResultCodeEnum;
import com.careyq.explore.common.util.StrUtil;
import com.careyq.explore.common.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;
import java.text.MessageFormat;

/**
 * <p>
 * 全局异常处理
 * </p>
 *
 * @author CareyQ
 * @since 2022-08-21
 */
@Slf4j
@RestControllerAdvice
@ResponseStatus(HttpStatus.ACCEPTED)
public class GlobalExceptionHandler {

    /**
     * 其他异常
     */
    @ExceptionHandler(Exception.class)
    public Result<?> exception(Exception e) {
        log.error("全局异常", e);
        return Result.fail("服务维护中...", getOutMsg(e));
    }

    @ExceptionHandler(UserException.class)
    public Result<?> exception(UserException e) {
        return Result.fail(e.getMsg());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Result<?> exception(MissingServletRequestParameterException e) {
        log.error("请求参数缺失: {} \n\n异常堆栈--->StackTrace：", e.getMessage(), e);
        return new Result<>(ResultCodeEnum.PARAMS_ERROR, getOutMsg(e), "请求参数【" + e.getParameterName() + "】缺失");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<?> exception(MethodArgumentNotValidException e) {
        log.error("参数验证未通过: {} \n\n异常堆栈--->StackTrace：", e.getMessage(), e);
        return new Result<>(ResultCodeEnum.PARAMS_ERROR, getOutMsg(e), e.getBindingResult().getFieldError() != null ?
                e.getBindingResult().getFieldError().getDefaultMessage() : "");
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result<?> exception(HttpRequestMethodNotSupportedException e) {
        log.error("请求方式有误: {} \n\n异常堆栈--->StackTrace：", e.getMessage(), e);
        return Result.fail(e.getMessage());
    }

    /**
     * 获取异常消息
     */
    private String getOutMsg(final Throwable e) {
        if (e != null) {
            return e.getClass().getCanonicalName();
        }
        return StrUtil.EMPTY;
    }

    private String buildContent(HttpServletRequest request, Exception e) {
        final String platform = request.getHeader("Platform");
        return MessageFormat.format("Error：{0}, Platform：{1}", getOutMsg(e), platform);
    }

}
