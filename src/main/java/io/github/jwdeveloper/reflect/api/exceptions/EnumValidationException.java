package io.github.jwdeveloper.reflect.api.exceptions;

import io.github.jwdeveloper.reflect.api.validators.EnumValidatorModel;
import io.github.jwdeveloper.reflect.api.validators.ValidationResult;
import lombok.Getter;

@Getter
public class EnumValidationException extends ValidationException {

    private final EnumValidatorModel model;
    private final String version;
    private final ValidationResult<Class<?>> validationResult;


    public EnumValidationException(EnumValidatorModel model, String version, ValidationResult<Class<?>> validationResult) {
        super("Enum not found");
        this.model = model;
        this.version = version;
        this.validationResult = validationResult;
    }
}
