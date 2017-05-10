package no.stelar7.api.l4j8.pojo.summoner;


import no.stelar7.api.l4j8.basic.Utils;
import no.stelar7.api.l4j8.basic.constants.api.Platform;
import no.stelar7.api.l4j8.basic.constants.types.ChampionType;
import no.stelar7.api.l4j8.impl.*;
import no.stelar7.api.l4j8.pojo.championmastery.ChampionMastery;
import no.stelar7.api.l4j8.pojo.league.*;
import no.stelar7.api.l4j8.pojo.match.MatchReference;
import no.stelar7.api.l4j8.pojo.summoner.masteries.MasteryPage;
import no.stelar7.api.l4j8.pojo.summoner.runes.*;

import java.time.*;
import java.util.List;

public final class Summoner
{
    private Integer  profileIconId;
    private String   name;
    private Integer  summonerLevel;
    private Long     accountId;
    private Long     id;
    private Long     revisionDate;
    private Platform platform;
    
    /**
     * The Summoners ID
     *
     * @return Long
     */
    public Long getSummonerId()
    {
        return this.id;
    }
    
    /**
     * The Summoners name
     *
     * @return String
     */
    public String getName()
    {
        return this.name;
    }
    
    /**
     * The Summoners name normalized. (no whitespace, lowercase Locale.ENGLISH)
     *
     * @return String
     */
    public String getNormalizedName()
    {
        return Utils.normalizeSummonerName(name);
    }
    
    /**
     * ID of the summoner icon associated with the summoner
     *
     * @return Integer
     */
    public Integer getProfileIconId()
    {
        return this.profileIconId;
    }
    
    /**
     * Date summoner was last modified specified as epoch milliseconds. The following events will update this timestamp: profile icon change, playing the tutorial or advanced tutorial, finishing a game, summoner name change
     *
     * @return Long
     */
    public Long getRevisionDate()
    {
        return this.revisionDate;
    }
    
    /**
     * A ZonedDateTime of {@code getRevisionDate()}
     *
     * @return ZonedDateTime
     */
    public ZonedDateTime getRevisionDateAsDate()
    {
        return ZonedDateTime.ofInstant(Instant.ofEpochMilli(this.revisionDate), ZoneOffset.UTC);
    }
    
    /**
     * Summoner level associated with the summoner
     *
     * @return Integer
     */
    public Integer getSummonerLevel()
    {
        return this.summonerLevel;
    }
    
    /**
     * Account ID associated with the summoner
     *
     * @return Integer
     */
    public Long getAccountId()
    {
        return accountId;
    }
    
    public List<RunePage> getRunePages()
    {
        return SummonerAPI.getInstance().getRunes(platform, this.getSummonerId());
    }
    
    public List<MasteryPage> getMasteryPages()
    {
        return SummonerAPI.getInstance().getMasteries(platform, this.getSummonerId());
    }
    
    public List<MatchReference> getRecentGames()
    {
        return MatchAPI.getInstance().getRecentMatches(platform, accountId);
    }
    
    public List<MatchReference> getRankedGames()
    {
        return MatchAPI.getInstance().getMatchList(platform, accountId, null, null, null, null, null, null, null);
    }
    
    public ChampionMastery getChampionMastery(ChampionType type)
    {
        return MasteryAPI.getInstance().getChampionMastery(platform, id, type.getId());
    }
    
    public List<ChampionMastery> getChampionMasteries()
    {
        return MasteryAPI.getInstance().getChampionMasteries(platform, id);
    }
    
    public List<LeaguePosition> getLeagueEntry()
    {
        return LeagueAPI.getInstance().getLeaguePosition(platform, id);
    }
    
    public List<LeagueList> getFullLeague()
    {
        return LeagueAPI.getInstance().getLeague(platform, id);
    }
    
    public Platform getPlatform()
    {
        return platform;
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
        
        Summoner summoner = (Summoner) o;
        
        if ((profileIconId != null) ? !profileIconId.equals(summoner.profileIconId) : (summoner.profileIconId != null))
        {
            return false;
        }
        if ((name != null) ? !name.equals(summoner.name) : (summoner.name != null))
        {
            return false;
        }
        if ((summonerLevel != null) ? !summonerLevel.equals(summoner.summonerLevel) : (summoner.summonerLevel != null))
        {
            return false;
        }
        if ((accountId != null) ? !accountId.equals(summoner.accountId) : (summoner.accountId != null))
        {
            return false;
        }
        if ((id != null) ? !id.equals(summoner.id) : (summoner.id != null))
        {
            return false;
        }
        return (revisionDate != null) ? revisionDate.equals(summoner.revisionDate) : (summoner.revisionDate == null);
    }
    
    @Override
    public int hashCode()
    {
        int result = profileIconId != null ? profileIconId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (summonerLevel != null ? summonerLevel.hashCode() : 0);
        result = 31 * result + (accountId != null ? accountId.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (revisionDate != null ? revisionDate.hashCode() : 0);
        return result;
    }
    
    
    @Override
    public String toString()
    {
        return String.format("Summoner{profileIconId=%d, name='%s', summonerLevel=%d, accountId=%d, id=%d, revisionDate=%d}", profileIconId, name, summonerLevel, accountId, id, revisionDate);
    }
}