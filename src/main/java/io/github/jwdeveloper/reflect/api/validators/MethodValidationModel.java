package io.github.jwdeveloper.reflect.api.validators;

import io.github.jwdeveloper.reflect.api.matcher.ParameterMatcher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class MethodValidationModel extends ValidationModel {
    private Class<?> parentClass;
    private String superClass;
    private String returnType;
    private ParameterMatcher parameterMatcher = (a) -> {
        return a;
    };
    private List<String> generics = new ArrayList<>();
    private List<ParameterModel> parameterModels = new ArrayList<>();

    public void addGenerics(String name) {
        generics.add(name);
    }

    @Data
    @AllArgsConstructor
    public static class ParameterModel {
        private String type;
        private String name;
        private int index;
    }


}
