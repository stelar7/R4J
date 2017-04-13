package no.stelar7.api.l4j8.basic.constants.types;

import java.util.Optional;
import java.util.stream.*;

public enum TierType
{
    CHALLENGER,
    MASTER,
    DIAMOND,
    PLATINUM,
    GOLD,
    SILVER,
    BRONZE,
    UNRANKED;
    
    /**
     * Returns an TierType from the provided value
     *
     * @return TierType
     */
    public static Optional<TierType> getFromCode(final String type)
    {
        return Stream.of(TierType.values()).filter(t -> t.name().equalsIgnoreCase(type)).findFirst();
    }
    
    /**
     * The value used to map strings to objects
     *
     * @return String
     */
    public String getCode()
    {
        return this.name();
    }
}
