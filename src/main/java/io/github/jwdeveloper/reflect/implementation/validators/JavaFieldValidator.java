package io.github.jwdeveloper.reflect.implementation.validators;

import io.github.jwdeveloper.reflect.api.exceptions.ValidationException;
import io.github.jwdeveloper.reflect.api.validators.FieldValidationModel;
import io.github.jwdeveloper.reflect.api.validators.ValidationResult;
import io.github.jwdeveloper.reflect.api.validators.Validator;
import io.github.jwdeveloper.reflect.implementation.Visibility;
import io.github.jwdeveloper.reflect.implementation.models.JavaFieldModel;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


public class JavaFieldValidator extends JavaValidator implements Validator<FieldValidationModel, JavaFieldModel> {
    @Override
    public JavaFieldModel validate(FieldValidationModel model, String version) throws Exception {
        var parent = model.getParentClass();
        var result = this.checkClasses(model, parent, Class::getDeclaredFields, this::validateField);
        if (!result.isValid()) {
            throw new ValidationException("Field not found",result,parent);

        }
        return new JavaFieldModel(result.value());
    }


    private ValidationResult validateField(Field field, FieldValidationModel model) {
        if (model.getVisibility() == Visibility.PUBLIC && !Modifier.isPublic(field.getModifiers()))
            return new ValidationResult(false, field, "is not public");

        if (model.hasName() && !field.getName().equalsIgnoreCase(model.getName()))
            return new ValidationResult(false, field, "name difference");

        if (model.isStatic() && !Modifier.isStatic(field.getModifiers()))
            return new ValidationResult(false, field, "is not static");

        if (model.getVisibility() == Visibility.PROTECTED && !Modifier.isProtected(field.getModifiers()))
            return new ValidationResult(false, field, "is not protected");

        if (model.getVisibility() == Visibility.PRIVATE && !Modifier.isPrivate(field.getModifiers()))
            return new ValidationResult(false, field, "is not private");


        int numGenerics = model.getGenerics().size();
        if (numGenerics > 0) {
            Type[] types = ((ParameterizedType) field.getGenericType()).getActualTypeArguments();

            if (types.length < numGenerics)
                return new ValidationResult(false, field, "different generic parameter size");

            for (int i = 0; i < numGenerics; i++) {
                if (!model.getGenerics().get(i).equals(((Class<?>) types[i]).getTypeName()))
                    return new ValidationResult(false, field, "different name for parameter " + i);
            }
        }

        return new ValidationResult(true, field, "found");
    }
}
