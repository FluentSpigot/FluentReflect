package io.github.jwdeveloper.examples;

import io.github.jwdeveloper.reflect.api.models.ClassModel;
import io.github.jwdeveloper.reflect.api.models.ConstructorModel;
import io.github.jwdeveloper.reflect.api.models.FieldModel;
import io.github.jwdeveloper.reflect.api.models.MethodModel;
import io.github.jwdeveloper.reflect.implementation.FluentReflect;

import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, InstantiationException {

        String apiVersion = "1.0.0";
        FluentReflect fluentReflect = new FluentReflect(apiVersion);

        //In case using reflections for Library that changes packages names every new version it is
        //pretty handle use method `forVersion()`
        ClassModel exampleClass = fluentReflect.findClass()
                .forVersion("1.0.0", finder ->
                {
                    finder.withName("io.github.jwdeveloper.examples._1_0_0.ExampleClass");
                })
                .forVersion("2.0.0", finder ->
                {
                    finder.withName("io.github.jwdeveloper.examples._2_0_0.ExampleClass");
                }).find();

        FieldModel nameField = exampleClass
                .findField()
                .forAnyVersion(finder ->
                {
                    finder.withPrivate();
                    finder.withType(String.class);
                    finder.withFinal();
                }).find();

        MethodModel helloWorldMethod = exampleClass
                .findMethod()
                .forAnyVersion(finder ->
                {
                    finder.withName("helloWorld");
                    finder.withProtected();
                    finder.withType(String.class);
                }).find();

        ConstructorModel constructor = exampleClass
                .findConstructor()
                .forAnyVersion(finder ->
                {
                    finder.withParameterCount(1);
                }).find();


        Object instance = constructor.newInstance("Mike");
        Object nameValue = nameField.getValue(instance);
        Object methodResult = helloWorldMethod.invoke(instance);

        System.out.println(instance.toString()+" Name:"+nameValue+" Method output:"+methodResult);
    }
}