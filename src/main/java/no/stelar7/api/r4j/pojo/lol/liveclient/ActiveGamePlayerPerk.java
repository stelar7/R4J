package no.stelar7.api.r4j.pojo.lol.liveclient;

import java.util.Objects;

public class ActiveGamePlayerPerk
{
    private String displayName;
    private int    id;
    private String rawDescription;
    private String rawDisplayName;
    
    public String getDisplayName()
    {
        return displayName;
    }
    
    public int getId()
    {
        return id;
    }
    
    public String getRawDescription()
    {
        return rawDescription;
    }
    
    public String getRawDisplayName()
    {
        return rawDisplayName;
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
        ActiveGamePlayerPerk that = (ActiveGamePlayerPerk) o;
        return id == that.id &&
               Objects.equals(displayName, that.displayName) &&
               Objects.equals(rawDescription, that.rawDescription) &&
               Objects.equals(rawDisplayName, that.rawDisplayName);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(displayName, id, rawDescription, rawDisplayName);
    }
    
    @Override
    public String toString()
    {
        return "ActiveGamePlayerPerk{" +
               "displayName='" + displayName + '\'' +
               ", id=" + id +
               ", rawDescription='" + rawDescription + '\'' +
               ", rawDisplayName='" + rawDisplayName + '\'' +
               '}';
    }
}
