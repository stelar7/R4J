package no.stelar7.api.r4j.pojo.shared;

import com.google.gson.JsonPrimitive;
import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.basic.exceptions.APIEnumNotUpToDateException;

import java.io.Serializable;
import java.util.Objects;

public class RiotAccountRegion implements Serializable
{
    private String puuid;
    private String game;
    private String region;

    public String getPuuid()
    {
        return puuid;
    }

    public String getGame()
    {
        return game;
    }

    public String getRegion()
    {
        return region;
    }

    /**
     * The active region is a League platform (lol and tft share them)
     *
     * @return the platform of the active region
     */
    public LeagueShard getRegionAsEnum()
    {
        return LeagueShard.fromString(this.region).orElseThrow(() -> new APIEnumNotUpToDateException(LeagueShard.class, new JsonPrimitive(this.region)));
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
        RiotAccountRegion that = (RiotAccountRegion) o;
        return Objects.equals(puuid, that.puuid) &&
               Objects.equals(game, that.game) &&
               Objects.equals(region, that.region);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(puuid, game, region);
    }

    @Override
    public String toString()
    {
        return "RiotAccountRegion{" +
               "puuid='" + puuid + '\'' +
               ", game='" + game + '\'' +
               ", region='" + region + '\'' +
               '}';
    }
}
