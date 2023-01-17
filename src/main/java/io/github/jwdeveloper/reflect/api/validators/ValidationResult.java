package io.github.jwdeveloper.reflect.api.validators;

import java.lang.reflect.Method;


public record ValidationResult<T>(boolean isValid, T value, String message) {
}
