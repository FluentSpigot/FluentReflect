package io.github.jwdeveloper.reflect.implementation.validators;

import io.github.jwdeveloper.reflect.api.exceptions.ValidationException;
import io.github.jwdeveloper.reflect.api.validators.ConstructorValidationModel;
import io.github.jwdeveloper.reflect.api.validators.ValidationResult;
import io.github.jwdeveloper.reflect.api.validators.Validator;
import io.github.jwdeveloper.reflect.implementation.Visibility;
import io.github.jwdeveloper.reflect.implementation.models.JavaConstructorModel;
import java.lang.reflect.*;


public class JavaConstructorValidator  extends JavaValidator implements Validator<ConstructorValidationModel, JavaConstructorModel> {
    @Override
    public JavaConstructorModel validate(ConstructorValidationModel model, String version) throws Exception {
        var parent = model.getParentClass();
        var result = this.checkClasses(model, parent, Class::getDeclaredConstructors, this::validateMethod);
        if (!result.isValid()) {
            throw new ValidationException("Constructor not found",result,parent);
        }
        return new JavaConstructorModel(result.value(), model.getParameterMatcher());
    }


    private ValidationResult validateMethod(Constructor<?> method, ConstructorValidationModel model) {

        if (model.getVisibility() == Visibility.PUBLIC && !Modifier.isPublic(method.getModifiers()))
            return new ValidationResult(false, method, "is not public");

        if (model.getVisibility() == Visibility.PROTECTED && !Modifier.isProtected(method.getModifiers()))
            return new ValidationResult(false, method, "is not protected");

        if (model.getVisibility() == Visibility.PRIVATE && !Modifier.isPrivate(method.getModifiers()))
            return new ValidationResult(false, method, "is not private");


        var numParameters = model.getParameterModels().size();
        var parameters = method.getParameterTypes();

        if (numParameters != parameters.length)
            return new ValidationResult(false, method, "different parameter size");

        for (int i = 0; i < numParameters; i++) {
            if (!model.getParameterModels().get(i).type().equals(parameters[i].getTypeName()))
                return new ValidationResult(false, method, "different type for parameter " + i);
        }

        int numGenerics = model.getGenerics().size();
        if (numGenerics > 0) {
            Type[] types = method.getGenericParameterTypes();

            if (types.length < numGenerics)
                return new ValidationResult(false, method, "different generic parameter size");

            for (int i = 0; i < numGenerics; i++) {
                if (!model.getGenerics().get(i).equals(((Class<?>) types[i]).getTypeName()))
                    return new ValidationResult(false, method, "different name for parameter " + i);
            }
        }

        return new ValidationResult(true, method, "found");
    }

}
