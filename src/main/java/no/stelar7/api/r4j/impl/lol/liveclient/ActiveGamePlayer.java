package no.stelar7.api.r4j.impl.lol.liveclient;

import java.util.List;

public class ActiveGamePlayer
{
    String                   championName;
    boolean                  isBot;
    boolean                  isDead;
    List<ActiveGameItem>     items;
    int                      level;
    String                   position;
    String                   rawChampionName;
    String                   rawSkinName;
    float                 respawnTimer;
    ActiveGamePlayerPerks  runes;
    ActiveGamePlayerScores scores;
    int                    skinID;
    String                   skinName;
    String                         summonerName;
    ActiveGamePlayerSummonerSpells summonerSpells;
    String                         team;
    
}
