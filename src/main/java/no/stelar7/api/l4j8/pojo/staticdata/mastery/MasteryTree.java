package no.stelar7.api.l4j8.pojo.staticdata.mastery;

import java.util.List;

public class MasteryTree
{
    List<MasteryTreeList> Defense;
    List<MasteryTreeList> Offense;
    List<MasteryTreeList> Utility;

    /**
     * Gets the defense.
     *
     * @return the defense
     */
    public List<MasteryTreeList> getDefense()
    {
        return Defense;
    }

    /**
     * Gets the offense.
     *
     * @return the offense
     */
    public List<MasteryTreeList> getOffense()
    {
        return Offense;
    }

    /**
     * Gets the utility.
     *
     * @return the utility
     */
    public List<MasteryTreeList> getUtility()
    {
        return Utility;
    }
}
