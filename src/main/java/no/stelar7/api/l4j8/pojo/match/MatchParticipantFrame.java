package no.stelar7.api.l4j8.pojo.match;

public class MatchParticipantFrame
{
    
    private Integer       totalGold;
    private Integer       teamScore;
    private Integer       participantId;
    private Integer       level;
    private Integer       currentGold;
    private Integer       minionsKilled;
    private Integer       dominionScore;
    private MatchPosition position;
    private Integer       xp;
    private Integer       jungleMinionsKilled;
    
    @Override
    public boolean equals(final Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (this.getClass() != obj.getClass())
        {
            return false;
        }
        final MatchParticipantFrame other = (MatchParticipantFrame) obj;
        if (this.currentGold == null)
        {
            if (other.currentGold != null)
            {
                return false;
            }
        } else if (!this.currentGold.equals(other.currentGold))
        {
            return false;
        }
        if (this.dominionScore == null)
        {
            if (other.dominionScore != null)
            {
                return false;
            }
        } else if (!this.dominionScore.equals(other.dominionScore))
        {
            return false;
        }
        if (this.jungleMinionsKilled == null)
        {
            if (other.jungleMinionsKilled != null)
            {
                return false;
            }
        } else if (!this.jungleMinionsKilled.equals(other.jungleMinionsKilled))
        {
            return false;
        }
        if (this.level == null)
        {
            if (other.level != null)
            {
                return false;
            }
        } else if (!this.level.equals(other.level))
        {
            return false;
        }
        if (this.minionsKilled == null)
        {
            if (other.minionsKilled != null)
            {
                return false;
            }
        } else if (!this.minionsKilled.equals(other.minionsKilled))
        {
            return false;
        }
        if (this.participantId == null)
        {
            if (other.participantId != null)
            {
                return false;
            }
        } else if (!this.participantId.equals(other.participantId))
        {
            return false;
        }
        if (this.position == null)
        {
            if (other.position != null)
            {
                return false;
            }
        } else if (!this.position.equals(other.position))
        {
            return false;
        }
        if (this.teamScore == null)
        {
            if (other.teamScore != null)
            {
                return false;
            }
        } else if (!this.teamScore.equals(other.teamScore))
        {
            return false;
        }
        if (this.totalGold == null)
        {
            if (other.totalGold != null)
            {
                return false;
            }
        } else if (!this.totalGold.equals(other.totalGold))
        {
            return false;
        }
        if (this.xp == null)
        {
            if (other.xp != null)
            {
                return false;
            }
        } else if (!this.xp.equals(other.xp))
        {
            return false;
        }
        return true;
    }
    
    /**
     * Participant's current gold
     *
     * @return Integer
     */
    public Integer getCurrentGold()
    {
        return this.currentGold;
    }
    
    /**
     * Dominion score of the participant
     *
     * @return Integer
     */
    public Integer getDominionScore()
    {
        return this.dominionScore;
    }
    
    /**
     * Number of jungle minions killed by participant
     *
     * @return Integer
     */
    public Integer getJungleMinionsKilled()
    {
        return this.jungleMinionsKilled;
    }
    
    /**
     * Participant's current level
     *
     * @return Integer
     */
    public Integer getLevel()
    {
        return this.level;
    }
    
    /**
     * Number of minions killed by participant
     *
     * @return Integer
     */
    public Integer getMinionsKilled()
    {
        return this.minionsKilled;
    }
    
    /**
     * Participant ID
     *
     * @return Integer
     */
    public Integer getParticipantId()
    {
        return this.participantId;
    }
    
    /**
     * Participant's position
     *
     * @return Integer
     */
    public MatchPosition getPosition()
    {
        return this.position;
    }
    
    /**
     * Team score of the participant
     *
     * @return Integer
     */
    public Integer getTeamScore()
    {
        return this.teamScore;
    }
    
    /**
     * Participant's total gold
     *
     * @return Integer
     */
    public Integer getTotalGold()
    {
        return this.totalGold;
    }
    
    /**
     * Experience earned by participant
     *
     * @return Integer
     */
    public Integer getXp()
    {
        return this.xp;
    }
    
    @Override
    public int hashCode()
    {
        final int prime  = 31;
        int       result = 1;
        result = (prime * result) + ((this.currentGold == null) ? 0 : this.currentGold.hashCode());
        result = (prime * result) + ((this.dominionScore == null) ? 0 : this.dominionScore.hashCode());
        result = (prime * result) + ((this.jungleMinionsKilled == null) ? 0 : this.jungleMinionsKilled.hashCode());
        result = (prime * result) + ((this.level == null) ? 0 : this.level.hashCode());
        result = (prime * result) + ((this.minionsKilled == null) ? 0 : this.minionsKilled.hashCode());
        result = (prime * result) + ((this.participantId == null) ? 0 : this.participantId.hashCode());
        result = (prime * result) + ((this.position == null) ? 0 : this.position.hashCode());
        result = (prime * result) + ((this.teamScore == null) ? 0 : this.teamScore.hashCode());
        result = (prime * result) + ((this.totalGold == null) ? 0 : this.totalGold.hashCode());
        result = (prime * result) + ((this.xp == null) ? 0 : this.xp.hashCode());
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
