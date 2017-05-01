package no.stelar7.api.l4j8.pojo.esports.navitems;

import java.util.List;

public class NavItemWrapper
{
    private List<NavItem>       navItems;
    private List<EsportsLeague> leagues;
    
    public List<NavItem> getNavItems()
    {
        return navItems;
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
        
        NavItemWrapper navItems1 = (NavItemWrapper) o;
        
        if ((navItems != null) ? !navItems.equals(navItems1.navItems) : (navItems1.navItems != null))
        {
            return false;
        }
        return (leagues != null) ? leagues.equals(navItems1.leagues) : (navItems1.leagues == null);
    }
    
    @Override
    public int hashCode()
    {
        int result = navItems != null ? navItems.hashCode() : 0;
        result = 31 * result + (leagues != null ? leagues.hashCode() : 0);
        return result;
    }
    
    @Override
    public String toString()
    {
        return "NavItemWrapper{" +
               "navItems=" + navItems +
               ", leagues=" + leagues +
               '}';
    }
}
