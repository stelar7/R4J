package no.stelar7.api.l4j8.pojo.tournament;

public class TournamentRegistrationParameters
{
    String name;
    Long   providerId;

    public String getName()
    {
        return name;
    }

    public Long getProviderId()
    {
        return providerId;
    }

    public TournamentRegistrationParameters(String name, long providerId2)
    {
        super();
        this.name = name;
        this.providerId = providerId2;
    }

    public TournamentRegistrationParameters(Long providerId)
    {
        super();
        this.providerId = providerId;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setProviderId(Long providerId)
    {
        this.providerId = providerId;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((providerId == null) ? 0 : providerId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TournamentRegistrationParameters other = (TournamentRegistrationParameters) obj;
        if (name == null)
        {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (providerId == null)
        {
            if (other.providerId != null)
                return false;
        } else if (!providerId.equals(other.providerId))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "TournamentRegistrationParameters [name=" + name + ", providerId=" + providerId + "]";
    }
}
