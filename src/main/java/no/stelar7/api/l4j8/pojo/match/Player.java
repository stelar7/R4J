package no.stelar7.api.l4j8.pojo.match;

import no.stelar7.api.l4j8.basic.constants.api.Platform;

import java.io.Serializable;

public class Player implements Serializable
{
    private static final long serialVersionUID = -2121986473110048417L;
    
    private String   matchHistoryUri;
    private int      profileIcon;
    private long     summonerId;
    private String   summonerName;
    private Platform currentPlatformId;
    private Platform platformId;
    private long     accountId;
    private long     currentAccountId;
    
    
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
    public int getProfileIcon()
    {
        return this.profileIcon;
    }
    
    /**
     * Gets the summoner id.
     *
     * @return the summoner id
     */
    public long getSummonerId()
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
    
    public long getAccountId()
    {
        return accountId;
    }
    
    public long getCurrentAccountId()
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
        
        if (profileIcon != player.profileIcon)
        {
            return false;
        }
        if (summonerId != player.summonerId)
        {
            return false;
        }
        if (accountId != player.accountId)
        {
            return false;
        }
        if (currentAccountId != player.currentAccountId)
        {
            return false;
        }
        if ((matchHistoryUri != null) ? !matchHistoryUri.equals(player.matchHistoryUri) : (player.matchHistoryUri != null))
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
        return platformId == player.platformId;
    }
    
    @Override
    public int hashCode()
    {
        int result = matchHistoryUri != null ? matchHistoryUri.hashCode() : 0;
        result = 31 * result + profileIcon;
        result = 31 * result + (int) (summonerId ^ (summonerId >>> 32));
        result = 31 * result + (summonerName != null ? summonerName.hashCode() : 0);
        result = 31 * result + (currentPlatformId != null ? currentPlatformId.hashCode() : 0);
        result = 31 * result + (platformId != null ? platformId.hashCode() : 0);
        result = 31 * result + (int) (accountId ^ (accountId >>> 32));
        result = 31 * result + (int) (currentAccountId ^ (currentAccountId >>> 32));
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
               ", currentPlatformId=" + currentPlatformId +
               ", platformId=" + platformId +
               ", accountId=" + accountId +
               ", currentAccountId=" + currentAccountId +
               '}';
    }
}
