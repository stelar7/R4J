package no.stelar7.api.l4j8.pojo.match;

import java.util.List;

public class Team
{
    private List<BannedChampion> bans;
    private Integer              baronKills;
    private Long                 dominionVictoryScore;
    private Integer              dragonKills;
    private Boolean              firstBaron;
    private Boolean              firstBlood;
    private Boolean              firstDragon;
    private Boolean              firstInhibitor;
    private Boolean              firstTower;
    private Integer              inhibitorKills;
    private Integer              teamId;
    private Integer              towerKills;
    private Integer              vilemawKills;
    private Boolean              winner;
    private Boolean              firstRiftHerald;
    private Integer              riftHeraldKills;

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Team other = (Team) obj;
        if (bans == null)
        {
            if (other.bans != null)
                return false;
        } else if (!bans.equals(other.bans))
            return false;
        if (baronKills == null)
        {
            if (other.baronKills != null)
                return false;
        } else if (!baronKills.equals(other.baronKills))
            return false;
        if (dominionVictoryScore == null)
        {
            if (other.dominionVictoryScore != null)
                return false;
        } else if (!dominionVictoryScore.equals(other.dominionVictoryScore))
            return false;
        if (dragonKills == null)
        {
            if (other.dragonKills != null)
                return false;
        } else if (!dragonKills.equals(other.dragonKills))
            return false;
        if (firstBaron == null)
        {
            if (other.firstBaron != null)
                return false;
        } else if (!firstBaron.equals(other.firstBaron))
            return false;
        if (firstBlood == null)
        {
            if (other.firstBlood != null)
                return false;
        } else if (!firstBlood.equals(other.firstBlood))
            return false;
        if (firstDragon == null)
        {
            if (other.firstDragon != null)
                return false;
        } else if (!firstDragon.equals(other.firstDragon))
            return false;
        if (firstInhibitor == null)
        {
            if (other.firstInhibitor != null)
                return false;
        } else if (!firstInhibitor.equals(other.firstInhibitor))
            return false;
        if (firstRiftHerald == null)
        {
            if (other.firstRiftHerald != null)
                return false;
        } else if (!firstRiftHerald.equals(other.firstRiftHerald))
            return false;
        if (firstTower == null)
        {
            if (other.firstTower != null)
                return false;
        } else if (!firstTower.equals(other.firstTower))
            return false;
        if (inhibitorKills == null)
        {
            if (other.inhibitorKills != null)
                return false;
        } else if (!inhibitorKills.equals(other.inhibitorKills))
            return false;
        if (riftHeraldKills == null)
        {
            if (other.riftHeraldKills != null)
                return false;
        } else if (!riftHeraldKills.equals(other.riftHeraldKills))
            return false;
        if (teamId == null)
        {
            if (other.teamId != null)
                return false;
        } else if (!teamId.equals(other.teamId))
            return false;
        if (towerKills == null)
        {
            if (other.towerKills != null)
                return false;
        } else if (!towerKills.equals(other.towerKills))
            return false;
        if (vilemawKills == null)
        {
            if (other.vilemawKills != null)
                return false;
        } else if (!vilemawKills.equals(other.vilemawKills))
            return false;
        if (winner == null)
        {
            if (other.winner != null)
                return false;
        } else if (!winner.equals(other.winner))
            return false;
        return true;
    }

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
     * Gets the team id.
     *
     * @return the team id
     */
    public Integer getTeamId()
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
        final int prime = 31;
        int result = 1;
        result = prime * result + ((bans == null) ? 0 : bans.hashCode());
        result = prime * result + ((baronKills == null) ? 0 : baronKills.hashCode());
        result = prime * result + ((dominionVictoryScore == null) ? 0 : dominionVictoryScore.hashCode());
        result = prime * result + ((dragonKills == null) ? 0 : dragonKills.hashCode());
        result = prime * result + ((firstBaron == null) ? 0 : firstBaron.hashCode());
        result = prime * result + ((firstBlood == null) ? 0 : firstBlood.hashCode());
        result = prime * result + ((firstDragon == null) ? 0 : firstDragon.hashCode());
        result = prime * result + ((firstInhibitor == null) ? 0 : firstInhibitor.hashCode());
        result = prime * result + ((firstRiftHerald == null) ? 0 : firstRiftHerald.hashCode());
        result = prime * result + ((firstTower == null) ? 0 : firstTower.hashCode());
        result = prime * result + ((inhibitorKills == null) ? 0 : inhibitorKills.hashCode());
        result = prime * result + ((riftHeraldKills == null) ? 0 : riftHeraldKills.hashCode());
        result = prime * result + ((teamId == null) ? 0 : teamId.hashCode());
        result = prime * result + ((towerKills == null) ? 0 : towerKills.hashCode());
        result = prime * result + ((vilemawKills == null) ? 0 : vilemawKills.hashCode());
        result = prime * result + ((winner == null) ? 0 : winner.hashCode());
        return result;
    }

    /**
     * Checks if is first baron.
     *
     * @return the boolean
     */
    public Boolean isFirstBaron()
    {
        return this.firstBaron;
    }

    /**
     * Checks if is first blood.
     *
     * @return the boolean
     */
    public Boolean isFirstBlood()
    {
        return this.firstBlood;
    }

    /**
     * Checks if is first dragon.
     *
     * @return the boolean
     */
    public Boolean isFirstDragon()
    {
        return this.firstDragon;
    }

    /**
     * Checks if is first inhibitor.
     *
     * @return the boolean
     */
    public Boolean isFirstInhibitor()
    {
        return this.firstInhibitor;
    }

    /**
     * Checks if is first tower.
     *
     * @return the boolean
     */
    public Boolean isFirstTower()
    {
        return this.firstTower;
    }

    /**
     * Checks if is winner.
     *
     * @return the boolean
     */
    public Boolean isWinner()
    {
        return this.winner;
    }

    /**
     * Gets the first baron.
     *
     * @return the first baron
     */
    public Boolean getFirstBaron()
    {
        return firstBaron;
    }

    /**
     * Gets the first blood.
     *
     * @return the first blood
     */
    public Boolean getFirstBlood()
    {
        return firstBlood;
    }

    /**
     * Gets the first dragon.
     *
     * @return the first dragon
     */
    public Boolean getFirstDragon()
    {
        return firstDragon;
    }

    /**
     * Gets the first inhibitor.
     *
     * @return the first inhibitor
     */
    public Boolean getFirstInhibitor()
    {
        return firstInhibitor;
    }

    /**
     * Gets the first tower.
     *
     * @return the first tower
     */
    public Boolean getFirstTower()
    {
        return firstTower;
    }

    /**
     * Gets the winner.
     *
     * @return the winner
     */
    public Boolean getWinner()
    {
        return winner;
    }

    /**
     * Flag indicating whether or not the team got the first rift herald kill.
     *
     * @return the first rift herald
     */
    public Boolean getFirstRiftHerald()
    {
        return firstRiftHerald;
    }

    /**
     * Number of times the team killed rift herald.
     *
     * @return the rift herald kills
     */
    public Integer getRiftHeraldKills()
    {
        return riftHeraldKills;
    }

    @Override
    public String toString()
    {
        return "Team [bans=" + bans + ", baronKills=" + baronKills + ", dominionVictoryScore=" + dominionVictoryScore + ", dragonKills=" + dragonKills + ", firstBaron=" + firstBaron + ", firstBlood=" + firstBlood + ", firstDragon=" + firstDragon + ", firstInhibitor=" + firstInhibitor + ", firstTower=" + firstTower + ", inhibitorKills=" + inhibitorKills + ", teamId=" + teamId + ", towerKills=" + towerKills + ", vilemawKills=" + vilemawKills + ", winner=" + winner + ", firstRiftHerald=" + firstRiftHerald + ", riftHeraldKills=" + riftHeraldKills + "]";
    }

}
