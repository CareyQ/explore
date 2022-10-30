package com.careyq.explore.common.util;

import lombok.experimental.UtilityClass;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    public final String SLASH = "/";
    public final String BACKSLASH = "\\";
    public final String DOT = ".";
    public final String NULL = "null";
    public final String UNDEFINED = "undefined";
    public final String UNKNOWN = "unknown";
    public final String LOCALHOST = "127.0.0.1";

    /**
     * 是否为空字符，且为 unknown
     *
     * @param val 需要验证的字符串
     * @return 若为空，返回 true
     */
    public boolean isBlankOrUnknown(CharSequence val) {
        return !StrUtil.isNotBlank(val) || UNKNOWN.equalsIgnoreCase((String) val);
    }

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
     * 查找指定字符串中是否包含 字符串数组中的任意一个
     *
     * @param val      指定字符串
     * @param checkVal 需要检查的字符串数组
     * @return 是否包含
     */
    public boolean isContainsStr(CharSequence val, CharSequence... checkVal) {
        if (isBlank(val) || checkVal == null || checkVal.length == 0) {
            return false;
        }
        for (CharSequence checkStr : checkVal) {
            if (val.toString().contains(checkStr)) {
                return true;
            }
        }
        return false;
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
        return md5Hex(builder.toString());
    }

    /**
     * MD5 加密
     *
     * @param val 待加密字符串
     * @return 加密后的字符串
     */
    public String md5Hex(String val) {
        if (isBlank(val)) {
            return EMPTY;
        }
        return DigestUtils.md5DigestAsHex(val.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 将字符串按照指定分隔符，分割为集合
     *
     * @param val       待分割的字符串
     * @param separator 分隔符
     * @return 集合
     */
    public List<String> split(String val, String separator) {
        if (val == null) {
            return new ArrayList<>(0);
        }
        List<String> arrayList = new ArrayList<>();
        if (isBlank(separator)) {
            arrayList.add(val);
            return arrayList;
        }
        arrayList.addAll(Arrays.asList(val.split(separator)));
        return arrayList;
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
