package no.stelar7.api.l4j8.pojo.match;

public class Player
{
    private String  matchHistoryUri;
    private Integer profileIcon;
    private Long    summonerId;
    private String  summonerName;

    /**
     * Gets the match history uri.
     *
     * @return the match history uri
     */
    public String getMatchHistoryUri()
    {
        return matchHistoryUri;
    }

    /**
     * Gets the profile icon.
     *
     * @return the profile icon
     */
    public Integer getProfileIcon()
    {
        return profileIcon;
    }

    /**
     * Gets the summoner id.
     *
     * @return the summoner id
     */
    public Long getSummonerId()
    {
        return summonerId;
    }

    /**
     * Gets the summoner name.
     *
     * @return the summoner name
     */
    public String getSummonerName()
    {
        return summonerName;
    }

}
