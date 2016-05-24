package no.stelar7.api.l4j8.impl;

import java.util.*;

import no.stelar7.api.l4j8.basic.*;
import no.stelar7.api.l4j8.basic.DataCall.*;
import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.pojo.summoner.*;

public class L4J8
{

    private final APICredentials CREDS;

    public L4J8(APICredentials cred)
    {
        this.CREDS = cred;
    }

    public Map<String, Optional<Summoner>> getSummoner(Server server, String... usernames)
    {
        // Transform String[] into List<String>
        List<String> names = Arrays.asList(usernames);

        // Build the query
        final DataCallBuilder builder = DataCall.builder();
        builder.withAPICredentials(CREDS);
        builder.withServer(server);
        builder.withRegion(server);
        builder.withEndpoint(URLEndpoint.SUMMONER_BY_NAME);
        names.forEach(name -> builder.withURLData("{summonerName}", Utils.prepareForURL(name)));

        // Get the query result
        Object callResult = builder.build();
        Map<String, Optional<Summoner>> finalResult = new HashMap<>();

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
