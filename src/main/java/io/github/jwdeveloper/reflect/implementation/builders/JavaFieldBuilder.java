package io.github.jwdeveloper.reflect.implementation.builders;

import io.github.jwdeveloper.reflect.api.builders.FieldBuilder;
import io.github.jwdeveloper.reflect.api.builders.common.Buildable;
import io.github.jwdeveloper.reflect.api.matcher.ParameterMatcher;
import io.github.jwdeveloper.reflect.api.validators.FieldValidationModel;

import io.github.jwdeveloper.reflect.api.validators.ValidationResult;
import io.github.jwdeveloper.reflect.api.builders.common.Visibility;

import java.lang.reflect.Field;
import java.util.function.Consumer;

public class JavaFieldBuilder implements FieldBuilder, Buildable<FieldValidationModel> {

    private final FieldValidationModel model;

    public JavaFieldBuilder(Class<?> instance)
    {
        this.model = new FieldValidationModel();
        this.model.setParentClass(instance);
    }

    @Override
    public FieldBuilder withName(String name) {
        model.setName(name);
        return this;
    }

    @Override
    public FieldBuilder withStatic() {
        model.setStatic(true);
        return this;
    }

    @Override
    public FieldBuilder withType(Class<?> type) {
        model.setType(type.getName());
        return this;
    }

    @Override
    public FieldBuilder withType(String type) {
        model.setType(type);
        return this;
    }

    @Override
    public FieldBuilder withPackagePrivate() {
        model.setVisibility(Visibility.PACKET_PRIVATE);
        return this;
    }

    @Override
    public FieldBuilder withPublic() {
        model.setVisibility(Visibility.PUBLIC);
        return this;
    }

    @Override
    public FieldBuilder withPrivate() {
        model.setVisibility(Visibility.PRIVATE);
        return this;
    }

    @Override
    public FieldBuilder withProtected() {
        model.setVisibility(Visibility.PROTECTED);
        return this;
    }


    @Override
    public FieldBuilder withGenericType(String name) {
        model.getGenerics().add(name);
        return this;
    }



    @Override
    public FieldBuilder withParameterMatcher(ParameterMatcher matcher) {
        model.setParameterMatcher(matcher);
        return this;
    }

    @Override
    public FieldBuilder withFinal() {
        model.setFinal(true);
        return this;
    }

    @Override
    public FieldBuilder onFound(Consumer<ValidationResult<Field>> event)
    {
        model.setOnFound(event);
        return this;
    }

    @Override
    public FieldValidationModel build() {
        return model;
    }
}
