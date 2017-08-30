package no.stelar7.api.l4j8.pojo.league;

import java.io.Serializable;

public class MiniSeries implements Serializable
{
    private int    losses;
    private String progress;
    private int    target;
    private int    wins;
    
   
    /**
     * Number of current losses in the mini series.
     *
     * @return the losses
     */
    public int getLosses()
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
    public int getTarget()
    {
        return this.target;
    }
    
    /**
     * Number of current wins in the mini series.
     *
     * @return the wins
     */
    public int getWins()
    {
        return this.wins;
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
        
        MiniSeries that = (MiniSeries) o;
        
        if (losses != that.losses)
        {
            return false;
        }
        if (target != that.target)
        {
            return false;
        }
        if (wins != that.wins)
        {
            return false;
        }
        return (progress != null) ? progress.equals(that.progress) : (that.progress == null);
    }
    
    @Override
    public int hashCode()
    {
        int result = losses;
        result = 31 * result + (progress != null ? progress.hashCode() : 0);
        result = 31 * result + target;
        result = 31 * result + wins;
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
