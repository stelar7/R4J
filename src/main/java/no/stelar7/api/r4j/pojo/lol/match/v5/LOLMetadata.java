package no.stelar7.api.r4j.pojo.lol.match.v5;

import java.io.Serializable;
import java.util.*;

public class LOLMetadata implements Serializable
{
    private static final long serialVersionUID = -1563260611816090828L;
    
    private String       dataVersion;
    private String       matchId;
    private List<String> participants;
    
    public String getDataVersion()
    {
        return dataVersion;
    }
    
    public String getMatchId()
    {
        return matchId;
    }
    
    public List<String> getParticipantPUUIDs()
    {
        return participants;
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
        LOLMetadata that = (LOLMetadata) o;
        return Objects.equals(dataVersion, that.dataVersion) && Objects.equals(matchId, that.matchId) && Objects.equals(participants, that.participants);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(dataVersion, matchId, participants);
    }
    
    @Override
    public String toString()
    {
        return "LOLMatchMetadata{" +
               "dataVersion='" + dataVersion + '\'' +
               ", matchId='" + matchId + '\'' +
               ", participants=" + participants +
               '}';
    }
}
