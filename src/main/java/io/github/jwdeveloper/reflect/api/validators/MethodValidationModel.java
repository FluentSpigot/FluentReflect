package io.github.jwdeveloper.reflect.api.validators;

import io.github.jwdeveloper.reflect.implementation.Visibility;
import io.github.jwdeveloper.reflect.api.matcher.ParameterMatcher;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MethodValidationModel
{
    private Class<?> parentClass;
    private String superClass;
    private String returnType;
    private String name;
    private Visibility visibility = Visibility.PUBLIC;
    private boolean isStatic;
    private boolean isAbstract;
    private boolean isFinal;
    private ParameterMatcher parameterMatcher =(a)-> {return a;};
    private List<String> generics = new ArrayList<>();
    private List<ParameterModel> parameterModels = new ArrayList<>();

    public void addGenerics(String name)
    {
        generics.add(name);
    }

    public static record ParameterModel(String type, String name, int index){};

    public boolean hasName()
    {
        return name != null;
    }

    public boolean hasReturnType()
    {
        return returnType != null;
    }
}
