package io.github.jwdeveloper.reflect.api.validators;

import io.github.jwdeveloper.reflect.api.exceptions.ValidationException;

public interface Validator<Input, Output>
{
   Output validate(Input input, String version) throws ValidationException;
}
