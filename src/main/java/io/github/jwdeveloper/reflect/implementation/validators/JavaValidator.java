package io.github.jwdeveloper.reflect.implementation.validators;

import io.github.jwdeveloper.reflect.api.validators.MethodValidationModel;
import io.github.jwdeveloper.reflect.api.validators.ValidationModel;
import io.github.jwdeveloper.reflect.api.validators.ValidationResult;
import io.github.jwdeveloper.reflect.api.validators.Valitable;
import io.github.jwdeveloper.reflect.implementation.Visibility;

import java.lang.reflect.Executable;
import java.lang.reflect.Member;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.List;
import java.util.function.Function;

public class JavaValidator {


    public <Values, Model> ValidationResult<Values> checkClasses(
            Model modelToCheck,
            Class<?> classToCheck,
            Function<Class<?>, Values[]> supplier,
            Valitable<Values, Model> onValidation) {
        var logs = new HashSet<ValidationResult<Values>>();
        while (classToCheck != Object.class) {
            var values = supplier.apply(classToCheck);
            for (var value : values) {
                var result = onValidation.onValidation(value, modelToCheck);
                logs.add(result);
                if (result.isValid()) {
                    return new ValidationResult(true, result.value, "not found", logs);
                }
            }
            classToCheck = classToCheck.getSuperclass();
        }
        return new ValidationResult(false, null, "not found", logs);
    }

    protected <T extends Member> ValidationResult checkVisibility(T value, Visibility visibility) {
        if (visibility == Visibility.PUBLIC && !Modifier.isPublic(value.getModifiers()))
            return new ValidationResult(false, value, "is not public");

        if (visibility == Visibility.PROTECTED && !Modifier.isProtected(value.getModifiers()))
            return new ValidationResult(false, value, "is not protected");

        if (visibility == Visibility.PRIVATE && !Modifier.isPrivate(value.getModifiers()))
            return new ValidationResult(false, value, "is not private");

        return new ValidationResult(true, value, "valid");
    }

    protected <T extends Member, M extends ValidationModel> ValidationResult checkModifiers(T value, M model)
    {
        if (model.isStatic() && !Modifier.isStatic(value.getModifiers()))
            return new ValidationResult(false, value, "is not static");

        if (model.isAbstract() && !Modifier.isAbstract(value.getModifiers()))
            return new ValidationResult(false, value, "is not abstract");

        return new ValidationResult(true, value, "valid");
    }

    protected <T extends Executable> ValidationResult checkParameters(T value, List<MethodValidationModel.ParameterModel> parameters) {
        var numParameters = parameters.size();
        if(numParameters == 0)
        {
            return new ValidationResult(true, value, "valid");
        }
        var valueParameters = value.getParameterTypes();

        if (numParameters != valueParameters.length)
            return new ValidationResult(false, value, "different parameter size");

        for (int i = 0; i < numParameters; i++) {
            if (!parameters.get(i).getType().equals(valueParameters[i].getTypeName()))
                return new ValidationResult(false, value, "different type for parameter " + i);
        }
        return new ValidationResult(true, value, "valid");
    }

    protected  <T extends Executable> ValidationResult checkGenericParameters(T value, List<String> genericParameters)
    {
        int numGenerics = genericParameters.size();
        if (numGenerics > 0) {
            Type[] types = value.getGenericParameterTypes();

            if (types.length < numGenerics)
                return new ValidationResult(false, value, "different generic parameter size");

            for (int i = 0; i < numGenerics; i++) {
                if (!genericParameters.get(i).equals(((Class<?>) types[i]).getTypeName()))
                    return new ValidationResult(false, value, "different name for parameter " + i);
            }
        }
        return new ValidationResult(true, value, "valid");
    }
}
