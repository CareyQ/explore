package com.careyq.explore.common.util;

import lombok.experimental.UtilityClass;

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

}
