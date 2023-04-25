package io.github.jwdeveloper.reflect.api.validators;

import lombok.Getter;

import java.util.HashSet;
import java.util.Set;


@Getter
public class ValidationResult<T> {
    public final boolean isValid;
    public final T value;
    public final String message;
    public final Set<ValidationResult<T>> logs;

    public ValidationResult(boolean isValid, T value, String message) {
        this(isValid, value, message, new HashSet<>());
    }

    public ValidationResult(boolean isValid, T value, String message, Set<ValidationResult<T>> logs) {
        this.isValid = isValid;
        this.value = value;
        this.message = message;
        this.logs = logs;
    }
}
