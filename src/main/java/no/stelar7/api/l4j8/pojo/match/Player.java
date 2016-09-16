package no.stelar7.api.l4j8.pojo.match;

public class Player
{
    private String  matchHistoryUri;
    private Integer profileIcon;
    private Long    summonerId;
    private String  summonerName;

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
        final Player other = (Player) obj;
        if (this.matchHistoryUri == null)
        {
            if (other.matchHistoryUri != null)
            {
                return false;
            }
        } else if (!this.matchHistoryUri.equals(other.matchHistoryUri))
        {
            return false;
        }
        if (this.profileIcon == null)
        {
            if (other.profileIcon != null)
            {
                return false;
            }
        } else if (!this.profileIcon.equals(other.profileIcon))
        {
            return false;
        }
        if (this.summonerId == null)
        {
            if (other.summonerId != null)
            {
                return false;
            }
        } else if (!this.summonerId.equals(other.summonerId))
        {
            return false;
        }
        if (this.summonerName == null)
        {
            if (other.summonerName != null)
            {
                return false;
            }
        } else if (!this.summonerName.equals(other.summonerName))
        {
            return false;
        }
        return true;
    }

    /**
     * Gets the match history uri.
     *
     * @return the match history uri
     */
    public String getMatchHistoryUri()
    {
        return this.matchHistoryUri;
    }

    /**
     * Gets the profile icon.
     *
     * @return the profile icon
     */
    public Integer getProfileIcon()
    {
        return this.profileIcon;
    }

    /**
     * Gets the summoner id.
     *
     * @return the summoner id
     */
    public Long getSummonerId()
    {
        return this.summonerId;
    }

    /**
     * Gets the summoner name.
     *
     * @return the summoner name
     */
    public String getSummonerName()
    {
        return this.summonerName;
    }

    @Override
    public int hashCode()
    {
        final int prime  = 31;
        int       result = 1;
        result = (prime * result) + ((this.matchHistoryUri == null) ? 0 : this.matchHistoryUri.hashCode());
        result = (prime * result) + ((this.profileIcon == null) ? 0 : this.profileIcon.hashCode());
        result = (prime * result) + ((this.summonerId == null) ? 0 : this.summonerId.hashCode());
        result = (prime * result) + ((this.summonerName == null) ? 0 : this.summonerName.hashCode());
        return result;
    }

    @Override
    public String toString()
    {
        return "Player [matchHistoryUri=" + this.matchHistoryUri + ", profileIcon=" + this.profileIcon + ", summonerId=" + this.summonerId + ", summonerName=" + this.summonerName + "]";
    }

}
