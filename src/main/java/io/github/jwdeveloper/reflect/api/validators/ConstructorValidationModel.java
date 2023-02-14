package io.github.jwdeveloper.reflect.api.validators;

import io.github.jwdeveloper.reflect.api.matcher.ParameterMatcher;
import io.github.jwdeveloper.reflect.implementation.Visibility;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ConstructorValidationModel
{
    private Class<?> parentClass;
    private Visibility visibility = Visibility.PUBLIC;
    private int parameterCount;
    private ParameterMatcher parameterMatcher =(a)-> {return a;};
    private List<String> generics = new ArrayList<>();
    private List<MethodValidationModel.ParameterModel> parameterModels = new ArrayList<>();
}
