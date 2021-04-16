package no.stelar7.api.r4j.pojo.lol.clash;

import java.io.Serializable;
import java.util.*;

public class ClashTournament implements Serializable
{
    private static final long serialVersionUID = -845740111002585276L;
    private int id;
    private int                        themeId;
    private String                     nameKey;
    private String                     nameKeySecondary;
    private List<ClashTournamentPhase> schedule;
    
    public int getId()
    {
        return id;
    }
    
    public int getThemeId()
    {
        return themeId;
    }
    
    public String getNameKey()
    {
        return nameKey;
    }
    
    public String getNameKeySecondary()
    {
        return nameKeySecondary;
    }
    
    public List<ClashTournamentPhase> getSchedule()
    {
        return schedule;
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
        ClashTournament that = (ClashTournament) o;
        return id == that.id &&
               themeId == that.themeId &&
               Objects.equals(nameKey, that.nameKey) &&
               Objects.equals(nameKeySecondary, that.nameKeySecondary) &&
               Objects.equals(schedule, that.schedule);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(id, themeId, nameKey, nameKeySecondary, schedule);
    }
    
    @Override
    public String toString()
    {
        return "ClashTournament{" +
               "id=" + id +
               ", themeId=" + themeId +
               ", nameKey='" + nameKey + '\'' +
               ", nameKeySecondary='" + nameKeySecondary + '\'' +
               ", schedule=" + schedule +
               '}';
    }
}

