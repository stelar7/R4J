package no.stelar7.api.r4j.basic.constants.types.val;

import no.stelar7.api.r4j.basic.constants.types.CodedEnum;

import java.util.Optional;
import java.util.stream.Stream;

public enum TierDivisionType implements CodedEnum<TierDivisionType>
{
    UNRANKED(0),
    UNUSED_1(1),
    UNUSED_2(2),
    IRON_1(3),
    IRON_2(4),
    IRON_3(5),
    BRONZE_1(6),
    BRONZE_2(7),
    BRONZE_3(8),
    SILVER_1(9),
    SILVER_2(10),
    SILVER_3(11),
    GOLD_1(12),
    GOLD_2(13),
    GOLD_3(14),
    PLATINUM_1(15),
    PLATINUM_2(16),
    PLATINUM_3(17),
    DIAMOND_1(18),
    DIAMOND_2(19),
    DIAMOND_3(20),
    IMMORTAL_1(21),
    IMMORTAL_2(22),
    IMMORTAL_3(23),
    RADIANT_1(24),
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
