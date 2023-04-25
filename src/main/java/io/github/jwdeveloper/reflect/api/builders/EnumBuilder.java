package io.github.jwdeveloper.reflect.api.builders;

import io.github.jwdeveloper.reflect.api.builders.common.Nameable;
import io.github.jwdeveloper.reflect.api.builders.common.Visbilityable;

public interface EnumBuilder extends
        Visbilityable<EnumBuilder>,
        Nameable<EnumBuilder>  {

    EnumBuilder withValuesCount(int count);
}
