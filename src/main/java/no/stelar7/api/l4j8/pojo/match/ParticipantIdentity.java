package no.stelar7.api.l4j8.pojo.match;

import java.io.Serializable;

public class ParticipantIdentity implements Serializable
{
    private int    participantId;
    private Player player;
    
    
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
     * Player information
     *
     * @return Player
     */
    public Player getPlayer()
    {
        return this.player;
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
        
        ParticipantIdentity that = (ParticipantIdentity) o;
        
        if (participantId != that.participantId)
        {
            return false;
        }
        return (player != null) ? player.equals(that.player) : (that.player == null);
    }
    
    @Override
    public int hashCode()
    {
        int result = participantId;
        result = 31 * result + (player != null ? player.hashCode() : 0);
        return result;
    }
    
    @Override
    public String toString()
    {
        return "ParticipantIdentity{" +
               "participantId=" + participantId +
               ", player=" + player +
               '}';
    }
}
