package io.github.jwdeveloper.reflect.api.exceptions;

import io.github.jwdeveloper.reflect.api.validators.ClassValidationModel;
import io.github.jwdeveloper.reflect.api.validators.ValidationResult;
import lombok.Getter;


@Getter
public class ClassValidationException extends ValidationException
{
    private final ClassValidationModel model;
    private final String version;
    private final ValidationResult<Class<?>> validationResult;

    public ClassValidationException(ClassValidationModel model, String version, ValidationResult<Class<?>> validationResult)
    {
        super("Class validation error");
        this.model = model;
        this.version = version;
        this.validationResult = validationResult;
    }
}
