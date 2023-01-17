package io.github.jwdeveloper.reflect.implementation.builders;

import io.github.jwdeveloper.reflect.api.builders.ClassBuilder;
import io.github.jwdeveloper.reflect.implementation.Visibility;
import io.github.jwdeveloper.reflect.api.builders.common.Buildable;
import io.github.jwdeveloper.reflect.api.validators.ClassValidationModel;

import java.util.Arrays;

public class JavaClassBuilder implements ClassBuilder, Buildable<ClassValidationModel>
{
    private ClassValidationModel model;

    public JavaClassBuilder()
    {
        model = new ClassValidationModel();
    }

    @Override
    public ClassBuilder withSuperClass(Class<?> _class) {
        return withSuperClass(_class.getName());
    }

    @Override
    public ClassBuilder withSuperClass(String className) {
        model.setSuperClass(className);
        return this;
    }

    @Override
    public ClassBuilder withInterface(Class<?>... _interfaces) {
        var names = Arrays.stream(_interfaces)
                .map(Class::getName)
                .toList()
                .toArray(new String[_interfaces.length]);
        return withInterface(names);
    }

    @Override
    public ClassBuilder withInterface(String... interfacesName) {
        model.setInterfaces(interfacesName);
        return this;
    }

    @Override
    public ClassBuilder withName(String name) {
        model.setName(name);
        return null;
    }

    @Override
    public ClassBuilder withStatic() {
        model.setStatic(true);
        return this;
    }

    @Override
    public ClassBuilder withPackagePrivate()
    {
        model.setVisibility(Visibility.PACKET_PRIVATE);
        return this;
    }

    @Override
    public ClassBuilder withPublic() {
        model.setVisibility(Visibility.PUBLIC);
        return this;
    }

    @Override
    public ClassBuilder withPrivate() {
        model.setVisibility(Visibility.PRIVATE);
        return this;
    }

    @Override
    public ClassBuilder withProtected() {
        model.setVisibility(Visibility.PROTECTED);
        return this;
    }


    @Override
    public ClassBuilder withGenericType(String name) {
        return null;
    }

    @Override
    public ClassValidationModel build()
    {
        return model;
    }

}
