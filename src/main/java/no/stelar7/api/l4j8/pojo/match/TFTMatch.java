package no.stelar7.api.l4j8.pojo.match;

import no.stelar7.api.l4j8.basic.constants.types.GameQueueType;

import java.io.Serializable;
import java.time.*;
import java.util.*;

public class TFTMatch implements Serializable
{
    private long                 game_datetime;
    private String               game_id;
    private int                  game_length;
    private String               game_version;
    private GameQueueType        queue_id;
    private int                  tft_set_number;
    private List<TFTParticipant> participants;
    
    public long getMatchCreation()
    {
        return game_datetime;
    }
    
    public ZonedDateTime getMatchCreationAsDate()
    {
        return ZonedDateTime.ofInstant(Instant.ofEpochMilli(this.game_datetime), ZoneOffset.UTC);
    }
    
    public String getGameId()
    {
        return game_id;
    }
    
    public int getDuration()
    {
        return game_length;
    }
    
    public String getVersion()
    {
        return game_version;
    }
    
    public GameQueueType getQueue()
    {
        return queue_id;
    }
    
    public int getChampionSetId()
    {
        return tft_set_number;
    }
    
    public List<TFTParticipant> getParticipants()
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
        TFTMatch tftMatch = (TFTMatch) o;
        return game_datetime == tftMatch.game_datetime &&
               game_length == tftMatch.game_length &&
               tft_set_number == tftMatch.tft_set_number &&
               Objects.equals(game_id, tftMatch.game_id) &&
               Objects.equals(game_version, tftMatch.game_version) &&
               queue_id == tftMatch.queue_id &&
               Objects.equals(participants, tftMatch.participants);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(game_datetime, game_id, game_length, game_version, queue_id, tft_set_number, participants);
    }
    
    @Override
    public String toString()
    {
        return "TFTMatch{" +
               "game_datetime=" + game_datetime +
               ", game_id='" + game_id + '\'' +
               ", game_length=" + game_length +
               ", game_version='" + game_version + '\'' +
               ", queue_id=" + queue_id +
               ", tft_set_number=" + tft_set_number +
               ", participants=" + participants +
               '}';
    }
}
