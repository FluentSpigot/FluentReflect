package io.github.jwdeveloper.reflect.implementation.models;

import io.github.jwdeveloper.reflect.api.models.ClassModel;
import io.github.jwdeveloper.reflect.implementation.FinderFacade;
import io.github.jwdeveloper.reflect.implementation.FluentBuilder;
import io.github.jwdeveloper.reflect.implementation.builders.JavaClassBuilder;
import io.github.jwdeveloper.reflect.implementation.builders.JavaConstructorBuilder;
import io.github.jwdeveloper.reflect.implementation.builders.JavaFieldBuilder;
import io.github.jwdeveloper.reflect.implementation.builders.JavaMethodBuilder;
import io.github.jwdeveloper.reflect.implementation.validators.JavaFieldValidator;
import lombok.Getter;


public class JavaClassModel extends ClassModel {

    private final FinderFacade finderFacade;
    @Getter
    private final Class<?> classType;
    @Getter
    private final String apiVersion;

    public JavaClassModel(Class<?> instanceClass,
                          String version) {
        this.apiVersion = version;
        this.classType = instanceClass;
        this.finderFacade = new FinderFacade(version);
    }

    public FluentBuilder<JavaClassBuilder, JavaClassModel> findClass() {
        return finderFacade.findClass();
    }

    public FluentBuilder<JavaMethodBuilder, JavaMethodModel> findMethod() {
        return finderFacade.findMethod(classType);
    }

    public FluentBuilder<JavaFieldBuilder, JavaFieldModel> findField() {
        return finderFacade.findField(classType);
    }

    public FluentBuilder<JavaConstructorBuilder, JavaConstructorModel> findConstructor() {
        return finderFacade.findConstructor(classType);
    }
}
