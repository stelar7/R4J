package no.stelar7.api.r4j.impl.lor;


import no.stelar7.api.r4j.basic.utils.*;
import no.stelar7.api.r4j.pojo.lor.offline.card.*;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class LoRDeckCode
{
    public static LoRDeck decode(String code)
    {
        LoRDeck    deck  = new LoRDeck();
        List<Byte> bytes = new ArrayList<>(Arrays.asList(Base32.decodeBoxed(code)));
        
        int firstByte = bytes.remove(0);
        int format    = firstByte >>> 4;
        int version   = firstByte & 0xF;
        
        int MAX_KNOWN_VERSION = 3;
        if (version > MAX_KNOWN_VERSION)
        {
            throw new IllegalArgumentException("The provided code requires a higher version of this library; please update");
        }
        
        for (int i = 3; i > 0; i--)
        {
            int groupCount = VarInt.pop(bytes);
            
            for (int j = 0; j < groupCount; j++)
            {
                int itemCount = VarInt.pop(bytes);
                int set       = VarInt.pop(bytes);
                int faction   = VarInt.pop(bytes);
                
                for (int k = 0; k < itemCount; k++)
                {
                    int    card          = VarInt.pop(bytes);
                    String setString     = Utils.padLeft(String.valueOf(set), "0", 2);
                    String factionString = LoRFaction.fromID(faction).getShortCode();
                    String cardString    = Utils.padLeft(String.valueOf(card), "0", 3);
                    
                    deck.addCard(LoRCard.create(setString, factionString, cardString), i);
                }
            }
        }
        
        while (bytes.size() > 0)
        {
            int count   = VarInt.pop(bytes);
            int set     = VarInt.pop(bytes);
            int faction = VarInt.pop(bytes);
            int number  = VarInt.pop(bytes);
            
            String setString     = Utils.padLeft(String.valueOf(set), "0", 2);
            String factionString = LoRFaction.fromID(faction).getShortCode();
            String numberString  = Utils.padLeft(String.valueOf(number), "0", 3);
            
            deck.addCard(LoRCard.create(setString, factionString, numberString), count);
        }
        
        return deck;
    }
    
    public static String encode(LoRDeck deck)
    {
        List<Integer> result = new ArrayList<>();
        
        if (deck.getDeck().containsKey(null))
        {
            return null;
        }
        
        // format and version = 0001 0011
        result.add(0b00010011);
        
        Map<Integer, List<Map.Entry<LoRCard, Integer>>> counts = deck.getDeck().entrySet().stream()
                                                                     .collect(Collectors.groupingBy(Entry::getValue));
        List<List<Map.Entry<LoRCard, Integer>>> grouped3 = groupByFactionAndSetSorted(counts.getOrDefault(3, new ArrayList<>()));
        List<List<Map.Entry<LoRCard, Integer>>> grouped2 = groupByFactionAndSetSorted(counts.getOrDefault(2, new ArrayList<>()));
        List<List<Map.Entry<LoRCard, Integer>>> grouped1 = groupByFactionAndSetSorted(counts.getOrDefault(1, new ArrayList<>()));
        List<Map.Entry<LoRCard, Integer>> nOfs = counts.entrySet().stream()
                                                       .filter(e -> e.getKey() > 3)
                                                       .flatMap(e -> e.getValue().stream())
                                                       .collect(Collectors.toList());
        
        result.addAll(encodeGroup(grouped3));
        result.addAll(encodeGroup(grouped2));
        result.addAll(encodeGroup(grouped1));
        result.addAll(encodeNofs(nOfs));
        
        return Base32.encodeBoxed(result);
    }
    
    private static List<Integer> encodeNofs(List<Map.Entry<LoRCard, Integer>> nOfs)
    {
        List<Integer> result = new ArrayList<>();
        
        for (Map.Entry<LoRCard, Integer> cc : nOfs)
        {
            result.addAll(VarInt.get(cc.getValue()));
            result.addAll(VarInt.get(cc.getKey().getSet()));
            result.addAll(VarInt.get(cc.getKey().getFaction().getId()));
            result.addAll(VarInt.get(cc.getKey().getId()));
        }
        
        return result;
    }
    
    private static List<Integer> encodeGroup(List<List<Map.Entry<LoRCard, Integer>>> group)
    {
        List<Integer> result = new ArrayList<>(VarInt.get(group.size()));
        
        for (List<Map.Entry<LoRCard, Integer>> list : group)
        {
            result.addAll(VarInt.get(list.size()));
            
            LoRCard firstCard = list.get(0).getKey();
            result.addAll(VarInt.get(firstCard.getSet()));
            result.addAll(VarInt.get(firstCard.getFaction().getId()));
            
            for (Map.Entry<LoRCard, Integer> cc : list)
            {
                result.addAll(VarInt.get(cc.getKey().getId()));
            }
        }
        
        return result;
    }
    
    private static List<List<Map.Entry<LoRCard, Integer>>> groupByFactionAndSetSorted(List<Map.Entry<LoRCard, Integer>> cards)
    {
        List<List<Map.Entry<LoRCard, Integer>>> result = new ArrayList<>();
        
        while (cards.size() > 0)
        {
            List<Map.Entry<LoRCard, Integer>> set = new ArrayList<>();
            
            Map.Entry<LoRCard, Integer> first     = cards.remove(0);
            LoRCard                     firstCard = first.getKey();
            set.add(first);
            
            for (int i = cards.size() - 1; i >= 0; i--)
            {
                LoRCard compareCard = cards.get(i).getKey();
                
                if (firstCard.getSet() == compareCard.getSet() && firstCard.getFaction() == compareCard.getFaction())
                {
                    set.add(cards.get(i));
                    cards.remove(i);
                }
            }
            
            result.add(set);
        }
        
        // sort outer list by size, then by inner list code, then sort inner list by code
        Comparator<List<Map.Entry<LoRCard, Integer>>> c  = Comparator.comparing(List::size);
        Comparator<List<Map.Entry<LoRCard, Integer>>> c2 = Comparator.comparing((List<Map.Entry<LoRCard, Integer>> a) -> a.get(0).getKey().getCardCode());
        result.sort(c.thenComparing(c2));
        for (List<Map.Entry<LoRCard, Integer>> group : result)
        {
            group.sort(Comparator.comparing(a -> a.getKey().getCardCode()));
        }
        
        return result;
    }
}
