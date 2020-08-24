package no.stelar7.api.r4j.pojo.val.match;

import no.stelar7.api.r4j.basic.constants.types.val.TierDivisionType;

import java.io.Serializable;
import java.util.Objects;

public class Player implements Serializable
{
    private static final long serialVersionUID = 6640355688349553215L;
    
    private String           puuid;
    private String           teamId;
    private String           partyId;
    private String           characterId;
    private PlayerTotalStats stats;
    private TierDivisionType competitiveTier;
    private String           playerCard;
    private String           playerTitle;
    
    public String getPUUID()
    {
        return puuid;
    }
    
    public String getTeamId()
    {
        return teamId;
    }
    
    public String getPartyId()
    {
        return partyId;
    }
    
    public String getCharacterId()
    {
        return characterId;
    }
    
    public PlayerTotalStats getStats()
    {
        return stats;
    }
    
    public TierDivisionType getCompetitiveTier()
    {
        return competitiveTier;
    }
    
    public String getPlayerCard()
    {
        return playerCard;
    }
    
    public String getPlayerTitle()
    {
        return playerTitle;
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
        Player player = (Player) o;
        return Objects.equals(puuid, player.puuid) &&
               Objects.equals(teamId, player.teamId) &&
               Objects.equals(partyId, player.partyId) &&
               Objects.equals(characterId, player.characterId) &&
               Objects.equals(stats, player.stats) &&
               Objects.equals(competitiveTier, player.competitiveTier) &&
               Objects.equals(playerCard, player.playerCard) &&
               Objects.equals(playerTitle, player.playerTitle);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(puuid, teamId, partyId, characterId, stats, competitiveTier, playerCard, playerTitle);
    }
    
    @Override
    public String toString()
    {
        return "Player{" +
               "puuid='" + puuid + '\'' +
               ", teamId='" + teamId + '\'' +
               ", partyId='" + partyId + '\'' +
               ", characterId='" + characterId + '\'' +
               ", stats=" + stats +
               ", competitiveTier=" + competitiveTier +
               ", playerCard='" + playerCard + '\'' +
               ", playerTitle='" + playerTitle + '\'' +
               '}';
    }
}
