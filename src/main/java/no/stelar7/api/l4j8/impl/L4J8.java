package no.stelar7.api.l4j8.impl;

import java.util.*;

import no.stelar7.api.l4j8.basic.*;
import no.stelar7.api.l4j8.basic.DataCall.*;
import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.pojo.summoner.*;

/**
 * The base class for calling anything from this api wrapper
 * 
 *
 */
public class L4J8
{

    /**
     * Constructor for the L4J8 class.
     * 
     * @param cred
     *            the API credentials used for the API (your token)
     */
    public L4J8(final APICredentials creds)
    {
        DataCallBuilder.defaultCredentials(creds);
    }

    /**
     * The response object contains the summoner objects mapped by their user id.
     * 
     * @param server
     *            the region to execute against
     * @param userids
     *            list of summonerIds associated with summoners to retrieve.
     * @return a map of the userId to an Optional Summoner
     */
    public Map<Long, Optional<Summoner>> getSummoner(final Server server, final Long... userids)
    {
        final Map<Long, Optional<Summoner>> finalResult = new HashMap<>();

        // Transform Long[] into List<Long>
        final List<Long> ids = Arrays.asList(userids);

        if (ids.size() > 40)
        {
            // Recursively call this method, so it can be called with an arbitrary amount of userids
            List<Long> subList = ids.subList(39, ids.size());
            finalResult.putAll(this.getSummoner(server, subList.toArray(new Long[subList.size()])));
        }

        // Build the query
        final DataCallBuilder builder = DataCall.builder();
        builder.withServer(server);
        builder.withRegion(server);
        builder.withEndpoint(URLEndpoint.SUMMONER_BY_ID);
        ids.forEach(id -> builder.withURLData("{summonerId}", String.valueOf(id)));

        // Get the query result
        final Object callResult = builder.build();

        // Handle 404
        if ((callResult instanceof Optional) && !((Optional<?>) callResult).isPresent())
        {
            ids.forEach(id -> finalResult.putIfAbsent(id, Optional.empty()));
            return finalResult;
        }

        final Map<Long, Summoner> castResult = (Map<Long, Summoner>) callResult;

        // Map result to Optional<Summoner>, and add missing names
        castResult.forEach((key, value) -> finalResult.put(key, Optional.of(value)));
        ids.forEach(id -> finalResult.putIfAbsent(id, Optional.empty()));

        return finalResult;
    }

    /**
     * The response object contains the summoner objects mapped by their username.
     * 
     * @param server
     *            the region to execute against
     * @param usernames
     *            list of summoner names or standardized summoner names associated with summoners to retrieve.
     * @return a map of the userId to an Optional Summoner
     */
    public Map<String, Optional<Summoner>> getSummoner(final Server server, final String... usernames)
    {
        final Map<String, Optional<Summoner>> finalResult = new HashMap<>();

        // Transform String[] into List<String>
        final List<String> names = Arrays.asList(usernames);

        if (names.size() > 40)
        {
            // Recursively call this method, so it can be called with an arbitrary amount of userids
            List<String> subList = names.subList(39, names.size());
            finalResult.putAll(this.getSummoner(server, subList.toArray(new String[subList.size()])));
        }

        // Build the query
        final DataCallBuilder builder = DataCall.builder();
        builder.withServer(server);
        builder.withRegion(server);
        builder.withEndpoint(URLEndpoint.SUMMONER_BY_NAME);
        names.forEach(name -> builder.withURLData("{summonerName}", Utils.prepareForURL(name)));

        // Get the query result
        final Object callResult = builder.build();

        // Handle 404
        if ((callResult instanceof Optional) && !((Optional<?>) callResult).isPresent())
        {
            names.forEach(name -> finalResult.putIfAbsent(name, Optional.empty()));
            return finalResult;
        }

        final Map<String, Summoner> castResult = (Map<String, Summoner>) callResult;

        // Map result to Optional<Summoner>, and add missing names
        castResult.forEach((key, value) -> finalResult.put(key, Optional.of(value)));
        names.forEach(name -> finalResult.putIfAbsent(name, Optional.empty()));

        return finalResult;
    }

    public TournamentAPI getTournamentAPI()
    {
        return TournamentAPI.getInstance();
    }

}
