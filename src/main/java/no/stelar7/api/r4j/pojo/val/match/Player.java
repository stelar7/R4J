package no.stelar7.api.r4j.pojo.val.match;

import no.stelar7.api.r4j.basic.constants.api.regions.ValorantShard;
import no.stelar7.api.r4j.basic.constants.types.val.*;
import no.stelar7.api.r4j.basic.utils.sql.*;
import no.stelar7.api.r4j.impl.val.VALContentAPI;
import no.stelar7.api.r4j.pojo.val.content.ContentItem;

import java.io.Serializable;
import java.util.*;

public class Player implements Serializable
{
    private static final long serialVersionUID = 6640355688349553215L;
    
    @SQLReturnKey
    private String           puuid;
    private TeamType         teamId;
    private String           partyId;
    private String           characterId;
    private PlayerTotalStats stats;
    private TierDivisionType competitiveTier;
    private String           playerCard;
    private String           playerTitle;
    private String  gameName;
    private String  tagLine;
    private boolean isObserver;
    private int     accountLevel;
    
    public String getPUUID()
    {
        return puuid;
    }
    
    public TeamType getTeam()
    {
        return teamId;
    }
    
    public String getPartyId()
    {
        return partyId;
    }
    
    public String getCharacter()
    {
        return characterId;
    }
    
    public Optional<ContentItem> getCharacterAsContent()
    {
        return VALContentAPI.getInstance()
                            .getContent(ValorantShard.EU, Optional.empty())
                            .getCharacters()
                            .stream()
                            .filter(c -> c.getId().equalsIgnoreCase(this.characterId))
                            .findFirst();
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
    
    public Optional<ContentItem> getPlayerCardAsContent()
    {
        return VALContentAPI.getInstance()
                            .getContent(ValorantShard.EU, Optional.empty())
                            .getPlayerCards()
                            .stream()
                            .filter(c -> c.getId().equalsIgnoreCase(this.playerCard))
                            .findFirst();
    }
    
    public String getPlayerTitle()
    {
        return playerTitle;
    }
    
    public Optional<ContentItem> getPlayerTitleAsContent()
    {
        return VALContentAPI.getInstance()
                            .getContent(ValorantShard.EU, Optional.empty())
                            .getPlayerTitles()
                            .stream()
                            .filter(c -> c.getId().equalsIgnoreCase(this.playerTitle))
                            .findFirst();
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
               Objects.equals(playerTitle, player.playerTitle) &&
               Objects.equals(gameName, player.gameName) &&
               Objects.equals(tagLine, player.tagLine) &&
               Objects.equals(isObserver, player.isObserver) &&
               Objects.equals(accountLevel, player.accountLevel);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(puuid, teamId, partyId, characterId, stats, competitiveTier, playerCard, playerTitle, gameName, tagLine, isObserver, accountLevel);
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
               ", gameName=" + gameName +
               ", tagLine=" + tagLine +
               ", isObserver=" + isObserver +
               ", accountLevel=" + accountLevel +
               '}';
    }
    
    @SQLForeignMap
    private static Map<Class<?>, String> getForeignKeyMap()
    {
        Map<Class<?>, String> returnMap = new HashMap<>();
        
        returnMap.put(VALMatch.class, "id");
        
        return returnMap;
    }
    
    public String getGameName()
    {
        return gameName;
    }
    
    public String getTagLine()
    {
        return tagLine;
    }
    
    public boolean isObserver()
    {
        return isObserver;
    }
    
    public int getAccountLevel()
    {
        return accountLevel;
    }
}
