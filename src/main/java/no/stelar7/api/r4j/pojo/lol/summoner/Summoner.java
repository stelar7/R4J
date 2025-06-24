package no.stelar7.api.r4j.pojo.lol.summoner;


import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.impl.lol.builders.championmastery.ChampionMasteryBuilder;
import no.stelar7.api.r4j.impl.lol.builders.league.LeagueBuilder;
import no.stelar7.api.r4j.impl.lol.builders.matchv5.match.MatchListBuilder;
import no.stelar7.api.r4j.impl.lol.builders.thirdparty.ThirdPartyCodeBuilder;
import no.stelar7.api.r4j.impl.lol.raw.*;
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
    
    private int         profileIconId;
    private String      name;
    private int         summonerLevel;
    private String      accountId;
    private String      puuid;
    private String      id;
    private long        revisionDate;
    private LeagueShard platform;
    
    public static Summoner byPUUID(LeagueShard platform, String puuid)
    {
        return SummonerAPI.getInstance().getSummonerByPUUID(platform, puuid);
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
        return new MatchListBuilder().withPlatform(platform).withPuuid(puuid);
    }
    
    /**
     * This method has the same function as the main one but with the accountId and platform already set
     *
     * @return {@code List<String>}
     */
    public List<String> getTFTGames()
    {
        return TFTMatchAPI.getInstance().getMatchList(platform.toRegionShard(), puuid, 0, 20, null, null);
    }
    
    /**
     * Returns the mastery of the provided championid
     *
     * @param championId the championId
     * @return ChampionMastery
     */
    public ChampionMastery getChampionMastery(int championId)
    {
        return new ChampionMasteryBuilder().withPlatform(platform).withPUUID(puuid).withChampionId(championId).getChampionMastery();
    }
    
    /**
     * Returns a list of all the masteries of all the champions
     *
     * @return {@code List<ChampionMastery> }
     */
    public List<ChampionMastery> getChampionMasteries()
    {
        return new ChampionMasteryBuilder().withPlatform(platform).withPUUID(puuid).getChampionMasteries();
    }
    
    public List<LeagueEntry> getLeagueEntry()
    {
        return new LeagueBuilder().withPlatform(platform).withPuuid(puuid).getLeagueEntries();
    }
    
    public SpectatorGameInfo getCurrentGame()
    {
        return SpectatorAPI.getInstance().getCurrentGame(platform, puuid);
    }
    
    public String getThirdPartyCode()
    {
        return new ThirdPartyCodeBuilder().withPlatform(platform).withSummonerId(id).getCode();
    }
    
    public int getMasteryScore()
    {
        return new ChampionMasteryBuilder().withPlatform(platform).withPUUID(puuid).getMasteryScore();
    }
    
    /**
     * Platform this summoner is on
     *
     * @return Platform
     */
    public LeagueShard getPlatform()
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
        return profileIconId == summoner.profileIconId
               && summonerLevel == summoner.summonerLevel
               && revisionDate == summoner.revisionDate
               && Objects.equals(name, summoner.name)
               && Objects.equals(accountId, summoner.accountId)
               && Objects.equals(puuid, summoner.puuid)
               && Objects.equals(id, summoner.id)
               && platform == summoner.platform;
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
               ", accountId='" + accountId + '\'' +
               ", puuid='" + puuid + '\'' +
               ", id='" + id + '\'' +
               ", revisionDate=" + revisionDate +
               ", platform=" + platform +
               '}';
    }
}