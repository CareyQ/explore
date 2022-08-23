package com.careyq.explore.common.exception;

import com.careyq.explore.common.enums.ResultCodeEnum;
import com.careyq.explore.common.util.StrUtil;
import com.careyq.explore.common.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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

    private String getOutMsg(final Throwable e) {
        if (e != null) {
            return e.getClass().getCanonicalName();
        }
        return StrUtil.EMPTY;
    }
}
