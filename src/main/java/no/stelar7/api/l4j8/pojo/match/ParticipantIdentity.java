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
        return participantId;
    }

    /**
     * Player information
     *
     * @return Player
     */
    public Player getPlayer()
    {
        return player;
    }
}
