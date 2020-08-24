package no.stelar7.api.r4j.basic.constants.types.val;

import no.stelar7.api.r4j.basic.constants.types.CodedEnum;

import java.util.Optional;
import java.util.stream.Stream;

public enum FinishingDamageType implements CodedEnum<FinishingDamageType>
{
    WEAPON("Weapon"),
    ABILITY("Ability"),
    MELEE("Melee"),
    BOMB("Bomb"),
    FALL("Fall"),
    
    ;
    
    
    private final String mode;
    
    /**
     * Constructor for MapType
     *
     * @param code the mapId
     */
    FinishingDamageType(final String code)
    {
        this.mode = code;
    }
    
    /**
     * Gets from code.
     *
     * @param mapId the map id
     * @return the from code
     */
    public Optional<FinishingDamageType> getFromCode(final String mapId)
    {
        return Stream.of(FinishingDamageType.values()).filter(t -> t.mode.equals(mapId)).findFirst();
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
