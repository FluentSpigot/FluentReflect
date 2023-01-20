package io.github.jwdeveloper.reflect.api.models;

import java.lang.reflect.InvocationTargetException;

public interface ConstructorModel
{
    public <T> T newInstance(Object... args) throws InvocationTargetException, IllegalAccessException, InstantiationException;

    public <T> T tryNewInstance(Object... args);
}
