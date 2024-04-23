package no.stelar7.api.r4j.pojo.lol.spectator.v5;

import no.stelar7.api.r4j.basic.constants.types.lol.*;

import java.io.Serializable;
import java.util.Objects;

public class Team implements Serializable
{
    private static final long serialVersionUID = 4142374045646251402L;
    
    private String             puuid;
    private String             accountId;
    private String             summonerId;
    private String             locale;
    private boolean            teamOwner;
    private long               profileIconId;
    private long               teamParticipantId;
    private long               championId;
    private SpectatorRole      selectedRole;
    private LeaguePositionType selectedPosition;
    
    public String getPuuid()
    {
        return puuid;
    }
    
    public String getAccountId()
    {
        return accountId;
    }
    
    public String getSummonerId()
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
    
    public SpectatorRole getSelectedRole()
    {
        return selectedRole;
    }
    
    public LeaguePositionType getSelectedPosition()
    {
        return selectedPosition;
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
        Team team = (Team) o;
        return teamOwner == team.teamOwner
               && profileIconId == team.profileIconId
               && teamParticipantId == team.teamParticipantId
               && championId == team.championId
               && Objects.equals(puuid, team.puuid)
               && Objects.equals(accountId, team.accountId)
               && Objects.equals(summonerId, team.summonerId)
               && Objects.equals(locale, team.locale)
               && selectedRole == team.selectedRole
               && selectedPosition == team.selectedPosition;
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(puuid, accountId, summonerId, locale, teamOwner, profileIconId, teamParticipantId, championId, selectedRole, selectedPosition);
    }
    
    @Override
    public String toString()
    {
        return "Team{" +
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
               '}';
    }
}
