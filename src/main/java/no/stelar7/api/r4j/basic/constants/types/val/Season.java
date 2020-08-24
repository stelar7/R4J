package no.stelar7.api.r4j.basic.constants.types.val;

import no.stelar7.api.r4j.basic.constants.types.CodedEnum;

import java.util.Optional;
import java.util.stream.Stream;

public enum Season implements CodedEnum<Season>
{
    NONE(""),
    ACT_1("3f61c772-4560-cd3f-5d3f-a7ab5abda6b3"),
    ACT_2("0530b9c4-4980-f2ee-df5d-09864cd00542"),
    ;
    
    
    private final String mode;
    
    /**
     * Constructor for MapType
     *
     * @param code the mapId
     */
    Season(final String code)
    {
        this.mode = code;
    }
    
    /**
     * Gets from code.
     *
     * @param mapId the map id
     * @return the from code
     */
    public Optional<Season> getFromCode(final String mapId)
    {
        return Stream.of(Season.values()).filter(t -> t.mode.equals(mapId)).findFirst();
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
        return this.mode;
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
