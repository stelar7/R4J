package no.stelar7.api.r4j.pojo.lol.liveclient;

import java.util.*;

public class ActiveGamePlayer
{
    private String                         championName;
    private boolean                        isBot;
    private boolean                        isDead;
    private List<ActiveGameItem>           items;
    private int                            level;
    private String                         position;
    private String                         rawChampionName;
    private String                         rawSkinName;
    private float                          respawnTimer;
    private ActiveGamePlayerPerks          runes;
    private ActiveGamePlayerScores         scores;
    private int                            skinID;
    private String                         skinName;
    private String                         summonerName;
    private ActiveGamePlayerSummonerSpells summonerSpells;
    private String                         team;
    
    public String getChampionName()
    {
        return championName;
    }
    
    public boolean isBot()
    {
        return isBot;
    }
    
    public boolean isDead()
    {
        return isDead;
    }
    
    public List<ActiveGameItem> getItems()
    {
        return items;
    }
    
    public int getLevel()
    {
        return level;
    }
    
    public String getPosition()
    {
        return position;
    }
    
    public String getRawChampionName()
    {
        return rawChampionName;
    }
    
    public String getRawSkinName()
    {
        return rawSkinName;
    }
    
    public float getRespawnTimer()
    {
        return respawnTimer;
    }
    
    public ActiveGamePlayerPerks getRunes()
    {
        return runes;
    }
    
    public ActiveGamePlayerScores getScores()
    {
        return scores;
    }
    
    public int getSkinID()
    {
        return skinID;
    }
    
    public String getSkinName()
    {
        return skinName;
    }
    
    public String getSummonerName()
    {
        return summonerName;
    }
    
    public ActiveGamePlayerSummonerSpells getSummonerSpells()
    {
        return summonerSpells;
    }
    
    public String getTeam()
    {
        return team;
    }
    
    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        ActiveGamePlayer that = (ActiveGamePlayer) o;
        return isBot == that.isBot &&
               isDead == that.isDead &&
               level == that.level &&
               Float.compare(that.respawnTimer, respawnTimer) == 0 &&
               skinID == that.skinID &&
               Objects.equals(championName, that.championName) &&
               Objects.equals(items, that.items) &&
               Objects.equals(position, that.position) &&
               Objects.equals(rawChampionName, that.rawChampionName) &&
               Objects.equals(rawSkinName, that.rawSkinName) &&
               Objects.equals(runes, that.runes) &&
               Objects.equals(scores, that.scores) &&
               Objects.equals(skinName, that.skinName) &&
               Objects.equals(summonerName, that.summonerName) &&
               Objects.equals(summonerSpells, that.summonerSpells) &&
               Objects.equals(team, that.team);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(championName, isBot, isDead, items, level, position, rawChampionName, rawSkinName, respawnTimer, runes, scores, skinID, skinName, summonerName, summonerSpells, team);
    }
    
    @Override
    public String toString()
    {
        return "ActiveGamePlayer{" +
               "championName='" + championName + '\'' +
               ", isBot=" + isBot +
               ", isDead=" + isDead +
               ", items=" + items +
               ", level=" + level +
               ", position='" + position + '\'' +
               ", rawChampionName='" + rawChampionName + '\'' +
               ", rawSkinName='" + rawSkinName + '\'' +
               ", respawnTimer=" + respawnTimer +
               ", runes=" + runes +
               ", scores=" + scores +
               ", skinID=" + skinID +
               ", skinName='" + skinName + '\'' +
               ", summonerName='" + summonerName + '\'' +
               ", summonerSpells=" + summonerSpells +
               ", team='" + team + '\'' +
               '}';
    }
}
