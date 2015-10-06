package no.stelar7.api.l4j8.pojo.currentgame;

import java.util.Collections;
import java.util.List;

import no.stelar7.api.l4j8.basic.APIObject;

public class CurrentGameParticipant extends APIObject
{
    private Boolean       bot;
    private Long          championId;
    private List<Mastery> masteries;
    private Long          profileIconId;
    private List<Rune>    runes;
    private Long          spell1Id;
    private Long          spell2Id;
    private Long          summonerId;
    private String        summonerName;
    private Long          teamId;

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
     * The masteries used by this participant
     *
     * @return {@code List<Masteries>}
     */
    public List<Mastery> getMasteries()
    {
        return Collections.unmodifiableList(this.masteries);
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
     * The runes used by this participant
     *
     * @return {@code List<Rune>}
     */
    public List<Rune> getRunes()
    {
        return Collections.unmodifiableList(this.runes);
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
     * The summoner ID of this participant
     *
     * @return Long
     */
    public Long getSummonerId()
    {
        return this.summonerId;
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

    @Override
    public String toString()
    {
        return "CurrentGameParticipant [\n\tbot=" + this.bot + ", \n\tchampionId=" + this.championId + ", \n\tmasteries=" + this.masteries + ", \n\tprofileIconId=" + this.profileIconId + ", \n\trunes=" + this.runes + ", \n\tspell1Id=" + this.spell1Id + ", \n\tspell2Id=" + this.spell2Id + ", \n\tsummonerId=" + this.summonerId + ", \n\tsummonerName=" + this.summonerName + ", \n\tteamId=" + this.teamId + "]";
    }

}
