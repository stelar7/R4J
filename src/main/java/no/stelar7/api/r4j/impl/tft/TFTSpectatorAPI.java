package no.stelar7.api.r4j.impl.tft;

import no.stelar7.api.r4j.basic.calling.*;
import no.stelar7.api.r4j.basic.constants.api.*;
import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.pojo.lol.spectator.SpectatorGameInfo;

import java.util.*;

public final class TFTSpectatorAPI
{
    private static final TFTSpectatorAPI INSTANCE = new TFTSpectatorAPI();

    public static TFTSpectatorAPI getInstance()
    {
        return TFTSpectatorAPI.INSTANCE;
    }

    private TFTSpectatorAPI()
    {
        // Hide public constructor
    }

    /**
     * The game the player is currently in.
     *
     * @param server the region to execute against
     * @param puuid  the puuid of the player
     * @return SpectatorGameInfo, or null if the player is not in a game
     */
    public SpectatorGameInfo getCurrentGame(LeagueShard server, String puuid)
    {
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.PUUID_ID_PLACEHOLDER, puuid)
                                                       .withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, DataCall.getCredentials().getTFTAPIKey())
                                                       .withEndpoint(URLEndpoint.V5_TFT_SPECTATOR_CURRENT)
                                                       .withPlatform(server);

        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", server);
        data.put("puuid", puuid);

        return builder.buildCached(SpectatorGameInfo.class, data);
    }
}
