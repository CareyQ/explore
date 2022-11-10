package com.careyq.explore.common.util;

import lombok.experimental.UtilityClass;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

/**
 * 字符串工具类
 *
 * @author careyq
 * @since 2022-08-19
 */
@UtilityClass
public class DigestUtil {

    /**
     * 加密
     *
     * @param val 待加密字符串
     * @return 加密后的字符串
     */
    public String encrypt(String val) {
        if (StrUtil.isBlank(val)) {
            return StrUtil.EMPTY;
        }
        StringBuilder builder = new StringBuilder();
        byte[] bytes = val.getBytes(StandardCharsets.UTF_8);
        for (int i = 0; i < bytes.length; i++) {
            builder.append(bytes[i]).append(i);
        }
        return md5Hex(builder.toString());
    }

    /**
     * MD5 加密
     *
     * @param val 待加密字符串
     * @return 加密后的字符串
     */
    public String md5Hex(String val) {
        if (StrUtil.isBlank(val)) {
            return StrUtil.EMPTY;
        }
        return DigestUtils.md5DigestAsHex(val.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * MD5 加密 16 位
     *
     * @param val 待加密字符串
     * @return 加密后的字符串
     */
    public String md5Hex16(String val) {
        if (StrUtil.isBlank(val)) {
            return StrUtil.EMPTY;
        }
        return DigestUtils.md5DigestAsHex(val.getBytes(StandardCharsets.UTF_8)).substring(8, 24);
    }
}
