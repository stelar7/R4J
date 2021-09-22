package no.stelar7.api.r4j.pojo.lol.match.v5;

import java.io.Serializable;
import java.util.*;

public class PerkStyle implements Serializable
{
    private static final long serialVersionUID = -1935275150471079204L;
    
    private String description;
    private List<PerkSelection> selections;
    private int                 style;
    
    public String getDescription()
    {
        return description;
    }
    
    public List<PerkSelection> getSelections()
    {
        return selections;
    }
    
    public int getStyle()
    {
        return style;
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
        PerkStyle perkStyle = (PerkStyle) o;
        return style == perkStyle.style && Objects.equals(description, perkStyle.description) && Objects.equals(selections, perkStyle.selections);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(description, selections, style);
    }
    
    @Override
    public String toString()
    {
        return "PerkStyle{" +
               "description='" + description + '\'' +
               ", selections=" + selections +
               ", style=" + style +
               '}';
    }
}
