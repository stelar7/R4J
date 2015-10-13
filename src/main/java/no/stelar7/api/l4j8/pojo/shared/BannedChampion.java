package no.stelar7.api.l4j8.pojo.shared;

import no.stelar7.api.l4j8.basic.APIObject;
import no.stelar7.api.l4j8.basic.constants.Team;

public class BannedChampion implements APIObject
{
    private Long    championId;
    private Integer pickTurn;
    private Long    teamId;

    /**
     * The ID of the banned champion
     *
     * @return Long
     */
    public Long getChampionId()
    {
        return this.championId;
    }

    /**
     * The turn during which the champion was banned
     *
     * @return Integer
     */
    public Integer getPickTurn()
    {
        return this.pickTurn;
    }

    /**
     * a Team representing the team that banned the champion
     *
     * @return Team
     */
    public Team getTeam()
    {
        return Team.getFromCode(this.teamId);
    }

    /**
     * The ID of the team that banned the champion
     *
     * @return Long
     */
    public Long getTeamId()
    {
        return this.teamId;
    }

    @Override
    public String toString()
    {
        return "BannedChampion [championId=" + this.championId + ", pickTurn=" + this.pickTurn + ", teamId=" + this.teamId + "]";
    }

}
