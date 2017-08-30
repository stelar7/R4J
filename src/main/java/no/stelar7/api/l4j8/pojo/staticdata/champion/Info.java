package no.stelar7.api.l4j8.pojo.staticdata.champion;

import java.io.Serializable;

public class Info  implements Serializable
{
    private int attack;
    private int defense;
    private int difficulty;
    private int magic;
    
    
    /**
     * Gets the attack.
     *
     * @return the attack
     */
    public int getAttack()
    {
        return this.attack;
    }
    
    /**
     * Gets the defense.
     *
     * @return the defense
     */
    public int getDefense()
    {
        return this.defense;
    }
    
    /**
     * Gets the difficulty.
     *
     * @return the difficulty
     */
    public int getDifficulty()
    {
        return this.difficulty;
    }
    
    /**
     * Gets the magic.
     *
     * @return the magic
     */
    public int getMagic()
    {
        return this.magic;
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
        
        Info info = (Info) o;
        
        if (attack != info.attack)
        {
            return false;
        }
        if (defense != info.defense)
        {
            return false;
        }
        if (difficulty != info.difficulty)
        {
            return false;
        }
        return magic == info.magic;
    }
    
    @Override
    public int hashCode()
    {
        int result = attack;
        result = 31 * result + defense;
        result = 31 * result + difficulty;
        result = 31 * result + magic;
        return result;
    }
    
    @Override
    public String toString()
    {
        return "Info{" +
               "attack=" + attack +
               ", defense=" + defense +
               ", difficulty=" + difficulty +
               ", magic=" + magic +
               '}';
    }
}
