package io.github.jwdeveloper.reflect.api.models;

import io.github.jwdeveloper.reflect.implementation.FluentBuilder;
import io.github.jwdeveloper.reflect.implementation.builders.*;

public interface ClassModel {

    Class<?> getClassType();

    String getVersion();

    FluentBuilder<JavaClassBuilder, ClassModel> findClass();

    FluentBuilder<JavaEnumBuilder, EnumModel> findEnum();

    FluentBuilder<JavaMethodBuilder, MethodModel> findMethod();

    FluentBuilder<JavaFieldBuilder, FieldModel> findField();

    FluentBuilder<JavaConstructorBuilder, ConstructorModel> findConstructor();
}
