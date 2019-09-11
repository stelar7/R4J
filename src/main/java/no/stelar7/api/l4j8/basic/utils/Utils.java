package no.stelar7.api.l4j8.basic.utils;

import com.google.gson.*;
import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.basic.constants.types.*;
import no.stelar7.api.l4j8.basic.ratelimiting.RateLimit;
import no.stelar7.api.l4j8.basic.serializer.*;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.*;

public final class Utils
{
    private static final Gson gson;
    
    private Utils()
    {
    }
    
    static
    {
        GsonBuilder builder = new GsonBuilder();
        
        // Register enum types with GSON
        builder.registerTypeAdapter(AscencionType.class, new GenericEnumSerializer<AscencionType>());
        builder.registerTypeAdapter(BuildingType.class, new GenericEnumSerializer<BuildingType>());
        builder.registerTypeAdapter(EventType.class, new GenericEnumSerializer<EventType>());
        builder.registerTypeAdapter(GameModeType.class, new GenericEnumSerializer<GameModeType>());
        builder.registerTypeAdapter(GameQueueType.class, new GenericEnumSerializer<GameQueueType>());
        builder.registerTypeAdapter(GameSubType.class, new GenericEnumSerializer<GameSubType>());
        builder.registerTypeAdapter(GameType.class, new GenericEnumSerializer<GameType>());
        builder.registerTypeAdapter(LaneType.class, new GenericEnumSerializer<LaneType>());
        builder.registerTypeAdapter(LevelUpType.class, new GenericEnumSerializer<LevelUpType>());
        builder.registerTypeAdapter(MapType.class, new GenericEnumSerializer<Map>());
        builder.registerTypeAdapter(MonsterType.class, new GenericEnumSerializer<MonsterType>());
        builder.registerTypeAdapter(MonsterSubType.class, new GenericEnumSerializer<MonsterSubType>());
        builder.registerTypeAdapter(Platform.class, new GenericEnumSerializer<Platform>());
        builder.registerTypeAdapter(PointType.class, new GenericEnumSerializer<PointType>());
        builder.registerTypeAdapter(RateLimit.class, new RatelimitDeserializer());
        builder.registerTypeAdapter(RoleType.class, new GenericEnumSerializer<RoleType>());
        builder.registerTypeAdapter(SeasonType.class, new GenericEnumSerializer<SeasonType>());
        builder.registerTypeAdapter(SummonerSpellType.class, new GenericEnumSerializer<SummonerSpellType>());
        builder.registerTypeAdapter(TeamType.class, new GenericEnumSerializer<TeamType>());
        builder.registerTypeAdapter(TierType.class, new GenericEnumSerializer<TierType>());
        builder.registerTypeAdapter(TournamentMapType.class, new GenericEnumSerializer<TournamentMapType>());
        builder.registerTypeAdapter(TournamentPickType.class, new GenericEnumSerializer<TournamentPickType>());
        builder.registerTypeAdapter(TournamentSpectatorType.class, new GenericEnumSerializer<TournamentSpectatorType>());
        builder.registerTypeAdapter(TowerType.class, new GenericEnumSerializer<TowerType>());
        builder.registerTypeAdapter(WardType.class, new GenericEnumSerializer<WardType>());
        builder.registerTypeAdapter(RealmSpesificEnum.class, new RealmSpesificEnumSerializer());
        builder.registerTypeAdapter(ServicePlatform.class, new RealmSpesificEnumSerializer());
        gson = builder.setPrettyPrinting().disableHtmlEscaping().create();
    }
    
    public static Map<String, String> extractRegex(String input, String regex, String... vars)
    {
        Map<String, String> matches = new HashMap<>();
        
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(input);
        if (!m.find())
        {
            return Collections.emptyMap();
        }
        
        Arrays.stream(vars).forEach(v -> matches.put(v, m.group(v)));
        
        return matches;
    }
    
    public static String extractRegex(String input, String regex, String var)
    {
        return extractRegex(input, regex, new String[]{var}).getOrDefault(var, null);
    }
    
    public static Gson getGson()
    {
        return gson;
    }
    
    @SuppressWarnings("CharsetObjectCanBeUsed")
    public static String normalizeString(final String data)
    {
        try
        {
            final String stripped = data.replaceAll("\\s+", "");
            final String result   = stripped;
            return URLEncoder.encode(result, "UTF-8");
        } catch (final UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    public static String getURLData(String url)
    {
        final StringJoiner joiner = new StringJoiner("\n");
        try
        {
            final URL           outUrl = new URL(url);
            final URLConnection uc     = outUrl.openConnection();
            try (BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream(), StandardCharsets.UTF_8)))
            {
                String inputLine;
                while ((inputLine = in.readLine()) != null)
                {
                    joiner.add(inputLine);
                }
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        
        return joiner.toString();
    }
    
}
