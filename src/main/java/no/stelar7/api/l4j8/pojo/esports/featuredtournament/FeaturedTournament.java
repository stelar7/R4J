package no.stelar7.api.l4j8.pojo.esports.featuredtournament;

public class FeaturedTournament
{
    
    
    private int     id;
    private String  locale;
    private String  tournamentTitle;
    private int     tournamentLeague;
    private boolean embedEnabled;
    private String  createdAt;
    private String  updatedAt;
    
    public int getId()
    {
        return id;
    }
    
    public void setId(int id)
    {
        this.id = id;
    }
    
    public String getLocale()
    {
        return locale;
    }
    
    public void setLocale(String locale)
    {
        this.locale = locale;
    }
    
    public String getTournamentTitle()
    {
        return tournamentTitle;
    }
    
    public void setTournamentTitle(String tournamentTitle)
    {
        this.tournamentTitle = tournamentTitle;
    }
    
    public int getTournamentLeague()
    {
        return tournamentLeague;
    }
    
    public void setTournamentLeague(int tournamentLeague)
    {
        this.tournamentLeague = tournamentLeague;
    }
    
    public boolean isEmbedEnabled()
    {
        return embedEnabled;
    }
    
    public void setEmbedEnabled(boolean embedEnabled)
    {
        this.embedEnabled = embedEnabled;
    }
    
    public String getCreatedAt()
    {
        return createdAt;
    }
    
    public void setCreatedAt(String createdAt)
    {
        this.createdAt = createdAt;
    }
    
    public String getUpdatedAt()
    {
        return updatedAt;
    }
    
    public void setUpdatedAt(String updatedAt)
    {
        this.updatedAt = updatedAt;
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
        
        FeaturedTournament that = (FeaturedTournament) o;
        
        if (id != that.id)
        {
            return false;
        }
        if (tournamentLeague != that.tournamentLeague)
        {
            return false;
        }
        if (embedEnabled != that.embedEnabled)
        {
            return false;
        }
        if ((locale != null) ? !locale.equals(that.locale) : (that.locale != null))
        {
            return false;
        }
        if ((tournamentTitle != null) ? !tournamentTitle.equals(that.tournamentTitle) : (that.tournamentTitle != null))
        {
            return false;
        }
        if ((createdAt != null) ? !createdAt.equals(that.createdAt) : (that.createdAt != null))
        {
            return false;
        }
        return (updatedAt != null) ? updatedAt.equals(that.updatedAt) : (that.updatedAt == null);
    }
    
    @Override
    public int hashCode()
    {
        int result = id;
        result = 31 * result + (locale != null ? locale.hashCode() : 0);
        result = 31 * result + (tournamentTitle != null ? tournamentTitle.hashCode() : 0);
        result = 31 * result + tournamentLeague;
        result = 31 * result + (embedEnabled ? 1 : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        return result;
    }
    
    
    @Override
    public String toString()
    {
        return "FeaturedTournament{" +
               "id=" + id +
               ", locale='" + locale + '\'' +
               ", tournamentTitle='" + tournamentTitle + '\'' +
               ", tournamentLeague=" + tournamentLeague +
               ", embedEnabled=" + embedEnabled +
               ", createdAt='" + createdAt + '\'' +
               ", updatedAt='" + updatedAt + '\'' +
               '}';
    }
}
