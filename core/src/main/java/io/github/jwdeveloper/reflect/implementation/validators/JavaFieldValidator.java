package io.github.jwdeveloper.reflect.implementation.validators;

import io.github.jwdeveloper.reflect.api.exceptions.FieldValidationException;
import io.github.jwdeveloper.reflect.api.exceptions.ValidationException;
import io.github.jwdeveloper.reflect.api.validators.FieldValidationModel;
import io.github.jwdeveloper.reflect.api.validators.ValidationResult;
import io.github.jwdeveloper.reflect.api.validators.Validator;
import io.github.jwdeveloper.reflect.api.builders.common.Visibility;
import io.github.jwdeveloper.reflect.implementation.models.JavaFieldModel;


import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


public class JavaFieldValidator extends JavaValidator implements Validator<FieldValidationModel, JavaFieldModel> {
    @Override
    public JavaFieldModel validate(FieldValidationModel model, String version) throws ValidationException {
        var parent = model.getParentClass();
        try
        {
            var clazz = Class.forName(model.getType(), false, this.getClass().getClassLoader());
            model.setClassType(clazz);
        }
        catch (Exception e)
        {
            throw new ValidationException("Class for Field type not exists "+model.getType()+" "+e.getMessage());
        }

        var validation = this.checkClasses(model, parent, Class::getDeclaredFields, this::validateField);
        if (!validation.isValid()) {
            throw new FieldValidationException(model, version, validation);
        }
        if(model.getOnFound() != null)
        {
            model.getOnFound().accept(validation);
        }
        return new JavaFieldModel(validation.getValue());
    }


    private ValidationResult validateField(Field field, FieldValidationModel model) {
        if (model.getVisibility() == Visibility.PUBLIC && !Modifier.isPublic(field.getModifiers()))
            return new ValidationResult(false, field, "is not public");

        if (model.hasType() && !field.getType().isAssignableFrom(model.getClassType()))
            return new ValidationResult(false, field, "different return type");

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
            Type genericType = field.getGenericType();


            if (!(genericType instanceof ParameterizedType))
                return new ValidationResult(false, field, "not generic type");

            Type[] types = ((ParameterizedType) genericType).getActualTypeArguments();


            if (types.length < numGenerics)
                return new ValidationResult(false, field, "not enough types parameters");

            // Type parameters need to match in sequence
            for (int i = 0; i < numGenerics; i++) {
                if (!model.getGenerics().get(i).matches(unwrapType(types[i]).getName()))
                    return new ValidationResult(false, field, "Different parameters need to match in sequence");
            }
        }

        return new ValidationResult(true, field, "found");
    }


    private Class<?> unwrapType(Type type) {
        if (type instanceof Class)
            return (Class<?>) type;

        if (type instanceof ParameterizedType)
            return unwrapType(((ParameterizedType) type).getRawType());

       // throw new IllegalStateException("Cannot unwrap type of class=" + type.getClass());
        return Class.class;
    }
}
