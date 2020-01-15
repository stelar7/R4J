package no.stelar7.api.r4j.pojo.lol.tournament;

import java.io.Serializable;

public class TournamentRegistrationParameters implements Serializable
{
    private static final long serialVersionUID = 3489947085635831943L;
    
    private String name;
    private long   providerId;
    
    public TournamentRegistrationParameters(final long providerId)
    {
        this("", providerId);
    }
    
    public TournamentRegistrationParameters(final String name, final long providerId)
    {
        this.name = name;
        this.providerId = providerId;
    }
    
    
    /**
     * The optional name of the tournament.
     *
     * @return string
     */
    public String getName()
    {
        return this.name;
    }
    
    public void setName(final String name)
    {
        this.name = name;
    }
    
    /**
     * The provider ID to specify the regional registered provider data to associate this tournament.
     *
     * @return long
     */
    public long getProviderId()
    {
        return this.providerId;
    }
    
    public void setProviderId(final long providerId)
    {
        this.providerId = providerId;
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
        
        TournamentRegistrationParameters that = (TournamentRegistrationParameters) o;
        
        if (providerId != that.providerId)
        {
            return false;
        }
        return (name != null) ? name.equals(that.name) : (that.name == null);
    }
    
    @Override
    public int hashCode()
    {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (int) (providerId ^ (providerId >>> 32));
        return result;
    }
    
    @Override
    public String toString()
    {
        return "TournamentRegistrationParameters [name=" + this.name + ", providerId=" + this.providerId + "]";
    }
}
