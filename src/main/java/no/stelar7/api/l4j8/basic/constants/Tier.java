package no.stelar7.api.l4j8.basic.constants;

import java.util.stream.*;

public enum Tier
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
     * Returns an Tier from the provided value
     *
     * @return Tier
     */
    public static Tier getFromCode(final String type)
    {
        return Stream.of(Tier.values()).filter(t -> t.name().equalsIgnoreCase(type)).findFirst().orElse(UNRANKED);
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
