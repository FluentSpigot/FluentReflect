package io.github.jwdeveloper.reflect.implementation;


import io.github.jwdeveloper.reflect.api.VersionCompare;
import io.github.jwdeveloper.reflect.api.models.*;
import io.github.jwdeveloper.reflect.implementation.builders.*;
import io.github.jwdeveloper.reflect.implementation.utils.DefaultVersionCompare;
import io.github.jwdeveloper.reflect.implementation.validators.*;
import lombok.Getter;

public class FluentReflect {

    @Getter
    private final String currentVersion;
    private final VersionCompare versionCompare;

    public FluentReflect(String currentVersion, VersionCompare versionCompare)
    {
        this.currentVersion = currentVersion;
        this.versionCompare = versionCompare;
    }

    public FluentReflect(String version)
    {
      this(version, new DefaultVersionCompare());
    }

    public FluentBuilder<JavaClassBuilder, ClassModel> findClass() {
        return new FluentBuilder<JavaClassBuilder, ClassModel>(
                currentVersion,
                new JavaClassValidator(),
                new JavaClassBuilder(),
                versionCompare
        );
    }




    public FluentBuilder<JavaEnumBuilder, EnumModel> findEnum() {
        return findEnum(null);
    }

    public FluentBuilder<JavaEnumBuilder, EnumModel> findEnum(Class<?> locationClass) {
        return new FluentBuilder<JavaEnumBuilder, EnumModel>(
                currentVersion,
                new JavaEnumValidator(),
                new JavaEnumBuilder(locationClass),
                versionCompare
        );
    }


    public FluentBuilder<JavaMethodBuilder, MethodModel> findMethod(Class<?> locationClass) {
        return new FluentBuilder<JavaMethodBuilder, MethodModel>(
                currentVersion,
                new JavaMethodValidator(),
                new JavaMethodBuilder(locationClass),
                versionCompare
        );
    }

    public FluentBuilder<JavaFieldBuilder, FieldModel> findField(Class<?> locationClass) {
        return new FluentBuilder<JavaFieldBuilder, FieldModel>(
                currentVersion,
                new JavaFieldValidator(),
                new JavaFieldBuilder(locationClass),
                versionCompare
        );
    }

    public FluentBuilder<JavaConstructorBuilder, ConstructorModel> findConstructor(Class<?> locationClass) {
        return new FluentBuilder<JavaConstructorBuilder, ConstructorModel>(
                currentVersion,
                new JavaConstructorValidator(),
                new JavaConstructorBuilder(locationClass),
                versionCompare
        );
    }
}
