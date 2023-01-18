package io.github.jwdeveloper.reflect.api.exceptions;

import io.github.jwdeveloper.reflect.api.validators.FieldValidationModel;
import io.github.jwdeveloper.reflect.api.validators.MethodValidationModel;
import io.github.jwdeveloper.reflect.api.validators.ValidationResult;
import lombok.Getter;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


@Getter
public class MethodValidationException extends ValidationException
{
    private final MethodValidationModel model;
    private final String version;
    private final ValidationResult<Method> validationResult;

    public MethodValidationException(MethodValidationModel model, String version, ValidationResult<Method> validationResult)
    {
        super("Constructor validation error");
        this.model = model;
        this.version = version;
        this.validationResult = validationResult;
    }
}
