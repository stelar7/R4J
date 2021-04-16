package no.stelar7.api.r4j.basic.constants.types.val;

import no.stelar7.api.r4j.basic.constants.types.CodedEnum;

import java.util.Optional;
import java.util.stream.Stream;

public enum TeamType implements CodedEnum<TeamType>
{
    RED("Red"),
    BLUE("Blue"),
    NEUTRAL("Neutral"),
    SOLO("THIS IS THE PLAYERS UUID"),
    ;
    
    
    private final String team;
    
    /**
     * Constructor for MapType
     *
     * @param code the teamId
     */
    TeamType(final String code)
    {
        this.team = code;
    }
    
    /**
     * Gets from code.
     *
     * @param teamId the map id
     * @return the from code
     */
    public Optional<TeamType> getFromCode(final String teamId)
    {
        if (teamId.length() > 15)
        {
            return Optional.of(SOLO);
        }
        
        return Stream.of(TeamType.values()).filter(t -> t.team.equals(teamId)).findFirst();
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
        return this.team;
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
