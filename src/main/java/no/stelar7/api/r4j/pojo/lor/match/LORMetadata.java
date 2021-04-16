package no.stelar7.api.r4j.pojo.lor.match;

import java.util.*;

public class LORMetadata
{
    private String       data_version;
    private String       match_id;
    private List<String> participants;
    
    public String getDataVersion()
    {
        return data_version;
    }
    
    public String getMatchID()
    {
        return match_id;
    }
    
    public List<String> getParticipants()
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
        LORMetadata that = (LORMetadata) o;
        return Objects.equals(data_version, that.data_version) &&
               Objects.equals(match_id, that.match_id) &&
               Objects.equals(participants, that.participants);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(data_version, match_id, participants);
    }
    
    @Override
    public String toString()
    {
        return "LORMetadata{" +
               "data_version='" + data_version + '\'' +
               ", match_id='" + match_id + '\'' +
               ", participants=" + participants +
               '}';
    }
}
