package no.stelar7.api.r4j.pojo.lol.staticdata.shared;

import java.io.Serializable;

public class BaseStaticData implements Serializable
{
    private static final long serialVersionUID = 3861893407107172040L;
    
    private String type;
    private String version;
    
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
        final BaseStaticData other = (BaseStaticData) obj;
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
            return other.version == null;
        } else
        {
            return this.version.equals(other.version);
        }
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
        result = (prime * result) + ((this.type == null) ? 0 : this.type.hashCode());
        result = (prime * result) + ((this.version == null) ? 0 : this.version.hashCode());
        return result;
    }
    
    @Override
    public String toString()
    {
        return "BaseStaticData{" +
               "type='" + type + '\'' +
               ", version='" + version + '\'' +
               '}';
    }
}
