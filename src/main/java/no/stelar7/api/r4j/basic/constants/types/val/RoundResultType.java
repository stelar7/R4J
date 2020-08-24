package no.stelar7.api.r4j.basic.constants.types.val;

import no.stelar7.api.r4j.basic.constants.types.CodedEnum;

import java.util.Optional;
import java.util.stream.Stream;

public enum RoundResultType implements CodedEnum<RoundResultType>
{
    NONE(""),
    DEFAULT("CeremonyDefault"),
    FLAWLESS("CeremonyFlawless"),
    CLOSER("CeremonyCloser"),
    CLUTCH("CeremonyClutch"),
    THRIFTY("CeremonyThrifty"),
    ACE("CeremonyAce"),
    TEAM_ACE("CeremonyTeamAce"),
    ;
    
    
    private final String result;
    
    /**
     * Constructor for MapType
     *
     * @param code the mapId
     */
    RoundResultType(final String code)
    {
        this.result = code;
    }
    
    /**
     * Gets from code.
     *
     * @param mapId the map id
     * @return the from code
     */
    public Optional<RoundResultType> getFromCode(final String mapId)
    {
        return Stream.of(RoundResultType.values()).filter(t -> t.result.equals(mapId)).findFirst();
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
        return this.result;
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
