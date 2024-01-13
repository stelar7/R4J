package no.stelar7.api.r4j.pojo.lol.spectator.v5;

import no.stelar7.api.r4j.basic.constants.types.lol.TeamType;

import java.io.Serializable;
import java.util.Objects;

public class BannedChampions implements Serializable
{
    private static final long serialVersionUID = -7647626817552576139L;
    
    private long     championId;
    private TeamType teamId;
    private long     pickTurn;
    
    public long getChampionId()
    {
        return championId;
    }
    
    public TeamType getTeamId()
    {
        return teamId;
    }
    
    public long getPickTurn()
    {
        return pickTurn;
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
        BannedChampions that = (BannedChampions) o;
        return championId == that.championId && pickTurn == that.pickTurn && teamId == that.teamId;
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(championId, teamId, pickTurn);
    }
    
    @Override
    public String toString()
    {
        return "BannedChampions{" +
               "championId=" + championId +
               ", teamId=" + teamId +
               ", pickTurn=" + pickTurn +
               '}';
    }
}
