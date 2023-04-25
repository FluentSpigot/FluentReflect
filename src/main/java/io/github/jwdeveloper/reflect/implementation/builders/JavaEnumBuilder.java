package io.github.jwdeveloper.reflect.implementation.builders;

import io.github.jwdeveloper.reflect.api.builders.EnumBuilder;
import io.github.jwdeveloper.reflect.api.builders.common.Buildable;
import io.github.jwdeveloper.reflect.api.validators.EnumValidatorModel;
import io.github.jwdeveloper.reflect.api.builders.common.Visibility;


public class JavaEnumBuilder implements EnumBuilder, Buildable<EnumValidatorModel> {

    private final EnumValidatorModel model;

    public JavaEnumBuilder(Class<?> locationClass) {
        model = new EnumValidatorModel();
        model.setLocationClass(locationClass);
    }

    @Override
    public EnumBuilder withName(String name) {
        model.setName(name);
        return this;
    }

    @Override
    public EnumBuilder withPackagePrivate() {
        model.setVisibility(Visibility.PACKET_PRIVATE);
        return this;
    }

    @Override
    public EnumBuilder withPublic() {
        model.setVisibility(Visibility.PUBLIC);
        return this;
    }

    @Override
    public EnumBuilder withPrivate() {
        model.setVisibility(Visibility.PRIVATE);
        return this;
    }

    @Override
    public EnumBuilder withProtected() {
        model.setVisibility(Visibility.PROTECTED);
        return this;
    }

    @Override
    public EnumValidatorModel build() {
        return model;
    }

    @Override
    public EnumBuilder withValuesCount(int count) {
        model.setValuesCount(count);
        return this;
    }
}
