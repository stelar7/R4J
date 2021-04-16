package no.stelar7.api.r4j.basic.constants.types.lol;

import no.stelar7.api.r4j.basic.constants.types.CodedEnum;

import java.util.Optional;
import java.util.stream.Stream;

public enum TournamentPickType implements CodedEnum
{
    BLIND_PICK,
    DRAFT_MODE,
    ALL_RANDOM,
    TOURNAMENT_DRAFT;
    
    
    public Optional<TournamentPickType> getFromCode(final String value)
    {
        return Stream.of(TournamentPickType.values()).filter(t -> t.name().equals(value)).findFirst();
    }
    
    @Override
    public String prettyName()
    {
        switch (this)
        {
            case BLIND_PICK:
                return "Blind Pick";
            case DRAFT_MODE:
                return "Draft Mode";
            case ALL_RANDOM:
                return "All Random";
            case TOURNAMENT_DRAFT:
                return "Tournament Draft";
            default:
                return "This enum does not have a pretty name";
        }
    }
    
}
