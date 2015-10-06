package no.stelar7.api.l4j8.basic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import no.stelar7.api.l4j8.pojo.champion.Champion;
import no.stelar7.api.l4j8.pojo.currentgame.CurrentGameInfo;
import no.stelar7.api.l4j8.pojo.matchlist.MatchReference;
import no.stelar7.api.l4j8.pojo.summoner.Summoner;
import no.stelar7.api.l4j8.pojo.summoner.masteries.MasteryPage;
import no.stelar7.api.l4j8.pojo.summoner.names.StringMap;
import no.stelar7.api.l4j8.pojo.summoner.runes.RunePage;

public class APIObject
{
    Object createFromString(final Class<?> returnType, final String json) throws Exception
    {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        final JsonNode node = mapper.readTree(json);

        if (returnType.equals(Summoner.class))
        {
            final Map<Object, Summoner> summoners = new HashMap<>();
            node.fields().forEachRemaining(m -> {
                try
                {
                    final Summoner summoner = mapper.readValue(m.getValue().toString(), Summoner.class);
                    summoners.put(m.getKey(), summoner);
                } catch (final Exception e)
                {
                    e.printStackTrace();
                }
            });
            return summoners;
        }

        if (returnType.equals(MasteryPage.class) || returnType.equals(RunePage.class))
        {
            final Map<Object, List<Object>> masteries = new HashMap<>();
            node.fields().forEachRemaining(m -> {
                final List<Object> pages = new ArrayList<>();

                m.getValue().get("pages").iterator().forEachRemaining(n -> {
                    try
                    {
                        final Object page = mapper.readValue(n.toString(), returnType);
                        pages.add(page);
                    } catch (final Exception e)
                    {
                        e.printStackTrace();
                    }
                });

                masteries.put(m.getKey(), pages);
            });
            return masteries;
        }

        if (returnType.equals(StringMap.class))
        {
            final Map<String, String> data = new HashMap<>();
            node.fields().forEachRemaining(e -> data.put(e.getKey(), e.getValue().toString()));
            return data;
        }

        if (returnType.equals(Champion.class))
        {
            final List<Object> champs = new ArrayList<>();

            if (node.has("champions"))
            {
                node.get("champions").forEach(n -> {
                    try
                    {
                        final Object champ = mapper.readValue(n.toString(), returnType);
                        champs.add(champ);
                    } catch (final Exception e)
                    {
                        e.printStackTrace();
                    }
                });
            } else
            {
                final Object champ = mapper.readValue(node.toString(), returnType);
                champs.add(champ);
            }

            return champs;
        }

        if (returnType.equals(MatchReference.class))
        {
            final List<Object> matches = new ArrayList<>();

            if (node.get("totalGames").asLong() == 0)
            {
                return matches;
            }

            node.get("matches").forEach(n -> {
                try
                {
                    final Object match = mapper.readValue(n.toString(), returnType);
                    matches.add(match);
                } catch (final Exception e)
                {
                    e.printStackTrace();
                }
            });
            return matches;
        }

        if (returnType.equals(CurrentGameInfo.class))
        {
            final Object info = mapper.readValue(node.toString(), returnType);
            return info;
        }

        return mapper.readValue(node.toString(), returnType);
    }
}
