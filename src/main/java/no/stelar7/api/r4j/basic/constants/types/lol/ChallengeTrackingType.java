package no.stelar7.api.r4j.basic.constants.types.lol;

import no.stelar7.api.r4j.basic.constants.types.CodedEnum;

import java.util.Optional;
import java.util.stream.Stream;

public enum ChallengeTrackingType implements CodedEnum
{
    /**
     * stats are incremented without reset
     */
    LIFETIME,
    /**
     * stats are accumulated by season and reset at the beginning of new season
     */
    SEASON;
    
    @Override
    public Optional<ChallengeTrackingType> getFromCode(String type)
    {
        return Stream.of(ChallengeTrackingType.values()).filter(t -> t.name().equalsIgnoreCase(type)).findFirst();
    }
    
    @Override
    public String prettyName()
    {
        return this.name();
    }
}
