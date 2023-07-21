package no.stelar7.api.r4j.basic.constants.types.lol;

import no.stelar7.api.r4j.basic.constants.types.CodedEnum;
import no.stelar7.api.r4j.pojo.lol.match.v5.MatchParticipant;

import java.util.*;
import java.util.stream.*;

/**
 * Represent in which team a player is in Classical League game mode (Summoner's Rift, ARAM, ...). <br><br>
 * 
 * <b>IMPORTANT NOTE: Teams in Arena game mode (Cherry) are not determined by {@link TeamType} 
 * but by the "playerSubteamId" field inside {@link MatchParticipant#getArenaTeamId()}. </b> 
 * The meaning of this field for Arena Game Mode remain unknown. <br><br>
 * 
 * (Please update this if you have find a meaning to it)
 */
public enum TeamType implements CodedEnum
{
	/**
	 * Team 0 appearing in Arena game type. <br>
	 * <b>DOES NOT REPRESENT A TEAM, PLEASE SEE "playerSubteamId" field in {@link MatchParticipant#getArenaTeamId()} !</b>
	 */
    TEAM0(0),
    /**
     * Blue Team OR Team 1 in Arena game type
     */
    BLUE(100),
    /**
     * Red Team OR Team 2 in Arena game type
     */
    RED(200),
    /**
     * AI Team
     */
    AI(300);
    
    private final Integer code;
    
    TeamType(final int code)
    {
        this.code = code;
    }
    
    public Optional<TeamType> getFromCode(String value)
    {
        if (value.contains("."))
        {
            value = value.substring(0, value.indexOf("."));
        }
        
        return getFromId(Integer.parseInt(value));
    }
    
    public Optional<TeamType> getFromId(final int teamId)
    {
        return Stream.of(TeamType.values()).filter(t -> t.code.equals(teamId)).findFirst();
    }
    
    public TeamType opposite()
    {
        switch (this)
        {
            case RED:
                return BLUE;
            case BLUE:
                return RED;
            case AI:
                return AI;
            default:
                return null;
        }
    }
    
    @Override
    public String prettyName()
    {
        switch (this)
        {
            case BLUE:
                return "Blue";
            case RED:
                return "Red";
            case AI:
                return "AI";
            default:
                return "This enum does not have a pretty name";
        }
    }
    
    public Integer getValue()
    {
        return this.code;
    }
}
