package no.stelar7.api.l4j8.basic.deserializer;

import com.google.gson.*;
import no.stelar7.api.l4j8.basic.constants.api.Platform;
import no.stelar7.api.l4j8.basic.exceptions.APIEnumNotUpToDateException;

import java.lang.reflect.Type;

public class PlatformDeserializer implements JsonDeserializer<Platform>
{
    @Override
    public Platform deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
    {
        return Platform.getFromCode(json.getAsString()).orElseThrow(() -> new APIEnumNotUpToDateException(typeOfT, json));
    }
}
