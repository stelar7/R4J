package no.stelar7.api.r4j.basic.constants.types.lol;

import no.stelar7.api.r4j.basic.constants.types.CodedEnum;

import java.util.*;
import java.util.regex.Pattern;

public enum TierDivisionType implements CodedEnum
{
    CHALLENGER_I,
    
    GRANDMASTER_I,
    
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
    
    public String getTier()
    {
        if (!name().contains("_"))
        {
            return null;
        }
        
        return name().split("_")[0];
    }
    
    public String getDivision()
    {
        if (!name().contains("_"))
        {
            return null;
        }
        
        return name().split("_")[1];
    }
    
    
    public Optional<TierDivisionType> getFromCode(String tierAndRank)
    {
        Pattern pattern = Pattern.compile(tierAndRank);
        return Arrays.stream(TierDivisionType.values()).filter(s -> pattern.matcher(s.name()).matches()).findFirst();
    }
    
    @Override
    public String prettyName()
    {
        switch (this)
        {
            case CHALLENGER_I:
                return "Challenger";
            case GRANDMASTER_I:
                return "Grandmaster";
            case MASTER_I:
                return "Master";
            case DIAMOND_I:
                return "Diamond I";
            case DIAMOND_II:
                return "Diamond II";
            case DIAMOND_III:
                return "Diamond III";
            case DIAMOND_IV:
                return "Diamond IV";
            case DIAMOND_V:
                return "Diamond V";
            case PLATINUM_I:
                return "Platinum I";
            case PLATINUM_II:
                return "Platinum II";
            case PLATINUM_III:
                return "Platinum III";
            case PLATINUM_IV:
                return "Platinum IV";
            case PLATINUM_V:
                return "Platinum V";
            case GOLD_I:
                return "Gold I";
            case GOLD_II:
                return "Gold II";
            case GOLD_III:
                return "Gold III";
            case GOLD_IV:
                return "Gold IV";
            case GOLD_V:
                return "Gold V";
            case SILVER_I:
                return "Silver I";
            case SILVER_II:
                return "Silver II";
            case SILVER_III:
                return "Silver III";
            case SILVER_IV:
                return "Silver IV";
            case SILVER_V:
                return "Silver V";
            case BRONZE_I:
                return "Bronze I";
            case BRONZE_II:
                return "Bronze II";
            case BRONZE_III:
                return "Bronze III";
            case BRONZE_IV:
                return "Bronze IV";
            case BRONZE_V:
                return "Bronze V";
            case IRON_I:
                return "Iron I";
            case IRON_II:
                return "Iron II";
            case IRON_III:
                return "Iron III";
            case IRON_IV:
                return "Iron IV";
            case IRON_V:
                return "Iron V";
            case UNRANKED:
                return "Unranked";
            default:
                return "This enum does not have a pretty name";
        }
    }
    
    public static TierDivisionType getFromCodes(String tier, String rank)
    {
        Pattern pattern = Pattern.compile(String.format("%s_%s", tier, rank));
        return Arrays.stream(TierDivisionType.values()).filter(s -> pattern.matcher(s.name()).matches()).findFirst().orElse(TierDivisionType.UNRANKED);
    }
}
