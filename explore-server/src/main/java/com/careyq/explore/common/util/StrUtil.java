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
public class StrUtil {

    public final String EMPTY = "";
    public final String COMMA = ",";
    public final String NULL = "null";
    public final String UNDEFINED = "undefined";

    /**
     * 是否为空字符串
     *
     * @param val 需要验证的字符串
     * @return 若为空，返回 true
     */
    public boolean isBlank(CharSequence val) {
        int length;
        if ((val == null) || (length = val.length()) == 0) {
            return true;
        }

        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(val.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 是否非空字符串
     *
     * @param val 需要验证的字符串
     * @return 若非空，返回 true
     */
    public boolean isNotBlank(CharSequence val) {
        return !isBlank(val);
    }

    /**
     * 加密
     *
     * @param val 待加密字符串
     * @return 加密后的字符串
     */
    public String encrypt(String val) {
        if (isBlank(val)) {
            return EMPTY;
        }
        StringBuilder builder = new StringBuilder();
        byte[] bytes = val.getBytes(StandardCharsets.UTF_8);
        for (int i = 0; i < bytes.length; i++) {
            builder.append(bytes[i]).append(i);
        }
        return DigestUtils.md5DigestAsHex(builder.toString().getBytes(StandardCharsets.UTF_8));
    }

    public static void main(String[] args) {

        /*String a = "\t\n";
        String b = " 1 ";
        String c = null;
        String d = "  ";

        System.out.println(isNotBlank(a));
        System.out.println(isNotBlank(b));
        System.out.println(isNotBlank(c));
        System.out.println(isBlank(d));*/

        System.out.println(encrypt("123456"));
    }
}
