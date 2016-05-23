package no.stelar7.api.l4j8.impl;

import java.util.*;

import no.stelar7.api.l4j8.basic.*;
import no.stelar7.api.l4j8.basic.DataCall.*;
import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.pojo.summoner.*;

public class L4J8
{

    private final DataCallBuilder builder = DataCall.builder();

    private final APICredentials  CREDS;

    public L4J8(String apiKey)
    {
        CREDS = new APICredentials(apiKey, APIType.NORMAL);
    }

    public L4J8(String apiKey, APIType type)
    {
        CREDS = new APICredentials(apiKey, type);
    }

    public L4J8(String apiKey, String tournamentKey)
    {
        CREDS = new APICredentials(apiKey, tournamentKey);
    }

    public Optional<Summoner> getSummoner(String name, Server server)
    {
        builder.withAPICredentials(CREDS);
        builder.withServer(Server.EUW);
        builder.withRegion(Server.EUW);
        builder.withEndpoint(URLEndpoint.SUMMONER_BY_NAME);
        builder.withURLData("{summonerName}", Utils.prepareForURL(name));
        Map<String, Summoner> data = (Map<String, Summoner>) builder.build();
        return Optional.of(data.get(name));
    }

}
