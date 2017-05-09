package no.stelar7.api.l4j8.pojo.match;

import no.stelar7.api.l4j8.basic.constants.api.Platform;

public class Player
{
    private String   matchHistoryUri;
    private Integer  profileIcon;
    private Long     summonerId;
    private String   summonerName;
    private Platform currentPlatformId;
    private Platform platformId;
    private Long     accountId;
    private Long     currentAccountId;
    
    
    /**
     * Gets the match history uri.
     *
     * @return the match history uri
     */
    public String getMatchHistoryUri()
    {
        return this.matchHistoryUri;
    }
    
    /**
     * Gets the profile icon.
     *
     * @return the profile icon
     */
    public Integer getProfileIcon()
    {
        return this.profileIcon;
    }
    
    /**
     * Gets the summoner id.
     *
     * @return the summoner id
     */
    public Long getSummonerId()
    {
        return this.summonerId;
    }
    
    /**
     * Gets the summoner name.
     *
     * @return the summoner name
     */
    public String getSummonerName()
    {
        return this.summonerName;
    }
    
    public Platform getCurrentPlatform()
    {
        return currentPlatformId;
    }
    
    public Platform getPlatform()
    {
        return platformId;
    }
    
    public Long getAccountId()
    {
        return accountId;
    }
    
    public Long getCurrentAccountId()
    {
        return currentAccountId;
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
        
        if ((matchHistoryUri != null) ? !matchHistoryUri.equals(player.matchHistoryUri) : (player.matchHistoryUri != null))
        {
            return false;
        }
        if ((profileIcon != null) ? !profileIcon.equals(player.profileIcon) : (player.profileIcon != null))
        {
            return false;
        }
        if ((summonerId != null) ? !summonerId.equals(player.summonerId) : (player.summonerId != null))
        {
            return false;
        }
        if ((summonerName != null) ? !summonerName.equals(player.summonerName) : (player.summonerName != null))
        {
            return false;
        }
        if (currentPlatformId != player.currentPlatformId)
        {
            return false;
        }
        if (platformId != player.platformId)
        {
            return false;
        }
        if ((accountId != null) ? !accountId.equals(player.accountId) : (player.accountId != null))
        {
            return false;
        }
        return (currentAccountId != null) ? currentAccountId.equals(player.currentAccountId) : (player.currentAccountId == null);
    }
    
    @Override
    public int hashCode()
    {
        int result = matchHistoryUri != null ? matchHistoryUri.hashCode() : 0;
        result = 31 * result + (profileIcon != null ? profileIcon.hashCode() : 0);
        result = 31 * result + (summonerId != null ? summonerId.hashCode() : 0);
        result = 31 * result + (summonerName != null ? summonerName.hashCode() : 0);
        result = 31 * result + (currentPlatformId != null ? currentPlatformId.hashCode() : 0);
        result = 31 * result + (platformId != null ? platformId.hashCode() : 0);
        result = 31 * result + (accountId != null ? accountId.hashCode() : 0);
        result = 31 * result + (currentAccountId != null ? currentAccountId.hashCode() : 0);
        return result;
    }
    
    @Override
    public String toString()
    {
        return "Player{" +
               "matchHistoryUri='" + matchHistoryUri + '\'' +
               ", profileIcon=" + profileIcon +
               ", summonerId=" + summonerId +
               ", summonerName='" + summonerName + '\'' +
               '}';
    }
}
