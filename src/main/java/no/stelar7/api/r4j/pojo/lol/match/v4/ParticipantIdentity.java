package no.stelar7.api.r4j.pojo.lol.match.v4;

import java.io.Serializable;
import java.util.Objects;

public class ParticipantIdentity implements Serializable
{
    private static final long serialVersionUID = -5368696475522411736L;
    
    private int               participantId;
    private ParticipantPlayer player;
    
    public int getParticipantId()
    {
        return participantId;
    }
    
    public ParticipantPlayer getPlayer()
    {
        return player;
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
        return participantId == that.participantId && Objects.equals(player, that.player);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(participantId, player);
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
