package no.stelar7.api.l4j8.basic.deserializer;

import com.google.gson.*;
import no.stelar7.api.l4j8.basic.constants.api.Platform;
import no.stelar7.api.l4j8.basic.exceptions.APIEnumNotUpToDateException;

import java.lang.reflect.Type;
import java.util.Arrays;

public class PlatformDeserializer implements JsonDeserializer<Platform>
{
    @Override
    public Platform deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
    {
        String[] id = {json.getAsString()};
        if ("NA".equalsIgnoreCase(id[0]))
        {
            id[0] = "NA1";
        }
        return Arrays.stream(Platform.values()).filter(v -> v.name().equalsIgnoreCase(id[0])).findFirst().orElseThrow(() -> new APIEnumNotUpToDateException(typeOfT, json));
    }
}
