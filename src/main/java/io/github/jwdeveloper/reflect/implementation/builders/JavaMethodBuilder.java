package io.github.jwdeveloper.reflect.implementation.builders;

import io.github.jwdeveloper.reflect.api.builders.MethodBuilder;
import io.github.jwdeveloper.reflect.implementation.Visibility;
import io.github.jwdeveloper.reflect.api.builders.common.Buildable;
import io.github.jwdeveloper.reflect.api.matcher.ParameterMatcher;
import io.github.jwdeveloper.reflect.api.validators.MethodValidationModel;

public class JavaMethodBuilder implements MethodBuilder, Buildable<MethodValidationModel> {

    private final MethodValidationModel model;

    public JavaMethodBuilder(Class<?> parentClass) {
        model = new MethodValidationModel();
        model.setParentClass(parentClass);
    }

    @Override
    public MethodBuilder withName(String name) {
        model.setName(name);
        return null;
    }

    @Override
    public MethodBuilder withStatic() {
        model.setStatic(true);
        return this;
    }

    @Override
    public MethodBuilder withType(Class<?> type) {
        model.setReturnType(type.getName());
        return this;
    }

    @Override
    public MethodBuilder withType(String type) {
        model.setReturnType(type);
        return this;
    }

    @Override
    public MethodBuilder withPackagePrivate() {
        model.setVisibility(Visibility.PACKET_PRIVATE);
        return this;
    }

    @Override
    public MethodBuilder withPublic() {
        model.setVisibility(Visibility.PUBLIC);
        return this;
    }

    @Override
    public MethodBuilder withPrivate() {
        model.setVisibility(Visibility.PRIVATE);
        return this;
    }

    @Override
    public MethodBuilder withProtected() {
        model.setVisibility(Visibility.PROTECTED);
        return this;
    }


    @Override
    public MethodBuilder withGenericType(String name) {
        model.addGenerics(name);
        return this;
    }

    @Override
    public MethodBuilder withParameter(Class<?> type) {
         model.getParameterModels().add(new MethodValidationModel.ParameterModel(type.getName(),"",-1));
         return this;
    }

    @Override
    public MethodBuilder withParameter(Class<?> type, String name) {
        model.getParameterModels().add(new MethodValidationModel.ParameterModel(type.getName(), name, -1));
        return this;
    }

    @Override
    public MethodBuilder withParameterCount(int parameterCount) {
        model.setParameterCount(parameterCount);
        return this;
    }

    @Override
    public MethodBuilder withParameter(String type) {
        model.getParameterModels().add(new MethodValidationModel.ParameterModel(type, "", -1));
        return this;
    }

    @Override
    public MethodBuilder withParameter(String type, String name) {
        model.getParameterModels().add(new MethodValidationModel.ParameterModel(type, name, -1));
        return this;
    }

    @Override
    public MethodBuilder withParameterMatcher(ParameterMatcher matcher) {
        model.setParameterMatcher(matcher);
        return this;
    }

    @Override
    public MethodValidationModel build() {
        return model;
    }

    @Override
    public MethodBuilder withFinal() {
        model.setFinal(true);
        return this;
    }
}
