package no.stelar7.api.l4j8.tests.div;

import com.google.gson.reflect.TypeToken;
import no.stelar7.api.l4j8.basic.cache.impl.FileSystemCacheProvider;
import no.stelar7.api.l4j8.basic.calling.DataCall;
import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.basic.constants.types.*;
import no.stelar7.api.l4j8.basic.utils.*;
import no.stelar7.api.l4j8.impl.L4J8;
import no.stelar7.api.l4j8.pojo.match.*;
import no.stelar7.api.l4j8.pojo.staticdata.champion.StaticChampion;
import no.stelar7.api.l4j8.pojo.summoner.Summoner;
import no.stelar7.api.l4j8.tests.SecretFile;
import org.junit.Test;

import javax.annotation.Nullable;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

/*
    Python code taken from https://github.com/meraki-analytics/role-identification/ (see link for lisence)
    Derived work covered under Apache2
 */


public class RoleIdentificationTest
{
    class ChampionGGData
    {
        class IDContainer
        {
            private int championId;
        }
        
        private IDContainer  _id;
        private LaneRoleType role;
        private float        playRate;
    }
    
    static class ParticipantMatchPositions
    {
        public ParticipantMatchPositions(Integer top, Integer jungle, Integer mid, Integer adc, Integer sup)
        {
            this.top = top;
            this.jungle = jungle;
            this.mid = mid;
            this.adc = adc;
            this.sup = sup;
        }
        
        private Integer top;
        private Integer jungle;
        private Integer mid;
        private Integer adc;
        private Integer sup;
        
        public Map<LaneRoleType, Integer> getFields()
        {
            Map<LaneRoleType, Integer> data = new HashMap<>();
            data.put(LaneRoleType.TOP, top);
            data.put(LaneRoleType.JUNGLE, jungle);
            data.put(LaneRoleType.MID, mid);
            data.put(LaneRoleType.DUO_CARRY, adc);
            data.put(LaneRoleType.DUO_SUPPORT, sup);
            
            data.values().removeAll(Collections.singleton(null));
            
            return data;
        }
        
        
        public static ParticipantMatchPositions fromMap(Map<LaneRoleType, Integer> fixed)
        {
            return new ParticipantMatchPositions(fixed.get(LaneRoleType.TOP), fixed.get(LaneRoleType.JUNGLE), fixed.get(LaneRoleType.MID), fixed.get(LaneRoleType.DUO_CARRY), fixed.get(LaneRoleType.DUO_SUPPORT));
        }
    }
    
    
    L4J8                         api;
    Map<Integer, StaticChampion> staticChampionData;
    
    {
        api = new L4J8(SecretFile.CREDS);
        DataCall.setLogLevel(LogLevel.INFO);
        DataCall.setCacheProvider(new FileSystemCacheProvider());
        
        staticChampionData = api.getStaticAPI().getChampions(Platform.NA1, null, null, null);
    }
    
    private Map<Integer, Map<LaneRoleType, Float>> loadChampionGGData()
    {
        
        String               data = Utils.getURLData("http://api.champion.gg/v2/champions?limit=2000&champData=play_rate&api_key=" + SecretFile.CHAMPION_GG_API_KEY);
        List<ChampionGGData> list = Utils.getGson().fromJson(data, new TypeToken<List<ChampionGGData>>() {}.getType());
        
        Map<Integer, Map<LaneRoleType, Float>> returnValue = new HashMap<>();
        for (ChampionGGData cgg : list)
        {
            Integer      champion = staticChampionData.get(cgg._id.championId).getId();
            float        playRate = cgg.playRate;
            LaneRoleType role     = cgg.role;
            
            Map<LaneRoleType, Float> inner = returnValue.getOrDefault(champion, new HashMap<>());
            inner.put(role, playRate);
            
            returnValue.put(champion, inner);
        }
        
        returnValue.forEach((k, v) -> {
            int   countMissing = 0;
            float totalMissing = 1;
            
            for (LaneRoleType role : LaneRoleType.values())
            {
                if (!v.containsKey(role))
                {
                    countMissing++;
                } else
                {
                    totalMissing -= v.get(role);
                }
            }
            
            float missingPerUnCountedRole = totalMissing / countMissing;
            
            for (LaneRoleType role : LaneRoleType.values())
            {
                if (!v.containsKey(role))
                {
                    returnValue.get(k).put(role, -1 + missingPerUnCountedRole);
                }
            }
        });
        
        return returnValue;
    }
    
    
    private Map<Integer, Map<LaneRoleType, Float>> getChampionGGRates()
    {
        Map<Integer, Map<LaneRoleType, Float>> data = loadChampionGGData();
        System.out.println();
        return null;
    }
    
    private boolean hasSmite(Participant p)
    {
        return p.getSpell1() == SummonerSpellType.SMITE || p.getSpell2() == SummonerSpellType.SMITE;
    }
    
    private QuadObject getTeamRoles(Match match, TeamType team, @Nullable Map<Integer, Map<LaneRoleType, Float>> roleData, boolean verbose)
    {
        if (roleData == null)
        {
            roleData = loadChampionGGData();
        }
        
        List<Integer>     champions = match.getParticipants(team).stream().map(Participant::getChampionId).collect(Collectors.toList());
        List<Participant> smiteList = match.getParticipants(team).stream().filter(this::hasSmite).collect(Collectors.toList());
        
        ParticipantMatchPositions pos = new ParticipantMatchPositions(null, smiteList.size() == 0 ? null : smiteList.get(0).getChampionId(), null, null, null);
        
        QuadObject rpcs = iterativeGetRoles(roleData, champions, pos, verbose);
        
        float prob       = Float.parseFloat(Utils.getDecimalFormat().format(rpcs.getProbability() * 100));
        float confidence = Float.parseFloat(Utils.getDecimalFormat().format(rpcs.getConfidence() * 100));
        
        return new QuadObject(rpcs.getRoles(), prob, confidence, rpcs.getSecondary());
        
    }
    
    private QuadObject iterativeGetRoles(Map<Integer, Map<LaneRoleType, Float>> roleData, List<Integer> comp, ParticipantMatchPositions pos, boolean verbose)
    {
        Map<LaneRoleType, Integer>             fixed     = pos.getFields();
        Map<Integer, Map<LaneRoleType, Float>> _roleData = new HashMap<>(roleData);
        
        Map<LaneRoleType, Integer> roles           = new HashMap<>();
        Map<LaneRoleType, Integer> secondBestRoles = null;
        
        float prob           = Float.NEGATIVE_INFINITY;
        float secondBestProb = Float.NEGATIVE_INFINITY;
        
        while (fixed.keySet().size() < 4)
        {
            for (LaneRoleType roleType : fixed.keySet())
            {
                for (Entry<Integer, Map<LaneRoleType, Float>> entry : _roleData.entrySet())
                {
                    Integer                  k = entry.getKey();
                    Map<LaneRoleType, Float> v = entry.getValue();
                    
                    if (fixed.values().contains(k))
                    {
                        continue;
                    }
                    
                    float playRate = v.get(roleType);
                    v.put(roleType, (float) -1);
                    
                    if (playRate > 0)
                    {
                        int   rolesLeft    = (int) v.values().stream().filter(val -> val > 0).count();
                        float toDistribute = 0;
                        if (rolesLeft > 0)
                        {
                            toDistribute = playRate / rolesLeft;
                        }
                        
                        for (Entry<LaneRoleType, Float> playrates : v.entrySet())
                        {
                            if (playrates.getValue() < 0)
                            {
                                continue;
                            }
                            
                            playrates.setValue(playrates.getValue() + toDistribute);
                        }
                    }
                }
            }
            
            
            // roles, probability, confidence, position
            QuadObject rpcs = getRoles(_roleData, comp, ParticipantMatchPositions.fromMap(fixed), verbose);
            roles = rpcs.getRoles();
            prob = rpcs.getProbability();
            
            QuadObject _rpcs;
            if (rpcs.getSecondary() != null)
            {
                _rpcs = getRoles(_roleData, comp, ParticipantMatchPositions.fromMap(rpcs.getSecondary()), verbose);
                
                if (prob > _rpcs.getProbability() && _rpcs.getProbability() > secondBestProb)
                {
                    secondBestProb = _rpcs.getProbability();
                    secondBestRoles = rpcs.getSecondary();
                }
            }
            
            List<Entry<LaneRoleType, Integer>> best = roles.entrySet().stream()
                                                           .filter(e -> !fixed.containsKey(e.getKey()))
                                                           .sorted((a, b) -> getSortOrder(roleData, a, b))
                                                           .collect(Collectors.toList());
            
            Entry<LaneRoleType, Integer> bbest = best.stream().findFirst().get();
            fixed.put(bbest.getKey(), bbest.getValue());
        }
        
        float confidence = (prob - secondBestProb) / prob;
        if (verbose)
        {
            for (LaneRoleType role : LaneRoleType.values())
            {
                System.out.format("%s: %s  (%s%%)%n", role, staticChampionData.get(roles.get(role)).getName(), Utils.getDecimalFormat().format(100 * roleData.get(roles.get(role)).get(role)));
            }
            
            System.out.format("Probability: %s%n", Utils.getDecimalFormat().format(100 * prob));
            
            if (secondBestRoles == null)
            {
                System.out.format("Confidence: %s%n", Utils.getDecimalFormat().format(100 * confidence));
            } else
            {
                StringJoiner alternative = new StringJoiner(", ");
                for (LaneRoleType role : LaneRoleType.values())
                {
                    if (!roles.get(role).equals(secondBestRoles.get(role)))
                    {
                        alternative.add(String.format("%s: %s", role, staticChampionData.get(secondBestRoles.get(role)).getName()));
                    }
                }
                System.out.format("Confidence: %s (Alternative is %s)%n", Utils.getDecimalFormat().format(100 * confidence), alternative);
            }
        }
        
        return new QuadObject(roles, prob, confidence, secondBestRoles);
    }
    
    private int getSortOrder(Map<Integer, Map<LaneRoleType, Float>> roleData, Entry<LaneRoleType, Integer> a, Entry<LaneRoleType, Integer> b)
    {
        return roleData.get(a.getValue()).get(a.getKey()) > roleData.get(b.getValue()).get(b.getKey()) ? 1 : -1;
    }
    
    private QuadObject getRoles(Map<Integer, Map<LaneRoleType, Float>> roleData, List<Integer> comp, ParticipantMatchPositions positions, boolean verbose)
    {
        Map<LaneRoleType, Integer> bestRoles       = new HashMap<>();
        Map<LaneRoleType, Integer> secondBestRoles = null;
        
        Map<LaneRoleType, Float> bestPlay       = new HashMap<>();
        Map<LaneRoleType, Float> secondBestPlay = null;
        
        float bestMetric;
        float secondBestMetric = Float.NEGATIVE_INFINITY;
        
        if (!positions.getFields().containsValue(null) && positions.getFields().size() == 5)
        {
            bestRoles.put(LaneRoleType.TOP, positions.top);
            bestRoles.put(LaneRoleType.JUNGLE, positions.jungle);
            bestRoles.put(LaneRoleType.MID, positions.mid);
            bestRoles.put(LaneRoleType.DUO_CARRY, positions.adc);
            bestRoles.put(LaneRoleType.DUO_SUPPORT, positions.sup);
            
            bestPlay.put(LaneRoleType.TOP, roleData.get(positions.top).get(LaneRoleType.TOP));
            bestPlay.put(LaneRoleType.JUNGLE, roleData.get(positions.jungle).get(LaneRoleType.JUNGLE));
            bestPlay.put(LaneRoleType.MID, roleData.get(positions.mid).get(LaneRoleType.MID));
            bestPlay.put(LaneRoleType.DUO_CARRY, roleData.get(positions.adc).get(LaneRoleType.DUO_CARRY));
            bestPlay.put(LaneRoleType.DUO_SUPPORT, roleData.get(positions.sup).get(LaneRoleType.DUO_SUPPORT));
            
            bestMetric = (float) (bestPlay.values().stream().mapToDouble(i -> i).sum() / 5);
        } else
        {
            bestRoles.put(LaneRoleType.TOP, comp.get(0));
            bestRoles.put(LaneRoleType.JUNGLE, comp.get(1));
            bestRoles.put(LaneRoleType.MID, comp.get(2));
            bestRoles.put(LaneRoleType.DUO_CARRY, comp.get(3));
            bestRoles.put(LaneRoleType.DUO_SUPPORT, comp.get(4));
            
            bestPlay.put(LaneRoleType.TOP, roleData.get(comp.get(0)).get(LaneRoleType.TOP));
            bestPlay.put(LaneRoleType.JUNGLE, roleData.get(comp.get(1)).get(LaneRoleType.JUNGLE));
            bestPlay.put(LaneRoleType.MID, roleData.get(comp.get(2)).get(LaneRoleType.MID));
            bestPlay.put(LaneRoleType.DUO_CARRY, roleData.get(comp.get(3)).get(LaneRoleType.DUO_CARRY));
            bestPlay.put(LaneRoleType.DUO_SUPPORT, roleData.get(comp.get(4)).get(LaneRoleType.DUO_SUPPORT));
            
            bestMetric = (float) (bestPlay.values().stream().mapToDouble(i -> i).sum() / 5);
            
            secondBestRoles = new HashMap<>();
            secondBestRoles.put(LaneRoleType.TOP, comp.get(0));
            secondBestRoles.put(LaneRoleType.JUNGLE, comp.get(1));
            secondBestRoles.put(LaneRoleType.MID, comp.get(2));
            secondBestRoles.put(LaneRoleType.DUO_CARRY, comp.get(3));
            secondBestRoles.put(LaneRoleType.DUO_SUPPORT, comp.get(4));
            
            secondBestPlay = new HashMap<>();
            secondBestPlay.put(LaneRoleType.TOP, roleData.get(comp.get(0)).get(LaneRoleType.TOP));
            secondBestPlay.put(LaneRoleType.JUNGLE, roleData.get(comp.get(1)).get(LaneRoleType.JUNGLE));
            secondBestPlay.put(LaneRoleType.MID, roleData.get(comp.get(2)).get(LaneRoleType.MID));
            secondBestPlay.put(LaneRoleType.DUO_CARRY, roleData.get(comp.get(3)).get(LaneRoleType.DUO_CARRY));
            secondBestPlay.put(LaneRoleType.DUO_SUPPORT, roleData.get(comp.get(4)).get(LaneRoleType.DUO_SUPPORT));
            
            secondBestMetric = (float) (bestPlay.values().stream().mapToDouble(i -> i).sum() / 5);
            List<List<Integer>> championPermutations = Permutations.of(comp).map(s -> s.collect(Collectors.toList())).collect(Collectors.toList());
            
            
            for (List<Integer> champs : championPermutations)
            {
                Map<LaneRoleType, Float> roles = new HashMap<>();
                roles.put(LaneRoleType.TOP, roleData.get(champs.get(0)).get(LaneRoleType.TOP));
                roles.put(LaneRoleType.JUNGLE, roleData.get(champs.get(1)).get(LaneRoleType.JUNGLE));
                roles.put(LaneRoleType.MID, roleData.get(champs.get(2)).get(LaneRoleType.MID));
                roles.put(LaneRoleType.DUO_CARRY, roleData.get(champs.get(3)).get(LaneRoleType.DUO_CARRY));
                roles.put(LaneRoleType.DUO_SUPPORT, roleData.get(champs.get(4)).get(LaneRoleType.DUO_SUPPORT));
                
                
                if (positions.top != null && !champs.get(0).equals(positions.top))
                {
                    continue;
                }
                if (positions.jungle != null && !champs.get(1).equals(positions.jungle))
                {
                    continue;
                }
                if (positions.mid != null && !champs.get(2).equals(positions.mid))
                {
                    continue;
                }
                if (positions.adc != null && !champs.get(3).equals(positions.adc))
                {
                    continue;
                }
                if (positions.sup != null && !champs.get(4).equals(positions.sup))
                {
                    continue;
                }
                
                float metric = (float) (roles.values().stream().mapToDouble(i -> i).sum() / 5);
                
                if (metric > bestMetric)
                {
                    secondBestMetric = bestMetric;
                    secondBestRoles = bestRoles;
                    bestMetric = metric;
                    
                    bestRoles = new HashMap<>();
                    bestRoles.put(LaneRoleType.TOP, champs.get(0));
                    bestRoles.put(LaneRoleType.JUNGLE, champs.get(1));
                    bestRoles.put(LaneRoleType.MID, champs.get(2));
                    bestRoles.put(LaneRoleType.DUO_CARRY, champs.get(3));
                    bestRoles.put(LaneRoleType.DUO_SUPPORT, champs.get(4));
                    
                    bestPlay = new HashMap<>();
                    bestPlay.put(LaneRoleType.TOP, roleData.get(champs.get(0)).get(LaneRoleType.TOP));
                    bestPlay.put(LaneRoleType.JUNGLE, roleData.get(champs.get(1)).get(LaneRoleType.JUNGLE));
                    bestPlay.put(LaneRoleType.MID, roleData.get(champs.get(2)).get(LaneRoleType.MID));
                    bestPlay.put(LaneRoleType.DUO_CARRY, roleData.get(champs.get(3)).get(LaneRoleType.DUO_CARRY));
                    bestPlay.put(LaneRoleType.DUO_SUPPORT, roleData.get(champs.get(4)).get(LaneRoleType.DUO_SUPPORT));
                }
                
                if (bestMetric > metric && metric > secondBestMetric)
                {
                    secondBestMetric = metric;
                    
                    secondBestRoles = new HashMap<>();
                    secondBestRoles.put(LaneRoleType.TOP, champs.get(0));
                    secondBestRoles.put(LaneRoleType.JUNGLE, champs.get(1));
                    secondBestRoles.put(LaneRoleType.MID, champs.get(2));
                    secondBestRoles.put(LaneRoleType.DUO_CARRY, champs.get(3));
                    secondBestRoles.put(LaneRoleType.DUO_SUPPORT, champs.get(4));
                    
                    secondBestPlay = new HashMap<>();
                    secondBestPlay.put(LaneRoleType.TOP, roleData.get(champs.get(0)).get(LaneRoleType.TOP));
                    secondBestPlay.put(LaneRoleType.JUNGLE, roleData.get(champs.get(1)).get(LaneRoleType.JUNGLE));
                    secondBestPlay.put(LaneRoleType.MID, roleData.get(champs.get(2)).get(LaneRoleType.MID));
                    secondBestPlay.put(LaneRoleType.DUO_CARRY, roleData.get(champs.get(3)).get(LaneRoleType.DUO_CARRY));
                    secondBestPlay.put(LaneRoleType.DUO_SUPPORT, roleData.get(champs.get(4)).get(LaneRoleType.DUO_SUPPORT));
                }
            }
        }
        
        if (secondBestRoles == null || secondBestRoles.equals(bestRoles))
        {
            secondBestRoles = null;
            secondBestPlay = null;
            secondBestMetric = Float.NEGATIVE_INFINITY;
        }
        
        int          badAssignments = (int) bestPlay.values().stream().filter(x -> x < 0).count();
        int          badSecondary   = 0;
        boolean      foundAlt       = secondBestPlay != null;
        StringJoiner alternative    = new StringJoiner(", ");
        
        float confidence = (bestMetric - secondBestMetric) / bestMetric;
        if (foundAlt)
        {
            StringBuilder sb = new StringBuilder();
            for (LaneRoleType role : LaneRoleType.values())
            {
                if (!bestRoles.get(role).equals(secondBestRoles.get(role)))
                {
                    alternative.add(String.format("%s: %s", role, staticChampionData.get(secondBestRoles.get(role)).getName()));
                }
            }
        }
        
        if (verbose)
        {
            for (LaneRoleType role : LaneRoleType.values())
            {
                System.out.format("%s: %s  (%s%%)%n", role, staticChampionData.get(bestRoles.get(role)).getName(), Utils.getDecimalFormat().format(100 * roleData.get(bestRoles.get(role)).get(role)));
            }
            
            System.out.format("Probability: %s%n", Utils.getDecimalFormat().format(100 * bestMetric));
            if (!foundAlt)
            {
                System.out.format("Confidence: %s%n", Utils.getDecimalFormat().format(100 * confidence));
            } else
            {
                System.out.format("Confidence: %s (Alternative is %s)%n", Utils.getDecimalFormat().format(100 * confidence), alternative);
            }
        }
        
        return new QuadObject(bestRoles, bestMetric, confidence, secondBestRoles);
    }
    
    private boolean matchesRole(Participant p, Map<LaneRoleType, Integer> roles, LaneRoleType lane)
    {
        return p.getChampionId() == roles.get(lane);
    }
    
    private void printResults(Match match, TeamType team, Map<LaneRoleType, Integer> roles, float prob, float conf, @Nullable Map<LaneRoleType, Integer> secondBest)
    {
        StringJoiner alternative = new StringJoiner(", ");
        if (secondBest != null)
        {
            for (LaneRoleType role : LaneRoleType.values())
            {
                if (!roles.get(role).equals(secondBest.get(role)))
                {
                    alternative.add(String.format("%s: %s", role, staticChampionData.get(secondBest.get(role)).getName()));
                }
            }
        }
        
        Participant toplaner = match.getParticipants(team).stream().filter(p -> matchesRole(p, roles, LaneRoleType.TOP)).findAny().get();
        Participant jungler  = match.getParticipants(team).stream().filter(p -> matchesRole(p, roles, LaneRoleType.JUNGLE)).findAny().get();
        Participant midlaner = match.getParticipants(team).stream().filter(p -> matchesRole(p, roles, LaneRoleType.MID)).findAny().get();
        Participant carry    = match.getParticipants(team).stream().filter(p -> matchesRole(p, roles, LaneRoleType.DUO_CARRY)).findAny().get();
        Participant helper   = match.getParticipants(team).stream().filter(p -> matchesRole(p, roles, LaneRoleType.DUO_SUPPORT)).findAny().get();
        
        String top = String.format("%s (%s %s)", staticChampionData.get(roles.get(LaneRoleType.TOP)).getName(), toplaner.getSpell1(), toplaner.getSpell2());
        String jun = String.format("%s (%s %s)", staticChampionData.get(roles.get(LaneRoleType.JUNGLE)).getName(), jungler.getSpell1(), jungler.getSpell2());
        String mid = String.format("%s (%s %s)", staticChampionData.get(roles.get(LaneRoleType.MID)).getName(), midlaner.getSpell1(), midlaner.getSpell2());
        String adc = String.format("%s (%s %s)", staticChampionData.get(roles.get(LaneRoleType.DUO_CARRY)).getName(), carry.getSpell1(), carry.getSpell2());
        String sup = String.format("%s (%s %s)", staticChampionData.get(roles.get(LaneRoleType.DUO_SUPPORT)).getName(), helper.getSpell1(), helper.getSpell2());
        
        System.out.format("Probability: %s%%%nConfidence: %s%%%nTop: %s%nJungle: %s%nMid: %s%nADC: %s%nSupport: %s%nAlternative roles: [%s]%nMatch id: %s:%s%nTeamid: %s%n",
                          prob, conf,
                          top, jun, mid, adc, sup,
                          alternative.toString(),
                          match.getPlatform(),
                          match.getMatchId(),
                          String.format("%s (%s)", team, team.getValue()));
    }
    
    
    @Test
    public void testAll()
    {
        Summoner                 sum  = api.getSummonerAPI().getSummonerByName(Platform.EUW1, "stelar7");
        LazyList<MatchReference> refs = api.getMatchAPI().getMatchList(Platform.EUW1, sum.getAccountId());
        
        for (MatchReference ref : refs)
        {
            Match      full = ref.getFullMatch();
            TeamType   team = full.getParticipantFromSummonerId(sum.getSummonerId()).getTeam();
            QuadObject rpcs = getTeamRoles(full, team, null, false);
            printResults(full, team, rpcs.getRoles(), rpcs.getProbability(), rpcs.getConfidence(), rpcs.getSecondary());
            System.out.println();
        }
    }
}
