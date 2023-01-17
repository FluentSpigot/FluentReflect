package io.github.jwdeveloper.reflect.implementation.validators;

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
    public JavaMethodModel validate(MethodValidationModel model, String version) throws Exception {
        var parent = model.getParentClass();
        var result = this.checkClasses(model, parent, Class::getMethods, this::validateMethod);
        if (!result.isValid()) {
            throw new ValidationException("Method not found",result,parent);
        }
        return new JavaMethodModel(result.value(), model.getParameterMatcher());
    }


    private ValidationResult validateMethod(Method method, MethodValidationModel model) {

        if (model.getVisibility() == Visibility.PUBLIC && !Modifier.isPublic(method.getModifiers()))
            return new ValidationResult(false, method, "is not public");

        if (model.hasReturnType() && !model.getReturnType().equalsIgnoreCase(method.getReturnType().getName()))
            return new ValidationResult(false, method, "different return type");

        if (model.hasName() && !method.getName().equalsIgnoreCase(model.getName()))
            return new ValidationResult(false, method, "name difference");

        if (model.isStatic() && !Modifier.isStatic(method.getModifiers()))
            return new ValidationResult(false, method, "is not static");

        if (model.isAbstract() && !Modifier.isAbstract(method.getModifiers()))
            return new ValidationResult(false, method, "is not abstract");

        if (model.getVisibility() == Visibility.PROTECTED && !Modifier.isProtected(method.getModifiers()))
            return new ValidationResult(false, method, "is not protected");

        if (model.getVisibility() == Visibility.PRIVATE && !Modifier.isPrivate(method.getModifiers()))
            return new ValidationResult(false, method, "is not private");


        var numParameters = model.getParameterModels().size();

        if(numParameters > 0)
        {
            var parameters = method.getParameterTypes();
            if (numParameters != parameters.length)
                return new ValidationResult(false, method, "different parameter size");

            for (int i = 0; i < numParameters; i++) {
                if (!model.getParameterModels().get(i).type().equals(parameters[i].getTypeName()))
                    return new ValidationResult(false, method, "different type for parameter " + i);
            }
        }


        int numGenerics = model.getGenerics().size();
        if (numGenerics > 0) {
            Type[] types = ((ParameterizedType) method.getGenericReturnType()).getActualTypeArguments();

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
