package no.stelar7.api.l4j8.pojo.staticdata.champion;

import java.io.Serializable;

public class Skin implements Serializable
{
    private static final long serialVersionUID = 6490718203654409943L;
    
    private int    id;
    private String name;
    private int    num;
    
    
    /**
     * Gets the id.
     *
     * @return the id
     */
    public int getId()
    {
        return this.id;
    }
    
    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName()
    {
        return this.name;
    }
    
    /**
     * Gets the num.
     *
     * @return the num
     */
    public int getNum()
    {
        return this.num;
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
        
        Skin skin = (Skin) o;
        
        if (id != skin.id)
        {
            return false;
        }
        if (num != skin.num)
        {
            return false;
        }
        return (name != null) ? name.equals(skin.name) : (skin.name == null);
    }
    
    @Override
    public int hashCode()
    {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + num;
        return result;
    }
    
    @Override
    public String toString()
    {
        return "Skin{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", num=" + num +
               '}';
    }
}
