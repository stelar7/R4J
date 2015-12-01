package no.stelar7.api.l4j8.pojo.staticdata.champion;

public class BlockItem
{
    Integer count;
    Integer id;

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
        final BlockItem other = (BlockItem) obj;
        if (this.count == null)
        {
            if (other.count != null)
            {
                return false;
            }
        } else if (!this.count.equals(other.count))
        {
            return false;
        }
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
        return true;
    }

    /**
     * Gets the count.
     *
     * @return the count
     */
    public Integer getCount()
    {
        return this.count;
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

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((this.count == null) ? 0 : this.count.hashCode());
        result = (prime * result) + ((this.id == null) ? 0 : this.id.hashCode());
        return result;
    }
}
