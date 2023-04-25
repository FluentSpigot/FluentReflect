package io.github.jwdeveloper.reflect.api.models;

public interface EnumModel {

    Class<?> getEnumClass();

    Object[] getValues();

    Object getValueAt(int index);

    Object getValueByName(String name);
}
