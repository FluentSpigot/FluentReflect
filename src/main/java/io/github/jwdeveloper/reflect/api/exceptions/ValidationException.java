package io.github.jwdeveloper.reflect.api.exceptions;

import io.github.jwdeveloper.reflect.api.validators.ValidationResult;

public class ValidationException extends RuntimeException
{
        public ValidationException(String message, ValidationResult result, Class class_)
        {
            super(message+": "+result.message()+" for class: "+class_.getName());
        }
}
