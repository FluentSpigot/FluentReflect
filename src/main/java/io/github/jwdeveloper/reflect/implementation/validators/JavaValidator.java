package io.github.jwdeveloper.reflect.implementation.validators;

import io.github.jwdeveloper.reflect.api.validators.ValidationResult;
import io.github.jwdeveloper.reflect.api.validators.Valitable;

import java.util.HashSet;
import java.util.List;
import java.util.function.Function;

public class JavaValidator {


    public <Values, Model> ValidationResult<Values> checkClasses(
            Model modelToCheck,
            Class<?> classToCheck,
            Function<Class<?>, Values[]> supplier,
            Valitable<Values, Model> onValidation) {
        var logs = new HashSet<ValidationResult>();
        while (classToCheck != Object.class) {
            var methods = supplier.apply(classToCheck);
            for (var a : methods) {
                var result = onValidation.onValidation(a, modelToCheck);
                if (result.isValid()) {
                    return result;
                }
                logs.add(result);
            }
            classToCheck = classToCheck.getSuperclass();
        }
        return new ValidationResult(false,null,"not found",logs);
    }
}
