package io.github.jwdeveloper.reflect.api.validators;

import io.github.jwdeveloper.reflect.implementation.Visibility;
import io.github.jwdeveloper.reflect.api.matcher.ParameterMatcher;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MethodValidationModel extends ValidationModel
{
    private Class<?> parentClass;
    private String superClass;
    private String returnType;
    private ParameterMatcher parameterMatcher =(a)-> {return a;};
    private List<String> generics = new ArrayList<>();
    private List<ParameterModel> parameterModels = new ArrayList<>();

    public void addGenerics(String name)
    {
        generics.add(name);
    }

    public static record ParameterModel(String type, String name, int index){};


}
