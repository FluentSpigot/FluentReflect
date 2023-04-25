package io.github.jwdeveloper.reflect.api.validators;

public interface Valitable<T, L> {

    public ValidationResult onValidation(T a, L b);
}
