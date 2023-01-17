package io.github.jwdeveloper.reflect.api.builders;

import io.github.jwdeveloper.reflect.api.builders.common.*;

public interface ConstructorBuilder<SELF extends ConstructorBuilder<SELF>> extends
        Visbilityable<SELF>,
        Genericable<SELF>,
        Matchable<SELF> {

    SELF withParameter(Class<?> type);
    SELF withParameter(Class<?> type, String name);

    SELF withParameter(String type, String name);
}

