package io.github.jwdeveloper.reflect.api.validators;

public interface Validator<Input, Output>
{
   Output validate(Input input, String version) throws Exception;
}
