package io.github.jwdeveloper.reflect.api.builders.common;

public interface Visbilityable<Callback>
{
    Callback withPackagePrivate();

    Callback withPublic();

    Callback withPrivate();

    Callback withProtected();
}
