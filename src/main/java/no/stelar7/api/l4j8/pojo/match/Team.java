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

    /**
     * Gets the bans.
     *
     * @return the bans
     */
    public List<BannedChampion> getBans()
    {
        return bans;
    }

    /**
     * Gets the baron kills.
     *
     * @return the baron kills
     */
    public Integer getBaronKills()
    {
        return baronKills;
    }

    /**
     * Gets the dominion victory score.
     *
     * @return the dominion victory score
     */
    public Long getDominionVictoryScore()
    {
        return dominionVictoryScore;
    }

    /**
     * Gets the dragon kills.
     *
     * @return the dragon kills
     */
    public Integer getDragonKills()
    {
        return dragonKills;
    }

    /**
     * Checks if is first baron.
     *
     * @return the boolean
     */
    public Boolean isFirstBaron()
    {
        return firstBaron;
    }

    /**
     * Checks if is first blood.
     *
     * @return the boolean
     */
    public Boolean isFirstBlood()
    {
        return firstBlood;
    }

    /**
     * Checks if is first dragon.
     *
     * @return the boolean
     */
    public Boolean isFirstDragon()
    {
        return firstDragon;
    }

    /**
     * Checks if is first inhibitor.
     *
     * @return the boolean
     */
    public Boolean isFirstInhibitor()
    {
        return firstInhibitor;
    }

    /**
     * Checks if is first tower.
     *
     * @return the boolean
     */
    public Boolean isFirstTower()
    {
        return firstTower;
    }

    /**
     * Gets the inhibitor kills.
     *
     * @return the inhibitor kills
     */
    public Integer getInhibitorKills()
    {
        return inhibitorKills;
    }

    /**
     * Gets the team id.
     *
     * @return the team id
     */
    public Integer getTeamId()
    {
        return teamId;
    }

    /**
     * Gets the tower kills.
     *
     * @return the tower kills
     */
    public Integer getTowerKills()
    {
        return towerKills;
    }

    /**
     * Gets the vilemaw kills.
     *
     * @return the vilemaw kills
     */
    public Integer getVilemawKills()
    {
        return vilemawKills;
    }

    /**
     * Checks if is winner.
     *
     * @return the boolean
     */
    public Boolean isWinner()
    {
        return winner;
    }

}
