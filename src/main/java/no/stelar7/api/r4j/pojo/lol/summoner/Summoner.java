package no.stelar7.api.r4j.pojo.lol.summoner;


import no.stelar7.api.r4j.basic.constants.api.Platform;
import no.stelar7.api.r4j.basic.utils.Utils;
import no.stelar7.api.r4j.basic.utils.sql.SQLTypeMap;
import no.stelar7.api.r4j.impl.lol.builders.championmastery.ChampionMasteryBuilder;
import no.stelar7.api.r4j.impl.lol.builders.league.LeagueBuilder;
import no.stelar7.api.r4j.impl.lol.builders.match.MatchListBuilder;
import no.stelar7.api.r4j.impl.lol.builders.spectator.SpectatorBuilder;
import no.stelar7.api.r4j.impl.lol.builders.thirdparty.ThirdPartyCodeBuilder;
import no.stelar7.api.r4j.impl.lol.raw.SummonerAPI;
import no.stelar7.api.r4j.impl.tft.TFTMatchAPI;
import no.stelar7.api.r4j.pojo.lol.championmastery.ChampionMastery;
import no.stelar7.api.r4j.pojo.lol.league.LeagueEntry;
import no.stelar7.api.r4j.pojo.lol.spectator.SpectatorGameInfo;

import java.io.Serializable;
import java.time.*;
import java.util.*;

public final class Summoner implements Serializable
{
    private static final long serialVersionUID = 7941348691314302772L;
    
    private int      profileIconId;
    private String   name;
    private int      summonerLevel;
    private String   accountId;
    private String   puuid;
    private String   id;
    private long     revisionDate;
    private Platform platform;
    
    
    public static Summoner byName(Platform platform, String name)
    {
        return SummonerAPI.getInstance().getSummonerByName(platform, name);
    }
    
    public static Summoner bySummonerId(Platform platform, String id)
    {
        return SummonerAPI.getInstance().getSummonerById(platform, id);
    }
    
    public static Summoner byAccountId(Platform platform, String id)
    {
        return SummonerAPI.getInstance().getSummonerByAccount(platform, id);
    }
    
    /**
     * The Summoners ID
     *
     * @return String
     */
    public String getSummonerId()
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
        return Utils.normalizeString(name);
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
    public String getAccountId()
    {
        return accountId;
    }
    
    /**
     * Unique id attributed to the summoner
     *
     * @return string
     */
    public String getPUUID()
    {
        return puuid;
    }
    
    /**
     * This method has the same function as the main one but with the accountId and platform already set
     *
     * @return MatchListBuilder
     */
    public MatchListBuilder getLeagueGames()
    {
        return new MatchListBuilder().withPlatform(platform).withAccountId(accountId);
    }
    
    /**
     * This method has the same function as the main one but with the accountId and platform already set
     *
     * @return {@code List<String>}
     */
    public List<String> getTFTGames()
    {
        return TFTMatchAPI.getInstance().getMatchList(platform.toRegionalEnum(), puuid, 20);
    }
    
    /**
     * Returns the mastery of the provided championid
     *
     * @param championId the championId
     * @return ChampionMastery
     */
    public ChampionMastery getChampionMastery(int championId)
    {
        return new ChampionMasteryBuilder().withPlatform(platform).withSummonerId(id).withChampionId(championId).getChampionMastery();
    }
    
    /**
     * Returns a list of all the masteries of all the champions
     *
     * @return {@code List<ChampionMastery> }
     */
    public List<ChampionMastery> getChampionMasteries()
    {
        return new ChampionMasteryBuilder().withPlatform(platform).withSummonerId(id).getChampionMasteries();
    }
    
    public List<LeagueEntry> getLeagueEntry()
    {
        return new LeagueBuilder().withPlatform(platform).withSummonerId(id).getLeagueEntries();
    }
    
    public SpectatorGameInfo getCurrentGame()
    {
        return new SpectatorBuilder().withPlatform(platform).withSummonerId(id).getCurrentGame();
    }
    
    public String getThirdPartyCode()
    {
        return new ThirdPartyCodeBuilder().withPlatform(platform).withSummonerId(id).getCode();
    }
    
    public int getMasteryScore()
    {
        return new ChampionMasteryBuilder().withPlatform(platform).withSummonerId(id).getMasteryScore();
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
        if (!Objects.equals(id, summoner.id))
        {
            return false;
        }
        if (!Objects.equals(accountId, summoner.accountId))
        {
            return false;
        }
        if (!Objects.equals(puuid, summoner.puuid))
        {
            return false;
        }
        if (revisionDate != summoner.revisionDate)
        {
            return false;
        }
        if (!Objects.equals(name, summoner.name))
        {
            return false;
        }
        return platform == summoner.platform;
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(profileIconId, name, summonerLevel, accountId, puuid, id, revisionDate, platform);
    }
    
    @Override
    public String toString()
    {
        return "Summoner{" +
               "profileIconId=" + profileIconId +
               ", name='" + name + '\'' +
               ", summonerLevel=" + summonerLevel +
               ", accountId=" + accountId +
               ", id=" + id +
               ", revisionDate=" + revisionDate +
               ", platform=" + platform +
               '}';
    }
    
    @SQLTypeMap
    public static Map<String, String> toTypeMap()
    {
        Map<String, String> returnMap = new HashMap<>();
        
        returnMap.put("profileIconId", "int16");
        returnMap.put("name", "text");
        returnMap.put("summonerLevel", "int16");
        returnMap.put("accountId", "text");
        returnMap.put("puuid", "text");
        returnMap.put("id", "text");
        returnMap.put("revisionDate", "int64");
        returnMap.put("platform", "API_PLATFORM");
        
        return returnMap;
    }
}