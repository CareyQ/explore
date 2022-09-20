package com.careyq.explore.config.jackson;

import com.careyq.explore.common.annotation.EnumName;
import com.careyq.explore.common.enums.IEnum;
import com.careyq.explore.common.util.StrUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.IOException;

/**
 * <p>
 * 枚举描述序列化
 * </p>
 *
 * @author CareyQ
 * @since 2022-09-18
 */
@AllArgsConstructor
@NoArgsConstructor
public class EnumSerialize extends JsonSerializer<Integer> implements ContextualSerializer {

    private Class<? extends IEnum> enumClass;

    @Override
    public void serialize(Integer value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeObject(value);
        String alias = jsonGenerator.getOutputContext().getCurrentName() + "Name";
        if (value == null) {
            jsonGenerator.writeString(StrUtil.EMPTY);
            return;
        }

        if (IEnum.noCode(enumClass, value)) {
            jsonGenerator.writeStringField(alias, "");
            return;
        }
        IEnum iEnum = IEnum.codeOf(enumClass, value);
        jsonGenerator.writeStringField(alias, iEnum.getDesc());
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        if (beanProperty != null) {
            EnumName enumName = beanProperty.getAnnotation(EnumName.class);
            if (enumName == null) {
                enumName = beanProperty.getContextAnnotation(EnumName.class);
            }
            if (enumName != null) {
                return new EnumSerialize(enumName.value());
            }
            return serializerProvider.findValueSerializer(beanProperty.getType(), beanProperty);
        }
        return serializerProvider.findNullValueSerializer(null);
    }
}
