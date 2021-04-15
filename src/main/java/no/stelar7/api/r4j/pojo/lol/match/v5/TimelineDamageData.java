package no.stelar7.api.r4j.pojo.lol.match.v5;

import no.stelar7.api.r4j.basic.constants.types.lol.*;

import java.io.Serializable;
import java.util.Objects;

public class TimelineDamageData implements Serializable
{
    private static final long serialVersionUID = -8587347268764921198L;
    
    private boolean            basic;
    private int                magicDamage;
    private String             name;
    private int                participantId;
    private int                physicalDamage;
    private String             spellName;
    private SpellSlotType      spellSlot;
    private int                trueDamage;
    private TimelineDamageType type;
    
    public boolean isBasic()
    {
        return basic;
    }
    
    public int getMagicDamage()
    {
        return magicDamage;
    }
    
    public String getName()
    {
        return name;
    }
    
    public int getParticipantId()
    {
        return participantId;
    }
    
    public int getPhysicalDamage()
    {
        return physicalDamage;
    }
    
    public String getSpellName()
    {
        return spellName;
    }
    
    public SpellSlotType getSpellSlot()
    {
        return spellSlot;
    }
    
    public int getTrueDamage()
    {
        return trueDamage;
    }
    
    public TimelineDamageType getType()
    {
        return type;
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
        TimelineDamageData that = (TimelineDamageData) o;
        return basic == that.basic && magicDamage == that.magicDamage && participantId == that.participantId && physicalDamage == that.physicalDamage && spellSlot == that.spellSlot && trueDamage == that.trueDamage && Objects.equals(name, that.name) && Objects.equals(spellName, that.spellName) && type == that.type;
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(basic, magicDamage, name, participantId, physicalDamage, spellName, spellSlot, trueDamage, type);
    }
    
    @Override
    public String toString()
    {
        return "TimelineDamageData{" +
               "basic=" + basic +
               ", magicDamage=" + magicDamage +
               ", name='" + name + '\'' +
               ", participantId=" + participantId +
               ", physicalDamage=" + physicalDamage +
               ", spellName='" + spellName + '\'' +
               ", spellSlot=" + spellSlot +
               ", trueDamage=" + trueDamage +
               ", type=" + type +
               '}';
    }
}
