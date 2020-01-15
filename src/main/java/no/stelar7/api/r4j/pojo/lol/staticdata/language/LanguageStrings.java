package no.stelar7.api.r4j.pojo.lol.staticdata.language;

import no.stelar7.api.r4j.pojo.lol.staticdata.shared.BaseStaticData;

import java.util.Map;

public class LanguageStrings extends BaseStaticData
{
    private static final long serialVersionUID = -1930488558119931986L;
    
    private Map<String, String> data;
    
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
        final LanguageStrings other = (LanguageStrings) obj;
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
    public Map<String, String> getData()
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
        return "LanguageStrings{" +
               "data=" + data +
               '}';
    }
}
