package no.stelar7.api.l4j8.basic.constants.types;

import java.util.Optional;
import java.util.stream.Stream;

public enum TournamentSpectatorType
{
    NONE,
    LOBBYONLY,
    ALL;
    
    public static Optional<TournamentSpectatorType> getFromCode(final String value)
    {
        return Stream.of(TournamentSpectatorType.values()).filter(t -> t.name().equals(value)).findFirst();
    }
}
