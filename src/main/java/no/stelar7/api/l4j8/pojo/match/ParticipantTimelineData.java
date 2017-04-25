package no.stelar7.api.l4j8.pojo.match;

public class ParticipantTimelineData
{
    
    private Double tenToTwenty;
    private Double thirtyToEnd;
    private Double twentyToThirty;
    private Double zeroToTen;
    
    /**
     * Gets the ten to twenty.
     *
     * @return the ten to twenty
     */
    public Double getTenToTwenty()
    {
        return this.tenToTwenty;
    }
    
    /**
     * Gets the thirty to end.
     *
     * @return the thirty to end
     */
    public Double getThirtyToEnd()
    {
        return this.thirtyToEnd;
    }
    
    /**
     * Gets the twenty to thirty.
     *
     * @return the twenty to thirty
     */
    public Double getTwentyToThirty()
    {
        return this.twentyToThirty;
    }
    
    /**
     * Gets the zero to ten.
     *
     * @return the zero to ten
     */
    public Double getZeroToTen()
    {
        return this.zeroToTen;
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
        
        ParticipantTimelineData that = (ParticipantTimelineData) o;
        
        if ((tenToTwenty != null) ? !tenToTwenty.equals(that.tenToTwenty) : (that.tenToTwenty != null))
        {
            return false;
        }
        if ((thirtyToEnd != null) ? !thirtyToEnd.equals(that.thirtyToEnd) : (that.thirtyToEnd != null))
        {
            return false;
        }
        if ((twentyToThirty != null) ? !twentyToThirty.equals(that.twentyToThirty) : (that.twentyToThirty != null))
        {
            return false;
        }
        return (zeroToTen != null) ? zeroToTen.equals(that.zeroToTen) : (that.zeroToTen == null);
    }
    
    @Override
    public int hashCode()
    {
        int result = tenToTwenty != null ? tenToTwenty.hashCode() : 0;
        result = 31 * result + (thirtyToEnd != null ? thirtyToEnd.hashCode() : 0);
        result = 31 * result + (twentyToThirty != null ? twentyToThirty.hashCode() : 0);
        result = 31 * result + (zeroToTen != null ? zeroToTen.hashCode() : 0);
        return result;
    }
    
    @Override
    public String toString()
    {
        return "ParticipantTimelineData{" +
               "tenToTwenty=" + tenToTwenty +
               ", thirtyToEnd=" + thirtyToEnd +
               ", twentyToThirty=" + twentyToThirty +
               ", zeroToTen=" + zeroToTen +
               '}';
    }
}
