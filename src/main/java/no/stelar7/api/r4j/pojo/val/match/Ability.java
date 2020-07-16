package no.stelar7.api.r4j.pojo.val.match;

import java.io.Serializable;
import java.util.Objects;

public class Ability implements Serializable
{
    private static final long serialVersionUID = -1967721053588165202L;
    
    private String grenadeEffects;
    private String ability1Effects;
    private String ability2Effects;
    private String ultimateEffects;
    
    public String getGrenadeEffects()
    {
        return grenadeEffects;
    }
    
    public String getAbility1Effects()
    {
        return ability1Effects;
    }
    
    public String getAbility2Effects()
    {
        return ability2Effects;
    }
    
    public String getUltimateEffects()
    {
        return ultimateEffects;
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
        Ability ability = (Ability) o;
        return Objects.equals(grenadeEffects, ability.grenadeEffects) &&
               Objects.equals(ability1Effects, ability.ability1Effects) &&
               Objects.equals(ability2Effects, ability.ability2Effects) &&
               Objects.equals(ultimateEffects, ability.ultimateEffects);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(grenadeEffects, ability1Effects, ability2Effects, ultimateEffects);
    }
    
    @Override
    public String toString()
    {
        return "Ability{" +
               "grenadeEffects='" + grenadeEffects + '\'' +
               ", ability1Effects='" + ability1Effects + '\'' +
               ", ability2Effects='" + ability2Effects + '\'' +
               ", ultimateEffects='" + ultimateEffects + '\'' +
               '}';
    }
}
