package io.github.jwdeveloper.reflect.implementation.models;

import io.github.jwdeveloper.reflect.api.matcher.ParameterMatcher;
import io.github.jwdeveloper.reflect.api.models.MethodModel;
import lombok.Getter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class JavaMethodModel extends MethodModel
{
    @Getter
    private final Method method;

    private final ParameterMatcher matcher;

    public JavaMethodModel(Method method, ParameterMatcher matcher)
    {
        this.method = method;
        this.matcher = matcher;
        method.setAccessible(true);
    }

    public <T> T invoke(Object object, Object ... args) throws InvocationTargetException, IllegalAccessException {
        args = matcher.onMatching(args);
        return (T)method.invoke(object, args);
    }

    public <T> T tryInvoke(Object object, Object ... args)
    {
        try {
            args = matcher.onMatching(args);
            return (T)method.invoke(object, args);
        }
        catch (Exception e)
        {
           return null;
        }
    }
}
