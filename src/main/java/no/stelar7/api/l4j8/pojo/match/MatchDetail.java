package no.stelar7.api.l4j8.pojo.match;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;

import no.stelar7.api.l4j8.basic.APIObject;
import no.stelar7.api.l4j8.basic.Platform;
import no.stelar7.api.l4j8.basic.Server;
import no.stelar7.api.l4j8.basic.constants.GameMode;
import no.stelar7.api.l4j8.basic.constants.GameQueueType;
import no.stelar7.api.l4j8.basic.constants.GameType;
import no.stelar7.api.l4j8.basic.constants.Map;
import no.stelar7.api.l4j8.basic.constants.Season;

public class MatchDetail implements APIObject 
{
    private Integer                   mapId;
    private Long                      matchCreation;
    private Long                      matchDuration;
    private Long                      matchId;
    private String                    matchMode;
    private String                    matchType;
    private String                    matchVersion;
    private List<ParticipantIdentity> participantIdentities;
    private List<Participant>         participants;
    private String                    platformId;
    private String                    queueType;
    private String                    region;
    private String                    season;
    private List<Team>                teams;
    private Timeline                  timeline;

    
    
    /**
     * A ZonedDateTime of {@code getMatchCreation()}
     *
     * * @return ZonedDateTime
     */
    public ZonedDateTime getMatchCreationDate()
    {
        return ZonedDateTime.ofInstant(Instant.ofEpochMilli(this.matchCreation), ZoneOffset.UTC);
    }

    /**
     * Match map ID
     *
     * @return Integer
     */
    public Integer getMapId()
    {
        return mapId;
    }

    /**
     * a Map representing the Map.
     *
     * @return Map
     */
    public Map getMap()
    {
        return Map.getFromCode(this.mapId);
    }

    /**
     * Match creation time. Designates when the team select lobby is created and/or the match is made through match making, not when the game actually starts.
     *
     * @return Long
     */
    public Long getMatchCreation()
    {
        return matchCreation;
    }

    /**
     * Match duration
     *
     * @return Long
     */
    public Long getMatchDuration()
    {
        return matchDuration;
    }

    /**
     * ID of the match
     *
     * @return Long
     */
    public Long getMatchId()
    {
        return matchId;
    }

    /**
     * Match mode (Legal values: CLASSIC, ODIN, ARAM, TUTORIAL, ONEFORALL, ASCENSION, FIRSTBLOOD, KINGPORO)
     *
     * @return String
     */
    public String getMatchModeId()
    {
        return matchMode;
    }

    /**
     * a GameMode representing the gameMode.
     *
     * @return GameMode
     */
    public GameMode getMatchMode()
    {
        return GameMode.getFromCode(this.matchMode);
    }

    /**
     * Match type (Legal values: CUSTOM_GAME, MATCHED_GAME, TUTORIAL_GAME)
     *
     * @return the match type
     */
    public String getMatchTypeId()
    {
        return matchType;
    }

    /**
     * a GameType representing the GameType.
     *
     * @return GameType
     */
    public GameType getMatchType()
    {
        return GameType.getFromCode(this.matchType);
    }

    /**
     * Match version
     *
     * @return String
     */
    public String getMatchVersion()
    {
        return matchVersion;
    }

    /**
     * Participant identity information
     *
     * @return {@code List<ParticipantIdentity>}
     */
    public List<ParticipantIdentity> getParticipantIdentities()
    {
        return participantIdentities;
    }

    /**
     * Participant information
     *
     * @return {@code List<Participant>}
     */
    public List<Participant> getParticipants()
    {
        return participants;
    }

    /**
     * Platform ID of the match
     *
     * @return String
     */
    public String getPlatformId()
    {
        return platformId;
    }

    /**
     * Platform of the match (inferred from the platformId)
     * 
     * @return
     */
    public Platform getPlatform()
    {
        return Platform.getFromCode(platformId);
    }

    /**
     * Match queue type (Legal values: CUSTOM, NORMAL_5x5_BLIND, RANKED_SOLO_5x5, RANKED_PREMADE_5x5, BOT_5x5, NORMAL_3x3, RANKED_PREMADE_3x3, NORMAL_5x5_DRAFT, ODIN_5x5_BLIND, ODIN_5x5_DRAFT, BOT_ODIN_5x5, BOT_5x5_INTRO, BOT_5x5_BEGINNER, BOT_5x5_INTERMEDIATE, RANKED_TEAM_3x3, RANKED_TEAM_5x5, BOT_TT_3x3, GROUP_FINDER_5x5, ARAM_5x5, ONEFORALL_5x5, FIRSTBLOOD_1x1, FIRSTBLOOD_2x2, SR_6x6, URF_5x5, ONEFORALL_MIRRORMODE_5x5, BOT_URF_5x5, NIGHTMARE_BOT_5x5_RANK1, NIGHTMARE_BOT_5x5_RANK2, NIGHTMARE_BOT_5x5_RANK5, ASCENSION_5x5, HEXAKILL, BILGEWATER_ARAM_5x5, KING_PORO_5x5, COUNTER_PICK, BILGEWATER_5x5)
     *
     * @return String
     */
    public String getQueueTypeId()
    {
        return queueType;
    }

    /**
     * a GameQueueType representing the GameQueueType.
     *
     * @return GameQueueType
     */
    public GameQueueType getGameQueueType()
    {
        return GameQueueType.getFromCode(this.queueType);
    }

    /**
     * Region where the match was played
     *
     * @return the region
     */
    public String getRegionId()
    {
        return region;
    }

    /**
     * The server inferred from the regionId
     * 
     * @return Server
     */
    public Server getRegion()
    {
        return Server.getFromCode(region);
    }

    /**
     * Season match was played (Legal values: PRESEASON3, SEASON3, PRESEASON2014, SEASON2014, PRESEASON2015, SEASON2015)
     *
     * @return String
     */
    public String getSeasonId()
    {
        return season;
    }

    /**
     * the Season inferred from the seasonId
     * 
     * @return Season
     */
    public Season getSeason()
    {
        return Season.getFromCode(season);
    }

    /**
     * Team information
     *
     * @return {@code List<Team>}
     */
    public List<Team> getTeams()
    {
        return teams;
    }

    /**
     * Match timeline data (not included by default)
     *
     * @return Timeline
     */
    public Timeline getTimeline()
    {
        return timeline;
    }
}
