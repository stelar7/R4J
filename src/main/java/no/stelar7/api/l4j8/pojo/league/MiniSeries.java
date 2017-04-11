package no.stelar7.api.l4j8.pojo.league;

public class MiniSeries
{
    private Integer losses;
    private String  progress;
    private Integer target;
    private Integer wins;
    
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
        final MiniSeries other = (MiniSeries) obj;
        if (this.losses == null)
        {
            if (other.losses != null)
            {
                return false;
            }
        } else if (!this.losses.equals(other.losses))
        {
            return false;
        }
        if (this.progress == null)
        {
            if (other.progress != null)
            {
                return false;
            }
        } else if (!this.progress.equals(other.progress))
        {
            return false;
        }
        if (this.target == null)
        {
            if (other.target != null)
            {
                return false;
            }
        } else if (!this.target.equals(other.target))
        {
            return false;
        }
        if (this.wins == null)
        {
            if (other.wins != null)
            {
                return false;
            }
        } else if (!this.wins.equals(other.wins))
        {
            return false;
        }
        return true;
    }
    
    /**
     * Number of current losses in the mini series.
     *
     * @return the losses
     */
    public Integer getLosses()
    {
        return this.losses;
    }
    
    /**
     * String showing the current, sequential mini series progress where 'W' represents a win, 'L' represents a loss, and 'N' represents a game that hasn't been played yet.
     *
     * @return the progress
     */
    public String getProgress()
    {
        return this.progress;
    }
    
    /**
     * Number of wins required for promotion.
     *
     * @return the target
     */
    public Integer getTarget()
    {
        return this.target;
    }
    
    /**
     * Number of current wins in the mini series.
     *
     * @return the wins
     */
    public Integer getWins()
    {
        return this.wins;
    }
    
    @Override
    public int hashCode()
    {
        final int prime  = 31;
        int       result = 1;
        result = (prime * result) + ((this.losses == null) ? 0 : this.losses.hashCode());
        result = (prime * result) + ((this.progress == null) ? 0 : this.progress.hashCode());
        result = (prime * result) + ((this.target == null) ? 0 : this.target.hashCode());
        result = (prime * result) + ((this.wins == null) ? 0 : this.wins.hashCode());
        return result;
    }
    
    @Override
    public String toString()
    {
        return "MiniSeries{" +
               "losses=" + losses +
               ", progress='" + progress + '\'' +
               ", target=" + target +
               ", wins=" + wins +
               '}';
    }
}
