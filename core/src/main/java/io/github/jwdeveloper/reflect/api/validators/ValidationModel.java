package io.github.jwdeveloper.reflect.api.validators;

import io.github.jwdeveloper.reflect.api.builders.common.Visibility;
import lombok.Data;

@Data
public class ValidationModel
{
    private String type;
    private String name;
    private Visibility visibility = Visibility.PUBLIC;
    private boolean isStatic;
    private boolean isAbstract;
    private boolean isFinal;


    public boolean hasName()
    {
        return name != null;
    }

    public boolean hasReturnType()
    {
        return type != null;
    }
}
