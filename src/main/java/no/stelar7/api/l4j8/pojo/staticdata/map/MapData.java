package no.stelar7.api.l4j8.pojo.staticdata.map;

import no.stelar7.api.l4j8.pojo.staticdata.shared.BaseStaticData;

import java.util.Map;

public class MapData extends BaseStaticData
{
    private static final long serialVersionUID = 6338742347984604023L;
    
    private Map<String, MapDetails> data;
    
    @Override
    public boolean equals(final Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (this.getClass() != obj.getClass())
        {
            return false;
        }
        final MapData other = (MapData) obj;
        if (this.data == null)
        {
            return other.data == null;
        } else
        {
            return this.data.equals(other.data);
        }
    
    }
    
    /**
     * Gets the data.
     *
     * @return the data
     */
    public Map<String, MapDetails> getData()
    {
        return this.data;
    }
    
    @Override
    public int hashCode()
    {
        final int prime  = 31;
        int       result = 1;
        result = (prime * result) + ((this.data == null) ? 0 : this.data.hashCode());
        result = (prime * result) + super.hashCode();
        return result;
    }
    
    @Override
    public String toString()
    {
        return "MapData{" +
               "data=" + data +
               '}';
    }
}
