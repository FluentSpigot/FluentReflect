package io.github.jwdeveloper.reflect.implementation.validators;

import io.github.jwdeveloper.reflect.api.exceptions.ConstructorValidationException;
import io.github.jwdeveloper.reflect.api.exceptions.ValidationException;
import io.github.jwdeveloper.reflect.api.validators.ConstructorValidationModel;
import io.github.jwdeveloper.reflect.api.validators.ValidationResult;
import io.github.jwdeveloper.reflect.api.validators.Validator;
import io.github.jwdeveloper.reflect.implementation.Visibility;
import io.github.jwdeveloper.reflect.implementation.models.JavaConstructorModel;

import java.lang.reflect.*;


public class JavaConstructorValidator extends JavaValidator implements Validator<ConstructorValidationModel, JavaConstructorModel> {
    @Override
    public JavaConstructorModel validate(ConstructorValidationModel model, String version) throws ValidationException {
        var parent = model.getParentClass();
        var result = this.checkClasses(model, parent, Class::getDeclaredConstructors, this::validateMethod);
        if (!result.isValid()) {
            throw new ConstructorValidationException(model, version, result);
        }
        return new JavaConstructorModel(result.getValue(), model.getParameterMatcher());
    }


    private ValidationResult validateMethod(Constructor<?> constructor, ConstructorValidationModel model) {


        var visibilityResult = checkVisibility(constructor, model.getVisibility());
        if (!visibilityResult.isValid()) {
            return visibilityResult;
        }

        visibilityResult = checkParameters(constructor, model.getParameterModels());
        if (!visibilityResult.isValid()) {
            return visibilityResult;
        }

        visibilityResult = checkGenericParameters(constructor, model.getGenerics());
        if (!visibilityResult.isValid()) {
            return visibilityResult;
        }

        return new ValidationResult(true, constructor, "found");
    }

}
