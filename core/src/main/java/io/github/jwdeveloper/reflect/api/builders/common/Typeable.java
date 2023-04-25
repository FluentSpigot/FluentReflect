package io.github.jwdeveloper.reflect.api.builders.common;

public interface Typeable<CallBack>
{
    CallBack withType(Class<?> type);

    CallBack withType(String type);
}
