package no.stelar7.api.l4j8.pojo.featuredgames;

import no.stelar7.api.l4j8.basic.constants.Team;

public class FeaturedGameParticipant
{
    private Boolean bot;
    private Long    championId;
    private Long    profileIconId;
    private Long    spell1Id;
    private Long    spell2Id;
    private String  summonerName;
    private Long    teamId;

    /**
     * Flag indicating whether or not this participant is a bot
     *
     * @return Boolean
     */
    public Boolean getBot()
    {
        return this.bot;
    }

    /**
     * The ID of the champion played by this participant
     *
     * @return Long
     */
    public Long getChampionId()
    {
        return this.championId;
    }

    /**
     * The ID of the profile icon used by this participant
     *
     * @return Long
     */
    public Long getProfileIconId()
    {
        return this.profileIconId;
    }

    /**
     * The ID of the first summoner spell used by this participant
     *
     * @return Long
     */
    public Long getSpell1Id()
    {
        return this.spell1Id;
    }

    /**
     * The ID of the second summoner spell used by this participant
     *
     * @return Long
     */
    public Long getSpell2Id()
    {
        return this.spell2Id;
    }

    /**
     * The summoner name of this participant
     *
     * @return String
     */
    public String getSummonerName()
    {
        return this.summonerName;
    }

    /**
     * The team ID of this participant, indicating the participant's team
     *
     * @return Long
     */
    public Long getTeamId()
    {
        return this.teamId;
    }
    
    /**
     * a Team representing the team of the participant
     *
     * @return Team
     */
    public Team getTeam()
    {
        return Team.getFromCode(this.teamId);
    }


}
