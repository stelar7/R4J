package no.stelar7.api.l4j8.basic.constants.types;

import java.util.*;
import java.util.regex.Pattern;

public enum TierDivisionType implements CodedEnum
{
    CHALLENGER_I,
    
    GRANDMASTER_I,
    GRANDMASTER_II,
    GRANDMASTER_III,
    GRANDMASTER_IV,
    GRANDMASTER_V,
    
    MASTER_I,
    MASTER_II,
    MASTER_III,
    MASTER_IV,
    MASTER_V,
    
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
    
    IRON_I,
    IRON_II,
    IRON_III,
    IRON_IV,
    IRON_V,
    
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
    
    
    public Optional<TierDivisionType> getFromCode(String tierAndRank)
    {
        Pattern pattern = Pattern.compile(tierAndRank);
        return Arrays.stream(TierDivisionType.values()).filter(s -> pattern.matcher(s.name()).matches()).findFirst();
    }
    
    public static TierDivisionType getFromCodes(String tier, String rank)
    {
        Pattern pattern = Pattern.compile(String.format("%s_%s", tier, rank));
        return Arrays.stream(TierDivisionType.values()).filter(s -> pattern.matcher(s.name()).matches()).findFirst().orElse(TierDivisionType.UNRANKED);
    }
}
