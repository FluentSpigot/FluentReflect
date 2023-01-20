package io.github.jwdeveloper.reflect.api.validators;

import io.github.jwdeveloper.reflect.api.matcher.ParameterMatcher;
import io.github.jwdeveloper.reflect.implementation.Visibility;
import lombok.Data;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Data
public class FieldValidationModel extends ValidationModel
{
    private Class<?> parentClass;
    private Class<?> classType;

    private Consumer<ValidationResult<Field>> onFound;
    private ParameterMatcher parameterMatcher =(a)-> {return a;};
    private List<String> generics = new ArrayList<>();
}
