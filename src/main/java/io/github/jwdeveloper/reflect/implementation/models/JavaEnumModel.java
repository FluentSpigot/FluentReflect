package io.github.jwdeveloper.reflect.implementation.models;

import io.github.jwdeveloper.reflect.api.models.EnumModel;

public class JavaEnumModel implements EnumModel
{

    private final Class<?> enumType;
    private final Class<?> parent;
    private final Object[] enumValues;

    public JavaEnumModel(Class<?> enumType, Class<?> parent) {
        this.enumType = enumType;
        this.parent = parent;
        enumValues= enumType.getEnumConstants();
    }


    @Override
    public Class<?> getEnumClass() {
        return enumType;
    }

    @Override
    public Object[] getValues() {
        return enumValues;
    }

    @Override
    public Object getValueAt(int index)
    {
        return enumValues[index];
    }

    @Override
    public Object getValueByName(String name) {
        return null;
    }
}
