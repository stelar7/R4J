package no.stelar7.api.r4j.pojo.lol.staticdata.perk;

import java.io.Serializable;
import java.util.List;

public class PerkPath implements Serializable
{
    private static final long           serialVersionUID = -2843318973414769225L;
    private              List<PerkSlot> slots;
    private              String         icon;
    private              String         key;
    private              String         name;
    private              int            id;
    
    public List<PerkSlot> getSlots()
    {
        return slots;
    }
    
    public String getIcon()
    {
        return icon;
    }
    
    public String getKey()
    {
        return key;
    }
    
    public String getName()
    {
        return name;
    }
    
    public int getId()
    {
        return id;
    }
    
    @Override
    public String toString()
    {
        return "PerkPath{" +
               "slots=" + slots +
               ", icon='" + icon + '\'' +
               ", key='" + key + '\'' +
               ", name='" + name + '\'' +
               ", id=" + id +
               '}';
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
        
        PerkPath perkPath = (PerkPath) o;
        
        if (id != perkPath.id)
        {
            return false;
        }
        if ((slots != null) ? !slots.equals(perkPath.slots) : (perkPath.slots != null))
        {
            return false;
        }
        if ((icon != null) ? !icon.equals(perkPath.icon) : (perkPath.icon != null))
        {
            return false;
        }
        if ((key != null) ? !key.equals(perkPath.key) : (perkPath.key != null))
        {
            return false;
        }
        return (name != null) ? name.equals(perkPath.name) : (perkPath.name == null);
    }
    
    @Override
    public int hashCode()
    {
        int result = slots != null ? slots.hashCode() : 0;
        result = 31 * result + (icon != null ? icon.hashCode() : 0);
        result = 31 * result + (key != null ? key.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }
}
