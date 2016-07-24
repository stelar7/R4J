package no.stelar7.api.l4j8.pojo.featuredgames;

import no.stelar7.api.l4j8.pojo.shared.*;

public class FeaturedGameParticipant extends BaseGameParticipant
{
    private Boolean bot;

    /**
     * A flag indicating if this participant is a bot
     *
     * @return boolean
     */
    public Boolean isBot()
    {
        return bot;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + super.hashCode();
        result = prime * result + ((bot == null) ? 0 : bot.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        FeaturedGameParticipant other = (FeaturedGameParticipant) obj;
        if (!super.equals(other))
        {
            return false;
        }
        if (bot == null)
        {
            if (other.bot != null)
            {
                return false;
            }
        } else if (!bot.equals(other.bot))
        {
            return false;
        }
        return true;
    }

}
