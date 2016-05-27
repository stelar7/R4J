package no.stelar7.api.l4j8.impl;

import java.util.*;

import no.stelar7.api.l4j8.basic.*;
import no.stelar7.api.l4j8.basic.DataCall.*;
import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.pojo.summoner.*;

public class L4J8
{

    public static APICredentials CREDS;

    public L4J8(APICredentials cred)
    {
        L4J8.CREDS = cred;
    }

    public Map<Long, Optional<Summoner>> getSummoner(Server server, Long... userids)
    {
        Map<Long, Optional<Summoner>> finalResult = new HashMap<>();

        // Transform Long[] into List<Long>
        List<Long> ids = Arrays.asList(userids);

        if (ids.size() > 40)
        {
            finalResult.putAll(getSummoner(server, ids.subList(39, ids.size()).toArray(new Long[40])));
        }

        // Build the query
        final DataCallBuilder builder = DataCall.builder();
        builder.withAPICredentials(CREDS);
        builder.withServer(server);
        builder.withRegion(server);
        builder.withEndpoint(URLEndpoint.SUMMONER_BY_ID);
        ids.forEach(id -> builder.withURLData("{summonerId}", String.valueOf(id)));

        // Get the query result
        Object callResult = builder.build();

        // Handle 404
        if (callResult instanceof Optional && !((Optional<?>) callResult).isPresent())
        {
            ids.forEach(id -> finalResult.putIfAbsent(id, Optional.empty()));
            return finalResult;
        }

        Map<Long, Summoner> castResult = (Map<Long, Summoner>) callResult;

        // Map result to Optional<Summoner>, and add missing names
        castResult.forEach((key, value) -> finalResult.put(key, Optional.of(value)));
        ids.forEach(id -> finalResult.putIfAbsent(id, Optional.empty()));

        return finalResult;
    }

    public Map<String, Optional<Summoner>> getSummoner(Server server, String... usernames)
    {
        Map<String, Optional<Summoner>> finalResult = new HashMap<>();

        // Transform String[] into List<String>
        List<String> names = Arrays.asList(usernames);

        if (names.size() > 40)
        {
            finalResult.putAll(getSummoner(server, names.subList(39, names.size()).toArray(new String[40])));
        }

        // Build the query
        final DataCallBuilder builder = DataCall.builder();
        builder.withAPICredentials(CREDS);
        builder.withServer(server);
        builder.withRegion(server);
        builder.withEndpoint(URLEndpoint.SUMMONER_BY_NAME);
        names.forEach(name -> builder.withURLData("{summonerName}", Utils.prepareForURL(name)));

        // Get the query result
        Object callResult = builder.build();

        // Handle 404
        if (callResult instanceof Optional && !((Optional<?>) callResult).isPresent())
        {
            names.forEach(name -> finalResult.putIfAbsent(name, Optional.empty()));
            return finalResult;
        }

        Map<String, Summoner> castResult = (Map<String, Summoner>) callResult;

        // Map result to Optional<Summoner>, and add missing names
        castResult.forEach((key, value) -> finalResult.put(key, Optional.of(value)));
        names.forEach(name -> finalResult.putIfAbsent(name, Optional.empty()));

        return finalResult;
    }

}
