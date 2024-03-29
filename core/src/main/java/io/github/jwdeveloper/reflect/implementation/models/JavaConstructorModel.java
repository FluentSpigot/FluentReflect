package io.github.jwdeveloper.reflect.implementation.models;

import io.github.jwdeveloper.reflect.api.matcher.ParameterMatcher;
import io.github.jwdeveloper.reflect.api.models.ConstructorModel;
import lombok.Getter;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class JavaConstructorModel implements ConstructorModel {
    @Getter
    private final Constructor constructor;

    private final ParameterMatcher matcher;

    public JavaConstructorModel(Constructor constructor, ParameterMatcher matcher) {
        this.constructor = constructor;
        this.matcher = matcher;
        constructor.setAccessible(true);
    }

    public <T> T newInstance(Object... args) throws InvocationTargetException, IllegalAccessException, InstantiationException {
        args = matcher.onMatching(args);
        return (T) constructor.newInstance(args);
    }


    public <T> T tryNewInstance(Object... args) {
        try {
            return newInstance(args);
        } catch (Exception e) {
          return null;
        }
    }
}
