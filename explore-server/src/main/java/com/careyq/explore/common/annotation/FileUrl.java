package com.careyq.explore.common.annotation;

import com.careyq.explore.config.jackson.FileUrlSerialize;
import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * 文件 URL 注解，自动拼接域名
 * </p>
 *
 * @author CareyQ
 * @since 2022-09-13
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@JacksonAnnotationsInside
@JsonSerialize(using = FileUrlSerialize.class)
public @interface FileUrl {
}
