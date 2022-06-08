package no.stelar7.api.r4j.basic.constants.types.lol;

import no.stelar7.api.r4j.basic.constants.types.CodedEnum;

import java.util.Optional;
import java.util.stream.Stream;

public enum ChallengeTierType implements CodedEnum
{
    LOWEST,
    HIGHEST,
    HIGHEST_NOT_LEADERBOARD_ONLY,
    CHALLENGER,
    GRANDMASTER,
    MASTER,
    DIAMOND,
    PLATINUM,
    GOLD,
    SILVER,
    BRONZE,
    IRON,
    NONE;
    
    /**
     * Returns an TierType from the provided value
     *
     * @param type the type to check
     * @return TierType
     */
    public Optional<ChallengeTierType> getFromCode(final String type)
    {
        return Stream.of(ChallengeTierType.values()).filter(t -> t.name().equalsIgnoreCase(type)).findFirst();
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
            case NONE:
                return "Unranked";
            case HIGHEST:
                return "Highest";
            case LOWEST:
                return "Lowest";
            case HIGHEST_NOT_LEADERBOARD_ONLY:
                return "Highest, not only leaderboard";
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
