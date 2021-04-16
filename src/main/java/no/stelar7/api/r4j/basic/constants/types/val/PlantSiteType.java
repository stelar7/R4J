package no.stelar7.api.r4j.basic.constants.types.val;

import no.stelar7.api.r4j.basic.constants.types.CodedEnum;

import java.util.Optional;
import java.util.stream.Stream;

public enum PlantSiteType implements CodedEnum<PlantSiteType>
{
    NONE(""),
    A("A"),
    B("B"),
    C("C"),
    ;
    
    
    private final String site;
    
    /**
     * Constructor for MapType
     *
     * @param code the mapId
     */
    PlantSiteType(final String code)
    {
        this.site = code;
    }
    
    /**
     * Gets from code.
     *
     * @param mapId the map id
     * @return the from code
     */
    public Optional<PlantSiteType> getFromCode(final String mapId)
    {
        return Stream.of(PlantSiteType.values()).filter(t -> t.site.equals(mapId)).findFirst();
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
        return this.site;
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
