package no.stelar7.api.l4j8.basic.exceptions;

import com.google.gson.JsonElement;

import java.lang.reflect.Type;

public class APIEnumNotUpToDateException extends RuntimeException
{
    public APIEnumNotUpToDateException(Type type, JsonElement json)
    {
        super(String.format("The enum %s is missing the type %s!%nPlease make sure you have the latest version of the library!%nIf you do, send this message to the maintainer of the API.", type.getTypeName(), json));
    }
}
