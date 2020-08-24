package no.stelar7.api.r4j.basic.constants.types.lol;

import no.stelar7.api.r4j.basic.constants.types.CodedEnum;

import java.util.Optional;
import java.util.stream.*;

public enum TierType implements CodedEnum
{
    CHALLENGER,
    GRANDMASTER,
    MASTER,
    DIAMOND,
    PLATINUM,
    GOLD,
    SILVER,
    BRONZE,
    IRON,
    UNRANKED;
    
    /**
     * Returns an TierType from the provided value
     *
     * @param type the type to check
     * @return TierType
     */
    public Optional<TierType> getFromCode(final String type)
    {
        return Stream.of(TierType.values()).filter(t -> t.name().equalsIgnoreCase(type)).findFirst();
    }
    
    @Override
    public String prettyName()
    {
        switch (this)
        {
            case CHALLENGER:
                return "Challenger";
            case GRANDMASTER:
                return "Grandmaster";
            case MASTER:
                return "Master";
            case DIAMOND:
                return "Diamond";
            case PLATINUM:
                return "Platinum";
            case GOLD:
                return "Gold";
            case SILVER:
                return "Silver";
            case BRONZE:
                return "Bronze";
            case IRON:
                return "Iron";
            case UNRANKED:
                return "Unranked";
            default:
                return "This enum does not have a pretty name";
        }
    }
    
    /**
     * The value used to map strings to objects
     *
     * @return String
     */
    public String getValue()
    {
        return this.name();
    }
}
