package no.stelar7.api.r4j.basic.utils;

import com.google.gson.*;
import no.stelar7.api.r4j.basic.constants.api.regions.*;
import no.stelar7.api.r4j.basic.constants.types.lol.*;
import no.stelar7.api.r4j.basic.ratelimiting.RateLimit;
import no.stelar7.api.r4j.basic.serializer.GenericEnumSerializer;
import no.stelar7.api.r4j.basic.constants.types.*;
import no.stelar7.api.r4j.basic.serializer.*;
import no.stelar7.api.r4j.pojo.lor.offline.expedition.ExpeditionState;
import no.stelar7.api.r4j.pojo.lor.offline.game.LoRGameState;

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
        
        // league
        builder.registerTypeAdapter(LoRGameState.class, new GenericEnumSerializer<LoRGameState>());
        builder.registerTypeAdapter(AscencionType.class, new GenericEnumSerializer<AscencionType>());
        builder.registerTypeAdapter(BuildingType.class, new GenericEnumSerializer<BuildingType>());
        builder.registerTypeAdapter(EventType.class, new GenericEnumSerializer<EventType>());
        builder.registerTypeAdapter(ExpeditionState.class, new GenericEnumSerializer<ExpeditionState>());
        builder.registerTypeAdapter(GameModeType.class, new GenericEnumSerializer<GameModeType>());
        builder.registerTypeAdapter(GameQueueType.class, new GenericEnumSerializer<GameQueueType>());
        builder.registerTypeAdapter(GameSubType.class, new GenericEnumSerializer<GameSubType>());
        builder.registerTypeAdapter(GameType.class, new GenericEnumSerializer<GameType>());
        builder.registerTypeAdapter(LaneType.class, new GenericEnumSerializer<LaneType>());
        builder.registerTypeAdapter(LevelUpType.class, new GenericEnumSerializer<LevelUpType>());
        builder.registerTypeAdapter(MapType.class, new GenericEnumSerializer<MapType>());
        builder.registerTypeAdapter(MonsterType.class, new GenericEnumSerializer<MonsterType>());
        builder.registerTypeAdapter(MonsterSubType.class, new GenericEnumSerializer<MonsterSubType>());
        builder.registerTypeAdapter(LeagueShard.class, new GenericEnumSerializer<LeagueShard>());
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
        builder.registerTypeAdapter(RegionShard.class, new RealmSpesificEnumSerializer());
        
        // valorant
        builder.registerTypeAdapter(no.stelar7.api.r4j.basic.constants.types.val.GameQueueType.class, new GenericEnumSerializer<no.stelar7.api.r4j.basic.constants.types.val.GameQueueType>());
        builder.registerTypeAdapter(no.stelar7.api.r4j.basic.constants.types.val.PlantSiteType.class, new GenericEnumSerializer<no.stelar7.api.r4j.basic.constants.types.val.PlantSiteType>());
        builder.registerTypeAdapter(no.stelar7.api.r4j.basic.constants.types.val.ProvisioningFlowType.class, new GenericEnumSerializer<no.stelar7.api.r4j.basic.constants.types.val.ProvisioningFlowType>());
        builder.registerTypeAdapter(no.stelar7.api.r4j.basic.constants.types.val.RoundResultCodeType.class, new GenericEnumSerializer<no.stelar7.api.r4j.basic.constants.types.val.RoundResultCodeType>());
        builder.registerTypeAdapter(no.stelar7.api.r4j.basic.constants.types.val.RoundResultType.class, new GenericEnumSerializer<no.stelar7.api.r4j.basic.constants.types.val.RoundResultType>());
        builder.registerTypeAdapter(no.stelar7.api.r4j.basic.constants.types.val.TeamType.class, new GenericEnumSerializer<no.stelar7.api.r4j.basic.constants.types.val.TeamType>());
        builder.registerTypeAdapter(no.stelar7.api.r4j.basic.constants.types.val.TierDivisionType.class, new GenericEnumSerializer<no.stelar7.api.r4j.basic.constants.types.val.TierDivisionType>());
        builder.registerTypeAdapter(no.stelar7.api.r4j.basic.constants.types.val.Season.class, new GenericEnumSerializer<no.stelar7.api.r4j.basic.constants.types.val.Season>());
        builder.registerTypeAdapter(no.stelar7.api.r4j.basic.constants.types.val.Armor.class, new GenericEnumSerializer<no.stelar7.api.r4j.basic.constants.types.val.Armor>());
        builder.registerTypeAdapter(no.stelar7.api.r4j.basic.constants.types.val.FinishingDamageType.class, new GenericEnumSerializer<no.stelar7.api.r4j.basic.constants.types.val.FinishingDamageType>());
        builder.registerTypeAdapter(no.stelar7.api.r4j.basic.constants.types.val.Skill.class, new GenericEnumSerializer<no.stelar7.api.r4j.basic.constants.types.val.Skill>());
        
        gson = builder.setPrettyPrinting().disableHtmlEscaping().create();
    }
    
    public static String padLeft(String input, String val, int length)
    {
        StringBuilder sb = new StringBuilder(input);
        while (sb.length() < length)
        {
            sb.insert(0, val);
        }
        
        return sb.toString();
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
    
    public static Object getLastItem(List<Object> list)
    {
        return list.size() > 0 ? list.get(list.size() - 1) : null;
    }
}
