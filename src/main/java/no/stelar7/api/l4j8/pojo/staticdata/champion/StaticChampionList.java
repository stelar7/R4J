package no.stelar7.api.l4j8.pojo.staticdata.champion;

import java.io.Serializable;
import java.util.Map;

public class StaticChampionList implements Serializable
{
    private static final long serialVersionUID = -6617734110275400559L;
    
    private Map<Integer, StaticChampion> data;
    private String                       format;
    private Map<String, String>          keys;
    private String                       type;
    private String                       version;
    
    
    /**
     * Gets the data.
     *
     * @return the data
     */
    public Map<Integer, StaticChampion> getData()
    {
        return this.data;
    }
    
    /**
     * Gets the format.
     *
     * @return the format
     */
    public String getFormat()
    {
        return this.format;
    }
    
    /**
     * Gets the keys.
     *
     * @return the keys
     */
    public Map<String, String> getKeys()
    {
        return this.keys;
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
    
    /**
     * Gets the version.
     *
     * @return the version
     */
    public String getVersion()
    {
        return this.version;
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
        
        StaticChampionList that = (StaticChampionList) o;
        
        if ((data != null) ? !data.equals(that.data) : (that.data != null))
        {
            return false;
        }
        if ((format != null) ? !format.equals(that.format) : (that.format != null))
        {
            return false;
        }
        if ((keys != null) ? !keys.equals(that.keys) : (that.keys != null))
        {
            return false;
        }
        if ((type != null) ? !type.equals(that.type) : (that.type != null))
        {
            return false;
        }
        return (version != null) ? version.equals(that.version) : (that.version == null);
    }
    
    @Override
    public int hashCode()
    {
        int result = data != null ? data.hashCode() : 0;
        result = 31 * result + (format != null ? format.hashCode() : 0);
        result = 31 * result + (keys != null ? keys.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (version != null ? version.hashCode() : 0);
        return result;
    }
    
    @Override
    public String toString()
    {
        return "StaticChampionList{" +
               "data=" + data +
               ", format='" + format + '\'' +
               ", keys=" + keys +
               ", type='" + type + '\'' +
               ", version='" + version + '\'' +
               '}';
    }
}
