package io.github.jwdeveloper.reflect.implementation.validators;

import io.github.jwdeveloper.reflect.api.exceptions.FieldValidationException;
import io.github.jwdeveloper.reflect.api.exceptions.MethodValidationException;
import io.github.jwdeveloper.reflect.api.exceptions.ValidationException;
import io.github.jwdeveloper.reflect.implementation.Visibility;
import io.github.jwdeveloper.reflect.implementation.models.JavaMethodModel;
import io.github.jwdeveloper.reflect.api.validators.MethodValidationModel;
import io.github.jwdeveloper.reflect.api.validators.ValidationResult;
import io.github.jwdeveloper.reflect.api.validators.Validator;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class JavaMethodValidator extends JavaValidator implements Validator<MethodValidationModel, JavaMethodModel> {


    @Override
    public JavaMethodModel validate(MethodValidationModel model, String version) throws ValidationException {
        var parent = model.getParentClass();
        var result = this.checkClasses(model, parent, Class::getMethods, this::validateMethod);
        if (!result.isValid()) {
            throw new MethodValidationException(model,version,result);
        }
        return new JavaMethodModel(result.getValue(), model.getParameterMatcher());
    }


    private ValidationResult validateMethod(Method method, MethodValidationModel model) {


        if (model.hasReturnType() && !model.getReturnType().equalsIgnoreCase(method.getReturnType().getName()))
            return new ValidationResult(false, method, "different return type");

        if (model.hasName() && !method.getName().equalsIgnoreCase(model.getName()))
            return new ValidationResult(false, method, "name difference");


        var visibilityResult = checkVisibility(method, model.getVisibility());
        if (!visibilityResult.isValid()) {
            return visibilityResult;
        }

        visibilityResult = checkModifiers(method, model);
        if (!visibilityResult.isValid()) {
            return visibilityResult;
        }

        visibilityResult = checkParameters(method, model.getParameterModels());
        if (!visibilityResult.isValid()) {
            return visibilityResult;
        }

        visibilityResult = checkGenericParameters(method, model.getGenerics());
        if (!visibilityResult.isValid()) {
            return visibilityResult;
        }

        return new ValidationResult(true, method, "found");
    }


}
