package io.github.jwdeveloper.reflect.implementation;


import io.github.jwdeveloper.reflect.implementation.builders.JavaConstructorBuilder;
import io.github.jwdeveloper.reflect.implementation.builders.JavaFieldBuilder;
import io.github.jwdeveloper.reflect.implementation.builders.JavaMethodBuilder;
import io.github.jwdeveloper.reflect.implementation.models.JavaClassModel;
import io.github.jwdeveloper.reflect.api.validators.ClassValidationModel;
import io.github.jwdeveloper.reflect.implementation.builders.JavaClassBuilder;
import io.github.jwdeveloper.reflect.implementation.models.JavaConstructorModel;
import io.github.jwdeveloper.reflect.implementation.models.JavaFieldModel;
import io.github.jwdeveloper.reflect.implementation.models.JavaMethodModel;
import io.github.jwdeveloper.reflect.implementation.validators.JavaClassValidator;
import io.github.jwdeveloper.reflect.implementation.validators.JavaConstructorValidator;
import io.github.jwdeveloper.reflect.implementation.validators.JavaFieldValidator;
import io.github.jwdeveloper.reflect.implementation.validators.JavaMethodValidator;

public class FinderFacade {

    private final String version;
    public FinderFacade(String version)
    {
        this.version = version;
    }


    public FluentBuilder<JavaClassBuilder, JavaClassModel> findClass() {
        return new FluentBuilder<JavaClassBuilder, JavaClassModel>(
                version,
                new JavaClassValidator(),
                new JavaClassBuilder()
        );
    }

    public FluentBuilder<JavaClassBuilder, ClassValidationModel> findEnum() {
        return new FluentBuilder<JavaClassBuilder, ClassValidationModel>(
                version,
                new JavaClassValidator(),
                new JavaClassBuilder()
        );
    }

    public FluentBuilder<JavaClassBuilder, ClassValidationModel> findAnnotation() {
        return new FluentBuilder<JavaClassBuilder, ClassValidationModel>(
                version,
                new JavaClassValidator(),
                new JavaClassBuilder()
        );
    }


    public FluentBuilder<JavaMethodBuilder, JavaMethodModel> findMethod(Class<?> locationClass) {
        return new FluentBuilder<JavaMethodBuilder, JavaMethodModel>(
                version,
                new JavaMethodValidator(),
                new JavaMethodBuilder(locationClass)
        );
    }

    public FluentBuilder<JavaFieldBuilder, JavaFieldModel> findField(Class<?> locationClass) {
        return new FluentBuilder<JavaFieldBuilder, JavaFieldModel>(
                version,
                new JavaFieldValidator(),
                new JavaFieldBuilder(locationClass)
        );
    }

    public FluentBuilder<JavaConstructorBuilder, JavaConstructorModel> findConstructor(Class<?> locationClass) {
        return new FluentBuilder<JavaConstructorBuilder, JavaConstructorModel>(
                version,
                new JavaConstructorValidator(),
                new JavaConstructorBuilder(locationClass)
        );
    }
}
