package no.stelar7.api.l4j8.pojo.esports.featuredtournament;

import no.stelar7.api.l4j8.pojo.esports.navitems.EsportsLeague;

import java.util.List;

public class FeaturedTournamentWrapper
{
    
    private FeaturedTournament  featuredTournamentOption;
    private List<EsportsLeague> leagues;
    
    public FeaturedTournament getFeaturedTournamentOption()
    {
        return featuredTournamentOption;
    }
    
    public List<EsportsLeague> getLeagues()
    {
        return leagues;
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
        
        FeaturedTournamentWrapper that = (FeaturedTournamentWrapper) o;
        
        if ((featuredTournamentOption != null) ? !featuredTournamentOption.equals(that.featuredTournamentOption) : (that.featuredTournamentOption != null))
        {
            return false;
        }
        return (leagues != null) ? leagues.equals(that.leagues) : (that.leagues == null);
    }
    
    @Override
    public int hashCode()
    {
        int result = featuredTournamentOption != null ? featuredTournamentOption.hashCode() : 0;
        result = 31 * result + (leagues != null ? leagues.hashCode() : 0);
        return result;
    }
    
    @Override
    public String toString()
    {
        return "FeaturedTournamentWrapper{" +
               "featuredTournamentOption=" + featuredTournamentOption +
               ", leagues=" + leagues +
               '}';
    }
}
