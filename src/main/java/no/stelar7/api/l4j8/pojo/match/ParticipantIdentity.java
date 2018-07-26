package no.stelar7.api.l4j8.pojo.match;

import no.stelar7.api.l4j8.basic.constants.api.Platform;
import no.stelar7.api.l4j8.pojo.summoner.Summoner;

import java.io.Serializable;
import java.util.Objects;

public class ParticipantIdentity implements Serializable
{
    private static final long serialVersionUID = 1762488869710744332L;
    
    private int      participantId;
    private String   matchHistoryUri;
    private int      profileIcon;
    private long     summonerId;
    private String   summonerName;
    private Platform currentPlatformId;
    private Platform platformId;
    private long     accountId;
    private long     currentAccountId;
    
    public Summoner getCurrentSummoner()
    {
        return Summoner.byAccountId(currentAccountId, currentPlatformId);
    }
    
    public Summoner getOldSummoner()
    {
        return Summoner.byAccountId(accountId, platformId);
    }
    
    public int getParticipantId()
    {
        return participantId;
    }
    
    public String getMatchHistoryUri()
    {
        return matchHistoryUri;
    }
    
    public int getProfileIcon()
    {
        return profileIcon;
    }
    
    public long getSummonerId()
    {
        return summonerId;
    }
    
    public String getSummonerName()
    {
        return summonerName;
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
        ParticipantIdentity that = (ParticipantIdentity) o;
        return participantId == that.participantId &&
               profileIcon == that.profileIcon &&
               summonerId == that.summonerId &&
               accountId == that.accountId &&
               currentAccountId == that.currentAccountId &&
               Objects.equals(matchHistoryUri, that.matchHistoryUri) &&
               Objects.equals(summonerName, that.summonerName) &&
               currentPlatformId == that.currentPlatformId &&
               platformId == that.platformId;
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(participantId, matchHistoryUri, profileIcon, summonerId, summonerName, currentPlatformId, platformId, accountId, currentAccountId);
    }
    
    @Override
    public String toString()
    {
        return "ParticipantIdentity{" +
               "participantId=" + participantId +
               ", matchHistoryUri='" + matchHistoryUri + '\'' +
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
