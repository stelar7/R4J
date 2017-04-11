package no.stelar7.api.l4j8.pojo.currentgame;

import no.stelar7.api.l4j8.pojo.featuredgames.*;

import java.util.*;

public class CurrentGameParticipant extends FeaturedGameParticipant
{
    private List<Mastery> masteries;
    private List<Rune>    runes;
    private Long          summonerId;
    
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
        final CurrentGameParticipant other = (CurrentGameParticipant) obj;
        if (!super.equals(other))
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
        return true;
    }
    
    /**
     * The masteries used by this participant
     *
     * @return {@code List<Masteries>}
     */
    public List<Mastery> getMasteries()
    {
        return Collections.unmodifiableList(this.masteries);
    }
    
    /**
     * The runes used by this participant
     *
     * @return {@code List<Rune>}
     */
    public List<Rune> getRunes()
    {
        return Collections.unmodifiableList(this.runes);
    }
    
    /**
     * The summoner ID of this participant
     *
     * @return Long
     */
    public Long getSummonerId()
    {
        return this.summonerId;
    }
    
    @Override
    public int hashCode()
    {
        final int prime  = 31;
        int       result = 1;
        result = (prime * result) + super.hashCode();
        result = (prime * result) + ((this.masteries == null) ? 0 : this.masteries.hashCode());
        result = (prime * result) + ((this.runes == null) ? 0 : this.runes.hashCode());
        result = (prime * result) + ((this.summonerId == null) ? 0 : this.summonerId.hashCode());
        return result;
    }
    
    @Override
    public String toString()
    {
        return "CurrentGameParticipant{" +
               "masteries=" + masteries +
               ", runes=" + runes +
               ", summonerId=" + summonerId +
               '}';
    }
}
