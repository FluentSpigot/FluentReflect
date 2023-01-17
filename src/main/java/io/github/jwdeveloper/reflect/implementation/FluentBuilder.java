package io.github.jwdeveloper.reflect.implementation;

import io.github.jwdeveloper.reflect.api.builders.common.Buildable;
import io.github.jwdeveloper.reflect.api.finders.Finder;
import io.github.jwdeveloper.reflect.api.validators.Validator;

public class FluentBuilder<Builder extends Buildable<?>, Result> {
    private final Builder builder;
    private final String currentVersion;
    private final Validator validator;

    public FluentBuilder(String version,
                         Validator validator,
                         Builder builder)
    {
        this.currentVersion = version;
        this.validator = validator;
        this.builder = builder;
    }


    public FluentBuilder<Builder, Result> forAnyVersion(Finder<Builder> consumer)
    {
        consumer.find(builder);
        return this;
    }


    public FluentBuilder<Builder, Result> forVersion(String version, Finder<Builder> consumer)
    {
        if(!currentVersion.equals(version))
        {
            return this;
        }
        consumer.find(builder);
        return this;
    }

    public FluentBuilder<Builder, Result> forVersionRange(String fromVersion,
                                                          String toVersion,
                                                          Finder<Builder> consumer) {
        return this;
    }

    public FluentBuilder<Builder, Result> forVersionLowerThen(String version,
                                                              Finder<Builder> consumer) {
        return this;
    }

    public FluentBuilder<Builder, Result> forVersionGraterThen(String version,
                                                               Finder<Builder> consumer) {
        return this;
    }

    public Result find() throws Exception {
        var model = builder.build();
        var result = (Result) validator.validate(model, currentVersion);
        return result;
    }

    public Result tryFind() throws Exception {
      try {
          return find();
      }
      catch (Exception e)
      {
          return null;
      }
    }
}
