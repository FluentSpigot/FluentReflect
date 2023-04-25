package io.github.jwdeveloper.reflect.api.models;

import java.lang.reflect.Field;

public interface FieldModel {

    Field getField();
    Object getValue(Object object) throws IllegalAccessException;

    Object tryGetValue(Object object);

    void setValue(Object object, Object value) throws IllegalAccessException;

    void trySetValue(Object object, Object value);
}
