package no.stelar7.api.l4j8.pojo.champion;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

import no.stelar7.api.l4j8.basic.APIObject;

public class Champion implements APIObject
{
    public static List<Champion> createFromString(final String json) throws Exception
    {
        final JsonNode node = APIObject.getDefaultMapper().readTree(json);

        final List<Champion> champs = new ArrayList<>();

        if (node.has("champions"))
        {
            node.get("champions").forEach(n -> {
                try
                {
                    final Champion champ = APIObject.getDefaultMapper().readValue(n.toString(), Champion.class);
                    champs.add(champ);
                } catch (final Exception e)
                {
                    e.printStackTrace();
                }
            });
        } else
        {
            final Champion champ = APIObject.getDefaultMapper().readValue(node.toString(), Champion.class);
            champs.add(champ);
        }

        return champs;
    }

    Boolean active;
    Boolean botEnabled;
    Boolean botMmEnabled;
    Boolean freeToPlay;
    Integer id;

    Boolean rankedPlayEnabled;

    public Integer getId()
    {
        return this.id;
    }

    public Boolean isActive()
    {
        return this.active;
    }

    public Boolean isBotEnabled()
    {
        return this.botEnabled;
    }

    public Boolean isBotMmEnabled()
    {
        return this.botMmEnabled;
    }

    public Boolean isFreeToPlay()
    {
        return this.freeToPlay;
    }

    public Boolean isRankedPlayEnabled()
    {
        return this.rankedPlayEnabled;
    }

    @Override
    public String toString()
    {
        return "Champion [active=" + this.active + ", botEnabled=" + this.botEnabled + ", botMmEnabled=" + this.botMmEnabled + ", freeToPlay=" + this.freeToPlay + ", id=" + this.id + ", rankedPlayEnabled=" + this.rankedPlayEnabled + "]";
    }
}
