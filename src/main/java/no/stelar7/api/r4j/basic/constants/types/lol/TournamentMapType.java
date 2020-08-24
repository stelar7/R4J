package no.stelar7.api.r4j.basic.constants.types.lol;

import no.stelar7.api.r4j.basic.constants.types.CodedEnum;

import java.util.*;
import java.util.stream.*;

public enum TournamentMapType implements CodedEnum
{
    /**
     * Summoner's Rift Current Version
     */
    SUMMONERS_RIFT(11),
    /**
     * The Crystal Scar Dominion Map
     * Dominion is gone...
     */
    CRYSTAL_SCAR(8),
    /**
     * Twisted Treeline Current Version
     */
    TWISTED_TREELINE(10),
    /**
     * Howling Abyss ARAM Map
     */
    HOWLING_ABYSS(12);
    
    private final Integer code;
    
    TournamentMapType(final Integer code)
    {
        this.code = code;
    }
    
    public Optional<TournamentMapType> getFromCode(final String teamId)
    {
        return Stream.of(TournamentMapType.values()).filter(t -> t.code.equals(Integer.valueOf(teamId))).findFirst();
    }
    
    @Override
    public String prettyName()
    {
        switch (this)
        {
            case SUMMONERS_RIFT:
                return "Summoners Rift";
            case CRYSTAL_SCAR:
                return "Crystal Scar";
            case TWISTED_TREELINE:
                return "Twisted Treeline";
            case HOWLING_ABYSS:
                return "Howling Abyss";
            default:
                return "This enum does not have a pretty name";
        }
    }
    
    public Integer getCode()
    {
        return this.code;
    }
    
}
