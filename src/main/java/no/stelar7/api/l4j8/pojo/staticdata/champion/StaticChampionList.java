package no.stelar7.api.l4j8.pojo.staticdata.champion;

import java.util.*;

public class StaticChampionList
{
    private Map<String, StaticChampion> data;
    private String                      format;
    private Map<String, String>         keys;
    private String                      type;
    private String                      version;
    
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
        final StaticChampionList other = (StaticChampionList) obj;
        if (this.data == null)
        {
            if (other.data != null)
            {
                return false;
            }
        } else if (!this.data.equals(other.data))
        {
            return false;
        }
        if (this.format == null)
        {
            if (other.format != null)
            {
                return false;
            }
        } else if (!this.format.equals(other.format))
        {
            return false;
        }
        if (this.keys == null)
        {
            if (other.keys != null)
            {
                return false;
            }
        } else if (!this.keys.equals(other.keys))
        {
            return false;
        }
        if (this.type == null)
        {
            if (other.type != null)
            {
                return false;
            }
        } else if (!this.type.equals(other.type))
        {
            return false;
        }
        if (this.version == null)
        {
            if (other.version != null)
            {
                return false;
            }
        } else if (!this.version.equals(other.version))
        {
            return false;
        }
        return true;
    }
    
    /**
     * Gets the data.
     *
     * @return the data
     */
    public Map<String, StaticChampion> getData()
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
    public int hashCode()
    {
        final int prime  = 31;
        int       result = 1;
        result = (prime * result) + ((this.data == null) ? 0 : this.data.hashCode());
        result = (prime * result) + ((this.format == null) ? 0 : this.format.hashCode());
        result = (prime * result) + ((this.keys == null) ? 0 : this.keys.hashCode());
        result = (prime * result) + ((this.type == null) ? 0 : this.type.hashCode());
        result = (prime * result) + ((this.version == null) ? 0 : this.version.hashCode());
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
