package no.stelar7.api.r4j.pojo.lol.liveclient;

import java.util.Objects;

public class ActiveGamePlayerSummonerSpell
{
    private String displayName;
    private String rawDescription;
    private String rawDisplayName;
    
    public String getDisplayName()
    {
        return displayName;
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
        ActiveGamePlayerSummonerSpell that = (ActiveGamePlayerSummonerSpell) o;
        return Objects.equals(displayName, that.displayName) &&
               Objects.equals(rawDescription, that.rawDescription) &&
               Objects.equals(rawDisplayName, that.rawDisplayName);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(displayName, rawDescription, rawDisplayName);
    }
    
    @Override
    public String toString()
    {
        return "ActiveGamePlayerSummonerSpell{" +
               "displayName='" + displayName + '\'' +
               ", rawDescription='" + rawDescription + '\'' +
               ", rawDisplayName='" + rawDisplayName + '\'' +
               '}';
    }
}
