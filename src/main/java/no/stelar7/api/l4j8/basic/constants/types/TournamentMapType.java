package no.stelar7.api.l4j8.basic.constants.types;

import java.util.*;
import java.util.stream.*;

public enum TournamentMapType
{
    /**
     * Summoner's Rift Current Version
     */
    SUMMONERS_RIFT(11),
    /**
     * The Crystal Scar Dominion Map
     * <p>
     * <p>
     * Dominion is gone...
     */
    @Deprecated CRYSTAL_SCAR(8),
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
    
    public static Optional<TournamentMapType> getFromCode(final String teamId)
    {
        return Stream.of(TournamentMapType.values()).filter(t -> t.code.equals(Integer.valueOf(teamId))).findFirst();
    }
    
    public Integer getCode()
    {
        return this.code;
    }
    
}
