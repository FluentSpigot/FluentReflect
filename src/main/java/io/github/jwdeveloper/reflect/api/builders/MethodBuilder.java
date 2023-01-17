package io.github.jwdeveloper.reflect.api.builders;

import io.github.jwdeveloper.reflect.api.builders.common.*;

import java.util.function.Consumer;

public interface MethodBuilder extends
        Typeable<MethodBuilder>,
        StaticAble<MethodBuilder>,
        Visbilityable<MethodBuilder>,
        Nameable<MethodBuilder>,
        Finalable<MethodBuilder>,
        ConstructorBuilder<MethodBuilder>
{

}
