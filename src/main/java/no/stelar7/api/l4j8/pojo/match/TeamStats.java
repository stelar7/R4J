package no.stelar7.api.l4j8.pojo.match;

import no.stelar7.api.l4j8.basic.constants.types.TeamType;
import no.stelar7.api.l4j8.pojo.shared.*;

import java.util.*;

public class TeamStats
{
    private List<BannedChampion> bans;
    private Integer              baronKills;
    private Long                 dominionVictoryScore;
    private Integer              dragonKills;
    private boolean              firstBaron;
    private boolean              firstBlood;
    private boolean              firstDragon;
    private boolean              firstInhibitor;
    private boolean              firstTower;
    private Integer              inhibitorKills;
    private TeamType             teamId;
    private Integer              towerKills;
    private Integer              vilemawKills;
    private String               win;
    private boolean              firstRiftHerald;
    private Integer              riftHeraldKills;
    
    
    /**
     * Gets the bans.
     *
     * @return the bans
     */
    public List<BannedChampion> getBans()
    {
        return this.bans;
    }
    
    /**
     * Gets the baron kills.
     *
     * @return the baron kills
     */
    public Integer getBaronKills()
    {
        return this.baronKills;
    }
    
    /**
     * Gets the dominion victory score.
     *
     * @return the dominion victory score
     */
    public Long getDominionVictoryScore()
    {
        return this.dominionVictoryScore;
    }
    
    /**
     * Gets the dragon kills.
     *
     * @return the dragon kills
     */
    public Integer getDragonKills()
    {
        return this.dragonKills;
    }
    
    /**
     * Gets the inhibitor kills.
     *
     * @return the inhibitor kills
     */
    public Integer getInhibitorKills()
    {
        return this.inhibitorKills;
    }
    
    /**
     * Number of times the team killed rift herald.
     *
     * @return the rift herald kills
     */
    public Integer getRiftHeraldKills()
    {
        return this.riftHeraldKills;
    }
    
    /**
     * Gets the team id.
     *
     * @return the team id
     */
    public TeamType getTeamType()
    {
        return this.teamId;
    }
    
    /**
     * Gets the tower kills.
     *
     * @return the tower kills
     */
    public Integer getTowerKills()
    {
        return this.towerKills;
    }
    
    /**
     * Gets the vilemaw kills.
     *
     * @return the vilemaw kills
     */
    public Integer getVilemawKills()
    {
        return this.vilemawKills;
    }
    
    @Override
    public int hashCode()
    {
        int result = bans != null ? bans.hashCode() : 0;
        result = 31 * result + (baronKills != null ? baronKills.hashCode() : 0);
        result = 31 * result + (dominionVictoryScore != null ? dominionVictoryScore.hashCode() : 0);
        result = 31 * result + (dragonKills != null ? dragonKills.hashCode() : 0);
        result = 31 * result + (firstBaron ? 1 : 0);
        result = 31 * result + (firstBlood ? 1 : 0);
        result = 31 * result + (firstDragon ? 1 : 0);
        result = 31 * result + (firstInhibitor ? 1 : 0);
        result = 31 * result + (firstTower ? 1 : 0);
        result = 31 * result + (inhibitorKills != null ? inhibitorKills.hashCode() : 0);
        result = 31 * result + (teamId != null ? teamId.hashCode() : 0);
        result = 31 * result + (towerKills != null ? towerKills.hashCode() : 0);
        result = 31 * result + (vilemawKills != null ? vilemawKills.hashCode() : 0);
        result = 31 * result + (win != null ? win.hashCode() : 0);
        result = 31 * result + (firstRiftHerald ? 1 : 0);
        result = 31 * result + (riftHeraldKills != null ? riftHeraldKills.hashCode() : 0);
        return result;
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
        
        TeamStats teamStats = (TeamStats) o;
        
        if (firstBaron != teamStats.firstBaron)
        {
            return false;
        }
        if (firstBlood != teamStats.firstBlood)
        {
            return false;
        }
        if (firstDragon != teamStats.firstDragon)
        {
            return false;
        }
        if (firstInhibitor != teamStats.firstInhibitor)
        {
            return false;
        }
        if (firstTower != teamStats.firstTower)
        {
            return false;
        }
        if (firstRiftHerald != teamStats.firstRiftHerald)
        {
            return false;
        }
        if ((bans != null) ? !bans.equals(teamStats.bans) : (teamStats.bans != null))
        {
            return false;
        }
        if ((baronKills != null) ? !baronKills.equals(teamStats.baronKills) : (teamStats.baronKills != null))
        {
            return false;
        }
        if ((dominionVictoryScore != null) ? !dominionVictoryScore.equals(teamStats.dominionVictoryScore) : (teamStats.dominionVictoryScore != null))
        {
            return false;
        }
        if ((dragonKills != null) ? !dragonKills.equals(teamStats.dragonKills) : (teamStats.dragonKills != null))
        {
            return false;
        }
        if ((inhibitorKills != null) ? !inhibitorKills.equals(teamStats.inhibitorKills) : (teamStats.inhibitorKills != null))
        {
            return false;
        }
        if (teamId != teamStats.teamId)
        {
            return false;
        }
        if ((towerKills != null) ? !towerKills.equals(teamStats.towerKills) : (teamStats.towerKills != null))
        {
            return false;
        }
        if ((vilemawKills != null) ? !vilemawKills.equals(teamStats.vilemawKills) : (teamStats.vilemawKills != null))
        {
            return false;
        }
        if ((win != null) ? !win.equals(teamStats.win) : (teamStats.win != null))
        {
            return false;
        }
        return (riftHeraldKills != null) ? riftHeraldKills.equals(teamStats.riftHeraldKills) : (teamStats.riftHeraldKills == null);
    }
    
    /**
     * Checks if is first baron.
     *
     * @return the boolean
     */
    public boolean isFirstBaron()
    {
        return this.firstBaron;
    }
    
    /**
     * Checks if is first blood.
     *
     * @return the boolean
     */
    public boolean isFirstBlood()
    {
        return this.firstBlood;
    }
    
    /**
     * Checks if is first dragon.
     *
     * @return the boolean
     */
    public boolean isFirstDragon()
    {
        return this.firstDragon;
    }
    
    /**
     * Checks if is first inhibitor.
     *
     * @return the boolean
     */
    public boolean isFirstInhibitor()
    {
        return this.firstInhibitor;
    }
    
    /**
     * Checks if is first tower.
     *
     * @return the boolean
     */
    public boolean isFirstTower()
    {
        return this.firstTower;
    }
    
    /**
     * Is first rift herald boolean.
     *
     * @return the boolean
     */
    public boolean isFirstRiftHerald()
    {
        return firstRiftHerald;
    }
    
    /**
     * Checks if is winner.
     *
     * @return the boolean
     */
    public boolean isWinner()
    {
        return "Win".equalsIgnoreCase(this.win);
    }
    
    @Override
    public String toString()
    {
        return "TeamType{" +
               "bans=" + bans +
               ", baronKills=" + baronKills +
               ", dominionVictoryScore=" + dominionVictoryScore +
               ", dragonKills=" + dragonKills +
               ", firstBaron=" + firstBaron +
               ", firstBlood=" + firstBlood +
               ", firstDragon=" + firstDragon +
               ", firstInhibitor=" + firstInhibitor +
               ", firstTower=" + firstTower +
               ", inhibitorKills=" + inhibitorKills +
               ", teamId=" + teamId +
               ", towerKills=" + towerKills +
               ", vilemawKills=" + vilemawKills +
               ", winner=" + win +
               ", firstRiftHerald=" + firstRiftHerald +
               ", riftHeraldKills=" + riftHeraldKills +
               '}';
    }
}
