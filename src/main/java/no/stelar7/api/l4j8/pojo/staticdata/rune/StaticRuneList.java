package no.stelar7.api.l4j8.pojo.staticdata.rune;

import no.stelar7.api.l4j8.pojo.staticdata.shared.*;

import java.util.*;

public class StaticRuneList
{
    private BasicData             basic;
    private Map<Integer, StaticRune> data;
    
    
    
    /**
     * Gets the basic.
     *
     * @return the basic
     */
    public BasicData getBasic()
    {
        return this.basic;
    }
    
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
        
        if ((basic != null) ? !basic.equals(that.basic) : (that.basic != null))
        {
            return false;
        }
        return (data != null) ? data.equals(that.data) : (that.data == null);
    }
    
    @Override
    public int hashCode()
    {
        int result = basic != null ? basic.hashCode() : 0;
        result = 31 * result + (data != null ? data.hashCode() : 0);
        return result;
    }
    
    @Override
    public String toString()
    {
        return "StaticRuneList{" +
               "basic=" + basic +
               ", data=" + data +
               '}';
    }
}
