package io.github.jwdeveloper.reflect.api.models;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public interface ConstructorModel {
    Constructor getConstructor();

    <T> T newInstance(Object... args) throws InvocationTargetException, IllegalAccessException, InstantiationException;

    <T> T tryNewInstance(Object... args);
}
