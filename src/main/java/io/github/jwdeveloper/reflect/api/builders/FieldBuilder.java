package io.github.jwdeveloper.reflect.api.builders;

import io.github.jwdeveloper.reflect.api.builders.common.*;
import io.github.jwdeveloper.reflect.api.validators.ValidationResult;

import java.lang.reflect.Field;
import java.util.function.Consumer;


public interface FieldBuilder extends
        Typeable<FieldBuilder>,
        StaticAble<FieldBuilder>,
        Visbilityable<FieldBuilder>,
        Nameable<FieldBuilder>,
        Finalable<FieldBuilder>,
        Genericable<FieldBuilder>,
        Matchable<FieldBuilder>
{
     FieldBuilder onFound(Consumer<ValidationResult<Field>> event);
}
