package no.stelar7.api.l4j8.pojo.match;

public class ParticipantIdentity
{
    private Integer participantId;
    private Player  player;

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
        final ParticipantIdentity other = (ParticipantIdentity) obj;
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
        if (this.player == null)
        {
            if (other.player != null)
            {
                return false;
            }
        } else if (!this.player.equals(other.player))
        {
            return false;
        }
        return true;
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
     * Player information
     *
     * @return Player
     */
    public Player getPlayer()
    {
        return this.player;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((this.participantId == null) ? 0 : this.participantId.hashCode());
        result = (prime * result) + ((this.player == null) ? 0 : this.player.hashCode());
        return result;
    }

    @Override
    public String toString()
    {
        return "ParticipantIdentity [participantId=" + this.participantId + ", player=" + this.player + "]";
    }
}
