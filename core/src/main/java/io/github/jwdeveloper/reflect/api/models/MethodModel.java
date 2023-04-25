package io.github.jwdeveloper.reflect.api.models;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public interface MethodModel {

    Method getMethod();

    <T> T invoke(Object object, Object... args) throws InvocationTargetException, IllegalAccessException;

    <T> T tryInvoke(Object object, Object... args);
}
