package no.stelar7.api.l4j8.pojo.shared;

import java.io.Serializable;
import java.util.Objects;

public class BannedChampion implements Serializable
{
    private static final long serialVersionUID = -4431824998067471719L;
    
    private int championId;
    private int pickTurn;
    private int teamId;
    
    
    /**
     * The ID of the banned champion
     * -1 if they did not ban
     *
     * @return long
     */
    public int getChampionId()
    {
        return this.championId;
    }
    
    /**
     * The turn during which the champion was banned
     *
     * @return int
     */
    public int getPickTurn()
    {
        return this.pickTurn;
    }
    
    /**
     * The team that banned this champion
     * @return int
     */
    public int getTeamId()
    {
        return teamId;
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
        BannedChampion that = (BannedChampion) o;
        return championId == that.championId &&
               pickTurn == that.pickTurn &&
               teamId == that.teamId;
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(championId, pickTurn, teamId);
    }
    
    @Override
    public String toString()
    {
        return "BannedChampion{" +
               "championId=" + championId +
               ", pickTurn=" + pickTurn +
               ", teamId=" + teamId +
               '}';
    }
}
