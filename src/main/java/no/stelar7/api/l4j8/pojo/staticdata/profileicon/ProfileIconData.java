package no.stelar7.api.l4j8.pojo.staticdata.profileicon;

import no.stelar7.api.l4j8.pojo.staticdata.shared.BaseStaticData;

import java.util.Map;

public class ProfileIconData extends BaseStaticData
{
    private static final long serialVersionUID = 5125741045029057092L;
    
    private Map<Long, ProfileIconDetails> data;
    
    public Map<Long, ProfileIconDetails> getData()
    {
        return data;
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
        if (!super.equals(o))
        {
            return false;
        }
        
        ProfileIconData that = (ProfileIconData) o;
        
        return (data != null) ? data.equals(that.data) : (that.data == null);
    }
    
    @Override
    public int hashCode()
    {
        int result = super.hashCode();
        result = 31 * result + (data != null ? data.hashCode() : 0);
        return result;
    }
    
    
    @Override
    public String toString()
    {
        return "ProfileIconData{" +
               "data=" + data +
               '}';
    }
}
