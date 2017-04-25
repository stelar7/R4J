package no.stelar7.api.l4j8.pojo.match;

public class ParticipantIdentity
{
    private Integer participantId;
    private Player  player;
    
    
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
        
        if ((participantId != null) ? !participantId.equals(that.participantId) : (that.participantId != null))
        {
            return false;
        }
        return (player != null) ? player.equals(that.player) : (that.player == null);
    }
    
    @Override
    public int hashCode()
    {
        int result = participantId != null ? participantId.hashCode() : 0;
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
