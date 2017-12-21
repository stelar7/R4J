package no.stelar7.api.l4j8.basic.deserializer;

import com.google.gson.*;
import no.stelar7.api.l4j8.basic.ratelimiting.RateLimit;

import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

public class RatelimitDeserializer implements JsonDeserializer<RateLimit>
{
    
    @Override
    public RateLimit deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
    {
        if (json.isJsonObject())
        {
            Integer permit = json.getAsJsonObject().get("permits").getAsInt();
            Long    delay  = json.getAsJsonObject().get("delayInMs").getAsLong();
            
            return new RateLimit(permit, delay, TimeUnit.MILLISECONDS);
        }
        
        
        if (json.isJsonPrimitive())
        {
            String   data = json.getAsString().split("\\{")[1].split("}")[0];
            String[] objs = data.split(", ");
            
            Object[] result = new Object[2];
            
            for (String obj : objs)
            {
                String field = obj.split("=")[0];
                String value = obj.split("=")[1];
                
                if ("permits".equalsIgnoreCase(field))
                {
                    result[0] = new Integer(value);
                }
                
                if ("delayInMs".equalsIgnoreCase(field))
                {
                    result[1] = new Long(value);
                }
            }
            
            return new RateLimit((Integer) result[0], (Long) result[1], TimeUnit.MILLISECONDS);
        }
        
        return null;
    }
}
