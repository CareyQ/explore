package com.careyq.explore.common.vo;

import com.careyq.explore.common.enums.ResultCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 通用返回类
 *
 * @author careyq
 * @since 2022-08-19
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Result<T> {

    /**
     * 结果标识
     */
    private Integer code;

    /**
     * 系统消息
     */
    private String msg;

    /**
     * 显示消息
     */
    private String showMsg;

    /**
     * 数据
     */
    private T data;

    public Result(ResultCodeEnum codeEnum, String msg, String showMsg) {
        this.code = codeEnum.getCode();
        this.msg = msg;
        this.showMsg = showMsg;
    }

    /**
     * 成功 - 默认
     */
    public static <T> Result<T> ok() {
        ResultCodeEnum okResult = ResultCodeEnum.SUCCESS;
        return Result.<T>builder()
                .code(okResult.getCode())
                .showMsg(okResult.getDesc())
                .build();
    }

    /**
     * 成功 - 自定义显示消息
     */
    public static <T> Result<T> success(String showMsg) {
        return Result.<T>ok().setShowMsg(showMsg);
    }

    /**
     * 成功 - 携带数据
     */
    public static <T> Result<T> success(T data) {
        return Result.<T>ok().setData(data);
    }

    /**
     * 成功 - 携带数据，自定义显示消息
     */
    public static <T> Result<T> success(T data, String showMsg) {
        return Result.<T>ok().setData(data).setShowMsg(showMsg);
    }

    /**
     * 失败 - 默认
     */
    public static <T> Result<T> fail() {
        ResultCodeEnum failResult = ResultCodeEnum.FAIL;
        return Result.<T>builder()
                .code(failResult.getCode())
                .showMsg(failResult.getDesc())
                .build();
    }

    /**
     * 失败 - 自定义显示消息
     */
    public static <T> Result<T> fail(String showMsg) {
        return Result.<T>fail().setShowMsg(showMsg);
    }

    /**
     * 失败 - 自定义状态码、显示消息
     */
    public static <T> Result<T> fail(Integer code, String showMsg) {
        return Result.<T>fail().setCode(code).setShowMsg(showMsg);
    }

    /**
     * 失败 - 自定义状态码、显示消息
     */
    public static <T> Result<T> fail(String showMsg, String msg) {
        return Result.<T>fail().setCode(ResultCodeEnum.FAIL.getCode()).setShowMsg(showMsg).setMsg(msg);
    }

    /**
     * 失败 - 携带数据，自定义显示消息
     */
    public static <T> Result<T> fail(T data, String showMsg) {
        return Result.<T>fail().setData(data).setShowMsg(showMsg);
    }

    /**
     * 用户未登录
     */
    public static Result<Object> unLogin() {
        ResultCodeEnum unLogin = ResultCodeEnum.UN_LOGIN;
        return Result.builder()
                .code(unLogin.getCode())
                .showMsg(unLogin.getDesc())
                .msg("invalid ticket")
                .build();
    }
}
