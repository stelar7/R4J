package no.stelar7.api.l4j8.pojo.staticdata.rune;

import java.io.Serializable;
import java.util.Map;

public class StaticRuneList implements Serializable
{
    private static final long serialVersionUID = -1837520049667698339L;
    
    private Map<Integer, StaticRune> data;
    
    
    /**
     * Gets the data.
     *
     * @return the data
     */
    public Map<Integer, StaticRune> getData()
    {
        return this.data;
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
        
        StaticRuneList that = (StaticRuneList) o;
        
        return (data != null) ? data.equals(that.data) : (that.data == null);
    }
    
    @Override
    public int hashCode()
    {
        return data != null ? data.hashCode() : 0;
    }
}
