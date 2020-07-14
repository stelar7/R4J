package no.stelar7.api.r4j.pojo.shared;

import java.util.Objects;

public class RiotAccount
{
    private String puuid;
    private String gameName;
    private String tagLine;
    
    public String getPUUID()
    {
        return puuid;
    }
    
    public String getName()
    {
        return gameName;
    }
    
    public String getTag()
    {
        return tagLine;
    }
    
    @Override
    public String toString()
    {
        return "RiotAccount{" +
               "puuid='" + puuid + '\'' +
               ", gameName='" + gameName + '\'' +
               ", tagLine='" + tagLine + '\'' +
               '}';
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
        RiotAccount that = (RiotAccount) o;
        return Objects.equals(puuid, that.puuid) &&
               Objects.equals(gameName, that.gameName) &&
               Objects.equals(tagLine, that.tagLine);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(puuid, gameName, tagLine);
    }
}
