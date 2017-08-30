package no.stelar7.api.l4j8.pojo.summoner;


import no.stelar7.api.l4j8.basic.Utils;
import no.stelar7.api.l4j8.basic.constants.api.Platform;
import no.stelar7.api.l4j8.impl.*;
import no.stelar7.api.l4j8.pojo.championmastery.ChampionMastery;
import no.stelar7.api.l4j8.pojo.league.*;
import no.stelar7.api.l4j8.pojo.match.MatchReference;
import no.stelar7.api.l4j8.pojo.summoner.masteries.MasteryPage;
import no.stelar7.api.l4j8.pojo.summoner.runes.RunePage;

import java.io.Serializable;
import java.time.*;
import java.util.List;

public final class Summoner  implements Serializable
{
    private int      profileIconId;
    private String   name;
    private int      summonerLevel;
    private long     accountId;
    private long     id;
    private long     revisionDate;
    private Platform platform;
    
    /**
     * The Summoners ID
     *
     * @return long
     */
    public long getSummonerId()
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
     * @return int
     */
    public int getProfileIconId()
    {
        return this.profileIconId;
    }
    
    /**
     * Date summoner was last modified specified as epoch milliseconds. The following events will update this timestamp: profile icon change, playing the tutorial or advanced tutorial, finishing a game, summoner name change
     *
     * @return long
     */
    public long getRevisionDate()
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
     * @return int
     */
    public int getSummonerLevel()
    {
        return this.summonerLevel;
    }
    
    /**
     * Account ID associated with the summoner
     *
     * @return int
     */
    public long getAccountId()
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
    
    public ChampionMastery getChampionMastery(int championId)
    {
        return MasteryAPI.getInstance().getChampionMastery(platform, id, championId);
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
        
        if (profileIconId != summoner.profileIconId)
        {
            return false;
        }
        if (summonerLevel != summoner.summonerLevel)
        {
            return false;
        }
        if (accountId != summoner.accountId)
        {
            return false;
        }
        if (id != summoner.id)
        {
            return false;
        }
        if (revisionDate != summoner.revisionDate)
        {
            return false;
        }
        if ((name != null) ? !name.equals(summoner.name) : (summoner.name != null))
        {
            return false;
        }
        return platform == summoner.platform;
    }
    
    @Override
    public int hashCode()
    {
        int result = profileIconId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + summonerLevel;
        result = 31 * result + (int) (accountId ^ (accountId >>> 32));
        result = 31 * result + (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (revisionDate ^ (revisionDate >>> 32));
        result = 31 * result + (platform != null ? platform.hashCode() : 0);
        return result;
    }
    
    @Override
    public String toString()
    {
        return String.format("Summoner{profileIconId=%d, name='%s', summonerLevel=%d, accountId=%d, id=%d, revisionDate=%d}", profileIconId, name, summonerLevel, accountId, id, revisionDate);
    }
}