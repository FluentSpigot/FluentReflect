package io.github.jwdeveloper.reflect.api.exceptions;

import io.github.jwdeveloper.reflect.api.validators.ConstructorValidationModel;
import io.github.jwdeveloper.reflect.api.validators.ValidationResult;
import lombok.Getter;

import java.lang.reflect.Constructor;

@Getter
public class ConstructorValidationException extends ValidationException
{
    private final ConstructorValidationModel model;
    private final String version;
    private final ValidationResult<Constructor<?>> validationResult;

    public ConstructorValidationException(ConstructorValidationModel model, String version, ValidationResult<Constructor<?>> validationResult)
    {
        super("Constructor validation error");
        this.model = model;
        this.version = version;
        this.validationResult = validationResult;
    }
}
