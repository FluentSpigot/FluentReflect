package io.github.jwdeveloper.reflect.api.validators;

import io.github.jwdeveloper.reflect.api.validators.ValidationResult;

public interface Valitable<T, L> {

    public ValidationResult onValidation(T a, L b);
}
