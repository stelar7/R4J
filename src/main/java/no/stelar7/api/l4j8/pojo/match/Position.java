package no.stelar7.api.l4j8.pojo.match;

public class Position
{
    private Integer x;
    private Integer y;

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
        final Position other = (Position) obj;
        if (this.x == null)
        {
            if (other.x != null)
            {
                return false;
            }
        } else if (!this.x.equals(other.x))
        {
            return false;
        }
        if (this.y == null)
        {
            if (other.y != null)
            {
                return false;
            }
        } else if (!this.y.equals(other.y))
        {
            return false;
        }
        return true;
    }

    /**
     * The X position
     *
     * @return Integer
     */
    public Integer getX()
    {
        return this.x;
    }

    /**
     * The Y position
     *
     * @return Integer
     */
    public Integer getY()
    {
        return this.y;
    }

    @Override
    public int hashCode()
    {
        final int prime  = 31;
        int       result = 1;
        result = (prime * result) + ((this.x == null) ? 0 : this.x.hashCode());
        result = (prime * result) + ((this.y == null) ? 0 : this.y.hashCode());
        return result;
    }
}
