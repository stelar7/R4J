package no.stelar7.api.r4j.basic.constants.types.lol;

import no.stelar7.api.r4j.basic.constants.types.CodedEnum;

import java.util.Optional;
import java.util.stream.Stream;

public enum ChallengeStateType implements CodedEnum
{
    /**
     * not visible and not calculated
     */
    DISABLED,
    /**
     * not visible, but calculated
     */
    HIDDEN,
    /**
     * visible and calculated
     */
    ENABLED,
    /**
     * visible, but not calculated
     */
    ARCHIVED;
    
    @Override
    public Optional<ChallengeStateType> getFromCode(String type)
    {
        return Stream.of(ChallengeStateType.values()).filter(t -> t.name().equalsIgnoreCase(type)).findFirst();
    }
    
    @Override
    public String prettyName()
    {
        return this.name();
    }
}
