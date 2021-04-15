package no.stelar7.api.r4j.pojo.lol.match.v5;

import java.io.Serializable;
import java.util.*;

public class PerkStyle implements Serializable
{
    private static final long serialVersionUID = 3304708021329029764L;
    
    private String              description;
    private List<PerkSelection> selections;
    
    public String getDescription()
    {
        return description;
    }
    
    public List<PerkSelection> getSelections()
    {
        return selections;
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
        return Objects.equals(description, perkStyle.description) && Objects.equals(selections, perkStyle.selections);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(description, selections);
    }
    
    @Override
    public String toString()
    {
        return "PerkStyle{" +
               "description='" + description + '\'' +
               ", selections=" + selections +
               '}';
    }
}
