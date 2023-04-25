package io.github.jwdeveloper.reflect.implementation.validators;

import io.github.jwdeveloper.reflect.api.exceptions.EnumValidationException;
import io.github.jwdeveloper.reflect.api.exceptions.ValidationException;
import io.github.jwdeveloper.reflect.api.validators.EnumValidatorModel;
import io.github.jwdeveloper.reflect.api.validators.ValidationResult;
import io.github.jwdeveloper.reflect.api.validators.Validator;
import io.github.jwdeveloper.reflect.api.builders.common.Visibility;
import io.github.jwdeveloper.reflect.implementation.models.JavaEnumModel;

import java.lang.reflect.Modifier;


public class JavaEnumValidator  extends JavaValidator implements Validator<EnumValidatorModel, JavaEnumModel>
{
    @Override
    public JavaEnumModel validate(EnumValidatorModel model, String version) throws ValidationException
    {
        var parent = model.getLocationClass();
        if(parent == null)
        {
            try
            {
                var enumType = Class.forName(model.getName(), false, this.getClass().getClassLoader());
                var validation = validateEnum(enumType, model);
                if(!validation.isValid())
                {
                    throw new EnumValidationException(model, version,validation);
                }
                return new JavaEnumModel((Class<?>)validation.getValue(), parent);
            }
            catch (Exception e)
            {
                throw new EnumValidationException(model, version, new ValidationResult<Class<?>>(false, null, e.getMessage()));
            }
        }



        var validation = this.checkClasses(model, parent, Class::getDeclaredClasses, this::validateEnum);
        if (!validation.isValid()) {
            throw new EnumValidationException(model, version,validation);
        }

        return new JavaEnumModel(validation.getValue(), parent);
    }


    private ValidationResult validateEnum(Class<?> _enum, EnumValidatorModel model) {

        if(!_enum.isEnum())
            return new ValidationResult(false, model, "is not enum");


        if (model.getVisibility() == Visibility.PUBLIC && !Modifier.isPublic(_enum.getModifiers()))
            return new ValidationResult(false, model, "is not public");


        if (model.hasName())
        {
            var name = _enum.getName();
            if(_enum.getName().contains("$"))
            {
                name = _enum.getName().split("\\$")[1];
            }

            if(!name.equalsIgnoreCase(model.getName()))
            {
                return new ValidationResult(false, model, "name difference");
            }

        }



        if (model.getVisibility() == Visibility.PACKET_PRIVATE && !Modifier.isPrivate(_enum.getModifiers()))
            return new ValidationResult(false, model, "is not protected");


        if (model.getValuesCount() != -1 && _enum.getEnumConstants().length != model.getValuesCount() )
            return new ValidationResult(false, model, "different amount of enum values");


        return new ValidationResult(true, _enum, "found");
    }
}
