package no.stelar7.api.l4j8.basic.deserializer;

import com.google.gson.*;
import no.stelar7.api.l4j8.basic.constants.types.*;
import no.stelar7.api.l4j8.basic.exceptions.APIEnumNotUpToDateException;

import java.lang.reflect.Type;

public class WardTypeDeserializer implements JsonDeserializer<WardType>
{
    
    @Override
    public WardType deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
    {
        String id = json.getAsString();
        return WardType.getFromCode(id).orElseThrow(() -> new APIEnumNotUpToDateException(typeOfT, json));
    }
}
