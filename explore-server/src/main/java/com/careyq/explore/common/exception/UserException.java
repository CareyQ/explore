package com.careyq.explore.common.exception;

import com.careyq.explore.common.enums.ResultCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户异常
 * </p>
 *
 * @author CareyQ
 * @since 2022-08-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserException extends RuntimeException {

    private String msg;
    private Integer code = ResultCodeEnum.FAIL.getCode();

    public UserException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public UserException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public UserException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public UserException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }
}
