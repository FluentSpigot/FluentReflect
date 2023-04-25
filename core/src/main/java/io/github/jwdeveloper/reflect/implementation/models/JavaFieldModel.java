package io.github.jwdeveloper.reflect.implementation.models;

import io.github.jwdeveloper.reflect.api.models.FieldModel;
import lombok.Getter;

import java.lang.reflect.Field;

public class JavaFieldModel implements FieldModel {
    @Getter
    private final Field field;

    public JavaFieldModel(Field field) {
        this.field = field;
        field.setAccessible(true);
    }

    public Object getValue(Object object) throws IllegalAccessException {
        return field.get(object);
    }

    public Object tryGetValue(Object object) {
        try {
            return getValue(object);
        } catch (Exception e) {
            return null;
        }
    }

    public void setValue(Object object, Object value) throws IllegalAccessException {
        field.set(object, value);
    }

    public void trySetValue(Object object, Object value) {
        try {
            setValue(object, value);
        } catch (Exception e) {
            return;
        }
    }
}
