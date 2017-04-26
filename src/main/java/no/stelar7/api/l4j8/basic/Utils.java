package no.stelar7.api.l4j8.basic;

import com.google.gson.*;
import no.stelar7.api.l4j8.basic.constants.types.*;
import no.stelar7.api.l4j8.basic.deserializer.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Locale;
import java.util.logging.*;

public final class Utils
{
    private static final Logger LOGGER = Logger.getGlobal();
    private static final Gson gson;
    
    private Utils()
    {
    }
    
    static
    {
        GsonBuilder builder = new GsonBuilder();
        
        // Register enum types with GSON
        builder.registerTypeAdapter(AscencionType.class, new AscencionTypeDeserializer());
        builder.registerTypeAdapter(BuildingType.class, new BuildingTypeDeserializer());
        builder.registerTypeAdapter(ChampionType.class, new ChampionTypeDeserializer());
        builder.registerTypeAdapter(EventType.class, new EventTypeDeserializer());
        builder.registerTypeAdapter(GameModeType.class, new GameModeTypeDeserializer());
        builder.registerTypeAdapter(GameQueueType.class, new GameQueueTypeDeserializer());
        builder.registerTypeAdapter(GameSubType.class, new GameSubTypeDeserializer());
        builder.registerTypeAdapter(GameType.class, new GameTypeDeserializer());
        builder.registerTypeAdapter(LaneType.class, new LaneTypeDeserializer());
        builder.registerTypeAdapter(LevelUpType.class, new LevelUpTypeDeserializer());
        builder.registerTypeAdapter(MapType.class, new MapTypeDeserializer());
        builder.registerTypeAdapter(MonsterType.class, new MonsterTypeDeserializer());
        builder.registerTypeAdapter(MonsterSubType.class, new MonsterSubTypeDeserializer());
        builder.registerTypeAdapter(PlayerStatSummaryType.class, new PlayerStatSummaryTypeDeserializer());
        builder.registerTypeAdapter(PointType.class, new PointTypeDeserializer());
        builder.registerTypeAdapter(RankedQueueType.class, new RankedQueueTypeDeserializer());
        builder.registerTypeAdapter(RoleType.class, new RoleTypeDeserializer());
        builder.registerTypeAdapter(SeasonType.class, new SeasonTypeDeserializer());
        builder.registerTypeAdapter(SummonerSpellType.class, new SummonerSpellTypeDeserializer());
        builder.registerTypeAdapter(TeamType.class, new TeamTypeDeserializer());
        builder.registerTypeAdapter(TierType.class, new TierTypeDeserializer());
        builder.registerTypeAdapter(TournamentMapType.class, new TournamentMapTypeDeserializer());
        builder.registerTypeAdapter(TournamentPickType.class, new TournamentPickTypeDeserializer());
        builder.registerTypeAdapter(TournamentSpectatorType.class, new TournamentSpectatorTypeDeserializer());
        builder.registerTypeAdapter(TowerType.class, new TowerTypeDeserializer());
        builder.registerTypeAdapter(WardType.class, new WardTypeDeserializer());
        gson = builder.create();
    }
    
    public static Gson getGson()
    {
        return gson;
    }
    
    public static String normalizeSummonerName(final String data)
    {
        try
        {
            final String stripped  = data.replaceAll("\\s+", "");
            final String lowercase = stripped.toLowerCase(Locale.ENGLISH);
            return URLEncoder.encode(lowercase, "UTF-8");
        } catch (final UnsupportedEncodingException e)
        {
            Utils.LOGGER.log(Level.WARNING, e.getMessage(), e);
        }
        return null;
    }
    
}
