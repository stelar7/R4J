package no.stelar7.api.l4j8.basic.constants.types;

import java.lang.reflect.Type;
import java.util.Optional;

public interface CodedEnum<T>
{
    Optional<T> getFromCode(final String type);
    
    static Object printError(Type type, String value)
    {
        String.format("The enum %s is missing the type %s!%nPlease make sure you have the latest version of the library!%nIf you do, send this message to the maintainer of the API.", type.getTypeName(), value);
        return null;
    }
}
