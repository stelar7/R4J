package no.stelar7.api.l4j8.basic.serializer;

import com.google.gson.*;
import no.stelar7.api.l4j8.basic.constants.types.RealmSpesificEnum;

import java.lang.reflect.Type;
import java.util.Locale;

public class RealmSpesificEnumSerializer implements JsonSerializer<RealmSpesificEnum>
{
    
    @Override
    public JsonElement serialize(RealmSpesificEnum src, Type typeOfSrc, JsonSerializationContext context)
    {
        return new JsonPrimitive(src.getRealmValue().toUpperCase(Locale.ENGLISH));
    }
}
