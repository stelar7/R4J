package no.stelar7.api.l4j8.pojo.match;

import java.util.*;

import no.stelar7.api.l4j8.basic.constants.*;
import no.stelar7.api.l4j8.basic.constants.Team;

public class Participant
{
    private Integer             championId;
    private String              highestAchievedSeasonTier;
    private List<Mastery>       masteries;
    private Integer             participantId;
    private List<Rune>          runes;
    private Integer             spell1Id;
    private Integer             spell2Id;
    private ParticipantStats    stats;
    private Integer             teamId;
    private ParticipantTimeline timeline;

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
        final Participant other = (Participant) obj;
        if (this.championId == null)
        {
            if (other.championId != null)
            {
                return false;
            }
        } else if (!this.championId.equals(other.championId))
        {
            return false;
        }
        if (this.highestAchievedSeasonTier == null)
        {
            if (other.highestAchievedSeasonTier != null)
            {
                return false;
            }
        } else if (!this.highestAchievedSeasonTier.equals(other.highestAchievedSeasonTier))
        {
            return false;
        }
        if (this.masteries == null)
        {
            if (other.masteries != null)
            {
                return false;
            }
        } else if (!this.masteries.equals(other.masteries))
        {
            return false;
        }
        if (this.participantId == null)
        {
            if (other.participantId != null)
            {
                return false;
            }
        } else if (!this.participantId.equals(other.participantId))
        {
            return false;
        }
        if (this.runes == null)
        {
            if (other.runes != null)
            {
                return false;
            }
        } else if (!this.runes.equals(other.runes))
        {
            return false;
        }
        if (this.spell1Id == null)
        {
            if (other.spell1Id != null)
            {
                return false;
            }
        } else if (!this.spell1Id.equals(other.spell1Id))
        {
            return false;
        }
        if (this.spell2Id == null)
        {
            if (other.spell2Id != null)
            {
                return false;
            }
        } else if (!this.spell2Id.equals(other.spell2Id))
        {
            return false;
        }
        if (this.stats == null)
        {
            if (other.stats != null)
            {
                return false;
            }
        } else if (!this.stats.equals(other.stats))
        {
            return false;
        }
        if (this.teamId == null)
        {
            if (other.teamId != null)
            {
                return false;
            }
        } else if (!this.teamId.equals(other.teamId))
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
     * Champion ID
     *
     * @return Integer
     */
    public Integer getChampionId()
    {
        return this.championId;
    }

    /**
     * Highest ranked tier achieved for the previous season, if any, otherwise null. Used to display border in game loading screen. (Legal values: CHALLENGER, MASTER, DIAMOND, PLATINUM, GOLD, SILVER, BRONZE, UNRANKED)
     *
     * @return String
     */
    public String getHighestAchievedSeasonTierId()
    {
        return this.highestAchievedSeasonTier;
    }

    /**
     * Highest ranked tier achieved for the previous season (inferred from the id)
     *
     * @return Tier
     */
    public Tier getHighestAchivedSeasonTier()
    {
        return Tier.getFromCode(this.highestAchievedSeasonTier);
    }

    /**
     * List of mastery information
     *
     * @return {@code List<Mastery>}
     */
    public List<Mastery> getMasteries()
    {
        return this.masteries;
    }

    /**
     * Participant ID
     *
     * @return Integer
     */
    public Integer getParticipantId()
    {
        return this.participantId;
    }

    /**
     * List of rune information
     *
     * @return {@code List<Rune>}
     */
    public List<Rune> getRunes()
    {
        return this.runes;
    }

    /**
     * First summoner spell
     *
     * @return SummonerSpell
     */
    public Optional<SummonerSpell> getSpell1()
    {
        return SummonerSpell.getFromCode(this.spell1Id);
    }

    /**
     * First summoner spell ID
     *
     * @return Long
     */
    public Integer getSpell1Id()
    {
        return this.spell1Id;
    }

    /**
     * Second summoner spell
     *
     * @return SummonerSpell
     */
    public Optional<SummonerSpell> getSpell2()
    {
        return SummonerSpell.getFromCode(this.spell2Id);
    }

    /**
     * Second summoner spell ID
     *
     * @return Long
     */
    public Integer getSpell2Id()
    {
        return this.spell2Id;
    }

    /**
     * Participant statistics
     *
     * @return ParticipantStats
     */
    public ParticipantStats getStats()
    {
        return this.stats;
    }

    /**
     * Team inferred from the team id
     *
     * @return Team
     */
    public Optional<Team> getTeam()
    {
        return Team.getFromCode(this.teamId);
    }

    /**
     * Team ID
     *
     * @return Integer
     */
    public Integer getTeamId()
    {
        return this.teamId;
    }

    /**
     * Timeline data. Delta fields refer to values for the specified period (e.g., the gold per minute over the first 10 minutes of the game versus the second 20 minutes of the game. Diffs fields refer to the deltas versus the calculated lane opponent(s).
     *
     * @return ParticipantTimeline
     */
    public ParticipantTimeline getTimeline()
    {
        return this.timeline;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((this.championId == null) ? 0 : this.championId.hashCode());
        result = (prime * result) + ((this.highestAchievedSeasonTier == null) ? 0 : this.highestAchievedSeasonTier.hashCode());
        result = (prime * result) + ((this.masteries == null) ? 0 : this.masteries.hashCode());
        result = (prime * result) + ((this.participantId == null) ? 0 : this.participantId.hashCode());
        result = (prime * result) + ((this.runes == null) ? 0 : this.runes.hashCode());
        result = (prime * result) + ((this.spell1Id == null) ? 0 : this.spell1Id.hashCode());
        result = (prime * result) + ((this.spell2Id == null) ? 0 : this.spell2Id.hashCode());
        result = (prime * result) + ((this.stats == null) ? 0 : this.stats.hashCode());
        result = (prime * result) + ((this.teamId == null) ? 0 : this.teamId.hashCode());
        result = (prime * result) + ((this.timeline == null) ? 0 : this.timeline.hashCode());
        return result;
    }

    @Override
    public String toString()
    {
        return "Participant [championId=" + this.championId + ", highestAchievedSeasonTier=" + this.highestAchievedSeasonTier + ", masteries=" + this.masteries + ", participantId=" + this.participantId + ", runes=" + this.runes + ", spell1Id=" + this.spell1Id + ", spell2Id=" + this.spell2Id + ", stats=" + this.stats + ", teamId=" + this.teamId + ", timeline=" + this.timeline + "]";
    }

}
