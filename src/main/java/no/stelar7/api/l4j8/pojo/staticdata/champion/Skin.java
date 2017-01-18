package no.stelar7.api.l4j8.pojo.staticdata.champion;

public class Skin
{
    private Integer id;
    private String  name;
    private Integer num;
    
    @Override
    public boolean equals(final Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (this.getClass() != obj.getClass())
        {
            return false;
        }
        final Skin other = (Skin) obj;
        if (this.id == null)
        {
            if (other.id != null)
            {
                return false;
            }
        } else if (!this.id.equals(other.id))
        {
            return false;
        }
        if (this.name == null)
        {
            if (other.name != null)
            {
                return false;
            }
        } else if (!this.name.equals(other.name))
        {
            return false;
        }
        if (this.num == null)
        {
            if (other.num != null)
            {
                return false;
            }
        } else if (!this.num.equals(other.num))
        {
            return false;
        }
        return true;
    }
    
    /**
     * Gets the id.
     *
     * @return the id
     */
    public Integer getId()
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
    public Integer getNum()
    {
        return this.num;
    }
    
    @Override
    public int hashCode()
    {
        final int prime  = 31;
        int       result = 1;
        result = (prime * result) + ((this.id == null) ? 0 : this.id.hashCode());
        result = (prime * result) + ((this.name == null) ? 0 : this.name.hashCode());
        result = (prime * result) + ((this.num == null) ? 0 : this.num.hashCode());
        return result;
    }
}
