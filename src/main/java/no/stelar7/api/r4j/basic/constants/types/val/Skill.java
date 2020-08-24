package no.stelar7.api.r4j.basic.constants.types.val;

import com.google.gson.JsonPrimitive;
import no.stelar7.api.r4j.basic.constants.types.CodedEnum;
import no.stelar7.api.r4j.basic.exceptions.APIEnumNotUpToDateException;

import java.util.Optional;
import java.util.stream.Stream;

public enum Skill implements CodedEnum<Skill>
{
    NONE(""),
    ABILITY_1("Ability1"),
    ABILITY_2("Ability2"),
    ABILITY_3("Ability3"),
    PRIMARY("Primary"),
    GRENADE("GrenadeAbility"),
    ULTIMATE("Ultimate"),
    ;
    
    
    private final String mode;
    
    /**
     * Constructor for MapType
     *
     * @param code the mapId
     */
    Skill(final String code)
    {
        this.mode = code;
    }
    
    /**
     * Gets from code.
     *
     * @param mapId the map id
     * @return the from code
     */
    public Optional<Skill> getFromCode(final String mapId)
    {
        return Stream.of(Skill.values()).filter(t -> t.mode.equals(mapId)).findFirst();
    }
    
    public static Skill fromCode(final String mapId)
    {
        return Stream.of(Skill.values()).filter(t -> t.mode.equals(mapId)).findFirst().orElseThrow(() -> new APIEnumNotUpToDateException(Skill.class, new JsonPrimitive(mapId)));
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
