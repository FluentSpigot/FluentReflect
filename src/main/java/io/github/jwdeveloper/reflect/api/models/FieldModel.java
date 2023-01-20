package io.github.jwdeveloper.reflect.api.models;

public interface FieldModel {
    Object getValue(Object object) throws IllegalAccessException;

    Object tryGetValue(Object object);

    void setValue(Object object, Object value) throws IllegalAccessException;

    void trySetValue(Object object, Object value);
}
