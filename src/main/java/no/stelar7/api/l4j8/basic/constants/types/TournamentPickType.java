package no.stelar7.api.l4j8.basic.constants.types;

import java.util.Optional;
import java.util.stream.Stream;

public enum TournamentPickType
{
    BLIND_PICK,
    DRAFT_MODE,
    ALL_RANDOM,
    TOURNAMENT_DRAFT;
    
    
    public static Optional<TournamentPickType> getFromCode(final String value)
    {
        return Stream.of(TournamentPickType.values()).filter(t -> t.name().equals(value)).findFirst();
    }
    
}
