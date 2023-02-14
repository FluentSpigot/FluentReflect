package io.github.jwdeveloper.reflect.api.builders;

import io.github.jwdeveloper.reflect.api.builders.common.Genericable;
import io.github.jwdeveloper.reflect.api.builders.common.Matchable;
import io.github.jwdeveloper.reflect.api.builders.common.Visbilityable;

public interface ConstructorBuilder<SELF extends ConstructorBuilder<SELF>> extends
        Visbilityable<SELF>,
        Genericable<SELF>,
        Matchable<SELF> {

    SELF withParameter(Class<?> type);

    SELF withParameter(Class<?> type, String name);

    SELF withParameterCount(int parameterCount);

    SELF withParameter(String type);

    SELF withParameter(String type, String name);
}

