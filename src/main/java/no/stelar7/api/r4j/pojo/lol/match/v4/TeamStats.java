package no.stelar7.api.r4j.pojo.lol.match.v4;

import no.stelar7.api.r4j.basic.constants.types.lol.TeamType;
import no.stelar7.api.r4j.pojo.lol.shared.BannedChampion;

import java.io.Serializable;
import java.util.*;

public class TeamStats implements Serializable
{
    private static final long serialVersionUID = -8718660324316598418L;
    
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
    
    public String getWinString()
    {
        return this.win;
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
        return baronKills == teamStats.baronKills &&
               dominionVictoryScore == teamStats.dominionVictoryScore &&
               dragonKills == teamStats.dragonKills &&
               firstBaron == teamStats.firstBaron &&
               firstBlood == teamStats.firstBlood &&
               firstDragon == teamStats.firstDragon &&
               firstInhibitor == teamStats.firstInhibitor &&
               firstTower == teamStats.firstTower &&
               inhibitorKills == teamStats.inhibitorKills &&
               towerKills == teamStats.towerKills &&
               vilemawKills == teamStats.vilemawKills &&
               firstRiftHerald == teamStats.firstRiftHerald &&
               riftHeraldKills == teamStats.riftHeraldKills &&
               Objects.equals(bans, teamStats.bans) &&
               teamId == teamStats.teamId &&
               Objects.equals(win, teamStats.win);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(bans, baronKills, dominionVictoryScore, dragonKills, firstBaron, firstBlood, firstDragon, firstInhibitor, firstTower, inhibitorKills, teamId, towerKills, vilemawKills, win, firstRiftHerald, riftHeraldKills);
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
