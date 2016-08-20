package no.stelar7.api.l4j8.pojo.match;

import no.stelar7.api.l4j8.basic.constants.*;
import no.stelar7.api.l4j8.basic.constants.api.Platform;
import no.stelar7.api.l4j8.basic.constants.api.Server;

import java.time.*;
import java.util.List;
import java.util.Optional;

public class MatchDetail
{
    private Integer mapId;
    private Long matchCreation;
    private Long matchDuration;
    private Long matchId;
    private String matchMode;
    private String matchType;
    private String matchVersion;
    private List<ParticipantIdentity> participantIdentities;
    private List<Participant> participants;
    private String platformId;
    private String queueType;
    private String region;
    private String season;
    private List<Team> teams;
    private Timeline timeline;

    @Override
    public boolean equals(final Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (this.getClass() != obj.getClass())
        {
            return false;
        }
        final MatchDetail other = (MatchDetail) obj;
        if (this.mapId == null)
        {
            if (other.mapId != null)
            {
                return false;
            }
        } else if (!this.mapId.equals(other.mapId))
        {
            return false;
        }
        if (this.matchCreation == null)
        {
            if (other.matchCreation != null)
            {
                return false;
            }
        } else if (!this.matchCreation.equals(other.matchCreation))
        {
            return false;
        }
        if (this.matchDuration == null)
        {
            if (other.matchDuration != null)
            {
                return false;
            }
        } else if (!this.matchDuration.equals(other.matchDuration))
        {
            return false;
        }
        if (this.matchId == null)
        {
            if (other.matchId != null)
            {
                return false;
            }
        } else if (!this.matchId.equals(other.matchId))
        {
            return false;
        }
        if (this.matchMode == null)
        {
            if (other.matchMode != null)
            {
                return false;
            }
        } else if (!this.matchMode.equals(other.matchMode))
        {
            return false;
        }
        if (this.matchType == null)
        {
            if (other.matchType != null)
            {
                return false;
            }
        } else if (!this.matchType.equals(other.matchType))
        {
            return false;
        }
        if (this.matchVersion == null)
        {
            if (other.matchVersion != null)
            {
                return false;
            }
        } else if (!this.matchVersion.equals(other.matchVersion))
        {
            return false;
        }
        if (this.participantIdentities == null)
        {
            if (other.participantIdentities != null)
            {
                return false;
            }
        } else if (!this.participantIdentities.equals(other.participantIdentities))
        {
            return false;
        }
        if (this.participants == null)
        {
            if (other.participants != null)
            {
                return false;
            }
        } else if (!this.participants.equals(other.participants))
        {
            return false;
        }
        if (this.platformId == null)
        {
            if (other.platformId != null)
            {
                return false;
            }
        } else if (!this.platformId.equals(other.platformId))
        {
            return false;
        }
        if (this.queueType == null)
        {
            if (other.queueType != null)
            {
                return false;
            }
        } else if (!this.queueType.equals(other.queueType))
        {
            return false;
        }
        if (this.region == null)
        {
            if (other.region != null)
            {
                return false;
            }
        } else if (!this.region.equals(other.region))
        {
            return false;
        }
        if (this.season == null)
        {
            if (other.season != null)
            {
                return false;
            }
        } else if (!this.season.equals(other.season))
        {
            return false;
        }
        if (this.teams == null)
        {
            if (other.teams != null)
            {
                return false;
            }
        } else if (!this.teams.equals(other.teams))
        {
            return false;
        }
        if (this.timeline == null)
        {
            if (other.timeline != null)
            {
                return false;
            }
        } else if (!this.timeline.equals(other.timeline))
        {
            return false;
        }
        return true;
    }

    /**
     * a GameQueueType representing the GameQueueType.
     *
     * @return GameQueueType
     */
    public Optional<GameQueueType> getGameQueueType()
    {
        return GameQueueType.getFromCode(this.queueType);
    }

    /**
     * a Map representing the Map.
     *
     * @return Map
     */
    public Optional<Map> getMap()
    {
        return Map.getFromCode(this.mapId);
    }

    /**
     * Match map ID
     *
     * @return Integer
     */
    public Integer getMapId()
    {
        return this.mapId;
    }

    /**
     * Match creation time. Designates when the team select lobby is created and/or the match is made through match making, not when the game actually starts.
     *
     * @return Long
     */
    public Long getMatchCreation()
    {
        return this.matchCreation;
    }

    /**
     * A ZonedDateTime of {@code getMatchCreation()}
     * <p>
     * * @return ZonedDateTime
     */
    public ZonedDateTime getMatchCreationDate()
    {
        return ZonedDateTime.ofInstant(Instant.ofEpochMilli(this.matchCreation), ZoneOffset.UTC);
    }

    /**
     * Match duration
     *
     * @return Long
     */
    public Long getMatchDuration()
    {
        return this.matchDuration;
    }

    /**
     * ID of the match
     *
     * @return Long
     */
    public Long getMatchId()
    {
        return this.matchId;
    }

    /**
     * a GameMode representing the gameMode.
     *
     * @return GameMode
     */
    public Optional<GameMode> getMatchMode()
    {
        return GameMode.getFromCode(this.matchMode);
    }

    /**
     * Match mode (Legal values: CLASSIC, ODIN, ARAM, TUTORIAL, ONEFORALL, ASCENSION, FIRSTBLOOD, KINGPORO)
     *
     * @return String
     */
    public String getMatchModeId()
    {
        return this.matchMode;
    }

    /**
     * a GameType representing the GameType.
     *
     * @return GameType
     */
    public Optional<GameType> getMatchType()
    {
        return GameType.getFromCode(this.matchType);
    }

    /**
     * Match type (Legal values: CUSTOM_GAME, MATCHED_GAME, TUTORIAL_GAME)
     *
     * @return the match type
     */
    public String getMatchTypeId()
    {
        return this.matchType;
    }

    /**
     * Match version
     *
     * @return String
     */
    public String getMatchVersion()
    {
        return this.matchVersion;
    }

    /**
     * Participant identity information
     *
     * @return {@code List<ParticipantIdentity>}
     */
    public List<ParticipantIdentity> getParticipantIdentities()
    {
        return this.participantIdentities;
    }

    /**
     * Participant information
     *
     * @return {@code List<Participant>}
     */
    public List<Participant> getParticipants()
    {
        return this.participants;
    }

    /**
     * Platform of the match (inferred from the platformId)
     *
     * @return  {@code Optional<Platform>}
     */
    public Optional<Platform> getPlatform()
    {
        return Platform.getFromCode(this.platformId);
    }

    /**
     * Platform ID of the match
     *
     * @return String
     */
    public String getPlatformId()
    {
        return this.platformId;
    }

    /**
     * Match queue type (Legal values: CUSTOM, NORMAL_5x5_BLIND, RANKED_SOLO_5x5, RANKED_PREMADE_5x5, BOT_5x5, NORMAL_3x3, RANKED_PREMADE_3x3, NORMAL_5x5_DRAFT, ODIN_5x5_BLIND, ODIN_5x5_DRAFT, BOT_ODIN_5x5, BOT_5x5_INTRO, BOT_5x5_BEGINNER, BOT_5x5_INTERMEDIATE, RANKED_TEAM_3x3, RANKED_TEAM_5x5, BOT_TT_3x3, GROUP_FINDER_5x5, ARAM_5x5, ONEFORALL_5x5, FIRSTBLOOD_1x1, FIRSTBLOOD_2x2, SR_6x6, URF_5x5, ONEFORALL_MIRRORMODE_5x5, BOT_URF_5x5, NIGHTMARE_BOT_5x5_RANK1, NIGHTMARE_BOT_5x5_RANK2, NIGHTMARE_BOT_5x5_RANK5, ASCENSION_5x5, HEXAKILL, BILGEWATER_ARAM_5x5, KING_PORO_5x5, COUNTER_PICK, BILGEWATER_5x5)
     *
     * @return String
     */
    public String getQueueTypeId()
    {
        return this.queueType;
    }

    /**
     * The server inferred from the regionId
     *
     * @return Server
     */
    public Optional<Server> getRegion()
    {
        return Server.getFromCode(this.region);
    }

    /**
     * Region where the match was played
     *
     * @return the region
     */
    public String getRegionId()
    {
        return this.region;
    }

    /**
     * the Season inferred from the seasonId
     *
     * @return Season
     */
    public Optional<Season> getSeason()
    {
        return Season.getFromCode(this.season);
    }

    /**
     * Season match was played (Legal values: PRESEASON3, SEASON3, PRESEASON2014, SEASON2014, PRESEASON2015, SEASON2015)
     *
     * @return String
     */
    public String getSeasonId()
    {
        return this.season;
    }

    /**
     * Team information
     *
     * @return {@code List<Team>}
     */
    public List<Team> getTeams()
    {
        return this.teams;
    }

    /**
     * Match timeline data (not included by default)
     *
     * @return Timeline
     */
    public Timeline getTimeline()
    {
        return this.timeline;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((this.mapId == null) ? 0 : this.mapId.hashCode());
        result = (prime * result) + ((this.matchCreation == null) ? 0 : this.matchCreation.hashCode());
        result = (prime * result) + ((this.matchDuration == null) ? 0 : this.matchDuration.hashCode());
        result = (prime * result) + ((this.matchId == null) ? 0 : this.matchId.hashCode());
        result = (prime * result) + ((this.matchMode == null) ? 0 : this.matchMode.hashCode());
        result = (prime * result) + ((this.matchType == null) ? 0 : this.matchType.hashCode());
        result = (prime * result) + ((this.matchVersion == null) ? 0 : this.matchVersion.hashCode());
        result = (prime * result) + ((this.participantIdentities == null) ? 0 : this.participantIdentities.hashCode());
        result = (prime * result) + ((this.participants == null) ? 0 : this.participants.hashCode());
        result = (prime * result) + ((this.platformId == null) ? 0 : this.platformId.hashCode());
        result = (prime * result) + ((this.queueType == null) ? 0 : this.queueType.hashCode());
        result = (prime * result) + ((this.region == null) ? 0 : this.region.hashCode());
        result = (prime * result) + ((this.season == null) ? 0 : this.season.hashCode());
        result = (prime * result) + ((this.teams == null) ? 0 : this.teams.hashCode());
        result = (prime * result) + ((this.timeline == null) ? 0 : this.timeline.hashCode());
        return result;
    }

    @Override
    public String toString()
    {
        return "MatchDetail [mapId=" + this.mapId + ", matchCreation=" + this.matchCreation + ", matchDuration=" + this.matchDuration + ", matchId=" + this.matchId + ", matchMode=" + this.matchMode + ", matchType=" + this.matchType + ", matchVersion=" + this.matchVersion + ", participantIdentities=" + this.participantIdentities + ", participants=" + this.participants + ", platformId=" + this.platformId + ", queueType=" + this.queueType + ", region=" + this.region + ", season=" + this.season + ", teams=" + this.teams + ", timeline=" + this.timeline + "]";
    }
}
