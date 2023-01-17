package io.github.jwdeveloper.reflect.api.validators;

import io.github.jwdeveloper.reflect.api.matcher.ParameterMatcher;
import io.github.jwdeveloper.reflect.implementation.Visibility;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class FieldValidationModel
{
    private Class<?> parentClass;
    private String type;
    private String name;
    private Visibility visibility = Visibility.PUBLIC;
    private boolean isStatic;
    private boolean isAbstract;
    private boolean isFinal;
    private ParameterMatcher parameterMatcher =(a)-> {return a;};
    private List<String> generics = new ArrayList<>();


    public boolean hasName()
    {
        return name != null;
    }
}
