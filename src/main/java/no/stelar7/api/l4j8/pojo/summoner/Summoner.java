package no.stelar7.api.l4j8.pojo.summoner;


import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.basic.constants.types.*;
import no.stelar7.api.l4j8.basic.utils.*;
import no.stelar7.api.l4j8.impl.*;
import no.stelar7.api.l4j8.pojo.championmastery.ChampionMastery;
import no.stelar7.api.l4j8.pojo.league.*;
import no.stelar7.api.l4j8.pojo.match.MatchReference;
import no.stelar7.api.l4j8.pojo.summoner.masteries.MasteryPage;
import no.stelar7.api.l4j8.pojo.summoner.runes.RunePage;

import java.io.Serializable;
import java.time.*;
import java.util.*;

public final class Summoner implements Serializable
{
    private static final long serialVersionUID = 7941348691314302772L;
    
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
    
    /**
     * This method has the same function as
     * <p>
     * {@link MatchAPI#getMatchList(no.stelar7.api.l4j8.basic.constants.api.Platform, long, Long, Long, Integer, Integer, java.util.Set, java.util.Set, java.util.Set)}
     * <p>
     * but with the accountId and platform already set
     *
     * @param beginTime  optional filter the games started after this time
     * @param endTime    optional filter for games started before this time
     * @param beginIndex optional filter for skipping the first x games
     * @param endIndex   optional filter for skipping only showing x games
     * @param queueTypes optional filter for selecting the queue
     * @param seasons    optional filter for selecting the season
     * @param championId optional filter for selecting the champion played
     * @return {@code List<MatchReference>}
     */
    public List<MatchReference> getGames(Long beginTime, Long endTime, Integer beginIndex, Integer endIndex, Set<GameQueueType> queueTypes, Set<SeasonType> seasons, Set<Integer> championId)
    {
        return MatchAPI.getInstance().getMatchList(platform, accountId, beginTime, endTime, beginIndex, endIndex, queueTypes, seasons, championId);
    }
    
    /**
     * This method has the same function as
     * <p>
     * {@link no.stelar7.api.l4j8.impl.MasteryAPI#getChampionMastery(no.stelar7.api.l4j8.basic.constants.api.Platform, long, int)}
     * <p>
     * but with the id and platform already set
     * <p>
     *
     * @param championId the championId
     * @return ChampionMastery
     */
    public ChampionMastery getChampionMastery(int championId)
    {
        return MasteryAPI.getInstance().getChampionMastery(platform, id, championId);
    }
    
    /**
     * This method has the same function as
     * <p>
     * {@link no.stelar7.api.l4j8.impl.MasteryAPI#getChampionMasteries(no.stelar7.api.l4j8.basic.constants.api.Platform, long)}
     * <p>
     * but with the id and platform already set
     *
     * @return {@code List<ChampionMastery> }
     */
    public List<ChampionMastery> getChampionMasteries()
    {
        return MasteryAPI.getInstance().getChampionMasteries(platform, id);
    }
    
    /**
     * This method has the same function as
     * <p>
     * {@link no.stelar7.api.l4j8.impl.LeagueAPI#getLeaguePosition(no.stelar7.api.l4j8.basic.constants.api.Platform, long)}
     * <p>
     * but with the id and platform already set
     *
     * @return {@code List<LeaguePosition>}
     */
    public List<LeaguePosition> getLeagueEntry()
    {
        return LeagueAPI.getInstance().getLeaguePosition(platform, id);
    }
    
    /**
     * This method has the same function as
     * <p>
     * {@link no.stelar7.api.l4j8.impl.LeagueAPI#getLeague(no.stelar7.api.l4j8.basic.constants.api.Platform, long)}
     * <p>
     * but with the id and platform already set
     *
     * @return {@code List<LeagueList> }
     */
    public List<LeagueList> getFullLeague()
    {
        return LeagueAPI.getInstance().getLeague(platform, id);
    }
    
    /**
     * Platform this summoner is on
     *
     * @return Platform
     */
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