package no.stelar7.api.l4j8.pojo.staticdata.profileicon;

import java.util.Map;

public class ProfileIconData
{
    private Map<String, ProfileIconDetails> data;
    private String                          version;
    private String                          type;
    
    public Map<String, ProfileIconDetails> getData()
    {
        return data;
    }
    
    public String getVersion()
    {
        return version;
    }
    
    public String getType()
    {
        return type;
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
        
        ProfileIconData that = (ProfileIconData) o;
        
        if ((data != null) ? !data.equals(that.data) : (that.data != null))
        {
            return false;
        }
        if ((version != null) ? !version.equals(that.version) : (that.version != null))
        {
            return false;
        }
        return (type != null) ? type.equals(that.type) : (that.type == null);
    }
    
    @Override
    public int hashCode()
    {
        int result = data != null ? data.hashCode() : 0;
        result = 31 * result + (version != null ? version.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
    
    
    @Override
    public String toString()
    {
        return "ProfileIconData{" +
               "data=" + data +
               ", version='" + version + '\'' +
               ", type='" + type + '\'' +
               '}';
    }
}
