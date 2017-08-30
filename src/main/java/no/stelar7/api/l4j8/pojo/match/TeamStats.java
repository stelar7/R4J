package no.stelar7.api.l4j8.pojo.match;

import no.stelar7.api.l4j8.basic.constants.types.TeamType;
import no.stelar7.api.l4j8.pojo.shared.BannedChampion;

import java.io.Serializable;
import java.util.List;

public class TeamStats implements Serializable
{
    private List<BannedChampion> bans;
    private int                  baronKills;
    private long                 dominionVictoryScore;
    private int                  dragonKills;
    private boolean              firstBaron;
    private boolean              firstBlood;
    private boolean              firstDragon;
    private boolean              firstInhibitor;
    private boolean              firstTower;
    private int                  inhibitorKills;
    private TeamType             teamId;
    private int                  towerKills;
    private int                  vilemawKills;
    private String               win;
    private boolean              firstRiftHerald;
    private int                  riftHeraldKills;
    
    
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
    public int getBaronKills()
    {
        return this.baronKills;
    }
    
    /**
     * Gets the dominion victory score.
     *
     * @return the dominion victory score
     */
    public long getDominionVictoryScore()
    {
        return this.dominionVictoryScore;
    }
    
    /**
     * Gets the dragon kills.
     *
     * @return the dragon kills
     */
    public int getDragonKills()
    {
        return this.dragonKills;
    }
    
    /**
     * Gets the inhibitor kills.
     *
     * @return the inhibitor kills
     */
    public int getInhibitorKills()
    {
        return this.inhibitorKills;
    }
    
    /**
     * Number of times the team killed rift herald.
     *
     * @return the rift herald kills
     */
    public int getRiftHeraldKills()
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
    public int getTowerKills()
    {
        return this.towerKills;
    }
    
    /**
     * Gets the vilemaw kills.
     *
     * @return the vilemaw kills
     */
    public int getVilemawKills()
    {
        return this.vilemawKills;
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
        
        if (baronKills != teamStats.baronKills)
        {
            return false;
        }
        if (dominionVictoryScore != teamStats.dominionVictoryScore)
        {
            return false;
        }
        if (dragonKills != teamStats.dragonKills)
        {
            return false;
        }
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
        if (inhibitorKills != teamStats.inhibitorKills)
        {
            return false;
        }
        if (towerKills != teamStats.towerKills)
        {
            return false;
        }
        if (vilemawKills != teamStats.vilemawKills)
        {
            return false;
        }
        if (firstRiftHerald != teamStats.firstRiftHerald)
        {
            return false;
        }
        if (riftHeraldKills != teamStats.riftHeraldKills)
        {
            return false;
        }
        if ((bans != null) ? !bans.equals(teamStats.bans) : (teamStats.bans != null))
        {
            return false;
        }
        if (teamId != teamStats.teamId)
        {
            return false;
        }
        return (win != null) ? win.equals(teamStats.win) : (teamStats.win == null);
    }
    
    @Override
    public int hashCode()
    {
        int result = bans != null ? bans.hashCode() : 0;
        result = 31 * result + baronKills;
        result = 31 * result + (int) (dominionVictoryScore ^ (dominionVictoryScore >>> 32));
        result = 31 * result + dragonKills;
        result = 31 * result + (firstBaron ? 1 : 0);
        result = 31 * result + (firstBlood ? 1 : 0);
        result = 31 * result + (firstDragon ? 1 : 0);
        result = 31 * result + (firstInhibitor ? 1 : 0);
        result = 31 * result + (firstTower ? 1 : 0);
        result = 31 * result + inhibitorKills;
        result = 31 * result + (teamId != null ? teamId.hashCode() : 0);
        result = 31 * result + towerKills;
        result = 31 * result + vilemawKills;
        result = 31 * result + (win != null ? win.hashCode() : 0);
        result = 31 * result + (firstRiftHerald ? 1 : 0);
        result = 31 * result + riftHeraldKills;
        return result;
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
