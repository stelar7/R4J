package no.stelar7.api.r4j.pojo.val.match;

import java.io.Serializable;
import java.util.Objects;

public class Damage implements Serializable
{
    private static final long serialVersionUID = -1161514106876666671L;
    
    private String receiver;
    private Integer damage;
    private Integer legshots;
    private Integer bodyshots;
    private Integer headshots;
    
    public String getReceiver()
    {
        return receiver;
    }
    
    public Integer getDamage()
    {
        return damage;
    }
    
    public Integer getLegshots()
    {
        return legshots;
    }
    
    public Integer getBodyshots()
    {
        return bodyshots;
    }
    
    public Integer getHeadshots()
    {
        return headshots;
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
        Damage damage1 = (Damage) o;
        return Objects.equals(receiver, damage1.receiver) &&
               Objects.equals(damage, damage1.damage) &&
               Objects.equals(legshots, damage1.legshots) &&
               Objects.equals(bodyshots, damage1.bodyshots) &&
               Objects.equals(headshots, damage1.headshots);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(receiver, damage, legshots, bodyshots, headshots);
    }
    
    @Override
    public String toString()
    {
        return "Damage{" +
               "receiver='" + receiver + '\'' +
               ", damage=" + damage +
               ", legshots=" + legshots +
               ", bodyshots=" + bodyshots +
               ", headshots=" + headshots +
               '}';
    }
}
