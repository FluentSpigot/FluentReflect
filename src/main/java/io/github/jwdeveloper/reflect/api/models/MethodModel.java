package io.github.jwdeveloper.reflect.api.models;

import java.lang.reflect.InvocationTargetException;

public interface MethodModel {
    <T> T invoke(Object object, Object... args) throws InvocationTargetException, IllegalAccessException;

    <T> T tryInvoke(Object object, Object... args);
}
