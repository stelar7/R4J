package no.stelar7.api.l4j8.pojo.staticdata.mastery;

import java.util.List;

public class MasteryTree
{
    private List<MasteryTreeList> Defense;
    private List<MasteryTreeList> Offense;
    private List<MasteryTreeList> Utility;

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
        final MasteryTree other = (MasteryTree) obj;
        if (this.Defense == null)
        {
            if (other.Defense != null)
            {
                return false;
            }
        } else if (!this.Defense.equals(other.Defense))
        {
            return false;
        }
        if (this.Offense == null)
        {
            if (other.Offense != null)
            {
                return false;
            }
        } else if (!this.Offense.equals(other.Offense))
        {
            return false;
        }
        if (this.Utility == null)
        {
            if (other.Utility != null)
            {
                return false;
            }
        } else if (!this.Utility.equals(other.Utility))
        {
            return false;
        }
        return true;
    }

    /**
     * Gets the defense.
     *
     * @return the defense
     */
    public List<MasteryTreeList> getDefense()
    {
        return this.Defense;
    }

    /**
     * Gets the offense.
     *
     * @return the offense
     */
    public List<MasteryTreeList> getOffense()
    {
        return this.Offense;
    }

    /**
     * Gets the utility.
     *
     * @return the utility
     */
    public List<MasteryTreeList> getUtility()
    {
        return this.Utility;
    }

    @Override
    public int hashCode()
    {
        final int prime  = 31;
        int       result = 1;
        result = (prime * result) + ((this.Defense == null) ? 0 : this.Defense.hashCode());
        result = (prime * result) + ((this.Offense == null) ? 0 : this.Offense.hashCode());
        result = (prime * result) + ((this.Utility == null) ? 0 : this.Utility.hashCode());
        return result;
    }
}
