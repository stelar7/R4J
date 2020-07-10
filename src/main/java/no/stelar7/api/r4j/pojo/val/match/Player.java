package no.stelar7.api.r4j.pojo.val.match;

import java.util.Objects;

public class Player
{
    private String      puuid;
    private String      teamId;
    private String      partyId;
    private String      characterId;
    private PlayerStats stats;
    private Integer     competitiveTier;
    private String      playerCard;
    private String      playerTitle;
    
    public String getPuuid()
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
    
    public PlayerStats getStats()
    {
        return stats;
    }
    
    public Integer getCompetitiveTier()
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
