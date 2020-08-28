package no.stelar7.api.r4j.basic.constants.types.val;

import no.stelar7.api.r4j.basic.constants.types.CodedEnum;

import java.util.Optional;
import java.util.stream.Stream;

public enum RoundResultCodeType implements CodedEnum<RoundResultCodeType>
{
    
    TIMEOUT(""),
    DEFUSE("Defuse"),
    ELIMINATION("Elimination"),
    DETONATE("Detonate"),
    SURRENDERED("Surrendered"),
    TIME_EXPIRED("TimeExpired"),
    CHEAT("Cheat"),
    
    ;
    
    
    private final String code;
    
    /**
     * Constructor for MapType
     *
     * @param code the mapId
     */
    RoundResultCodeType(final String code)
    {
        this.code = code;
    }
    
    /**
     * Gets from code.
     *
     * @param mapId the map id
     * @return the from code
     */
    public Optional<RoundResultCodeType> getFromCode(final String mapId)
    {
        return Stream.of(RoundResultCodeType.values()).filter(t -> t.code.equals(mapId)).findFirst();
    }
    
    @Override
    public String prettyName()
    {
        switch (this)
        {
            default:
                return "This enum does not have a pretty name";
        }
    }
    
    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId()
    {
        return this.code;
    }
    
    
    /**
     * Used internaly in the api...
     *
     * @return the value
     */
    public String getValue()
    {
        return getId();
    }
    
}
