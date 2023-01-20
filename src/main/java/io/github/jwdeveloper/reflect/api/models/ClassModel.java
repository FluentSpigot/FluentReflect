package io.github.jwdeveloper.reflect.api.models;

import io.github.jwdeveloper.reflect.implementation.FluentBuilder;
import io.github.jwdeveloper.reflect.implementation.builders.JavaClassBuilder;
import io.github.jwdeveloper.reflect.implementation.builders.JavaConstructorBuilder;
import io.github.jwdeveloper.reflect.implementation.builders.JavaFieldBuilder;
import io.github.jwdeveloper.reflect.implementation.builders.JavaMethodBuilder;
import io.github.jwdeveloper.reflect.implementation.models.JavaClassModel;
import io.github.jwdeveloper.reflect.implementation.models.JavaConstructorModel;
import io.github.jwdeveloper.reflect.implementation.models.JavaFieldModel;
import io.github.jwdeveloper.reflect.implementation.models.JavaMethodModel;

public interface ClassModel
{
     FluentBuilder<JavaClassBuilder, JavaClassModel> findClass();

     FluentBuilder<JavaMethodBuilder, JavaMethodModel> findMethod();

     FluentBuilder<JavaFieldBuilder, JavaFieldModel> findField();

     FluentBuilder<JavaConstructorBuilder, JavaConstructorModel> findConstructor();
}
