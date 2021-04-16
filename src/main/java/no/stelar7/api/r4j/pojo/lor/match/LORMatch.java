package no.stelar7.api.r4j.pojo.lor.match;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.*;

public class LORMatch
{
    private String               game_mode;
    private String               game_type;
    private String               game_start_time_utc;
    private String               game_version;
    private List<LORParticipant> players;
    private Integer              total_turn_count;
    
    public String getGameMode()
    {
        return game_mode;
    }
    
    public String getGameType()
    {
        return game_type;
    }
    
    public String getGameStartTimeUTC()
    {
        return game_start_time_utc;
    }
    
    public ZonedDateTime getGameStartAsDate()
    {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        TemporalAccessor  dateTime  = formatter.parse(game_start_time_utc);
        return ZonedDateTime.from(dateTime);
    }
    
    public String getGameVersion()
    {
        return game_version;
    }
    
    public List<LORParticipant> getPlayers()
    {
        return players;
    }
    
    public LORParticipant getWinner()
    {
        return players.stream().filter(LORParticipant::didWin).findFirst().orElse(null);
    }
    
    public Integer getTotalTurnCount()
    {
        return total_turn_count;
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
        LORMatch lorMatch = (LORMatch) o;
        return Objects.equals(game_mode, lorMatch.game_mode) &&
               Objects.equals(game_type, lorMatch.game_type) &&
               Objects.equals(game_start_time_utc, lorMatch.game_start_time_utc) &&
               Objects.equals(game_version, lorMatch.game_version) &&
               Objects.equals(players, lorMatch.players) &&
               Objects.equals(total_turn_count, lorMatch.total_turn_count);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(game_mode, game_type, game_start_time_utc, game_version, players, total_turn_count);
    }
    
    @Override
    public String toString()
    {
        return "LORMatch{" +
               "game_mode='" + game_mode + '\'' +
               ", game_type='" + game_type + '\'' +
               ", game_start_time_utc='" + game_start_time_utc + '\'' +
               ", game_version='" + game_version + '\'' +
               ", players=" + players +
               ", total_turn_count=" + total_turn_count +
               '}';
    }
}
