package no.stelar7.api.r4j.basic.constants.types.val;

import no.stelar7.api.r4j.basic.constants.types.CodedEnum;

import java.util.Optional;
import java.util.stream.Stream;

public enum GameQueueType implements CodedEnum<GameQueueType>
{
    
    COMPETITIVE("competitive"),
    DEATHMATCH("deathmatch"),
    SPIKE_RUSH("spikerush"),
    UNRATED("unrated"),
    CUSTOM_GAME(""),
    TOURNAMENT_MODE("tournamentmode"),
    ONE_FOR_ALL("onefa"),
    ESCALATION("ggteam"),
    NEW_MAP("newmap"),
    SWIFTPLAY("swiftplay"),
    PREMIER("premier"),
    TEAM_DEATHMATCH("hurm");
    ;
    
    
    private final String queue;
    
    /**
     * Constructor for MapType
     *
     * @param code the mapId
     */
    GameQueueType(final String code)
    {
        this.queue = code;
    }
    
    /**
     * Gets from code.
     *
     * @param mapId the map id
     * @return the from code
     */
    public Optional<GameQueueType> getFromCode(final String mapId)
    {
        return Stream.of(GameQueueType.values()).filter(t -> t.queue.equals(mapId)).findFirst();
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
        return this.queue;
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
