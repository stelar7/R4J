package no.stelar7.api.l4j8.pojo.match;

public class ParticipantFrame
{

    private Integer     currentGold;
    private Integer     dominionScore;
    private Integer     jungleMinionsKilled;
    private Integer     level;
    private Integer     minionsKilled;
    private Integer     participantId;
    private Participant position;
    private Integer     teamScore;
    private Integer     totalGold;
    private Integer     xp;

    /**
     * Participant's current gold
     * 
     * @return Integer
     */
    public Integer getCurrentGold()
    {
        return currentGold;
    }

    /**
     * Dominion score of the participant
     * 
     * @return Integer
     */
    public Integer getDominionScore()
    {
        return dominionScore;
    }

    /**
     * Number of jungle minions killed by participant
     * 
     * @return Integer
     */
    public Integer getJungleMinionsKilled()
    {
        return jungleMinionsKilled;
    }

    /**
     * Participant's current level
     * 
     * @return Integer
     */
    public Integer getLevel()
    {
        return level;
    }

    /**
     * Number of minions killed by participant
     * 
     * @return Integer
     */
    public Integer getMinionsKilled()
    {
        return minionsKilled;
    }

    /**
     * Participant ID
     * 
     * @return Integer
     */
    public Integer getParticipantId()
    {
        return participantId;
    }

    /**
     * Participant's position
     * 
     * @return Integer
     */
    public Participant getPosition()
    {
        return position;
    }

    /**
     * Team score of the participant
     * 
     * @return Integer
     */
    public Integer getTeamScore()
    {
        return teamScore;
    }

    /**
     * Participant's total gold
     * 
     * @return Integer
     */
    public Integer getTotalGold()
    {
        return totalGold;
    }

    /**
     * Experience earned by participant
     * 
     * @return Integer
     */
    public Integer getXp()
    {
        return xp;
    }
}
