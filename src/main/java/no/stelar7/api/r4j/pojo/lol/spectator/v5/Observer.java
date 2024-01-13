package no.stelar7.api.r4j.pojo.lol.spectator.v5;

import no.stelar7.api.r4j.basic.constants.types.lol.*;

import java.io.Serializable;
import java.util.Objects;

public class Observer implements Serializable
{
    private static final long serialVersionUID = -4680988471013321487L;
    
    private String             puuid;
    private long               accountId;
    private long               summonerId;
    private String             locale;
    private boolean            teamOwner;
    private long               profileIconId;
    private long               teamParticipantId;
    private long               championId;
    private RoleType           selectedRole;
    private LeaguePositionType selectedPosition;
    private String             summonerName;
    
    public String getPuuid()
    {
        return puuid;
    }
    
    public long getAccountId()
    {
        return accountId;
    }
    
    public long getSummonerId()
    {
        return summonerId;
    }
    
    public String getLocale()
    {
        return locale;
    }
    
    public boolean isTeamOwner()
    {
        return teamOwner;
    }
    
    public long getProfileIconId()
    {
        return profileIconId;
    }
    
    public long getTeamParticipantId()
    {
        return teamParticipantId;
    }
    
    public long getChampionId()
    {
        return championId;
    }
    
    public RoleType getSelectedRole()
    {
        return selectedRole;
    }
    
    public LeaguePositionType getSelectedPosition()
    {
        return selectedPosition;
    }
    
    public String getSummonerName()
    {
        return summonerName;
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
        Observer observer = (Observer) o;
        return accountId == observer.accountId
               && summonerId == observer.summonerId
               && teamOwner == observer.teamOwner
               && profileIconId == observer.profileIconId
               && teamParticipantId == observer.teamParticipantId
               && championId == observer.championId
               && Objects.equals(puuid, observer.puuid)
               && Objects.equals(locale, observer.locale)
               && selectedRole == observer.selectedRole
               && selectedPosition == observer.selectedPosition
               && Objects.equals(summonerName, observer.summonerName);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(puuid, accountId, summonerId, locale, teamOwner, profileIconId, teamParticipantId, championId, selectedRole, selectedPosition, summonerName);
    }
    
    @Override
    public String toString()
    {
        return "Observer{" +
               "puuid='" + puuid + '\'' +
               ", accountId=" + accountId +
               ", summonerId=" + summonerId +
               ", locale='" + locale + '\'' +
               ", teamOwner=" + teamOwner +
               ", profileIconId=" + profileIconId +
               ", teamParticipantId=" + teamParticipantId +
               ", championId=" + championId +
               ", selectedRole=" + selectedRole +
               ", selectedPosition=" + selectedPosition +
               ", summonerName='" + summonerName + '\'' +
               '}';
    }
}
