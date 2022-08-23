package com.careyq.explore.common.util;

import lombok.experimental.UtilityClass;

import java.util.Collection;

/**
 * 集合工具类
 *
 * @author careyq
 * @since 2022-08-19
 */
@UtilityClass
public class CollUtil {

    /**
     * 集合是否为空
     *
     * @param val 集合
     * @return 若为空，返回 true
     */
    public static boolean isEmpty(Collection<?> val) {
        return (val == null) || val.isEmpty();
    }

    /**
     * 集合是否非空
     *
     * @param val 集合
     * @return 若非空，返回 true
     */
    public static boolean isNotEmpty(Collection<?> val) {
        return !isEmpty(val);
    }

}
