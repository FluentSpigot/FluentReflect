package io.github.jwdeveloper.reflect.implementation.models;

import io.github.jwdeveloper.reflect.api.models.*;
import io.github.jwdeveloper.reflect.implementation.FluentBuilder;
import io.github.jwdeveloper.reflect.implementation.FluentReflect;
import io.github.jwdeveloper.reflect.implementation.builders.*;
import lombok.Getter;


public class JavaClassModel implements ClassModel {

    private final FluentReflect finderFacade;
    @Getter
    private final Class<?> classType;
    @Getter
    private final String version;

    public JavaClassModel(Class<?> instanceClass, String version) {
        this.version = version;
        this.classType = instanceClass;
        this.finderFacade = new FluentReflect(version);
    }

    public FluentBuilder<JavaClassBuilder, ClassModel> findClass() {
        return finderFacade.findClass();
    }

    public FluentBuilder<JavaMethodBuilder, MethodModel> findMethod() {
        return finderFacade.findMethod(classType);
    }

    public FluentBuilder<JavaFieldBuilder, FieldModel> findField() {
        return finderFacade.findField(classType);
    }

    public FluentBuilder<JavaEnumBuilder, EnumModel> findEnum() {
        return finderFacade.findEnum(classType);
    }

    public FluentBuilder<JavaConstructorBuilder, ConstructorModel> findConstructor() {
        return finderFacade.findConstructor(classType);
    }
}
