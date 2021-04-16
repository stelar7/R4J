package no.stelar7.api.r4j.basic.constants.types.lol;

import no.stelar7.api.r4j.basic.constants.types.CodedEnum;
import no.stelar7.api.r4j.basic.utils.Pair;

import java.util.*;

public final class TowerLocationType implements CodedEnum
{
    private TowerLocationType()
    {
        // empty constructor
    }
    
    private static final Map<MapType, Map<LaneType, Map<TowerType, Map<TeamType, Pair<Integer, Integer>>>>> towers = new EnumMap<>(MapType.class);
    
    static
    {
        Map<LaneType, Map<TowerType, Map<TeamType, Pair<Integer, Integer>>>> one;
        Map<TowerType, Map<TeamType, Pair<Integer, Integer>>>                two;
        Map<TeamType, Pair<Integer, Integer>>                                three;
        one = towers.getOrDefault(MapType.SUMMONERS_RIFT, new HashMap<>());
        two = one.getOrDefault(LaneType.BOT, new HashMap<>());
        
        three = two.getOrDefault(TowerType.OUTER_TURRET, new HashMap<>());
        three.put(TeamType.BLUE, new Pair<>(10504, 1029));
        three.put(TeamType.RED, new Pair<>(13866, 4505));
        two.put(TowerType.OUTER_TURRET, three);
        
        three = two.getOrDefault(TowerType.INNER_TURRET, new HashMap<>());
        three.put(TeamType.BLUE, new Pair<>(6919, 1483));
        three.put(TeamType.RED, new Pair<>(13327, 8226));
        two.put(TowerType.INNER_TURRET, three);
        
        three = two.getOrDefault(TowerType.BASE_TURRET, new HashMap<>());
        three.put(TeamType.BLUE, new Pair<>(4281, 1253));
        three.put(TeamType.RED, new Pair<>(13624, 10572));
        two.put(TowerType.BASE_TURRET, three);
        
        three = two.getOrDefault(TowerType.NEXUS_TURRET, new HashMap<>());
        three.put(TeamType.BLUE, new Pair<>(2177, 1807));
        three.put(TeamType.RED, new Pair<>(13052, 12612));
        two.put(TowerType.NEXUS_TURRET, three);
        one.put(LaneType.BOT, two);
        
        two = one.getOrDefault(LaneType.MID, new HashMap<>());
        three = two.getOrDefault(TowerType.OUTER_TURRET, new HashMap<>());
        three.put(TeamType.BLUE, new Pair<>(5846, 6396));
        three.put(TeamType.RED, new Pair<>(8955, 8510));
        two.put(TowerType.OUTER_TURRET, three);
        
        three = two.getOrDefault(TowerType.INNER_TURRET, new HashMap<>());
        three.put(TeamType.BLUE, new Pair<>(5048, 4812));
        three.put(TeamType.RED, new Pair<>(9767, 10113));
        two.put(TowerType.INNER_TURRET, three);
        
        three = two.getOrDefault(TowerType.BASE_TURRET, new HashMap<>());
        three.put(TeamType.BLUE, new Pair<>(3651, 3696));
        three.put(TeamType.RED, new Pair<>(11134, 11207));
        two.put(TowerType.BASE_TURRET, three);
        one.put(LaneType.MID, two);
        
        two = one.getOrDefault(LaneType.TOP, new HashMap<>());
        three = two.getOrDefault(TowerType.OUTER_TURRET, new HashMap<>());
        three.put(TeamType.BLUE, new Pair<>(981, 10441));
        three.put(TeamType.RED, new Pair<>(4318, 13875));
        two.put(TowerType.OUTER_TURRET, three);
        
        three = two.getOrDefault(TowerType.INNER_TURRET, new HashMap<>());
        three.put(TeamType.BLUE, new Pair<>(1512, 6699));
        three.put(TeamType.RED, new Pair<>(7943, 13411));
        two.put(TowerType.INNER_TURRET, three);
        
        three = two.getOrDefault(TowerType.BASE_TURRET, new HashMap<>());
        three.put(TeamType.BLUE, new Pair<>(1169, 4287));
        three.put(TeamType.RED, new Pair<>(10481, 13650));
        two.put(TowerType.BASE_TURRET, three);
        
        three = two.getOrDefault(TowerType.NEXUS_TURRET, new HashMap<>());
        three.put(TeamType.BLUE, new Pair<>(1748, 2270));
        three.put(TeamType.RED, new Pair<>(12611, 13084));
        two.put(TowerType.NEXUS_TURRET, three);
        one.put(LaneType.TOP, two);
        
        towers.put(MapType.SUMMONERS_RIFT, one);
    }
    
    public static Map<LaneType, Map<TowerType, Map<TeamType, Pair<Integer, Integer>>>> getTowersMap(MapType map)
    {
        return towers.get(map);
    }
    
    public static List<Pair<Integer, Integer>> getTowers(MapType map)
    {
        List<Pair<Integer, Integer>> retTowers = new ArrayList<>();
        
        towers.getOrDefault(map, new HashMap<>()).forEach((k, v) -> v.forEach((k1, v1) -> v1.forEach((k2, v2) -> retTowers.add(v2))));
        
        return retTowers;
    }
    
    @Override
    public Optional<TowerLocationType> getFromCode(String type)
    {
        return Optional.empty();
    }
    
    @Override
    public String prettyName()
    {
        return null;
    }
}
