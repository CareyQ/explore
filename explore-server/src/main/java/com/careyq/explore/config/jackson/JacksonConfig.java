package com.careyq.explore.config.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * Jackson 配置
 * </p>
 *
 * @author CareyQ
 * @since 2022-08-21
 */
@Configuration
@ConditionalOnClass(ObjectMapper.class)
public class JacksonConfig {

}
