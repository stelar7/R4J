package no.stelar7.api.r4j.basic.constants.types.val;

import no.stelar7.api.r4j.basic.constants.types.CodedEnum;

import java.util.Optional;
import java.util.stream.Stream;

public enum TierDivisionType implements CodedEnum<TierDivisionType>
{
    UNRANKED(0),
    IRON_1(1),
    IRON_2(2),
    IRON_3(3),
    BRONZE_1(4),
    BRONZE_2(5),
    BRONZE_3(6),
    SILVER_1(7),
    SILVER_2(8),
    SILVER_3(9),
    GOLD_1(10),
    GOLD_2(11),
    GOLD_3(12),
    PLATINUM_1(13),
    PLATINUM_2(14),
    PLATINUM_3(15),
    DIAMOND_1(16),
    DIAMOND_2(17),
    DIAMOND_3(18),
    IMMORTAL_1(19),
    IMMORTAL_2(20),
    IMMORTAL_3(21),
    RADIANT_1(22),
    RADIANT_2(23),
    RADIANT_3(24),
    ;
    
    
    private final Integer tier;
    
    /**
     * Constructor for MapType
     *
     * @param code the mapId
     */
    TierDivisionType(final Integer code)
    {
        this.tier = code;
    }
    
    /**
     * Gets from code.
     *
     * @param mapId the map id
     * @return the from code
     */
    public Optional<TierDivisionType> getFromCode(final String mapId)
    {
        return Stream.of(TierDivisionType.values()).filter(t -> t.tier.equals(Integer.valueOf(mapId))).findFirst();
    }
    
    @Override
    public String prettyName()
    {
        switch (this)
        {
            default:
                return "This enum does not have a pretty name";
        }
    }
    
    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId()
    {
        return String.valueOf(this.tier);
    }
    
    
    /**
     * Used internaly in the api...
     *
     * @return the value
     */
    public String getValue()
    {
        return getId();
    }
    
}
