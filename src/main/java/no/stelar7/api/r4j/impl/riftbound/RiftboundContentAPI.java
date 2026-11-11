package no.stelar7.api.r4j.impl.riftbound;

import no.stelar7.api.r4j.basic.calling.*;
import no.stelar7.api.r4j.basic.constants.api.*;
import no.stelar7.api.r4j.basic.constants.api.regions.RegionShard;
import no.stelar7.api.r4j.basic.constants.types.ApiKeyType;
import no.stelar7.api.r4j.pojo.riftbound.RiftboundContent;

import java.util.*;

/**
 * riftbound-content-v1
 */
public final class RiftboundContentAPI
{
    private static final RiftboundContentAPI INSTANCE = new RiftboundContentAPI();

    public static RiftboundContentAPI getInstance()
    {
        return RiftboundContentAPI.INSTANCE;
    }

    private RiftboundContentAPI()
    {
        // Hide public constructor
    }

    /**
     * All the sets and cards in the game, using the LoL api key.
     *
     * @param server the region to query (AMERICAS, ASIA or EUROPE)
     * @param locale the locale to get the content in (ie. "en"), or null to use the api default
     * @return the content
     */
    public RiftboundContent getContent(RegionShard server, String locale)
    {
        return getContent(server, locale, ApiKeyType.LOL);
    }

    /**
     * All the sets and cards in the game.
     *
     * @param server  the region to query (AMERICAS, ASIA or EUROPE)
     * @param locale  the locale to get the content in (ie. "en"), or null to use the api default
     * @param keyType which of the keys in the APICredentials has access to the Riftbound api
     * @return the content
     */
    public RiftboundContent getContent(RegionShard server, String locale, ApiKeyType keyType)
    {
        DataCallBuilder builder = new DataCallBuilder().withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, DataCall.getCredentials().getKey(keyType))
                                                       .withEndpoint(URLEndpoint.V1_RIFTBOUND_CONTENT)
                                                       .withPlatform(server);

        if (locale != null)
        {
            builder.withQueryParameter(Constants.LOCALE_PLACEHOLDER_DATA, locale);
        }

        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", server);
        data.put("locale", locale);

        return builder.buildCached(RiftboundContent.class, data);
    }
}
