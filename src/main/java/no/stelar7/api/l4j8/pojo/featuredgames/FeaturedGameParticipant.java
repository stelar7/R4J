package no.stelar7.api.l4j8.pojo.featuredgames;

import no.stelar7.api.l4j8.pojo.shared.*;

public class FeaturedGameParticipant extends BaseGameParticipant
{
    private Boolean bot;
    
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
        final FeaturedGameParticipant other = (FeaturedGameParticipant) obj;
        if (!super.equals(other))
        {
            return false;
        }
        if (this.bot == null)
        {
            if (other.bot != null)
            {
                return false;
            }
        } else if (!this.bot.equals(other.bot))
        {
            return false;
        }
        return true;
    }
    
    @Override
    public int hashCode()
    {
        final int prime  = 31;
        int       result = 1;
        result = (prime * result) + super.hashCode();
        result = (prime * result) + ((this.bot == null) ? 0 : this.bot.hashCode());
        return result;
    }
    
    /**
     * A flag indicating if this participant is a bot
     *
     * @return boolean
     */
    protected Boolean isBot()
    {
        return this.bot;
    }
    
}
