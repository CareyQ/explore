package com.careyq.explore.config.jackson;

import com.careyq.explore.common.util.date.DatePattern;
import com.fasterxml.jackson.core.json.PackageVersion;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.TimeZone;

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

    @Bean
    public ObjectMapper objectMapper(ApplicationContext applicationContext) {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.applicationContext(applicationContext);
        builder.locale(Locale.CHINA);
        builder.simpleDateFormat(DatePattern.NORM_DATETIME_PATTERN);
        builder.timeZone(TimeZone.getTimeZone(ZoneId.systemDefault()));
        builder.modules(new CustomModule());
        return builder.createXmlMapper(false).build();
    }

    public static class CustomModule extends SimpleModule {
        public CustomModule() {
            super(PackageVersion.VERSION);
            this.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN)));
        }
    }
}
