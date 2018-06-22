package no.stelar7.api.l4j8.basic.deserializer;

import com.google.gson.*;
import no.stelar7.api.l4j8.basic.constants.types.*;
import no.stelar7.api.l4j8.basic.exceptions.APIEnumNotUpToDateException;

import java.lang.reflect.Type;
import java.util.*;

public class GameQueueTypeDeserializer implements JsonDeserializer<GameQueueType>
{
    
    @Override
    @SuppressWarnings("unchecked")
    public GameQueueType deserialize(JsonElement json, Type t, JsonDeserializationContext context)
    {
        String          id     = json.getAsString();
        List<CodedEnum> values = Arrays.asList(((Class<CodedEnum>) t).getEnumConstants());
        Optional<Object> retu = values.stream()
                                      .map(v -> v.getFromCode(id))
                                      .filter(Optional::isPresent)
                                      .map(Optional::get)
                                      .findFirst();
        
        return retu.map(o -> (GameQueueType) o).orElseGet(() -> (GameQueueType) CodedEnum.printError(t, id));
    }
}