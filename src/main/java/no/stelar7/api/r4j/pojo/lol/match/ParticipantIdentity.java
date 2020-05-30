package no.stelar7.api.r4j.pojo.lol.match;

import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.pojo.lol.summoner.Summoner;

import java.io.Serializable;
import java.util.Objects;

public class ParticipantIdentity implements Serializable
{
    private static final long serialVersionUID = 1762488869710744332L;
    
    private int      participantId;
    private String   matchHistoryUri;
    private int      profileIcon;
    private String   summonerId;
    private String      summonerName;
    private LeagueShard currentPlatformId;
    private LeagueShard platformId;
    private String      accountId;
    private String   currentAccountId;
    
    public Summoner getCurrentSummoner()
    {
        return Summoner.byAccountId(currentPlatformId, currentAccountId);
    }
    
    public Summoner getOldSummoner()
    {
        return Summoner.byAccountId(platformId, accountId);
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
    
    public String getSummonerId()
    {
        return summonerId;
    }
    
    public String getSummonerName()
    {
        return summonerName;
    }
    
    public LeagueShard getCurrentPlatform()
    {
        return currentPlatformId;
    }
    
    public LeagueShard getPlatform()
    {
        return platformId;
    }
    
    public String getAccountId()
    {
        return accountId;
    }
    
    public String getCurrentAccountId()
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
               Objects.equals(matchHistoryUri, that.matchHistoryUri) &&
               Objects.equals(summonerId, that.summonerId) &&
               Objects.equals(summonerName, that.summonerName) &&
               currentPlatformId == that.currentPlatformId &&
               platformId == that.platformId &&
               Objects.equals(accountId, that.accountId) &&
               Objects.equals(currentAccountId, that.currentAccountId);
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
