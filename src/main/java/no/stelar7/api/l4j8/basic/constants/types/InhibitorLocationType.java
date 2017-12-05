package no.stelar7.api.l4j8.basic.constants.types;

import no.stelar7.api.l4j8.basic.utils.Pair;

import java.util.*;

public final class InhibitorLocationType
{
    private InhibitorLocationType()
    {
        // empty constructor
    }
    
    private static final Map<MapType, Map<LaneType, Map<TeamType, Pair<Integer, Integer>>>> inhibs = new EnumMap<>(MapType.class);
    
    static
    {
        Map<LaneType, Map<TeamType, Pair<Integer, Integer>>> one;
        Map<TeamType, Pair<Integer, Integer>>                two;
        one = inhibs.getOrDefault(MapType.SUMMONERS_RIFT, new HashMap<>());
        
        two = one.getOrDefault(LaneType.BOT, new HashMap<>());
        two.put(TeamType.BLUE, new Pair<>(3452, 1236));
        two.put(TeamType.RED, new Pair<>(13604, 11316));
        one.put(LaneType.BOT, two);
        
        two = one.getOrDefault(LaneType.MID, new HashMap<>());
        two.put(TeamType.BLUE, new Pair<>(3203, 3208));
        two.put(TeamType.RED, new Pair<>(11598, 11667));
        one.put(LaneType.MID, two);
        
        two = one.getOrDefault(LaneType.TOP, new HashMap<>());
        two.put(TeamType.BLUE, new Pair<>(1171, 3571));
        two.put(TeamType.RED, new Pair<>(11261, 13676));
        one.put(LaneType.TOP, two);
        
        
        inhibs.put(MapType.SUMMONERS_RIFT, one);
    }
    
    public static Map<LaneType, Map<TeamType, Pair<Integer, Integer>>> getInhibMap(MapType map)
    {
        return inhibs.get(map);
    }
    
    public static List<Pair<Integer, Integer>> getInhibs(MapType map)
    {
        List<Pair<Integer, Integer>> retTowers = new ArrayList<>();
        
        inhibs.getOrDefault(map, new HashMap<>()).forEach((k, v) -> v.forEach((k2, v2) -> retTowers.add(v2)));
        
        return retTowers;
    }
}
