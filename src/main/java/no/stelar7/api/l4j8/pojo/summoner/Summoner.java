package no.stelar7.api.l4j8.pojo.summoner;


import no.stelar7.api.l4j8.basic.Utils;

import java.time.*;

public final class Summoner
{
    private Integer profileIconId;
    private String  name;
    private Integer summonerLevel;
    private Long    accountId;
    private Long    id;
    private Long    revisionDate;
    
    /**
     * The Summoners ID
     *
     * @return Long
     */
    public Long getId()
    {
        return this.id;
    }
    
    /**
     * The Summoners name
     *
     * @return String
     */
    public String getName()
    {
        return this.name;
    }
    
    /**
     * The Summoners name normalized. (no whitespace, lowercase Locale.ENGLISH)
     *
     * @return String
     */
    public String getNormalizedName()
    {
        return Utils.normalizeSummonerName(name);
    }
    
    /**
     * ID of the summoner icon associated with the summoner
     *
     * @return Integer
     */
    public Integer getProfileIconId()
    {
        return this.profileIconId;
    }
    
    /**
     * Date summoner was last modified specified as epoch milliseconds. The following events will update this timestamp: profile icon change, playing the tutorial or advanced tutorial, finishing a game, summoner name change
     * <p>
     * * @return Long
     */
    public Long getRevisionDate()
    {
        return this.revisionDate;
    }
    
    /**
     * A ZonedDateTime of {@code getRevisionDate()}
     * <p>
     * * @return ZonedDateTime
     */
    public ZonedDateTime getRevisionDateAsDate()
    {
        return ZonedDateTime.ofInstant(Instant.ofEpochMilli(this.revisionDate), ZoneOffset.UTC);
    }
    
    /**
     * Summoner level associated with the summoner
     *
     * @return Integer
     */
    public Integer getSummonerLevel()
    {
        return this.summonerLevel;
    }
    
    /**
     * Account ID associated with the summoner
     *
     * @return Integer
     */
    public Long getAccountId()
    {
        return accountId;
    }
    
    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        
        Summoner summoner = (Summoner) o;
        
        if ((profileIconId != null) ? !profileIconId.equals(summoner.profileIconId) : (summoner.profileIconId != null))
        {
            return false;
        }
        if ((name != null) ? !name.equals(summoner.name) : (summoner.name != null))
        {
            return false;
        }
        if ((summonerLevel != null) ? !summonerLevel.equals(summoner.summonerLevel) : (summoner.summonerLevel != null))
        {
            return false;
        }
        if ((accountId != null) ? !accountId.equals(summoner.accountId) : (summoner.accountId != null))
        {
            return false;
        }
        if ((id != null) ? !id.equals(summoner.id) : (summoner.id != null))
        {
            return false;
        }
        return (revisionDate != null) ? revisionDate.equals(summoner.revisionDate) : (summoner.revisionDate == null);
    }
    
    @Override
    public int hashCode()
    {
        int result = profileIconId != null ? profileIconId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (summonerLevel != null ? summonerLevel.hashCode() : 0);
        result = 31 * result + (accountId != null ? accountId.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (revisionDate != null ? revisionDate.hashCode() : 0);
        return result;
    }
    
    
    @Override
    public String toString()
    {
        return String.format("Summoner{profileIconId=%d, name='%s', summonerLevel=%d, accountId=%d, id=%d, revisionDate=%d}", profileIconId, name, summonerLevel, accountId, id, revisionDate);
    }
}