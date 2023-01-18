package io.github.jwdeveloper.reflect.implementation.validators;

import io.github.jwdeveloper.reflect.api.exceptions.ClassValidationException;
import io.github.jwdeveloper.reflect.api.validators.ValidationResult;
import io.github.jwdeveloper.reflect.implementation.models.JavaClassModel;
import io.github.jwdeveloper.reflect.api.validators.Validator;
import io.github.jwdeveloper.reflect.api.validators.ClassValidationModel;

public class JavaClassValidator implements Validator<ClassValidationModel, JavaClassModel> {

    public JavaClassModel validate(ClassValidationModel model, String version) throws ClassValidationException{
        try
        {
            var clazz = Class.forName(model.getName(), false, this.getClass().getClassLoader());
            return new JavaClassModel(clazz,version);
        }
        catch (Exception e)
        {
            throw new ClassValidationException(model, version, new ValidationResult<Class<?>>(false,null,e.getMessage()));
        }
    }

}
