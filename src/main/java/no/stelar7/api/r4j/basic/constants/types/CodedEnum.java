package no.stelar7.api.r4j.basic.constants.types;

import java.util.Optional;

public interface CodedEnum<T>
{
    Optional<T> getFromCode(final String type);
    
    String prettyName();
    
    default String commonName()
    {
        return prettyName();
    }
}
