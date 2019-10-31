package no.stelar7.api.l4j8.pojo.match.lol;

import java.io.Serializable;

public class MatchParticipantFrame implements Serializable
{
    private static final long serialVersionUID = -5104567744427910673L;
    
    private int           totalGold;
    private int           teamScore;
    private int           participantId;
    private int           level;
    private int           currentGold;
    private int           minionsKilled;
    private int           dominionScore;
    private MatchPosition position;
    private int           xp;
    private int           jungleMinionsKilled;
    
    
    /**
     * Participant's current gold
     *
     * @return int
     */
    public int getCurrentGold()
    {
        return this.currentGold;
    }
    
    /**
     * Dominion score of the participant
     *
     * @return int
     */
    public int getDominionScore()
    {
        return this.dominionScore;
    }
    
    /**
     * Number of jungle minions killed by participant
     *
     * @return int
     */
    public int getJungleMinionsKilled()
    {
        return this.jungleMinionsKilled;
    }
    
    /**
     * Participant's current level
     *
     * @return int
     */
    public int getLevel()
    {
        return this.level;
    }
    
    /**
     * Number of minions killed by participant
     *
     * @return int
     */
    public int getMinionsKilled()
    {
        return this.minionsKilled;
    }
    
    /**
     * Participant ID
     *
     * @return int
     */
    public int getParticipantId()
    {
        return this.participantId;
    }
    
    /**
     * Participant's position
     *
     * @return int
     */
    public MatchPosition getPosition()
    {
        return this.position;
    }
    
    /**
     * Team score of the participant
     *
     * @return int
     */
    public int getTeamScore()
    {
        return this.teamScore;
    }
    
    /**
     * Participant's total gold
     *
     * @return int
     */
    public int getTotalGold()
    {
        return this.totalGold;
    }
    
    /**
     * Experience earned by participant
     *
     * @return int
     */
    public int getXp()
    {
        return this.xp;
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
        
        MatchParticipantFrame that = (MatchParticipantFrame) o;
        
        if (totalGold != that.totalGold)
        {
            return false;
        }
        if (teamScore != that.teamScore)
        {
            return false;
        }
        if (participantId != that.participantId)
        {
            return false;
        }
        if (level != that.level)
        {
            return false;
        }
        if (currentGold != that.currentGold)
        {
            return false;
        }
        if (minionsKilled != that.minionsKilled)
        {
            return false;
        }
        if (dominionScore != that.dominionScore)
        {
            return false;
        }
        if (xp != that.xp)
        {
            return false;
        }
        if (jungleMinionsKilled != that.jungleMinionsKilled)
        {
            return false;
        }
        return (position != null) ? position.equals(that.position) : (that.position == null);
    }
    
    @Override
    public int hashCode()
    {
        int result = totalGold;
        result = 31 * result + teamScore;
        result = 31 * result + participantId;
        result = 31 * result + level;
        result = 31 * result + currentGold;
        result = 31 * result + minionsKilled;
        result = 31 * result + dominionScore;
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + xp;
        result = 31 * result + jungleMinionsKilled;
        return result;
    }
    
    @Override
    public String toString()
    {
        return "MatchParticipantFrame{" +
               "currentGold=" + currentGold +
               ", dominionScore=" + dominionScore +
               ", jungleMinionsKilled=" + jungleMinionsKilled +
               ", level=" + level +
               ", minionsKilled=" + minionsKilled +
               ", participantId=" + participantId +
               ", position=" + position +
               ", teamScore=" + teamScore +
               ", totalGold=" + totalGold +
               ", xp=" + xp +
               '}';
    }
}
