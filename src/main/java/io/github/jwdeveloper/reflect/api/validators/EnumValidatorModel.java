package io.github.jwdeveloper.reflect.api.validators;

import io.github.jwdeveloper.reflect.api.builders.common.Visibility;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class EnumValidatorModel
{
    private Class<?> locationClass;
    private String superClass;
    private String name;
    private int valuesCount = -1;
    private Visibility visibility = Visibility.PUBLIC;

    public boolean hasName()
    {
        return name != null;
    }
}
