package no.stelar7.api.l4j8.pojo.staticdata.shared;

public class MetaData
{
    Boolean isRune;
    String  tier;
    String  type;

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
        final MetaData other = (MetaData) obj;
        if (this.isRune == null)
        {
            if (other.isRune != null)
            {
                return false;
            }
        } else if (!this.isRune.equals(other.isRune))
        {
            return false;
        }
        if (this.tier == null)
        {
            if (other.tier != null)
            {
                return false;
            }
        } else if (!this.tier.equals(other.tier))
        {
            return false;
        }
        if (this.type == null)
        {
            if (other.type != null)
            {
                return false;
            }
        } else if (!this.type.equals(other.type))
        {
            return false;
        }
        return true;
    }

    /**
     * Gets the checks if is rune.
     *
     * @return the checks if is rune
     */
    public Boolean getIsRune()
    {
        return this.isRune;
    }

    /**
     * Gets the tier.
     *
     * @return the tier
     */
    public String getTier()
    {
        return this.tier;
    }

    /**
     * Gets the type.
     *
     * @return the type
     */
    public String getType()
    {
        return this.type;
    }

    @Override
    public int hashCode()
    {
        final int prime  = 31;
        int       result = 1;
        result = (prime * result) + ((this.isRune == null) ? 0 : this.isRune.hashCode());
        result = (prime * result) + ((this.tier == null) ? 0 : this.tier.hashCode());
        result = (prime * result) + ((this.type == null) ? 0 : this.type.hashCode());
        return result;
    }
}
