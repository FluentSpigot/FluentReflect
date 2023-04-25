package io.github.jwdeveloper.reflect.api.validators;

import io.github.jwdeveloper.reflect.api.builders.common.Visibility;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ClassValidationModel {

    private String version;
    private Object parent;
    private String superClass;
    private String name;
    private Visibility visibility = Visibility.PUBLIC;
    private String[] interfaces;
    private boolean isStatic;
    private Map<String,String> generics = new HashMap<>();
}
