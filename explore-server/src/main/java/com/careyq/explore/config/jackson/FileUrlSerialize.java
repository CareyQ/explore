package com.careyq.explore.config.jackson;

import com.careyq.explore.common.annotation.FileUrl;
import com.careyq.explore.common.util.StrUtil;
import com.careyq.explore.common.util.SysUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;

import java.io.IOException;
import java.util.Objects;

/**
 * <p>
 * 文件 URL 序列化
 * </p>
 *
 * @author CareyQ
 * @since 2022-09-13
 */
public class FileUrlSerialize extends JsonSerializer<String> implements ContextualSerializer {

    @Override
    public void serialize(String value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (StrUtil.isBlank(value)) {
            jsonGenerator.writeString(value);
            return;
        }

        String fileUrl = SysUtil.getFileUrl(value);
        jsonGenerator.writeString(fileUrl);
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        if (beanProperty != null) {
            // 非 String 类直接跳过
            if (Objects.equals(beanProperty.getType().getRawClass(), String.class)) {
                FileUrl fileUrl = beanProperty.getAnnotation(FileUrl.class);
                if (fileUrl == null) {
                    fileUrl = beanProperty.getContextAnnotation(FileUrl.class);
                }
                if (fileUrl != null) {
                    return new FileUrlSerialize();
                }
            }
            return serializerProvider.findValueSerializer(beanProperty.getType(), beanProperty);
        }
        return serializerProvider.findNullValueSerializer(null);
    }
}
