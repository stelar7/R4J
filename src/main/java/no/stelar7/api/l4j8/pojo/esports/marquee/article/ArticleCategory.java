package no.stelar7.api.l4j8.pojo.esports.marquee.article;

public class ArticleCategory
{
    
    
    private String machineName;
    private String displayName;
    
    public String getMachineName()
    {
        return machineName;
    }
    
    
    public String getDisplayName()
    {
        return displayName;
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
        
        ArticleCategory that = (ArticleCategory) o;
        
        if ((machineName != null) ? !machineName.equals(that.machineName) : (that.machineName != null))
        {
            return false;
        }
        return (displayName != null) ? displayName.equals(that.displayName) : (that.displayName == null);
    }
    
    @Override
    public int hashCode()
    {
        int result = machineName != null ? machineName.hashCode() : 0;
        result = 31 * result + (displayName != null ? displayName.hashCode() : 0);
        return result;
    }
    
    @Override
    public String toString()
    {
        return "ArticleCategory{" +
               "machineName='" + machineName + '\'' +
               ", displayName='" + displayName + '\'' +
               '}';
    }
}
