package io.github.jwdeveloper.reflect.api.exceptions;

import io.github.jwdeveloper.reflect.api.validators.ConstructorValidationModel;
import io.github.jwdeveloper.reflect.api.validators.FieldValidationModel;
import io.github.jwdeveloper.reflect.api.validators.ValidationResult;
import lombok.Getter;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;


@Getter
public class FieldValidationException extends ValidationException
{
    private final FieldValidationModel model;
    private final String version;
    private final ValidationResult<Field> validationResult;

    public FieldValidationException(FieldValidationModel model, String version, ValidationResult<Field> validationResult)
    {
        super("Constructor validation error");
        this.model = model;
        this.version = version;
        this.validationResult = validationResult;
    }
}
