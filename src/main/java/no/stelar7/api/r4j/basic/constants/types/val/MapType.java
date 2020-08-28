package no.stelar7.api.r4j.basic.constants.types.val;

import no.stelar7.api.r4j.basic.constants.types.CodedEnum;

import java.util.Optional;
import java.util.stream.Stream;

public enum MapType implements CodedEnum<MapType>
{
    
    BIND("/Game/Maps/Duality/Duality"),
    HAVEN("/Game/Maps/Triad/Triad"),
    SPLIT("/Game/Maps/Bonsai/Bonsai"),
    ASCENT("/Game/Maps/Ascent/Ascent"),
    THE_RANGE("/Game/Maps/Poveglia/Range"),
    THE_RANGE_NPE("/Game/Maps/Poveglia/Range_NewPlayerExperience_Master"),
    ;
    
    
    private final String mapId;
    
    /**
     * Constructor for MapType
     *
     * @param code the mapId
     */
    MapType(final String code)
    {
        this.mapId = code;
    }
    
    /**
     * Gets from code.
     *
     * @param mapId the map id
     * @return the from code
     */
    public Optional<MapType> getFromCode(final String mapId)
    {
        return Stream.of(MapType.values()).filter(t -> t.mapId.equals(mapId)).findFirst();
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
        return this.mapId;
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
