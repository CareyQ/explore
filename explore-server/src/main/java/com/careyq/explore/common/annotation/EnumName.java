package com.careyq.explore.common.annotation;

import com.careyq.explore.common.enums.IEnum;
import com.careyq.explore.config.jackson.EnumSerialize;
import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * 枚举描述注解，自动添加枚举描述字段
 * </p>
 *
 * @author CareyQ
 * @since 2022-09-18
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@JacksonAnnotationsInside
@JsonSerialize(using = EnumSerialize.class)
public @interface EnumName {

    /**
     * 枚举类
     */
    Class<? extends IEnum> value();
}
