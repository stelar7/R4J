package no.stelar7.api.l4j8.pojo.match;

import java.util.*;

public class TFTMetadata
{
    private String       data_version;
    private List<String> participants;
    private String       match_id;
    
    public String getDataVersion()
    {
        return data_version;
    }
    
    public List<String> getParticipants()
    {
        return participants;
    }
    
    public String getMatchId()
    {
        return match_id;
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
        TFTMetadata that = (TFTMetadata) o;
        return Objects.equals(data_version, that.data_version) &&
               Objects.equals(participants, that.participants) &&
               Objects.equals(match_id, that.match_id);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(data_version, participants, match_id);
    }
    
    @Override
    public String toString()
    {
        return "TFTMetadata{" +
               "data_version='" + data_version + '\'' +
               ", participants=" + participants +
               ", match_id='" + match_id + '\'' +
               '}';
    }
}
