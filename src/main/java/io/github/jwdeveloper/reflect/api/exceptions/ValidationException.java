package io.github.jwdeveloper.reflect.api.exceptions;

import io.github.jwdeveloper.reflect.api.validators.ValidationResult;

public class ValidationException extends RuntimeException {



    public ValidationException(String message) {
        super(message);

    }
}
