package io.github.jwdeveloper.reflect.implementation.builders;

import io.github.jwdeveloper.reflect.api.builders.ConstructorBuilder;
import io.github.jwdeveloper.reflect.api.builders.common.Buildable;
import io.github.jwdeveloper.reflect.api.matcher.ParameterMatcher;
import io.github.jwdeveloper.reflect.api.validators.ConstructorValidationModel;
import io.github.jwdeveloper.reflect.api.validators.MethodValidationModel;
import io.github.jwdeveloper.reflect.implementation.Visibility;


public class JavaConstructorBuilder implements ConstructorBuilder<JavaConstructorBuilder>, Buildable<ConstructorValidationModel> {

    private final ConstructorValidationModel model;

    public JavaConstructorBuilder(Class<?> parentClass) {
        model = new ConstructorValidationModel();
        model.setParentClass(parentClass);
    }

    @Override
    public JavaConstructorBuilder withGenericType(String name) {
        model.getGenerics().add(name);
        return this;
    }

    @Override
    public JavaConstructorBuilder withParameterMatcher(ParameterMatcher matcher) {
        model.setParameterMatcher(matcher);
        return this;
    }

    @Override
    public JavaConstructorBuilder withPackagePrivate() {
        model.setVisibility(Visibility.PACKET_PRIVATE);
        return this;
    }

    @Override
    public JavaConstructorBuilder withParameterCount(int parameterCount) {
        model.setParameterCount(parameterCount);
        return this;
    }

    @Override
    public JavaConstructorBuilder withPublic() {
        model.setVisibility(Visibility.PUBLIC);
        return this;
    }

    @Override
    public JavaConstructorBuilder withPrivate() {
        model.setVisibility(Visibility.PRIVATE);
        return this;
    }

    @Override
    public JavaConstructorBuilder withProtected() {
        model.setVisibility(Visibility.PROTECTED);
        return this;
    }

    @Override
    public ConstructorValidationModel build() {
        return model;
    }

    @Override
    public JavaConstructorBuilder withParameter(Class<?> type) {
        return withParameter(type.getName(), "");
    }

    @Override
    public JavaConstructorBuilder withParameter(Class<?> type, String name) {
        return withParameter(type.getName(), name);
    }

    @Override
    public JavaConstructorBuilder withParameter(String type) {
        return withParameter(type, "");
    }

    @Override
    public JavaConstructorBuilder withParameter(String type, String name) {
        model.getParameterModels().add(new MethodValidationModel.ParameterModel(type, name,-1));
        return this;
    }
}
