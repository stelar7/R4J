package no.stelar7.api.r4j.basic.serializer;

import com.google.gson.*;
import no.stelar7.api.r4j.basic.constants.types.RealmSpecificEnum;

import java.lang.reflect.Type;
import java.util.Locale;

public class RealmSpecificEnumSerializer implements JsonSerializer<RealmSpecificEnum>
{
    
    @Override
    public JsonElement serialize(RealmSpecificEnum src, Type typeOfSrc, JsonSerializationContext context)
    {
        return new JsonPrimitive(src.getRealmValue().toUpperCase(Locale.ENGLISH));
    }
}
