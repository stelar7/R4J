package no.stelar7.api.r4j.pojo.lol.match.lol;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Objects;

public class ParticipantTimelineDeltaMapMap implements Serializable
{
    private static final long serialVersionUID = 3538983437183521194L;
    
    @SerializedName("0-10")
    private float zeroTen;
    @SerializedName("10-20")
    private float tenTwenty;
    @SerializedName("20-30")
    private float twentyThirty;
    @SerializedName("30-end")
    private float thirtyEnd;
    
    public float getZeroTen()
    {
        return zeroTen;
    }
    
    public float getTenTwenty()
    {
        return tenTwenty;
    }
    
    public float getTwentyThirty()
    {
        return twentyThirty;
    }
    
    public float getThirtyEnd()
    {
        return thirtyEnd;
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
        ParticipantTimelineDeltaMapMap that = (ParticipantTimelineDeltaMapMap) o;
        return Float.compare(that.zeroTen, zeroTen) == 0 &&
               Float.compare(that.tenTwenty, tenTwenty) == 0 &&
               Float.compare(that.twentyThirty, twentyThirty) == 0 &&
               Float.compare(that.thirtyEnd, thirtyEnd) == 0;
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(zeroTen, tenTwenty, twentyThirty, thirtyEnd);
    }
    
    @Override
    public String toString()
    {
        return "ParticipantTimelineDeltaMapMap{" +
               "zeroTen=" + zeroTen +
               ", tenTwenty=" + tenTwenty +
               ", twentyThirty=" + twentyThirty +
               ", thirtyEnd=" + thirtyEnd +
               '}';
    }
}
