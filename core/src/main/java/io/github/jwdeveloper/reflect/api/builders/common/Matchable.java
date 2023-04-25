package io.github.jwdeveloper.reflect.api.builders.common;

import io.github.jwdeveloper.reflect.api.matcher.ParameterMatcher;

public interface Matchable<Callback>
{
    public Callback withParameterMatcher(ParameterMatcher matcher);
}
