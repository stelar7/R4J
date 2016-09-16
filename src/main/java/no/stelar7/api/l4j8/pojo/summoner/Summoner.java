package no.stelar7.api.l4j8.pojo.summoner;

import no.stelar7.api.l4j8.basic.constants.api.Server;

import java.time.*;

public class Summoner
{
    private Long id;
    private String name;
    private Integer profileIconId;
    private Long revisionDate;
    private Server server;
    private Integer summonerLevel;

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
        final Summoner other = (Summoner) obj;
        if (this.id == null)
        {
            if (other.id != null)
            {
                return false;
            }
        } else if (!this.id.equals(other.id))
        {
            return false;
        }
        if (this.name == null)
        {
            if (other.name != null)
            {
                return false;
            }
        } else if (!this.name.equals(other.name))
        {
            return false;
        }
        if (this.profileIconId == null)
        {
            if (other.profileIconId != null)
            {
                return false;
            }
        } else if (!this.profileIconId.equals(other.profileIconId))
        {
            return false;
        }
        if (this.revisionDate == null)
        {
            if (other.revisionDate != null)
            {
                return false;
            }
        } else if (!this.revisionDate.equals(other.revisionDate))
        {
            return false;
        }
        if (this.summonerLevel == null)
        {
            if (other.summonerLevel != null)
            {
                return false;
            }
        } else if (!this.summonerLevel.equals(other.summonerLevel))
        {
            return false;
        }
        return true;
    }

    /**
     * The Summoners ID
     *
     * @return Long
     */
    public Long getId()
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
     * @return Integer
     */
    public Integer getProfileIconId()
    {
        return this.profileIconId;
    }

    /**
     * The summoners region
     *
     * @return Server
     */
    public Server getRegion()
    {
        return this.server;
    }

    /**
     * Date summoner was last modified specified as epoch milliseconds. The following events will update this timestamp: profile icon change, playing the tutorial or advanced tutorial, finishing a game, summoner name change
     * <p>
     * * @return Long
     */
    public Long getRevisionDate()
    {
        return this.revisionDate;
    }

    /**
     * A ZonedDateTime of {@code getRevisionDate()}
     * <p>
     * * @return ZonedDateTime
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

    @Override
    public int hashCode()
    {
        final int prime  = 31;
        int       result = 1;
        result = (prime * result) + ((this.id == null) ? 0 : this.id.hashCode());
        result = (prime * result) + ((this.name == null) ? 0 : this.name.hashCode());
        result = (prime * result) + ((this.profileIconId == null) ? 0 : this.profileIconId.hashCode());
        result = (prime * result) + ((this.revisionDate == null) ? 0 : this.revisionDate.hashCode());
        result = (prime * result) + ((this.summonerLevel == null) ? 0 : this.summonerLevel.hashCode());
        return result;
    }

    @Override
    public String toString()
    {
        return "Summoner [id=" + this.id + ", server=" + this.server + ", name=" + this.name + ", profileIconId=" + this.profileIconId + ", revisionDate=" + this.revisionDate + ", summonerLevel=" + this.summonerLevel + "]";
    }

}