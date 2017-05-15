package no.stelar7.api.l4j8.pojo.staticdata.shared;

public class MetaData
{
    private boolean isRune;
    private String  tier;
    private String  type;
    
    
    /**
     * Gets the checks if is rune.
     *
     * @return the checks if is rune
     */
    public boolean getIsRune()
    {
        return this.isRune;
    }
    
    /**
     * Gets the tier.
     *
     * @return the tier
     */
    public String getTier()
    {
        return this.tier;
    }
    
    /**
     * Gets the type.
     *
     * @return the type
     */
    public String getType()
    {
        return this.type;
    }
    
    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        
        MetaData metaData = (MetaData) o;
        
        if (isRune != metaData.isRune)
        {
            return false;
        }
        if ((tier != null) ? !tier.equals(metaData.tier) : (metaData.tier != null))
        {
            return false;
        }
        return (type != null) ? type.equals(metaData.type) : (metaData.type == null);
    }
    
    @Override
    public int hashCode()
    {
        int result = (isRune ? 1 : 0);
        result = 31 * result + (tier != null ? tier.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
    
    @Override
    public String toString()
    {
        return "MetaData{" +
               "isRune=" + isRune +
               ", tier='" + tier + '\'' +
               ", type='" + type + '\'' +
               '}';
    }
}
