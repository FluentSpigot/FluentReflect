package io.github.jwdeveloper.reflect.implementation;


import io.github.jwdeveloper.reflect.api.VersionCompare;
import io.github.jwdeveloper.reflect.api.validators.EnumValidatorModel;
import io.github.jwdeveloper.reflect.implementation.builders.*;
import io.github.jwdeveloper.reflect.implementation.models.*;
import io.github.jwdeveloper.reflect.api.validators.ClassValidationModel;
import io.github.jwdeveloper.reflect.implementation.utils.DefaultVersionCompare;
import io.github.jwdeveloper.reflect.implementation.validators.*;
import lombok.Getter;

public class FluentReflect {

    @Getter
    private final String version;
    private final VersionCompare versionCompare;

    public FluentReflect(String version, VersionCompare versionCompare)
    {
        this.version = version;
        this.versionCompare = versionCompare;
    }

    public FluentReflect(String version)
    {
      this(version, new DefaultVersionCompare());
    }

    public FluentBuilder<JavaClassBuilder, JavaClassModel> findClass() {
        return new FluentBuilder<JavaClassBuilder, JavaClassModel>(
                version,
                new JavaClassValidator(),
                new JavaClassBuilder(),
                versionCompare
        );
    }

    public FluentBuilder<JavaEnumBuilder, JavaEnumModel> findEnum() {
        return findEnum(null);
    }

    public FluentBuilder<JavaEnumBuilder, JavaEnumModel> findEnum(Class<?> locationClass) {
        return new FluentBuilder<JavaEnumBuilder, JavaEnumModel>(
                version,
                new JavaEnumValidator(),
                new JavaEnumBuilder(locationClass),
                versionCompare
        );
    }


    public FluentBuilder<JavaMethodBuilder, JavaMethodModel> findMethod(Class<?> locationClass) {
        return new FluentBuilder<JavaMethodBuilder, JavaMethodModel>(
                version,
                new JavaMethodValidator(),
                new JavaMethodBuilder(locationClass),
                versionCompare
        );
    }

    public FluentBuilder<JavaFieldBuilder, JavaFieldModel> findField(Class<?> locationClass) {
        return new FluentBuilder<JavaFieldBuilder, JavaFieldModel>(
                version,
                new JavaFieldValidator(),
                new JavaFieldBuilder(locationClass),
                versionCompare
        );
    }

    public FluentBuilder<JavaConstructorBuilder, JavaConstructorModel> findConstructor(Class<?> locationClass) {
        return new FluentBuilder<JavaConstructorBuilder, JavaConstructorModel>(
                version,
                new JavaConstructorValidator(),
                new JavaConstructorBuilder(locationClass),
                versionCompare
        );
    }
}
