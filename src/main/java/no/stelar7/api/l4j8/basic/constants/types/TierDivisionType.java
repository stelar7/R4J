package no.stelar7.api.l4j8.basic.constants.types;

import java.util.Arrays;
import java.util.regex.Pattern;

public enum TierDivisionType
{
    CHALLENGER_I,
    
    MASTER_I,
    
    DIAMOND_I,
    DIAMOND_II,
    DIAMOND_III,
    DIAMOND_IV,
    DIAMOND_V,
    
    PLATINUM_I,
    PLATINUM_II,
    PLATINUM_III,
    PLATINUM_IV,
    PLATINUM_V,
    
    GOLD_I,
    GOLD_II,
    GOLD_III,
    GOLD_IV,
    GOLD_V,
    
    SILVER_I,
    SILVER_II,
    SILVER_III,
    SILVER_IV,
    SILVER_V,
    
    BRONZE_I,
    BRONZE_II,
    BRONZE_III,
    BRONZE_IV,
    BRONZE_V,
    
    UNRANKED;
    
    
    /**
     * The value used to map strings to objects
     *
     * @return String
     */
    public String getCode()
    {
        return this.name();
    }
    
    public static TierDivisionType getFromCodes(String tier, String rank)
    {
        Pattern pattern = Pattern.compile(String.format("%s_%s", tier, rank));
        return Arrays.stream(TierDivisionType.values()).filter(s -> pattern.matcher(s.name()).matches()).findFirst().orElse(TierDivisionType.UNRANKED);
    }
    
    public TierDivisionType getNext()
    {
        return TierDivisionType.values()[Math.max(this.ordinal() - 1, 0)];
    }
}
