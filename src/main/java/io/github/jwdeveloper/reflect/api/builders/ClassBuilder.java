package io.github.jwdeveloper.reflect.api.builders;

import io.github.jwdeveloper.reflect.api.builders.common.*;

public interface ClassBuilder extends
        StaticAble<ClassBuilder>,
        Visbilityable<ClassBuilder>,
        Nameable<ClassBuilder>,
        Genericable<ClassBuilder>
{

    ClassBuilder withSuperClass(Class<?> _class);
    ClassBuilder withSuperClass(String className);

    ClassBuilder withInterface(Class<?> ... _interfaces);
    ClassBuilder withInterface(String ... interfacesName);
}
