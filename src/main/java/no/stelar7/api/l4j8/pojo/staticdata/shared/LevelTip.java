package no.stelar7.api.l4j8.pojo.staticdata.shared;

import java.util.*;

public class LevelTip
{
    List<String> effect;
    List<String> label;

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
        final LevelTip other = (LevelTip) obj;
        if (this.effect == null)
        {
            if (other.effect != null)
            {
                return false;
            }
        } else if (!this.effect.equals(other.effect))
        {
            return false;
        }
        if (this.label == null)
        {
            if (other.label != null)
            {
                return false;
            }
        } else if (!this.label.equals(other.label))
        {
            return false;
        }
        return true;
    }

    /**
     * Gets the effect.
     *
     * @return the effect
     */
    public List<String> getEffect()
    {
        return this.effect;
    }

    /**
     * Gets the label.
     *
     * @return the label
     */
    public List<String> getLabel()
    {
        return this.label;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((this.effect == null) ? 0 : this.effect.hashCode());
        result = (prime * result) + ((this.label == null) ? 0 : this.label.hashCode());
        return result;
    }
}
