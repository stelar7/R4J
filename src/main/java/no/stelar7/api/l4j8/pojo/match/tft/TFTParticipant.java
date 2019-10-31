package no.stelar7.api.l4j8.pojo.match.tft;

import java.io.Serializable;
import java.util.*;

public class TFTParticipant implements Serializable
{
    private String         puuid;
    private int            level;
    private int            placement;
    private int            last_round;
    private int            players_eliminated;
    private int            total_damage_to_players;
    private int            gold_left;
    private float          time_eliminated;
    private TFTCompanion   companion;
    private List<TFTTrait> traits;
    private List<TFTUnit>  units;
    
    public String getPuuid()
    {
        return puuid;
    }
    
    public int getLevel()
    {
        return level;
    }
    
    public int getPlacement()
    {
        return placement;
    }
    
    public int getLastRound()
    {
        return last_round;
    }
    
    public int getPlayersEliminated()
    {
        return players_eliminated;
    }
    
    public int getTotalDamageToPlayers()
    {
        return total_damage_to_players;
    }
    
    public int getGoldLeft()
    {
        return gold_left;
    }
    
    public float getTimeEliminated()
    {
        return time_eliminated;
    }
    
    public TFTCompanion getCompanion()
    {
        return companion;
    }
    
    public List<TFTTrait> getTraits()
    {
        return traits;
    }
    
    public List<TFTUnit> getUnits()
    {
        return units;
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
        TFTParticipant that = (TFTParticipant) o;
        return level == that.level &&
               placement == that.placement &&
               last_round == that.last_round &&
               players_eliminated == that.players_eliminated &&
               total_damage_to_players == that.total_damage_to_players &&
               gold_left == that.gold_left &&
               Float.compare(that.time_eliminated, time_eliminated) == 0 &&
               Objects.equals(puuid, that.puuid) &&
               Objects.equals(companion, that.companion) &&
               Objects.equals(traits, that.traits) &&
               Objects.equals(units, that.units);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(puuid, level, placement, last_round, players_eliminated, total_damage_to_players, gold_left, time_eliminated, companion, traits, units);
    }
    
    @Override
    public String toString()
    {
        return "TFTParticipant{" +
               "puuid='" + puuid + '\'' +
               ", level=" + level +
               ", placement=" + placement +
               ", lastRound=" + last_round +
               ", players_eliminated=" + players_eliminated +
               ", total_damage_to_players=" + total_damage_to_players +
               ", gold_left=" + gold_left +
               ", time_eliminated=" + time_eliminated +
               ", companion=" + companion +
               ", traits=" + traits +
               ", units=" + units +
               '}';
    }
}
